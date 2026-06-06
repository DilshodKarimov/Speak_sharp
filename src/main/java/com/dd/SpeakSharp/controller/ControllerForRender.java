package com.dd.SpeakSharp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerForRender {

    @GetMapping("/")
    public String health() {
        return "OK";
    }
}
