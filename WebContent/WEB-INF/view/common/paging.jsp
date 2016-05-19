<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 
	import="com.km.util.page.IPageList" 
    import="com.km.util.page.PageList" 
    import="com.km.util.page.PageUtil"
    import="org.apache.taglibs.standard.tag.common.core.UrlSupport"%>

<%
	String urlAddress=request.getParameter("urlAddress");
	String pageModelName= request.getParameter("pageModelName");
 	
	Integer pageCount = 10;
	String tmp = request.getParameter("pageCount");
	if((tmp != null && !tmp.isEmpty()))
	{
		pageCount = Integer.valueOf(tmp);
	}
%>

<div class="row">
<%
	if(pageModelName==null || pageModelName.isEmpty()){%>
		未获取到分页标识！
	<%}
	else if(urlAddress==null || urlAddress.isEmpty()){%>
		未获取到url地址！
	<%}
	else if(pageCount<10){%>
		分页数量不能小于10！
	<%}

	IPageList<?> pageModel = (IPageList<?>)request.getAttribute(pageModelName);
	if(pageModel==null){%>
		未获取到分页内容！
	<%}
	else{%>
	
		<div class="col-md-5 col-sm-12">
			<div class="dataTables_info">(每页显示<%=pageModel.getPageSize() %>条，共 <%=pageModel.getItemsCount() %> 条记录)</div>
		</div>
	
		<div class="col-md-7 col-sm-12">
			<div class="dataTables_paginate paging_bootstrap">
		   		<ul class="pagination">

		<%String queryString=request.getQueryString();
		urlAddress = UrlSupport.resolveUrl(urlAddress, null, pageContext);
		if (pageModel.isHasPre()) {
			
            String preUrl = PageUtil.resolveUrl(urlAddress, queryString, pageModel.getCurrentPageNo() - 1, null);%>
            <li class="prev">
				<a href="<%=preUrl%>">上一页</a>
			</li>
        <%}
		else{%>
			<li class="prev disabled">
				<span>上一页</span>
			</li>
		<%}
		if(pageModel.getPageCount()<=pageCount){
        	for(int i=1;i<=pageModel.getPageCount();i++){
        		if(i==pageModel.getCurrentPageNo()){%>
        	<li class="active"><a href="#"><%=i %></a></li>
        		<%}
        		else{
        			String pageUrl  = PageUtil.resolveUrl(urlAddress, queryString, i, null);%>
            <li><a href="<%=pageUrl%>"><%=i %></a></li>
                <%}	
        	}
        }
		else{   	
        	for(int i=1;i<=pageCount;i++){
        		if(pageModel.getCurrentPageNo()<=pageCount/2){
        			if(i==pageModel.getCurrentPageNo()){%>
        				<li class="active"><a href="#"><%=i %></a></li>
        			<%}
        			else if(pageCount-i>2){
        				String pageUrl  = PageUtil.resolveUrl(urlAddress, queryString, i, null);%>
                        <li><a href="<%=pageUrl%>"><%=i %></a></li>
    				<%}
        			else if(i==pageCount-2){%>
        				<span>...</span>
        			<%}
        			else{
    					int pageNo=pageModel.getPageCount()-(pageCount-i);
    					String pageUrl  = PageUtil.resolveUrl(urlAddress, queryString, pageNo, null);%>
                        <li><a href="<%=pageUrl%>"><%=pageNo %></a></li>
        			<%}
        		}
        		else if(pageModel.getPageCount()-pageModel.getCurrentPageNo()<pageCount/2){
        			if(i<3){
        				String pageUrl  = PageUtil.resolveUrl(urlAddress, queryString, i, null);%>
                        <li><a href="<%=pageUrl%>"><%=i %></a></li>
        			<%}
        			else if(i==3){%>
        				<span>...</span>
        			<%}
        			else{
        				int pageNo=pageModel.getPageCount()-(pageCount-i);
        				if(pageNo==pageModel.getCurrentPageNo()){%>
        					<li class="active"><a href="#"><%=pageNo %></a></li>
        				<%}
        				else{
        					String pageUrl  = PageUtil.resolveUrl(urlAddress, queryString, pageNo, null);%>
                            <li><a href="<%=pageUrl%>"><%=pageNo %></a></li>
        				<%}
        			}
        		}
        		else{
        			if(i<3){
        				String pageUrl  = PageUtil.resolveUrl(urlAddress, queryString, i, null);%>
                        <li><a href="<%=pageUrl%>"><%=i %></a></li>
        			<%}
        			else if(i==3 || i==pageCount-2){%>
        				<span>...</span>
        			<%}
        			else if(i>pageCount-2){
        				int pageNo=pageModel.getPageCount()-(pageCount-i);
        				String pageUrl  = PageUtil.resolveUrl(urlAddress, queryString, pageNo, null);%>
                        <li><a href="<%=pageUrl%>"><%=pageNo %></a></li>
        			<%}
        			else{
        				if(pageCount%2==0)
        				{
            				if(i==pageCount/2){%>
            					<li class="active"><a href="#"><%=pageModel.getCurrentPageNo() %></a></li>
            				<%}
            				else if(i<pageCount/2){
            					int pageNo=pageModel.getCurrentPageNo()-(pageCount/2-i);
                				String pageUrl  = PageUtil.resolveUrl(urlAddress, queryString, pageNo, null);%>
                                <li><a href="<%=pageUrl%>"><%=pageNo %></a></li>
            				<%}
            				else{
            					int pageNo=pageModel.getCurrentPageNo()+(i-pageCount/2);
                				String pageUrl  = PageUtil.resolveUrl(urlAddress, queryString, pageNo, null);%>
                                <li><a href="<%=pageUrl%>"><%=pageNo %></a></li>
            				<%}
        				}
        				else{
        					if(i==(pageCount+1)/2){%>
            					<li class="active"><a href="#"><%=pageModel.getCurrentPageNo() %></a></li>
            				<%}
        					else if(i<(pageCount+1)/2){
            					int pageNo=pageModel.getCurrentPageNo()-((pageCount+1)/2-i);
                				String pageUrl  = PageUtil.resolveUrl(urlAddress, queryString, pageNo, null);%>                            
                                <li><a href="<%=pageUrl%>"><%=pageNo %></a></li>
            				<%}
            				else{
            					int pageNo=pageModel.getCurrentPageNo()+(i-(pageCount+1)/2);
                				String pageUrl  = PageUtil.resolveUrl(urlAddress, queryString, pageNo, null);%>
                                <li><a href="<%=pageUrl%>"><%=pageNo %></a></li>
            				<%}
        				}
        			}
        		}
        	}
        }
		if (pageModel.isHasNext()) {
            String nextUrl  = PageUtil.resolveUrl(urlAddress, queryString, pageModel.getCurrentPageNo() + 1, null);%>
            <li class="next">
				<a href="<%=nextUrl %>">下一页</a>
			</li>
        <%}
        else{%>
        	<li class="next disabled">
				<span>下一页</span>
			</li>
        <%}%>
        		</ul>
			</div>
		</div>
        	
	<%}%>
</div>

