<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:url value="/" var="URL"></c:url>
<!DOCTYPE html>
<html>
<head>
    <title>Page Title</title>
</head>
<body>
    <sitemesh:write property ="body" />
    <script src="${URL}assets/global/plugins/jquery.min.js" type="text/javascript"></script>
    <script>
        function chooseFile(fileInput){
            console.log(fileInput.files[0])
            if(fileInput.files && fileInput.files[0]){
                var reader = new FileReader()
                reader.onload = function (e){
                    document.getElementById("HinhAnh").setAttribute('src', e.target.result);
                }
                reader.readAsDataURL(fileInput.files[0])
            }
        }
    </script>
</body>
</html>
