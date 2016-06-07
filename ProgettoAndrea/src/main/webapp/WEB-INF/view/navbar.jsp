<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<c:url value="/searchMovieResult" var="searchUrl" />


<nav class="navbar navbar-inverse navbar-fixed-top app-navbar">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#navbar-collapse-main">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>


			<button class="btn btn-small navbar-btn navbar-btn-avitar"
				data-toggle="popover" data-original-title="" title="">
				<img class="img-circle"
					src="${context}/assets/images/movieIcon1.png">
			</button>

		</div>
		<div class="navbar-collapse collapse" id="navbar-collapse-main">

			<ul class="nav navbar-nav hidden-xs">
				<li class="active"><a
					href="<%=request.getContextPath()%>/goHome">Home</a></li>
				<li><a href="<%=request.getContextPath()%>/searchMovie">Search
						Movie</a></li>
				<li><a href="<%=request.getContextPath()%>/guidaTV">Guida tv</a></li>
				<li><a>My filmoteque</a></li>
				<li><a>Amici</a></li>
				<li><a>Affini</a></li>


			</ul>

			<ul class="nav navbar-nav navbar-right m-r-0 hidden-xs">
				<li><a class="app-notifications"
					href="notifications/index.html"> <span class="icon icon-bell"></span>
				</a></li>
				<li>
					<button class="btn btn-default navbar-btn navbar-btn-avitar"
						data-toggle="popover" data-original-title="" title="">
						<img class="img-circle"
							src="${context}/assets/images/avatar-dhg.png">
					</button>
				</li>
			</ul>

			<form class="navbar-form navbar-right app-search" role="search" action="${searchUrl}">
				<div class="form-group">
					<input type="text" name="movieTitle" class="form-control" data-action="grow"
						placeholder="Taxi Driver" value="Taxi Driver">
					<input type="hidden" name="onlytext" value="onlytext">
				</div>
			</form>


			<ul class="nav navbar-nav hidden">
				<li><a href="#" data-action="growl">Growl</a></li>
				<li><a href="login/index.html">Logout</a></li>
			</ul>
		</div>
	</div>
</nav>