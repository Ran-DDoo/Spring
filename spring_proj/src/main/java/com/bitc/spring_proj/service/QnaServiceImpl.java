package com.bitc.spring_proj.service;

import com.bitc.spring_proj.dto.QnaDTO;
import com.bitc.spring_proj.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QnaServiceImpl implements QnaService{

    @Autowired
    private BoardMapper boardMapper;

    @Override
    public List<QnaDTO> userQna(String qcreateid) throws Exception {
        return boardMapper.userQna(qcreateid);
    }

}
