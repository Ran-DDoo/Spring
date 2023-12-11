package com.bitc.spring_proj.mapper;

import com.bitc.spring_proj.dto.QnaDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    List<QnaDTO> userQna(String qcreateid) throws Exception;
}

