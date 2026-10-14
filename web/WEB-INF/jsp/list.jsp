<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.urise.webapp.model.Resume" %>
<%@ page import="com.urise.webapp.model.ContactType" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text-html; charset=UTF-8">
<link rel="stylesheet" href="css/style.css">"
    <title>
        Список всех резюме
    </title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<section>
<a href="resume?action=add">Добавить</a>
    <table border="1" cellpadding="8" cellspacing="0">
        <tr>
            <th>Имя</th>
            <th>Email</th>
            <th></th>
            <th></th>
        </tr>
        <%
        for(Resume resume : (List<Resume>) request.getAttribute("resumes")){
        %>
        <tr>
             <td><a href="resume?uuid=<%=resume.getUuid()%>&action=view"><%=resume.getFullName()%></a></td>
             <td><%=ContactType.MAIL.toHtml(resume.getContact(ContactType.MAIL))%></td>
             <td><a href="resume?uuid=<%=resume.getUuid()%>&action=delete">Delete</a></td>
             <td><a href="resume?uuid=<%=resume.getUuid()%>&action=edit">Edit</a></td>
        </tr>
        <%
        }
        %>
    </table>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>