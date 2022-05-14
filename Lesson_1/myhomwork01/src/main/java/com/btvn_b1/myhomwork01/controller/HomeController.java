package com.btvn_b1.myhomwork01.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.websocket.server.PathParam;

import com.btvn_b1.myhomwork01.model.student;

import org.apache.tomcat.util.http.parser.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HomeController {

    @GetMapping("/random")
    public String getRandomStr() {
        String s = "QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm0123456789";
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= 8; i++) {
            Random rd = new Random();
            int number = rd.nextInt(s.length());
            sb.append(s.charAt(number));
        }
        return sb.toString();
    }
    

    @GetMapping("/quote")
    public String getRandomQuote() {
        List<String> list = new ArrayList<>() ;
        list.add("Kiến tha lâu đầy tổ");
        list.add("Có công mài sắt, có ngày nên kim");
        list.add("Không thầy đố mày làm nên");
        list.add("Học thầy không tày học bạn");
        Random rd = new Random();
        int index = rd.nextInt(4);
        return list.get(index);
    }

    @PostMapping("/bmi")
    public double caculatorbmi(@RequestParam double w, @RequestParam double h) {
        return (h / (w * w));
    }
    
    

}
