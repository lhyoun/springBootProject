// ok
import React, { useState } from 'react';
import { Container, Row, Col, Button, Form, Modal } from 'react-bootstrap';

const JoinModal = () => {
	const [show, setShow] = useState(false);

	const handleClose = () => setShow(false);
	const handleShow = () => setShow(true);

	let emptyFlag = true;				// 빈 칸 확인 플레그 true : 가입가능
	let idCheckFlag = false;			// id 중복확인 플레그 true : 사용가능
	let nicknameCheckFlag = false; 		// nickname 중복확인 플레그 true : 사용가능


	const openTextFile = (e) => {
		e.preventDefault();
		var input = document.createElement("input");
		input.type = "file";
		input.accept = "image/*";
		input.id = "uploadInput";
		input.click();
		input.onchange = function (event) {
			processFile(event.target.files[0]);
		};
	}

	function processFile(file) {
		var reader = new FileReader();
		reader.onload = function () {
			var result = reader.result;
			console.log(result);
			setUser({
				...user,
				"image": result
			});
		};
		reader.readAsDataURL(file);
	}

	const [user, setUser] = useState({
		loginid: "",
		username: "",
		password: "",
		nickname: "",
		email: "",
		phone: "",
		location: "",
		image: "",
		position: ""
	});

	const inputHandle = (e) => {
		setUser({
			...user,
			[e.target.name]: e.target.value,
		});
	};

	const idDuplicateCheck = (e) => {
		e.preventDefault();
		fetch(`http://localhost:8000/idCheck/${user.loginid}`, {
			method: "GET",
			headers: {
			}
		}).then(res => res.text())
			.then(res => {
				if (res === "ok") {
					idCheckFlag = true;
					alert("사용 가능한 아이디  입니다");
				} else {
					alert("중복 아이디 입니다");
				}
			});
	}

	const nicknameDuplicateCheck = (e) => {
		e.preventDefault();
		fetch(`http://localhost:8000/nicknameCheck/${user.nickname}`, {
			method: "GET",
		}).then(res => res.text())
			.then(res => {
				if (res === "ok") {
					nicknameCheckFlag = true;
					alert("사용 가능한 닉네임  입니다");
				} else {
					alert("중복  닉네임  입니다");
				}
			});
	}

	const joinRequest = (e) => {
		e.preventDefault();
		let person = {
			loginid: user.loginid,
			username: user.username,
			password: user.password,
			nickname: user.nickname,
			email: user.email,
			phone: user.phone,
			location: user.location,
			image: "default.png",
			position: user.position
		}
		const keys = Object.keys(person) // ['name', 'weight', 'price', 'isFresh']
		for (let i = 0; i < keys.length; i++) {
			const key = keys[i] // 각각의 키
			const value = person[key] // 각각의 키에 해당하는 각각의 값

			if (value === "") {
				console.log("joinForm:: empty key: ", key);
				console.log("joinForm:: empty value: ", value);
				emptyFlag = false;	// 빈 값 들어오면 가입 불가능
			} else {
				emptyFlag = true;
			}
		}
		if (emptyFlag) {
			fetch("http://localhost:8000/join", {
				method: "POST",
				body: JSON.stringify(person),
				headers: {
					'Content-Type': "application/json; charset=utf-8"
				}
			}).then(res => {
				if (res.text = "ok") return "회원가입에 성공하였습니다.";
				else return "회원가입 실패하였습니다.";
			}).then(res => {
				alert(res);   // 로그인의 결과
				handleClose();
			});
		} else {
			if (!emptyFlag) alert("빈 값 있음");
			if (!idCheckFlag) alert("id중복확인을 해 주세요");
			if (!nicknameCheckFlag) alert("nickname 중복확인을 해 주세요");
		}
	}

	return (
		<div>
			<Button variant="dark" onClick={handleShow}>회원가입</Button>

			<Modal show={show} size={"lg"} onHide={handleClose}>
				<Modal.Header closeButton>
					<Modal.Title>회원가입</Modal.Title>
				</Modal.Header>
				<Modal.Body>
					<Container>
						<Form>
							<Form.Group as={Col} controlId="formGridEmail">
								<Form.Label>아이디</Form.Label>
								<Row>
									<Col md={10}>
										<Form.Control
											type="text"
											name="loginid"
											placeholder="아이디"
											onChange={inputHandle}
											value={user.loginid} /></Col>
									<Col md={2}><Button variant="dark" onClick={idDuplicateCheck}>중복검사</Button>{' '}
									</Col>
								</Row>
							</Form.Group>

							<Form.Group as={Col} controlId="formGridPassword">
								<Form.Label>비밀번호</Form.Label>
								<Form.Control
									type="password"
									name="password"
									placeholder="비밀번호"
									onChange={inputHandle}
									value={user.password} />
							</Form.Group>

							<Form.Group as={Col} controlId="formGridEmail">
								<Form.Label>이름</Form.Label>
								<Form.Control
									type="text"
									name="username"
									placeholder="이름"
									onChange={inputHandle}
									value={user.username} />
							</Form.Group>

							<Form.Group as={Col} controlId="formGridEmail">
								<Form.Label>닉네임</Form.Label>
								<Row>
									<Col md={10}>
										<Form.Control
											type="text"
											name="nickname"
											placeholder="닉네임"
											onChange={inputHandle}
											value={user.nickname} />
									</Col>
									<Col md={2}>
										<Button variant="dark" onClick={nicknameDuplicateCheck}>중복검사</Button>{' '}
									</Col>
								</Row>
							</Form.Group>

							<Form.Group as={Col} controlId="formGridEmail">
								<Form.Label>이메일</Form.Label>
								<Form.Control
									type="email"
									name="email"
									placeholder="이메일"
									onChange={inputHandle}
									value={user.email} />
							</Form.Group>

							<Form.Group as={Col} controlId="formGridEmail">
								<Form.Label>휴대폰 번호 </Form.Label>
								<Form.Control
									type="tel"
									name="phone"
									placeholder="휴대폰번호"
									onChange={inputHandle}
									value={user.tel} />
							</Form.Group>

							<Form.Group as={Col} controlId="formGridEmail">
								<Form.Label>지역 </Form.Label>
								<Form.Control
									type="text"
									name="location"
									placeholder="지역을 입력하세요"
									onChange={inputHandle}
									value={user.location} />
							</Form.Group>

							<Form.Group as={Col} controlId="formGridEmail">
								<Form.Label>포지션 </Form.Label>
								<Form.Control
									type="text"
									name="position"
									placeholder="포지션을 입력하세요"
									onChange={inputHandle}
									value={user.position} />
							</Form.Group>

							<Form.Group as={Col} controlId="formGridEmail">
								<Button variant="dark" name="name" onClick={openTextFile}>Select Image</Button>{' '}
							</Form.Group>

							<Form.Group as={Col} controlId="formGridEmail">
								<hr />
								<Button variant="info" onClick={joinRequest}>회원가입</Button>{' '}
							</Form.Group>
						</Form>
					</Container>
				</Modal.Body>
				<Modal.Footer>
					<Button variant="secondary" onClick={handleClose}>Close</Button>
				</Modal.Footer>
			</Modal>
		</div>
	)
}

export default JoinModal;