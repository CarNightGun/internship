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
   
   
   <!-- 
   <link rel="shortcut icon" href="favicon.ico" /> -->
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
                  	用户信息 <small>中心</small>
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
            
               <!-- 开始表单-->
               <div class="portlet box light-grey">
                  <div class="portlet-title">
                     <div class="caption"><i class="icon-table"></i>${requestScope.permissionMenu.curName}</div>
                  </div>
                  <div class="portlet-body form">
                     <form:form modelAttribute="contentModel" class="form-horizontal" enctype="multipart/form-data" method="POST">
                        <div class="form-body">
                        <form:hidden path="pkuid"/>
                           <div class="form-group">
                              <label  class="col-md-2 control-label">用户名</label>
                              <div class="col-md-10">
	                              <p class="form-control-static">
	                                 	${contentModel.accountName }
	                               </p>
                              
                              </div>
                           </div> 
                        <div class="form-group">
                              <label  class="col-md-2 control-label">名称</label>
                              <div class="col-md-10">
                                 <form:input path="name" required="required" autofocus="autofocus" class="form-control" placeholder="名称"/><br/>
                              </div>
                           </div> 
                           <div class="form-group">
                              <label  class="col-md-2 control-label">电话</label>
                              <div class="col-md-10">
                                 <form:input path="phone" type="number" required="required" class="form-control" placeholder="电话"/><br/>
                              </div>
                           </div>  
                           <div class="form-group">
                              <label  class="col-md-2 control-label">性别</label>
                              <div class="col-md-10">
                              <label class="radio-inline">
 									<form:radiobutton path="sex" value="0"/>未知
								</label>
								<label class="radio-inline">
 									<form:radiobutton path="sex" value="1"/>男 
								</label>
								<label class="radio-inline">
 									<form:radiobutton path="sex" value="2"/>女 
								</label>
 
                              </div>
                           </div>
                           <div class="form-group">
                              <label  class="col-md-2 control-label">所属机构</label>
                              <div class="col-md-10">
                                 <p class="form-control-static">
                                 	${contentModel.organization.name == null ? "系统中心" : contentModel.organization.name }
                                 </p>
                              </div>
                           </div> 
                           <div class="form-group">
                              <label  class="col-md-2 control-label">角色</label>
                              <div class="col-md-10">
                               	<p class="form-control-static">
                                 	${contentModel.role.name }
                                 </p>
                              </div>
                           </div> 
                           <div class="form-group">
                              <label  class="col-md-2 control-label">是否已启用</label>
                              <div class="col-md-10">
                                 <p class="form-control-static">
                                 	${contentModel.audit == true ? "是" : "否"}
                                 </p>
                              </div>
                           </div> 
                           <div class="form-group">
                              <label  class="col-md-2 control-label">邮件</label>
                              <div class="col-md-10">
                                 <form:input path="email" type="email" min="0" step="0.01" required="required" class="form-control" /><br/>
                              </div>
                           </div> 
                           <div class="form-group">
                              <label  class="col-md-2 control-label">真实姓名</label>
                              <div class="col-md-10">
                                 <form:input path="realName" minlength ="3" required="required" class="form-control" /><br/>
                              </div>
                           </div> 
                           <div class="form-group">
                              <label  class="col-md-2 control-label">密码</label>
                              <div class="col-md-10">
                                 <form:input path="password" type="password" minlength ="3" required="required" class="form-control"/><br/>
                              </div>
                           </div> 
                           
                           <div class="form-group">
                              <label  class="col-md-2 control-label">头像</label>
                              <div class="col-md-10">
                                 <input type="file" name="myfile"  class="form-control" accept="image/png,image/gif,image/jpeg" /> 
                              </div>
                           </div> 
                                                                                   
                        </div>
                        <div class="form-actions fluid">
                           <div class="col-md-offset-6 col-md-6">
                              <button type="submit" class="btn btn-success">保存</button>                             
                           </div>
                        </div>
                     </form:form>
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
   
   
   <script type="text/javascript">
   $(function() {   
       App.init();
       
 
   </script>

</body>
</html>