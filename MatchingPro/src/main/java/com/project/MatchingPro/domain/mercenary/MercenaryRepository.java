package com.project.MatchingPro.domain.mercenary;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MercenaryRepository extends JpaRepository<Mercenary, Integer> {

	int findByTeam_id(int teamid);
	
	@Query(value = "select * from mercenary where team_id= :teamid", nativeQuery = true)//nativeQuery: 이쿼리를 사용하겠다.
	List<Mercenary> mercenaryList(int teamid);
}
