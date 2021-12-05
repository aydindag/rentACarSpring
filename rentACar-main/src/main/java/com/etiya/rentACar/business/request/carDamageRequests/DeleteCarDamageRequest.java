package com.etiya.rentACar.business.request.carDamageRequests;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteCarDamageRequest {
    @NotNull
    @Min(1)
    private int carDamageId;

    @JsonIgnore
    private int carId;
}
