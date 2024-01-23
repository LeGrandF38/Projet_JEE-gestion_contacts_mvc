<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Supprimer un contact</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
<h1>Supprimer un contact</h1>

<p>Êtes-vous sûr de vouloir supprimer ce contact ?</p>

<form action="ControllerServlet" method="post">
    <input type="hidden" name="do_this" value="delete">
    <input type="hidden" name="contact_id" value="${param.contact_id}">

    <input type="submit" value="Confirmer la suppression">
</form>

<br>
<a href="ControllerServlet">Annuler</a>
</body>
</html>
