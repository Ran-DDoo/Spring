package com.bitc.spring_proj.dto;

import lombok.Data;

@Data
public class QnaDTO {
    private int qIdx;
    private String qtitle;
    private String qcontents;
    private String qcreateDate;
    private String qcreateId;
    private String qname;
}
