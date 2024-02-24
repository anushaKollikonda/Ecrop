package com.ecrops.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecrops.entity.Cultivator;

@Service
public interface CultivatorService {

	public List<Cultivator> listAll();

	public List<Cultivator> getCultivatorsByKathaNo(Cultivator cultivator);

	public int updateOwnerOrEnjoerDetails(Cultivator cultivator);

	public Cultivator saveCultivatorsData(Cultivator cultivator);

	public int updateCultivatorDetails(Cultivator cultivator);

	public void deleteCultivatorDetails(Cultivator cultivator);

	public Float getAnubhavadarExtent(Cultivator cultivator);

	public Float getTotalOccupantExtent(Cultivator cultivator);

}
