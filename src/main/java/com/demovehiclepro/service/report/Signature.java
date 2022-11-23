package com.demovehiclepro.service.report;

import org.json.JSONException;
import org.json.JSONObject;

public class Signature extends ReportDecorator{
    Signature(Report report) {
        super(report);
    }

    @Override
    public JSONObject showReport() throws JSONException {
        JSONObject jsonObject = super.generateReport();
        jsonObject.put("signature", "Dealer A");
        return jsonObject;
    }
}
