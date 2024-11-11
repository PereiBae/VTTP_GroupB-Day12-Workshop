package com.example.day12.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

@Controller
@RequestMapping(path ={"/", "/index.html"})
public class IndexResource {

    @GetMapping(produces = {"text/html"})
    public String index(Model model) {
        model.addAttribute("currTime", (new Date()).toString());
        return "index";
    }

}
