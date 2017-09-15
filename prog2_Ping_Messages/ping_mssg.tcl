set ns [new Simulator]
set tr [open out3.tr w]
set nf [open out3.nam w]
$ns trace-all $tr
$ns namtrace-all $nf

$ns color 1 "Blue"
$ns color 2 "Green"
$ns color 3 "Red"
$ns color 4 "Yellow"

proc finish { } {
	global ns tr nf
	$ns flush-trace
	close $tr
	close $nf
	exec nam out3.nam &
	exit 0
}

set n0 [$ns node]
set n1 [$ns node]
set n2 [$ns node]
set n3 [$ns node]
set n4 [$ns node]
set n5 [$ns node]

$ns duplex-link $n0 $n2 2Mb 10ms DropTail
$ns duplex-link $n1 $n2 2Mb 10ms DropTail
$ns duplex-link $n2 $n3 0.2Mb 10ms DropTail
$ns duplex-link $n3 $n4 2Mb 10ms DropTail
$ns duplex-link $n3 $n5 2Mb 10ms DropTail

$ns queue-limit $n2 $n3 10

$ns duplex-link-op $n0 $n2 orient right-down
$ns duplex-link-op $n1 $n2 orient right-up
$ns duplex-link-op $n2 $n3 orient right
$ns duplex-link-op $n3 $n4 orient right-up
$ns duplex-link-op $n3 $n5 orient right-down

set ping0 [new Agent/Ping]
set ping1 [new Agent/Ping]
set ping4 [new Agent/Ping]
set ping5 [new Agent/Ping]

$ping0 set class_ 1
$ping1 set class_ 2
$ping4 set class_ 3
$ping5 set class_ 4

$ns attach-agent $n0 $ping0
$ns attach-agent $n1 $ping1
$ns attach-agent $n4 $ping4
$ns attach-agent $n5 $ping5

$ns connect $ping0 $ping5
$ns connect $ping1 $ping4
$ns connect $ping4 $ping0
$ns connect $ping5 $ping1

proc sendingPacket { } {
	global ns ping0 ping1 ping4 ping5
	set pinginterval 0.01
	set now [$ns now]
	#global ns ping0 ping1 ping4 ping5
	$ns at [expr $now +$pinginterval] "$ping0 send"
	$ns at [expr $now +$pinginterval] "$ping1 send"
	$ns at [expr $now +$pinginterval] "$ping4 send"
	$ns at [expr $now +$pinginterval] "$ping5 send"
	$ns at [expr $now +$pinginterval] "sendingPacket"
} 

set seq 1
Agent/Ping instproc recv { from rtt } {
	global seq
	$self instvar node_
	puts "node [$node_ id] received ping answer from  $from with round-trip-time $rtt ms"
	set seq [expr $seq+1]
}

$ns at 0.01 "sendingPacket"
$ns rtmodel-at 3.0 down $n2 $n3
$ns rtmodel-at 5.0 up $n2 $n3
$ns at 10.0 "finish"
$ns run






































