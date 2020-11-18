import React from 'react';
import styled from 'styled-components';

const Style = styled.span`
	color : black;
	font-weight : 700;
	font-family : "Times New Roman"; 
	/* Georgia serif cursive sans-serif monospace fantasy  */
`;

const LogoStyle = (props) => {
	return (
		<Style>
			{props.imt}{props.msg}{props.msg2}
		</Style>
	);
};

export default LogoStyle;