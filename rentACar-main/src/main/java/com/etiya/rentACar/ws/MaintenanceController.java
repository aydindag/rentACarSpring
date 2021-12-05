package com.etiya.rentACar.ws;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.etiya.rentACar.business.abstracts.MaintenanceService;
import com.etiya.rentACar.business.dtos.MaintenanceSearchListDto;
import com.etiya.rentACar.business.request.maintenanceRequests.CreateMaintenanceRequest;
import com.etiya.rentACar.business.request.maintenanceRequests.DeleteMaintenanceRequest;
import com.etiya.rentACar.business.request.maintenanceRequests.UpdateMaintenanceRequest;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("api/maintenance")
public class MaintenanceController {

	private MaintenanceService maintenanceService;

	public MaintenanceController(MaintenanceService maintenanceService) {
		super();
		this.maintenanceService = maintenanceService;
	}
	
	@GetMapping("list")
	public DataResult<List<MaintenanceSearchListDto>> getAll(){
		return this.maintenanceService.getAll();
	}
	@PostMapping("add")
	public Result add(@RequestBody @Valid CreateMaintenanceRequest createMaintenanceRequest) {
		return this.maintenanceService.save(createMaintenanceRequest);
	}
	@DeleteMapping("delete")
	public Result delete(@RequestBody @Valid DeleteMaintenanceRequest deleteMaintenanceRequest) {
		return this.maintenanceService.delete(deleteMaintenanceRequest);
	}
	@PutMapping("update")
	public Result update(@RequestBody @Valid UpdateMaintenanceRequest updateMaintenanceRequest) {
		return this.maintenanceService.update(updateMaintenanceRequest);
	}
}
