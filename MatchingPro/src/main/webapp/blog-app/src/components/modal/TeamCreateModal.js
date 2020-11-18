//ok
import React, { useState } from 'react';
import { Container, Row, Col, Button, Form, Modal } from 'react-bootstrap';

let teamNameCheckFlag = false;
let emptyFlag = true;

const TeamCreateModal = () => {
	const [team, setTeam] = useState({
		name: "",
		explaintation: ""
	});

	const [show, setShow] = useState(false);
	const handleClose = () => setShow(false);
	const handleShow = () => setShow(true);

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
				} else alert("중복 팀 이름  입니다");
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
			explaintation: team.explaintation,
			image: "default.png"
		}
		//빈 값 없음이 이렇게 해도 되나?
		if (team.name === "" || team.explaintation === "") emptyFlag = false;

		if (emptyFlag && teamNameCheckFlag) {
			fetch("http://localhost:8000/user/create", {
				method: "POST",
				body: JSON.stringify(team2),
				headers: {
					'Content-Type': "application/json; charset=utf-8",
					'Authorization': localStorage.getItem("Authorization")
				}
			}).then(res => {
				if (res.text = "ok") {
					// 여기서 모달을 닫으면 되지?
					handleClose();
					return "팀 생성에  성공하였습니다.";
				}
				else return "팀 생성에  실패하였습니다.";
			}).then(res => alert(res));
		} else {
			if (!emptyFlag) alert("빈 값 있음");
			if (!teamNameCheckFlag) alert("팀 이름 중복확인을 해 주세요");
		}
	};

	return (
		<div>
			<Button variant="dark" onClick={handleShow}>팀만들기</Button>

			<Modal show={show} size={"lg"} onHide={handleClose}>
				<Modal.Header closeButton>
					<Modal.Title>팀만들기</Modal.Title>
				</Modal.Header>
				<Modal.Body>
					<Container>
						<Form>
							<Form.Group as={Col} controlId="formGridEmail">
								<Form.Label>팀이름</Form.Label>
								<Row>
									<Col md={10}>
										<Form.Control
											type="text"
											name="name"
											placeholder="팀이름을 입력하세요"
											onChange={inputHandle}
											value={team.name} /></Col>
									<Col md={2}><Button variant="dark" onClick={teamNameDuplicateCheck}>중복검사</Button>{' '}
									</Col>
								</Row>
							</Form.Group>

							<Form.Group as={Col} controlId="formGridPassword">
								<Form.Label>팀설명</Form.Label>
								<Form.Control
									type="text"
									name="explaintation"
									placeholder="팀설명을 입력하세요"
									onChange={inputHandle}
									value={team.explaintation} />
							</Form.Group>

							<Form.Group as={Col} controlId="formGridEmail">
								<Form.Label>지역</Form.Label>
								<Form.Control

									type="text"
									name="location"
									placeholder="지역을 입력하세요"
									onChange={inputHandle}
									value={team.location}
								/>
							</Form.Group>
						</Form>
					</Container>
				</Modal.Body>
				<Modal.Footer>
					<Button onClick={joinRequest}>생성하기</Button>

					<Button variant="secondary" onClick={handleClose}>Close</Button>
				</Modal.Footer>
			</Modal>
		</div>
	);
}

export default TeamCreateModal;

