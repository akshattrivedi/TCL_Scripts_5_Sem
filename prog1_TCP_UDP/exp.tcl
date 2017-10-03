set ns [new Simulator]
set tr [open out.tr w]
set nam [open out.nam w]
$ns trace-all $tr
$ns namtrace-all $nam
proc finish {} {
	global ns tr nam
	$ns flush-trace
	close $tr
	close $nam
	exec nam out.nam &
	exit(0)
}

set n0 [$ns node]
set n1 [$ns node]
set n2 [$ns node]
set n3 [$ns node]
set n4 [$ns node]
set n5 [$ns node]

$n0 label "TCP Source"
$n1 label "UDP Source"
$n2 label "R1"
$n3 label "R2"
$n4 label "TCP Sink"
$n5 label "Null"

$ns color 1 "Red"
$ns color 2 "Green"

$ns duplex-link $n0 $n2 2.0Mb 10ms DropTail
$ns duplex-link $n1 $n2 2.0Mb 10ms DropTail
$ns duplex-link $n2 $n3 2.5Mb 10ms DropTail
$ns duplex-link $n3 $n4 2.0Mb 10ms DropTail
$ns duplex-link $n3 $n5 2.0Mb 10ms DropTail

$ns queue-limit $n2 $n3 10

set tcp0 [new Agent/TCP]
$ns attach-agent $n0 $tcp0

set ftp0 [new Application/FTP]
$ftp0 attach-agent $tcp0

set sink4 [new Agent/TCPSink]
$ns attach-agent $n4 $sink4

$ns connect $tcp0 $sink4

set udp1 [new Agent/UDP]
$ns attach-agent $n1 $udp1

set cbr1 [new Application/Traffic/CBR]
$cbr1 attach-agent $udp1

set null5 [new Agent/Null]
$ns attach-agent $n5 $null5

$ns connect $udp1 $null5

$ftp0 set packetSize_ 1000

$cbr1 set packetSize_ 500
$cbr1 set interval_ 0.002

$tcp0 set class_ 1
$udp1 set class_ 2

$ns at 1.0 "$cbr1 start"
$ns at 2.0 "$ftp0 start"
$ns at 8.0 "$ftp0 stop"
$ns at 9.0 "$cbr1 stop"
$ns at 10.0 "finish"

$ns run
