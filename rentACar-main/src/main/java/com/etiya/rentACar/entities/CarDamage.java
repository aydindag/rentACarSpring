package com.etiya.rentACar.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="car_damages")
public class CarDamage {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)

    @Column(name="car_damage_id")
    private int damageId;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    @Column(name = "damage_description")
    private String damageDescription;

    @Column(name = "damage_cost")
    private double damageCost;
}