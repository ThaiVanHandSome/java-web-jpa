<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Category</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
	crossorigin="anonymous">
</head>
<body>
	<div class="">
		<div
			class="w-100 fs-4 fw-700 text-center text-warning bg-secondary pt-1"
			style="height: 46px;">Quản lí Category</div>
		<div class="row mt-4">
			<div class="col col-lg-3 py-1 px-4">
				<form action="/admin-category" method="post"
					enctype="multipart/form-data">
					<div class="form-group">
						<label>Category Name</label> <input name="categoryName"
							id="CategoryName" value="${category.getCategoryName() }"
							type="text" class="form-control"
							placeholder="Enter category name..." />
					</div>
					<div class="form-group">
						<label>Category Icon</label> <input name="icon" id="icon"
							value="${category.getIcon() }" type="file" class="form-control" />
					</div>
					<div
						class="w-100 d-flex align-items-center justify-content-between mt-3">
						<button type="submit" class="btn btn-primary"
							formaction="<c:url value="/admin-category/create" />">Create</button>
						<button class="btn btn-warning"
							formaction="<c:url value="/admin-category/update?categoryid=${category.getCategoryID() }" />">Update</button>
						<button class="btn btn-danger"
							formaction="<c:url value="/admin-category/delete?categoryid=${category.getCategoryID() }" />">Delete</button>
						<button class="btn btn-success"
							formaction="<c:url value="/admin-category/reset" />">Reset</button>
					</div>
				</form>
			</div>
			<div class="col col-lg-9">
				<table class="table table-striped table-bordered table-hover">
					<thead>
						<th scope="col">Ảnh đại diện</th>
						<th scope="col">Tên danh mục</th>
						<th scope="col">Hành động</th>
					</thead>
					<tbody>
						<c:forEach var="item" items="${categories }">
							<tr>
								<td><c:url
										value="/image?fname=category/${item.getIcon() != null ? item.getIcon() : 'uploads/abc.jpg' }"
										var="imgUrl" /> <img width="50px" height="50px"
									src="${imgUrl }" /></td>
								<td>${item.getCategoryName() }</td>
								<td><a
									href="<c:url value="/admin-category/edit?categoryid=${item.getCategoryID() }"/>">Edit</a>
									|| <a
									href="<c:url value="/admin-category/delete?categoryid=${item.getCategoryID() }"/>">Delete</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>
</html>