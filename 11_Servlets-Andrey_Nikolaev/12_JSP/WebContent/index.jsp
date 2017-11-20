<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0">
	<jsp:directive.page language="java"
		contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" />
	<jsp:text>
		<![CDATA[ <?xml version="1.0" encoding="UTF-8" ?> ]]>
	</jsp:text>
	<jsp:text>
		<![CDATA[ <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> ]]>
	</jsp:text>
	<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link type="text/css" rel="stylesheet" href="css/login_form.css" />
<title>Insert title here</title>
</head>
<body>
	<form class="login_form" method="post" action="chat_controller">
		<h1>Форма входа в чат</h1>
		<input type="hidden" name="cmd" value="login" />
		<p>
			<label for="login">Логин или email</label> <input type="text"
				name="name" placeholder="Логин или email" required="true" />
		</p>

		<p>
			<input type="submit" name="submit" value="Войти" />
		</p>
		<br /> ${errorLoginMessage}
	</form>
</body>
	</html>
</jsp:root>