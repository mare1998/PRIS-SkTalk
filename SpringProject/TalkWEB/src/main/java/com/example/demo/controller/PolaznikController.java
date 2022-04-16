package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

}
