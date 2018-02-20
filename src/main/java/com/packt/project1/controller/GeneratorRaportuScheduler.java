package com.packt.project1.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class GeneratorRaportuScheduler {

	@PersistenceContext
    private EntityManager entityManager;
	
    private static final Logger log = LoggerFactory.getLogger(GeneratorRaportuScheduler.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(cron = "0 * * * * *")
    public void reportCurrentTime() {
        log.info("The time is now {}", dateFormat.format(new Date()));
        try {

            GeneratorRaportu generator = new GeneratorRaportu();
            generator.sumaKosztowZaWczoraj(entityManager, "C:\\users\\patkrz2\\Desktop\\XML\\");
    }catch (Throwable ex) {
        System.err.println("Initial SessionFactory creation failed." + ex);
    } finally {
    }
}}