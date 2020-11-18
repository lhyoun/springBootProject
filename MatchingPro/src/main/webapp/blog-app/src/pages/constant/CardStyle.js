import React from 'react';
import styled from 'styled-components';

const Style = styled.span`
	color : black;
	font-weight : 700;
`;

const CardStyle = (props) => {
	return (
		<Style>
			{props.imt}{props.msg}{props.msg2}
		</Style>
	);
};

export default SpanTagStyle;