<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Trang Chủ</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: Arial, sans-serif;
        }

        header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 20px;
            background-color: #f0f0f0;
        }

        h1 {
            font-size: 24px;
        }

        .auth-buttons {
            display: flex;
            gap: 10px;
        }

        button {
            padding: 10px 20px;
            font-size: 16px;
            cursor: pointer;
        }

        button#login-btn {
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 5px;
        }
        button#register-btn {
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 5px;
        }

    </style>
</head>
<body>
<header>
    <h1>Chào mừng đến với Website</h1>
    <div class="auth-buttons">
        <button id="register-btn">Đăng ký</button>
        <button id="login-btn">Đăng nhập</button>
    </div>
</header>

<main>
    <p>Đây là trang chủ của website.</p>
</main>

<script>
    document.getElementById('register-btn').addEventListener('click', function() {
        window.location.href = window.location.href + '/register';
    });
    document.getElementById('login-btn').addEventListener('click', function() {
        window.location.href = window.location.href + '/login';
    });


</script>
</body>
</html>
