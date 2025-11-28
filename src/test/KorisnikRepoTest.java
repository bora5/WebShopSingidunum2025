package test;

import repo.KorisnikRepo;

public class KorisnikRepoTest {
	
	public static void main(String[] args) {
		
		KorisnikRepo kr = new KorisnikRepo();
		kr.findAll().stream().forEach(k -> System.out.println(k));
		
	}
	
}
