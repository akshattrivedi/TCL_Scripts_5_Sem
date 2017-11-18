BEGIN {
	PacketRcvd = 0;
	Throughput = 0.0;
}
{
	if(($1 == "r")&&($5=="tcp")&&($10==4.0))
	{
		PacketRcvd++;
	}
}
END{
	Throughput=((PacketRcvd*1000*8)/(95.0*1000000));
	printf("Packet received :%f\n",PacketRcvd);
	printf("The throughput is: %f\n",Throughput);
}	
