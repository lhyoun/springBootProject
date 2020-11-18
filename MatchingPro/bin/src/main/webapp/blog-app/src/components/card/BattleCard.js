import React from 'react';
import { ListGroup, Card, ListGroupItem, Row, Col } from 'react-bootstrap';
import { Link } from 'react-router-dom';
import styled from 'styled-components';

const LinkStyle = styled.span`
    color : black;
  `;

const TeamCard = (props) => {
	console.log("TeamCard:: props data: ", props);

	const memberCount = props.team.users.length;
	const { id, explaintation, name, owner} = props.team;
	const nickname = owner.nickname;
	const url = "/Team_detail/"+id;

	return (
		<div>
		<Card style={{ width: '18rem' }}>
			<Card.Img variant="top" src="1slideepic.png" />
			<Card.Body>
				<Card.Title>{name}</Card.Title>
				<Card.Text>
					{explaintation}
				</Card.Text>
			</Card.Body>
			<ListGroup className="list-group-flush">
				<ListGroupItem><Row><Col md={2}>👑</Col>{nickname}</Row></ListGroupItem>
				<ListGroupItem><Row><Col md={2}>👭</Col>{props.team.users.length}/20</Row></ListGroupItem>
				<ListGroupItem><Link to={url}><LinkStyle><Row><Col md={2}>🗓</Col>경기일정</Row></LinkStyle></Link></ListGroupItem>
				<ListGroupItem><Link to={url}><LinkStyle><Row><Col md={2}>✔</Col>상세보기</Row></LinkStyle></Link></ListGroupItem>
			</ListGroup>
			{/* <Card.Body>
				<Link to={url}><LinkStyle>✔상세보기</LinkStyle></Link>
			</Card.Body> */}
		</Card>
		<br/>
		</div>
	);
};

export default TeamCard;