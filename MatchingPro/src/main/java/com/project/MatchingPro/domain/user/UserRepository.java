package com.project.MatchingPro.domain.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer>{

	User findByLoginidAndPassword(String loginid, String password);
	
	int countByLoginid(String loginid);	//count = 결과 레코드수 반환

	int countByNickname(String nickname);
	
	int countByTeams_id(int teamid);
	
	@Query(value = "select * from user where teams_id is null", nativeQuery = true)
	List<User>  mfindAll();
	
	User  findByNickname(String name);
	
	@Modifying
	@Query(value = "update user set teams_id = null where teams_id = id", nativeQuery = true)
	void removeTeam(int id);
}
