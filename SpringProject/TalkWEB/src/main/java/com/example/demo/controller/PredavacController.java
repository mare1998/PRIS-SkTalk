package com.example.demo.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.repository.KursRepository;
import com.example.demo.repository.LekcijaRepository;

import model.Lekcija;

@Controller
@RequestMapping(value="/predavac")
public class PredavacController {
	
	@Autowired
	LekcijaRepository lekcijaRepo;
	
	@Autowired
	KursRepository kursRepo;
	
	@RequestMapping(value="/dodajLekciju", method=RequestMethod.POST, consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<Boolean> dodajLekciju(@RequestParam("idKurs") Integer idKursa, @RequestPart("slika") MultipartFile slika, @RequestParam("tekst") String tekst, @RequestParam("url_videa") String urlVidea) throws IOException{
		Lekcija lekcija = new Lekcija();
		System.out.println("Lekcija" + slika);
		lekcija.setKur(kursRepo.getById(idKursa));
		byte[] mediaBytes = slika.getBytes();
		lekcija.setSlika(mediaBytes);
		lekcija.setTekst(tekst);
		lekcija.setUrlVidea(urlVidea);
		lekcija = lekcijaRepo.save(lekcija);
		if(lekcija != null) {
			return new ResponseEntity<Boolean>(false, HttpStatus.OK);
		}
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}
	
	
}
