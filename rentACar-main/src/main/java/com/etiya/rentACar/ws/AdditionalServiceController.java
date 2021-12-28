package com.etiya.rentACar.ws;

import com.etiya.rentACar.business.abstracts.AdditionalServiceService;
import com.etiya.rentACar.business.dtos.AdditionalServiceSearchListDto;
import com.etiya.rentACar.business.request.additionalServiceRequests.CreateAdditionalServiceRequest;
import com.etiya.rentACar.business.request.additionalServiceRequests.DeleteAdditionalServiceRequest;
import com.etiya.rentACar.business.request.additionalServiceRequests.UpdateAdditionalServiceRequest;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;
import com.etiya.rentACar.entities.AdditionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/additionalServices")
public class AdditionalServiceController {
    private AdditionalServiceService additionalServiceService;

    @Autowired
    public AdditionalServiceController(AdditionalServiceService additionalServiceService) {
        this.additionalServiceService = additionalServiceService;
    }
    @GetMapping("list")
    public DataResult<List<AdditionalServiceSearchListDto>> getAll(){
        return this.additionalServiceService.getAdditionalServices();
    }
    @PostMapping("add")
    public Result add(@RequestBody @Valid CreateAdditionalServiceRequest createAdditionalServiceRequest){
        return this.additionalServiceService.save(createAdditionalServiceRequest);
    }
    @DeleteMapping("delete")
    public Result delete(@RequestBody @Valid DeleteAdditionalServiceRequest deleteAdditionalServiceRequest){
        return this.additionalServiceService.delete(deleteAdditionalServiceRequest);
    }
    @PutMapping("update")
    public Result update(@RequestBody @Valid UpdateAdditionalServiceRequest updateAdditionalServiceRequest){
        return this.additionalServiceService.update(updateAdditionalServiceRequest);
    }
}
