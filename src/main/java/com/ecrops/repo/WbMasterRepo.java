package com.ecrops.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ecrops.entity.WbMaster;

import antlr.collections.List;

@Repository
public interface WbMasterRepo extends JpaRepository<WbMaster, Integer>  {

	@Query(value = "select wb.wbedname, wb.wbemname, wb.wbevname, wb.wbdcode, wb.wbmcode, wb.wbvcode, wb.mcode"
			+ " from wbvillage_mst wb where wb.wbdcode = :wbdcode and wb.wbmcode = :wbmcode  limit 1 ", nativeQuery = true)

	public WbMaster getWbMasterDetailsForMandal(@Param("wbmcode") Integer wbmcode, @Param("wbdcode") Integer wbdcode);

	@Query(value = "select wb.wbedname, wb.wbemname, wb.wbevname, wb.wbdcode, wb.wbmcode, wb.wbvcode,wb.mcode"
			+ " from wbvillage_mst wb where  wb.wbdcode = :wbdcode and wb.wbmcode = :wbmcode"
			+ " and wb.wbvcode = :wbvcode", nativeQuery = true)

	public WbMaster getWbMasterDetailsForVillage(@Param("wbvcode") Integer wbvcode, @Param("wbmcode") Integer wbmcode,
			@Param("wbdcode") Integer wbdcode);

}
