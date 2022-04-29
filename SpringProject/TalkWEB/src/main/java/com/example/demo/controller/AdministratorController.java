package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.repository.KategorijaRepository;
import com.example.demo.repository.KorisnikRepository;
import com.example.demo.repository.KursRepository;
import com.example.demo.repository.PredavacRepository;

import model.Kategorija;
import model.Korisnik;
import model.Predavac;
import model.Kur;

@Controller
@RequestMapping(value="/administrator")
public class AdministratorController {
	
	@Autowired
	PredavacRepository predavacRepo;
	
	@Autowired
	KategorijaRepository kategorijaRepo;
	
	@Autowired
	KorisnikRepository korisnikRepo;
	
	@Autowired
	KursRepository kursRepo;
	
	@RequestMapping(value = "/dodajKategoriju", method = RequestMethod.POST)
	public ResponseEntity<Boolean> dodajKategoriju(@RequestParam("naziv") String naziv, @RequestParam("slika") String slika) {
		Kategorija kategorija = kategorijaRepo.findKategorija(naziv);
		if(kategorija == null) {
			Kategorija k = new Kategorija();
			k.setNaziv(naziv);
			k.setSlika(slika);
			k = kategorijaRepo.save(k);
			if(k != null) {
				return new ResponseEntity<Boolean>(true, HttpStatus.OK);
			}
		}
		return new ResponseEntity<Boolean>(false, HttpStatus.OK);
	}
	
	@RequestMapping(value="/dodajPredavaca", method= RequestMethod.POST)
	public ResponseEntity<Boolean> dodajPredavaca(@RequestParam("ime") String ime, @RequestParam("prezime") String prezime, @RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("staz") int staz, @RequestParam("plata") int plata) {
		Korisnik korisnik1 = korisnikRepo.findByUsername(username);
		if(korisnik1 == null) {

			Korisnik korisnik2 = new Korisnik();
			korisnik2.setIme(ime);
			korisnik2.setPrezime(prezime);
			korisnik2.setUsername(username);
			korisnik2.setPassword(password);
			korisnik2.setPredavac(null);
			korisnik2 = korisnikRepo.save(korisnik2);
			if(korisnik2 == null) {
				return new ResponseEntity<Boolean>(false, HttpStatus.OK);
			}	
			korisnik2 = korisnikRepo.findByUsername(username);
			Predavac predavac = new Predavac();
			predavac.setKorisnik(korisnik2);
			predavac.setPlata(plata);
			predavac.setStaz(staz);
			predavac = predavacRepo.save(predavac);
			predavac = predavacRepo.findByKorisnik(korisnik2);
			korisnik2.setPredavac(predavac);
			korisnikRepo.save(korisnik2);	
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		}else {
			return new ResponseEntity<Boolean>(false, HttpStatus.OK);
		}
	}
	

	@RequestMapping(value = "/dodajKurs", method = RequestMethod.POST)
	public ResponseEntity<Boolean> dodajKurs(@RequestParam("naziv") String naziv, @RequestParam("opis") String opis, @RequestParam("ocekivaniIshod") String ocekivaniIshod, @RequestParam("idKategorije") int idKategorije, @RequestParam("idPredavaca") int idPredavaca, @RequestParam("cena") int cena) {
		Kur k = new Kur();
		k.setNaziv(naziv);
		k.setOpis(opis);
		k.setCena(cena);
		k.setOcekivaniIshod(ocekivaniIshod);
		k.setKategorija(kategorijaRepo.getById(idKategorije));
		k.setPredavac(predavacRepo.getById(idPredavaca));
		k = kursRepo.save(k);
		if(k != null) {
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		}
		return new ResponseEntity<Boolean>(false, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/sviPredavaci", method = RequestMethod.GET)
	public ResponseEntity<List<Korisnik>> sviPredavaci() {
		List<Korisnik> predavaci = korisnikRepo.findPredavace();
		return new ResponseEntity<List<Korisnik>>(predavaci, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/sveKategorije", method = RequestMethod.GET)
	public ResponseEntity<List<Kategorija>> sveKategorije() {
		return new ResponseEntity<List<Kategorija>>(kategorijaRepo.findAll(), HttpStatus.OK);
	}
}
