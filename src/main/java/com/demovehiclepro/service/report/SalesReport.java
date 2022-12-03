package com.demovehiclepro.service.report;

import com.demovehiclepro.data.model.CustomerBooking;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class SalesReport implements Report {
    private List<CustomerBooking> customerBookingList;
    public SalesReport(List<CustomerBooking> customerBookingList) {
        super();
        this.customerBookingList = customerBookingList;
    }

    @Override
    public JSONObject showReport() throws JSONException {
        JSONArray allDataArray = new JSONArray();
        JSONObject result = new JSONObject();
        if (!this.customerBookingList.isEmpty()) {
            for (CustomerBooking customerBooking : this.customerBookingList) {
                JSONObject eachData = new JSONObject();
                try {
                    eachData.put("vehicle", customerBooking.getVehicleId());
                    eachData.put("SE", customerBooking.getSalesExecutiveId());
                    eachData.put("status", customerBooking.getBookingStatus());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                allDataArray.put(eachData);
            }
        }
        result.put("raw", allDataArray);
        return result;
    }
}
