package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.repository.KursRepository;

import model.Kur;

@Controller
@RequestMapping(value="/polaznik")
public class PolaznikController {
	
	@Autowired
	KursRepository kursRepo;
	
	@RequestMapping(value="/sviKursevi", method=RequestMethod.GET)
	public List<Kur> sviKursevi(){
		return kursRepo.findAll();
	}
	
	@RequestMapping(value="/prikazKursa", method=RequestMethod.GET)
	public Kur prikazKursa(@RequestParam int id) {
		Kur k = kursRepo.getById(id);
		if(k != null) {
			return k;
		}else {
			return null;
		}
	}

}
