package com.rober.hotel.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/hello")

public class Hello {
    
    @GetMapping
    public String helloWord(){
        return "hello motherfuckers";
    }
}
