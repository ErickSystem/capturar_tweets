package br.com.si.variable;

public interface CredencialTwitter {
	public final String API_KEY = System.getenv("API_KEY");
	public final String API_SECRET = System.getenv("API_SECRET");
	public final String API_TOKEN = System.getenv("API_TOKEN");
	public final String API_TOKEN_SECRET = System.getenv("API_TOKEN_SECRET");

}
