package com.etiya.rentACar.ws;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.etiya.rentACar.business.abstracts.RentingBillService;
import com.etiya.rentACar.business.dtos.RentingBillSearchListDto;
import com.etiya.rentACar.core.utilities.results.DataResult;

@RestController
@RequestMapping("api/rentingBills")
public class RentingBillController {
	private RentingBillService rentingBillService;

	@Autowired
	public RentingBillController(RentingBillService rentingBillService) {
		super();
		this.rentingBillService = rentingBillService;
	}
	
	@GetMapping("list")
	public DataResult<List<RentingBillSearchListDto>> getAll(){
		return this.rentingBillService.getAll();
	}
	@GetMapping("getBillsByUserId")
	public DataResult<List<RentingBillSearchListDto>> getBillsByUserId(@RequestParam int userId){
		return this.rentingBillService.getRentingBillByUserId(userId);
	}
	@GetMapping("getBillsBetweenDates")
	public DataResult<List<RentingBillSearchListDto>> getBillsBetweenDates(@RequestParam Date startDate, @RequestParam Date endDate){
		return this.rentingBillService.getRentingBillByDateInterval(startDate, endDate);
	}
	
}
