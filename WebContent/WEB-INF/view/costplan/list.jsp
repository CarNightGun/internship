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
				<div class="portlet box light-grey">
				   <div class="portlet-title">
					  <div class="caption"><i class="icon-search"></i>年份检索</div>
				   </div>
				   <div class="portlet-body form">
					  <!-- form 开始-->
					  <form:form modelAttribute="searchModel" class="form-horizontal" method="GET">
						 <div class="form-body">
							<div class="row">
							   <div class="col-md-6">
								  <div class="form-group">
									 <label class="control-label col-md-3">年份</label>
									 <div class="col-md-9 input-group">
										<form:input path="planYear"  type="number" min="2015" step="1"  class="form-control placeholder-no-fix" autocomplete="off" placeholder="计划年份,如2015"/>
									 	<span class="input-group-btn">
       									 	<button type="submit" class="btn btn-success" type="button">搜索</button>
      									</span>
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
		                     <table class="table table-striped table-bordered table-hover" id="data-table">
		                        <thead>
		                           <tr>
		                              <th class="table-checkbox"><input type="checkbox" class="group-checkable"/></th>
		                              <th >实习总经费</th>
		                              <th >专业实习人数</th>
		                              <th >毕业实习人数</th>
		                              <th >专业实习平均费用</th>
		                              <th >毕业实习平均费用</th>
		                              <th >本专科生实习费用</th>
		                              <th >实习联系费</th>
		                              <th >实习机动费</th>
		                              <th >剩余实习经费</th>
		                              <th >实习经费计划名称</th>
		                              <th >实习计划所属年份</th>
		                           </tr>
		                        </thead>
		                        <tbody>
		                        	<c:forEach items="${contentModel.items}" var="item">
							        <tr class="odd gradeX">
							        	<td class="check_cell">
									        <input type="checkbox" class="checkboxes" name="Id" value="${ item.pkuid }" />
									    </td>
							            <td>${ item.totalCost }</td>
							            <td>${ item.professionalNumber }</td>
							            <td>${ item.graduateNumner }</td>  
							            <td>${ item.professionalAvgCost }</td>
							            <td>${ item.graduateAvgCost }</td>
							            <td>${ item.collegeStuFee }</td>
							            <td>${ item.connectionFee }</td>
							            <td>${ item.activeFee }</td>
							            <td>${ item.residualFee }</td>
							            <td>${ item.costPlanName }</td>
							            <td>${ item.planYear }</td>
							        </tr>
							        </c:forEach>
		                        </tbody>
		                     </table>
	                     </div>
	                     <c:import url = "../common/paging.jsp">
	        				<c:param name="pageModelName" value="contentModel"/>
	        				<c:param name="urlAddress" value="/costplan/list"/>
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
               { link: true, display: "新建", css: "icon-plus", showIcon: true, url: "<%=UrlUtil.resolveWithReturnUrl("/costplan/add", requestUrl, requestQuery, pageContext)%>" },
			   { splitter: true }, 
               { link: true, display: "编辑", css: "icon-edit", showIcon: true, url: "<%=UrlUtil.resolveWithReturnUrl("/costplan/edit/{0}", requestUrl, requestQuery, pageContext)%>", 
                  	selector: "#data-table .checkboxes", mustSelect: "请先选择数据！", singleSelect: "该操作只支持单选！"},
               { splitter: true }, 
               { link: true, display: "删除", css: "icon-trash", showIcon: true, url: "<%=UrlUtil.resolveWithReturnUrl("/costplan/delete/{0}", requestUrl, requestQuery, pageContext)%>", 
                 	 selector: "#data-table .checkboxes", mustSelect: "请先选择数据！", confirm: "确认删除所选数据吗？"}
           ]
       });
    });
   </script>

</body>
</html>