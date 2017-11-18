import java.util.Scanner;

class leaky
{
	public static void main(String args[])
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("ENTER BUCKET SIZE"); 
		int bSize, tRate, tTime;
		bSize = sc.nextInt();
		System.out.println("ENTER BANDWIDTH");
		tRate = sc.nextInt();
		System.out.println("ENTER TRANSMISSION TIME");
		tTime = sc.nextInt();
		
		int inp[] = new int[50];
		System.out.println("Enter data sent at each second");
		for(int i = 0; i < tTime; i++)
			inp[i] = sc.nextInt();
		
		for(int i = tTime; i < 50; i++)
			inp[i] = 0;
		
		int queData = 0;
		int recv[] = new int[50];
		int drop[] = new int[50];
		int rem[] = new int[50];
		
		for(int i = 0; i < 50; i++)
		{
			queData += inp[i];
			recv[i] = 0;	
			drop[i] = 0;
			rem[i] = 0;		
	
			if(queData <= tRate)
			{
				recv[i] = queData;
				queData = 0;
			//	recv[i] = tRate[i];
			//	queData -= inp[i];
			}
			else if(queData <= bSize)
			{
				recv[i] = tRate;
			//	recv[i] = inp[i];	
				rem[i] = queData - tRate;
				queData -= tRate;
			}
			else
			{
				recv[i] = tRate;
				drop[i] = queData - bSize;
				rem[i] = bSize - tRate;
				queData = bSize - tRate;
			}
			if(rem[i] == 0 && recv[i] == 0)
				break;
		}

		System.out.println("\nTIME\tRECEIVED\t\tSENT\tREMAINING\tDROPPED");
		for(int i = 0; i < 50; i++)
		{
			if(recv[i] == 0 && recv[i] == 0)
				break;
			System.out.println((i+1) + "\t\t" + inp[i] + "\t\t" + recv[i] + "\t\t" + rem[i] + "\t\t" + drop[i]);
		}
		
		System.out.println();
		for(int i = 0; i < tTime; i++)
			if(drop[i] > 0)
				System.out.println(drop[i] + " PACKETS DROPPED AT TIME " + (i+1) + "s");
	}

}
