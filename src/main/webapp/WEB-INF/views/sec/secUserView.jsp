<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <jsp:include page="../include/_meta.jsp" />
    <jsp:include page="../include/_stylesheet.jsp" />
	<script type="text/javascript">
	function fetchData(pageNum) {
    	var elem = document.getElementById("pageNumber");
    	elem.value = pageNum;
    	document.myForm.submit();
	}
	</script>
	<title>Usuarios | Seguridad</title>

</head>
<body>

	<jsp:include page="../include/_navbar.jsp" flush="true" />


	<div class="container">

		<div class="page-header"><h1>Seguridad <small>Usuarios</small></h1></div>
		
<!-- 		<div class="row"> -->
<!-- 			<div class="col-md-12 col-sm-12"> -->
<%-- 				<a href="${pageContext.request.contextPath}/mh/">Regresar</a> --%>
<!-- 			</div> -->
<!-- 		</div> -->

		<div class="row">
			<div class="col-md-12 col-sm-12">
				<div class="form-group">
					<h2>Usuarios <c:if test="${totalElements != null}"><span class="badge">${totalElements}</span></c:if></h2>
				</div>
			</div>
		</div>
		
		<!-- FORMULARIO -->
		<div class="row">
			<jsp:include page="../include/_errors.jsp" flush="true" />
			<div class="col-md-6 col-sm-6">
				<button type="button" class="btn btn-default" id="btn-add"><i class="glyphicon glyphicon-plus"></i> Nuevo</button>
			</div>
			
			<div class="col-md-6 col-sm-6">	
				<div class="form-group pull-right">
					<form class="form-inline pull-right" name="myForm" id="myForm" method="post">
						
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
						
						<div class="form-group">
							<label for="usuario">Usuario: </label>
							<input type="text" class="form-control" name="secUserUsername" id="secUserUsername" placeholder="Usuario" value="${secUserUsername}" />
						</div>
						<input type="hidden" name="pageNumber" id="pageNumber" />
						<button type="submit" class="btn btn-primary" id="btn-filter">
							<i class="glyphicon glyphicon-filter"></i> Filtrar
						</button>
						<button type="button" class="btn btn-default" id="btn-export"><i class="glyphicon glyphicon-export"></i> Exportar</button>
					</form>
				</div>
			</div>
		</div>
		<!--/ FORMULARIO -->
		
		<!--  TABLA DE RESULTADOS -->
		<div class="row">
			<div class="col-md-12 col-sm-12">
				<c:if test="${not empty message}">
					<div class="alert alert-success alert-dismissible">
					  <button type="button" class="close" data-dismiss="alert"><span>&times;</span></button>
					  <p><strong>${message}</strong></p>
					</div>
				</c:if>

				<c:if test="${result != null}">

					<table class="table table-striped">
					<tr>
						<th class="hidden-xs hidden-sm">USUARIO</th>
						<th class="hidden-xs hidden-sm">NOMBRE</th>
						<th class="hidden-xs hidden-sm">EMAIL</th>
						<th class="hidden-xs hidden-sm">ROL</th>
						<th class="hidden-xs hidden-sm"></th>
					</tr>
					<c:forEach var="r" items="${result}" >
					<tr>
						<td><strong>${r.secUserUsername}</strong></td>
<%-- 						<td class="text-center hidden-xs hidden-sm">${r.secUserName}</td> --%>
						<td class="hidden-xs hidden-sm">${r.secUserName}</td>
						<td class="hidden-xs hidden-sm">${r.secUserEmail}</td>
						<td class="hidden-xs hidden-sm">${r.secRole.secRoleName}</td>
						<td class="hidden-xs hidden-sm">
							<div class="pull-right">
								<button type="button" class="btn btn-default" id="btn-edit" onClick="roles(${r.secUserId});"><i class="glyphicon glyphicon-lock"></i></button>
								<button type="button" class="btn btn-default" id="btn-edit" onClick="edit(${r.secUserId});"><i class="glyphicon glyphicon-pencil"></i></button>
								<button type="button" class="btn btn-default" id="btn-delete" onClick="del(${r.secUserId});"><i class="glyphicon glyphicon-remove"></i></button>
							</div>
						</td>
					</tr>
					</c:forEach>
					</table>

					<div>
						<nav>
						  <ul class="pagination">
						    <li>
						      <%-- <a href="${pageContext.request.contextPath}/mh/user/list?pageNumber=0"> --%>
						      <a href="#" onClick="fetchData(0);">
						        <span>&laquo;</span>
						      </a>
						    </li>
						    <c:forEach begin="1" end="${totalPages}" var="p">
						    	<c:choose>
							    	<c:when test="${currentPage == (p - 1)}">
							    		<%-- <li class="active"><a href="${pageContext.request.contextPath}/mh/user/list?pageNumber=${p - 1}">${p}</a></li> --%>
							    		<li class="active"><a href="#" onClick="fetchData(${p - 1});">${p}</a></li>
							    	</c:when>
							    	<c:otherwise>
							    		<%-- <li><a href="${pageContext.request.contextPath}/mh/user/list?pageNumber=${p - 1}">${p}</a></li> --%>
							    		<li><a href="#" onClick="fetchData(${p - 1});">${p}</a></li>
							    	</c:otherwise>
						    	</c:choose>
						    </c:forEach>
						    <li>
						      <%-- <a href="${pageContext.request.contextPath}/mh/user/list?pageNumber=${totalPages - 1}"> --%>
						      <a href="#" onClick="fetchData(${totalPages - 1});">
						        <span>&raquo;</span>
						      </a>
						    </li>
						  </ul>
						</nav>
					</div>
				</c:if>
			</div>
		</div>
		<!--/  TABLA DE RESULTADOS -->
	</div>



	<jsp:include page="../include/_footer.jsp" flush="true" />
	<jsp:include page="../include/_javascript.jsp" flush="true" />

	<script type="text/javascript">
		$(function() {

			$('#btn-filter').click(function() {
				$(this).parents('form:first')
					.attr('action', '${pageContext.request.contextPath}/mh/user/list')
					.submit();
			});

			$('#btn-export').click(function() {
				$(this).parents('form:first')
					.attr('action', '${pageContext.request.contextPath}/mh/user/export')
					.submit();
			});
			
			$('#btn-add').click(function() {
				window.location.href = '${pageContext.request.contextPath}/mh/user/addView';
			});
			
		});
		
		function roles(id) {
			window.location.href = '${pageContext.request.contextPath}/mh/user/role/list?secUserId=' + id;
		};
		
		function edit(id) {
			window.location.href = '${pageContext.request.contextPath}/mh/user/editView?secUserId=' + id;
		};
		
		function del(id) {
			var pageNumber = $('#pageNumber').val();
			window.location.href = '${pageContext.request.contextPath}/mh/user/delete?secUserId=' + id + '&pageNumber=' + pageNumber;
		};
		
	</script>

</body>
</html>