<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:url value="/movie/inserimento" var="actionUrl" />
<c:url value="/movie/inserimentoIndicizzato" var="actionUrl2" />


<form class="well form-search" id="search-by-title-form"
	action="${actionUrl}">
	<fieldset>
		<legend>By Title</legend>
	</fieldset>
	<div>
		<label class="control-label" for="t">Title:</label> <input type="text"
			id="t" name="movieName" class="input-small"> &nbsp;&nbsp; 
			
		<button type="submit" name="action" value="insert" class="btn btn-default">Insert</button>
		<button type="submit" name="action" value="randomInsert" class="btn btn-default">Random Insert Db</button>
		<button type="submit" name="action" value="cleanDb" class="btn btn-default">Clean Db</button>
		<button type="submit" name="action" value="wikiTry" class="btn btn-default">Wiki try</button>
		<button type="submit" name="action" value="jsoupTry" class="btn btn-default">Jsoup try</button>
		
		
	</div>
	</form>
	<form class="well form-search" id="search-by-title-form" action="${actionUrl2}">
	<fieldset>
		<legend>By Id</legend>
	</fieldset>
	<div>
		 <input type="text" id="t" name="movieStartIndex" class="input-small"> &nbsp;&nbsp; 
		<input type="text" id="t" name="movieEndIndex" class="input-small"> &nbsp;&nbsp; 
			
		<button type="submit" name="action" value="insert" class="btn btn-default">Insert</button>
		
	</div>
	
	
	<div class="well">${responseMessage}</div>
</form>