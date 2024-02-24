package com.ecrops.service.impl;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecrops.config.CustomPasswordEncoder;
import com.ecrops.dto.UserRegistrationDto;
import com.ecrops.entity.AppUser;
import com.ecrops.entity.Roles;
import com.ecrops.repo.AppUserRepo;
import com.ecrops.service.UserService;


@Service
public class UserRegServiceImpl implements UserService{
	
	private AppUserRepo appUserRepo;
	
	@Autowired 
	private CustomPasswordEncoder passwordEncoder;
	
	public UserRegServiceImpl(AppUserRepo appUserRepo) {
		super();
		this.appUserRepo = appUserRepo;
	}

	@Override
	public AppUser save(UserRegistrationDto userRegistrationDto) {
		
		AppUser user = new AppUser(
				userRegistrationDto.getUserid(),
				passwordEncoder.encode(userRegistrationDto.getEncpassword()),
				
				userRegistrationDto.getDcode(),
				userRegistrationDto.getMcode(),
				
				userRegistrationDto.getStatus(),
				
				userRegistrationDto.getType_user(),
				
				
				
				Arrays.asList(new Roles("ROLE_USER"))
				);
		return appUserRepo.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("UserServiceImpl=> loadUserByUsername=>username=>"+username);
		AppUser user = appUserRepo.findByUserid(username);
		System.out.println("user----------------------->"+user);
		if(user == null) {
			throw new UsernameNotFoundException("Invalid Email and Password "+username);
		}
		System.out.println("password------------>"+user.getEncpassword());
		System.out.println();    
		
		return new User(user.getUserid(), user.getEncpassword(), mapRolesToAuthorities(user.getRoles()));
	}
	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Roles> roles){
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}

}
