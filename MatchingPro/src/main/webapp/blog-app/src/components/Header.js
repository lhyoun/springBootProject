// ok
import React from "react";
import { Link } from "react-router-dom";
import { Navbar, Nav, NavDropdown, Row } from 'react-bootstrap';
import styled from "styled-components";
import SpanTagStyle from "../pages/constant/SpanTagStyle";
import LoginModal from "./modal/LoginModal";
import JoinModal from "./modal/JoinModal";
import LogoStyle from "../pages/constant/LogoStyle";

const HeaderStyle = styled.div`
  width: 100%;
  top: 0;
  position: fixed;
  z-index: 999;
`;

const SpanStyle = styled.span`
	color : black;
	font-weight : 700;
`;

const Header = (props) => {
  const isToken = props.isToken;
  const setToken = props.setToken;

  const logoutfunction = () => {
    localStorage.removeItem("Authorization");

    // logout fetch 
    fetch(`http://localhost:8000/logout`, {
      method: "GET",
      headers: {
      }
    }).then(res => res.text())
      .then(res => {
        if (res === "ok") {
          setToken();
          alert("로그아웃에 성공하였습니다");
        } else {
          alert("로그아웃 실패");
        }
      });
  }

  const isLogin = (flag) => {
    if (flag) {
      return <Row>
        <NavDropdown title="MYPAGE" id="basic-nav-dropdown">
          <NavDropdown.Item ><Nav.Link><Link to="/Mypage"><SpanTagStyle msg="MYPAGE"></SpanTagStyle></Link></Nav.Link></NavDropdown.Item>
          <NavDropdown.Item ><Nav.Link><Link to="/MyTeam"><SpanTagStyle msg="MYTEAM"></SpanTagStyle></Link></Nav.Link></NavDropdown.Item>
          {/* <NavDropdown.Divider />
          <NavDropdown.Item ><Nav.Link><Link to="/"><SpanTagStyle msg="MYTEAM"></SpanTagStyle></Link></Nav.Link></NavDropdown.Item> */}
        </NavDropdown>
        {/* <Nav.Link><Link to="/"><SpanTagStyle func={logoutfunction} msg="Logout"></SpanTagStyle></Link></Nav.Link> */}
        <Nav.Link><Link to="/"><SpanStyle onClick={logoutfunction}>Logout</SpanStyle></Link></Nav.Link>
      </Row>
    } else {
      return <Row>
        <Nav.Link><Link to="/Login"><LoginModal setToken={props.setToken}></LoginModal></Link></Nav.Link>
        <Nav.Link><Link to="/Join"><JoinModal></JoinModal></Link></Nav.Link>
      </Row>
    }
  }

  return (
    <HeaderStyle>
      <Navbar bg="light" expand="lg">

        <Nav.Link><Link to="/">
          <LogoStyle msg={<Navbar.Brand >
            <img src="/soccer_logo-removebg-preview.png"
              width="30"
              height="30"
              alt="React Bootstrap logo" />{''}

              &nbsp;FIELD HERO
          </Navbar.Brand>}></LogoStyle>

        </Link></Nav.Link>
        {/* 윗 부분 로고 */}

        {/* <Navbar.Toggle aria-controls="basic-navbar-nav" /> */}
        <Navbar.Collapse id="basic-navbar-nav">
          <Nav className="mr-auto">
            {isLogin(isToken)}
          </Nav>
        </Navbar.Collapse>
        {/* 윗 부분 로그인 여부에 따른 메뉴 */}
        
      </Navbar>
    </HeaderStyle>
  )
};

export default Header;