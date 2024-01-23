<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="style.css">
    <title>Modifier un contact</title>
</head>
<body>
<h1>Modifier un contact</h1>

<form action="ControllerServlet" method="post">
    <input type="hidden" name="do_this" value="update">
    <input type="hidden" name="contact_id" value="${param.contact_id}">

    <label for="lastName">Nom :</label>
    <input type="text" id="lastName" name="lastName" value="${param.lastName}" required><br>

    <label for="firstName">Prénom :</label>
    <input type="text" id="firstName" name="firstName" value="${param.firstName}" required><br>

    <label for="email">Email :</label>
    <input type="email" id="email" name="email" value="${param.email}" required><br>

    <label for="phone">Numéro de téléphone :</label>
    <input type="text" id="phone" name="phone" value="${param.phone}" required><br>

    <label for="address">Adresse :</label>
    <input type="text" id="address" name="address" value="${param.address}" required><br>

    <input type="submit" value="Modifier le contact">
</form>

<br>
<a href="ControllerServlet">Retour à la liste des contacts</a>
</body>
</html>
