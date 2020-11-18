// ok
import React, { useEffect, useState } from 'react';
import styled from 'styled-components';
import { Container, Row, Col, Carousel, Jumbotron, Button, Breadcrumb, Card, ListGroup, ListGroupItem, ResponsiveEmbed, Form, FormControl, Modal, Tabs, Tab } from 'react-bootstrap';
import { Link } from 'react-router-dom';
import TeamCard from '../components/card/TeamCard';
import UserCard from '../components/card/UserCard';
import Slide from '../components/Slide';
import SpanTagStyle from './constant/SpanTagStyle';
import TitleH1TagStyle from './constant/TitleH1TagStyle';
import TitleH3TagStyle from './constant/TitleH3TagStyle';
import Br2 from './constant/Br2';
import Footer from '../components/Footer';


const MainCardStyle = styled.div`

    width: 100%;
    margin: auto;
`;

const MainForm = () => {
  useEffect(() => {
    // teamList fetch -> teamCard에 담아서 보여줌
    fetch("http://localhost:8000/teamList", {
      method: "get",
    }).then((res) => res.json())
      .then((res) => {
        console.log("mainForm teamList response [json type]", res);
        setTeams(res);
      });

    // userList fetch -> userCard에 담아서 보여줌
    fetch("http://localhost:8000/userList", {
      method: "get",
    }).then((res) => res.json())
      .then((res) => {
        console.log("mainForm userList response [json type]", res);
        setUsers(res);
      });

    // rank fetch -> rank tab에서 보여줌
    fetch("http://localhost:8000/rank", {
      method: "get",
    }).then((res) => res.json())
      .then((res) => {
        console.log("mainForm rank response [json type]", res);
        setRank(res);
      });
  }, []);

  const [rank, setRank] = useState([]);
  const [teams, setTeams] = useState([]);
  const [users, setUsers] = useState([]);

  return (
    <Container>
      <br/>
        <br/>
          <br/>
      <Slide />
      <Row>
        <MainCardStyle>
          <Jumbotron>
            <TitleH1TagStyle msg="아마추어 축구 여기서 시작하세요!"></TitleH1TagStyle>
            <Button variant="info">
              <Link to="/Team_create"><SpanTagStyle msg="팀 만들기"></SpanTagStyle></Link>
            </Button>
            <hr />

            <TitleH3TagStyle msg="원하는 팀과 선수를 찾아보세요"></TitleH3TagStyle>
            <Tabs defaultActiveKey="rank" id="uncontrolled-tab-example">

              {/* teamlist tab */}
              <Tab eventKey="team" title="TEAM">
                <Row>
                  {teams.map((res) => (<Col md={4}><TeamCard team={res} key={res.id}></TeamCard></Col>))}
                </Row>
              </Tab>

              {/* userlist tab */}
              <Tab eventKey="user" title="PLAYER">
                <Row>
                  {users.map((res) => (<Col md={4}><UserCard user={res} key={res.id}></UserCard></Col>))}
                </Row>
              </Tab>

              {/* rank tab */}
              <Tab eventKey="rank" title="RANK">
                <Br2/>
                <Row>
                  <Col md={2}></Col>
                  <Col md={2}><SpanTagStyle msg="TEAM"></SpanTagStyle></Col>
                  <Col md={2}><SpanTagStyle msg="SCORE"></SpanTagStyle></Col>
                  <Col md={2}><SpanTagStyle msg="W"></SpanTagStyle></Col>
                  <Col md={2}><SpanTagStyle msg="L"></SpanTagStyle></Col>
                  <Col md={2}><SpanTagStyle msg="D"></SpanTagStyle></Col>
                </Row>
                {rank.map((res) => <Row>
                  <Col md={2}></Col>
                  <Col md={2}>{res.team.name}</Col>
                  <Col md={2}>{res.total}</Col>
                  <Col md={2}>{res.win}</Col>
                  <Col md={2}>{res.lose}</Col>
                  <Col md={2}>{res.draw}</Col>
                </Row>)}
              </Tab>
            </Tabs>
          </Jumbotron>
        </MainCardStyle>
      </Row>


  
    </Container>
  );
};

export default MainForm;