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
                  	实习经费报销 <small>中心</small>
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
					  <div class="caption"><i class="icon-search"></i>检索</div>
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
									 <label class="control-label col-md-3">专业计划</label>
									 <div class="col-md-9">
										 <form:select path="majorPlan.pkuid" class="form-control" >  
							                <option value="">请选择</option>  
							                <form:options items="${selectMajorPlanDataSource}"/>  
							           	 </form:select>
									 </div>
								  </div>
							   </div>						   
							   <div class="col-md-4">
								  <div class="form-group">
									 <label class="control-label col-md-3">审核状态</label>
									 <div class="col-md-9">
									 <form:select path="auditState" class="form-control" >  
							                <option value="">请选择</option>  
							                <form:options items="${selectAuditStateDataSource}"/>  
							           	 </form:select>
										
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
		                              <th >凭证编号</th>
		                              <th >审核状态</th>
		                              <th >凭证名称</th>
		                              <th >申请报销金额</th>
		                              <th >已报销金额</th>
		                              <th >未报销金额</th>
		                              <th >专业</th>
		                              <th >专业计划</th>
		                              <th >申请人</th>
		                              <th >申请时间</th>
		                              <th >最后审阅时间</th>
		                           </tr>
		                        </thead>
		                        <tbody>
		                        	<c:forEach items="${contentModel.items}" var="item">
							        <tr class="odd gradeX">
							        	<td class="check_cell">
									        <input type="checkbox" class="checkboxes" name="pkuid" value="${ item.pkuid }" />
									    </td>
									     <td>${ item.wipeCode }</td>
							            <td class="auditstate">${ item.auditState }</td>
							            <td>${ item.wipeCostName }</td>
							            <td>${ item.willWipedCost }</td>   
							            <td>${ item.hasWipedCost }</td>
							            <td>${ item.noWipedCost }</td>
							            <td>${ item.major.name }</td>
							            <td>${ item.majorPlan.majorPlanName }</td>
							            <td>${ item.applicant.name }</td>
							            <td>${ item.createTime.toLocaleString() }</td>
							            <td>${ item.updateTime.toLocaleString() }</td>
							        </tr>
							        </c:forEach>
		                        </tbody>
		                     </table>
	                     </div>
	                     <c:import url = "../common/paging.jsp">
	        				<c:param name="pageModelName" value="contentModel"/>
	        				<c:param name="urlAddress" value="/wipecost/list"/>
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
    
   $(function() {   
       App.init();
      
       
       $("#data-table").tableManaged();
       
       $(".table-toolbar").toolbarLite({
           items: [
               { link: true, display: "新建", css: "icon-plus", showIcon: true, url: "<%=UrlUtil.resolveWithReturnUrl("/wipecost/add", requestUrl, requestQuery, pageContext)%>" },
			   
               { splitter: true }, 
               { link: true, display: "编辑", css: "icon-edit", showIcon: true, url: "javascript:;", 
                  	selector: "#data-table .checkboxes", mustSelect: "请先选择数据！", singleSelect: "该操作只支持单选！",
                  	click:function(){
                  		var pkuid = 0;
                  		var auditstate = "";
                        $("#data-table .checkboxes").each(function() {
                            if ($(this).prop("checked")){                          	
                            	pkuid = $(this).val();
                            	auditstate = $(this).parent().parent().parent().parent().find(".auditstate").text();
                            	return false;
                            }
                        });
                        if(pkuid == 0){
                        	alert("请刷新后选择数据！");
                        	return;
                        }
                        
                        if(!(auditstate == "草稿" || auditstate == "未通过")){
                        	alert("非草稿或者未通过状态不能编辑");
                        	return;
                        }
                        location.href = "<%=UrlUtil.resolveWithReturnUrl("/wipecost/edit/{0}", requestUrl, requestQuery, pageContext)%>".replace("{0}",pkuid);
                        return false;
                  	}              
               },
               { splitter: true }, 
               { link: true, display: "删除", css: "icon-trash", showIcon: true, url: "javascript:;", 
                 	 selector: "#data-table .checkboxes", mustSelect: "请先选择数据！",singleSelect: "该操作只支持单选！", 
	               click:function(){
	            	   var pkuid = 0;
                 		var auditstate = "";
                       $("#data-table .checkboxes").each(function() {
                           if ($(this).prop("checked")){                          	
                           	pkuid = $(this).val();
                           	auditstate = $(this).parent().parent().parent().parent().find(".auditstate").text();
                           	return false;
                           }
                       });
                       if(pkuid == 0){
                       	alert("请刷新后选择数据！");
                       	return false;
                       }
                       
                       if(auditstate != "草稿"){
                       	alert("非草稿状态不能删除");
                       	return false;
                       }
                       if (!confirm("确认删除所选数据吗?")){
                    	   return false;
                       } 
                       
                       location.href = "<%=UrlUtil.resolveWithReturnUrl("/wipecost/delete/{0}", requestUrl, requestQuery, pageContext)%>".replace("{0}",pkuid);
                       return false;
	            	   
	               }  	 
             
               },
               { splitter: true }, 
			   { link: true, display: "提交审核", css: "glyphicon glyphicon-ok", showIcon: true, url: "javascript:;", 
               	 selector: "#data-table .checkboxes", mustSelect: "请先选择数据！",singleSelect: "该操作只支持单选！",
               	 click:function(){
               		var pkuid = 0;
             		var auditstate = "";
                   $("#data-table .checkboxes").each(function() {
                       if ($(this).prop("checked")){                          	
                       	pkuid = $(this).val();
                       	auditstate = $(this).parent().parent().parent().parent().find(".auditstate").text();
                       	return false;
                       }
                   });
                   if(pkuid == 0){
                   	alert("请刷新后选择数据！");
                   	return false;
                   }
                   
                   if(!(auditstate == "草稿" || auditstate == "未通过")){
                   	alert("非草稿或者未通过状态不能提交");
                   	return false;
                   }
                   
                   
                   
                   if (!confirm("确认提交所选数据吗?")){
                	   return false;
                   } 
               		 
               		location.href = "<%=UrlUtil.resolveWithReturnUrl("/wipecost/submit/{0}", requestUrl, requestQuery, pageContext)%>".replace("{0}",pkuid);
               		 return false;
               	 }
			   },
			   { splitter: true }, 
			   { link: true, display: "确认完成报销", css: "glyphicon glyphicon-saved", showIcon: true, url: "javascript:;", 
               	 selector: "#data-table .checkboxes", mustSelect: "请先选择数据！",singleSelect: "该操作只支持单选！",
               	 click:function(){
               		var pkuid = 0;
             		var auditstate = "";
                   $("#data-table .checkboxes").each(function() {
                       if ($(this).prop("checked")){                          	
                       	pkuid = $(this).val();
                       	auditstate = $(this).parent().parent().parent().parent().find(".auditstate").text();
                       	return false;
                       }
                   });
                   if(pkuid == 0){
                   	alert("请刷新后选择数据！");
                   	return false;
                   }
                   
                   if(auditstate != "已通过"){
                   	alert("非已通过状态不能确认");
                   	return false;
                   }
                   if (!confirm("确认完成报销吗?")){
                	   return false;
                   } 
               		 
               		location.href = "<%=UrlUtil.resolveWithReturnUrl("/wipecost/complete/{0}", requestUrl, requestQuery, pageContext)%>".replace("{0}",pkuid);
               		 return false;
               	 }
               	},
                { splitter: true }, 
                { link: true, display: "查阅", css: "glyphicon glyphicon-eye-open", showIcon: true, url: "<%=UrlUtil.resolveWithReturnUrl("/wipecost/viewwipecost/{0}", requestUrl, requestQuery, pageContext)%>", 
              	selector: "#data-table .checkboxes", mustSelect: "请先选择数据！", singleSelect: "该操作只支持单选！"},
              
           ]
       });
    });
   </script>

</body>
</html>