package com.ecrops.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecrops.dto.UnsurveyedDetailsPojo;
import com.ecrops.dto.UnsurvySetlePojo;
import com.ecrops.dto.UnsurvyedVillagePojo;
import com.ecrops.entity.ActiveSeason;
import com.ecrops.repo.ActiveSeasonRepository;
import com.ecrops.repo.MaoReportsRepo;

@Controller
public class MaoReportsController {
	
	@Autowired
	ActiveSeasonRepository activeSeasonRepo;
	
	@Autowired
	MaoReportsRepo maoReportsRepo;

	
	@GetMapping("/unServeyedReports")
	public String getUnSurveyedUnsettledReport(Model model  ){
		
		   String dcode="545";
		     String mcode="4905";
		     String userId="TAH_4905";
//		     ActiveSeason a = activeSeasonRepo.findByActiveAndCurrentSeason("A","C");
//		String  season=a.getSeason();
//		int cropYear=a.getCropyear();
	
		List<UnsurveyedDetailsPojo> l =maoReportsRepo.getUnsurveyDetails();
		//model.addAttribute("data",l);
		 
	return "unServeyedReport";
	}
	
	
	//1st report
	
	@GetMapping("/unServeyedReport")
	public List<UnsurveyedDetailsPojo> getUnSurveyedUnsettledRepor(Model model  ){
		
		   String dcode="545";
		     String mcode="4905";
		     String userId="TAH_4905";
		    List<ActiveSeason> a = activeSeasonRepo.findByActiveAndCurrentSeason("A","C");
//		String  season=a.getSeason();
//		int cropYear=a.getCropyear();
//	
		List<UnsurveyedDetailsPojo> l =maoReportsRepo.getUnsurveyDetails();
	model.addAttribute("data",l);
		 
	return l;
	}
	
	
	//3 rd report
	@GetMapping("/unsrvyedUnsettldReportVillageWise")
	public String getunsrvyedUnsettldReportVillageWise(HttpSession httpSession,Model model  ){
		
		 
		List<UnsurvyedVillagePojo> l =maoReportsRepo.getUnsurveyDetailsVilageWise();
		model.addAttribute("data",l);
		 
	return "unsurveyedUnSettledReportsVillageWise";
	}
	
	
	//first report
	@GetMapping("/unsrvy")
	public String unsrvy(HttpSession httpSession,Model model  ){
	List<UnsurvySetlePojo> l =(List<UnsurvySetlePojo>) maoReportsRepo.unsurvey();
		model.addAttribute("data",l);
	return "unsrvy";
	}
}
