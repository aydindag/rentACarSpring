package com.etiya.rentACar.business.concretes;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.etiya.rentACar.business.abstracts.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.rentACar.business.abstracts.CarImageService;
import com.etiya.rentACar.business.dtos.CarImageSearchListDto;
import com.etiya.rentACar.business.request.carImageRequests.CreateCarImageRequest;
import com.etiya.rentACar.business.request.carImageRequests.DeleteCarImageRequest;
import com.etiya.rentACar.business.request.carImageRequests.UpdateCarImageRequest;
import com.etiya.rentACar.core.utilities.business.BusinessRules;
import com.etiya.rentACar.core.utilities.helpers.FileHelper;
import com.etiya.rentACar.core.utilities.helpers.FileHelperManager;
import com.etiya.rentACar.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.ErrorResult;
import com.etiya.rentACar.core.utilities.results.Result;
import com.etiya.rentACar.core.utilities.results.SuccessDataResult;
import com.etiya.rentACar.core.utilities.results.SuccessResult;
import com.etiya.rentACar.dataAccess.abstracts.CarImageDao;
import com.etiya.rentACar.entities.CarImage;

@Service
public class CarImageManager implements CarImageService {

	private CarImageDao carImageDao;
	private ModelMapperService modelMapperService;
	private CarService carService;
	FileHelper fileHelper = new FileHelperManager();

	@Autowired
	public CarImageManager(CarImageDao carImageDao, ModelMapperService modelMapperService, CarService carService) {
		super();
		this.carImageDao = carImageDao;
		this.modelMapperService = modelMapperService;
		this.carService = carService;
	}

	@Override
	public List<CarImageSearchListDto> getCarImages() {
		List<CarImage> list = this.carImageDao.findAll();	
		List<CarImageSearchListDto> response = list.stream()
				.map(carImage -> modelMapperService.forDto().map(carImage, CarImageSearchListDto.class))
				.collect(Collectors.toList());

		return response;
	}

	@Override
	public Result save(CreateCarImageRequest createCarImageRequest) throws Exception {
		
		Result result = BusinessRules.run(checkCarImageCount(createCarImageRequest.getCarId()),
				carService.checkExistingCar(createCarImageRequest.getCarId()));

		if (result != null) {
			return result;
		}
		CarImage carImage = modelMapperService.forRequest().map(createCarImageRequest, CarImage.class);
		String carImageName = fileHelper
				.saveImage(createCarImageRequest.getCarId(), createCarImageRequest.getMultipartFile()).getMessage();
		Date dateNow = new java.sql.Date(new java.util.Date().getTime());
		carImage.setDate(dateNow);
		carImage.setImagePath(
				fileHelper.returnFilePath(createCarImageRequest.getCarId()).getMessage() + "\\" + carImageName);
		this.carImageDao.save(carImage);
		return new SuccessResult();
	}

	@Override
	public Result delete(DeleteCarImageRequest deleteCarImageRequest) {
		CarImage carImage = this.carImageDao.getById(deleteCarImageRequest.getImageId());
		String toBeDeletedPath = carImage.getImagePath();
		fileHelper.deleteImage(toBeDeletedPath);
		this.carImageDao.delete(carImage);
		return new SuccessResult();
	}

	@Override
	public Result update(UpdateCarImageRequest updateCarImageRequest) throws IOException {
		CarImage carImage = this.carImageDao.getById(updateCarImageRequest.getImageId());
		int id = carImage.getCar().getCarId();
		fileHelper.deleteImage(carImage.getImagePath());
		String carImageName = fileHelper.saveImage(id, updateCarImageRequest.getMultipartFile()).getMessage();
		Date dateNow = new java.sql.Date(new java.util.Date().getTime());
		carImage.setDate(dateNow);
		carImage.setImagePath(
				fileHelper.returnFilePath(updateCarImageRequest.getCarId()).getMessage() + "\\" + carImageName);
		this.carImageDao.save(carImage);
		return new SuccessResult();
	}

	public DataResult<List<CarImageSearchListDto>> getCarImageByCarId(int carId) {
		Result resultCheck = BusinessRules.run(checkIfThereIsNoPicture(carId));
		if (resultCheck != null) {
			List<CarImage> carImages = new ArrayList<CarImage>();
			CarImage carImage1 = new CarImage();
			carImage1.setImagePath("C:\\Users\\aydin.dag\\Desktop\\carPicture");
			carImages.add(carImage1);
			List<CarImageSearchListDto> list1 = carImages.stream()
					.map(carImage -> modelMapperService.forDto().map(carImage, CarImageSearchListDto.class))
					.collect(Collectors.toList());
			return new SuccessDataResult<List<CarImageSearchListDto>>(list1);
		}
		List<CarImage> result = this.carImageDao.getByCar_CarId(carId);
		List<CarImageSearchListDto> list = result.stream()
				.map(carImage -> modelMapperService.forDto().map(carImage, CarImageSearchListDto.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<CarImageSearchListDto>>(list); 
	}

	private Result checkCarImageCount(int carId) {
		File file = new File("C:\\Users\\aydin.dag\\Desktop\\img\\car" + carId);
		if (file.exists()) {
			int numberOfFiles = file.listFiles().length;
			if (numberOfFiles >= 5) {
				return new ErrorResult("Bir araç için en fazla 5 resim kaydedilebilir.");
			}
		}
		return new SuccessResult();
	}

	private Result checkIfThereIsNoPicture(int carId) {
		File file = new File("C:\\Users\\aydin.dag\\Desktop\\img\\car" + carId);
		if (file.exists()) {
			if (file.listFiles().length == 0) {
				return new ErrorResult("default picture");
			}
		} else {
			return new ErrorResult("default picture1");
		}
		return new SuccessResult();
	}

	@Override
	public List<CarImage> getCarImageListByCarId(int carId) {
		return this.carImageDao.getByCar_CarId(carId);
	}

}
