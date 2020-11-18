package com.project.MatchingPro.domain.battle;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BattleRepository extends JpaRepository<Battle, Integer> {
	

	@Query(value = "select * from battle where requestTeam = :id or responseTeam=:id", nativeQuery = true)
	List<Battle> mfindAll(int id);
	
	@Query(value = "select * from battle where role = 2", nativeQuery = true)
	List<Battle> acceptAFindAll();
	
	@Query(value = "select * from battle where role = 1 and (requestTeam = :id or responseTeam=:id)", nativeQuery = true)
	List<Battle> waitingFindAll(int id);
	
	@Query(value = "select * from battle where role=2 and (requestTeam = :id or responseTeam = :id)", nativeQuery = true)
	List<Battle> acceptMFindAll(int id);

}
