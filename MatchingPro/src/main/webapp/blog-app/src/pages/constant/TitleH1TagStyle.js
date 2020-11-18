import React from 'react';
import styled from 'styled-components';

const Style = styled.h1`
	color : black;
	font-weight : 500;
	font-family : fantasy; 
	/* Georgia serif cursive sans-serif monospace fantasy  */
`;

const TitleH1TagStyle = (props) => {
	return (
		<Style>
			{props.msg}
		</Style>
	);
};

export default TitleH1TagStyle;