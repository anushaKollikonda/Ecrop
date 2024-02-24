package com.ecrops.repo;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ecrops.entity.NormalAreasMwiseMao;
import com.ecrops.projections.MasterProjections;



public interface NormalAreasMwiseMaoRepo extends JpaRepository<NormalAreasMwiseMao, String> {
	
	@Query(value="select b.mname ,c.wbvname ,COALESCE(SUM(a.normalarea), 0) as normalarea  from ecrop2023.village_crop_normalareas a inner join mandal_2011_cs b \r\n"
			+ " on a.mcode=b.mcode inner join wbvillage_mst c on a.vcode=c.wbvcode where a.dcode=:dcode and a.mcode=:mcode and a.cropyear=:cropyear and \r\n"
			+ " season=:season group by b.mname,c.wbvname", nativeQuery = true)
	List<NormalAreasMwiseMao> getListt(@Param("dcode") Integer dcode, @Param("mcode") Integer mcode ,
			@Param("season") String season,@Param("cropyear") Integer cropyear);
	
	@Query(value="select distinct on (a.cropyear,a.season) concat(a.season,'@',cropyear) as col1,concat(b.seasonname,' ',cropyear) as col2 \r\n" + 
			" from activeseason a,season b  where   a.season=b.season and a.active='A' order by a.cropyear,a.season",nativeQuery = true)
	public List<MasterProjections> getAllSeason();
	
}
