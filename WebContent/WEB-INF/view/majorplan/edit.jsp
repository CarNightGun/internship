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
                        <form:hidden path="pkuid"/>
                        	<div class="form-group">
                              <label  class="col-md-2 control-label">专业计划名称</label>
                              <div class="col-md-10">
                                 <form:input path="majorPlanName" required="required" autofocus="autofocus" class="form-control" placeholder="专业计划名称"/><br/>
                              </div>
                           </div> 
                           <div class="form-group">
                              <label  class="col-md-2 control-label">专业计划说明</label>
                              <div class="col-md-10">
                                 <form:textarea path="planRemarks" required="required"  class="form-control" placeholder="专业计划说明"/><br/>
                              </div>
                           </div> 
                           <div class="form-group">
                              <label  class="col-md-2 control-label">专业</label>
                              <div class="col-md-10">
                                 <form:select path="major.pkuid" class="form-control">  
					                <option value="">请选择</option>  
					                <form:options items="${selectMajorDataSource}"/>  
					           	 </form:select>
                              </div>
                           </div> 
                           <div class="form-group">
                              <label  class="col-md-2 control-label">实习费用计划</label>
                              <div class="col-md-10">
                                <form:select path="costPlan.pkuid" class="form-control">  
					                <option value="">请选择</option>  
					                <form:options items="${selectCostPlanDataSource}"/>  
					           	 </form:select>
                              </div>
                           </div>  
                           <%-- <div class="form-group">
                              <label  class="col-md-2 control-label">实习费用</label>
                              <div class="col-md-10">
                                 <form:input path="totalAmount" type="number" min="0" step="0.01" required="required" class="form-control" /><br/>
                              </div>
                           </div> --%>
                          <%--  <div class="form-group">
                              <label  class="col-md-2 control-label">所属年份</label>
                              <div class="col-md-10">
                                 <form:input path="planYear" type="number" min="2015" step="1"  required="required" class="form-control" placeholder="如2015" /><br/>
                              </div>
                           </div>  --%>
                           
                           
                           <%--  <c:if test="${not empty contentModel.pkuid}">
                           <div class="form-group">
                           		<label  class="col-md-2 control-label">费用明细</label>
                           	 	<div class="col-md-10">
                           	 		 <div class="portlet">
						                  <div class="portlet-body">
						                  	<div class="table-scrollable">
							                     <table class="table table-striped table-bordered table-hover" id="data-table">
							                        <thead>
							                           <tr>
							                              <th class="table-checkbox"><input type="checkbox" class="group-checkable"/></th>
							                              <th >费用类别编号</th>
							                              <th >费用类别名称</th>
							                              <th >费用金额</th>
							                              <th >费用说明</th>       
							                           </tr>
							                        </thead>
							                        <tbody>
							                        	<c:forEach items="${contentModel.costListDetail}" var="item" varStatus="vs">
												        <tr class="odd gradeX">
												        	<td class="check_cell">
														        <input type="checkbox" class="checkboxes" name="Id" value="${ item.pkuid }" />
														    </td>
														    <form:hidden path="costListDetail[${vs.index}].pkuid"/>
												            <td>
												            <form:select path="costListDetail[${vs.index}].costCategory.categoryCode" class="form-control">   
												                <form:options items="${selectMajorDataSource}"/>  
												           	 </form:select>
												            </td>
												            <td>
												           		<form:select path="costListDetail[${vs.index}].costCategory.categoryName" class="form-control">   
												                <form:options items="${selectMajorDataSource}"/>  
												           	 </form:select>
												            </td>
												            <td><form:input path="costListDetail[${vs.index}].price"/></td>  
												            <td><form:input path="costListDetail[${vs.index}].remark"/></td>
												        </tr>
												        </c:forEach>
							                        </tbody>
							                     </table>
	                     					</div>	 
						                  
						                  </div>
					                  </div>                           	 		
                           	 	</div>
                           </div>
                           </c:if> --%>
                           
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
   
        
    });
 
   </script>

</body>
</html>