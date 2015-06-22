<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${not empty errors}">
	<div class="alert alert-danger alert-dismissible">
	  <button type="button" class="close" data-dismiss="alert"><span>&times;</span></button>
	  <p><strong>Favor Verificar</strong></p>
	  <ul>
	  <c:forEach items="${errors}" var="error">
	  	<li>${error}</li>
	  </c:forEach>
	  </ul>
	</div>
</c:if>