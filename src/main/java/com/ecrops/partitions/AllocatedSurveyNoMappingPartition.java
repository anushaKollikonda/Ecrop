package com.ecrops.partitions;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.ecrops.entity.AllocatedSurveyNoMapping;


@Repository
@Transactional
public class AllocatedSurveyNoMappingPartition {
	
	@PersistenceContext
	private EntityManager entityManager;

	public List<AllocatedSurveyNoMapping> allocatedSnoDetails(String wbdcode, String cropyear, String mcode, String userid,String rbkid) {
		String[] season = cropyear.split("@");
		String seasonType = season[0];
		Integer seasonYear = Integer.parseInt(season[1]);
		
		String part_key = "";
		if (Integer.parseInt(wbdcode)> 9) {
			part_key = seasonType + wbdcode + seasonYear;
		} else {
			part_key = seasonType + "0" + wbdcode + seasonYear;
		}
		String tableName = "ecrop" + seasonYear + "." + "rbk_surveyno_mapping_" + part_key;

		System.out.println("tableName---------------->" + tableName);

		String Sql = " select lgdvname,data_src,occup_extent,kh_no,cr_sno,tot_extent from "+tableName+" \r\n"
				+ "a, wbvillage_mst b where a.vcode=wbvcode and a.mcode=? and rbkcode=?  \r\n"
				+ "order by lgdvname,cr_sno,kh_no ";

		Query insertQuery = (Query) entityManager.createNativeQuery(Sql);
		insertQuery.setParameter(1, Integer.parseInt(mcode));
		insertQuery.setParameter(2, Integer.parseInt(rbkid));
		//insertQuery.setParameter(3,  seasonYear);
		//insertQuery.setParameter(4, seasonType);
		
		System.out.println("insertQuery=>"+insertQuery);
		List<Object[]> detailsEntities1 = insertQuery.getResultList();
		//System.out.println("detailsEntities1=>"+detailsEntities1.size());
		//System.out.println("detailsEntities1=>"+detailsEntities1.toString());
		List<AllocatedSurveyNoMapping> detailsEntities = new ArrayList<AllocatedSurveyNoMapping>();
		

		for (Object[] row : detailsEntities1) {

			AllocatedSurveyNoMapping entity = new AllocatedSurveyNoMapping();
			entity.setLgdvname((String) row[0]);
			entity.setData_src(String.valueOf(row[1]));
			
			String df = (String)row[2].toString();
			//System.out.println("baby=>"+df);
	        BigDecimal bigDecimalValue = new BigDecimal(df);
	        bigDecimalValue = bigDecimalValue.stripTrailingZeros();
	        String resultString = bigDecimalValue.toString();
	       // System.out.println("Trimmed String: " + resultString);
			entity.setOccup_extent((long)Double.parseDouble(resultString));
			
			entity.setKh_no(((BigDecimal) row[3]).intValue());
			entity.setCr_sno((String) row[4]);
			
			String df2 = (String)row[5].toString();
			//System.out.println("baby2=>"+df2);
	        BigDecimal bigDecimalValue2 = new BigDecimal(df);
	        bigDecimalValue2 = bigDecimalValue2.stripTrailingZeros();
	        String resultString2 = bigDecimalValue2.toString();
	       // System.out.println("Trimmed String2: " + resultString2);
			entity.setTot_extent((long)Double.parseDouble(resultString2));
			
			detailsEntities.add(entity);

		}
		
		return detailsEntities;

	}
	

}
