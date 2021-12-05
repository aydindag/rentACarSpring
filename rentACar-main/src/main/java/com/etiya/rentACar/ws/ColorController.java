package com.etiya.rentACar.ws;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.etiya.rentACar.business.abstracts.ColorService;
import com.etiya.rentACar.business.dtos.ColorSearchListDto;
import com.etiya.rentACar.business.request.colorRequests.CreateColorRequest;
import com.etiya.rentACar.business.request.colorRequests.DeleteColorRequest;
import com.etiya.rentACar.business.request.colorRequests.UpdateColorRequest;
import com.etiya.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("api/color")
public class ColorController {
	
	private ColorService colorService;
	
	@Autowired
	public ColorController(ColorService colorService) {
		super();
		this.colorService = colorService;
	}


	@GetMapping("list")
	public List<ColorSearchListDto> getAll(){
		return this.colorService.getColors();
	}
	@PostMapping("add")
	public Result add(@RequestBody @Valid CreateColorRequest createColorRequest) {
		return this.colorService.save(createColorRequest);
	}
	@DeleteMapping("delete")
	public Result delete(@RequestBody @Valid DeleteColorRequest deleteColorRequest) {
		return this.colorService.delete(deleteColorRequest);
	}
	@PutMapping("update")
	public Result update(@RequestBody @Valid UpdateColorRequest updateColorRequest) {
		return this.colorService.update(updateColorRequest);
	}
}
