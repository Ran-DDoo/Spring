package com.bitc.spring_proj.dto;

import lombok.Data;

import java.util.List;

@Data
public class FoodItemsDTO {
    private List<FoodItemDTO> itemList;
}
