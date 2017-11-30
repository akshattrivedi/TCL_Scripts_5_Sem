 AWK FILE :

 BEGIN{
	 Pkt_rcvd=0;
	 Thruput=0;
 }
 {
	 	if(($1=="r") && ($5=="tcp") && ($10=4.0))
				{
							Pkt_rcvd++;
								}
 }
 END{
	 	Thruput=((Pkt_rcvd*1000*8)/(95.0*1000000))
			printf("Packet received: %f\n",Pkt_rcvd);
				printf("The throughput is:%f\n",Thruput);
}

Xgraph :

TitileText: CDMA
YUnitText: Throughput
XUnitText: Bandwidth

100000	0.367074
150000	0.526905
200000	0.561263
250000	0.663663
300000	1.113432
350000	1.185347
400000	1.196716
450000	1.205137
500000	1.212632

