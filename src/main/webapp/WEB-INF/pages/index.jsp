<html>
<body>
<script  src="/js/jquery.min.js"></script>
<h2>Hello World!</h2>

<textarea id="edit-email-name" name="email_name" rows="5" cols="60" placeholder="输入邮箱地址,支持批量"></textarea>
<button id="tijiao">提交</button>
</body>
<script>
    $("#tijiao").click(function () {
        $.ajax({
            type: "POST",
            url:  "/demo/",
            data: "email_name="+$('#edit-email-name').text(),// 你的formid
            async: false,
            success:function (data) {
                alert("success");
            }
        })
    })
</script>
</html>
