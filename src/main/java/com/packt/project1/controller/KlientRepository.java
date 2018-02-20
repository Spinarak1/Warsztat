package com.packt.project1.controller;

import org.springframework.data.repository.CrudRepository;

interface KlientRepository extends CrudRepository<Klient, Integer> {
}