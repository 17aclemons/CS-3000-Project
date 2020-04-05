package josephus;

import java.math.BigInteger;

public class SimpleJosephus {
	public static int prediction(int n) {//return predicted 'win' spot skipping by 2.	Nikki
		boolean[] b = numToBinary(n);
		binaryToNum(b);
		swap(b);
		return binaryToNum(b);
	}
	
	
	public static boolean[] numToBinary(int n) {	//Nikki
		BigInteger bi = BigInteger.valueOf(n);
		String s = bi.toString(2);
		System.out.println(s);
		boolean b[] = new boolean[s.length()];
		
		for(int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '1') {
				b[i] = true;
			}else {
				b[i] = false;
			}
			System.out.print(b[i]+" ");
		}
		System.out.println();
		return b;
	}
	
	public static void swap(boolean[] b) {		//Nikki
		boolean temp = b[0];
		for(int i = 0; i < b.length-1; i++) {
			b[i] = b[i+1];
					}
		b[b.length-1] = temp;
		
		for(int i = 0; i < b.length; i++) {
			if(b[i]) {
				System.out.print("1 ");
			}else { System.out.print("0 ");}
		}
		
		System.out.println();
		System.out.println("Swapped... ");
	}
	
	public static int binaryToNum(boolean[] b) {	//Nikki
		int result = 0;
		int p = 1;
		for(int i = b.length-1; i >= 0; i--) {
			//System.out.println(p);	//testing
			if(b[i]) {
				result += p;
			}
			p *=2;
		}
		System.out.println(result);
		return result;
	}
	
	public static void print(int x) {
		System.out.println(x);
	}
	public static void main(String[] args) {

		prediction(41);
		
		//int k = 3;
		//int n = 4;
		
		//print(jo(k, n));

//		for(int n = 2; n< 100; n++) {
//			
//			
//			if (k<=n) {
//				System.out.println();
//			System.out.println(n);
//			if(jo(n, k)==josephus2(n, k)) {
//				System.out.println("True");
//			
//			}else {
//				System.out.println("F a l s e");
//			}
//			System.out.println(jo(n, k));
//			System.out.println(josephus2(n, k));
//
//			}
//			
//		}
//		
		
	}
}
