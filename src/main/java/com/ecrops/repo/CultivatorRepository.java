package com.ecrops.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ecrops.entity.Cultivator;

@Repository
public interface CultivatorRepository extends JpaRepository<Cultivator, Integer> {

	@Query(value = "SELECT * , (anubhavadar_extent-occupant_extent) available_extent  FROM ecrop2023.cr_booking WHERE cr_vcode= :crVcode and kh_no =:fromKhno", nativeQuery = true)
	List<Cultivator> getCultivatorDetailsByKathaNo(@Param("fromKhno") Integer fromKhno,
			@Param("crVcode") Integer crVcode);

	@Query(value = "SELECT SUM(tot_extent) tot_extent FROM ecrop2023.CR_BOOKING WHERE kh_no = :khNo and cr_farmeruid = :cr_farmeruid AND owner_tenant = :owner_tenant GROUP BY kh_no, cr_farmeruid", nativeQuery = true)
	List<Cultivator> getCultivatorDetailsByKathaNo(@Param("khNo") Integer khNo,
			@Param("owner_tenant") String owner_tenant, @Param("cr_farmeruid") String cr_farmeruid);

	@Query(value = "SELECT sum(occupant_extent) FROM ecrop2023.cr_booking WHERE part_key = :part_key and kh_no = :kh_no and cr_vcode= :cr_vcode and cultivator_type IS NOT NULL and cr_sno = :cr_sno", nativeQuery = true)
	public Float getTotalOccupantExtent(@Param("part_key") String part_key, @Param("kh_no") Integer kh_no,
			@Param("cr_vcode") Integer cr_vcode, @Param("cr_sno") String cr_sno);

	@Query(value = "SELECT anubhavadar_extent FROM ecrop2023.cr_booking WHERE part_key = :part_key and kh_no = :kh_no and cr_vcode= :cr_vcode  and owner_tenant = 'O' and cr_sno = :cr_sno limit 1", nativeQuery = true)
	public Float getAnubhavadarExtent(@Param("part_key") String part_key, @Param("kh_no") Integer kh_no,
			@Param("cr_vcode") Integer cr_vcode, @Param("cr_sno") String cr_sno);
}
