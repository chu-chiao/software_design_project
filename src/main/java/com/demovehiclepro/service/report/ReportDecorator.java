package com.demovehiclepro.service.report;

import lombok.Getter;
import lombok.Setter;
import org.json.JSONException;
import org.json.JSONObject;

@Getter
@Setter
abstract public class ReportDecorator implements Report {
    private Report report;
    ReportDecorator(Report report) {
        this.report = report;
    }
    public JSONObject generateReport() throws JSONException {
        if (this.report != null) {
            return this.report.showReport();
        }
        return null;
    }
}
