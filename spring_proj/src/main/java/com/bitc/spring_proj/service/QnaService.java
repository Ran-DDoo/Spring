package com.bitc.spring_proj.service;

import com.bitc.spring_proj.dto.QnaDTO;

import java.util.List;

public interface QnaService {
    List<QnaDTO> userQna(String qcreateid) throws Exception;
}
