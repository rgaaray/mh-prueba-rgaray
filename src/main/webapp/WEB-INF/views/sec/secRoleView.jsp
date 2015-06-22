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

		<div class="page-header"><h1>Seguridad <small>Roles</small></h1></div>
		
		<div class="row">
			<div class="col-md-12 col-sm-12">
				<div class="form-group">
					<h2>Roles <c:if test="${totalElements != null}"><span class="badge">${totalElements}</span></c:if></h2>
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
					<form class="form-inline pull-right" name="myForm" id="myForm" action="${pageContext.request.contextPath}/mh/role/list" method="post">
						
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
						
						<div class="form-group">
							<label for="usuario">Rol: </label>
							<input type="text" class="form-control" name="secRoleName" id="secRoleName" placeholder="Rol" value="${secRoleName}" />
						</div>
						<input type="hidden" name="pageNumber" id="pageNumber" />
						<button type="submit" class="btn btn-primary" id="btn-filter">
							<i class="glyphicon glyphicon-filter"></i> Filtrar
						</button>
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
						<th class="hidden-xs hidden-sm">ROL</th>
						<th class="hidden-xs hidden-sm">ACTIVO?</th>
						<th class="hidden-xs hidden-sm"></th>
					</tr>
					<c:forEach var="r" items="${result}" >
						<tr>
							<td><strong>${r.secRoleName}</strong></td>
							<c:choose>
								<c:when test="${r.secRoleActive == 1}">
									<td class="hidden-xs hidden-sm">Si</td>
								</c:when>
								<c:otherwise>
									<td class="hidden-xs hidden-sm">No</td>
								</c:otherwise>
							</c:choose>
							<td class="hidden-xs hidden-sm">
								<div class="pull-right">
									<button type="button" class="btn btn-default" id="btn-edit" onClick="edit(${r.secRoleId});"><i class="glyphicon glyphicon-pencil"></i></button>
									<button type="button" class="btn btn-default" id="btn-delete" onClick="del(${r.secRoleId});"><i class="glyphicon glyphicon-remove"></i></button>
								</div>
							</td>
						</tr>
					</c:forEach>
					</table>

					<div>
						<nav>
						  <ul class="pagination">
						    <li>
						      <a href="#" onClick="fetchData(0);">
						        <span>&laquo;</span>
						      </a>
						    </li>
						    <c:forEach begin="1" end="${totalPages}" var="p">
						    	<c:choose>
							    	<c:when test="${currentPage == (p - 1)}">
							    		<li class="active"><a href="#" onClick="fetchData(${p - 1});">${p}</a></li>
							    	</c:when>
							    	<c:otherwise>
							    		<li><a href="#" onClick="fetchData(${p - 1});">${p}</a></li>
							    	</c:otherwise>
						    	</c:choose>
						    </c:forEach>
						    <li>
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
					.attr('action', '${pageContext.request.contextPath}/mh/role/list')
					.submit();
			});

			$('#btn-add').click(function() {
				window.location.href = '${pageContext.request.contextPath}/mh/role/addView';
			});
			
		});
		
		function edit(id) {
			window.location.href = '${pageContext.request.contextPath}/mh/role/editView?secRoleId=' + id;
		};
		
		function del(id) {
			var pageNumber = $('#pageNumber').val();
			window.location.href = '${pageContext.request.contextPath}/mh/role/delete?secRoleId=' + id + '&pageNumber=' + pageNumber;
		};
		
	</script>

</body>
</html>