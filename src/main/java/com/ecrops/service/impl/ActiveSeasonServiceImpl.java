package com.ecrops.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecrops.entity.ActiveSeason;
import com.ecrops.repo.ActiveSeasonRepository;
import com.ecrops.service.ActiveSeasonService;

@Service
public class ActiveSeasonServiceImpl implements ActiveSeasonService{

	@Autowired
	private ActiveSeasonRepository repo;

	public List<ActiveSeason> listAll() {

		return repo.findByActiveAndCurrentSeason("A", "C");
	}

}
