<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

</head>

<body>

	<h3>Tematem zadania jest formularz do wyliczania rat kredytu</h3>
	<h5>(okres kredytowania podaj w latach, oprocentowanie w procentach)</h5>
	
	<form action= "hello" method="post">
			
		<INPUT name="creditAmmount" placeholder = "kwota kredytu">
		<INPUT name="creditPeriod" placeholder = "okres kredytowania">
		<INPUT name="creditInterestRate" placeholder = "oprocentowanie">
		
		<SELECT id="list" name ="list">
		<OPTION VALUE = "rata_stala">rata stala</OPTION>
		<OPTION VALUE = "rata_malejaca">rata malejaca</OPTION>
		</SELECT>

		<INPUT TYPE = "submit" value = "oblicz" NAME = "oblicz">
		
	</form>

</body>
</html>