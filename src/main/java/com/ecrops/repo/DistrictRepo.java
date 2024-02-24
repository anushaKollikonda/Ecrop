package com.ecrops.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ecrops.entity.Districts;

public interface DistrictRepo  extends JpaRepository<Districts, Integer>{
	
	@Query(value="select wbdcode ,wbmcode,type_user from user_registration   where  userid=:userId",nativeQuery=true)
	String getDistByDcodeAndUserId(String userId);
	
	

}
