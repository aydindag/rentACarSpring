package com.etiya.rentACar.dataAccess.abstracts;

import com.etiya.rentACar.entities.AdditionalService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdditionalServiceDao extends JpaRepository<AdditionalService, Integer> {
AdditionalService getByServiceId(int serviceId);
boolean existsByServiceId(int serviceId);
}
