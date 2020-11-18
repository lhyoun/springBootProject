// ok (image - x) 
import React from 'react';
import { ListGroup, Card, ListGroupItem, Row, Col } from 'react-bootstrap';

const UserCard = (props) => {
	// function for team display
	const userTeamFunc = () => {
		try {
			return props.user.teams.name;
		} catch (error) {
			return "팀 없음";
		}
	}

	const Team = userTeamFunc();
	const { nickname, location, position, image } = props.user;

	const imageStr = `http://localhost:8000/image/${image}`

	return (
		<div>
			<Card style={{ width: '18rem' }}>
				{/* 이미지 나중에 고치기  */}
				<Card.Img variant="top" src={imageStr} />
				<Card.Body>
					<Card.Title>🧑 {nickname} </Card.Title>
				</Card.Body>
				<ListGroup className="list-group-flush">
					<ListGroupItem><Row><Col md={2}>🔖</Col>{Team}</Row></ListGroupItem>
					<ListGroupItem><Row><Col md={2}>⚽</Col>{position}</Row></ListGroupItem>
					<ListGroupItem><Row><Col md={2}>📍</Col>{location}</Row></ListGroupItem>
				</ListGroup>
			</Card>
			<br />
		</div>
	);
};

export default UserCard;