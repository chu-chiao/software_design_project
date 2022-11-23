package com.demovehiclepro.service.report;

import org.json.JSONException;
import org.json.JSONObject;

public class Header extends ReportDecorator{
    Header(Report report) {
        super(report);
    }

    @Override
    public JSONObject showReport() throws JSONException {
        JSONObject jsonObject = super.generateReport();
        jsonObject.put("title", "REPORT");
        return jsonObject;
    }

}
