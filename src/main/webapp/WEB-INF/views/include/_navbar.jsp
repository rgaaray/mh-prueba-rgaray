<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

	<nav class="navbar navbar-default">
	     <div class="container">

	       <div class="navbar-header">
	         <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" >
	           <span class="sr-only">Toggle navigation</span>
	           <span class="icon-bar"></span>
	           <span class="icon-bar"></span>
	           <span class="icon-bar"></span>
	         </button>
<%-- 	         <a class="navbar-brand" href="${pageContext.request.contextPath}/mh/"><img src="${pageContext.request.contextPath}/mh/resources/img/mh_logo_2.png" alt="MH" /></a> --%>
	         <a class="navbar-brand" href="${pageContext.request.contextPath}/mh/">MH - Ministerio de Hacienda</a>
	       </div>

	       <div id="navbar" class="collapse navbar-collapse">
	       		<sec:authorize access="isAuthenticated()">

		       		<form class="navbar-form navbar-right" method="post" action="${pageContext.request.contextPath}/mh/auth/logout">
						  <button type="submit" class="btn btn-default"><span class="glyphicon glyphicon-log-out"></span> Salir</button>
						  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			   		</form>

				    <ul class="nav navbar-nav  navbar-right">
				        <li>
				          <a href="#"><span class="glyphicon glyphicon-user"></span> <sec:authentication property="principal.username" /> </a>
				    	</li>
				    </ul>

			        <sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_SUPERVISOR')">
			        <ul class="nav navbar-nav  navbar-right">
				        <li class="dropdown">
				          <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="glyphicon glyphicon-th"></i> Administraci&oacute;n <span class="caret"></span></a>
				          <ul class="dropdown-menu">
			          		<sec:authorize access="hasRole('ROLE_ADMIN')">
								<li><a href="${pageContext.request.contextPath}/mh/user/list"><i class="glyphicon glyphicon-user"></i> Usuarios</a></li>
								<li class="divider"></li>
							</sec:authorize>
								<li><a href="${pageContext.request.contextPath}/mh/role/list"><i class="glyphicon glyphicon-lock"></i> Roles</a></li>
				          </ul>
				        </li>
				    </ul>
				    </sec:authorize>

			    </sec:authorize>
	       </div><!--/.nav-collapse -->
	  </div>
	</nav>