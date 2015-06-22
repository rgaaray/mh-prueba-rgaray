<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html lang="es">
	<head>
		<jsp:include page="../include/_meta.jsp" />
	    <jsp:include page="../include/_stylesheet.jsp" />
	
		<title>Usuarios	| Seguridad</title>
	</head>
	<body>
	
		<jsp:include page="../include/_navbar.jsp" flush="true" />
	
	
		<div class="container">
	
			<div class="page-header"><h1>Seguridad <small>Usuarios</small></h1></div>
			<!-- FORM -->
			<div class="row">
				<div class="col-md-6 col-sm-12">
					<h2>Agegar Usuario</h2>
					<jsp:include page="../include/_errors.jsp" flush="true" />
	
						<c:if test="${not empty message}">
							<div class="alert alert-success alert-dismissible">
							  <button type="button" class="close" data-dismiss="alert"><span>&times;</span></button>
							  <p><strong>${message}</strong></p>
							</div>
						</c:if>
				</div>
				<div id="secUserContent" class="forms">
					<div class="secUserForm" style="width: 470px;">
						<form method="POST" action="${pageContext.request.contextPath}/mh/user/add">
							<input type="hidden" id="csrfParameter" name="csrfParameter" value="${_csrf.parameterName}" />
							<input type="hidden" id="csrfToken" name="csrfToken" value="${_csrf.token}" />
							<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
							<input type="hidden" id="secUserId" name="secUser.secUserId" value="${secUser.secUserId}" />
							<fieldset>
								<div class="row-form">
									<label style="width: 175px;">Usuario:</label>
								</div>
								<div class="row-form row-form-spacing">
									<input type="text" class="form-control" name="secUser.secUserUsername" id="secUserUsername" value="${secUser.secUserUsername}" required />
								</div>
								<br />
								<div class="row-form">
									<label style="width: 175px;">Contraseña:</label>
								</div>
								<div class="row-form row-form-spacing">
									<input type="password" class="form-control" name="secUser.secUserPassword" id="secUserPassword" value="${secUser.secUserPassword}" maxlength="8" required />
								</div>
								<br />
								<div class="row-form">
									<label style="width: 175px;">Nombre:</label>
								</div>
								<div class="row-form row-form-spacing">
									<input type="text" class="form-control" name="secUser.secUserName" id="secUserName" value="${secUser.secUserName}" required />
								</div>
								<br />
								<div class="row-form">
									<label style="width: 175px;">Email:</label>
								</div>
								<div class="row-form row-form-spacing">
									<input type="text" class="form-control" name="secUser.secUserEmail" id="secUserEmail" value="${secUser.secUserEmail}" />
								</div>
								<br />
								<div class="row-form">
									<label style="width: 175px;">Fecha Nac.:</label>
								</div>
								<div class="row-form row-form-spacing" style="width:195px;">
									<div class="input-group input-append date" id="datePicker">
						                <input type="text" class="form-control" name="secUser.secUserBirthDate" id="secUserBirthDate" value="${secUser.secUserBirthDateAsDD_MM_YYYY}" />
						                <span class="input-group-addon add-on"><i class="glyphicon glyphicon-calendar"></i></span>
						            </div>
<%-- 									<input type="text" class="form-control" name="secUser.secUserBirthDate" id="secUserBirthDate" value="${secUser.secUserBirthDate}" /> --%>
								</div>
								<br />
								<div class="row-form">
									<label style="width: 175px;">Rol:</label>
								</div>
								<div class="row-form row-form-spacing">
									<select id="secUserRole" name="secUser.secRole.secRoleId" class="form-control">
										<c:if test="${secRole != null}">
											<c:forEach var="r" items="${secRole}" >
												<option value="${r.secRoleId}" <c:if test="${r.secRoleId == secUser.secRole.secRoleId}">selected</c:if>>${r.secRoleName}</option>
											</c:forEach>
										</c:if>
									</select>
								</div>
								<br />
								<div class="row-form">
									<label style="width: 175px;">Activo:</label>
								</div>
								<div class="row-form row-form-spacing">
									<select id="secUserActive" name="secUser.secUserActive" class="form-control">
										<option value="1" <c:if test="${secUser.secUserActive == '1'}">selected</c:if>>Si</option>
									  	<option value="0" <c:if test="${secUser.secUserActive == '0'}">selected</c:if> >No</option>
									</select>
								</div>
								<br />
								<div id="info" style="color: green;"></div>
							 </fieldset>
							 <button type="button" class="btn btn-default" id="btn-save" onClick="$(this).save();"><i class="glyphicon glyphicon-floppy-disk"></i>Guardar Cambios</button> o <a href="${pageContext.request.contextPath}/mh/user/list">Cancelar</a>
<%-- 					  		<button type="submit" class="btn btn-default"><i class="glyphicon glyphicon-floppy-disk"></i> Guardar Cambios</button> o <a href="${pageContext.request.contextPath}/mh/user/list">Cancelar</a> --%>
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
				
				$('#datePicker').datepicker({
                    format: "dd/mm/yyyy",
                    todayBtn: "linked",
                    autoclose: true,
                    todayHighlight: true
                });
				
				$.fn.save = function() {
					var url = "";
					var data = {};
					var csrfParameter = $('#csrfParameter').val();
					var csrfToken = $('#csrfToken').val(); 
					var secUserId = $('#secUserId').val();
					
					data[csrfParameter] = csrfToken;
					data["secUserId"] = secUserId;
					data["secUserUsername"] = $('#secUserUsername').val();
					data["secUserPassword"] = $('#secUserPassword').val();
					data["secUserName"] = $('#secUserName').val();
					data["secUserEmail"] = $('#secUserEmail').val();
					data["secRole.secRoleId"] = $('#secUserRole').val();
					data["secUserActive"] = $('#secUserActive').val();
					data["secUserBirthDateAsDD_MM_YYYY"] = $('#secUserBirthDate').val();
					
					if (secUserId == null || secUserId == "" || secUserId == undefined) {
						url = "${pageContext.request.contextPath}/mh/user/add";
					} else {
						url = "${pageContext.request.contextPath}/mh/user/edit";
					}
					
					$.ajax({
				        type: "POST",
				        url: url,
				        data: data,
				        dataType: 'json',
				        success: function(data){
					        $('#info').html(data.text);
					        if (data.action == 'add') {
						        $('#secUserId').val('');
						        $('#secUserUsername').val('');
						        $('#secUserPassword').val('');
						        $('#secUserName').val('');
						        $('#secUserEmail').val('');
						        $('#secUserRole').val('');
						        $('#secUserActive').val('');
						        $('#secUserBirthDate').val('');
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