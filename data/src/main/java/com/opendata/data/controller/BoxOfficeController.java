package com.opendata.data.controller;

import com.opendata.data.service.BoxOfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/api/boxoffice/*")
public class BoxOfficeController {
    @Autowired
    private BoxOfficeService boxOfficeService;

    @RequestMapping("/search")
    public HashMap<String, String> SearchBoxOffice(@RequestParam String targetDt){
        HashMap<String,String> result = boxOfficeService.RunSearch(targetDt);
    }
}
