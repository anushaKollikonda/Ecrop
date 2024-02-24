package com.ecrops.controller;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.ecrops.repo.ActiveSeasonRepository;
import com.ecrops.repo.FarmerBookingDetailsPartitions;
import com.ecrops.repo.FarmerBookingDetailsRepo;
import com.ecrops.repo.NormalAreasMwiseMaoRepo;
import com.ecrops.repo.RepLandDataDetailsRepo;
import com.ecrops.repo.RepLandDataDetailsRepo.Rep_VillLandDataDetails;



@Controller
public class MaoRibbonController {
	
	@Autowired RepLandDataDetailsRepo repLandDataDetailsRepo;	
	@Autowired FarmerBookingDetailsRepo farmerBookingDetailsRepo;
	
	@Autowired FarmerBookingDetailsPartitions  partion;
	
	@Autowired  ActiveSeasonRepository  activerepo;


	
	 
	@GetMapping("/normal")
	public String getNormalAreaList(HttpSession httpSession,Model model  ){
		String dcode=(String) httpSession.getAttribute("dcode");
		String mcode=(String) httpSession.getAttribute("mcode");
		 
	
	System.out.println("dcode==================="+dcode);
	System.out.println("mcode=============="+mcode);
	model.addAttribute("dcode","dcode");
	model.addAttribute("mcode","mcode");
	return "normalareasmao";
	}
	
	@GetMapping("/crop")
	public String getCropDetails(Model model ){
//	model.addAttribute("crp",crp);
	return "cropBookingDetailsMAO";
	}
	
	@GetMapping("/farmerdetails")
	public String getFarmerDetails(HttpSession httpSession){
	return "farmerbookingDetails";
	}
	
	
	
	
	@GetMapping("/rofr")
	public String roftbkdext( Model model) {
	return "rofr-extentbooked"	;
	}

	@GetMapping("/ekycmao")
	public String MaoEkyc(Model model) {
	return "maovaaVroEkyc"	;
	}
	@GetMapping("/rbksnomapping")
	public String rSnoMap() {
	return "RbkSurveyNoMapping"	;
	}
	@GetMapping("/allocatedSnoMap")
	public String allSnoMap(Model model) {
	return "Allocated_SurveyNo_Mapping"	;
	}
@GetMapping("/datasrc")
public String getDataSrc(Model model) {
	return "DataSourceWiseExt";
	
}

@GetMapping("/efishRofr")
public String getEfishRofr(Model model) {
	return "EfishRofrDetails";
	
}
@GetMapping("/farmdet")
public String getFarmerDet(Model model) {
return "FarmerDetails";

}
@GetMapping("/allcrops")
public String getSeasonAllCrops(Model model) {
return "SeasonCropBookedExtent";

}
@GetMapping("/efblkext")
public String getBlockedEfishExt(Model model) {
	return "BlockedEfishExtent";
}

@GetMapping("/objunobj")
public String getObjUnObj(Model model) {
	return "obj_unObj";
}
@GetMapping("/empL")
public String getRepEmpList(Model model) {
	return "EmployeeList";
}
@GetMapping("/repvaadet")
public String getRepVAADet(Model model) {
	return "VaaDetails";
}

@GetMapping("/devregdett")
public String getDevRegDet(Model model) {
	return "DeviceRegDetails";
}
@GetMapping("/cropins")
public String getCropInsGri(Model model) {
	return "CropInsGrivence";
}
@GetMapping("/supchkra")
public String getSupChkRecrdAlloted(Model model) {
	return "SuperCheckRecordsAlloted";
}

@GetMapping("/supchkappr")
public String getSupChkAppr(Model model) {
	return "SpuperChkAppr";
	
}
@GetMapping("/supchk")
public String getSupChR(Model model) {
	return "SpuperCheck";
	
}
@GetMapping("/supchkrejreport")
public String getSupChRej(Model model) {
	return "SuperChk_rejReport";
	
}
@GetMapping("/cropbmao")
public String getcrpMao(Model model) {
	return "CropBookingDetailsMaoIntf";
	
}
@GetMapping("/phyackvwise")
public String getPhyAckVwise(Model model) {
	return "PhyAckVwise";
	
}
@GetMapping("/phyackrbk")
public String getPhyAckRbk(Model model) {
	return "Rep_phy_ack_rbk";
	
}
@GetMapping("/dwnlddetintf")
public String getDwnldDetIntf(Model model) {
	return "Rep_DownloadedDetailsIntf";
	
}
@GetMapping("/landdatadet")
public String getLandData() {
	
	
	 return "RepLandDataDetails";
	
}//repLandDataDetailsRepo

@RequestMapping("/villLandData")
public String getVillLandData(HttpServletRequest httpServletRequest,Model model) {
	System.out.println("villLandData");
	 String dcode=httpServletRequest.getParameter("dcode");
	 String mcode=httpServletRequest.getParameter("mcode");
	
	 List<Rep_VillLandDataDetails> list =repLandDataDetailsRepo.getVillData(Integer.parseInt(dcode), Integer.parseInt(mcode));
	 model.addAttribute("data", list);
	return "Rep_VillLandDataDetails";
}

 @GetMapping("/nonwebview")
 public String getNonwebV(Model model) {
	return "nonWebView";
	}
 @GetMapping("/crpwrbkext")
 public String getcrpWiseRbkExt(Model model) {
	return "CropwiseExtBooked_RBKwise";
	}
 @GetMapping("/authmaoekycvaavaro")
 public String getAuthMaoEkyc() {
	return "Auth_MAO_vaavroekyc";
	}
 @GetMapping("/supupdsts")
 public String getSupUpdsts() {
	return "Superchekupdstatus";
	}
 @GetMapping("/maosocialauditR")
 public String getMaoSocialAuditCorrectionR() {
	return "MaoSocialAuditcorrection";
	}
 
}
