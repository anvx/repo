<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Download</title>

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/style/itechart.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/style/uploadapp.css">
</head>
<body>
<div class="jlab-row">
    <div class="row-12 grid-12">
        <h2>Available files on server</h2>
    </div>

    <div class="row-6">
        <form action="download" method="post" class="railway">
            <div class="subgrid">

                <div class="row-1">
                    <label for="select_file"> Select file which you want to download</label>
                </div>

                <div class="row-1 grid-1">
                    <select name="fileNameToDownload" id="select_file" required>
                        <c:forEach var="file" items="${filesInUploadDir}">
                            <option><c:out value="${file}"/></option>
                        </c:forEach>
                    </select>
                </div>

                <div class="row-1 button grid-1">
                    <label for="save_button">Download
                        <input type="submit" value="save" name="button" id="save_button">
                    </label>
                </div>

                <div class="row-1 button grid-1">
                    <label for="open_button">Open
                        <input type="submit" value="open" name="button" id="open_button">
                    </label>
                </div>

            </div>
        </form>
    </div>
</div>
</body>
</html>
