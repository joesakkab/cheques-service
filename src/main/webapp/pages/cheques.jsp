<!DOCTYPE html>
<html lang="java">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Cheques Service</title>
<%--    <style>--%>
<%--        table, th, td {--%>
<%--            border: 1px solid black;--%>
<%--            border-collapse: collapse;--%>
<%--            padding: 10px--%>
<%--        }--%>
<%--    </style>--%>
</head>
<h1>Welcome to Cheques Service!</h1>
<body>

    <form action="addCheque">
        <input type="number" name="chequeAmount" placeholder="Enter cheque amount"><br>
        <input type="text" name="chequeNumber" placeholder="Enter cheque number"><br>
        <input type="text" name="chequeDigit" placeholder="Enter cheque digit"><br>
        <input type="submit" value="Submit Cheque Info"><br>
    </form>

    <br><br>
    <form action="getCheque">
        <input type="text" name="chequeId" placeholder="Enter cheque id"><br>
        <input type="submit" value="Get Cheque Info"><br>
    </form>
</body>
</html>