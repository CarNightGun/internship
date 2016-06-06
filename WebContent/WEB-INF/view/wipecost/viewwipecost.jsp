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
                              <label  class="col-md-2 control-label">凭证名称</label>
                              <div class="col-md-10">
                              	<p class="form-control-static">
                                 	${contentModel.wipeCostName }
                                 </p>
                                 <%-- <form:input path="wipeCostName"  class="form-control"  required="required" /><br/> --%>
                              </div>
                           </div>
                           <div class="form-group">
                              <label  class="col-md-2 control-label">凭证说明</label>
                              <div class="col-md-10">
                              <p class="form-control-static">
                                 	${contentModel.wipeRemark }
                                 </p>
                                 <%-- <form:textarea path="wipeRemark" required="required"  class="form-control" placeholder="专业计划说明"/><br/> --%>
                              </div>
                           </div> 
                           <div class="form-group">
                              <label  class="col-md-2 control-label">专业</label>
                              <div class="col-md-10">
                                 <%-- <form:select path="major.pkuid" class="form-control">  
					                <option value="">请选择</option>  
					                <form:options items="${selectMajorDataSource}"/>  
					           	 </form:select> --%>
					           	 <p class="form-control-static">
                                 	${contentModel.major.name }
                                 </p>
                              </div>
                           </div> 
                           <div class="form-group">
                              <label  class="col-md-2 control-label">专业实习计划</label>
                              <div class="col-md-10">
                                 <%-- <form:select path="majorPlan.pkuid" class="form-control" required="required">  
					                <option value="">请选择</option>  
					                <form:options items="${selectMajorPlanDataSource}"/>  
					           	 </form:select> --%>
					           	 <p class="form-control-static">
                                 	${contentModel.majorPlan.majorPlanName }
                                 </p>
                              </div>
                           </div> 
                           
                          <c:if test="${not empty contentModel.pkuid}">
                           <div class="form-group">
                              <label  class="col-md-2 control-label">费用明细</label>
                              <div class="col-md-10">
                               <div class="portlet box light-grey">
                                  <div class="portlet-body">
				                    <!--  <div class="table-toolbar"></div> -->
				                     <div class="dataTables_wrapper form-inline" role="grid">
					                     <div class="table-scrollable">
						                     <table class="table table-striped table-bordered table-hover" id="data-table">
						                        <thead>
						                           <tr>
						                             <!--  <th class="table-checkbox"><input type="checkbox" class="group-checkable"/></th> -->
						                              <th >费用名称</th>
						                              <th >会计科目编号</th>
						                              <th >会计科目名称</th>
						                              <th >所属凭证编号</th>
						                              <th >所属凭证名称</th>
						                              <th >单据编号</th>
						                              <th >报销金额</th>
						                              <th >实习类别</th>
						                           </tr>
						                        </thead>
						                        <tbody>
						                 
						                        	<c:forEach items="${contentModel.wipeCostDetails}" var="item" varStatus="vs">
											        <tr class="odd gradeX">
											        	<%-- <td class="check_cell">
													        <input type="checkbox" class="checkboxes" name="pkuid" value="${ item.pkuid }" />
													    </td> --%>
													    <%-- <form:hidden path="wipeCostDetails[${vs.index}].pkuid"/> --%>
													    <td>${item.costClass.categoryName}</td>
													    <td>${item.accounting.categoryCode}</td>
													    <td>${item.accounting.categoryName}</td>
													    <td>${item.wipeCost.wipeCode}</td>
													    <td>${item.wipeCost.wipeCostName}</td>
													    <td>${item.wipedDocNumber}</td>
													    <td>${item.amount}</td>
													    <td>${item.internClass == 0 ? "专业实习" : "毕业实习"}</td>
											        </tr>
											        </c:forEach>
						                        </tbody>
						                     </table>
					                     </div>
					                     
				       				 </div>
				                  </div>
                              </div>
                           </div>
                           </div>
                        </c:if> 
                         <div class="form-group">
                              <label  class="col-md-2 control-label">报销总额</label>
                              <div class="col-md-10">
                              	<p class="form-control-static">
                                 	${contentModel.willWipedCost }
                                 </p>
                                 
                              </div>
                           </div>
                          <c:if test="${not empty contentModel.auditHistorys}">
                          <div class="form-group">
                              <label  class="col-md-2 control-label">已审核结果记录</label>
                              <div class="col-md-10">
                              	<div class="portlet box light-grey">
                                  <div class="portlet-body">
				                     <div class="dataTables_wrapper form-inline" role="grid">
					                     <div class="table-scrollable">
						                     <table class="table table-striped table-bordered table-hover" id="data-table">
						                        <thead>
						                           <tr>
						                              <th >审核结果</th>
						                              <th >审核意见</th>
						                              <th >审核人名称</th>
						                              <th >时间</th>
						                           </tr>
						                        </thead>
						                        <tbody>
						                        	<c:forEach items="${contentModel.auditHistorys}" var="item">
											        <tr class="odd gradeX">
											            <td>${item.auditResult }</td>
											            <td>${item.explainRemark } </td>
											            <td>${item.operatorName } </td>
											            <td>${item.createTime.toLocaleString() } </td>											             
											        </tr>
											        </c:forEach>
						                        </tbody>
						                     </table>
					                     </div>
					                     
				       				 </div>
				                  </div>
                              </div>
                                 
                              </div>
                           </div> 
                           </c:if> 
                           <%-- <c:if test="${contentModel.auditState == '审核中' }">
  							<div class="form-group">
                              <label  class="col-md-2 control-label">审核结果</label>
                              <div class="col-md-10">
	                              <form:select path="tpauditResult" class="form-control" required="required"> 
	                              		 <option value="">请选择</option>   
						                <form:options items="${selectAuditResultDataSource}"/>  
								    </form:select>
                              </div>
                           </div>
                           <div class="form-group">
                              <label  class="col-md-2 control-label">审核意见</label>
                              <div class="col-md-10">
                              	  <form:textarea path="tpexplain" class="form-control"  required="required"/>
                              </div>
                           </div>
                           <div class="form-group">
                              <label  class="col-md-2 control-label">审核人名称</label>
                              <div class="col-md-10">
                              	  <form:input path="tpoperatorName" class="form-control"  required="required"/>
                              </div>
                           </div>
                           </c:if> --%>
                       </div>
                       <div class="form-actions fluid">
                           <div class="col-md-offset-6 col-md-6">
                              <button type="submit" class="btn btn-success">返回</button>                             
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
   <c:if test="${not empty contentModel.pkuid}">
   <script type="text/javascript">
   $(function() {
  		 
	   var inputs = $("td input[type=text]");
 		for(var i=0;i<inputs.length;i++){
 			var le = inputs[i].value.length*15;
 			if(le > 150){
 				le = inputs[i].value.length*8;
 			}
 			inputs.eq(i).css("width",le+'px');
 		}  		 

		$("#data-table").tableManaged();
      
      	<%-- $(".table-toolbar").toolbarLite({
          items: [ 
              { link: true, display: "删除明细", css: "icon-trash", showIcon: true, url: "<%=UrlUtil.resolveWithReturnUrl("/wipecost/removewipecostdetail/{0}", requestUrl, requestQuery, pageContext)%>", 
                selector: "#data-table .checkboxes", mustSelect: "请先选择数据！", confirm: "确认删除所选数据吗？"}
          ]
      	}); --%>
      
    });
   </script>
	</c:if>
   
   
   
   <script type="text/javascript">
   $(function() {   
       App.init();
       
        
   
        
    });
   var price = $(".col-md-10 #price").val();
   var pkuid = $("#pkuid[type=hidden]").val();
   
   function tipYou(){
	   
	  
	   var inputValue = 0;
	   if(pkuid > 0){
		   inputValue = $(".col-md-10 #price").val() - price;
	   }else{
		   inputValue = $(".col-md-10 #price").val();
	   }
	   
	   var majorPlanId = $("select[id='majorPlan.pkuid']").val();
	   if(majorPlanId == ""){
		   return;
	   }
	   
	   var costCategoryId = $("select[id='costCategory.pkuid']").val();
	   if(costCategoryId == ""){
		   return;
	   }
	   
	   var mydata = {"inputValue":inputValue,"majorPlanId":majorPlanId,"costCategoryId":costCategoryId };
	   
	   var url = "<%=UrlUtil.resolveUrl("/costlistdetail/timing",pageContext)%>";
	   
	   $.ajax({
		   type : "post",
		   async : false, //同步请求
		   url : url,
		   dataType: "json",
		   data : mydata,
		   success:function(data){			
			   var costname = "";
			   if(data.isConnectionFee == true){
				   costname = "实习联系费";
			   }else{
				   costname ="本专科实习费";
			   }
			$(".col-md-10 #price").attr("data-original-title",costname+"总计为:"+data.usableTotalAmount+",剩余可用额为:"+data.residueTotalAmount);
			$(".col-md-10 #price").attr("max",data.residueTotalAmount);
			$(".col-md-10 #price").tooltip();
		   },
		   error: function(edata) {
			   console.log(edata);
		   }
		  });
   }
 
   </script>

</body>
</html>