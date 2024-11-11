package com.example.day12.errors;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.Map;

@Controller
public class CustomErrorController implements ErrorController {

    private final CustomErrorsAttribute errorAttributes;

    public CustomErrorController(CustomErrorsAttribute errorAttributes) {
        this.errorAttributes = errorAttributes;
    }

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        // Create an instance of WebRequest from HttpServletRequest
        ServletWebRequest webRequest = new ServletWebRequest(request);

        // Get error attributes with all details included
        Map<String, Object> errorDetails = errorAttributes.getErrorAttributes(
                webRequest, ErrorAttributeOptions.of(
                        ErrorAttributeOptions.Include.MESSAGE,
                        ErrorAttributeOptions.Include.EXCEPTION,
                        ErrorAttributeOptions.Include.STACK_TRACE,
                        ErrorAttributeOptions.Include.BINDING_ERRORS
                )
        );

        // Manually add each attribute to the model
        model.addAttribute("error", errorDetails.get("error"));
        model.addAttribute("message", errorDetails.get("message"));
        model.addAttribute("path", errorDetails.get("path"));
        model.addAttribute("exception", errorDetails.get("exception"));
        model.addAttribute("trace", errorDetails.get("trace"));
        model.addAttribute("status", errorDetails.get("status"));

        return "error"; // Should map to error.html in templates
    }
}

