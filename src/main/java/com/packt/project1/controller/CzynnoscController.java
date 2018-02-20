package com.packt.project1.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.apache.log4j.Logger;


@Controller
@RequestMapping("/")
public class CzynnoscController {
	
	 final static Logger logger = Logger.getLogger(CzynnoscController.class);

	   @Autowired
	   CzynnoscRepository czynnoscRepository;
	   
	   @RequestMapping(value = "/czynnosc", method = RequestMethod.GET)
	   @ResponseBody
	   public List<Czynnosc> findCzynnoscAll() { 
		   logger.info("hej info");
		   logger.debug("hej debug");
	       return (List<Czynnosc>) czynnoscRepository.findAll();
	   }
	   @RequestMapping(value = "/czynnosc/{id}", method = RequestMethod.GET)
	   @ResponseBody
	   public Czynnosc findCzynnoscId(@PathVariable("id") Integer id) {
		   logger.info("hej info");
		   logger.debug("hej debug");
	       Optional<Czynnosc> x = czynnoscRepository.findById(id);
	       if(x.isPresent() == false)
	    	   throw new NotFoundException();
	       return x.get();
	   }
	 
	   @RequestMapping(value = "/czynnosc/{id}", method = RequestMethod.PUT)
	   @ResponseStatus(HttpStatus.OK)
	   public void update(@PathVariable( "id" ) Integer id, @RequestBody Czynnosc czynnosc) {
		   logger.info("hej info");
		   logger.debug("hej debug");
		   if(! czynnoscRepository.existsById(id)) {
			   throw new NotFoundException();
		   }
		   if(czynnosc == null || czynnosc.getId() != id ) {
			   throw new BadRequestException();
		   }
	       czynnoscRepository.save(czynnosc);
	   }
	 
	   @RequestMapping(value = "/czynnosc/{id}", method = RequestMethod.DELETE)
	   @ResponseStatus(HttpStatus.OK)
	   public void deleteCzynnosc(@PathVariable("id") Integer id) {
		   logger.info("hej info");
		   logger.debug("hej debug");
		   if(! czynnoscRepository.existsById(id)) {
			   throw new NotFoundException();
		   }
	       czynnoscRepository.deleteById(id);
	   }
	
}
