package com.example.day12.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
public class NumberController {

    // This method serves the form page
    @GetMapping("/number-generator")
    public String showForm(){
        return "NumberGenerator";
    }

    @GetMapping("/generate")
    public String generateNumbers(@RequestParam("number") int number, Model model) {

        // Generate a list of non-duplicate random numbers between 1 and 30.
        Random random = new Random();
        Set<Integer> numberSet = new HashSet<>();

        // Keep generating until we have 'number' unique random numbers
        while (numberSet.size() < number) {
            int randomNumber = random.nextInt(30) + 1; // Generates a number between 1 and 30
            numberSet.add(randomNumber); // Adds only if it's unique
        }

        List<Integer> randomNumbers = new ArrayList<>(numberSet);
        Collections.shuffle(randomNumbers);

        // Pass the random numbers to the view
        model.addAttribute("randomNumbers", randomNumbers);
        return "result";
    }
}
