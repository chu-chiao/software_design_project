package com.demovehiclepro.service.report;

import org.json.JSONObject;

public class Signature extends ReportDecorator{
    Signature(Report report) {
        super(report);
    }

    @Override
    public JSONObject showReport() {
        JSONObject jsonObject = super.generateReport();
        jsonObject.put("signature", "Dealer A");
        return jsonObject;
    }
}
