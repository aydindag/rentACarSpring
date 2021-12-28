package com.etiya.rentACar.business.concretes;

import com.etiya.rentACar.business.abstracts.AdditionalServiceService;
import com.etiya.rentACar.business.constants.messages.AdditionalServiceMessages;
import com.etiya.rentACar.business.dtos.AdditionalServiceSearchListDto;
import com.etiya.rentACar.business.request.additionalServiceRequests.CreateAdditionalServiceRequest;
import com.etiya.rentACar.business.request.additionalServiceRequests.DeleteAdditionalServiceRequest;
import com.etiya.rentACar.business.request.additionalServiceRequests.UpdateAdditionalServiceRequest;
import com.etiya.rentACar.core.utilities.business.BusinessRules;
import com.etiya.rentACar.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACar.core.utilities.results.*;
import com.etiya.rentACar.dataAccess.abstracts.AdditionalServiceDao;
import com.etiya.rentACar.entities.AdditionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class AdditionalServiceManager implements AdditionalServiceService {
    private ModelMapperService modelMapperService;
    private AdditionalServiceDao additionalServiceDao;

    @Autowired
    public AdditionalServiceManager(ModelMapperService modelMapperService, AdditionalServiceDao additionalServiceDao) {
        this.modelMapperService = modelMapperService;
        this.additionalServiceDao = additionalServiceDao;
    }

    @Override
    public DataResult<List<AdditionalServiceSearchListDto>> getAdditionalServices() {
        List<AdditionalService> list = additionalServiceDao.findAll();
        List<AdditionalServiceSearchListDto> response = list.stream().map(additionalService -> modelMapperService.forDto().
                map(additionalService, AdditionalServiceSearchListDto.class)).collect(Collectors.toList());
        return new SuccessDataResult<List<AdditionalServiceSearchListDto>>(response);
    }

    @Override
    public Result save(CreateAdditionalServiceRequest createAdditionalServiceRequest) {
        Result result = BusinessRules.run(checkExistingServiceName(createAdditionalServiceRequest.getServiceName()));
        if(result!=null){
            return result;
        }
        AdditionalService additionalService = modelMapperService.forRequest().
                map(createAdditionalServiceRequest, AdditionalService.class);
        this.additionalServiceDao.save(additionalService);
        return new SuccessResult(AdditionalServiceMessages.add);
    }

    @Override
    public Result delete(DeleteAdditionalServiceRequest deleteAdditionalServiceRequest) {
        Result result = BusinessRules.run(checkExistingServiceId(deleteAdditionalServiceRequest.getServiceId()));
        if( result != null){
            return result;
        }
        AdditionalService additionalService = modelMapperService.forRequest().
                map(deleteAdditionalServiceRequest, AdditionalService.class);
        this.additionalServiceDao.delete(additionalService);
        return new SuccessResult(AdditionalServiceMessages.delete);
    }

    @Override
    public Result update(UpdateAdditionalServiceRequest updateAdditionalServiceRequest) {
        Result result = BusinessRules.run(checkExistingServiceName(updateAdditionalServiceRequest.getServiceName(),
                        updateAdditionalServiceRequest.getServiceId()),
                checkExistingServiceId(updateAdditionalServiceRequest.getServiceId()));
        if(result!=null){
            return result;
        }
        AdditionalService additionalService = modelMapperService.forRequest().
                map(updateAdditionalServiceRequest, AdditionalService.class);
        this.additionalServiceDao.save(additionalService);
        return new SuccessResult(AdditionalServiceMessages.update);
    }

    @Override
    public AdditionalService getById(int serviceId) {
        return additionalServiceDao.getByServiceId(serviceId);
    }

    @Override
    public boolean isExisting(int serviceId) {
        return additionalServiceDao.existsByServiceId(serviceId);
    }

    private Result checkExistingServiceName(String serviceName) {
        String lowerCaseServiceName = serviceName.toLowerCase();
        for (AdditionalService service: additionalServiceDao.findAll()) {
            String lowerCaseService = service.getServiceName().toLowerCase();
            if(lowerCaseService.equals(lowerCaseServiceName)) {
                return new ErrorResult(AdditionalServiceMessages.existingServiceName);
            }
        }
        return new SuccessResult();
    }
    private Result checkExistingServiceName(String serviceName, int serviceId) {
        String lowerCaseServiceName = serviceName.toLowerCase();
        for (AdditionalService service: additionalServiceDao.findAll()) {
            if(additionalServiceDao.getByServiceId(serviceId) == service){
                continue;
            }
            String lowerCaseService = service.getServiceName().toLowerCase();
            if(lowerCaseService.equals(lowerCaseServiceName)) {
                return new ErrorResult(AdditionalServiceMessages.existingServiceName);
            }
        }
        return new SuccessResult();
    }

    private Result checkExistingServiceId(int serviceId){
        if(additionalServiceDao.existsByServiceId(serviceId)){
            return new SuccessResult();
        }
        return new ErrorResult(AdditionalServiceMessages.serviceIdDoesNotExist);
    }

}