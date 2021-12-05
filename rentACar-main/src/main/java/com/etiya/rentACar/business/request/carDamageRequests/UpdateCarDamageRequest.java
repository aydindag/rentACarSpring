package com.etiya.rentACar.business.request.carDamageRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCarDamageRequest {
    @NotNull
    @Min(1)
    private int carDamageId;

    @NotNull
    @Min(1)
    private int carId;

    @NotNull
    @Size(min = 4)
    private String damageDescription;

    @NotNull
    @Min(0)
    private double damageCost;
}
