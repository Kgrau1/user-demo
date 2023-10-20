<%@ page language="java" contentType="text/html;
charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">


<%@include file="head.jsp"%>

<html><body>

<div class="container">
    <section class="">
        <a href="index.jsp">Home</a>
        <h2>Search Results: </h2>

        <c:if test="${not search.employeesFound}">
            <h2>No Employees Found</h2>
        </c:if>
        <c:if test="${search.employeesFound}">
            <c:forEach items="${search.employees}" var="employee">
                <table class="table table-dark table-striped m-3 w-50">
                <tbody>
                <tr>
                    <th>First Name:</th>
                    <td><c:out value="${employee.firstName}"/></td>
                </tr>
                <tr>
                    <th>Last Name:</th>
                    <td><c:out value="${employee.lastName}"/></td>
                </tr>
                <tr>
                    <th>Username:</th>
                    <td><c:out value="${employee.userName}"/></td>
                </tr>
                <tr>
                    <th>Date of birth:</th>
                    <td><c:out value="${employee.dateOfBirth}"/></td>
                </tr>
                <tr>
                    <th>Age:</th>
                    <td><c:out value="${employee.age}"/></td>
                </tr>
            </c:forEach>
            </tbody>
            </table>
        </c:if>
    </section>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>

</body>
</html>