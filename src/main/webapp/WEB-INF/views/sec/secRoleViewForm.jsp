<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="es">
	<head>
		<jsp:include page="../include/_meta.jsp" />
	    <jsp:include page="../include/_stylesheet.jsp" />
	
		<title>Roles | Seguridad</title>
	</head>
	<body>
	
		<jsp:include page="../include/_navbar.jsp" flush="true" />
	
	
		<div class="container">
	
			<div class="page-header"><h1>Seguridad <small>Roles</small></h1></div>
			<!-- FORM -->
			<div class="row">
				<div class="col-md-6 col-sm-12">
					<h2>Agegar Rol</h2>
					<jsp:include page="../include/_errors.jsp" flush="true" />
	
						<c:if test="${not empty message}">
							<div class="alert alert-success alert-dismissible">
							  <button type="button" class="close" data-dismiss="alert"><span>&times;</span></button>
							  <p><strong>${message}</strong></p>
							</div>
						</c:if>
				</div>
				<div id="secRoleContent" class="forms">
					<div class="secRoleForm" style="width: 470px;">
						<form method="POST" action="${pageContext.request.contextPath}/mh/role/add">
							<input type="hidden" id="csrfParameter" name="csrfParameter" value="${_csrf.parameterName}" />
							<input type="hidden" id="csrfToken" name="csrfToken" value="${_csrf.token}" />
							<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
							<input type="hidden" id="secRoleId" name="secRole.secRoleId" value="${secRole.secRoleId}" />
							<fieldset>
								<div class="row-form">
									<label style="width: 175px;">Rol:</label>
								</div>
								<div class="row-form row-form-spacing">
									<input type="text" class="form-control" name="secRole.secRoleName" id="secRoleName" value="${secRole.secRoleName}" required />
								</div>
								<br />
								<div class="row-form">
									<label style="width: 175px;">Activo:</label>
								</div>
								<div class="row-form row-form-spacing">
									<select id="secRoleActive" name="secRole.secRoleActive" class="form-control">
										<option value="1" <c:if test="${secRole.secRoleActive == '1'}">selected</c:if>>Si</option>
									  	<option value="0" <c:if test="${secRole.secRoleActive == '0'}">selected</c:if> >No</option>
									</select>
								</div>
								<br />
								<div id="info" style="color: green;"></div>
							 </fieldset>
							 <button type="button" class="btn btn-default" id="btn-save" onClick="$(this).save();"><i class="glyphicon glyphicon-floppy-disk"></i>Guardar Cambios</button> o <a href="${pageContext.request.contextPath}/mh/role/list">Cancelar</a>
						</form>
					</div>
				</div>
			</div>
			<!--/ FORM -->
		</div>
	
		<jsp:include page="../include/_footer.jsp" flush="true" />
		<jsp:include page="../include/_javascript.jsp" flush="true" />
		
		<script type="text/javascript">
			$(function() {
				
				$.fn.save = function() {
					var url = "";
					var data = {};
					var csrfParameter = $('#csrfParameter').val();
					var csrfToken = $('#csrfToken').val(); 
					var secRoleId = $('#secRoleId').val();
					
					data[csrfParameter] = csrfToken;
					data["secRoleId"] = secRoleId;
					data["secRoleName"] = $('#secRoleName').val();
					data["secRoleActive"] = $('#secRoleActive').val();
					
					if (secRoleId == null || secRoleId == "" || secRoleId == undefined) {
						url = "${pageContext.request.contextPath}/mh/role/add";
					} else {
						url = "${pageContext.request.contextPath}/mh/role/edit";
					}
					
					$.ajax({
				        type: "POST",
				        url: url,
				        data: data,
				        dataType: 'json',
				        success: function(data){
					        $('#info').html(data.text);
					        if (data.action == 'add') {
						        $('#secRoleId').val('');
						        $('#secRoleName').val('');
						        $('#secRoleActive').val('');
					        }
				        },
				        error: function(e){
				        	alert('Error: ' + e);
				        }
		        	});

		        };
		        
			});
		</script>
	
	</body>
</html>