package com.demovehiclepro.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@Setter
@Getter
public class GenReportDTO {
    private String reportStartDate;
    private String reportEndDate;
}
