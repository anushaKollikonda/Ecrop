package com.ecrops.service;

import com.ecrops.entity.WbMaster;

public interface WbMasterService {

	WbMaster getWbMasterDetailsForVillage(Integer wbvcode, Integer wbmcode, Integer wbdcode);

	WbMaster getWbMasterDetailsForMandal(Integer wbMcode, Integer wbDcode);

}
