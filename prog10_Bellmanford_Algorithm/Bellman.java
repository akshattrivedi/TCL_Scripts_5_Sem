import java.util.*;

public class Bellman
{
	public static void fun(int cost[][],int n,int s)
	{
		int dist[]=new int[n+1];
		int flag[]=new int[n+1];
		int nh[]=new int[n+1];
		int flag1[]=new int[n+1];
		for(int i=1;i<=n;i++)
		{
			dist[i]=cost[s][i];	
			flag[i]=0;
			nh[i]=s;
			flag1[i]=0;
		}
		flag[s]=1;
		for(int i=1;i<n;i++)
		{
			int min=999,node=0;
			for(int j=1;j<=n;j++)
			{
				if(dist[j]<min && flag[j]==0)
				{
					min=dist[j];
					node=j;	
				}
			}
		flag[node]=1;
			for(int w=1;w<=n;w++)
			{
				if(dist[w]>dist[node]+cost[node][w] && flag[w]==0 )
					{
						dist[w]=dist[node]+cost[node][w];
						nh[w]=node;
							
					}
			}
		}
		System.out.println("SOURCE\tDEST\tCOST\tNEXT HOP NUMB");
		for(int i=1;i<=n;i++)
		{
			if(i!=s)
			{
		
				{
				System.out.print(s+"\t"+i+"\t"+dist[i]+"\t");
				if(nh[i]==s)
				{
				System.out.println(i);	
				}
				else if(nh[nh[i]]==s)
				{
					System.out.println(nh[i]);
				}
				else
				{
				System.out.println(nh[nh[i]]);
				}
			}
		}
	}
}
	public static void main(String args[])
	{
		System.out.println("ENTER THE NUMBER OF NODES: ");
		int n;
		Scanner in=new Scanner(System.in);
		n=in.nextInt();
		int a[][]=new int[n+1][n+1];
		System.out.println("ENTER THE ADJACENCY MATRIX(GRAPH):");
		for(int i=1;i<=n;i++)
		{
			for(int j=1;j<=n;j++)	
			{
				a[i][j]=in.nextInt();
				
			}
		}
		System.out.println("ENTER THE SOUCE NODE");
		int s=in.nextInt();
		fun(a,n,s);
		in.close();
	}
}	
