<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><tiles:insertAttribute name="title" ignore="true" /></title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <style>
        /* Set height of the grid so .sidenav can be 100% (adjust if needed) */
        .row.content {height: 661px}

        /* Set gray background color and 100% height */
        .sidenav {
            background-color: #f1f1f1;
            height: 100%;
        }

        /* Set black background color, white text and some padding */
        footer {
            background-color: #555;
            color: white;
            padding: 15px;
        }
        /* On small screens, set height to 'auto' for sidenav and grid */
        @media screen and (max-width: 600px) {
            .sidenav {
                height: auto;
                padding: 15px;
            }
            .row.content {height: auto;}
        }
    </style>
</head>
<body>
<div class="container-fluid">
    <div class="row content">
        <div class="col-sm-3 sidenav">
            <h4><tiles:insertAttribute name="header" /></h4>
            <ul class="nav nav-pills nav-stacked">
                <h4><tiles:insertAttribute name="menu"/></h4>
            </ul><br>
        </div>
        <div class="col-sm-9">
            <h4><small><tiles:insertAttribute name="body" /></small></h4>
        </div>
    </div>
</div>
<footer class="container-fluid">
    <p><tiles:insertAttribute name="footer" /></p>
</footer>
</body>
</html>