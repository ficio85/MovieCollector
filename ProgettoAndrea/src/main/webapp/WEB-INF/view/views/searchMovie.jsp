<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:url value="/movie/ricerca" var="actionUrl" />


<form class="well form-search" id="search-by-title-form"action="ProgettoAndrea/loadActors">
	
	
	
		<div class="ui-widget">
		  <label for="actors" >Actors: </label>
		  <input type="text" id="actors">
		</div>
	<div></div>
<button type="submit" name="action" value="search" class="btn btn-default">Search</button>

</form>
