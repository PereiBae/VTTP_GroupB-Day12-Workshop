package com.example.day12.errors;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

@Component
public class CustomErrorsAttribute extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
        // Configure options to include message and exception details
        options = options.including(
                ErrorAttributeOptions.Include.MESSAGE,      // Include detailed message
                ErrorAttributeOptions.Include.EXCEPTION,    // Include exception type
                ErrorAttributeOptions.Include.STACK_TRACE,  // Include stack trace
                ErrorAttributeOptions.Include.BINDING_ERRORS // Include validation or binding errors
        );

        return super.getErrorAttributes(webRequest, options);
    }
}
