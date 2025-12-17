package test;

import model.Korisnik;
import repo.KorisnikRepo;

public class KorisnikRepoTest {
	
	public static void main(String[] args) {
		
		KorisnikRepo kr = new KorisnikRepo();
		
		kr.findAll().stream().forEach(k -> System.out.println(k));
		
		Korisnik temp = new Korisnik(null, "Test1", "Test1", "username", "password");
		
		temp = kr.create(temp);
		
		kr.findAll().stream().forEach(k -> System.out.println(k));
		
		temp.setIme("Test2");
		temp.setPrezime("Test3");
		
		temp = kr.update(temp);
		
		kr.findAll().stream().forEach(k -> System.out.println(k));
		
		kr.delete(temp.getId());
		
		kr.findAll().stream().forEach(k -> System.out.println(k));
		
	}
	
}
