package com.demovehiclepro.service.report;

import com.demovehiclepro.data.model.CustomerBooking;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class ReportFactory {
    private List<CustomerBooking> customerBookingList;
    public ReportFactory(List<CustomerBooking> customerBookingList) {
        this.customerBookingList = customerBookingList;
    }

    public JSONObject getReport() throws JSONException {
        Report report;
        report = new SalesReport(this.customerBookingList);
        report = new Header(report);
        report = new PieChart(report);
        report = new Signature(report);
        return report.showReport();
    }
}
