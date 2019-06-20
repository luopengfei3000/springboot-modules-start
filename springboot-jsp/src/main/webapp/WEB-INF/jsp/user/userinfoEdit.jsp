<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tags/mms.tld" prefix="mms"%>
<head>
    <meta charset="UTF-8"/>
    <link rel="stylesheet" type="text/css" href="/easyui/themes/default/easyui.css"/>
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

    });
    function saveForm(){
        if ($('#form').form('validate') == false) {
            return;
        }
        parent.userinfo.save(serializeObject($('#form')),"#edit");
    }
    function closeForm() {
        parent.userinfo.closeDialog("#edit");
    }
</script>

<body class="easyui-layout" fit="true">
<div data-options="region:'center',split:true,border:false">
    <form id='form'>
        <input type="hidden" name="userId" value="${user.userId}"/>
        <table>
            <tr>
                <th width="15%"><span style="color: red;">*</span>用户姓名:</th>
                <td width="35%">
                    <input name="userName" class="easyui-validatebox" required="true" value="${user.userName}"/>
                </td>
                <th width="15"><span style="color: red;">*</span>用户密码:</th>
                <td width="35%">
                    <input name="password" class="easyui-validatebox" required="true" value="${user.password}"/>
                </td>
            </tr>
            <tr>
                <th width="15%"><span style="color: red;">*</span>电话号码:</th>
                <td width="35%">
                    <input name="phone" class="easyui-validatebox" required="true" value="${user.phone}"/>
                </td>
                <th width="15"></th>
                <td width="35%">
                </td>
            </tr>
        </table>
    </form>
    <div id="toolbar" class="datagrid-toolbar datagrid-toolbar-extend foot-formopera">
        <table class="tableForm" border="0" cellspacing="1" width='100%'>
            <tr>
                <td width="50%" align="right">
                    <a title="保存" id="saveButton" class="easyui-linkbutton primary-btn" onclick="saveForm();" href="javascript:void(0);">保存</a>
                    <a title="返回" id="returnButton" class="easyui-linkbutton" onclick="closeForm();" href="javascript:void(0);">返回</a>
                </td>
            </tr>
        </table>
    </div>
</div>

</body>
</html>