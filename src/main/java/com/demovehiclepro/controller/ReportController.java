package com.demovehiclepro.controller;

import com.demovehiclepro.repository.CustomerBookingRepository;
import com.demovehiclepro.data.dtos.GenReportDTO;
import com.demovehiclepro.service.report.ReportService;
import com.demovehiclepro.service.report.ReportServiceImpl;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("report")
public class ReportController {
    @Autowired
    CustomerBookingRepository customerBookingRepository;

    @PostMapping(value = "/v1/gen-report", produces = "application/json")
    public ResponseEntity<?> genReport(@RequestBody GenReportDTO genReportDTO) throws JSONException {
        ReportService reportService = new ReportServiceImpl(customerBookingRepository);
        return ResponseEntity.ok(reportService.genReport(genReportDTO));
    }
}
