package com.lesson09.onlab_09.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lesson09.onlab_09.request.BmiRequest;
import com.lesson09.onlab_09.service.BmiService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class BmiController {
    private final BmiService bmiService;

    @GetMapping("/bmi-get")
    public double calculateBmi(@RequestParam double height, @RequestParam double weight) {
        return bmiService.calculateBmi(height, weight);
    }

    @PostMapping("/bmi-post")
    public double calculataBmiPost(@RequestBody BmiRequest bmiRequest) {
        return bmiService.calculateBmi(bmiRequest.getHeight(), bmiRequest.getWeight());
    }
}
