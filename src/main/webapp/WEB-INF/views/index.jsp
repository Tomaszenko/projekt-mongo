<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<body>
<h2>Hello World!</h2>
<p>
    <h1>Thanks for clicking on the link and making the controller do some work..</h1>
</p>
${ user }
<td>
    <spring:url value="/admin/blogs/${user.id}" var="userUrl" />
    <spring:url value="/admin/blogs/${user.id}/delete" var="deleteUrl" />
    <spring:url value="/admin/blogs/${user.id}/update" var="updateUrl" />

    <button class="btn btn-info"
            onclick="location.href='${userUrl}'">Query</button>
    <button class="btn btn-primary"
            onclick="location.href='${updateUrl}'">Update</button>
    <button class="btn btn-danger"
            onclick="this.disabled=true;post('${deleteUrl}')">Delete</button>
</td>
</body>
</html>
