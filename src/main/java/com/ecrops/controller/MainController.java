package com.ecrops.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.ecrops.config.Encrypt;
import com.ecrops.dto.AuthenticationRequest;
import com.ecrops.entity.AppUser;
import com.ecrops.entity.UserRegEntity;
import com.ecrops.entity.WbMaster;
import com.ecrops.service.AuthenticationService;
import com.ecrops.service.UserRegService;
import com.ecrops.service.WbMasterService;

import antlr.collections.List;

@Controller
public class MainController {

	@Autowired
	private AuthenticationService authenticationService;

	@Autowired
	private Encrypt encrypt;

	@Autowired
	UserRegService userRegService;

	@Autowired
	WbMasterService wbMasterService;

	AppUser user;

	@GetMapping("/home")
	public String homePage(@ModelAttribute AuthenticationRequest authenticationRequest, Model model,
			HttpServletRequest request) {

		return "home";
	}

	@GetMapping("/login")
	public String loginPage(@ModelAttribute AuthenticationRequest authenticationRequest, Model model,
			HttpServletRequest request) {

		return "login";
	}

	@PostMapping("/login-auth")
	public String loginUser(@ModelAttribute AuthenticationRequest authenticationRequest, Model model,
			HttpServletRequest request, HttpSession session) {
		try {
			final UserDetails userDetails = authenticationService
					.loadUserByUsername(authenticationRequest.getUsername());
			String encpassword = authenticationRequest.getPassword();
			String password = encrypt.setSha256Password(userDetails.getPassword());
			if (encpassword.equals(password)) {
				UserRegEntity userRegEntity = userRegService.getSessionValues(authenticationRequest.getUsername());
				System.out.println(userRegEntity.getWbDcode());
				setSessionValues(session, userRegEntity);
				
				return "home";
			} else {
				model.addAttribute("msg", "Invalid Credentials");
				return "login";
			}

		} catch (BadCredentialsException e) {
			e.printStackTrace();
		}

		return "home";
	}

	public void setSessionValues(HttpSession httpSession, UserRegEntity userRegEntity) {
		String userType = userRegEntity.getType_user();
//		List<WbMaster>  wbMaster = null;
		WbMaster wbMaster = null;

		if (userType != null && "17".equalsIgnoreCase(userType)) {
			
		} else if (userType != null && "5".equalsIgnoreCase(userType)) {
			System.out.println(userRegEntity.getWbDcode());
			System.out.println(userRegEntity.getWbMcode());
			System.out.println(userRegEntity.getWbvcode());
				wbMaster = wbMasterService.getWbMasterDetailsForMandal(userRegEntity.getWbMcode(),
					userRegEntity.getWbDcode());
			System.out.println("wbMaster====>"+wbMaster.toString());
			
			httpSession.setAttribute("dcode", userRegEntity.getDistCode());
			httpSession.setAttribute("mcode", userRegEntity.getMandCode());
			httpSession.setAttribute("wbdcode", userRegEntity.getWbDcode());
			httpSession.setAttribute("wbmcode", userRegEntity.getWbMcode());
			//httpSession.setAttribute("wbvcode", userRegEntity.getWbvcode());
			httpSession.setAttribute("wbedname", wbMaster.getWbedname());
			httpSession.setAttribute("wbemname", wbMaster.getWbemname());
			httpSession.setAttribute("wbevname", wbMaster.getWbevname());
			System.out.println("mycode is"+userRegEntity.getDistCode());
			
			
			
			
			//httpSession.setAttribute("mcodee", wbMaster.getMcode());
			
		} else if (userType != null && "25".equalsIgnoreCase(userType)) {
			wbMaster = wbMasterService.getWbMasterDetailsForVillage(userRegEntity.getWbvcode(),
					userRegEntity.getWbMcode(), userRegEntity.getWbDcode());
			httpSession.setAttribute("wbdcode", userRegEntity.getWbDcode());
			httpSession.setAttribute("mcode", userRegEntity.getMandCode());
			httpSession.setAttribute("wbmcode", userRegEntity.getWbMcode());
			httpSession.setAttribute("wbvcode", userRegEntity.getWbvcode());
			System.out.println(userRegEntity.getWbDcode());
			System.out.println(userRegEntity.getWbMcode());
			System.out.println(userRegEntity.getWbvcode());
			httpSession.setAttribute("wbedname", wbMaster.getWbedname());
			httpSession.setAttribute("wbemname", wbMaster.getWbemname());
			httpSession.setAttribute("wbevname", wbMaster.getWbevname());
			httpSession.setAttribute("dcode", userRegEntity.getDistCode());
			
			
		}
else if (userType != null && "2".equalsIgnoreCase(userType)) {
			
			wbMaster = wbMasterService.getWbMasterDetailsForMandal(userRegEntity.getWbMcode(),
					userRegEntity.getWbDcode());
			httpSession.setAttribute("dcode", userRegEntity.getDistCode());
			httpSession.setAttribute("mcode", userRegEntity.getMandCode());
     String dcode="545";
     String mcode="4905";
			
		}
		

		httpSession.setAttribute("role", userRegEntity.getType_user());
		httpSession.setAttribute("name", userRegEntity.getName());
		httpSession.setAttribute("userid", userRegEntity.getUserid());
		httpSession.setAttribute("typename", userRegEntity.getUserTypesEntity().getTypeName());
		httpSession.setAttribute("userType", userRegEntity.getUserTypesEntity().getUserType());

	}

}
