package com.packt.project1.controller;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

interface CzynnoscRepository extends CrudRepository<Czynnosc, Integer> {
	 @Query("SELECT c FROM Czynnosc c WHERE c.usluga.id = :id_uslugi")
	    public List<Czynnosc> findCzynnosciDlaUslugi(@Param("id_uslugi") Integer id_u);
}
