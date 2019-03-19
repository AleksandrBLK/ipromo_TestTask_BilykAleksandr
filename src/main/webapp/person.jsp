<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Person</title>
    <style>
        body {
            margin: 50px;
        }
        dl {
            background: none repeat scroll 0 0 #FAFAFA;
            margin: 8px 0;
            padding: 0;
        }

        dt {
            display: inline-block;
            width: 170px;
        }

        dd {
            display: inline-block;
            margin-left: 8px;
            vertical-align: top;
        }
    </style>
</head>
<body>
<section>
    <hr>
    <form method="post" action="person">
        <dl>
            <dt>Name:</dt>
            <dd><input type="text" value="${person.name}" size=40 name="PersonName" required></dd>
        </dl>
        <dl>
            <dt>Second Name:</dt>
            <dd><input type="text" value="${person.secondName}" name="SecondName" required></dd>
        </dl>
        <dl>
            <dt>Phone:</dt>
            <dd><input type="text" value="${person.phone}" name="Phone" required></dd>
        </dl>
        <dl>
            <dt>Email:</dt>
            <dd><input type="text" value="${person.email}" name="Email" required >
            </dd>
            <dd>${message}</dd>
        </dl>


        <dl>

            <c:forEach items="${stockclass}" var="stockclass">

                <dt>${stockclass.stock}</dt>
                <dl>
                    <input type="radio" value="${stockclass.stock}" name="stock" required>
                </dl>

            </c:forEach>

        </dl>
        <button type="submit">GenerateCode</button>
    </form>
</section>
</body>
</html>
