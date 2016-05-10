<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:url value="/detailMovie" var="actionUrl" />

<div class="row media list-group-item" id="elencoMovie">
	<div class="col-md-10">
		<div class="row p-b-md">
			<div class="col-md-8 movieTitle">
				${movie.titoloItaliano}(${movie.title}) di
				<c:forEach var="regista" items="${movie.directors}">
											${regista.name}				
										</c:forEach>
				anno :${movie.year};

			</div>
			<div class="col-md-4 ">
				<span class="fa-stack fa-lg">
				 <i class="fa icon icon-star fa-stack-2x"></i>
				  <i class="fa fa-flag fa-stack-1x fa-inverse"></i>
				</span>
			</div>
		</div>
		<div class="row">
			<div class="col-md-5 p-l-md">
				<div class="row">
					<img class="img-responsive img-thumbnail"
						style="width: 240px; height: 300px;" src="${movie.poster}">
				</div>
				<div class="row">
					<div class="label label-success">Cool</div>
					<div class="label label-info">Label Prova</div>
				</div>


			</div>
			<div class="col-md-7">
				<div class="row">
					<label for="input-2" class="control-label">Rate Completo</label> <input
						id="starinput" class="rating rating-loading" data-min="0"
						data-max="10" data-stars="10" data-step="0.1" data-size="xs">
				</div>
				<div class="row">
					<button type="submit" name="action" value="search"
						class="btn btn-default btn-info">Submit Rate</button>
				</div>
				<div class="row">
					<textarea class="form-control" id="exampleTextarea" rows="3"></textarea>
				</div>
			</div>
		</div>
		<div class="row">
			Attori:
			<c:forEach var="attore" items="${movie.actors}">
				<a>${attore.name}</a>
			</c:forEach>

		</div>
		<div class="row">Plot: ${movie.plot}</div>
		<div class="row">
			Genere:
			<c:forEach var="genere" items="${movie.genre}">
											${genere.desGenre}
	</c:forEach>
		</div>
		<div class="row">
			Sceneggiatori:
			<c:forEach var="writer" items="${movie.writers}">
											${writer.name}
			</c:forEach>
		</div>
		<div class="row">
			Lingue:
			<c:forEach var="language" items="${movie.languages}">
											${language.des}
			</c:forEach>
		</div>
	</div>



</div>
