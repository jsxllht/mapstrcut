package com.jsxl.mapstruct.demo.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CarDTO {
    private Long id;
    private String vin;
    private double price;
    private double totalPrice;
    private Date publishDate;
    private String color;
    private String brand;
    private List<PartDTO> partDTOS;
    private DriverDTO driverDTO;
}
