package com.project.MatchingPro.service;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.MatchingPro.domain.user.User;
import com.project.MatchingPro.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

	private final UserRepository userRepository;

	// 회원가입
	// 어떤 타입인지 몰라서 <?>
	public ResponseEntity<?> join(User user) {
		user.setRole("USER");
		try {
			userRepository.save(user);
			return new ResponseEntity<String>("ok", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("회원가입에 실패하였습니다", HttpStatus.EXPECTATION_FAILED);
		}

	}

	// 아이디 체크
	public ResponseEntity<?> idCheck(String loginid) {
		int n = userRepository.countByLoginid(loginid);
		if (n == 0) {
			return new ResponseEntity<String>("ok", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("사용불가아이디", HttpStatus.OK);
		}
	}

	// 닉네임 체크
	public ResponseEntity<?> nicknameCheck(String nickname) {
		int n = userRepository.countByNickname(nickname);
		if (n == 0) {
			return new ResponseEntity<String>("ok", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("중복됨", HttpStatus.NOT_FOUND);
		}
	}

	// 유저상세보기
	public ResponseEntity<?> detail(int id) {
		User user = userRepository.findById(id).get();

		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	// 유저 삭제
	public ResponseEntity<?> delete(User user) {
		if (user.getTeams() != null) {//<11/06> 유저에 팀아이디 있으면 삭제 안되고 메세지 호출
			return new ResponseEntity<String>("팀탈퇴를 하고 오세요", HttpStatus.OK);
		} else {
			userRepository.deleteById(user.getId());
			return new ResponseEntity<String>("ok", HttpStatus.OK);
		}
	}

	// 팀 탈퇴
	@Transactional
	public ResponseEntity<?> teamLeave(User user) {
		if (user.getTeams().getOwner() == user) {
			return new ResponseEntity<String>("팀장을 위임해야 탈퇴가능합니다", HttpStatus.OK);
		} else {
			userRepository.removeTeam(user.getTeams().getId());
			return new ResponseEntity<String>("ok", HttpStatus.OK);
		}
	}

}
