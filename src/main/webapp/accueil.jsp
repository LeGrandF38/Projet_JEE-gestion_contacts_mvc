<%@ page import="java.util.List" %>
<%@ page import="model.Contact" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Accueil</title>
</head>
<body>
<h1>Liste des CONTACTS :</h1>
<table border="2">
  <tr>
    <th>CONTACT Id</th>
    <th>First NAME</th>
    <th>LAST NAME</th>
    <th>EMAIL</th>
    <th>Phone Number</th>
    <th>Address</th>
    <th>Modifier</th>
    <th>Supprimer</th>
  </tr>
  <c:forEach var="contact" items="${requestScope.LISTCONTACTS}">
    <tr>
      <td>${contact.ID_CONTACT}</td>
      <td>${contact.FIRSTNAME}</td>
      <td>${contact.LASTNAME}</td>
      <td>${contact.EMAIL}</td>
      <td>${contact.PHONE}</td>
      <td>${contact.ADDRESS}</td>
      <td><a href="updateContact.jsp?contact_id=${contact.ID_CONTACT}&lastName=${contact.LASTNAME}&firstName=${contact.FIRSTNAME}&email=${contact.EMAIL}&phone=${contact.PHONE}&address=${contact.ADDRESS}">Modifier</a></td>
      <td><a href="ControllerServlet?do_this=delete&contact_id=${contact.ID_CONTACT}">Supprimer</a></td>
    </tr>
  </c:forEach>
</table>

<form action="addContact.jsp">
  <input type="submit" value="Ajouter un contact">
</form>
</body>
</html>
