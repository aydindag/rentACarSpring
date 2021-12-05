package com.etiya.rentACar.ws;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.etiya.rentACar.business.abstracts.CarImageService;
import com.etiya.rentACar.business.dtos.CarImageSearchListDto;
import com.etiya.rentACar.business.request.carImageRequests.CreateCarImageRequest;
import com.etiya.rentACar.business.request.carImageRequests.DeleteCarImageRequest;
import com.etiya.rentACar.business.request.carImageRequests.UpdateCarImageRequest;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("api/carImages")
public class CarImageController {

	private CarImageService carImageService;
	@Autowired
	public CarImageController(CarImageService carImageService) {
		super();
		this.carImageService = carImageService;
	}
	
	@GetMapping("list")
	public List<CarImageSearchListDto> getCarImages(){
		return this.carImageService.getCarImages();
	}
	
	@GetMapping("getCarImagesByCarId")
    public DataResult<List<CarImageSearchListDto>> getCarImagesByCarId(int carId) {
        return this.carImageService.getCarImageByCarId(carId);
    }
	
	@PostMapping("add")
	public Result add(@RequestParam("car_id") int carId, MultipartFile multipartFile) throws Exception {
		CreateCarImageRequest carImageRequest = new CreateCarImageRequest();
		carImageRequest.setCarId(carId);
		carImageRequest.setMultipartFile(multipartFile);
		return carImageService.save(carImageRequest);
	}
	@DeleteMapping("delete")
	public Result delete(@RequestBody @Valid DeleteCarImageRequest deleteCarImageRequest) {
		return this.carImageService.delete(deleteCarImageRequest);
	}
	@PutMapping("update")
	public Result update(@RequestParam("image_id") int imageId, MultipartFile multipartFile) throws IOException {
		UpdateCarImageRequest updateCarImageRequest = new UpdateCarImageRequest();
		updateCarImageRequest.setMultipartFile(multipartFile);
		updateCarImageRequest.setImageId(imageId);
		return carImageService.update(updateCarImageRequest);
	}
}
