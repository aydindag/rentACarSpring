package com.etiya.rentACar.ws;

import com.etiya.rentACar.business.abstracts.CarDamageService;
import com.etiya.rentACar.business.dtos.CarDamageSearchListDto;
import com.etiya.rentACar.business.request.brandRequests.UpdateBrandRequest;
import com.etiya.rentACar.business.request.carDamageRequests.CreateCarDamageRequest;
import com.etiya.rentACar.business.request.carDamageRequests.DeleteCarDamageRequest;
import com.etiya.rentACar.business.request.carDamageRequests.UpdateCarDamageRequest;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/carDamages")
public class CarDamageController {
    private CarDamageService carDamageService;

    @Autowired
    public CarDamageController(CarDamageService carDamageService) {
        this.carDamageService = carDamageService;
    }

    @GetMapping("list")
    public DataResult<List<CarDamageSearchListDto>> getAllDamages(){
        return this.carDamageService.getAllDamages();
    }
    @PostMapping("add")
    public Result add(@RequestBody @Valid CreateCarDamageRequest createCarDamageRequest){
        return this.carDamageService.save(createCarDamageRequest);
    }
    @DeleteMapping("delete")
    public Result delete(@RequestBody @Valid DeleteCarDamageRequest deleteCarDamageRequest){
        return this.carDamageService.delete(deleteCarDamageRequest);
    }
    @PutMapping("update")
    public Result update(@RequestBody @Valid UpdateCarDamageRequest updateCarDamageRequest){
        return this.carDamageService.update(updateCarDamageRequest);
    }
    @GetMapping("getDamagesByCarId")
    public DataResult<List<CarDamageSearchListDto>> getDamagesOfASpecificCar(@RequestParam("car_id") int carId){
        return this.carDamageService.getDamagesByCarId(carId);
    }
}
