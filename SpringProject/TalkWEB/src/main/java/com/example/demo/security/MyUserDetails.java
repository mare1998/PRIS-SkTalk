package com.example.demo.security;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.repository.PolaznikRepository;
import com.example.demo.repository.PredavacRepository;

import model.Korisnik;
import model.Polaznik;
import model.Predavac;

@SuppressWarnings("serial")
public class MyUserDetails implements UserDetails{
	private String username;
	private String password;
	private String ime;
	private String prezime;
	private List<GrantedAuthority> authorities;
	
	@Autowired
	PolaznikRepository polaznikRepo;
	
	@Autowired
	PredavacRepository predavacRepo;

	public MyUserDetails(Korisnik k) {
		System.out.println("MY USER DETAILS: "+k.getUsername());
		this.username = k.getUsername();
		this.password = k.getPassword();
		this.ime = k.getIme();
		this.prezime = k.getPrezime();
		Polaznik polaznik = polaznikRepo.findByKorisnik(k);
		Predavac predavac = predavacRepo.findByKorisnik(k);
		String role = polaznik != null ? "polaznik" : (predavac != null ? "predavac" : "admin");
		
		this.authorities = Arrays.asList(new SimpleGrantedAuthority(role));
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
	public String getIme() {
		return ime;
	}
	
	public String getPrezime() {
		return prezime;
	}

}
