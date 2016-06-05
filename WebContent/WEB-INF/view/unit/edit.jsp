<%@page import="org.springframework.ui.Model"%>
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
   
   <style type="text/css">
   
   table { border-collapse: collapse; border-spacing: 0; }
   </style>
   
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
                  	实习经费计划 <small>中心</small>
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
                     <form:form modelAttribute="contentModel" class="form-horizontal" method="POST">
                        <div class="form-body">
                        <div class="form-group">
                        <form:hidden path="pkuid"/>
                              <label  class="col-md-2 control-label">实习单位名称</label>
                              <div class="col-md-10">
                                 <form:input path="unitName" required="required" autofocus="autofocus" class="form-control" placeholder="实习单位名称"/><br/>
                              </div>
                           </div> 
                           <div class="form-group">
                              <label  class="col-md-2 control-label">地址</label>
                              <div class="col-md-10">
                                 <form:textarea path="address" required="required" class="form-control" placeholder="地址"/><br/>
                              </div>
                           </div> 
                           <div class="form-group">
                              <label  class="col-md-2 control-label">负责人</label>
                              <div class="col-md-10">
                                 <form:input path="chargeName" required="required" class="form-control" placeholder="负责人姓名"/><br/>
                              </div>
                           </div>  
                           <div class="form-group">
                              <label  class="col-md-2 control-label">电话</label>
                              <div class="col-md-10">
                                 <form:input path="telphone" type="tel"  required="required" class="form-control" placeholder="电话" /><br/>
                              </div>
                           </div>
                           
                           <c:if test="${not empty contentModel.pkuid}">
                           <div class="form-group">
                              <label  class="col-md-2 control-label">学生</label>
                              <div class="col-md-10">
                               <div class="portlet box light-grey">
                                  <div class="portlet-body">
				                     <div class="table-toolbar"></div>
				                     <div class="dataTables_wrapper form-inline" role="grid">
					                     <div class="table-scrollable">
						                     <table class="table table-striped table-bordered table-hover" id="data-table">
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
						                        	<c:forEach items="${contentModel.students}" var="item" varStatus="vs">
											        <tr class="odd gradeX">
											        	<td class="check_cell">
													        <input type="checkbox" class="checkboxes" name="pkuid" value="${ item.pkuid }" />
													    </td><form:hidden path="students[${vs.index}].pkuid"/>
											            <td><form:input path="students[${vs.index}].stuNo" readonly="true" disabled="true" style="border:none" /></td>
											            <td><form:input path="students[${vs.index}].realName" readonly="true" disabled="true" style="border:none"/></td>
											            <td><form:input path="students[${vs.index}].telphone" readonly="true" disabled="true" style="border:none"/></td>   
											            <td><form:input path="students[${vs.index}].email" readonly="true" disabled="true" style="border:none"/></td>
											            <td><form:input path="students[${vs.index}].major.name" readonly="true" disabled="true" style="border:none"/></td>
											            <td><form:input path="students[${vs.index}].stuClass.name" readonly="true" disabled="true" style="border:none"/></td>
											            <td><form:input path="students[${vs.index}].stuYear" readonly="true" disabled="true" style="border:none"/></td>
											        </tr>
											        </c:forEach>
						                        </tbody>
						                     </table>
					                     </div>
					                     <%-- <c:import url = "../common/paging.jsp">
					        				<c:param name="pageModelName" value="contentModel"/>
					        				<c:param name="urlAddress" value="/unit/list"/>
					       				 </c:import> --%>
				       				 </div>
				                  </div>
                              </div>
                           </div>
                           </div>
                        </c:if>
                           
                        
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
   
   <% 
		Object requestUrl = request.getAttribute("requestUrl");
		Object requestQuery = request.getAttribute("requestQuery");
	%>
   <script type="text/javascript">
   $(function() {
   		App.init();
   		 
     });
   </script>
   <c:if test="${not empty contentModel.pkuid}">
   <script type="text/javascript">
   $(function() {
  		 
	   var inputs = $("td input[type=text]");
 		for(var i=0;i<inputs.length;i++){
 			var le = inputs[i].value.length*15;
 			if(le > 100){
 				le = inputs[i].value.length*8;
 			}
 			inputs.eq(i).css("width",le+'px');
 		}  		 

		$("#data-table").tableManaged();
      
      	$(".table-toolbar").toolbarLite({
          items: [
              { link: true, display: "添加学生", css: "icon-plus", showIcon: true, url: "<%=UrlUtil.resolveWithReturnUrl("/unit/studentList/{editId0}", requestUrl, requestQuery, pageContext)%>".replace("{editId0}",${contentModel.pkuid})  },
              
              { link: true, display: "删除", css: "icon-trash", showIcon: true, url: "<%=UrlUtil.resolveWithReturnUrl("/unit/removestudent/{0}", requestUrl, requestQuery, pageContext)%>", 
                selector: "#data-table .checkboxes", mustSelect: "请先选择数据！", confirm: "确认删除所选数据吗？"}
          ]
      	});
      
    });
   </script>
	</c:if>
</body>
</html>