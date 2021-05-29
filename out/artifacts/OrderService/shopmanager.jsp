
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>店铺管理</title>
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
         <li class="active"><a href="shopManager.do">店铺&饮品管理</a></li>
         <li><a href="orderManager.do">订单&评论管理</a></li>
         
      </ul>
   </div>
</nav>
<div id="toolbar" class="fixed-table-toolbar" style="margin-bottom: -40px">
    <button id="add" class="btn btn-danger remove">
        新增店铺
    </button>
</div>
<table id="table" data-toggle="table"
       data-url="getAllShops.do"
       data-click-to-select="true"
       data-row-style="rowStyle"
       data-query-params="queryParams"
       data-pagination="false"
       data-search="true"
       data-height="600">
    <thead>

    <tr>
        <th data-field="shop_id">ID</th>
        <th data-field="shopname">店铺名称</th>
        <th data-field="address">地址</th>
        <th data-field="phonenum">电话</th>
        <th data-field="intro">简介</th>
        <th data-field="pic" data-formatter="picFormatter">图片</th>
        <th data-field="level">等级</th>
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
    function rowStyle(row, index) {
        var classes = ['active', 'success', 'info', 'warning', 'danger'];
        if (row.level=='1') {
            return {
                classes: 'danger'
            };
        }

        if (row.level=='2') {
            return {
                classes: 'warning'
            };
        }
        if (row.level=='5') {
            return {
                classes: 'active'
            };
        }
        return {};
    }
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
        $table.on('check.bs.table uncheck.bs.table check-all.bs.table uncheck-all.bs.table', function () {
            $remove.prop('disabled', !$table.bootstrapTable('getSelections').length);
        });
        $add.click(function () {
        	var newpage="newshopmanager.jsp";
            window.open(newpage);
        });
    });


    function actionFormatter(value, row, index) {
        return [
            '<button class="btn btn-primary update">修改</button>'+
            '<button class="btn btn-danger delete">删除</button>'+
            '<button class="btn btn-primary food">进入</button>'
        ].join('');
    }
    function picFormatter(value, row, index) {
    	console.log(value, row, index);

        return [
                '<img src='+value+' width="50p" height="50px"></img>'
        ].join('');
    }

    window.actionEvents = {
        'click .update': function (e, value, row, index) {
            //alert('修改, row: ' + JSON.stringify(row));
            console.log(value, row, index);
            var newpage="editShop.do?shop_id="+row.shop_id;
            window.open(newpage);
            
        },
        'click .delete': function (e, value, row, index) {
            $.ajax({
				cache: true,
				type: "POST",
				url:"deleteShop.do",
				data:{shop_id:row.shop_id},
				async: false,
			    error: function(request) {
			        alert("Connection error");
			    },
			    success: function(data) {
			    	if(data.success==1)
				  		 alert("删除成功");
			    	else
			    		alert("删除失败");
			    }
			});
        },
        'click .drink': function (e, value, row, index) {
        	var newpage="drinkManager.do?shop_id="+row.shop_id;
            window.open(newpage);
               
              }
        }

</script>
</body>
</html>