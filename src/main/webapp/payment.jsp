<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Payment - IntelliPrime Technology</title>
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
			<%
				Object amountAttr = request.getAttribute("amount");
				int amountValue = 0;
				String amountRupees = "0";
				if (amountAttr instanceof Number) {
					amountValue = ((Number) amountAttr).intValue();
					amountRupees = String.valueOf(amountValue / 100);
				} else if (amountAttr != null) {
					try {
						amountValue = Integer.parseInt(amountAttr.toString());
						amountRupees = String.valueOf(amountValue / 100);
					} catch (NumberFormatException ex) {
						amountValue = 0;
					}
				}
			%>
			<h2 class="text-center">Complete Payment</h2>
			<p>Course: <%= request.getAttribute("course") %></p>
			<p>Name: <%= request.getAttribute("name") %></p>
			<p>Email: <%= request.getAttribute("email") %></p>
			<div class="form-group">
				<label>Amount (INR)</label>
				<p class="form-control-static"><strong><%= amountRupees %></strong></p>
			</div>
			<button id="rzp-button1" class="btn btn-primary btn-block">Pay Now</button>
		</div>
	</div>
</div>

<!-- Razorpay Checkout Script -->
<script src="https://checkout.razorpay.com/v1/checkout.js"></script>
<script>
function buildRazorpayOptions(amount, orderId) {
    return {
        "key": "<%= request.getAttribute("key_id") %>",
        "amount": amount,
        "currency": "INR",
        "name": "IntelliPrime Technology",
        "description": "AI Course Enrollment",
        "order_id": orderId,
        "handler": function (response){
            window.location.href = "payment-success.jsp?payment_id=" + response.razorpay_payment_id + "&order_id=" + response.razorpay_order_id + "&signature=" + response.razorpay_signature;
        },
        "modal": {
            "ondismiss": function(){
                window.location.href = "payment-failure.jsp";
            }
        },
        "prefill": {
            "name": "<%= request.getAttribute("name") %>",
            "email": "<%= request.getAttribute("email") %>"
        },
        "theme": {
            "color": "#3399cc"
        }
    };
}

var currentOrderId = "<%= request.getAttribute("order_id") %>";
var currentAmount = parseInt('<%= request.getAttribute("amount") %>', 10);
var rzp1 = new Razorpay(buildRazorpayOptions(currentAmount, currentOrderId));

document.getElementById('rzp-button1').onclick = function(e) {
    e.preventDefault();
    rzp1.open();
};
</script>

<!-- JavaScript Libraries -->
<script src="lib/jquery/jquery.min.js"></script>
<script src="lib/bootstrap/js/bootstrap.min.js"></script>

</body>
</html>