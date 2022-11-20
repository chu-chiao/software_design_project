package com.demovehiclepro.service.report;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class PieChart extends ReportDecorator{
    PieChart(Report report) {
        super(report);
    }

    @Override
    public JSONObject showReport() {
        JSONObject jsonObject = super.generateReport();
        jsonObject.put("pieChart", genPieDataBySe(jsonObject.getJSONArray("raw")));
        return jsonObject;
    }

    private String genPieDataBySe(JSONArray rawData) {
        Map<Integer, Integer> seMap = new HashMap<>();
        String seKey = "SE";
        for (int i = 0, size = rawData.length(); i < size; i++) {
            JSONObject object = rawData.getJSONObject(i);
            int key = object.optInt(seKey);
            if (seMap.containsKey(key)) {
                int v = seMap.get(key);
                seMap.put(key, v + 1);
            } else {
                seMap.put(key, 1);
            }
        }
        JSONObject pieData = new JSONObject();
        for(Integer key : seMap.keySet()){
            pieData.put(String.valueOf(key), ((float) seMap.get(key) / (float) rawData.length()) * 100);
        }
        return pieData.toString();
    }
}
