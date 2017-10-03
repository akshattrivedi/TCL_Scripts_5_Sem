#!/usr/bin/awk -f

BEGIN{
	dropPkt=0;
}

{
	if($1=="d") {dropPkt++;}
	
}

END{
	printf("No of packets dropped due to congestion : %d \n", dropPkt);
}
