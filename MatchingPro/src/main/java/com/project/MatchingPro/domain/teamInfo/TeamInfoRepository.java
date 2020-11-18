package com.project.MatchingPro.domain.teamInfo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TeamInfoRepository extends JpaRepository<TeamInfo, Integer> {
 
	
	@Query(value = "select * from teaminfo where team_id=:id and id = (select max(id) from teaminfo where team_id=:id)", nativeQuery = true)
	//@Query(value = "select * from teaminfo where team_id = :id", nativeQuery = true)
	TeamInfo mFindByTeamid(int id);
}
