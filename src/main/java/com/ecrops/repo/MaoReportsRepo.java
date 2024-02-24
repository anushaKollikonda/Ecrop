package com.ecrops.repo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpSession;

import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ecrops.entity.ActiveSeason;
import com.ecrops.entity.MaoAuthVaaVroekyc;
import com.ecrops.dto.*;


@Repository
public class MaoReportsRepo{
@PersistenceContext
private EntityManager entityManager;

@Autowired
ActiveSeasonRepository activeSeasonRepo;

public List<UnsurveyedDetailsPojo> getUnsurveyDetails() {
	 String sessionDcode="545";
     String sessionMcode="4905";
     String userId="TAH_4905";
    // ActiveSeason a = activeSeasonRepo.findByActiveAndCurrentSeason("A","C");
//String  season=a.getSeason();
//int cropYear=a.getCropyear();

//	 String user = session.getAttribute("USER").toString();
//	    String role = session.getAttribute("ROLE").toString();
//	    String sesdistcode = session.getAttribute("DCODE").toString();
//	    String activeYear = session.getAttribute("ACTIVEYEAR").toString();
//	    String activeSeason=session.getAttribute("ActiveSeason").toString();
	    String tab1 = "unsurveyed_unsettled_det";
	      tab1="ecrop2023."+tab1;


            System.out.println("tab1 :" + tab1);

     String   qry = "select cr_dist_code,w.wbdname,count(distinct cr_mand_code) as no_mandals,count(distinct cr_vcode) as no_villages, "
                + " count(1) filter (where cat_code='1') as Unset_est, sum(occupant_extent) filter  (where cat_code='1') as Unset_est_ext,"
                + " count(2) filter (where cat_code='2') as Unset_inam,sum(occupant_extent) filter  (where cat_code='2') as Unset_inam_ext,"
                + " count(3) filter (where cat_code='3') as Unsurveyed_est, sum(occupant_extent) filter  (where cat_code='3') as Unsurveyed_est_ext,"
                + " count(4) filter (where cat_code='4') as Unsurveyed_Inam,sum(occupant_extent) filter  (where cat_code='4') as Unsurveyed_Inam_ext"
                + " from " + tab1 + " a, wbvillage_mst w "
                + " where a.cr_vcode=w.wbvcode group by  cr_dist_code,w.wbdname order by cr_dist_code";
     System.out.println(qry+"hhh");
       List<UnsurveyedDetailsPojo> pojoList = new ArrayList<UnsurveyedDetailsPojo>();
       Query q= (Query) entityManager.createNativeQuery(qry);
       List<Object[]> l=  q.getResultList();
           if(l !=null && l.size()>0) {
        	   for(Object[] lm:l) {
        		   UnsurveyedDetailsPojo pojo= new UnsurveyedDetailsPojo();
        		      pojo.setDistrict(lm[1].toString());
        		      pojo.setMandalCount(Integer.parseInt(lm[2].toString()));
        		      pojo.setVillageCount(Integer.parseInt(lm[3].toString()));
        		      pojo.setUnsetEst(Integer.parseInt(lm[4].toString()));
        		      pojo.setUnsetInam(Integer.parseInt(lm[6].toString()));
        		      if(lm[5] !=null) {
        		      pojo.setUnsetEstExt(Integer.parseInt(lm[5].toString()));
        		      }else {
        		      pojo.setUnsetEstExt(0);
        		      }
        		      if(lm[7] !=null) {
            		      pojo.setUnsetEstExt(Integer.parseInt(lm[7].toString()));
            		      }else {
            		      pojo.setUnsetEstExt(0);
            		      }
        		      if(lm[11] !=null) {
            		      pojo.setUnsetEstExt(Integer.parseInt(lm[11].toString()));
            		      }else {
            		      pojo.setUnsetEstExt(0);
            		      }
        		   
        		      if(lm[9] !=null) {
            		      pojo.setUnsetEstExt(Integer.parseInt(lm[9].toString()));
            		      }else {
            		      pojo.setUnsetEstExt(0);
            		      }
        		   
        		     
        		 
        		   pojo.setUnsurveyedInam(Integer.parseInt(lm[10].toString()));
        		   //pojo.setUnsurveyedInamExt(Integer.parseInt(lm[11].toString()));
        		   pojo.setUnsurveyedEst(Integer.parseInt(lm[8].toString()));
        		   //pojo.setUnsurveyedEstExt(Integer.parseInt(lm[9].toString()));
        		   pojoList.add(pojo);
        		       
        		       
        	   }
        	   
           }
        
          
          
	
	
	return pojoList;


}


public List<UnsurvyedVillagePojo> getUnsurveyDetailsVilageWise() {
	 String sessionDcode="545";
    String sessionMcode="4905";
    String userId="TAH_4905";
   // ActiveSeason a = activeSeasonRepo.findByActiveAndCurrentSeason("A","C");
//String  season=a.getSeason();
//int cropYear=a.getCropyear();

//	 String user = session.getAttribute("USER").toString();
//	    String role = session.getAttribute("ROLE").toString();
//	    String sesdistcode = session.getAttribute("DCODE").toString();
//	    String activeYear = session.getAttribute("ACTIVEYEAR").toString();
//	    String activeSeason=session.getAttribute("ActiveSeason").toString();
    String tab1 = "unsurveyed_unsettled_det";
	      tab1="ecrop2023."+tab1;


           System.out.println("tab1 :" + tab1);

         String  qry = "select cr_dist_code,w.wbdname,cr_mand_code,w.wbmname,cr_vcode,w.wbvname,count(1) filter (where cat_code='1') as Unset_est,count(2) filter (where cat_code='2') as Unset_inam,"
                   + " count(3) filter (where cat_code='3') as Unsurveyed_est,count(4) filter (where cat_code='4') as Unsurveyed_Inam from " + tab1 + " a, wbvillage_mst w "
                   + " where a.cr_vcode=w.wbvcode  group by  cr_dist_code,w.wbdname,cr_mand_code,w.wbmname,cr_vcode,w.wbvname order by cr_dist_code,w.wbdname,cr_mand_code,w.wbmname,cr_vcode ";
                   
    System.out.println(qry+"hhh");
      List<UnsurvyedVillagePojo> pojoList = new ArrayList<UnsurvyedVillagePojo>();
      Query q= (Query) entityManager.createNativeQuery(qry);
      List<Object[]> l=  q.getResultList();
          if(l !=null && l.size()>0) {
       	   for(Object[] lm:l) {
       		UnsurvyedVillagePojo pojo= new UnsurvyedVillagePojo();
       		   System.out.println("hii"+Integer.parseInt(lm[6].toString()));
       		      pojo.setWbdname(lm[1].toString());
       		   pojo.setWbmname(lm[3].toString());
       		  pojo.setWbvname(lm[5].toString());
       		  
       		  pojo.setUnsetEst(Integer.parseInt(lm[6].toString()));
       		  pojo.setUnsetInam(Integer.parseInt(lm[7].toString()));
       		  pojo.setUnsurveyedEst(Integer.parseInt(lm[8].toString()));
       		  pojo.setUnsurveyedInam(Integer.parseInt(lm[9].toString()));
       	
     	//	      pojo.setMandalCount(Integer.parseInt(lm[2].toString()));
//       		      pojo.setVillageCount(Integer.parseInt(lm[3].toString()));
//       		      pojo.setUnsetEst(Integer.parseInt(lm[4].toString()));
//       		      pojo.setUnsetInam(Integer.parseInt(lm[6].toString()));
//       		      if(lm[5] !=null) {
//       		      pojo.setUnsetEstExt(Integer.parseInt(lm[5].toString()));
//       		      }else {
//       		      pojo.setUnsetEstExt(0);
//       		      }
//       		      if(lm[7] !=null) {
//           		      pojo.setUnsetEstExt(Integer.parseInt(lm[7].toString()));
//           		      }else {
//           		      pojo.setUnsetEstExt(0);
//           		      }
//       		      if(lm[11] !=null) {
//           		      pojo.setUnsetEstExt(Integer.parseInt(lm[11].toString()));
//           		      }else {
//           		      pojo.setUnsetEstExt(0);
//           		      }
//       		   
//       		      if(lm[9] !=null) {
//           		      pojo.setUnsetEstExt(Integer.parseInt(lm[9].toString()));
//           		      }else {
//           		      pojo.setUnsetEstExt(0);
//           		      }
//       		   
//       		     
//       		 
//       		   pojo.setUnsurveyedInam(Integer.parseInt(lm[10].toString()));
//       		   //pojo.setUnsurveyedInamExt(Integer.parseInt(lm[11].toString()));
//       		   pojo.setUnsurveyedEst(Integer.parseInt(lm[8].toString()));
       		   //pojo.setUnsurveyedEstExt(Integer.parseInt(lm[9].toString()));
       		      
       		   pojoList.add(pojo);
       		       
       		       
       	   }
       	   
          }
       
         
         
	
	
	return pojoList;


}




public List<UnsurvySetlePojo> unsurvey() {
	 String sessionDcode="545";
   String sessionMcode="4905";
   String userId="TAH_4905";

   String tab1 = "unsurveyed_unsettled_det";
	      tab1="ecrop2023."+tab1;


          System.out.println("tab1 :" + tab1);

        String  qry="select  wbdname,wbmname,wbvname,occupname,occupfname,cr_sno,cr_farmeruid,tot_extent,mobileno,occupant_extent,"+
        		  "  case when cat_code='3' then 'Unsettled Estate' when cat_code='4' then 'Unsettled Inam' when "+
        		  "  cat_code='1' then 'UnSurveyed Estate' when cat_code='2' then 'UnSurveyed Inam' end as settledsts,case when gender='M' then 'Male'"+
        		  "   when gender='F' then 'Female' when gender='T' then 'Transgender' end as gender"+
        		  "  from unsurveyed_unsettled_det a,wbvillage_mst b where "+
        		  "  cr_dist_code=b.wbdcode and cr_mand_code=b.wbmcode and cr_vcode=wbvcode and updatedby=? "+
        		  " ORDER By wbdname,wbmname,wbvname"; 
   
     List<UnsurvySetlePojo> pojoList = new ArrayList<UnsurvySetlePojo>();
     Query q= (Query) entityManager.createNativeQuery(qry);
       q.setParameter(1,"TAH_4874");
       System.out.print(qry+"qryy");
     List<Object[]> l=  q.getResultList();
         if(l !=null && l.size()>0) {
      	   for(Object[] lm:l) {
      		 UnsurvySetlePojo pojo= new UnsurvySetlePojo();
      		  
      		      pojo.setWbdname(lm[0].toString());
      		   pojo.setWbmname(lm[1].toString());
      		  pojo.setWbvname(lm[2].toString());
      		  pojo.setOccupname(lm[3].toString());
      		  pojo.setOccupfname(lm[4].toString());
      		  pojo.setCrSno(lm[5].toString());
      		  pojo.setCrFarmeruid(lm[6].toString());
      		  pojo.setTotExtent(lm[7].toString());
      		  pojo.setMobileno(lm[8].toString());
      		 pojo.setOccupantExtent(lm[9].toString());

      		  if(lm[10]!=null) {
      		  pojo.setSettledsts(lm[10].toString());
      		  }
      		  pojo.setGender(lm[11].toString());
      		  

      	
    
      		      
      		   pojoList.add(pojo);
      		       
      		       
      	   }
      	   
         
         }
        
        
	
	
	return pojoList;


}



}