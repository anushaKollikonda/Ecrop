package com.ecrops.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MaoReportsController {

	
	@GetMapping("/unServeyedReport")
	public String getUnSurveyedUnsettledReport(HttpSession httpSession,Model model  ){
		System.out.println( "hii"+httpSession.getAttribute("dcode"));
		System.out.println( "hii"+httpSession.getAttribute("mcode"));
		 
		
		 
	return "unServeyedReport";
	}
	
	@GetMapping("/unsrvyedUnsettldReportVillageWise")
	public String getunsrvyedUnsettldReportVillageWise(HttpSession httpSession,Model model  ){
		System.out.println( "hii"+httpSession.getAttribute("dcode"));
		System.out.println( "hii"+httpSession.getAttribute("mcode"));
		 
		
		 
	return "unsurveyedUnSettledReportsVillageWise";
	}
}
