<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.lang.String" %>
<%@ page import="com.urise.webapp.model.Resume" %>
<%@ page import="com.urise.webapp.util.HtmlUtil" %>
<%@ page import="com.urise.webapp.util.DateUtil" %>
<%@ page import="com.urise.webapp.model.ContactType" %>
<%@ page import="com.urise.webapp.model.SectionType" %>
<%@ page import="com.urise.webapp.model.Section" %>
<%@ page import="com.urise.webapp.model.TextSection" %>
<%@ page import="com.urise.webapp.model.ListSection" %>
<%@ page import="com.urise.webapp.model.OrganizationSection" %>
<%@ page import="com.urise.webapp.model.Organization" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <jsp:useBean id="resume" type="com.urise.webapp.model.Resume" scope="request"/>
    <link rel="stylesheet" href="css/style.css">
    <title>Редактирование резюме ${resume.fullName}</title>
</head>

<body>
<jsp:include page="fragments/header.jsp"/>
<section>
    <form action="resume" method="post" enctype="application/x-www-form-urlencoded">
        <input type="hidden" name="uuid" value="${resume.uuid}">
        <dl>
            <dt>Имя:</dt>
            <dd>
                <input type="text" name="fullName" size="50" value="${resume.fullName}">
            </dd>
        </dl>
        <h3>Контакты:</h3>

        <%
            for (ContactType type : ContactType.values()) {
        %>
            <dl>
                <dt><%= type.getTitle() %></dt>
                <dd>
                    <input type="text" name="<%= type.name() %>" size="30" value="<%= resume.getContact(type) != null ? resume.getContact(type) : "" %>">
                </dd>
            </dl>
        <%
            }
        %>

        <hr>
        <h3>Секции:</h3>

        <%
            for (SectionType type : SectionType.values()) {
                Section section = resume.getSection(type);
        %>
            <h3><%= type.getTitle() %></h3>

            <% if (type == SectionType.OBJECTIVE) {
                String value = (section != null) ? ((TextSection) section).getContent() : "";
            %>
                <input type="text" name="<%= type.name() %>" size="75" value="<%= value %>">

            <% } else if (type == SectionType.PERSONAL) {
                String value = (section != null) ? ((TextSection) section).getContent() : "";
            %>
                <textarea name="<%= type.name() %>" cols="75" rows="5"><%= value %></textarea>

            <% } else if (type == SectionType.QUALIFICATIONS || type == SectionType.ACHIEVEMENT) {
                String value = (section != null) ? String.join("\n", ((ListSection) section).getItems()) : "";
            %>
                <textarea name="<%= type.name() %>" cols="75" rows="5"><%= value %></textarea>

            <% } else if (type == SectionType.EXPERIENCE || type == SectionType.EDUCATION) { %>
                <%
                    if (section != null) {
                        int orgIdx = 0; // Счетчик организаций: для каждой секции начнется заново с 0
                        for (Organization org : ((OrganizationSection) section).getOrganizations()) {
                %>
                        <dl>
                            <dt>Название учреждения:</dt>
                            <dd><input type="text" name="<%= type.name() %>" size="100" value="<%= org.getHomePage().getName() != null ? org.getHomePage().getName() : "" %>"></dd>
                        </dl>
                        <dl>
                            <dt>Сайт учреждения:</dt>
                            <dd><input type="text" name="<%= type.name() %>url" size="100" value="<%= org.getHomePage().getUrl() != null ? org.getHomePage().getUrl() : "" %>"></dd>
                        </dl>
                        <br/>
                        <div style="margin:0 0 0 30px">
                            <% for (Organization.Position pos : org.getPositions()) { %>
                                <dl>
                                    <dt>Начальная дата:</dt>
                                    <dd>
                                        <input type="text" name="<%= type.name() %><%= orgIdx %>startDate" size="10" value="<%= DateUtil.format(pos.getStartDate()) %>" placeholder="MM/yyyy">
                                    </dd>
                                </dl>
                                <dl>
                                    <dt>Конечная дата:</dt>
                                    <dd>
                                        <input type="text" name="<%= type.name() %><%= orgIdx %>endDate" size="10" value="<%= DateUtil.format(pos.getEndDate()) %>" placeholder="MM/yyyy">
                                    </dd>
                                </dl>
                                <dl>
                                    <dt>Должность:</dt>
                                    <dd>
                                        <input type="text" name="<%= type.name() %><%= orgIdx %>title" size="100" value="<%= pos.getTitle() != null ? pos.getTitle() : "" %>">
                                    </dd>
                                </dl>
                                <dl>
                                    <dt>Описание:</dt>
                                    <dd>
                                        <textarea name="<%= type.name() %><%= orgIdx %>description" cols="75" rows="5"><%= pos.getDescription() != null ? pos.getDescription() : "" %></textarea>
                                    </dd>
                                </dl>
                                <hr style="border: 0; border-top: 1px dashed #ccc; width: 50%; margin-left: 0;"/>
                            <% } %>
                        </div>
                <%
                            orgIdx++; // Увеличиваем индекс организации для генерации EXPERIENCE0, EXPERIENCE1...
                        }
                    }
                %>
            <% } %>
            <br/><br/>
        <%
            }
        %>

        <br>
        <button type="submit">Сохранить</button>
        <button type="button" onclick="window.history.back()">Отменить</button>
    </form>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
