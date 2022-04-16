package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Polaznik;

public interface PolaznikRepository extends JpaRepository<Polaznik, Integer> {

}
