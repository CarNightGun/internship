<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>实习经费管理系统</title>
<link rel="stylesheet" type="text/css"
	href="/internship/hlib/bootstrap/css/bootstrap.css">

<link rel="stylesheet" type="text/css"
	href="/internship/hsrc/css/theme.css">
<link rel="stylesheet"
	href="/internship/hlib/font-awesome/css/font-awesome.css">

<script src="/internship/hlib/jquery-1.8.1.min.js"
	type="text/javascript"></script>


<style type="text/css">
#line-chart {
	height: 300px;
	width: 800px;
	margin: 0px auto;
	margin-top: 1em;
}

.brand {
	font-family: georgia, serif;
}

.brand .first {
	color: #ccc;
	font-style: italic;
}

.brand .second {
	color: #fff;
	font-weight: bold;
}


</style>

</head>

<body>


	<div class="navbar">
		<div class="navbar-inner">
			<div class="container-fluid">
				<ul class="nav pull-right">

					<li id="fat-menu" class="dropdown"><a href="#" id="drop3"
						role="button" class="dropdown-toggle" data-toggle="dropdown">
							<i class="icon-user"></i> 空幕 <i class="icon-caret-down"></i>
					</a>

						<ul class="dropdown-menu">
							<li><a tabindex="-1" href="#">设置</a></li>
							<li class="divider"></li>
							<li><a tabindex="-1" href="sign-in.html">登出</a></li>
						</ul></li>

				</ul>
				<a class="brand" href="index.html"><span class="first">重庆理工大学</span>
					<span class="second">实习经费管理系统</span></a>
			</div>
		</div>
	</div>


	<div class="container-fluid">

		<div class="row-fluid">
			<div class="span3">
				<div class="sidebar-nav">
					<div class="nav-header" data-toggle="collapse"
						data-target="#dashboard-menu">
						<i class="icon-dashboard"></i>Dashboard
					</div>
					<ul id="dashboard-menu" class="nav nav-list collapse in">
						<li><a href="index.html">Home</a></li>
						<li><a href="users.html">Sample List</a></li>
						<li><a href="user.html">Sample Item</a></li>
						<li><a href="gallery.html">Gallery</a></li>
						<li><a href="calendar.html">Calendar</a></li>
						<li><a href="faq.html">Faq</a></li>
						<li><a href="help.html">Help</a></li>

					</ul>
					<div class="nav-header" data-toggle="collapse"
						data-target="#accounts-menu">
						<i class="icon-briefcase"></i>Account<span
							class="label label-info">+10</span>
					</div>
					<ul id="accounts-menu" class="nav nav-list collapse in">
						<li><a href="sign-in.html">Sign In</a></li>
						<li><a href="sign-up.html">Sign Up</a></li>
						<li><a href="reset-password.html">Reset Password</a></li>
					</ul>

					<div class="nav-header" data-toggle="collapse"
						data-target="#settings-menu">
						<i class="icon-exclamation-sign"></i>Error Pages
					</div>
					<ul id="settings-menu" class="nav nav-list collapse in">
						<li><a href="403.html">403 page</a></li>
						<li><a href="404.html">404 page</a></li>
						<li><a href="500.html">500 page</a></li>
						<li><a href="503.html">503 page</a></li>
					</ul>

					<div class="nav-header" data-toggle="collapse"
						data-target="#legal-menu">
						<i class="icon-legal"></i>Legal
					</div>
					<ul id="legal-menu" class="nav nav-list collapse in">
						<li><a href="privacy-policy.html">Privacy Policy</a></li>
						<li><a href="terms-and-conditions.html">Terms and
								Conditions</a></li>
					</ul>
				</div>
			</div>
			<div class="span9">
				<script type="text/javascript"
					src="/internship/hlib/jqplot/jquery.jqplot.min.js"></script>
				<script type="text/javascript" charset="utf-8"
					src="/internship/hsrc/js/graphDemo.js"></script>

				<div class="stats">
					<p class="stat">
						<span class="number">53</span>tickets
					</p>
					<p class="stat">
						<span class="number">27</span>tasks
					</p>
					<p class="stat">
						<span class="number">15</span>waiting
					</p>
				</div>
				<h1 class="page-title">Dashboard</h1>

				<div class="row-fluid">
					<div class="block">
						<p class="block-heading" data-toggle="collapse"
							data-target="#chart-container">Performance Chart</p>
						<div id="chart-container" class="block-body collapse in">
							<div id="line-chart"></div>
						</div>
					</div>
				</div>

				<div class="row-fluid">
					<div class="block span6">
						<div class="block-heading" data-toggle="collapse"
							data-target="#tablewidget">Users</div>
						<div id="tablewidget" class="block-body collapse in">
							<table class="table">
								<thead>
									<tr>
										<th>First Name</th>
										<th>Last Name</th>
										<th>Username</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>Mark</td>
										<td>Tompson</td>
										<td>the_mark7</td>
									</tr>
									<tr>
										<td>Ashley</td>
										<td>Jacobs</td>
										<td>ash11927</td>
									</tr>
									<tr>
										<td>Audrey</td>
										<td>Ann</td>
										<td>audann84</td>
									</tr>
									<tr>
										<td>John</td>
										<td>Robinson</td>
										<td>jr5527</td>
									</tr>
									<tr>
										<td>Aaron</td>
										<td>Butler</td>
										<td>aaron_butler</td>
									</tr>
									<tr>
										<td>Chris</td>
										<td>Albert</td>
										<td>cab79</td>
									</tr>
								</tbody>
							</table>
							<p>
								<a href="users.html">More...</a>
							</p>
						</div>
					</div>
					<div class="block span6">
						<div class="block-heading" data-toggle="collapse"
							data-target="#widget1container">Collapsible</div>
						<div id="widget1container" class="block-body collapse in">
							<h2>Using Ruby?</h2>
							<p>
								This template was developed with <a
									href="http://middlemanapp.com/" target="_blank">Middleman</a>
								and includes .erb layouts and views.
							</p>
							<p>All of the views you see here (sign in, sign up, users,
								etc) are already split up so you don't have to waste your time
								doing it yourself!</p>
							<p>The layout.erb file includes the header, footer, and side
								navigation and all of the views are broken out into their own
								files.</p>
							<p>If you aren't using Ruby, there is also a set of plain
								HTML files for each page, just like you would expect.</p>
						</div>
					</div>
				</div>

				<div class="row-fluid">
					<div class="block span6">
						<div class="block-heading" data-toggle="collapse"
							data-target="#widget2container">
							History<span class="label label-warning">+10</span>
						</div>
						<div id="widget2container" class="block-body collapse in">
							<table class="table">
								<tbody>
									<tr>
										<td>
											<p>
												<i class="icon-user"></i> Mark Otto
											</p>
										</td>
										<td>
											<p>Amount: $1,247</p>
										</td>
										<td>
											<p>Date: 7/19/2012</p> <a href="#">View Transaction</a>
										</td>
									</tr>
									<tr>
										<td>
											<p>
												<i class="icon-user"></i> Audrey Ann
											</p>
										</td>
										<td>
											<p>Amount: $2,793</p>
										</td>
										<td>
											<p>Date: 7/12/2012</p> <a href="#">View Transaction</a>
										</td>
									</tr>
									<tr>
										<td>
											<p>
												<i class="icon-user"></i> Mark Tompson
											</p>
										</td>
										<td>
											<p>Amount: $2,349</p>
										</td>
										<td>
											<p>Date: 3/10/2012</p> <a href="#">View Transaction</a>
										</td>
									</tr>
									<tr>
										<td>
											<p>
												<i class="icon-user"></i> Ashley Jacobs
											</p>
										</td>
										<td>
											<p>Amount: $1,192</p>
										</td>
										<td>
											<p>Date: 1/19/2012</p> <a href="#">View Transaction</a>
										</td>
									</tr>

								</tbody>
							</table>
						</div>
					</div>
					<div class="block span6">
						<p class="block-heading">Not Collapsible</p>
						<div class="block-body">
							<h2>Tip of the Day</h2>
							<p>Fava bean jícama seakale beetroot courgette shallot
								amaranth pea garbanzo carrot radicchio peanut leek pea sprouts
								arugula brussels sprout green bean. Spring onion broccoli
								chicory shallot winter purslane pumpkin gumbo cabbage squash
								beet greens lettuce celery. Gram zucchini swiss chard mustard
								burdock radish brussels sprout groundnut. Asparagus horseradish
								beet greens broccoli brussels sprout bitterleaf groundnut cress
								sweet pepper leek bok choy shallot celtuce scallion chickpea
								radish pea sprouts.</p>
							<p>
								<a class="btn btn-primary btn-large">Learn more &raquo;</a>
							</p>
						</div>
					</div>
				</div>

			</div>
		</div>


	</div>

	<footer>
		<hr>
		<p class="pull-right">
			<a href="" target="_blank">实习经费管理系统</a> by <a
				href="http://www.cqut.edu.com" target="_blank">重庆理工大学</a>
		</p>

		<p>
			&copy; 2016 <a href="">软件工程专业</a>
		</p>
	</footer>

	<script src="/internship/hlib/bootstrap/js/bootstrap.js"></script>

</body>
</html>