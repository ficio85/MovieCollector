<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<c:if test="${error!=null}">
	<div class="alert alert-danger" role="alert">
		<c:if test="${not empty error.header}">
			<strong>${error.header} </strong>
		</c:if>
		${error.msg}
	</div>
</c:if>