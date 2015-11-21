import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;


public class RSA {
	int n;
	int p;
	int q;
	int max;
	int pub;
	int pri;
	int message;
	int cypher;
	int secret;

//	public static void main(String[] args) {
//		RSA rsa = new RSA();
//		rsa.max = 100;
//		rsa.generatePrimes();
//		System.out.println("inputs:");
//		System.out.println("p: " + rsa.p);
//		System.out.println("q: " + rsa.q);
//		System.out.println("n: " + rsa.n);
//		rsa.generatePublicKey();
//		System.out.println("pub: " + rsa.pub);
//		System.out.println("message: " + rsa.message);
//		rsa.generatePrivateKey();
//		System.out.println("pri: " + rsa.pri);
//		rsa.encrypt();
//		System.out.println("cypher: " + rsa.cypher);
//		rsa.decrypt();
//		System.out.println("secret: " + rsa.secret);
//		
//	}
	
	public int[] setIntsModP(int p){
		int[] set = new int[p];
		for (int i=0;i<set.length;i++){
			set[i] = i;
		}
		return set;
	}
	
	public void setIntsModP_EXAMPLE(){
		int[] set = setIntsModP(10);
		for (int i:set){
			System.out.print(i);
		}
	}
	
	public int intRemainingAfterDividing(int y, int z){
		Random r = new Random();
		int k = r.nextInt(10);
		return (k*z)+y;
		//return y%z;
	}
	
	public int greatestCommonDivisor(int x, int p) { 
		if(p == 0){ 
			return x; 
		} 
		return greatestCommonDivisor(p, x%p); 
	}
	
	public int multiplicativeInverse(int x, int p) { 
		if (greatestCommonDivisor(x,p) != 1){
			System.out.print("X and P must have a gcd of 1");
			System.exit(0);
		}
		BigInteger bigX = new BigInteger(Integer.toString(x));
		BigInteger bigP = new BigInteger(Integer.toString(p));
		
		return bigX.modInverse(bigP).intValue();
	}
	
	public void generatePrimes(){
		Random r = new Random();
		int[] era = eratosthenes(this.max);
		this.p = era[r.nextInt(era.length)];
		this.q = era[r.nextInt(era.length)];
		
		//if they are the same number do it again
		if(this.p == this.q){
			generatePrimes();
		}
		this.n = this.p * this.q;
	}
	
	public int[] eratosthenes(int max){
        ArrayList<Integer> primes = new ArrayList<Integer>();
        boolean[] isPrime = new boolean[max + 1];
        for (int i = 2; i <= max; i++){
            isPrime[i] = true;
        }
        for(int i = 2; i*i <= max; i++){
            if (isPrime[i]){
                for(int j = i; i*j <= max; j++){
                    isPrime[i*j] = false;
                }
            }
        }
        for(int k=0;k<isPrime.length;k++){
        	if(isPrime[k]){
        		primes.add(k);
        	}
        }
        //converts arraylist to int[]
        return primes.stream().mapToInt(i -> i).toArray();
    }
	
	public int getTotient(int p, int q){
		System.out.println("totes: " + (p-1)*(q-1));
		return (p-1)*(q-1);
	}
	
	public void generatePublicKey(){
		Random r = new Random();
		int[] era = eratosthenes(getTotient(this.p,this.q));

		this.pub = era[r.nextInt(era.length)];
	}
	
	public void generatePrivateKey(){
		this.pri = multiplicativeInverse(this.pub,getTotient(this.p,this.q));
	}
	
	public void encrypt(){
		BigInteger message = new BigInteger(Integer.toString(this.message));
		BigInteger bigBoy = new BigInteger("1");
		BigInteger cypher = new BigInteger("1");
		BigInteger n = new BigInteger(Integer.toString(this.n));
		bigBoy = message.pow(this.pub);
		cypher = bigBoy.mod(n);
		this.cypher = cypher.intValue();
	}
	public void decrypt(){
		BigInteger cypher = new BigInteger(Integer.toString(this.cypher));
		BigInteger bigBoy = new BigInteger("1");
		BigInteger secret = new BigInteger("1");
		BigInteger n = new BigInteger(Integer.toString(this.n));
		bigBoy = cypher.pow(this.pri);
		secret = bigBoy.mod(n);
		this.secret = secret.intValue();
	}
}
