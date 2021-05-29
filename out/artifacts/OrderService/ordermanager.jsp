
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>饮品管理</title>
    <link rel="stylesheet" href="css/bootstrap.min.css"/>
    <link rel="stylesheet" href="css/bootstrap-theme.min.css"/>
    <link rel="stylesheet" href="css/bootstrap-table.min.css" />
</head>
<body>
<div class="container">

<nav class="navbar navbar-default" role="navigation">
   <div class="navbar-header">
      <a class="navbar-brand" href="#">后台管理</a>
   </div>
   <div>
      <ul class="nav navbar-nav">
         <li><a href="userManager.do">用户管理</a></li>
         <li ><a href="shopManager.do">店铺&饮品管理</a></li>
         <li  class="active"><a href="orderManager.do">订单&评论管理</a></li>
         
      </ul>
   </div>
</nav>
<div id="toolbar" class="fixed-table-toolbar" style="margin-bottom: -40px">
</div>
<table id="table" data-toggle="table"
       data-url="getAllOrder.do"
       data-click-to-select="true"
       data-query-params="queryParams"
       data-pagination="false"
       data-search="true"
       data-height="600">
    <thead>

    <tr>
        <th data-field="username">用户名</th>
        <th data-field="drinkname">饮品名称</th>
        <th data-field="shopname">店铺名称</th>
        <th data-field="price">价钱</th>
        <th data-field="ordertime">订餐时间</th>
        <th data-field="suggesttime">配餐时间</th>
        <th data-field="content">评论</th>
     <th data-field="action" data-formatter="actionFormatter" data-events="actionEvents">操作</th> 
    </tr>
    </thead>
</table>
</div>
<script src="js/jQuery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/bootstrap-table.min.js"></script>
<script src="js/bootstrap-table-zh-CN.min.js"></script>
<script>
    function queryParams() {
        return {
            type: 'owner',
            sort: 'updated',
            direction: 'desc',
            per_page: 100,
            page: 1
        };
    }

    var $table = $('#table'), $add = $('#add');

    $(function () {
      /*   $table.on('check.bs.table uncheck.bs.table check-all.bs.table uncheck-all.bs.table', function () {
            $add.prop('disabled', !$table.bootstrapTable('getSelections').length);
        });
        */
    });


    function actionFormatter(value, row, index) {
        return [
            '<button class="btn btn-danger delete">删除评论</button>'
        ].join('');
    }
    function picFormatter(value, row, index) {
    	console.log(value, row, index);

        return [
            '<img src='+value+' width="50p" height="50px"></img>'
        ].join('');
    }

    window.actionEvents = {
        'click .delete': function (e, value, row, index) {
        	$.ajax({
				cache: true,
				type: "POST",
				url:"deleteComment.do",
				data:{order_id:row.order_id},
				async: false,
			    error: function(request) {
			        alert("连接失败");
			    },
			    success: function(data) {
			    	if(data.success==1)
				  		 alert("评论删除成功");
			    	else
			    		alert("评论删除失败");
			    }
			});
        }
     }

</script>
</body>
</html>