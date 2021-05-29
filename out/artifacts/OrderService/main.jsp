
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>用户管理</title>
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
         <li class="active"><a href="userManager.do">用户管理</a></li>
         <li ><a href="shopManager.do">店铺&饮品管理</a></li>
         <li><a href="orderManager.do">订单&评论管理</a></li>
         
      </ul>
   </div>
</nav>
<div id="toolbar" class="fixed-table-toolbar" style="margin-bottom: -40px">
    <!-- <button id="add" class="btn btn-danger add">
        新增用户
    </button> -->
</div>
<table id="table" data-toggle="table"
       data-url="getAllUsers.do"
       data-click-to-select="true"
       data-query-params="queryParams"
       data-pagination="false"
       data-search="true"
       data-height="600">
    <thead>

    <tr>
        <th data-field="user_id">ID</th>
        <th data-field="username">用户名</th>
        <th data-field="userpass">密码</th>
        <th data-field="mobilenum">电话</th>
        <th data-field="address">地址</th>
        <th data-field="comment">备注</th>
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

    function actionFormatter(value, row, index) {
        return [
            '<button class="btn btn-primary update">密码重置</button>'
           
        ].join('');
    }

    window.actionEvents = {
        'click .update': function (e, value, row, index) {
        	$.ajax({
				cache: true,
				type: "POST",
				url:"resetPassword.do",
				data:{user_id:row.user_id},
				async: false,
			    error: function(request) {
			        alert("连接失败");
			    },
			    success: function(data) {
			    	if(data.success==1)
				  		 alert("密码重置为：123456");
			    	else
			    		alert("密码重置失败");
			    }
			});
        }
        }

</script>
</body>
</html>