package com.lesson09.onlab_09.service;

import java.util.Random;

import org.springframework.stereotype.Service;

import com.lesson09.onlab_09.exception.BadRequestException;

@Service
public class ColorService {
    public String randomColor(int type) {
        return switch (type) {
            case 1 -> randomColorName();
            case 2 -> randomHexColor();
            case 3 -> randomRgbColor();
            default -> throw new BadRequestException("type =" + type + "không hợp lệ");
        };

    }

    public String randomColorName() {
        String colorNames[] = { "red", "black", "brown", "blue", "green", "grey", "orange" };
        Random rd = new Random();
        String colorName = colorNames[rd.nextInt(colorNames.length)];
        return colorName;
    }

    public String randomHexColor() {
        String[] letters = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F" };
        String color = "#";
        for (int i = 0; i < 6; i++) {
            color += letters[(int) Math.round(Math.random() * 15)];
        }
        return color;
    }

    public String randomRgbColor() {
        Random numGen = new Random();
        return new String(numGen.nextInt(256) + ", " + numGen.nextInt(256) + ", " + numGen.nextInt(256));
    }
}
