<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<c:url value="/searchMovieResult" var="actionUrl" />

<div class="col-md-3"></div>
<div class="col-md-6 text-center">
	<form:form class="well form-horizontal p-t-md p-l-md"
		id="search-by-title-form" action="${actionUrl}"
		modelAttribute="searchMovieForm">
		<div class="form-group">
			<label for="actors" class="col-md-2 control-label">Genre: </label>
			<div class="col-md-6">
				<form:select path="genere" class="form-control ">
					<form:option value="">--Select Genre--</form:option>
					<form:options items="${generiList}" itemValue="codGenre"
						itemLabel="desGenre" />
				</form:select>

			</div>
			<div class="col-md-2 plusPadding">
				<button type="button" class="btn btn-info btn-sm addGenreClass"
					id="addGenre0">
					<span class="fa fa-plus fa-1x"></span>
				</button>
			</div>
		</div>
		<div id="replaceGenre"></div>
		<input type="hidden" name="contGenre" id="contGenre" value="0">
		<div id="unisciGeneri" class="form-group">
			
		</div>

		<div class="form-group ui-widget ">
			<label for="actors" class="col-md-2 control-label">Actors: </label>
			<div class="col-md-10">
				<form:input type="text" class="autoCompleteClass form-control"
					data-servlet="/loadActors" path="actor" id="actors"></form:input>
			</div>
		</div>
		<div class="form-group ui-widget">
			<label for="directors" class="col-md-2 control-label">Directors:
			</label>
			<div class="col-md-10">
				<form:input type="text" class="autoCompleteClass form-control"
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
			<input type="hidden" name="actionHidden" value="search">
				<button type="submit" name="action" value="search"
					class="btn btn-default btn-info">Search</button>
			</div>
		</div>
	</form:form>

</div>

<div class="col-md-3"></div>



