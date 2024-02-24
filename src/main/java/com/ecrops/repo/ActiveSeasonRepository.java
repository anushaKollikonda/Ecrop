package com.ecrops.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecrops.entity.ActiveSeason;

@Repository
public interface ActiveSeasonRepository extends JpaRepository<ActiveSeason, String> {

	List<ActiveSeason> findByActiveAndCurrentSeason(String active, String CurrentSeason);

}
