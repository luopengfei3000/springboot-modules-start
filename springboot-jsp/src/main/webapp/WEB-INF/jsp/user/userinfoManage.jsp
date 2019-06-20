<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tags/mms.tld" prefix="mms"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link rel="stylesheet" type="text/css" href="/easyui/themes/default/easyui.css"/>
    <link rel="stylesheet" type="text/css" href="/css/page.css"/>
    <link rel="stylesheet" type="text/css" href="/easyui/themes/icon.css"/>
    <link rel="stylesheet" type="text/css" href="/easyui/demo/demo.css"/>
    <script type="text/javascript" src="/easyui/jquery.min.js"></script>
    <script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/easyui/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="/common/CommonDialog.js"></script>
    <script type="text/javascript" src="/extend/easyUiExtend.js"></script>
    <script type="text/javascript" src="/extend/Tools.js"></script>
    <script type="text/javascript" src="/userinfo/userinfo.js"></script>
</head>
<script type="text/javascript">
    var userinfo;
    $(function () {
        userinfo = new Userinfo('dgUserinfo', '${url}', 'searchDialog', 'userinfo');
        userinfo.init();
    });

</script>
<body>
<div class="easyui-layout" data-options="fit:true">
    <div data-options="region:'north',title:'查询条件'" border="false" style="height: 119px;background: #fafafa;" iconCls="icon-search">
        <form id="userinfo">
            <table width="100%">
                <tr>
                    <td width="10%">11:</td>
                    <td width="50%">
                        <input class="easyui-validatebox" type="text" name="name"/>

                    </td>
                    <td width="10%">22</td>
                    <td width="50%">
                        <input class="easyui-validatebox" type="text" name="name" />
                    </td>
                </tr>
                <tr>
                    <td width="10%">33:</td>
                    <td width="50%">
                        <mms:mmslookup name="validFlag" lookupCode="VALID_FLAG" isNull="true"
                                       dataOptions="width:180,editable:false,panelHeight:'auto'">
                        </mms:mmslookup>
                    </td>
                    <td width="10%">44</td>
                    <td width="50%">
                        <select id="cc" class="easyui-combobox" name="dept" style="width:200px;">
                            <option value="aa">aitem1</option>
                            <option>bitem2</option>
                            <option>bitem3</option>
                            <option>ditem4</option>
                            <option>eitem5</option>
                        </select>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div data-options="region:'center',title:'用户列表'">
        <div id="toolbarUserinfo" class="datagrid-toolbar">
            <table>
                <tr>
                    <td><a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="userinfo.insert()">添加用户</a></td>
                    <td><a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cut" plain="true" onclick="userinfo.del()">删除用户</a></td>
                    <td><a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="userinfo.modify()">修改信息</a></td>
                    <td><a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="userinfo.uploadpic()">上传</a></td>
                </tr>
            </table>
        </div>
        <table id="dgUserinfo" class="easyui-datagrid" style="width:100%;height:100%"
               data-options="
                    fit: true,
                    border: false,
                    rownumbers: true,
                    animate: true,
                    collapsible: false,
                    fitColumns: true,
                    autoRowHeight: false,
                    idField :'userId',
                    toolbar:'#toolbarUserinfo',
                    singleSelect: true,
                    checkOnSelect: true,
                    selectOnCheck: false,
                    pagination:true,
                    pageSize:dataOptions.pageSize,
                    pageList:dataOptions.pageList,
                    striped:true">
            <thead>
            <tr>
                <th data-options="field:'userId', halign:'center',checkbox:true" width="220">表主键ID</th>
                <th data-options="field:'userName',align:'center',width:'220'">姓名</th>
                <th data-options="field:'password',align:'center',width:'220'">密码</th>
                <th data-options="field:'phone',align:'center',width:'220'">电话</th>
            </tr>
            </thead>
        </table>
    </div>
</div>
</body>
</html>