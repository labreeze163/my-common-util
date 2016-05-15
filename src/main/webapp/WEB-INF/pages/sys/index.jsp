<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>大家和果业</title>

    <link rel="stylesheet" href="${ctx}/static/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${ctx}/static/manage/style.css">
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

        td {
            vertical-align: middle;
        }
        td input.price, .unit {
            border-top: none;
            border-left: none;
            border-right: none;
            width: 100px;
        }
    </style>

    <script type="text/javascript">
        //获取商品信息，弹出编辑窗口
        var openEditWindow = function(id) {
            $("#myModal").modal('show');
            //获取单个商品信息
            var url = "${ctx}/sys/commodity/" + id + ".do";
            var param = {};
            $.post(url, param, function(data){
                if("E" == data.result) {
                    alert(data.message);
                    return;
                } else {
                    var commodity = data.commodity;
                    $("#_id").val(commodity.id);
                    $("#_name").val(commodity.name);
                    $("#_summary").val(commodity.summary);
                    $("#_mainUrl").val(commodity.mainUrl);
                    $("#_description").val( commodity.description );
                }
            });
        }
        //提交修改商品信息
        var save = function() {
            var url = "${ctx}/sys/modify.do";
            var param = {
                id          :   $("#_id").val(),
                name        :   $("#_name").val(),
                summary     :   $("#_summary").val(),
                description :   $("#_description").val(),
                mainUrl     :   $("#_mainUrl").val()
            };
            $.post(url, param, function(data){
                $('#myModal').modal('hide');//关闭弹出框
            });
        }
        //查询某天报价
        var search = function() {
            window.location.href= "?date=" + $("#_date").val();
        }
        var updateDailyPrice = function(id) {
            var url = "${ctx}/sys/updateDailyPrice.do";
            var param = {
                id: id,
                price: $("#price_"+id).val(),
                unit:  $("#unit_"+id).val(),
                date:  $("#_date").val()
            };
            $.post(url, param, function(data){
                alert(data.message);
            });
        }
    </script>

</head>
<body>

<div class="container-fluid">
    <div class="container-fluid">
        <!--搜索条件 -->
        <form class="form-inline" action="../" method="get">
            <div class="form-group">
                <label class="sr-only" for="_date">Amount (in dollars)</label>
                <div class="input-group">
                    <div class="input-group-addon">日期</div>
                    <input type="text" class="form-control" id="_date" value="${date}">
                </div>
            </div>
            <button type="button" class="btn btn-primary" onclick="search()">查询</button>
        </form>

        <hr/>

        <table class="table table-striped">
            <thead>
            <tr>
                <th>#</th>
                <th>名称</th>
                <th>当前价格</th>
                <th>单位</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${list}" var="item">
            <tr>
                <th scope="row">${item.id}</th>
                <td><a href="#">${item.name}</a></td>
                <td>
                    <input type="text" id="price_${item.id}" class="form-control price" value="${item.price}">
                </td>
                <td>
                    <input type="text" id="unit_${item.id}" class="form-control unit" value="${item.unit }">
                </td>
                <td>
                    <a role="button" class="btn btn-default" href="#"
                            onclick="openEditWindow(${item.id})"
                            >编辑</a>
                    <a role="button" class="btn btn-success" href="#"
                            onclick="updateDailyPrice(${item.id})"
                            >更新</a>
                </td>
            </tr>
            </c:forEach>
            </tbody>
        </table>

    </div>
    <hr>
    <footer>
        <p>&copy; 大家和果业 2015</p>
    </footer>
</div>

<!-- 编辑窗口 -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">商品编辑</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label for="_name" class="col-sm-2 control-label">名称</label>
                        <div class="col-sm-10">
                            <input type="hidden" id="_id">
                            <input type="text" class="form-control" id="_name" placeholder="名称">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="_mainurl" class="col-sm-2 control-label">缩略图</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="_mainUrl" placeholder="缩略图">
                            <%--<img id="_mainUrlShow" src="data:image/.png;base64,iVBORw0KGgoAAAANSUhEUgAAAQ4AAACWCAIAAACgkfFoAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsMAAA7DAcdvqGQAAAhLSURBVHhe7ZjbdSM5DAU3c8cwITiFCcEhTAiOZPVsN0CQvHy1ZKnqa8ciQABEyT773zcACKAKgASqAEigCoAEqgBIoAqABKoASKAKgASqAEigCoAEqgBIoAqABKoASKAKgASqAEigCoAEqgBIoAqABKoASKAKgASqAEigCoAEqgBIoAqABKoASKAKgASqAEigCoAEqgBIoAqABKoASKAKgASqAEigCoAEqgBIoAqABKoASKAKgASqAEigCoAEqgBIoAqABKoASLyoKv/+/vn48/ff7V8A4zxcla/Pj4+Pz6/bv8Y5S7IxMe8xzJ4GzOPRqlx2Y2P4F4FNd+JXrZ3R/ATOPBOPVcXvxoQ/mn6xLG4a8/+ATGbzjDzt380PVWXNt+hvlcXVvWBlUGWER6qyajeS31W/QZYj/vhClREeqIp7uJm7kezE087/zqqvDQOqjPAwVRb/Ye63Ykb+9LdVK9mvg4VfGztQZYRHqeLWbv5ubBfMS71MlfHEG8VFs6os8rEdWxaqWPTpTFyjFqI9WqXKzC/7haqco5fohSp53M4V5//yqswUZZ0qpvfJwqBKlqbRPKkqrup9Sza0smOz21ujSlLl1H1GlQxu7LUHm71LIgepYrekZXtvuATlDJ2q+Cdor7IMqsS0Ppd9p4VzrBc2XRV75ZnGLWxc4tbZX3BFNlaoYG9AlSvti/+6qvg9v9DSoE9QjW1XJaxxkOReVAmov9X5RGGhXkeV7BLKLdqClbh6ixZ3wySSe+01qHLCboe4jDboPsfLdOtv3UJ9j4Lq7uyDbaipf/vIduWQ+vJrrATVW9xRLHGE5F5bFqpkdn5P8jjnoYZhK4Zb36N9KUOqpI3+tT+JbjfYYoWAC/UW7yQVTiS5d8VrLuAwVerPFD9/oIp7x8Nmu793QJVkDaOfRgPaiCdVp/4GF5IC+0fsKw1vtYfeXZX6I/n3yT9/y1JNZX9xtyrJHm7H3Vrl+sonqFJ/hSD9Df2WDdfQiVgDe+69VbHjD4fuxlqYVzDY9E1mkNSwb8N9uK/A9md6939oucPCEHyrhUGl2ODgHcqDbLIlSJWLt0ebOjqSA1SxooSTcF9lpWlFcy2/cC9JFRNUOX2UP3rCdeJucWMqDyrAZneXJ8nPB/wPk4JD0lTFSFtWY0/HsV4V9/oCham6R7idbL9CYZEqJ26nwz59Lz+Hki7D+BI2wz48yf3zqf+ossqRJpVK7Q1vrErwDEVKY7XvsA218QaRdapcfprtM+nmfDL5Yc9G2ST3AoLpudrCigKCTCeyjd6xYe+sSmaCMcW52kTVJ9hwBeiBlomqlDExIX0thPNLXifMnZxyE8iULO29zf3WqgRzvr+Hm3BxTO6svi3dgY59nqWqnAhGttHdgE26S7N9UHoAN8YL5/O5UuWdtwnksKM5RJUcbvjlIXUPdJYph6oSb+aJ/vILqpw4fSbMNFNUSkuZqFLFjqg83YF9n/YSh6pSWMruFuwkWma4p6ZLc3moUqFFFH+4ZZzzHqK2I3XE7XTdhvQ0YvP2qFIfQUfWeS+0lEep4mZeHnDyQA3jtLFD73CEKo13tPXTr0pv69IdqFLATb4y0PRLNhpneqqLQi2rVcl0cA0qtidtpM8hBNU7PiUZttuWhSo73Gwrs4l25NVUyWV2fVYLqGy/qIo0yTS65wEuDdpAVNloEyXejser4irYX24zmAaC5Nmyc4PR+gzbsKHmSNXCO4X53JBT3ZPZslDlhptjlyivoUpho+rbojXr86SqyEOrlxRQyX7PaY913XQEx6rSJkp+1FFgugcicuAsVbJtnWkovJwoGFLUaWuOPqLvhS23LWHWldM5UhX3KrWZFB4xCo32QEIOnKDKZ76n7h3JzCloJOw02OKkkp/A3iJ33C78yWTLmnDDGg5Txb+Im4gZ1/kR/fy+9vHROMM9UJADJ6iS+f9FM9bDDyzImOn0+uNCDTYuYbR6ofJn4CBVKsO2nIeVvKpZsWicSYSKHDhFlRP7w/MX43JhnLZzRDZMomH8Pv/8iUziGFXahn0d1s+OXeb+Qqpcj1//u20yFWpN941oqETlEnvBm6ti9qXKfbzXqNu/XkqVH4b20FNruntEd5qrle6wWd9clfKM88P5+txmjSp1ak13j6hEqQPtCpvh3VX5WZjeB0KVOrWmu0fUxnaNeIMt691VGadRlW4Kz7telebtbQsfvGwVtixUGQVVItrCpdO7gg/aWlsWqoyCKhFt4cppU2/Agk22ZaHKKKgS0RYunK6ZEtFctcOWhSqjNKrS8Hxy4Buo0mNKSmMbtixUGQVVItrCG07bo4NU6rJ3ocooqBLRFj5ymemjj5wEtixUaWAb3f4xn0yVPtLkI9t7oi188DKDzaWQdcCmQpUy4eDN0FAloi188LISNnVEXgEbiyoeYe9QpUpb+OBlMtGgCgbYslClY9HMW6JKRFv44GV9XC8tCWDLemdV7CSqZF7wyVSRH9TUnSbvrvpKW/jgZauwZb2zKvUvY2U6qBLRFj542SpsWe+sSuhK80AaVenmoaoM0qbKk/LWqtzXZehr7LeqUgFVPO+tygyeQJUVoIoHVUZBlTqospJfowrAY0EVAAlUAZBAFQAJVAGQQBUACVQBkEAVAAlUAZBAFQAJVAGQQBUACVQBkEAVAAlUAZBAFQAJVAGQQBUACVQBkEAVAAlUAZBAFQAJVAGQQBUACVQBkEAVAAlUAZBAFQAJVAGQQBUACVQBkEAVAAlUAZBAFQAJVAGQQBUACVQBkEAVAAlUAZBAFQAJVAGQQBUACVQBkEAVAAlUAZBAFQAJVAGQQBUAge/v/wGYq0Gco+a/xAAAAABJRU5ErkJggg==">--%>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="_summary" class="col-sm-2 control-label">简介</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="_summary" placeholder="简介">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="_description" class="col-sm-2 control-label">描述</label>
                        <div class="col-sm-10">
                            <textarea class="form-control" rows="5" id="_description" placeholder="描述"></textarea>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-success" onclick="save()">保存</button>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript" src="${ctx}/static/js/jquery-2.1.0.min.js"></script>
<script type="text/javascript" src="http://apps.bdimg.com/libs/jquery/1.11.1/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/static/bootstrap/js/bootstrap.js"></script>
<!--<script type="text/javascript" src="http://apps.bdimg.com/libs/bootstrap/3.3.0/js/bootstrap.min.js"></script>-->

</body>
</html>