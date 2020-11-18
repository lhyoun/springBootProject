<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Error페이지 커스터마이징</title>
</head>
<body>

	<div>
	   <h1>Error Page 꾸며야됨</h1>
	   error code : <span> ${code}</span>
	   <br>error msg : <span>${msg}</span>
	   <br>timestamp : <span>${timestamp}</span>
	</div>
	
</body>
</html>