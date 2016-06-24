<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<shiro:user>
	<shiro:principal/> <a href="${pageContext.request.contextPath }/logout">退出</a>
</shiro:user>

	<table>
		<caption>用户列表</caption>
		<thead>
			<tr>
				<th>用户名</th>
				<th>邮箱</th>
				<th>电话</th>
				<th>地址</th>
				<th>性别</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<s:if test="#users!=null">
				<s:iterator value="users">
					<s:set value="id" var="id"/>
					<tr>
						<td><s:property value="userName" /></td>
						<td><s:property value="email" /></td>
						<td><s:property value="tel" /></td>
						<td><s:property value="address" /></td>
						<!-- 字符的1 对应整数的49 -->
						<td>${sex eq 49?"男":"女"}</td>
						<td>
							<shiro:hasPermission name="user:delete">
								<s:a namespace="/" action="userAction_delete?id=%{#id}">删除</s:a>
							</shiro:hasPermission>
							<shiro:hasPermission name="user:edit">
								<s:a namespace="/" action="userAction_edit?id=%{#id}">修改</s:a>
							</shiro:hasPermission>
						</td>
					</tr>
				</s:iterator>
			</s:if>
			<s:else>
				<tr>
					<td colspan="5">没有任何用户数据</td>
				</tr>
			</s:else>
		</tbody>
	</table>
</body>
</html>