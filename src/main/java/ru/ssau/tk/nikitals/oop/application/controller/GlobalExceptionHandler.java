package ru.ssau.tk.nikitals.oop.application.controller;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    public ErrorResponse handleInvalidArguments(IllegalArgumentException ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        return ErrorResponse.create(ex, HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public ErrorResponse handleRuntimeException(RuntimeException ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        return ErrorResponse.create(ex, HttpStatus.BAD_REQUEST, ex.getMessage());
    }


    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ErrorResponse handleGeneralException(Exception ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        return ErrorResponse.create(ex, HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
    }

}

