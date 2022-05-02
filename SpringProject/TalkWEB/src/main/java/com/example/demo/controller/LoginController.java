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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.classes.Response;
import com.example.demo.repository.KorisnikRepository;
import com.example.demo.repository.PolaznikRepository;
import com.example.demo.repository.PredavacRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import model.Korisnik;
import model.Polaznik;
import model.Predavac;

@Controller
@RequestMapping(value="/security")
public class LoginController {
	
	@Autowired
	KorisnikRepository korisnikRepo;
	
	@Autowired
	PolaznikRepository polaznikRepo;
	
	@Autowired
	PredavacRepository predavacRepo;
	
	@PostMapping(value="/login")
	public ResponseEntity<Response> login(@RequestParam("username") String username,@RequestParam("password") String password , Principal principal) {
		System.out.println("USERNAMEEEEE: "+username);
		Korisnik korisnik = korisnikRepo.findByUsername(username);
		Response resp = new Response();
		if ( korisnik == null) {
			return new ResponseEntity<Response>(resp, HttpStatus.OK);
		}
		BCryptPasswordEncoder passEncoder = new BCryptPasswordEncoder();
		if ( !passEncoder.matches(password, korisnik.getPassword()))
			return new ResponseEntity<Response>(resp, HttpStatus.OK);
		Polaznik polaznik = polaznikRepo.findByKorisnik(korisnik);
		Predavac predavac = predavacRepo.findByKorisnik(korisnik);
		String role = polaznik != null ? "polaznik" : (predavac != null ? "predavac" : "admin");
		resp.setIdKorisnika(korisnik.getIdKorisnik());
		resp.setUloga(role);
		System.out.println(role);
		resp.setToken(getJWTToken(username));
		return new ResponseEntity<Response>(resp, HttpStatus.OK);
	}
	
	@PostMapping(value="/registracija")
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
		Korisnik korisnik = korisnikRepo.findByUsername(username);
		Polaznik polaznik = polaznikRepo.findByKorisnik(korisnik);
		Predavac predavac = predavacRepo.findByKorisnik(korisnik);
		String role = polaznik != null ? "polaznik" : (predavac != null ? "predavac" : "admin");
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(role);
		
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
