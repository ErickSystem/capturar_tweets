package br.com.si.mining;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		TwitterApp tw = new TwitterApp();
		
		System.out.println("Criando conexão");
		tw.conectaMongoDB();
		
		System.out.println("Configurando credenciais para da api do twitter");
		tw.configuraCredenciais();
		
		System.out.println("Capturando os twitters com a palavra: UninoveDez");
		tw.capturaTweets();

	}

}
