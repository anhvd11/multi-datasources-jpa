package com.example.multipledb.multipledbjpa.controller;

import com.example.multipledb.multipledbjpa.dto.PartnerHolidayDto;
import com.example.multipledb.multipledbjpa.service.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/web")
public class WebController {
    @Autowired
    WebService webService;

    @RequestMapping(value="/datasource1-partner-holidays", method= RequestMethod.GET)
    public List<PartnerHolidayDto> getAllPartnerHolidays(){return webService.getAllPartnerHolidaysDB1();}

    @RequestMapping(value="/datasource2-partner-holidays", method= RequestMethod.GET)
    public List<PartnerHolidayDto> getAllPartnerHolidays2(){return webService.getAllPartnerHolidaysDB2();}
}
