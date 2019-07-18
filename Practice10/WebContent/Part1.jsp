<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
        table {
            border: solid 1px blue;
        }

        th {
            border: solid 1px blue;
        }
    </style>
</head>
<body>
	<table>
		<% for (int i = 0; i < 3; i++) { %>
        <tr>
            <% for (int j = 0; j < 3; j++) { %>
            <% if (i == 0 && j == 0) { %>
            <th></th>
            <% } else if (i == 0) { %>
            <th><%=j%></th>
            <% } else  if (j == 0) { %>
            <th><%=i%></th>
            <% } else {%>
                <th><%=i*(j)%></th>
            <% } %>
            <% } %>
        </tr>
    <% } %>
	</table>
</body>
</html>