package com.etiya.rentACar.dataAccess.abstracts;

import java.util.List;

import com.etiya.rentACar.business.dtos.CarSearchListDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.etiya.rentACar.entities.Car;
import com.etiya.rentACar.entities.complexTypes.CarDetail;

public interface CarDao extends JpaRepository<Car, Integer>{
	@Query("Select new com.etiya.rentACar.entities.complexTypes.CarDetail "
            + " (c.carId,b.brandName,cl.colorName,c.dailyPrice,c.modelYear,c.description,c.cityName, c.findeksPointCar, c.kilometer) "
            + "From Car c Inner join c.color cl inner join c.brand b")
	List<CarDetail> getCarWithColorAndBrandDetails();
	List<Car> getByBrand_BrandId(int brandId);
	List<Car> getByColor_ColorId(int colorId);
	List<Car> getByCityName(String cityName);
}
