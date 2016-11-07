<%@page import="com.dto.DirectorDTO"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page import="com.dto.MovieDTO"%>
<%@ page import="com.dto.LabelDTO"%>
<%@ page import="java.util.List"%>
<%@ page import="com.util.MovieGeneratorUtil"%>


<div class="row media list-group-item" id="">
	<div class="row p-b-md">
		<div class="col-md-8 movieTitle p-t-md">${director.name}</div>
		<%
			float rate = ((DirectorDTO) request.getAttribute("director")).getRate();
		
		%>

		<div class="col-md-4 p-r-lg text-center">

			<span class="fa-stack fa-3x" id="starContainer"> <i
				class="fa fa-star fa-stack-2x <%=MovieGeneratorUtil.getStarClass(rate)%>"></i>
				<i class="fa fa-stack-1x star-text-render">${director.rate}</i>
			</span>
		</div>
	</div>
	<div class="row"></div>
	<div class="row">
		<div class="col-md-5 p-l-md text-center">
			<div class="row">
				<img class="img-responsive img-thumbnail"
					style="width: 240px; height: 300px;" src="${director.images[0].src}">
			</div>
			<div class="row">
				<div class="label label-success lb-md">Cool</div>
				<div class="label label-info lb-md">Label Prova</div>
			</div>


		</div>
		<div class="col-md-7">
			<div class="row p-t-md">
				<label for="input-2" class="control-label ">Nome Completo</label>
			</div>
			<div class="row">${director.fullname}</div>
			<div class="row p-t-md">
				<label for="input-2" class="control-label ">Data Nascita</label>
			</div>
			<div class="row">${director.birthDate}</div>
			<div class="row p-t-md">
				<label for="input-2" class="control-label ">Luogo di Nascita</label>
			</div>
			<div class="row">${director.birthplace}</div>
			<div class="row">
				<label for="input-2" class="control-label">Rate Completo</label>
			</div>
			<div class="row">
				<div class="col-md-12">
					<input id="starinput-detail" class="rating rating-loading"
						data-min="0" data-max="10" data-stars="10" data-step="0.5"
						data-size="md">
				</div>

			</div>
			<div class="row">
				<div class="col-md-4">
					<input id="rateInt" name="minLength" class="form-control col-sm-3"
						type="text" value=""> <input id="rateDec" name="maxLength"
						class="form-control col-sm-3" type="text" value="">
				</div>
				<div class="col-md-8 text-left">
					<button type="submit" id="submitRateDirector" name="action" value="search"
						class="btn btn-default btn-sm btn-info">Submit Rate</button>
				</div>
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
				List<LabelDTO> labels = ((DirectorDTO) request.getAttribute("director")).getLabels();
				if (labels != null) {
			%>
			<div class="row">
				<div id="labelSpace">
					<%
						for (LabelDTO label : labels) {
					%>
					<div class="col-md-2 p-t-md classToCancel">

						<div
							class="label <%=MovieGeneratorUtil.getLabelRandomClass()%> lb-sm"><%=label.getName()%></div>
						<i class="icon icon-circle-with-cross removeLabel"></i>
					</div>
					<%
						}
					%>
				</div>
			</div>
			<%
				}
			%>
		</div>

	</div>

</div>

<div id="key" data-id="${director.name}"></div>
<div id="movieToParse" data-movie="${director.movie}"></div>
<div class="row media list-group-item" id="actorPage">
</div>