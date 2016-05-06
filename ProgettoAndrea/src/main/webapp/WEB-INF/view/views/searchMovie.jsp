<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<c:url value="/movie/searchMovieResult" var="actionUrl" />


<div class="row">
	<form:form class="well form-horizontal" id="search-by-t+
	itle-form"
		action="${actionUrl}" modelAttribute="searchMovieForm">
		<div class="form-group">
			<label for="actors" class="col-md-2 control-label">Genre: </label>
			<div class="col-md-10">
				<form:select path="genere" class="form-control col-sm-2">
					<form:option value="">--Select Genre--</form:option>
					<form:options items="${generiList}" itemValue="codGenre"
						itemLabel="desGenre" />
				</form:select>
			</div>
		</div>
		<div class="form-group ui-widget ">
			<label for="actors" class="col-md-2 control-label">Actors: </label>
			<div class="col-md-10">
				<form:input type="text"
					class="autoCompleteClass form-control col-sm-2"
					data-servlet="/loadActors" path="actor" id="actors"></form:input>
			</div>
		</div>
		<div class="form-group ui-widget">
			<label for="directors" class="col-md-2 control-label">Directors:
			</label>
			<div class="col-md-10">
				<form:input type="text"
					class="autoCompleteClass form-control col-sm-2"
					data-servlet="/loadDirectors" path="director" id="directors"></form:input>
			</div>
		</div>
		<div class="form-group">
			<label for="length" class="col-md-2 control-label">Length </label>
			<div class="col-md-10">
				<form:input type="text" class="form-control col-sm-2"
					path="minLength" id="lengthMin"></form:input>
				<form:input type="text" class="form-control col-sm-2"
					path="maxLength" id="lengthMag"></form:input>
			</div>
		</div>
		<div class="form-group">
			<label for="year" class="col-md-2 control-label">Year</label>
			<div class="col-md-10">
				<form:input type="text" path="year" id="year"
					class="form-control col-sm-2"></form:input>
			</div>
		</div>
		<div class="form-group">
			<div class="col-md-2 col-md-offset-2">
				<button type="submit" name="action" value="search"
					class="btn btn-default btn-info">Search</button>
			</div>
		</div>
	</form:form>
</div>

<c:import url="errors.jsp" />
<c:import url="views/resultMovies.jsp" />

<div class="row">
	<div class="col-md-2"></div>
	<div class="col-md-6">
		<ul class="list-group media-list media-list-stream">
			<c:forEach var="movie" items="${listMovies}">

				<li class="media well backgroundtablewhite">
					<div class="row">
						<h4>${movie.titoloItaliano}(${movie.title})</h4>
					</div>
					<div class="row">
						<div class="col-md-4">
							<img class="img-responsive img-thumbnail"
								style="width: 140px; height: 180px;" src="${movie.poster}">
						</div>
						<div class="col-md-6">
							<div class="row">
								<ul>
									<li>
										<div class="row">
											<div class="col-md-2">
												<strong>Anno</strong>
											</div>
											<div class="col-md-4">: ${movie.year}</div>
										</div>
									</li>
									<li><strong>Regista:</strong> <c:forEach var="regista"
											items="${movie.directors}">
										${regista.name}
									</c:forEach></li>
									<li><strong>Genere:</strong> <c:forEach var="genere"
											items="${movie.genre}">
											${genere.desGenre}
									</c:forEach></li>
									
										<div class="row">
										
											<div class="col-md-2">
												<strong>Attori:</strong>
											</div>
											<div class="col-md-4">
												<c:forEach var="attore" items="${movie.actors}">
												${attore.name}<br>
												</c:forEach>
											</div>
										</div>
									</li>

								</ul>
							</div>
							<div class="row">
								Rate <span class="icon icon-thumbs-up"></span>
							</div>
							<div class="row">Modifica Film</div>

						</div>
					</div>
				</li>
			</c:forEach>
		</ul>
	</div>
	<div class="col-md-2"></div>
</div>