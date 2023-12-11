package com.bitc.spring_proj.service;

import com.bitc.spring_proj.dto.FestaItemDTO;

import java.util.List;

public interface ProjService {
    List<FestaItemDTO> FestaList(String serviceUrl) throws Exception;
}
