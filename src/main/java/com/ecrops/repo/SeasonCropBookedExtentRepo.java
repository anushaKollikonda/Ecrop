package com.ecrops.repo;

import java.util.List;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ecrops.entity.SeasonCropBookedExtent;
import com.ecrops.projections.MasterProjections;


public interface SeasonCropBookedExtentRepo  extends JpaRepository<SeasonCropBookedExtent, String>{
	

	@Query(value="select distinct on (a.cropyear,a.season) concat(a.season,'@',cropyear) as col1,concat(b.seasonname,' ',cropyear) as col2 \r\n" + 
			" from activeseason a,season b  where   a.season=b.season and a.active='A' order by a.cropyear,a.season",nativeQuery = true)
	public List<MasterProjections> getAllSeason();
	
	@Query(value="select mcode as col1,mname as col2 from mandal_2011_cs where dcode=:dcode ",nativeQuery=true)
	public List<MasterProjections> getAllMandals(@Param("dcode") Integer dcode);
	
	@Query(value="select distinct(wbvcode) as col1,wbvname as col2 from wbvillage_mst "
			+ "where dcode=:dcode and mcode=:mcode order by wbvname",nativeQuery=true)
	public List<MasterProjections> getAllVillages(@Param("dcode") Integer dcode ,@Param("mcode") Integer mcode);
	
	@Query(value=" select cropgrpid as col1,grpname as col2 from cropgroups where active='A' ",nativeQuery=true)
	public List<MasterProjections> getAllCropGrp();
	
	@Query(value="select cropid  as col1,cropname as col2 from cropnames  "
			+ "where grpcode=:grpcode order by cropname ",nativeQuery=true)
	public List<MasterProjections> getAllCrpGrpid(@Param("grpcode") Integer grpcode);
	
	
	@Query(value="select mapped_extent,( cast(occupant_extent as numeric)-cast(mapped_extent as numeric) ) as avail_ext, b.dcode,b.mcode,\r\n"
			+ "b.wbvcode,village_name as wbvname,pattadar_name as ocname,pattadar_father_name as ocfname,booking_available,allowable_ext,\r\n"
			+ "occupant_name  as farmer_name,occupant_father_name  as father_name, cr_sno  as survey_no, kh_no, occupant_extent, total_extent as \r\n"
			+ "tot_extent from ecrop2023.cr_details_efish_2023 a, wbvillage_mst b where booking_available is null and  \r\n"
			+ "a.cr_vcode=cast(b.wbvcode as character varying)  and  b.dcode=:dcode and b.mcode=:mcode \r\n"
			+ "and b.wbvcode=:wbvcode order by wbvname",nativeQuery=true)
	public List<BlockedEfishExtent> findByExtent(@Param("dcode")Integer dcode,
			@Param("mcode")Integer mcode,@Param("wbvcode") Integer wbvcode);
	
	interface BlockedEfishExtent{
		String getMapped_extent();
		String getAvail_ext();
		String getDcode();
		String getMcode();
		String getWbvcode();
		String getwbvname();
		String getOcname();
		String getOcfname();
		String getBooking_available();
		String getAllowable_ext();
		String getFarmer_name();
		String getFather_name();
		String getSurvey_no();
		String getKh_no();
		String getOccupant_extent();
		String getTot_extent();
	}
@Query(value="select cast(code as character varying) as code ,category,remarks,crb_remarks "
		+ "from obj_unobj order by crb_remarks ",nativeQuery=true)
List<ObjUnobj> getObjObj();
interface ObjUnobj{
String getCode();	
String getCategory();	
String getRemarks();	
String getCrb_remarks();	
}
@Query(value="select c.vname, a.emp_code,emp_name,rbkuserid,mobile,email,'xxxxxxxx'||right(aadhaar_id,4) as aadhaar_id,case when designation='1'\r\n"
		+ " then 'VAA' when designation='2'  then 'VHA' when designation='3' then 'VSA' end as designation from ecrop2023.cr_emp_profile a , \r\n"
		+ " ecrop2023.emp_rbk_map b,vill_sec_det c where  a.dcode=b.dcode and a.mcode=b.mcode and b.dcode=c.dcode and b.mcode=c.mcode and \r\n"
		+ " b.rbkcode=c.vcode and  a.emp_code=b.empcode and a.dcode=:dcode and a.mcode=:mcode order by vname\r\n"
		+ "",nativeQuery=true)
List<EmployeeList> getEmpList(@Param("dcode") Integer dcode,@Param("mcode") Integer mcode);
interface EmployeeList{
	String getVname();
	String getEmp_code();
	String getEmp_name();
	String getRbkuserid();
	String getMobile();
	String getEmail();
	String getAadhaar_id();
	String getDesignation();
	
}	
@Query(value="SELECT userid,districtname,mandalname,villagename,name,mobile_phone,emailid,regdate,status  from user_registration_vs_v \r\n"
		+ "where status='A' and CAST(mcode AS text)=:mcode and type_user='25' order by mandalname,villagename \r\n"
		+ "",nativeQuery=true)
List<RepVaaDetails> getVaaDet(@Param("mcode") String mcode);
interface RepVaaDetails{
 String getUserid();	
 String getDistrictname();	
 String getMandalname();	
 String getVillagename();	
 String getName();	
 String getMobile_phone();	
 String getEmailid();	
 String getRegdate();	
 String getStatus();	
 
}
@Query(value="select distinct blockortehsil,village,userid,mobile_phone,emailid,dt.status,dt.dt_crt, imei1,imei2 from user_registration ur, "
		+ " devicedet dt  where ur.village=cast(dt.vcode as varchar) and cast(blockortehsil as text)=:mcode and type_user='25' "
		+ " and cropyear =:cropyear and season =:season order by village  ",nativeQuery=true)
List<DeviceRegDetails> getDevRegDet(@Param("mcode") String mcode,
		@Param("cropyear") Integer cropyear,
		@Param("season") String season);

interface DeviceRegDetails{
	String getBlockortehsil();
	String getWbmname();
	String getWbvname();
	String getVillage();
	String getUserid();
	String getMobile_phone();
	String getEmailid();
	String getStatus();
	String getDt_crt();
	String getImei1();
	String getImei2();
}
	
	@Query(value="select wbdname,wbmname, wbvname,claimid,claim_type,gri_type, old_extent, new_extent,old_cropname,new_cropname,old_varietyname,new_varietyname,\r\n"
			+ "old_name,new_name,old_uid,concat('********',substr(new_uid,8,4)) as newuid ,remarks from rep_cropins_gri_v "
			+ "where dcode=:dcode and mcode=:mcode and cropyear=:cropyear and season=:season \r\n"
			+ "  ",nativeQuery=true)
	List<CropInsGrievance> getCropIns(@Param("dcode") Integer dcode,
			                        @Param("mcode") Integer mcode,
			                        @Param("cropyear") Integer cropyear,
			                		@Param("season") String season);
	interface CropInsGrievance{
	String getWbdname();
	String getWbmname();
	String getWbvname();
	String getClaimid();
	String getClaim_type();
	String getGri_type();
	String getOld_extent();
	String getNew_extent();
	String getOld_cropname();
	String getNew_cropname();
	String getOld_varietyname();
	String getNew_varietyname();
	String getOld_name();
	String getNew_name();
	String getOld_uid();
	String getNewuid();
	String getRemarks();
	}
	
	@Query(value="select cast(kh_no as character varying) as kh_no,cr_sno,oc_name,oc_fname,occupname,occupfname,cast(tot_extent as character varying)\r\n"
			+ "as tot_extent, cast(occupant_extent as character varying) ,cr_farmeruid, cast(mobileno as varchar), cast(gender as varchar),\r\n"
			+ "wbdname,wbmname,wbvname from pattmast_nonwebland a,wbvillage_mst b where a.cr_dist_code=b.wbdcode and a.cr_mand_code=b.wbmcode\r\n"
			+ "and a.cr_vcode=b.wbvcode  and a.dcode=:dcode and a.mcode=:mcode  and cr_season=:season and cr_year=:cropyear"
			+ " order by wbdname,wbmname,wbvname",nativeQuery=true)
	List<NonWebView> getNonwebView(
			@Param("dcode") Integer dcode,
			@Param("mcode") Integer mcode,
    		@Param("season") String season,
    		@Param("cropyear") Integer cropyear);
	
	interface NonWebView{
		String getKh_no();
		String getCr_sno();
		String getOc_name();
		String getOc_fname();
		String getOccupname();
		String getOccupfname();
		String getTot_extent();
		String getOccupant_extent();
		String getCr_farmeruid();
		String getMobileno();
		String getGender();
		}
	
}
