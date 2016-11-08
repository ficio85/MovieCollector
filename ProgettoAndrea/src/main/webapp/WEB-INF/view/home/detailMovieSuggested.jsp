<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="row p-b-md" id="filmConsigliato">
	<c:if test="${isUserGuidaTv==1}">
		<div class="row">
			<div class="col-md-12">Il film consigliato di oggi con
				percentuale del 76% è </div>
		</div>
		<div class="row">
			<div class="col-md-4"></div>
			<div class="col-md-8"><h4>${movieSuggested.title }</h4></div>
	
		</div>
		<div class="row">
			<div class="col-md-12">
				<div class="row text-center">
					<img class="img-responsive img-thumbnail"
						style="width: 160px; height: 200px;" src="${movieSuggested.poster}">
				</div>
			</div>
		</div>
		<div class="row">
	
		</div>
		
		<div class="row">
			<div class="col-md-6">Consiglia agli amici</div>
			<div class="col-md-6">Dettagli Film</div>
		</div>
	</c:if>
	<c:if test="${isUserGuidaTv==0}">
		<div class="row">
			<div class="col-md-12">Il film consigliato di oggi è in caricamento attendere alcuni istanti...
				<i class="fa fa-spinner fa-pulse fa-2x fa-fw margin-bottom"></i>
				 </div>
		</div>
		
	</c:if>

</div>


	<div id="isUserGuidaTv" data-attr="${isUserGuidaTv}" ></div>
