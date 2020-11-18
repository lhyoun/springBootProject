package com.project.MatchingPro.domain.party;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


public interface PartyRepository extends JpaRepository<Party, Integer>{
	@Query(value = "select * from party where team_id = :id and roleNumber=0", nativeQuery = true)
	List<Party> mFindByTeamid(int id);
	
	int countByUser_idAndTeam_id(int id1, int id2);
	
	@Query(value = "select * from party where user_id = :user_id and roleNumber=1", nativeQuery = true)
	List<Party> findUser_idAll(int user_id);
	
	@Modifying
	@Query(value = "delete from party where user_id = :user_id", nativeQuery = true)
	void deleteParty(int user_id);
	
}
