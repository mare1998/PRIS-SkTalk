package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.repository.KorisnikRepository;

import model.Korisnik;

@Service("UserDetailProvider")
public class UserDetailProvider implements UserDetailsService {
	
	@Autowired
	KorisnikRepository korisnikRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("USERNAMEEAAAA : "+username);
		Korisnik k = korisnikRepo.findByUsername(username);
		UserDetails ud = new MyUserDetails(k);
		return ud;
//		Optional<Korisnik> korisnik = Optional.of(korisnikRepo.findByUsername(korisnickoIme));
//		korisnik.orElseThrow(() -> new UsernameNotFoundException("Nije pronadjeno korisnicko ime: "+ korisnickoIme));
//		return korisnik.map(MyUserDetails::new).get();
	}

}
