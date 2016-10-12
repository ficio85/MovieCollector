<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<c:url value="/searchMovieResult" var="actionUrl" />

<div class="col-md-12">
	<form class="well form-horizontal p-t-md p-l-md"
		id="search-by-title-form" action="${actionUrl}" 
		>
		<div class="form-group">
		
			<label for="piattaforma" class="col-md-2 ">Piattaforma: </label>
			
			<div class="col-md-2">
				<select name='piattaforma' id="selectPiattaforma" class="guidaTvForm">
					<option value="${selected}" selected>${selected}</option>
					<c:forEach items="${listPiattaforme}" var="piattaforma">
							<option value="${piattaforma.codPiattaforma}">${piattaforma.desPiattaforma}</option>
					</c:forEach>
				</select> 
			</div>
			
			<label for="orario" class="col-md-2 ">Orario: </label>
			
			
			<div class="col-md-2">
				<select name='fascia' id="selectFascia" class="guidaTvForm">
					<option value="${selected}" selected>${selected}</option>
					<c:forEach items="${listFasce}" var="fascia">
							<option value="${fascia.codFascia}">${fascia.desFascia}</option>
					</c:forEach>
				</select> 
			</div>
			
			<label for="programma" class="col-md-2 ">Programma: </label>
			
			
			<div class="col-md-2">
				<select name='programma' id="selectProgramma" class="guidaTvForm">
					<option value="${selected}" selected>${selected}</option>
					<c:forEach items="${listTipoProgrammi}" var="programma">
							<option value="${programma.codProgramma}">${programma.desProgramma}</option>
					</c:forEach>
				</select> 
			</div>
			
			
		</div>

			
	</form>

</div>





