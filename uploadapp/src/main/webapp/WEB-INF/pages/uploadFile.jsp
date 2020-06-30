<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Upload files</title>

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/style/itechart.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/style/uploadapp.css">
</head>
<body>

<div class="jlab-row">

    <div class="row-12 grid-12">
        <h2>Upload File Application</h2>
    </div>

    <div class="row-12 grid-12">
        <p>Allowed files .jpg, .gif, .png. Maximum size is 2Mb.</p>
    </div>

    <div class="row-6">

        <form method="post" action="${pageContext.request.contextPath}/upload"
              enctype="multipart/form-data" class="railway">

            <div class="subgrid">
                <div class="row-1 button">
                    <label for="file">Select file
                        <input accept=".png, .jpg, .gif" id="file" name="file" type="file" required/>
                    </label>
                </div>

                <div class="preview row-1">
                    <div class="error">
                        ${errorMessage}
                    </div>
                    <%--<p class="error">No files currently selected for upload</p>--%>
                </div>

                <div class="row-1 button">
                    <label for="submit">Upload
                        <input type="submit" name="button" value="upload" id="submit"/>
                    </label>
                </div>

            </div>
        </form>

    </div>

    <div class="row-6 linkgrid">
        <a href="${pageContext.request.contextPath}/download">Download files</a>
    </div>

</div>

</body>

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/script/validation.js"></script>

</html>