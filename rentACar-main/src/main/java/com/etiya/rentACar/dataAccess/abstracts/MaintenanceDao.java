package com.etiya.rentACar.dataAccess.abstracts;

import java.util.List;

import org.jboss.jandex.Main;
import org.springframework.data.jpa.repository.JpaRepository;

import com.etiya.rentACar.entities.Maintenance;

public interface MaintenanceDao extends JpaRepository<Maintenance, Integer>{
	List<Maintenance> getByCar_CarId(int carId);
	boolean existsByMaintenanceId(int maintenanceId);
}
