
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>新增店铺</title>
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

    <hr>
    <form role="form" id="form" enctype="multipart/form-data">
        <div class="form-group">
            <label for="shopname">名称</label>
            <input type="text" class="form-control" id="shopname"
                   placeholder="请输入名称" required>
        </div>
        <div class="form-group">
            <label for="address">地址</label>
            <input type="text" class="form-control" id="address"
                   placeholder="请输入地址" required>
        </div>
        <div class="form-group">
            <label for="phonenum">电话号码</label>
            <input type="text" class="form-control" id="phonenum"
                   placeholder="请输入电话号码" required>
        </div>
        <div class="form-group">
            <label for="intro">简介</label>
            <input type="text" class="form-control" id="intro"
                   placeholder="请输入简介">
        </div>
        <div class="form-group">
            <label for="level">等级</label>
            <input type="text" class="form-control" id="level"
                   placeholder="请输入等级" required>
        </div>
        <div class="form-group">
            <label for="pic">选择图片</label>
            <!--  <input type="file" id="pic" name="pic">  -->
            <input type="text" id="pic" name="pic" class="form-control"  placeholder="请输入图片网址">

        </div>
        <button type="submit" class="btn btn-success" id="submit">提交</button>
    </form>
</div>
<script src="js/jQuery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/bootstrap-table.min.js"></script>
<script src="js/bootstrap-table-zh-CN.min.js"></script>
<script>
    var $submit = $('#submit');

    $(function () {
        var shop_id="${shop.shop_id}";
        console.log(shop_id);
        if(shop_id!="")
        {
            $("#shopname").val("${shop.shopname}");
            $("#address").val("${shop.address}");
            $("#phonenum").val("${shop.phonenum}");
            $("#intro").val("${shop.intro}");
            $("#level").val("${shop.level}");
            $("#pic").val("${shop.pic}");
        }
        $submit.click(function () {
            var shopname=$("#shopname").val();
            var address=$("#address").val();
            var phonenum=$("#phonenum").val();
            var intro=$("#intro").val();
            var level=$("#level").val();
            var pic=$("#pic").val();
            if(shop_id!="")
                $.ajax({
                    cache: true,
                    type: "POST",
                    url:"updateShop.do",
                    data:{shopname:shopname,address:address,phonenum:phonenum,intro:intro,level:level,pic:pic,shop_id:shop_id},
                    async: false,
                    error: function(request) {
                        alert("连接失败");
                    },
                    success: function(data) {
                        if(data.success==1){
                            alert("修改成功");
                            var newpage="shopManager.do";
                            window.open(newpage)
                        }

                        else{
                            alert("增加失败,请稍后重试");
                            var newpage="editShop.do?shop_id="+shop_id;
                            window.open(newpage);
                        }
                    }
                }
                );
            else
            {
                $.ajax({
                    cache: true,
                    type: "POST",
                    url:"insertShop.do",
                    data:{shopname:shopname,address:address,phonenum:phonenum,intro:intro,level:level,pic:pic},
                    async: false,
                    error: function(request) {
                        alert("连接失败");
                    },
                    success: function(data) {
                        if(data.success==1){
                            alert("增加成功");
                            var newpage="shopManager.do";
                            window.open(newpage)
                        }
                        else{
                            alert("增加失败,请稍后重试");
                            var newpage="editShop.do?shop_id="+shop_id;
                            window.open(newpage);
                        }
                    }
                });
            }


        });
    });
</script>
</body>
</html>

