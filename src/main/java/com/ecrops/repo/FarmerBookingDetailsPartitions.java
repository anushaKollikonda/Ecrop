package com.ecrops.repo;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.ecrops.entity.FarmerBookingDetails;

@Repository
@Transactional
public class FarmerBookingDetailsPartitions {

	@PersistenceContext
	private EntityManager entityManager;

	public List<FarmerBookingDetails> farmerBookingDetails(String dcode, String mcode,String cropyear,String wbdcode) {
		String[] season = cropyear.split("@");
		String seasonType = season[0];
		Integer seasonYear = Integer.parseInt(season[1]);
		
		String part_key = "";
		if (Integer.parseInt(wbdcode) > 9) {
			part_key = seasonType + Integer.parseInt(wbdcode) + seasonYear;
		} else {
			part_key = seasonType + "0" + Integer.parseInt(wbdcode) + seasonYear;
		}
		String tableName = "ecrop" + seasonYear + "." + "cr_details_" + part_key;

		System.out.println("tableName---------------->" + tableName);

		String Sql = " select bookingid,occupname as oc_name,occupfname as oc_fname,cr_sno,kh_no,tot_extent,"
				+ "occupant_extent,COALESCE(mobileno, 0) as mobileno,cr_mand_code,mcode from " + tableName + "  where dcode=? and "
				+ " mcode=?  and cr_year=? and cr_season=?  ";

		Query insertQuery = (Query) entityManager.createNativeQuery(Sql);
		insertQuery.setParameter(1, Integer.parseInt(dcode));
		insertQuery.setParameter(2, Integer.parseInt(mcode));
		insertQuery.setParameter(3,  seasonYear);
		insertQuery.setParameter(4, seasonType);
		
		//System.out.println("insertQuery=>"+insertQuery);
		List<Object[]> detailsEntities1 = insertQuery.getResultList();
		//System.out.println("detailsEntities1=>"+detailsEntities1.size());
		//System.out.println("detailsEntities1=>"+detailsEntities1.toString());
		List<FarmerBookingDetails> detailsEntities = new ArrayList<FarmerBookingDetails>();
		

		for (Object[] row : detailsEntities1) {

			FarmerBookingDetails entity = new FarmerBookingDetails();
			entity.setBookingid(Long.valueOf(row[0].toString()));
			entity.setOc_name((String) row[1]);
			entity.setOc_fname((String) row[2]);
			entity.setCr_sno((String) row[3]);
			entity.setOccupant_extent(((BigDecimal) row[4]).intValue());
			entity.setKh_no(((BigDecimal) row[5]).intValue());
			entity.setTot_extent(((BigDecimal) row[6]).intValue());
			entity.setMobileno(Long.valueOf(row[7].toString()));
			detailsEntities.add(entity);

		}
		
		return detailsEntities;

	}


}