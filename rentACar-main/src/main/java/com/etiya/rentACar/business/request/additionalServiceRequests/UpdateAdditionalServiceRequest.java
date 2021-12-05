package com.etiya.rentACar.business.request.additionalServiceRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UpdateAdditionalServiceRequest {
    @NotNull
    @Min(1)
    private int serviceId;

    @NotNull
    @Size(min = 2)
    private String serviceName;

    @NotNull
    @Min(0)
    private int serviceDailyPrice;
}
