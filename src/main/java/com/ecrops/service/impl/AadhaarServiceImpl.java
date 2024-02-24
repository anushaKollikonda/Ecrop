package com.ecrops.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecrops.entity.Aadhaar;
import com.ecrops.repo.AadhaarRepository;
import com.ecrops.service.AadhaarService;

@Service
public class AadhaarServiceImpl implements AadhaarService{
	
	@Autowired
	private AadhaarRepository aadhaarrepo;

	@Override
	public List<Aadhaar> getAllDetails() {
		// TODO Auto-generated method stub
		return aadhaarrepo.findAll();
	}

}
