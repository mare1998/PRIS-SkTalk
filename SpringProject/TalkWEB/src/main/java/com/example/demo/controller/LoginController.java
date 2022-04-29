package com.example.demo.controller;

import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.repository.KorisnikRepository;
import com.example.demo.repository.PolaznikRepository;

import classes.Response;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import model.Korisnik;
import model.Polaznik;

@Controller
@RequestMapping(value="/login")
public class LoginController {
	
	@Autowired
	KorisnikRepository korisnikRepo;
	
	@Autowired
	PolaznikRepository polaznikRepo;
	
	@PostMapping(value="login")
	public ResponseEntity<Response> login(@RequestBody Korisnik k, Principal principal) {
		Korisnik korisnik = korisnikRepo.findByUsername(k.getUsername());
		Response resp = new Response();
		if ( korisnik == null) {
			return new ResponseEntity<Response>(resp, HttpStatus.OK);
		}
		BCryptPasswordEncoder passEncoder = new BCryptPasswordEncoder();
		if ( !passEncoder.matches(k.getPassword(), korisnik.getPassword())) //ukoliko se sifra ne poklapa sa sifrom koja odgovara tom usernameu
			return new ResponseEntity<Response>(resp, HttpStatus.OK);
		resp.setIdKorisnika(korisnik.getIdKorisnik());
		resp.setUloga(korisnik.getClass().getName());
		resp.setToken(getJWTToken(k.getUsername()));
		return new ResponseEntity<Response>(resp, HttpStatus.OK);
	}
	
	@PostMapping(value="registracija")
	public ResponseEntity<Boolean> registracija(@RequestParam(name="username") String username, @RequestParam(name="password") String password,
			@RequestParam(name="ime") String ime, @RequestParam(name="prezime") String prezime,
			@RequestParam(name="adresa") String adresa, @RequestParam(name="telefon") String telefon, HttpServletRequest request) {
		Korisnik kor = korisnikRepo.findByUsername(username);
		if (kor != null) {
			return new ResponseEntity<Boolean>(false, HttpStatus.OK);
		}

		Korisnik korisnik = new Korisnik();
		BCryptPasswordEncoder passEncoder = new BCryptPasswordEncoder();
		password = passEncoder.encode(password);
		
		korisnik.setUsername(username);
		korisnik.setPassword(password);
		korisnik.setIme(ime);
		korisnik.setPrezime(prezime);
		korisnik.setPolaznik(null);
		korisnik = korisnikRepo.save(korisnik);
		if(korisnik == null) {
			return new ResponseEntity<Boolean>(false, HttpStatus.OK);
		}
		
		korisnik = korisnikRepo.findByUsername(username);
		Polaznik p = new Polaznik();
		p.setKorisnik(korisnik);
		p.setTelefon(telefon);
		p.setAdresa(adresa);
		polaznikRepo.save(p);
		korisnik.setPolaznik(p);
		korisnikRepo.save(korisnik);
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);		
	}
	
	private String getJWTToken(String username) {
		String secretKey = "mySecretKey";
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(korisnikRepo.findByUsername(username).getClass().getName());
		
		String token = Jwts.builder()
				.setId("softtekJWT")
				.setSubject(username)
				.claim("authorities",
						grantedAuthorities.stream()
								.map(GrantedAuthority::getAuthority)
								.collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 600000))
				.signWith(SignatureAlgorithm.HS512,
						secretKey.getBytes()).compact();

		return "Bearer " + token;
	}
}
