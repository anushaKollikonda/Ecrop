package com.ecrops.repo;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ecrops.entity.AllocatedSurveyNoMapping;
import com.ecrops.projections.MasterProjections;
@Repository
public interface AllocataedSurveyNoMappingRepo extends JpaRepository<AllocatedSurveyNoMapping, String> {

	@Query(value="select distinct on (a.cropyear,a.season) concat(a.season,'@',cropyear) as col1,concat(b.seasonname,' ',cropyear) as col2 \r\n" + 
			" from activeseason a,season b  where   a.season=b.season and a.active='A' order by a.cropyear,a.season",nativeQuery = true)
	public List<MasterProjections> getAllSeason();
	
	
	
	
	/*
	 * @Query(
	 * value="select vcode as col1,vname as col2 from vill_sec_det where vcode in (select distinct rbkcode "
	 * + "from rbk_surveyno_mapping where   mcode=?",nativeQuery = true) public
	 * List<MasterProjections> getRBK(@Param("mcode") Integer mcode);
	 */
	

}
