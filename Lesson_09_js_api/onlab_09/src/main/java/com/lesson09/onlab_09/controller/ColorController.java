package com.lesson09.onlab_09.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lesson09.onlab_09.service.ColorService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class ColorController {

    // @Autowired
    // private ColorService colorService;

    private final ColorService colorService;

    @GetMapping("/random-color")
    public String randomColor(@RequestParam(name = "type") int type) {

        return colorService.randomColor(type);
    }
}
