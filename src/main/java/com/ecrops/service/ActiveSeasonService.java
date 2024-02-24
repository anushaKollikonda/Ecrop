package com.ecrops.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecrops.entity.ActiveSeason;

@Service
public interface ActiveSeasonService {

	public List<ActiveSeason> listAll();
	
}
