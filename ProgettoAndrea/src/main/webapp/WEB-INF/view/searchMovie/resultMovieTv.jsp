<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.util.MovieGeneratorUtil"%>


<div class="row" id="elencoMovie">
	<div class="col-md-1"></div>
	<div class="col-md-10">
		<ul class="list-group media-list media-list-stream">
			<c:forEach var="tvProgram" items="${listMovies}"
				varStatus="filmIndex">
				<c:set var="movie" value="${tvProgram.movie}" />
				<c:if test="${not empty movie.title}">
					
					<li class="media list-group-item p-a">

						<div class="row p-b-md">
							<div class="col-md-7 movieTitle">
								${movie.titoloItaliano}(${movie.title}) <span
									class="fa-stack fa-14x" id="starContainer"> <i
									class="fa fa-star fa-stack-2x"></i> <i
									class="fa fa-stack-1x star-text-render">${movie.rate}</i>
								</span>


							</div>
							<div class="col-md-3 text-right">${tvProgram.platform}
								${tvProgram.displayHour }</div>
							<div class="col-md-2 text-right p-r-md">
								<form action="${actionUrl}">
									<button class="btn btn-primary-outline btn-sm">Scheda
										Film</button>
									<input type="hidden" name="indexMovie"
										value="${movie.imdbKey} "> <input type="hidden"
										name="movieKey" value="${movie.movieKey} ">
								</form>
							</div>

							<div class="col-md-4 text-right"></div>
						</div>
						<div class="row">
							<div class="col-md-3 p-l-md">
								<div class="row text-center">
									<img class="img-responsive img-thumbnail"
										style="width: 140px; height: 180px;" src="${movie.poster}">
								</div>
								<div class="row text-center">
									<c:forEach var="label" items="${movie.labels}" varStatus="loop">

										<div
											class="label <%=MovieGeneratorUtil.getLabelRandomClass()%> lb-sm">${label.name}</div>

									</c:forEach>
								</div>
							</div>
							<div class="col-md-4 p-l-md">

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
							<div class="col-md-5">
								<c:url value="/detailMovie" var="actionUrl" />

								<div class="row">Rate social</div>
								<div class="row">

									<button type="button" data-index="${loop.index}" data-social
										class="btn btn-pill social btn-default btn-sm btn-default-outline">
										<span class="icon icon-thumbs-down"></span>
									</button>
									<button type="button" data-index="${loop.index}" data-social
										class="btn btn-pill social btn-default btn-sm  btn-default-outline">
										<span class="icon icon-emoji-sad"></span>
									</button>
									<button type="button" data-index="${loop.index}" data-social
										class="btn btn-pill social btn-default btn-sm btn-default-outline">
										<span class="icon icon-thumbs-up"></span>
									</button>
									<button type="button" data-index="${loop.index}" data-social
										class="btn btn-pill social btn-default btn-sm btn-default-outline">
										<span class="icon icon-star"></span>
									</button>
									<button type="button" data-index="${loop.index}" data-social
										class="btn btn-pill social btn-sm btn-default-outline">
										<span class="icon icon-heart"></span>
									</button>



								</div>

								<div class="row">
									<label for="input-2" class="control-label">Rate
										Completo</label>
									<div id="star-div${filmIndex.count}" class="ratingDisplay">

									</div>

								</div>
								<div class="row">
									<button type="submit" name="action" value="search"
										class="btn btn-default btn-info btn-sm">Submit Rate</button>
								</div>

							</div>
						</div>
					</li>
				</c:if>
				<c:if test="${empty movie.title}">
					<li class="media list-group-item p-a">
				
						<div class="row p-b-md">
								<div class="col-md-7 movieTitle">
									${tvProgram.titolo}
								</div>
								<div class="col-md-3 text-right">${tvProgram.platform}
									${tvProgram.displayHour }</div>
							
								<div class="col-md-4 text-right"></div>
							</div>
					</li>
				</c:if>
			</c:forEach>

		</ul>
	</div>

	<div class="col-md-1"></div>

</div>