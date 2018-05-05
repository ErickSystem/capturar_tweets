package br.com.si.mining;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		String palavra;
		Scanner entrada = new Scanner(System.in);
		TwitterApp tw = new TwitterApp();
		
		System.out.println("Criando conexão");
		tw.conectaMongoDB();
		
		System.out.println("Configurando credenciais para da api do twitter");
		tw.configuraCredenciais();
		
		System.out.println("Palavra que deseja buscar: ");
		palavra = entrada.next();

		System.out.println("Buscando Twitter's com a palavra: " + palavra);		
		tw.capturaTweets(palavra);

	}

}
