package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.repository.KursRepository;
import com.example.demo.repository.PredavacRepository;

import model.Kur;
import model.Predavac;

@Controller
@RequestMapping(value="/polaznik")
public class PolaznikController {
	
	@Autowired
	KursRepository kursRepo;
	
	@Autowired
	PredavacRepository predavacRepo;
	
	@RequestMapping(value="/sviKursevi", method=RequestMethod.GET)
	@ResponseBody
	public List<Kur> sviKursevi(){
		return kursRepo.findAll();
	}
	
	@RequestMapping(value="/prikazKursa", method=RequestMethod.GET)
	@ResponseBody
	public Kur prikazKursa(@RequestParam int id) {
		return kursRepo.getById(id);
	}

	@RequestMapping(value="/nadjiPredavaca", method=RequestMethod.GET)
	@ResponseBody
	public Predavac nadjiPredavaca(@RequestParam int id) {
		return predavacRepo.getById(id);
	}
}
