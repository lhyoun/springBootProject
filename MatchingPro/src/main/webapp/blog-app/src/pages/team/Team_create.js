import React, { useState } from 'react';
import { Container, Button } from 'react-bootstrap';

let teamNameCheckFlag = false;
let emptyFlag = true;

const Team_create = () => {

	const [team, setTeam] = useState({
		name: "",
		explaintation: ""
	});

	const teamNameDuplicateCheck = (e) => {
		e.preventDefault();

		fetch(`http://localhost:8000/check/${team.name}`, {
			method: "GET",
			headers: {
			}
		}).then(res => res.text())
			.then(res => {
				if (res === "ok") {
					teamNameCheckFlag = true;
					alert("사용 가능한 팀 이름  입니다");
				} else {
					alert("중복 팀 이름  입니다");
				}
			});
	}

	const inputHandle = (e) => {
		setTeam({
			...team,
			[e.target.name]: e.target.value,
		});
	};

	const joinRequest = (e) => {
		e.preventDefault();
		let team2 = {
			name: team.name,
			explaintation: team.explaintation
		}
		//빈 값 없음이 이렇게 해도 되나?
		if (team.name === "" || team.explaintation === "") {
			emptyFlag = false;
		}

		if (emptyFlag && teamNameCheckFlag) {
			fetch("http://localhost:8000/user/create", {
				method: "POST",
				body: JSON.stringify(team2),
				headers: {
					'Content-Type': "application/json; charset=utf-8",
					'Authorization': localStorage.getItem("Authorization")
				}
				}).then(res => {
				if (res.text = "ok") return "팀 생성에  성공하였습니다.";
				else return "팀 생성에  실패하였습니다.";
			}).then(res => {
				alert(res); 
			});
		} else {
			if (!emptyFlag) { 
				alert("빈 값 있음");
			}
			if (!teamNameCheckFlag) {
				alert("팀 이름 중복확인을 해 주세요");
			}
		}
	};

	return (
					

		<Container>
			<br/><br/><br/><br/><br/>
			<input
				type="text"
				name="name"
				placeholder="name"
				onChange={inputHandle}
				value={team.name}
			/>
			<Button variant="success" onClick={teamNameDuplicateCheck}>팀 이름  중복검사</Button>{' '}
			<br />
			<input
				type="text"
				name="explaintation"
				placeholder="explaintation"
				onChange={inputHandle}
				value={team.explaintation}
			/>
			<br />
						<input
				type="text"
				name="location"
				placeholder="type location here"
				onChange={inputHandle}
				value={team.location}
			/>
			<Button onClick={joinRequest}>생성하기</Button>
		</Container>
	);
};

export default Team_create;