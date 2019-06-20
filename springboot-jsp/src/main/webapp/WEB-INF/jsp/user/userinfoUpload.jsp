<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tags/mms.tld" prefix="mms"%>
<head>
    <meta http-equiv="Content-Type" content="multipart/form-data; charset=utf-8" />
    <link rel="stylesheet" type="text/css" href="/easyui/themes/default/easyui.css"/>
    <link rel="stylesheet" type="text/css" href="/css/page.css"/>
    <link rel="stylesheet" type="text/css" href="/easyui/themes/icon.css"/>
    <link rel="stylesheet" type="text/css" href="/easyui/demo/demo.css"/>
    <script type="text/javascript" src="/easyui/jquery.min.js"></script>
    <script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/easyui/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="/userinfo/userinfo.js"></script>
    <script type="text/javascript" src="/common/CommonDialog.js"></script>
    <script type="text/javascript" src="/extend/easyUiExtend.js"></script>
    <script type="text/javascript" src="/extend/Tools.js"></script>
</head>
<script type="text/javascript">

    $(function () {
        $('#addUserPhotoDialog').dialog('close',true);
        $("#userPhotoImg").attr("src",parent.userinfo.getUrl()+"upload/headerphoto/"+'${user.userId}');
    });
    function saveForm(){
        if ($('#form').form('validate') == false) {
            return;
        }
        parent.userinfo.save(serializeObject($('#form')),"#insert");
    }
    function closeUpLoadUserPhoto() {
        $('#addUserPhotoDialog').dialog('close',true);
    }
    //打开用户头像选择
    function chooseUserPhoto(){
        $('#addUserPhotoDialog').dialog('open',true);
    }

    //上传头像
    function upLoadUserPhoto(userId){
        if(document.getElementById("sysUserPhoto").value != ''){
            if(checkfiletype('sysUserPhoto')){
                $.messager.progress();  // 显示进度条
                $('#uploadForm').form('submit', {
                    url: parent.userinfo.getUrl()+'uploadimg/'+userId,
                    success: function(){
                         $.messager.progress('close');  // 如果提交成功则隐藏进度条
                         $.messager.alert('提示','头像文件上传成功!','info',function(r){
                             $('#addUserPhotoDialog').dialog('close',true);
                             document.getElementById("userPhotoImg").src = parent.userinfo.getUrl()+"upload/headerphoto/"+userId+"?o="+Math.random();
                        });
                    }
                });
                return;
            }
        } else {
            $.messager.alert('警告','请选择要上传的头像文件!','warning');
            return;
        }
    }


    //检查上传类型
    function checkfiletype(id){
        var fileName = document.getElementById(id).value;
        //设置文件类型数组
        var extArray =[".jpg",".png",".gif",".bmp"];
        //获取文件名称
        while (fileName.indexOf("//") != -1)
            fileName = fileName.slice(fileName.indexOf("//") + 1);
        //获取文件扩展名
        var ext = fileName.slice(fileName.indexOf(".")).toLowerCase();
        //遍历文件类型
        var count = extArray.length;
        for (;count--;){
            if (extArray[count] == ext){
                return true;
            }
        }
        $.messager.alert('错误','只能上传下列类型的文件: '  + extArray.join(" "),'error');
        return false;
    }
</script>

<body class="easyui-layout" fit="true">
<div data-options="region:'center',split:true,border:false">
    <div style="height: 200px;width: 200px">
        <img name="userPhotoImg" id="userPhotoImg" src="" width="110"
             height="120" title="上传个人图片"
             style="cursor: pointer;" onclick="chooseUserPhoto();" />
    </div>

    <!--上传用户头像对话框  -->
    <div id="addUserPhotoDialog" class="easyui-dialog" data-options="iconCls:'icon-add',resizable:true,modal:false,title:'上传头像'" style="width: 600px;height:200px;">
        <form action="" method="post" id="uploadForm" enctype="multipart/form-data" style="margin-top: 20px;">
            <table width="100%" border="0">
                <tr>
                    <td width="10%" align="right" nowrap="nowrap">选择本地头像文件</td>
                    <td width="90%" align="left"><input type=file style='width:90%' id=sysUserPhoto name=sysUserPhoto></td>
                </tr>
            </table>
        </form>
        <div id="upLoadPhotoToolbar" class="datagrid-toolbar datagrid-toolbar-extend" style="height:auto;display: block;">
            <table class="tableForm"  width='100%'>
                <tr>
                    <td align="right"><a title="上传" id="upLoadButton"  class="easyui-linkbutton" iconCls="icon-save" plain="false" onclick="upLoadUserPhoto('${user.userId}');" href="javascript:void(0);">上传</a></td>
                    <td><a title="关闭" id="returnButton"  class="easyui-linkbutton" iconCls="icon-undo" plain="false" onclick="closeUpLoadUserPhoto();" href="javascript:void(0);">关闭</a></td>
                </tr>
            </table>
        </div>
    </div>
</div>

</body>
</html>