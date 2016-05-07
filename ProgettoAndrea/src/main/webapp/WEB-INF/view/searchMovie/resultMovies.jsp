<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="col-md-1"></div>
<div class="col-md-10">
	<ul class="list-group media-list media-list-stream">
		<c:forEach var="movie" items="${listMovies}">
			<li class="media list-group-item p-a">
				<div class="row p-b-md">
					<div class="col-md-8 movieTitle">
						${movie.titoloItaliano}(${movie.title})
					</div>
					<div class="col-md-4 text-right">
						<div class="label label-success">Cool</div>
						<div class="label label-info">Label Prova</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-4 p-l-md">
						<img class="img-responsive img-thumbnail"
							style="width: 140px; height: 180px;" src="${movie.poster}">
					</div>
					<div class="col-md-4">

						<div class="row">

							<div class="col-md-2">
								<strong>Anno:</strong>
							</div>
							<div class="col-md-6 p-l-md">${movie.year}</div>
						</div>

						<div class="row">
							<div class="col-md-2">
								<strong>Regista:</strong>
							</div>
							<div class="col-md-6 p-l-md">

								<c:forEach var="regista" items="${movie.directors}">
											${regista.name}
											</c:forEach>
							</div>
						</div>

						<div class="row">
							<div class="col-md-2">
								<strong>Attori:</strong>
							</div>
							<div class="col-md-6 p-l-md">
								<c:forEach var="attore" items="${movie.actors}">
												${attore.name}<br>
								</c:forEach>
							</div>
						</div>

						<div class="row">
							<div class="col-md-2">
								<strong>Genere:</strong>
							</div>
							<div class="col-md-6 p-l-md">
								<c:forEach var="genere" items="${movie.genre}">
											${genere.desGenre}
											</c:forEach>
							</div>
						</div>

					</div>
					<div class="col-md-4">
						<div class="row">Scheda Film</div>
						<div class="row">Rate minimo</div>
						<div class="row">Rate completo</div>


					</div>
				</div>
			</li>
		</c:forEach>

	</ul>
</div>

<div class="col-md-1"></div>


