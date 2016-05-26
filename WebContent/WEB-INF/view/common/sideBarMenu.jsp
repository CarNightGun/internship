<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- 开始侧栏 -->
<div class="page-sidebar navbar-collapse collapse">

	<!-- 开始侧栏菜单 -->
	<ul class="page-sidebar-menu">
		<li>
			<!-- 侧边栏显示开关按钮开始 -->
			<div class="sidebar-toggler"></div>
			<div class="clearfix"></div> <!-- 侧边栏显示开关按钮结束 -->
		</li>

			<c:forEach items="${sessionScope.userAuth.userRole.authorityMenus}" var="item" varStatus="status">
				<c:choose>
					<c:when test="${status.first}">
						<c:choose>
							<c:when test="${item.pkuid eq requestScope.permissionMenu.rootPkuid}">
								<li class="start active">
							</c:when>
							<c:otherwise>
								<li class="start">
							</c:otherwise>
						</c:choose>
					</c:when>
					<c:otherwise>
						<c:choose>
							<c:when test="${item.pkuid eq requestScope.permissionMenu.rootPkuid}">
								<li class="active">
							</c:when>
							<c:otherwise>
								<li class="">
							</c:otherwise>
						</c:choose>
					</c:otherwise>
				</c:choose>
				<a href="javascript:;"> 
					<i class='${ empty item.itemIcon?"icon-list": item.itemIcon}'></i> 
					<span class="title">${ item.name }</span> 
					<span class="arrow "></span>
				</a>
				
				<c:forEach items="${item.childrens}" var="subItem" varStatus="subStatus">
					<c:if test="${subStatus.first}">
						<ul class="sub-menu">
					</c:if>
	
					<c:choose>
						<c:when test="${subItem.pkuid eq requestScope.permissionMenu.subPkuid}">
							<li class="active">
						</c:when>
						<c:otherwise>
							<li >
						</c:otherwise>
					</c:choose>
								<a href="<c:url value='${ subItem.url }'/>">${ subItem.name }</a>
							</li>
					<c:if test="${subStatus.last}">
						</ul>
					</c:if>
				</c:forEach>
				</li>
			</c:forEach>

			<li class="last">
				<a href="<c:url value = '/user/loginout'/>"> 
					<i class="icon-off"></i>
					<span class="title">注销登录</span>
				</a>
			</li>
	</ul>
	<!-- 结束侧边栏按钮 -->
</div>
<!-- 结束侧边栏 -->