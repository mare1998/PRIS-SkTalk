package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.repository.KursRepository;

@Controller
@RequestMapping(value="/polaznik")
public class PolaznikController {
	
	@Autowired
	KursRepository kursRepo;

}
