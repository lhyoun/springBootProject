package com.project.MatchingPro.service;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.MatchingPro.domain.mercenary.Mercenary;
import com.project.MatchingPro.domain.mercenary.MercenaryRepository;
import com.project.MatchingPro.domain.team.TeamRepository;
import com.project.MatchingPro.domain.user.User;
import com.project.MatchingPro.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MercenaryService {
	
	private final MercenaryRepository mercenaryRepository;
	private final TeamRepository teamRepository;
	private final UserRepository userRepository;
	
	public ResponseEntity<?> recruitment(Mercenary mercenary,User user){
		
		mercenary.setRole(1);//자리있을떄 1 자리없을때 0
		mercenary.setTeam(teamRepository.findById(user.getTeams().getId()).get());
		mercenaryRepository.save(mercenary);
		return new ResponseEntity<String>("ok",HttpStatus.OK);
	}
	
	//용병 지원(지원하기 누르면 이거 실행)
	@Transactional
	public ResponseEntity<?> mercenaryApply(User user, int mercid){
		Mercenary mercenatyEntity = mercenaryRepository.findById(mercid).get();
		if(mercenatyEntity.getUser1()==null) {
			mercenatyEntity.setUser1(userRepository.findById(user.getId()).get());
			return new ResponseEntity<String>("ok",HttpStatus.OK);
		}else if(mercenatyEntity.getUser2()==null) {
			mercenatyEntity.setUser2(userRepository.findById(user.getId()).get());
			return new ResponseEntity<String>("ok",HttpStatus.OK);
		}else if(mercenatyEntity.getUser3()==null) {
			mercenatyEntity.setUser3(userRepository.findById(user.getId()).get());
			mercenatyEntity.setRole(0);
			return new ResponseEntity<String>("ok",HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("모집인원가득참",HttpStatus.OK);
		}
	}
}
