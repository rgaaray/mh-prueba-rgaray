<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="es">
  <head>
    <jsp:include page="include/_meta.jsp" />
    <jsp:include page="include/_stylesheet.jsp" />

	<title>MH Prueba</title>
</head>
<body>
	<fmt:setBundle basename="messages" var="msg"/>
	<div>
		<form class="form-signin" method="post">
			<div class="page-header"><h1><span class="glyphicon glyphicon-lock"></span> Iniciar Sesi&oacute;n</h1></div>
			
			<c:if test="${not empty error}">
				<div class="alert alert-danger alert-dismissible">
				  <button type="button" class="close" data-dismiss="alert"><span>&times;</span></button>
				  <p><span class="glyphicon glyphicon-exclamation-sign"></span> ${error}</p>
				</div>
			</c:if>
			
			<label for="inputEmail" class="sr-only">Usuario</label>
			<input type="text" name="username" id="username" class="form-control" placeholder="Usuario" maxlength="20" required autofocus>
			
			<label for="inputPassword" class="sr-only">Clave</label>
			<input type="password" name="password" id="password" class="form-control" maxlength="8" placeholder="Clave" required>
			
			<div>
				<button class="btn btn-lg btn-block" type="submit"><span class="glyphicon glyphicon-log-in"></span> Autenticar</button>	
			</div>
			
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		</form>
		<div class="text-center">
			<div><img src="${pageContext.request.contextPath}/mh/resources/img/mh_logo.png" alt="Equifax Experto WAP" style="width: 125px" /></div>
			<div class="text-muted small">CopyRight</div>
		</div >
	</div>
	
	<jsp:include page="include/_javascript.jsp" flush="true" />
    
</body>
</html>