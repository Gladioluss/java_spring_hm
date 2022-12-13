<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<body>
<form:form action="save" modelAttribute="employee">
    <form:input path="name"/>
    <br><br>
    <form:input path="surname"/>
    <br><br>
    <form:input path="department"/>
    <br><br>
    <form:input path="salary"/>
    <br><br>
</form:form>
</body>
</html>