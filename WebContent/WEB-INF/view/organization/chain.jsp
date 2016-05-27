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
         <%@ include file="../common/styleSet.jsp"%>
           
         <!-- 开始页面标题-->
         <div class="row">
            <div class="col-md-12">
               
               <!-- 开始页面的标题和面包屑-->
               <h3 class="page-title">
                  	组织机构管理 <small>中心</small>
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
                  <div class="portlet-body">
                      <div id="treeData-list"></div>
                  </div>
                  <div class="portlet-title tool-bottom">
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
	   
	   $("#treeData-list").treeLite({
        	items: <c:out value="${contentModel}" escapeXml="false"></c:out>
       });
	   
       $(".tool-bottom").toolbarLite({
           items: [       
               { link: true, display: "创建同级节点", css: "icon-plus", showIcon: true, url: "javascript:;", 
                click: function() {
                    var parentId = $('#treeData-list').treeLite('parentId');
                    var expanded = $('#treeData-list').treeLite('expandedIds');
                    if(parentId != undefined){
                        location.href = "<%= UrlUtil.resolveWithReturnUrl("/organization/add/{0}", requestUrl, requestQuery, "expanded={1}", pageContext)%>".replace("{0}", parentId).replace(escape("{1}"), expanded);
                    } else{
                        alert("必须选择择一个节点！");
                    }
                    return false;
                  } 
               },
               { splitter: true }, 
               { link: true, display: "创建子级节点", css: "icon-plus", showIcon: true, url: "javascript:;", 
                click: function() {
                    var selectedId = $('#treeData-list').treeLite('selectedId');
                    var expanded = $('#treeData-list').treeLite('expandedIds');
                    if(selectedId != undefined){
                    	location.href = "<%= UrlUtil.resolveWithReturnUrl("/organization/add/{0}", requestUrl, requestQuery, "expanded={1}", pageContext)%>".replace("{0}", selectedId).replace(escape("{1}"), expanded);
                    }else{
                        alert("必须选择择一个节点！");
                    }
                    return false;
                   }
                },
                { splitter: true }, 
                { link: true, display: "编辑", css: "icon-edit", showIcon: true, url: "javascript:;",
                 click: function() {
                    var selectedId = $('#treeData-list').treeLite('selectedId');
                    var expanded = $('#treeData-list').treeLite('expandedIds');
                    if(selectedId != undefined){ 
                    	location.href = "<%= UrlUtil.resolveWithReturnUrl("/organization/edit/{0}", requestUrl, requestQuery, "expanded={1}", pageContext)%>".replace("{0}", selectedId).replace(escape("{1}"), expanded);
                    }else{
                        alert("必须选择择一个节点！");
                    }
                    return false;
                    } 
                },
                { splitter: true }, 
                { link: true, display: "删除", css: "icon-trash", showIcon: true, url: "javascript:;",
                 click: function() {
                    var selectedId = $('#treeData-list').treeLite('selectedId');
                    var expanded = $('#treeData-list').treeLite('expandedIds');
                    if(selectedId && selectedId != undefined) 
                    {
                        if(confirm("确认删除所选节点？")){
                        	location.href = "<%= UrlUtil.resolveWithReturnUrl("/organization/delete/{0}", requestUrl, requestQuery, "expanded={1}", pageContext)%>".replace("{0}", selectedId).replace(escape("{1}"), expanded);
                        }
                    }
                    else{
                        alert("必须选择择一个节点！");
                    }
                    return false;
                   } 
                }
            ]
        });                  
    });
   </script>

</body>
</html>