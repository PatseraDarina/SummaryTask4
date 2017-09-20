<%--
  Created by IntelliJ IDEA.
  User: Дарина
  Date: 17.09.2017
  Time: 23:43
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <meta charset="UTF-8">
    <title>22 лучших формы входа и регистрации | Vladmaxi.net</title>
    <link rel="stylesheet" href="<c:url value="/css/add_style.css" />"  type="text/css" />
    <link rel="icon" href="http://vladmaxi.net/favicon.ico" type="image/x-icon">
    <link rel="shortcut icon" href="http://vladmaxi.net/favicon.ico" type="image/x-icon">
    <!--[if lt IE 9]>
    <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
</head>
<body>

<!-- vladmaxi top bar -->
<div class="vladmaxi-top">
    <a href="http://vladmaxi.net" target="_blank">Главная Vladmaxi.net</a>
    <span class="right">
        <a href="http://vladmaxi.net/web-developer/css/22-luchshix-formy-vxoda-i-registracii-na-sajte-v-html-css.html">
                <strong>Вернуться назад к статье</strong>
            </a>
        </span>
    <div class="clr"></div>
</div>
<!--/ vladmaxi top bar -->

<div id="login-form">
    <h1>Add periodicals</h1>

    <fieldset>
        <form action="" method="get">
            <input type="text" name="namePeriodical" required value="Name" onBlur="if(this.value=='')this.value='Логин'" onFocus="if(this.value=='Логин')this.value='' ">
            <input type="password" required value="Пароль" onBlur="if(this.value=='')this.value='Пароль'" onFocus="if(this.value=='Пароль')this.value='' ">
            <input type="submit" value="ADD">
            <footer class="clearfix">
                <p><span class="info">?</span><a href="#">Забыли пароль?</a></p>
            </footer>
        </form>
    </fieldset>
</div>
</body>
</html>
