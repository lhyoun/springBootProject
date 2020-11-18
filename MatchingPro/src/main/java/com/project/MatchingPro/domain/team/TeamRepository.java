package com.project.MatchingPro.domain.team;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Integer>{

	int countByName(String name);	//count = 결과 레코드수 반환

}
