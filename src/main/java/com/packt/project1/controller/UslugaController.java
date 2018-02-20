package com.packt.project1.controller;
import java.util.List;
import org.apache.log4j.Logger;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;


@Controller
@RequestMapping("/")
public class UslugaController {
	
	 final static Logger logger = Logger.getLogger(UslugaController.class);

   @Autowired
   UslugaRepository uslugaRepository;
   @Autowired
   CzynnoscRepository czynnoscRepository;
   @RequestMapping(value = "/usluga", method = RequestMethod.GET)
   @ResponseBody
   public List<Usluga> findUslugaAll() { 
	   logger.info("hej info");
	   logger.debug("hej debug");
       return (List<Usluga>) uslugaRepository.findAll();
   }
   @RequestMapping(value = "/usluga/{id}/czynnosc", method = RequestMethod.GET)
   @ResponseBody
   public List<Czynnosc> znajdzCzynnosciWewnatrzUslugi(@PathVariable("id") Integer id) {
	   logger.info("hej info");
	   logger.debug("hej debug");
	   System.out.println("abc");
	   return czynnoscRepository.findCzynnosciDlaUslugi(id);
   }
   @RequestMapping(value = "/usluga/{id}", method = RequestMethod.GET)
   @ResponseBody
   public Usluga findUslugaId(@PathVariable("id") Integer id) {
	   logger.info("hej info");
	   logger.debug("hej debug");
       Optional<Usluga> x = uslugaRepository.findById(id);
       if(x.isPresent() == false)
    	   throw new NotFoundException();
       return x.get();
   }
   @RequestMapping(value = "/usluga", method = RequestMethod.POST)
   @ResponseStatus(HttpStatus.CREATED)
   @ResponseBody
   public Usluga create(@RequestBody Usluga usluga) {
	   logger.info("hej info");
	   logger.debug("hej debug");
	   System.out.println("???????");
	   System.out.println(usluga.getId());
       return uslugaRepository.save(usluga);
   }
   @RequestMapping(value = "/usluga/{id}/czynnosc", method = RequestMethod.POST)
   @ResponseStatus(HttpStatus.CREATED)
   @ResponseBody
   public Czynnosc dodajCzynnoscDoUslugi(@PathVariable("id") Integer id, @RequestBody Czynnosc czynnosc) {
	   logger.info("hej info");
	   logger.debug("hej debug");
	   System.out.println(czynnosc.getId());
	   Optional<Usluga> x = uslugaRepository.findById(id);
	   if(x.isPresent() == false)
		   throw new NotFoundException();
	   else {
		   Usluga usluga = x.get();
		   czynnosc.setUsluga(usluga);
		   return czynnoscRepository.save(czynnosc);
	   }
   }
 
   @RequestMapping(value = "/usluga/{id}", method = RequestMethod.PUT)
   @ResponseStatus(HttpStatus.OK)
   public void update(@PathVariable( "id" ) Integer id, @RequestBody Usluga usluga) {
	   logger.info("hej info");
	   logger.debug("hej debug");
	   if(! uslugaRepository.existsById(id)) {
		   throw new NotFoundException();
	   }
	   if(usluga == null || usluga.getId() != id ) {
		   throw new BadRequestException();
	   }
       uslugaRepository.save(usluga);
   }
 
   @RequestMapping(value = "/usluga/{id}", method = RequestMethod.DELETE)
   @ResponseStatus(HttpStatus.OK)
   public void deleteUsluga(@PathVariable("id") Integer id) {
	   logger.info("hej info");
	   logger.debug("hej debug");
	   if(! uslugaRepository.existsById(id)) {
		   throw new NotFoundException();
	   }
       uslugaRepository.deleteById(id);
   }
 
 
}