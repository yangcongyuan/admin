<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>
        <sitemesh:write property='title' /> - 万数引力
    </title>
    <sitemesh:write property='head' />
</head>
<body>
<header>header</header>
<hr />
demo.html的title将被填充到这儿：
<sitemesh:write property='title' /><br />
demo.html的body将被填充到这儿：
<sitemesh:write property='body' />
<hr />
<footer>footer</footer>
</body>
</html>
