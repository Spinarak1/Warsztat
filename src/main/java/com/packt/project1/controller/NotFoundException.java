package com.packt.project1.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;


@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

}