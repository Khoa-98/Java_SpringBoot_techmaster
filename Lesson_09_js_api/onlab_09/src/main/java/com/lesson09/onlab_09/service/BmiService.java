package com.lesson09.onlab_09.service;

import java.text.DecimalFormat;

import org.springframework.stereotype.Service;

import com.lesson09.onlab_09.exception.BadRequestException;

@Service
public class BmiService {
    public double calculateBmi(double height, double weight) {
        if (weight <= 0 || height <= 0) {
            throw new BadRequestException("Cân nặng và chiều cao phải lớn hơn 0");
        }

        double bmi = weight / (height * height);
       
        return bmi;
    }
}
