package com.project.MatchingPro.domain.score;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ScoreRepository extends JpaRepository<Score, Integer> {

	 @Query(value = " select team_id, total, id, draw,lose,win, (SELECT count(*)+1 from score where total > t.total ) As rank from score As t order by rank", nativeQuery = true)//nativeQuery: 이쿼리를 사용하겠다.
	   List<Score> rankAll();

}
