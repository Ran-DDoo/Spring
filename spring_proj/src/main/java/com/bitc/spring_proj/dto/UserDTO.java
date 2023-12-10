package com.bitc.spring_proj.dto;

import lombok.Data;

@Data
public class UserDTO {
    private int uIdx;
    private String uId;
    private String uPw;
    private String uName;
    private String uYn;
    private int uGrade;
}
