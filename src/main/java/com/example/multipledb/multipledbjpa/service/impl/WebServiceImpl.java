package com.example.multipledb.multipledbjpa.service.impl;

import com.example.multipledb.multipledbjpa.domain.datasource1.PartnerHolidays1;
import com.example.multipledb.multipledbjpa.domain.datasource2.PartnerHolidays2;
import com.example.multipledb.multipledbjpa.dto.PartnerHolidayDto;
import com.example.multipledb.multipledbjpa.repository.datasource1.PartnerHolidaysRepository;
import com.example.multipledb.multipledbjpa.repository.datasource2.PartnerHolidaysRepository2;
import com.example.multipledb.multipledbjpa.service.WebService;
import config.DatasourceQualify;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class WebServiceImpl implements WebService {
    @Autowired
    PartnerHolidaysRepository repositoryDatasource1;

    @Autowired
    PartnerHolidaysRepository2 repositoryDatasource2;

    @Override
    @Transactional(DatasourceQualify.DATASOURCE1)
    public List<PartnerHolidayDto> getAllPartnerHolidaysDB1() {
        List<PartnerHolidayDto> lstResult = new ArrayList<>();
        try {
            List<PartnerHolidays1> lstQuery = repositoryDatasource1.findAll();
            for (PartnerHolidays1 p : lstQuery) {
                PartnerHolidayDto dto = new PartnerHolidayDto();
                BeanUtils.copyProperties(p, dto);
                lstResult.add(dto);
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        return lstResult;
    }

    @Override
    @Transactional(DatasourceQualify.DATASOURCE2)
    public List<PartnerHolidayDto> getAllPartnerHolidaysDB2() {
        List<PartnerHolidayDto> lstResult = new ArrayList<>();
        try {
            List<PartnerHolidays2> lstQuery = repositoryDatasource2.findAll();
            for (PartnerHolidays2 p : lstQuery) {
                PartnerHolidayDto dto = new PartnerHolidayDto();
                BeanUtils.copyProperties(p, dto);
                lstResult.add(dto);
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        return lstResult;
    }
}
