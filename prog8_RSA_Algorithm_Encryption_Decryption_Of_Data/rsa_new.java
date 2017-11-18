import java.math.BigInteger;
import java.util.*;
import java.math.*;

public class rsa_new 
	{	
		public static void main(String args[])
		{
			Scanner sc = new Scanner(System.in);
			int a,b,x,y;
			System.out.println("a: ");
			a = sc.nextInt();
			System.out.println("b: ");
			b = sc.nextInt();
			int n = a*b;
			String str;
			System.out.println("\nENTER THE STRING TO ENCODE");
			str = sc.next();
			int array[] = new int[str.length()];
			boolean result = false;

			for(int i=0;i<str.length();i++)
			{
				array[i] = str.charAt(i);
			}
			
			while(true)
			{
				for(x = 2; x<(a-1)*(b-1);x++) {
					if(gcd(x,(a-1)*(b-1))==1){
						result=true;
						break;
					}
				}

					if(result==true)
						break;	
			}
		
			result = false;
			while(true){
				for(y=1;;y++)
				{
					if((x*y)%((a-1)*(b-1))==1){
						result=true;
						break;
					}
				}
				
				if(result==true)
					break;
			}

			System.out.println("\nPUBLIC KEY = ("+x+","+n+")");	
			System.out.println("\nPRIVATE KEY = ("+y+","+n+")");
			
			BigInteger c,m;
			String ans="";
			int array_enc[] = new int[str.length()];
			int array_dec[] = new int[str.length()];
			for(int i=0;i<str.length();i++){
				c=(new BigInteger(String.valueOf(array[i])).pow(x)).mod(new BigInteger(String.valueOf(n))) ;
				m = (c.pow(y)).mod(new BigInteger(String.valueOf(n)));
				array_enc[i] = c.intValue();
				array_dec[i] = m.intValue();
				ans = ans + (char)(m.intValue());
			}

			System.out.print("\nENCRYPTED VALUES :\n{");
			for(int i=0;i<str.length();i++)
				System.out.print(array_enc[i]+" ");
			System.out.print("}");
			
			System.out.print("\nDECRYPTED VALUES :\n{");
			for(int i=0;i<str.length();i++)
				System.out.print(array_dec[i]+" ");
			System.out.print("}");
			System.out.println("\n\nDECRYPTED DATA: "+ans);
		}

		static int gcd(int a , int b) {
			if(b==0)
				return a;
			else
				return gcd(b,a%b);
		}
}

	

