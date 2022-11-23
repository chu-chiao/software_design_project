package com.demovehiclepro.service.report;

import com.demovehiclepro.data.model.CustomerBooking;
import com.demovehiclepro.data.repository.CustomerBookingRepository;
import com.demovehiclepro.dtos.GenReportDTO;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class ReportServiceImpl implements ReportService{
    final CustomerBookingRepository customerBookingRepository;

    public ReportServiceImpl(CustomerBookingRepository customerBookingRepository) {
        this.customerBookingRepository = customerBookingRepository;
    }
    @Override
    public JSONObject genReport(GenReportDTO genReportDTO) throws JSONException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        List<CustomerBooking> customerBookingList = null;
        try {
            customerBookingList = customerBookingRepository.findAllByDateBetween(
                    formatter.parse(genReportDTO.getReportStartDate()), formatter.parse(genReportDTO.getReportEndDate()));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        ReportFactory reportFactory = new ReportFactory(customerBookingList);
        return reportFactory.getReport();
    }
}
