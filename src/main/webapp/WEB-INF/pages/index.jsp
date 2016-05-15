<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="NOT_IMPORT_FRUIT_NAME" value="国产水果"/>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>大家和果业</title>

    <link href="http://cdn.bootcss.com/bootstrap/3.3.4/css/bootstrap.min.css" rel="stylesheet">
    <style type="text/css">
        * {
            font-family: "Helvetica Neue",Helvetica,Microsoft Yahei,Hiragino Sans GB,WenQuanYi Micro Hei,sans-serif;
        }

        ::-moz-selection {
            background: lightpink;
        }

        ::selection {
            background: lightpink;
        }

        .desc {
            background-color: #f5f5f5;
            padding-top: 30px;
            padding-bottom: 30px;
            margin-top: -30px;
        }

        .desc h1 {
            font-size: 60px;
        }
        .desc p {
            margin-bottom: 0px;
            font-weight: 300;
            line-height: 1.4;
            font-size: 24px;
        }

        .img-fix-width {
            width: 100%;
        }

        .page-header a {
            text-decoration: none;
        }

        .to_top {
            position: fixed;
            right: 50px;
            bottom: 50px;
        }

        @media (max-width: 768px) {
            .category {
                text-align: center;
            }
            .to_top {
                position: fixed;
                right: 50px;
                bottom: 50px;
                opacity: 0.7;
            }
        }
        
        .fruit-catalog {
        	font-size:20px;
        	font-weight:20;
        }
    </style>
</head>
<body>

<nav class="navbar navbar-default">
    <div class="container">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <img alt="" src="static/images/djh/logo.png">
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li class="fruit-catalog"><a href="#">首页</a></li>
                <li class="fruit-catalog"><a href="#">精品水果 </a></li>
                <li class="fruit-catalog"><a href="#">${NOT_IMPORT_FRUIT_NAME}</a></li>
             <!--    <li class=""><a href="#">公司介绍</a></li>
                <li class=""><a href="#">合作流程</a></li> -->
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>

<div class="desc">
    <div class="container">
        <h1>${date}报价</h1>
        <p>
            下面是本水果行最新的报价，若有疑问，可致电0574-27819589进行咨询，欢迎各位新老客户选购！
        </p>
    </div>
</div>
<div class="container">
    <div class="row" id="importListContaner">
        <h1 id="gcsg" class="page-header category"><a href="#gcsg">精品水果</a></h1>

        <c:forEach items="${importList}" var="item">
        <div class="col-sm-6 col-md-3">
            <div class="thumbnail">
                <img class="img-fix-width" data-holder-rendered="true" src="${item.mainUrl}">
                <div class="caption">
                    <h3 >${item.name}<small>${item.price}元/${item.unit}</small></h3>
                </div>
                <p>${item.summary}</p>
            </div>
        </div>
        </c:forEach>
    </div>
    
    <div class="row" id="nationListContainer">
        <h1 id="gcsg" class="page-header category"><a href="#gcsg">${NOT_IMPORT_FRUIT_NAME}</a></h1>

        <c:forEach items="${nationList}" var="item">
        <div class="col-sm-6 col-md-3">
            <div class="thumbnail">
                <img class="img-fix-width" data-holder-rendered="true" src="${item.mainUrl}">
                <div class="caption">
                    <h3 >${item.name}<small>${item.price}元/${item.unit}</small></h3>
                </div>
                <p>${item.summary}</p>
            </div>
        </div>
        </c:forEach>
    </div>
    
</div>

<div class="to_top">
    <a href="#"><button type="button" class="btn btn-default"><span class="glyphicon glyphicon-plane"></span></button></a>
</div>
<script type="text/javascript" src="http://apps.bdimg.com/libs/jquery/1.11.1/jquery.min.js"></script>
<script type="text/javascript" src="http://apps.bdimg.com/libs/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/djh/index.js"></script>
</body>
</html>