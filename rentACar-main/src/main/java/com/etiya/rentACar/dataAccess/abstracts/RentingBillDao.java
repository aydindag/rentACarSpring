package com.etiya.rentACar.dataAccess.abstracts;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.etiya.rentACar.entities.RentingBill;

public interface RentingBillDao extends JpaRepository<RentingBill, Integer>{
	List<RentingBill> getByUser_UserId(int userId);
	
	List<RentingBill> findByCreationDateBetween(Date startDate, Date endDate);
}
