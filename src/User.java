public class User {
	public int pubKey;
	public int n;
	public int priKey;
	public int message;
	public int cypher;
	
	public void genKeys(){
		RSA rsa = new RSA();
		rsa.max = 100;
		rsa.generatePrimes();
		System.out.println("inputs:");
		System.out.println("p: " + rsa.p);
		System.out.println("q: " + rsa.q);
		System.out.println("n: " + rsa.n);
		rsa.generatePublicKey();
		System.out.println("pub: " + rsa.pub);
		System.out.println("message: " + rsa.message);
		rsa.generatePrivateKey();
		System.out.println("pri: " + rsa.pri);
		this.n = rsa.n;
		this.pubKey = rsa.pub;
		this.priKey = rsa.pri;
	}
	
	public int sendMessage(int message){
		RSA rsa = new RSA();
		this.message = message;
		rsa.pub = this.pubKey;
		rsa.n = this.n;
		rsa.message = this.message;
		rsa.encrypt();
		this.cypher = rsa.cypher;
		return this.cypher;
	}
	
	public void decode(){
		RSA rsa = new RSA();
		rsa.cypher = this.cypher;
		rsa.n = this.n;
		rsa.pri = this.priKey;
		rsa.decrypt();
		System.out.println(rsa.secret);
	}
}
