<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" 
    import="com.km.util.url.UrlUtil"%>
<!DOCTYPE html>

<%@ include file="../common/taglib.jsp"%>

<html>
<head>
   <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   <title>实习经费管理系统</title>
   <meta http-equiv="X-UA-Compatible" content="IE=edge">
   <meta content="width=device-width, initial-scale=1.0" name="viewport" />
   <meta content="" name="description" />
   <meta content="" name="author" />
   <meta name="MobileOptimized" content="320">
   
   <!-- 引入样式文件 -->
   <%@ include file="../common/importCss.jsp"%>
   
   
   
  <!--  <link rel="shortcut icon" href="favicon.ico" /> -->
</head>
<!-- 结束头部 -->

<!-- 开始body -->
<body class="page-header-fixed">
   
    <!-- 头部 -->
   <%@ include file="../common/pageHeader.jsp"%>
   
   <div class="clearfix"></div>
   
   <!-- 开始内容 -->
   <div class="page-container">
      
      <!-- 侧栏菜单 -->
      <%@ include file="../common/sideBarMenu.jsp"%>
      
      <!-- 内容显示页面开始 -->
      <div class="page-content">
         
         <!-- 风格设定 -->
         <%-- <%@ include file="../common/styleSet.jsp"%> --%>
           
         <!-- 开始页面标题-->
         <div class="row">
            <div class="col-md-12">
               
               <!-- 开始页面的标题和面包屑-->
               <h3 class="page-title">
                  	专业实习计划 <small>中心</small>
               </h3>
               <ul class="page-breadcrumb breadcrumb">
                  <li>
                     <i class="icon-home"></i>
                     <a href="<c:url value='/home/index'/>">首页</a> 
                     <i class="icon-angle-right"></i>
                  </li>
                  <li>
                     <span>${requestScope.permissionMenu.rootName}</span>
                     <i class="icon-angle-right"></i>
                  </li>
                   <li>
						<span>${requestScope.permissionMenu.subName}</span>
					</li>
               </ul>
               <!-- 结束页面的标题和面包屑-->
               
            </div>
         </div>
         <!-- 结束页面头部-->
         
         <!-- <div class="clearfix"></div> -->
         
        <!--  开始页面内容 -->
          <div class="row">
            <div class="col-md-12">
            
				<div class="portlet box light-grey">
				   <div class="portlet-title">
					  <div class="caption"><i class="icon-search"></i>学生检索</div>
				   </div>
				   <div class="portlet-body form">
					  <!-- form 开始-->
					  <form:form modelAttribute="searchModel" class="form-horizontal" method="GET">
						 <div class="form-body">
							<div class="row">						
							    <div class="col-md-4">
								  <div class="form-group">
									 <label class="control-label col-md-3">专业</label>
									 <div class="col-md-9">
										<form:select path="major.pkuid" class="form-control">  
							                <option value="">请选择</option>  
							                <form:options items="${selectMajorDataSource}"/>  
						           	 	</form:select>
									 </div>
								  </div>
							   </div>							   
							   <div class="col-md-4">
								  <div class="form-group">
									 <label class="control-label col-md-3">班级</label>
									 <div class="col-md-9">
										<form:select path="stuClass.pkuid" class="form-control">  
							                <option value="">请选择</option>  
							                <form:options items="${selectStuClassDataSource}"/>  
						           	 	</form:select>
									 </div>
								  </div>
							   </div>						   
							   <div class="col-md-4">
								  <div class="form-group">
									 <label class="control-label col-md-3">入学年份</label>
									 <div class="col-md-9">
										<form:input path="stuYear" type="number" min="2012" step="1" class="form-control placeholder-no-fix" autocomplete="off" placeholder="入学年份"/>
									 </div>
								  </div>
							   </div>							   							   
							</div>
							 <div class="form-actions">
								<div class="row">
								   <div class="col-md-12">
									  <div class="col-md-offset-5">
										 <button type="submit" class="btn btn-success">搜索</button>                            
									  </div>
								   </div>
								</div>
						 	</div>
						 </div>
						 
					  </form:form>
					  <!-- 结束form-->                
				   </div>
				</div>
				
				<div class="portlet box light-grey">
				   <div class="portlet-title">
					  <div class="caption"><i class="glyphicon glyphicon-file"></i>从Excel新增学生</div>
				   </div>
				   <div class="portlet-body form">
					  <!-- form 开始-->
					  <form:form   action='/internship/student/importstudent' class="form-horizontal" enctype="multipart/form-data" method="POST">
						 <div class="form-body">
							<div class="row">
							 	<div class="col-md-9">						
							   		<input id="file-myup" class="file" type="file" name="upfile" multiple data-min-file-count="1">  
						 		</div>
						 	</div>
						 </div>
						 
					  </form:form>
					  <!-- 结束form-->                
				   </div>
				</div>
				
               <!-- 开始表单-->
               <div class="portlet box light-grey">
                  <div class="portlet-title">
                     <div class="caption"><i class="icon-table"></i>${requestScope.permissionMenu.curName}</div>
                  </div>
                  <div class="portlet-body">
                     <div class="table-toolbar"></div>
                     <div class="dataTables_wrapper form-inline" role="grid">
	                     <div class="table-scrollable">
		                     <table class="table table-striped table-bordered table-hover" id="data-table" >
		                        <thead>
		                           <tr>
		                              <th class="table-checkbox"><input type="checkbox" class="group-checkable"/></th>
		                              <th >学号</th>
		                              <th >真实姓名</th>
		                              <th >电话</th>
		                              <th >电子邮件</th>
		                              <th >专业</th>
		                              <th >班级</th>
		                              <th >学年</th>
		                           </tr>
		                        </thead>
		                        <tbody>
		                        	<c:forEach items="${contentModel.items}" var="item">
							        <tr class="odd gradeX">
							        	<td class="check_cell">
									        <input type="checkbox" class="checkboxes" name="Id" value="${ item.pkuid }" />
									    </td>
							            <td>${ item.stuNo }</td>
							            <td>${ item.realName }</td>
							            <td>${ item.telphone }</td>   
							            <td>${ item.email }</td>
							            <td>${ item.major.name }</td>
							            <td>${ item.stuClass.name }</td>
							            <td>${ item.stuYear }</td>
							        </tr>
							        </c:forEach>
		                        </tbody>
		                     </table>
	                     </div>
	                     <c:import url = "../common/paging.jsp">
	        				<c:param name="pageModelName" value="contentModel"/>
	        				<c:param name="urlAddress" value="/student/list"/>
	       				 </c:import>
       				 </div>
                  </div>
               </div>
               <!-- 结束表单-->
               
            </div>
         </div>
    	<!--  结束页面内容 -->
      </div>
      <!-- 结束页面 -->
      
   </div>
   <!-- 结束页面内容 -->
   
   <!-- 页脚 -->
   <%@ include file="../common/pageFooter.jsp"%>
   
   <!-- 引入js文件 -->
   <%@ include file="../common/importJs.jsp"%>
   
   
	<% 
		Object requestUrl = request.getAttribute("requestUrl");
		Object requestQuery = request.getAttribute("requestQuery");
	%>
   <script type="text/javascript">
   $("#file-myup").fileinput({
		showPreview:false,
       allowedFileExtensions : ['xlsx', 'xls'],
   });
   $(function() {   
       App.init();
      
       
       $("#data-table").tableManaged();
       
       $(".table-toolbar").toolbarLite({
           items: [
              <%--  { link: true, display: " 新建", css: "glyphicon glyphicon-log-in", showIcon: true, url: "<%=UrlUtil.resolveWithReturnUrl("/student/add", requestUrl, requestQuery, pageContext)%>" },
			   
               { splitter: true }, 
               { link: true, display: "编辑", css: "icon-edit", showIcon: true, url: "<%=UrlUtil.resolveWithReturnUrl("/student/edit/{0}", requestUrl, requestQuery, pageContext)%>", 
                  	selector: "#data-table .checkboxes", mustSelect: "请先选择数据！", singleSelect: "该操作只支持单选！"},
               { splitter: true },  --%>
               { link: true, display: "删除", css: "icon-trash", showIcon: true, url: "<%=UrlUtil.resolveWithReturnUrl("/student/delete/{0}", requestUrl, requestQuery, pageContext)%>", 
                 	 selector: "#data-table .checkboxes", mustSelect: "请先选择数据！",confirm: "确认删除所选数据吗？"}
           ]
       });
    });
   </script>

</body>
</html>