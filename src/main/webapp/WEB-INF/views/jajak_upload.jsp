<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>자작툰 올리기 - 툰스토어</title>
<meta charset="UTF-8" />
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.5.0/css/all.css">
<link rel="stylesheet" href="${path}/resources/css/bootstrap.css" />
<link rel="stylesheet" href="${path}/resources/css/bootstrap-theme.css" />
<link rel="stylesheet" type="text/css"
	href="${path}/resources/css/style.css" />
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
</head>
<body>
	<jsp:include page="nav.jsp">
		<jsp:param name="name" value="value" />
	</jsp:include>
	<header>
		<div class="container">
			자작툰<br />올리기 <img src="${path}/resources/img/menu/jajak-upload.svg" />
		</div>
	</header>
	<section>
		<div class="container">
			<h1>
				<img src="${path}/resources/img/button/arrow.svg" /> 글쓰기
			</h1>
			<form method="post" action="jajak_upload_control"
				enctype="multipart/form-data">
				<div id="jajak-upload-form">
					<input type="text" name="bd_num" style="display: none;"> <input
						type="text" name="title" placeholder="제목" required /> <input
						type="hidden">
					<hr>
					<div class="toolbar">
						<div class="dropdown-fontsize">
							<span>H</span>
							<div class="dropdown-content-fontsize">
								<a href="" data-command='h1'>제목1</a> <a href=""
									data-command='h3'>제목2</a> <a href="" data-command='h2'>제목3</a>
								<a href="" data-command='p'>본문</a>
							</div>
						</div>
						<div class="dropdown-color">
							<span>A</span>
							<div class="dropdown-content-color">
								<input type="color" id="textcolor" />
							</div>
						</div>
						<a href="" data-command='bold'> <i class='fa fa-bold'></i>
						</a> <a href="" data-command='italic'> <i class='fa fa-italic'></i>
						</a> <a href="" data-command='underline'> <i
							class='fa fa-underline'></i>
						</a> <a href="" data-command='justifyLeft'> <i
							class='fa fa-align-left'></i>
						</a> <a href="" data-command='justifyCenter'> <i
							class='fa fa-align-center'></i>
						</a> <a href="" data-command='justifyRight'> <i
							class='fa fa-align-right'></i>
						</a> <a href="" data-command='justifyFull' style="margin-right: 8px;">
							<i class='fa fa-align-justify'></i>
						</a>
					</div>
					<textarea id="my-textarea" style="display: none" name="content"></textarea>
					<div class="editor" contenteditable="true"></div>
					<button type="submit" class="blue">확인</button>
				</div>
			</form>
		</div>
	</section>
	<jsp:include page="footer.jsp">
		<jsp:param name="name" value="value" />
	</jsp:include>
	<a href="#nav-container" id="top"><img
		src="${path}/resources/img/button/top.svg" /></a>
</body>
<script src="https://cdn.jsdelivr.net/jquery/3.2.1/jquery.min.js"></script>
<script src="${path}/resources/js/script.js">
	
</script>
<script src="${path}/resources/js/index.js"></script>
</html>
