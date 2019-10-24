package com.example.multipledb.multipledbjpa.service;

import com.example.multipledb.multipledbjpa.dto.PartnerHolidayDto;

import java.util.List;

public interface WebService {
    List<PartnerHolidayDto> getAllPartnerHolidaysDB1();
    List<PartnerHolidayDto> getAllPartnerHolidaysDB2();
}
