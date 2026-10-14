<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.lang.String" %>
<%@ page import="com.urise.webapp.model.Resume" %>
<%@ page import="com.urise.webapp.util.HtmlUtil" %>
<%@ page import="com.urise.webapp.model.ContactType" %>
<%@ page import="com.urise.webapp.model.SectionType" %>
<%@ page import="com.urise.webapp.model.Section" %>
<%@ page import="com.urise.webapp.model.TextSection" %>
<%@ page import="com.urise.webapp.model.ListSection" %>
<%@ page import="com.urise.webapp.model.OrganizationSection" %>
<%@ page import="com.urise.webapp.model.Organization" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text-html; charset=UTF-8">
<jsp:useBean id="resume" type="com.urise.webapp.model.Resume" scope="request"/>
<link rel="stylesheet" href="css/style.css">"
    <title>
        Резюме ${resume.fullName}
    </title>
</head>

<body>
<jsp:include page="fragments/header.jsp"/>
<section>

    <h2>${resume.fullName}&nbsp;<a href="resume?uuid=${resume.uuid}&action=edit">Edit</a></h2>
    <p>
    <p>
            <%

                for (Map.Entry<ContactType, String> contactEntry : resume.getContacts().entrySet()) {
            %>
                <%=contactEntry.getKey().toHtml(contactEntry.getValue())%><br/>
            <%
                }
            %>
        </p>
    </p>

    <table cellpadding="2">
            <%
                // Чистый Java-перебор карты секций без JSTL-багов
                for (Map.Entry<SectionType, Section> sectionEntry : resume.getSections().entrySet()) {
                    SectionType type = sectionEntry.getKey();
                    Section section = sectionEntry.getValue();
            %>
                <tr>
                    <td colspan="2"><h3><a><%= type.getTitle() %></a></h3></td>
                </tr>
                <tr>
                    <td colspan="2">
                    <%
                        // Проверяем тип секции через стандартный Java switch/if
                        if (section instanceof TextSection) {
                    %>
                        <b><%= ((TextSection) section).getContent() %></b>
                    <%
                        } else if (section instanceof ListSection) {
                    %>
                        <ul>
                        <% for (String item : ((ListSection) section).getItems()) { %>
                            <li><%= item %></li>
                        <% } %>
                        </ul>
                    <%
                        } else if (section instanceof OrganizationSection) {
                            for (Organization org : ((OrganizationSection) section).getOrganizations()) {
                    %>
                        <div style="margin-bottom: 10px;">
                            <% if (org.getHomePage().getUrl() == null || org.getHomePage().getUrl().isEmpty()) { %>
                                <h4><%= org.getHomePage().getName() %></h4>
                            <% } else { %>
                                <h4><a href="<%= org.getHomePage().getUrl() %>"><%= org.getHomePage().getName() %></a></h4>
                            <% } %>

                            <table style="margin-left: 20px;">
                            <% for (Organization.Position position : org.getPositions()) { %>
                                <tr>
                                    <td style="vertical-align: top; padding-right: 15px;">
                                        <%= HtmlUtil.formatDates(position) %>
                                    </td>
                                    <td>
                                        <b><%= position.getTitle() %></b><br/>
                                        <%= position.getDescription() != null ? position.getDescription() : "" %>
                                    </td>
                                </tr>
                            <% } %>
                            </table>
                        </div>
                    <%
                            }
                        }
                    %>
                    </td>
                </tr>
            <%
                }
            %>
            </table>
            <button type="button" onclick="window.history.back()">Ок</button>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>