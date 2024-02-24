package com.ecrops.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecrops.entity.WbMaster;
import com.ecrops.repo.WbMasterRepo;
import com.ecrops.service.WbMasterService;

@Service
public class WbMasterServiceImpl implements WbMasterService {

	@Autowired
	WbMasterRepo wbMasterRepo;

	@Override
	public WbMaster getWbMasterDetailsForVillage(Integer wbvcode, Integer wbMcode, Integer wbDcode) {
		return wbMasterRepo.getWbMasterDetailsForVillage(wbvcode, wbMcode, wbDcode);
	}

	@Override
	public WbMaster getWbMasterDetailsForMandal(Integer wbMcode, Integer wbDcode) {
		return wbMasterRepo.getWbMasterDetailsForMandal(wbMcode, wbDcode);
	}

}
