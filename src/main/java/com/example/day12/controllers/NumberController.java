package com.example.day12.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;
import java.util.stream.Collectors;

@Controller
public class NumberController {

    // This method serves the form page
    @GetMapping("/number-generator")
    public String showForm() {
        return "NumberGenerator";
    }

    @GetMapping("/generate")
    public String generateNumbers(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer number,
            @RequestParam(required = false, name = "list") String list,
            Model model) {

        if (list != null && !list.isEmpty()) {
            // Parse the 'list' parameter and convert to a set of unique integers
            // Start by creating an empty Set to store the unique numbers
            Set<Integer> numbers = new HashSet<>();

            // Split the `list` string by commas
            String[] parts = list.split(",");

            // Loop through each part in the array
            for (String part : parts) {
                // Remove any leading or trailing whitespace
                part = part.trim();

                // Convert the cleaned string to an integer
                int tempNumbers = Integer.parseInt(part);

                // Add the number to the Set (duplicates are automatically ignored in a Set)
                numbers.add(tempNumbers);
            }

            model.addAttribute("message", "Here are your list of numbers:");
            model.addAttribute("randomNumbers", numbers);
        } else if (number != null && name != null) {

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
            model.addAttribute("message", String.format("Hello, %s! Here are your %d randomly generated numbers:", name, number));
            model.addAttribute("randomNumbers", randomNumbers);

        } else {
            // Handle missing parameters
            model.addAttribute("message", "Please provide either a name and number or a list of numbers.");
            return "error"; // Redirect to an error page if required fields are missing
        }

        return "luckynumbers";
    }
}
