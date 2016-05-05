<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<c:url value="/movie/searchMovieResult" var="actionUrl" />


<div class="row">
	<form:form class="well form-horizontal" id="search-by-t+
	itle-form"
		action="${actionUrl}" modelAttribute="searchMovieForm">
		<div class="form-group">
			<label for="actors" class="col-md-2">Genre: </label>
			<div class="col-md-10">
				<form:select path="genere">
					<form:option value="">--Select Genre--</form:option>
					<form:options items="${generiList}" itemValue="codGenre"
						itemLabel="desGenre" />
				</form:select>
			</div>
		</div>
		<div class="form-group ui-widget ">
			<label for="actors" class="col-md-2">Actors: </label>
			<div class="col-md-10">
				<form:input type="text" class="autoCompleteClass"
					data-servlet="/loadActors" path="actor" id="actors"></form:input>
			</div>
		</div>
		<div class="form-group ui-widget">
			<label for="directors" class="col-md-2">Directors: </label>
			<div class="col-md-10">
				<form:input type="text" class="autoCompleteClass"
					data-servlet="/loadDirectors" path="director" id="directors"></form:input>
			</div>
		</div>
		<div class="form-group">
			<label for="length" class="col-md-2">Length </label>
			<div class="col-md-10">
				<form:input type="text" path="minLength" id="lengthMin"></form:input>
				<form:input type="text" path="maxLength" id="lengthMag"></form:input>
			</div>
		</div>
		<div class="form-group">
			<label for="year" class="col-md-2">Year</label>
			<div class="col-md-10">
				<form:input type="text" path="year" id="year"></form:input>
			</div>
		</div>
		<button type="submit" name="action" value="search"
			class="btn btn-default">Search</button>

	</form:form>
</div>

<div class="row">
	<c:forEach var="listValue" items="${lists}">
		<li>${listValue}</li>
	</c:forEach>
	<c:if test="${tableResult}">
		<table class="table table-bordered">
			<tr>
				<th>#</th>
				<th>Title</th>
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
					<td>${movie.titoloItaliano}(${movie.title})</td>
					<td>${movie.year}</td>
					<td><c:forEach var="genere" items="${movie.genre}">
					${genere.desGenre}
					</c:forEach></td>
					<td><c:forEach var="regista" items="${movie.directors}">
				${regista.name}
					</c:forEach></td>
					<td><c:forEach var="attore" items="${movie.actors}">
				${attore.name}<br>
						</c:forEach></td>
					<td>Buttons</td>
				</tr>
			</c:forEach>
		</table>

	</c:if>
</div>