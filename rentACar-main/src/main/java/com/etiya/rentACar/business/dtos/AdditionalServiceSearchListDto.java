package com.etiya.rentACar.business.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class AdditionalServiceSearchListDto {
    private int serviceId;
    private String serviceName;
    private int serviceDailyPrice;
}
