<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="row">
	<div class="col-md-2"></div>
	<div class="col-md-6">
		<ul class="list-group media-list media-list-stream">
			<c:forEach var="movie" items="${listMovies}">
				<li class="media list-group-item p-a">
					<div class="row">
						<h4>${movie.titoloItaliano}(${movie.title})</h4>
					</div>
					<div class="row">
						<div class="col-md-4">
							<img class="img-responsive img-thumbnail"
								style="width: 140px; height: 180px;" src="${movie.poster}">
						</div>
						<div class="col-md-6">
							
							<ul>
								<li>
									<div class="row">
										<div class="col-md-2">
											<strong>Anno</strong>
										</div>
										<div class="col-md-4">: ${movie.year}</div>
									</div>
								</li>
								<li>
									<div class="row">
										<div class="col-md-2">
											<strong>Regista:</strong>
										</div>
										<div class="col-md-4">

											<c:forEach var="regista" items="${movie.directors}">
											${regista.name}
											</c:forEach>
										</div>
									</div>
								</li>
								<li>
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
								<li>
									<div class="row">
										<div class="col-md-2">
											<strong>Genere:</strong>
										</div>
										<div class="col-md-4">
											<c:forEach var="genere" items="${movie.genre}">
											${genere.desGenre}
											</c:forEach>
										</div>
									</div>
								</li>

							</ul>

						</div>
					</div>
				</li>
			</c:forEach>

		</ul>
	</div>



</div>
