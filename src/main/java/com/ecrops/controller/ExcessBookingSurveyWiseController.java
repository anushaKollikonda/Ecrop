package com.ecrops.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import com.ecrops.entity.ExcessBookingKhataProjections;
import com.ecrops.entity.ExcessBookingSurveyProjections;
import com.ecrops.entity.ExcessBookingSurveyWise;
import com.ecrops.entity.Perinnial;
import com.ecrops.repo.DepartmentWiseProjections;
import com.ecrops.repo.DepartmentWiseRepository;
import com.ecrops.repo.ExcessBookingSurveyRepository;
import com.ecrops.repo.ExcessBookingkhataRepository;
@Controller
public class ExcessBookingSurveyWiseController {
	
	@Autowired
	private ExcessBookingSurveyRepository Repo;
	
	@Autowired
	private ExcessBookingkhataRepository Krepo;
	
	@Autowired
	private DepartmentWiseRepository drepo;
	
	
	
	@GetMapping("/bookingsurvey")
	public String getList(Model model) {
		List<ExcessBookingSurveyProjections> list=Repo.getList();
		model.addAttribute("list", list);
		return "ExcessBookingSurvey";
		
	}
	

	@GetMapping("/bookingkhata")
	public String getList1(Model model) {
		List<ExcessBookingKhataProjections> list=Krepo.getList();
		model.addAttribute("list",list);
		return "ExcessbookingKhata";
	}
	
	@GetMapping("/deptwise")
	public String getList11(Model model) {
		List<DepartmentWiseProjections> dept=drepo.getList();
		model.addAttribute("dept",dept);
		return "DeptWise";
	}
	
}
