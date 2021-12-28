package com.etiya.rentACar.ws;

import java.util.List;

import javax.validation.Valid;

import com.etiya.rentACar.entities.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.etiya.rentACar.business.abstracts.CarService;
import com.etiya.rentACar.business.dtos.CarDetailDto;
import com.etiya.rentACar.business.dtos.CarSearchListDto;
import com.etiya.rentACar.business.request.carRequests.CreateCarRequest;
import com.etiya.rentACar.business.request.carRequests.DeleteCarRequest;
import com.etiya.rentACar.business.request.carRequests.UpdateCarRequest;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;
import com.etiya.rentACar.entities.complexTypes.CarDetail;

@RestController
@RequestMapping("api/car")
public class CarController {
	
	private CarService carService;
	
	@Autowired
	public CarController(CarService carService) {
		super();
		this.carService = carService;
	}

	@GetMapping("list")
	public List<CarSearchListDto> getCars(){
		return this.carService.getCars();
	}
	@GetMapping("getWithBrandAndColor")
	public DataResult<List<CarDetail>> getDetailedCars(){
		return this.carService.getCarWithColorAndBrandDetails();
	}
	@GetMapping("getCarDetailsWithCarImages")
	DataResult<CarDetailDto> getCarDetailsByCarId(@RequestParam("carId") int carId) {
		return this.carService.getCarDetailsByCarId(carId);
	}
	@GetMapping("getCarsWithBrandId")
	public DataResult<List<CarSearchListDto>> getCarsByBrandId(@RequestParam("brand_id") int brandId){
		return this.carService.getByBrandId(brandId);
	}
	@GetMapping("getCarsWithColorId")
	public DataResult<List<CarSearchListDto>> getCarsByColorId(@RequestParam("color_id") int colorId){
		return this.carService.getByColorId(colorId);
	}
	@GetMapping("getCarsWithCity")
	public DataResult<List<CarSearchListDto>> getCarsByCityName(@RequestParam("city_id") int cityId){
		return this.carService.getByCity(cityId);
	}
	@PostMapping("add")
	public Result add(@RequestBody @Valid CreateCarRequest createCarRequest) {
		return this.carService.save(createCarRequest);
	}
	@DeleteMapping("delete")
	public Result delete(@RequestBody @Valid DeleteCarRequest deleteCarRequest) {
		return this.carService.delete(deleteCarRequest);
	}
	@PutMapping("update")
	public Result update(@RequestBody @Valid UpdateCarRequest updateCarRequest) {
		return this.carService.update(updateCarRequest);
	}
	@GetMapping("getAllWithoutCarsAtMaintenance")
		public DataResult<List<CarDetail>> getAllExcludingCarsAtMaintenance(){
			return this.carService.getCarsThatAreNotOnMaintenance();
		}
}
