<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Enrollment Form - IntelliPrime Technology</title>
<meta content="width=device-width, initial-scale=1.0" name="viewport">

<!-- Bootstrap CSS File -->
<link href="lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<!-- Main Stylesheet File -->
<link href="css/style.css" rel="stylesheet">

</head>
<body>

<header>
	<!-- header-area start -->
	<div id="sticker" class="header-area">
		<div class="container">
			<div class="row">
				<div class="col-md-12 col-sm-12">
					<!-- Navigation -->
					<nav class="navbar navbar-default">
						<div class="navbar-header">
							<button type="button" class="navbar-toggle collapsed"
								data-toggle="collapse"
								data-target=".bs-example-navbar-collapse-1"
								aria-expanded="false">
								<span class="sr-only">Toggle navigation</span> <span
									class="icon-bar"></span> <span class="icon-bar"></span> <span
									class="icon-bar"></span>
							</button>
							<a class="navbar-brand page-scroll sticky-logo"
								href="index.html"> 
								 <picture>
									<source media="(max-width: 500px)" srcset="img/logo/ip_logo139_58.png">
									<source media="(max-width: 800px)" srcset="img/logo/ip_logo_180_49.png">
									<source media="(max-width: 1300px)" srcset="img/logo/ip_logo209_57.png">
									<source media="(min-width: 1301px)" srcset="img/logo/ip_logo_314_86.png">
									<img src="img/logo/ip_logo_314_86.png" alt="ip">
								</picture> 
							</a>
						</div>
						<div
							class="collapse navbar-collapse main-menu bs-example-navbar-collapse-1"
							id="navbar-example">
							<ul class="nav navbar-nav navbar-right">
								<li><a class="page-scroll" href="index.html">Home</a></li>
								<li><a class="page-scroll" href="#about">About</a></li>
								<li><a class="page-scroll" href="#services">Services</a></li>
								<li><a class="page-scroll" href="#contact">Contact</a></li>
							</ul>
						</div>
					</nav>
				</div>
			</div>
		</div>
	</div>
</header>

<div class="container" style="margin-top: 50px;">
	<div class="row">
		<div class="col-md-6 col-md-offset-3">
			<h2 class="text-center">Enrollment Form</h2>
			<%
				String selectedCourse = request.getParameter("course");
				if (selectedCourse == null) {
					selectedCourse = "Intro to AI"; // default
				}
			%>
			<p><strong>Selected Course:</strong> <%= selectedCourse %></p>
			<form action="confirmation.jsp" method="post">
				<input type="hidden" name="course" value="<%= selectedCourse %>">
				<div class="form-group">
					<label for="name">Name:</label>
					<input type="text" class="form-control" id="name" name="name" required>
				</div>
				<div class="form-group">
					<label for="email">Email:</label>
					<input type="email" class="form-control" id="email" name="email" required>
				</div>
				<div class="form-group">
					<label for="phone">Phone:</label>
					<input type="tel" class="form-control" id="phone" name="phone" required>
				</div>
				<button type="submit" class="btn btn-primary btn-block">Submit</button>
			</form>
		</div>
	</div>
</div>

<!-- JavaScript Libraries -->
<script src="lib/jquery/jquery.min.js"></script>
<script src="lib/bootstrap/js/bootstrap.min.js"></script>

</body>
</html>