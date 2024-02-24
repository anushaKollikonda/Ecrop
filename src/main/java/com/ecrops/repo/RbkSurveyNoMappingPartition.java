package com.ecrops.repo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.ecrops.entity.MaoAuthVaaVroekyc;
import com.ecrops.entity.RbkSurveyNoMapping;

@Repository
@Transactional
public class RbkSurveyNoMappingPartition {
	
	

	@PersistenceContext
	private EntityManager entityManager;

	public List<RbkSurveyNoMapping> rbkSno(String wbdcode, String mcode,String cropyear,String updatedby,String userid) {
		
		String[] season = cropyear.split("@");
		String seasonType = season[0];
		Integer seasonYear = Integer.parseInt(season[1]);
		
		String part_key = "",tableName1="",tableName2="";
		if (Integer.parseInt(wbdcode) > 9) {
			part_key = seasonType + Integer.parseInt(wbdcode) + seasonYear;
		} else {
			part_key = seasonType + "0" + Integer.parseInt(wbdcode) + seasonYear;
		}
		
		 tableName1 = "ecrop" + seasonYear + "." + "cr_booking_partition_" + part_key;
		 tableName2 = "ecrop" + seasonYear + "." + "rbk_surveyno_mapping_" + part_key;

		System.out.println("tableName1---------------->" + tableName1);
		System.out.println("tableName2---------------->" + tableName2);

	

		String sql="  select rbkuserid,mao_alloted_ext,vaa_alloted_ext,occupant_extent from (select rbkuserid,round(sum(tot_extent),2) as mao_alloted_ext from \r\n"
		 		+ " (select distinct mcode,vcode,rbkuserid,kh_no,cr_sno,updatedby,tot_extent from "+tableName2+" where updatedby=?  \r\n"
		 		+ "  order by updatedby,vcode,cr_sno) a1 group by rbkuserid) a, (select srno_userid,round(sum(tot_extent),2) as vaa_alloted_ext,  \r\n"
		 		+ "round(sum(occupant_extent),2) as occupant_extent from (select distinct cr_vcode,cr_sno,srno_userid,tot_extent,occupant_extent \r\n"
		 		+ " from "+tableName1+" where mcode=?) b  group by srno_userid) b where rbkuserid=? ";

		Query sesnyr = (Query) entityManager.createNativeQuery(sql);
		sesnyr.setParameter(1,updatedby);
		sesnyr.setParameter(2, Integer.parseInt(mcode));
		sesnyr.setParameter(3,userid);
		
		
//		sesnyr.setParameter(3,  seasonYear);
//		sesnyr.setParameter(4, seasonType);
		
		System.out.println("sesnyr=>"+sesnyr);
		List<Object[]> ekycMao = sesnyr.getResultList();
		//System.out.println("ekycMao=>"+ekycMao.size());
		//System.out.println("ekycMao=>"+ekycMao.toString());
		List<RbkSurveyNoMapping> entityDetails = new ArrayList<RbkSurveyNoMapping>();
		

		for (Object[] row : ekycMao) {

			RbkSurveyNoMapping entity = new RbkSurveyNoMapping();
			
			System.out.println("row[0]=>"+row[0]);
			System.out.println("row[1]=>"+row[1]);
			
			entity.setRbkuserid((String) row[0]);
			entity.setMao_alloted_ext(((BigDecimal) row[1]).intValue());
			entity.setVaa_alloted_ext(((BigDecimal) row[2]).intValue());
			entity.setOccupant_extent(((BigDecimal) row[3]).intValue());
			entityDetails.add(entity);

		}
		
		return entityDetails;
	
	}

}
