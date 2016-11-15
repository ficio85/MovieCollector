<%@ page import="com.util.SessionUtil"%>


<div class="profile-header text-center" style="background-image: url(${context}/assets/images/iceland.jpg);">
  <div class="container">
    <div class="container-inner">
      <img class="img-circle media-object" src="../assets/img/avatar-dhg.png">
      <h3 class="profile-header-user"><%=SessionUtil.getCodPers(request) %></h3>
      <p class="profile-header-bio">
        Ciao…&nbsp;also.
      </p>
    </div>
  </div>

  <nav class="profile-header-nav">
    <ul class="nav nav-tabs">
      <li class="active">
        <a href="#">Filmography</a>
      </li>
      <li>
        <a href="#">Friends</a>
      </li>
      <li>
        <a href="#">Others</a>
      </li>
    </ul>
  </nav>
</div>