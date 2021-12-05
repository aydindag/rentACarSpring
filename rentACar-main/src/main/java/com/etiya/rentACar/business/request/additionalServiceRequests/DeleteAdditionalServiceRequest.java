package com.etiya.rentACar.business.request.additionalServiceRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class DeleteAdditionalServiceRequest {
    @NotNull
    @Min(1)
    private int serviceId;
}
