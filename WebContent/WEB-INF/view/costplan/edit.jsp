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
                        <div class="form-group">
                              <label  class="col-md-2 control-label">实习经费计划名称</label>
                              <div class="col-md-10">
                                 <form:input path="costPlanName" required="required" autofocus="autofocus" class="form-control" placeholder="实习经费计划名称"/><br/>
                              </div>
                           </div> 
                           <div class="form-group">
                              <label  class="col-md-2 control-label">实习经费计划说明</label>
                              <div class="col-md-10">
                                 <form:textarea path="planRemark" required="required" class="form-control" placeholder="实习经费计划说明"/><br/>
                              </div>
                           </div> 
                           <div class="form-group">
                              <label  class="col-md-2 control-label">实习计划所属年份</label>
                              <div class="col-md-10">
                                 <form:input path="planYear" type="number" min="2015" step="1" required="required" class="form-control" placeholder="如 2015"/><br/>
                              </div>
                           </div>  
                           <div class="form-group">
                              <label  class="col-md-2 control-label">实习总费用(元)</label>
                              <div class="col-md-10">
                                 <form:input path="totalCost" type="number" min="0" step="0.01" required="required" class="form-control" /><br/>
                              </div>
                           </div>
                           <div class="form-group">
                              <label  class="col-md-2 control-label">专业实习人数(人)</label>
                              <div class="col-md-10">
                                 <form:input path="professionalNumber" type="number" min="1" step="1" value="${contentModel.professionalNumber==0 ? 1 : contentModel.professionalNumber }" required="required" class="form-control" /><br/>
                              </div>
                           </div> 
                           <div class="form-group">
                              <label  class="col-md-2 control-label">毕业实习人数(人)</label>
                              <div class="col-md-10">
                                 <form:input path="graduateNumner" type="number" min="1" step="1" value="${contentModel.graduateNumner==0 ? 1 : contentModel.graduateNumner }" required="required" class="form-control" /><br/>
                              </div>
                           </div> 
                           <div class="form-group">
                              <label  class="col-md-2 control-label">专业实习平均费用(元)</label>
                              <div class="col-md-10">
                                 <form:input path="professionalAvgCost" type="number" min="0" step="0.01" required="required" class="form-control"/><br/>
                              </div>
                           </div> 
                           <div class="form-group">
                              <label  class="col-md-2 control-label">毕业实习平均费用(元)</label>
                              <div class="col-md-10">
                                 <form:input path="graduateAvgCost" type="number" min="0" step="0.01" required="required" class="form-control" /><br/>
                              </div>
                           </div> 
                           <div class="form-group">
                              <label  class="col-md-2 control-label">本专科生实习费用(元)</label>
                              <div class="col-md-10">
                                 <form:input path="collegeStuFee" type="number" min="0" step="0.01" required="required" class="form-control" /><br/>
                              </div>
                           </div> 
                           <div class="form-group">
                              <label  class="col-md-2 control-label">实习联系费(元)</label>
                              <div class="col-md-10">
                                 <form:input path="connectionFee" data-toggle="tooltip" type="number" min="0" step="0.01" required="required" class="form-control"/><br/>
                              </div>
                           </div> 
                           <div class="form-group">
                              <label  class="col-md-2 control-label">实习机动经费(元)</label>
                              <div class="col-md-10">
                                 <form:input path="activeFee" type="number" min="0" step="0.01" required="required" class="form-control" /><br/>
                              </div>
                           </div> 
                           <div class="form-group">
                              <label  class="col-md-2 control-label">剩余实习经费(元)</label>
                              <div class="col-md-10">
                                 <form:input path="residualFee" type="number" min="0" step="0.01" required="required" class="form-control" /><br/>
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
       
       $("#totalCost,#professionalNumber,#graduateNumner").on("blur",function(){
    	   counttotal();

         });
    
       $("#graduateAvgCost,#professionalAvgCost,#residualFee").on("blur",function(){
    	   var totalCost = $("#totalCost").val() - $("#residualFee").val();
    	   var professionalAvgCost = $("#professionalAvgCost").val();
    		var graduateAvgCost = $("#graduateAvgCost").val();
    		var professionalNumber = $("#professionalNumber").val();
    		var graduateNumner = $("#graduateNumner").val();
    	   var _totalCost = (professionalAvgCost*professionalNumber + graduateAvgCost*graduateNumner).toFixed(2);
    	   
    	   if($("#totalCost").val()-_totalCost < 0){
    		   counttotal();
    		   $("#activeFee").val(0);
    		   $("#residualFee").val(0);
    		   return;
    	   }
    	   
	   		if(totalCost - _totalCost < 0){
	   			$("#activeFee").val(0);
	   			$("#residualFee").val(($("#totalCost").val()-_totalCost).toFixed(2));
	   		}else{
	   			
	   	 		$("#activeFee").val((totalCost - _totalCost).toFixed(2));
	   		}
    	   $("#collegeStuFee").val((_totalCost * 0.9).toFixed(2));
     		$("#collegeStuFee").attr("min",_totalCost * 0.9);
    	   
    	   $("#connectionFee").val((_totalCost * 0.1).toFixed(2));
     		$("#connectionFee").attr("max",_totalCost * 0.1);
     		$('#connectionFee').attr("data-original-title","费用必须小于"+(_totalCost * 0.1).toFixed(2));
	   		$('#connectionFee').tooltip();

	   		
	   	 
       });
       
       $("#connectionFee").on("blur",function(){
    	   var professionalAvgCost = $("#professionalAvgCost").val();
	   		var graduateAvgCost = $("#graduateAvgCost").val();
	   		var professionalNumber = $("#professionalNumber").val();
	   		var graduateNumner = $("#graduateNumner").val();
	   		var connectionFee = $("#connectionFee").val()*1;
	   		var connectionFeeAttr = $("#connectionFee").attr("max")*1;
	   	   var _totalCost = (professionalAvgCost*professionalNumber + graduateAvgCost*graduateNumner).toFixed(2);
	   	   if( connectionFee  < connectionFeeAttr ){
	   			$("#collegeStuFee").val((_totalCost-connectionFee).toFixed(2));	   		   
	   	   }else{
	   		$("#connectionFee").val((_totalCost-$("#collegeStuFee").val()).toFixed(2));
	   	   }
    	   
       });
       
       $("#collegeStuFee").on("blur",function(){
    	   var professionalAvgCost = $("#professionalAvgCost").val();
	   		var graduateAvgCost = $("#graduateAvgCost").val();
	   		var professionalNumber = $("#professionalNumber").val();
	   		var graduateNumner = $("#graduateNumner").val();
	   		var collegeStuFee = $("#collegeStuFee").val()*1;
	   		var collegeStuFeeAttr = $("#collegeStuFee").attr("min")*1;
	   	   var _totalCost = (professionalAvgCost*professionalNumber+graduateAvgCost*graduateNumner).toFixed(2);
	   	   if( collegeStuFee  >  collegeStuFeeAttr ){
	   			$("#connectionFee").val((_totalCost-collegeStuFee).toFixed(2));	   
	   	   }else{
	   		$("#collegeStuFee").val((_totalCost-$("#connectionFee").val()).toFixed(2));	
	   	   }
    	   
       });
       
       
       $("#activeFee").on("blur",function(){
    	   var totalCost = $("#totalCost").val();
    	   var professionalAvgCost = $("#professionalAvgCost").val();
	   		var graduateAvgCost = $("#graduateAvgCost").val();
	   		var professionalNumber = $("#professionalNumber").val();
	   		var graduateNumner = $("#graduateNumner").val();
	   		var activeFee = $("#activeFee").val();
    	   $("#residualFee").val((totalCost - professionalAvgCost*professionalNumber-graduateAvgCost*graduateNumner - activeFee).toFixed(2));
       });
       
    });
   
   function counttotal(){
	   var totalCost = $("#totalCost").val();
 	    var professionalNumber = $("#professionalNumber").val();
 		var graduateNumner = $("#graduateNumner").val();
 		var totalStu = parseInt(professionalNumber*4) + parseInt(graduateNumner);    		
 		var avgCost = totalCost / totalStu; 
 		$("#graduateAvgCost").val(avgCost.toFixed(2));    		   		
 		$("#professionalAvgCost").val((avgCost * 4).toFixed(2));
 		
 		$("#collegeStuFee").val((totalCost * 0.9).toFixed(2));
 		$("#collegeStuFee").attr("min",totalCost * 0.9);
 		
 		$("#connectionFee").val((totalCost * 0.1).toFixed(2));
 		$("#connectionFee").attr("max",(totalCost * 0.1).toFixed(2));
 		$('#connectionFee').attr("data-original-title","费用必须小于"+(totalCost * 0.1).toFixed(2));
  		$('#connectionFee').tooltip();
   }
   

   
   </script>

</body>
</html>