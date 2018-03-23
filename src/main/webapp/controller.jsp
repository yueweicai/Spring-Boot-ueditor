<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="com.baidu.ueditor.ActionEnter"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%
    request.setCharacterEncoding( "utf-8" );
	String action = request.getParameter("action");  
	String rootPath = application.getRealPath( "/" );
	String str = new ActionEnter( request, rootPath ).exec();
	if( action!=null && (action.equals("listfile") || action.equals("listimage") ) ){  
        rootPath = rootPath.replace("\\", "/");  
        str = str.replaceAll(rootPath, "/");  
    }
	out.write(str);
%>