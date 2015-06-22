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

		<div class="page-header"><h1>Seguridad <small>Usuarios - Roles</small></h1></div>
		
		<div class="row">
			<div class="col-md-12 col-sm-12">
				<div class="form-group">
					<h2>${secUser.secUserUsername} <c:if test="${totalElements != null}"><span class="badge">${totalElements}</span></c:if></h2>
				</div>
			</div>
		</div>
		
		<!-- FORMULARIO -->
		<div class="row">
			<jsp:include page="../include/_errors.jsp" flush="true" />
			<div class="col-md-6 col-sm-6">
				<button type="button" class="btn btn-default" id="btn-add" data-toggle="modal" data-target="#exampleModal"><i class="glyphicon glyphicon-plus"></i> Agregar</button>
			</div>
			
			<div class="col-md-6 col-sm-6">	
				<div class="form-group pull-right">
					<form class="form-inline pull-right" name="myForm" id="myForm" method="post">
						
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
						
						<div class="form-group">
							<label for="role">Rol: </label>
							<input type="text" class="form-control" name="secRoleName" id="secRoleName" placeholder="Rol" value="${secRoleName}" />
						</div>
						<input type="hidden" name="pageNumber" id="pageNumber" />
						<input type="hidden" name="secUserId" id="secUserId" value="${secUser.secUserId}" />
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
						<th class="hidden-xs hidden-sm"></th>
					</tr>
					<c:forEach var="r" items="${result}" >
					<tr>
						<td class="hidden-xs hidden-sm">${r.secRole.secRoleName}</td>
						<td class="hidden-xs hidden-sm">
							<div class="pull-right">
								<button type="button" class="btn btn-default" id="btn-delete" onClick="del(${r.secUserRoleId});"><i class="glyphicon glyphicon-remove"></i></button>
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
		
		<!--  POPUP -->
		<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title" id="exampleModalLabel">Agregar Rol</h4>
					</div>
					<div class="modal-body">
						<form id="popup-form" name="popup-form" action="${pageContext.request.contextPath}/mh/user/role/add" method="POST">
							<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
							<input type="hidden" id="secUserId" name="secUserId" value="${secUser.secUserId}" />
							<input type="hidden" name="pageNumber" id="pageNumber" />
							<div class="form-group">
								<label for="recipient-name" class="control-label">Recipient:</label>
								<select id="secRoleId" name="secRoleId" class="form-control">
									<c:if test="${secRole != null}">
										<c:forEach var="r" items="${secRole}" >
											<option value="${r.secRoleId}" <c:if test="${r.secRoleId == secUser.secRole.secRoleId}">selected</c:if>>${r.secRoleName}</option>
										</c:forEach>
									</c:if>
								</select>
							</div>
							<div class="form-group text-right">
								<button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
								<button type="submit" id="btn-save" class="btn btn-primary">Agregar</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		<!--/  POPUP -->
		
	</div>


	<jsp:include page="../include/_footer.jsp" flush="true" />
	<jsp:include page="../include/_javascript.jsp" flush="true" />

	<script type="text/javascript">
		$(function() {

			$('#btn-filter').click(function() {
				$(this).parents('form:first')
					.attr('action', '${pageContext.request.contextPath}/mh/user/role/list')
					.submit();
			});

		});
		
		function del(id) {
			var pageNumber = $('#pageNumber').val();
			var secUserId = $('#secUserId').val();
			window.location.href = '${pageContext.request.contextPath}/mh/user/role/delete?secUserRoleId='+id+'&secUserId='+secUserId+'&pageNumber='+pageNumber;
		};
		
	</script>

</body>
</html>