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
                              <label  class="col-md-2 control-label">会计科目名称</label>
                              <div class="col-md-10">
                                 <form:select path="costCategory.pkuid" class="form-control" required="required">  
					                <option value="">请选择</option>  
					                <form:options items="${selectCostCategoryDataSource}"/>  
					           	 </form:select>
                              </div>
                           </div> 
                           <div class="form-group">
                              <label  class="col-md-2 control-label">费用说明</label>
                              <div class="col-md-10">
                                 <form:textarea path="remark" required="required"  class="form-control" placeholder="专业计划说明"/><br/>
                              </div>
                           </div> 
                           <div class="form-group">
                              <label  class="col-md-2 control-label">专业实习计划</label>
                              <div class="col-md-10">
                                 <form:select path="majorPlan.pkuid" class="form-control" required="required">  
					                <option value="">请选择</option>  
					                <form:options items="${selectMajorPlanDataSource}"/>  
					           	 </form:select>
                              </div>
                           </div> 
  
                           
                           <div class="form-group">
                              <label  class="col-md-2 control-label">实习类别</label>
                              <div class="col-md-10">
                                 <form:select path="internClass" class="form-control" required="required">  
					                <option value="">请选择</option>  
					                <form:options items="${selectInternClassDataSource}"/>  
					           	 </form:select>
                              </div>
                           </div>
                           
                           <div class="form-group">
                              <label  class="col-md-2 control-label">实习费用</label>
                              <div class="col-md-10">
                                 <form:input path="price" type="number" min="0" step="0.01"  class="form-control"  required="required" /><br/>
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
       
       $(".col-md-10 #price").on("blur",function(){
    	   setTimeout(tipYou(),1000); 
    	   
       });
   
        
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