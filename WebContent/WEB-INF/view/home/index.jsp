<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
   
   <!-- 开始页面级插件样式 --> 
   <link href="<c:url value='/plugins/fullcalendar/fullcalendar/fullcalendar.css'/>" rel="stylesheet" type="text/css"/>
   <!-- 结束页面级插件样式 -->
   
   <!-- <link rel="shortcut icon" href="favicon.ico" /> -->
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
      <%--    <%@ include file="../common/styleSet.jsp"%> --%>
           
         <!-- 开始页面标题-->
         <div class="row">
            <div class="col-md-12">
               
               <!-- 开始页面的标题和面包屑-->
               <h3 class="page-title">
                  	主页 <small>默认</small>
               </h3>
               <ul class="page-breadcrumb breadcrumb">
                  <li>
                     <i class="icon-home"></i>
                     ${requestScope.permissionMenu.rootName}
                     <i class="icon-angle-right"></i>
                  </li>
                  <li>${requestScope.permissionMenu.subName}</li>
                  <li class="pull-right">
                     <div id="dashboard-report-range" class="dashboard-date-range tooltips" data-placement="top" data-original-title="Change dashboard date range">
                        <i class="icon-calendar"></i>
                        <span></span>
                        <i class="icon-angle-down"></i>
                     </div>
                  </li>
               </ul>
               <!-- 结束页面的标题和面包屑-->
               
            </div>
         </div>
         <!-- 结束页面头部-->
         
         <div class="clearfix"></div>
         <div class="row">      
            	<h1 class="text-center">欢迎您, ${sessionScope.userAuth.username} !</h1>
         </div>
    
      </div>
      <!-- 结束页面 -->
      
   </div>
   <!-- 结束页面内容 -->
   
   <!-- 页脚 -->
   <%@ include file="../common/pageFooter.jsp"%>
   
   <!-- 引入js文件 -->
   <%@ include file="../common/importJs.jsp"%>
   
   <!-- 开始页面级插件 -->
   <script src="<c:url value='/plugins/gritter/js/jquery.gritter.js'/>" type="text/javascript"></script>
   <!-- IMPORTANT! fullcalendar depends on jquery-ui-1.10.3.custom.min.js for drag & drop support -->
   <script src="<c:url value='/plugins/fullcalendar/fullcalendar/fullcalendar.min.js'/>" type="text/javascript"></script>
   <script src="<c:url value='/plugins/jquery.sparkline.min.js'/>" type="text/javascript"></script>  
   <!-- 结束页面级插件 -->
  
   <!-- 开始页面级脚本 -->
   <script src="<c:url value='/js/app.js'/>" type="text/javascript"></script>
   <script src="<c:url value='/js/index.js'/>" type="text/javascript"></script>  
   <!-- 结束页面级脚本 -->  

   <script type="text/javascript">
      jQuery(document).ready(function() {    
         App.init(); // initlayout and core plugins
         Index.init();
         Index.initCalendar(); // init index page's custom scripts
         Index.initPeityElements();
         Index.initKnowElements();
         Index.initDashboardDaterange();
      });
   </script>

</body>
</html>