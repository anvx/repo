<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Upload result</title>

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/style/itechart.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/style/uploadapp.css">
</head>
<body>

<div class="jlab-row">

    <div class="row-12 grid-12">
        <h3>Upload has been done successfully!</h3>
    </div>

    <div class="row-6 linkgrid">
        <a href="${pageContext.request.contextPath}/upload" id="upload_link">Upload another file</a>
    </div>

    <div class="row-6 linkgrid">
        <a href="${pageContext.request.contextPath}/download" id="download_link">Download files</a>
    </div>

</div>

</body>
</html>
