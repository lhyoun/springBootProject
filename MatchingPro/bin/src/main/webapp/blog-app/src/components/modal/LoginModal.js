import React, { useState } from 'react';
import { Container, Form, Col, Button, Row, ListGroup, Badge, Modal } from 'react-bootstrap';

function LoginModal(props) {
	const setToken = props.setToken;
	const [user, setUser] = useState({
		loginid: "",
		password: "",
	});

	const [show, setShow] = useState(false);
	const handleClose = () => setShow(false);
	const handleShow = () => setShow(true);

	const inputHandle = (e) => {
		setUser({
			...user,
			[e.target.name]: e.target.value,
		});
	};

	const loginRequest = () => {
		let person = {
			loginid: user.loginid,
			password: user.password
		}

		fetch("http://localhost:8000/login", {
			method: "POST",
			body: JSON.stringify(person),
			headers: {
				'Content-Type': "application/json; charset=utf-8"
			}
		}).then(res => {
			for (let header of res.headers.entries()) {
				if (header[0] === "authorization") {
					let data = header[1];
					//data = data.substring(7);
					localStorage.setItem("Authorization", data);
					setToken();
				}
			}
			return res.text();
		}).then(res => alert(res)
		);
	}

	return (
		<div>
			<Button variant="dark" onClick={handleShow}>로그인</Button>

			<Modal show={show} onHide={handleClose}>
				<Modal.Header closeButton>
					<Modal.Title>로그인</Modal.Title>
				</Modal.Header>
				<Modal.Body>
					<Container>
						<Form.Row>
							<Col md={2}></Col>
							<Form.Group as={Col} md={8} controlId="formGridEmail">
								<Form.Label>아이디</Form.Label>
								<Form.Control
									type="text"
									name="loginid"
									placeholder="아이디"
									onChange={inputHandle}
									value={user.loginid} />
							</Form.Group>
						</Form.Row>
						<Form.Row>
							<Col md={2}></Col>
							<Form.Group as={Col} md={8} controlId="formGridPassword">
								<Form.Label>비밀번호</Form.Label>
								<Form.Control
									type="password"
									name="password"
									placeholder="비밀번호"
									onChange={inputHandle}
									value={user.password} />
							</Form.Group>
						</Form.Row>
						<br />
						<Form.Row>
							<Col md={2}></Col>
							<Button variant="info" onClick={loginRequest}>로그인</Button>{' '}
						</Form.Row>
					</Container>
				</Modal.Body>
				<Modal.Footer>
					<Button variant="secondary" onClick={handleClose}>Close</Button>
				</Modal.Footer>
			</Modal>
		</div>
	);
}

export default LoginModal;
