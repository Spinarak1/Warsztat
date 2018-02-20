package com.packt.project1.controller;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.apache.log4j.Logger;


@Controller
@RequestMapping("/")
class KlientController {
	
	 final static Logger logger = Logger.getLogger(KlientController.class);
 
   @Autowired
   KlientRepository klientRepository;
   @Autowired
   UslugaRepository uslugaRepository;
   @RequestMapping(value = "/klient", method = RequestMethod.GET)
   @ResponseBody
   public List<Klient> findKlientAll() { 
	   logger.info("szukam wszyskich klientów");
       return (List<Klient>) klientRepository.findAll();
   }
   @RequestMapping(value = "/klient/{id}", method = RequestMethod.GET)
   @ResponseBody
   public Klient findKlientId(@PathVariable("id") Integer id) {
	   logger.info("hej info");
       Optional<Klient> x = klientRepository.findById(id);
       if(x.isPresent() == false)
    	   throw new NotFoundException();
       return x.get();
   }

 
   @RequestMapping(value = "/klient", method = RequestMethod.POST)
   @ResponseStatus(HttpStatus.CREATED)
   @ResponseBody
   public Klient create(@RequestBody Klient klient) {
	   logger.info("hej info");
	   System.out.println(klient.getId());
       return klientRepository.save(klient);
   }
 
   @RequestMapping(value = "/klient/{id}", method = RequestMethod.PUT)
   @ResponseStatus(HttpStatus.OK)
   public void update(@PathVariable( "id" ) Integer id, @RequestBody Klient klient) {
	   logger.info("hej info");
	   if(! klientRepository.existsById(id)) {
		   throw new NotFoundException();
	   }
	   if(klient == null || klient.getId() != id ) {
		   throw new BadRequestException();
	   }
       klientRepository.save(klient);
   }
 
   @RequestMapping(value = "/klient/{id}", method = RequestMethod.DELETE)
   @ResponseStatus(HttpStatus.OK)
   public void deleteKlient(@PathVariable("id") Integer id) {
	   logger.info("hej info");
	   if(! klientRepository.existsById(id)) {
		   throw new NotFoundException();
	   }
       klientRepository.deleteById(id);
   }
 
}

