<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:fmt="http://java.sun.com/jsp/jstl/fmt" version="2.0">
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
<link type="text/css" rel="stylesheet" href="css/main.css" />
<link type="text/css" rel="stylesheet"
	href="bootstrap/css/bootstrap.min.css" />
<title>Java Chat</title>

</head>
<body>
	<h1>Java Chat</h1>

	<section>
		<p>
			Hello,
			<c:out value="${currentUser.name}" />
		</p>
		<p>
			<a href="chat_controller?cmd=logout">Выход</a>
		</p>
		<div class="panel panel-primary ppanel-left">
			<div class="panel-heading">
				<h3 class="panel-title">Messages</h3>
			</div>
			<div class="chat_messages panel-body" id="chat_mes">
				<c:forEach var="msg" items="${lastMessages}">
					<div class="message">
						<div class="message_info">
							<c:choose>
								<c:when test="${msg.roleId eq 1}">
									<span class="label label-danger"><c:out
											value="${msg.nick}" /></span>
								</c:when>
								<c:when test="${msg.roleId eq 2}">
									<span class="label label-success"><c:out
											value="${msg.nick}" /></span>
								</c:when>
							</c:choose>
							<span class="label_info label label-default"> <fmt:formatDate
									pattern="dd-MM-yyyy HH:mm:ss" value="${msg.timestamp.time}" />
							</span>
						</div>
						<div class="well well-sm">
							<c:out value="${msg.text}" />
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
		<form method="post" action="chat_controller">
			<div class="input-group ppanel-left">
				<input type="hidden" name="cmd" value="send" /> <input type="text"
					name="text" id="inputMsg" class="form-control" />
				<div class="input-group-btn">
					<button class="btn-size btn btn-success " type="submit">
						<span class="glyphicon glyphicon-comment" aria-hidden="true"></span>
					</button>
				</div>
			</div>
		</form>
		<div class="panel panel-primary ppanel-right">
			<div class="panel-heading">
				<h3 class="panel-title">Users</h3>
			</div>
			<div class="user_list panel-body">
				<div class="list-group">
					<c:forEach var="activeUser" items="${activeUsers}">
						<c:choose>
							<c:when test="${activeUser.role eq 1}">
								<a href="#" class="list-group-item list-group-item-danger"><c:out
										value="${activeUser.name}" /></a>
							</c:when>
							<c:when test="${activeUser.role eq 2}">
								<a href="#" class="list-group-item list-group-item-success"><c:out
										value="${activeUser.name}" /> <c:if
										test="${currentUser.role eq 1}">
										<form action="chat_controller" method="POST">
											<input type="hidden" name="cmd" value="kick" /> <input
												type="hidden" name="targetUser" value="${activeUser.name}" />
											<input type="submit" value="kick" />
										</form>
									</c:if></a>

							</c:when>
						</c:choose>
					</c:forEach>
				</div>
			</div>
		</div>

	</section>
	<jsp:text>
		<![CDATA[<script type=" text/javascript" src="js/chat.js"></script>]]>
	</jsp:text>
</body>
	</html>
</jsp:root>