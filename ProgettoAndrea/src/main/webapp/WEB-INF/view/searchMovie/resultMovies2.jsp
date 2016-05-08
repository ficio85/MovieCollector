<div class="col-md-1"></div>
	<div class="col-md-10">
		<ul class="list-group media-list media-list-stream">
			<c:forEach var="movie" items="${listMovies}" varStatus="loop">
				<li class="media list-group-item p-a">
					<div class="row p-b-md">
						<div class="col-md-8 movieTitle">
							${movie.titoloItaliano}(${movie.title})</div>
						<div class="col-md-4 text-right">
							<div class="label label-success">Cool</div>
							<div class="label label-info">Label Prova</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-3 p-l-md">
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
						<div class="col-md-5">
							<div class="row">Scheda Film</div>
							<div class="row">Rate social</div>
							<div class="row">

								<button type="button" data-index="${loop.index}" data-social
									class="btn btn-pill social btn-default btn-default-outline">
									<span class="icon icon-thumbs-down"></span>
								</button>
								<button type="button" data-index="${loop.index}" data-social
									class="btn btn-pill social btn-default btn-default-outline">
									<span class="icon icon-emoji-sad"></span>
								</button>
								<button type="button" data-index="${loop.index}" data-social
									class="btn btn-pill social btn-default btn-default-outline">
									<span class="icon icon-thumbs-up"></span>
								</button>
								<button type="button" data-index="${loop.index}" data-social
									class="btn btn-pill social btn-default btn-default-outline">
									<span class="icon icon-star"></span>
								</button>
								<button type="button" data-index="${loop.index}" data-social
									class="btn btn-pill social btn-default-outline">
									<span class="icon icon-heart"></span>
								</button>



							</div>

							<div class="row">
								<label for="input-2" class="control-label">Rate Completo</label>
								<input id="starinput-${loop.index}"
									class="rating rating-loading" data-min="0" data-max="10"
									data-stars="10" data-step="0.1" data-size="xs">
							</div>
							<div class="row">
								<button type="submit" name="action" value="search"
									class="btn btn-default btn-info">Submit Rate</button>
							</div>

						</div>
					</div>
				</li>
			</c:forEach>

		</ul>
	</div>

	<div class="col-md-1"></div>
