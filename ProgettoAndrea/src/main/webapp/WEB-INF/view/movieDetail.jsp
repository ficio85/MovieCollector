<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page import="com.dto.MovieDTO"%>
<%@ page import="com.dto.LabelDTO"%>
<%@ page import="java.util.List"%>
<%@ page import="com.util.MovieGeneratorUtil"%>


<c:url value="/detailMovie" var="actionUrl" />

<div class="row media list-group-item" id="elencoMovie">
	<div class="row p-b-md">
		<div class="col-md-8 movieTitle p-t-md">
			${movie.titoloItaliano}(${movie.title}) di
			<c:forEach var="regista" items="${movie.directors}">
											${regista.name}				
										</c:forEach>
			(${movie.year});

		</div>
		<%
				float rate  = ((MovieDTO) request.getAttribute("movie")).getRate();
			%>

		<div class="col-md-4 p-r-lg text-center">

			<span class="fa-stack fa-3x" id="starContainer"> 
				<i class="fa fa-star fa-stack-2x <%= MovieGeneratorUtil.getStarClass(rate) %>"></i>
				<i class="fa fa-stack-1x star-text-render">${movie.rate}</i>
			</span>
		</div>
	</div>
	<div class="row">
	</div>
	<div class="row">
		<div class="col-md-5 p-l-md text-center">
			<div class="row">
				<img class="img-responsive img-thumbnail"
					style="width: 240px; height: 300px;" src="${movie.poster}">
			</div>
			<div class="row">
				<div class="label label-success lb-md">Cool</div>
				<div class="label label-info lb-md">Label Prova</div>
			</div>


		</div>
		<div class="col-md-7">

			<div class="row">
				<label for="input-2" class="control-label">Rate Completo</label>
			</div>
			<div class="row">
				<div class="col-md-12">
					<input id="starinput-detail" class="rating rating-loading"
						data-min="0" data-max="10" data-stars="10" data-step="0.5"
						data-size="xs">
				</div>

			</div>
			<div class="row">
				<div class="col-md-4">
					<input id="rateInt" name="minLength" class="form-control col-sm-3"
						type="text" value=""> <input id="rateDec" name="maxLength"
						class="form-control col-sm-3" type="text" value="">
				</div>
				<div class="col-md-8 text-left">
					<button type="submit" id="submitRate" name="action" value="search"
						class="btn btn-default btn-sm btn-info">Submit Rate</button>
				</div>
			</div>
			<div class="row p-t-md">
				<label for="input-2" class="control-label ">Recensione</label>
			</div>
			<div class="row">
				<textarea class="form-control" id="exampleTextarea" rows="3"></textarea>
			</div>
			<div class="row p-t-md">
				<label for="input-2" class="control-label ">Label</label>
			</div>
			<div class="row">
				<div class="col-md-6">
					<input type="text" class="form-control" id="labelText"
						name="label form-control ">
				</div>
				<div class="col-md-6">
					<button type="submit" name="action" value="createLabel"
						class="btn btn-default btn-sm btn-info" id="createLabel">Create
						Label</button>
					<button type="submit" name="action" value="submitLabels"
						class="btn btn-default btn-sm btn-info" id="submitLabels">Submit
						Labels</button>
				</div>
			</div>
			<%
				List<LabelDTO> labels = ((MovieDTO) request.getAttribute("movie")).getLabels();
			%>
			<div class="row">
				<div id="labelSpace">
					<%
						for (LabelDTO label : labels) {
					%>
					<div class="col-md-2 p-t-md classToCancel">

						<div
							class="label <%=MovieGeneratorUtil.getLabelRandomClass() %> lb-sm"><%= label.getName()%></div>
						<i class="icon icon-circle-with-cross removeLabel"></i>
					</div>
					<%
						}
					%>
				</div>
			</div>
		</div>

	</div>
	<div class="row p-t-md"></div>
	<div class="row p-l-md">
		<strong>Attori:</strong>
		<c:forEach var="attore" items="${movie.actors}">
			<a>${attore.name}</a>
		</c:forEach>

	</div>
	<div class="row p-l-md">
		<strong>Plot: </strong>${movie.plot}</div>
	<div class="row p-l-md">
		<strong> Genere: </strong>
		<c:forEach var="genere" items="${movie.genre}">
											${genere.desGenre}
	</c:forEach>
	</div>
	<div class="row p-l-md">
		<strong> Sceneggiatori: </strong>
		<c:forEach var="writer" items="${movie.writers}">
											${writer.name}
			</c:forEach>
	</div>
	<div class="row p-l-md">
		<strong> Lingue: </strong>
		<c:forEach var="language" items="${movie.languages}">
											${language.des}
			</c:forEach>
	</div>
</div>
<div class="row">
	<ul class="list-group media-list media-list-stream">

		<li class="media list-group-item p-a"><a class="media-left"
			href="#"> <img class="media-object img-circle"
				src="assets/img/avatar-fat.jpg">
		</a>
			<div class="media-body">
				<div class="media-body-text">
					<div class="media-heading">
						<small class="pull-right text-muted">12 min</small>
						<h5>ficio85</h5>
					</div>
					<p>Donec id elit non mi porta gravida at eget metus. Integer
						posuere erat a ante venenatis dapibus posuere velit aliquet. Cum
						sociis natoque penatibus et magnis dis parturient montes, nascetur
						ridiculus mus. Morbi leo risus, porta ac consectetur ac,
						vestibulum at eros. Lorem ipsum dolor sit amet, consectetur
						adipiscing elit.</p>
				</div>
			</div></li>
		<li class="media list-group-item p-a">
			<div class="input-group">
				<textarea id="areaMess" class="form-control" placeholder="Message"></textarea>
				<div class="input-group-btn">
					<button type="button" class="btn btn-default">
						<span class="icon icon-edit"></span>
					</button>
				</div>
			</div>
		</li>
	</ul>
</div>
<div id="key" data-id="${movie.movieKey}"></div>