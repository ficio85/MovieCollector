
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

	<ul class="list-group media-list media-list-stream">

				<li class="media list-group-item p-a">
					<div class="input-group">
						<input type="text" class="form-control" placeholder="Message">
						<div class="input-group-btn">
							<button type="button" class="btn btn-default">
								<span class="icon icon-camera"></span>
							</button>
						</div>
					</div>
				</li>
				<li class="media list-group-item p-a">
					<c:import url="home/detailSocialInteration.jsp" />
					
				</li>
				<li class="media list-group-item p-a">
									<c:import url="home/detailMovieSuggested.jsp" />
				
				</li>


				
	</ul>