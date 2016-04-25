<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<c:url value="/movie/searchMovieResult" var="actionUrl" />



<form:form class="well form-search" id="search-by-title-form"
	action="${actionUrl}" modelAttribute="searchMovieForm">
	<div>
		<label for="actors">Genre: </label>
		<form:select path="genere">
			<form:options items="${generiList}" itemValue="codGenre"
				itemLabel="desGenre" />
		</form:select>
	</div>
	<div class="ui-widget">
		<label for="actors">Actors: </label>
		<form:input type="text" path="actor" id="actors"></form:input>
	</div>
	<div class="ui-widget">
		<label for="directors">Directors: </label>
		<form:input type="text" path="director" id="directors"></form:input>
	</div>
	<div></div>
	<button type="submit" name="action" value="search"
		class="btn btn-default">Search</button>

</form:form>


<c:forEach var="listValue" items="${lists}">
	<li>${listValue}</li>
</c:forEach>
<c:if test="${tableResult}">
	<table class="table table-bordered">
		<tr>
			<th>#</th>
			<th>Title</th>
			<th>Titolo Italiano</th>
			<th>Year</th>
			<th>Genre</th>
			<th>Director</th>
			<th>Actors</th>
			<th>Buttons</th>
		</tr>
		<c:forEach var="movie" items="${listMovies}">

			<tr>
				<td><img class="img-responsive img-thumbnail"
					style="width: 140px; height: 180px;" src="${movie.poster}"></td>
				<td>${movie.title}</td>
				<td>${movie.titoloItaliano}</td>
				<td>${movie.year}</td>
				<td><c:forEach var="genere" items="${movie.genre}">
				${genere.desGenre}
					</c:forEach>
				</td>
				<td><c:forEach var="regista" items="${movie.directors}">
				${regista.name}
					</c:forEach></td>
				<td><c:forEach var="attore" items="${movie.actors}">
				${attore.name}
					</c:forEach></td>
				<td>Buttons</td>
			</tr>
		</c:forEach>
	</table>

</c:if>