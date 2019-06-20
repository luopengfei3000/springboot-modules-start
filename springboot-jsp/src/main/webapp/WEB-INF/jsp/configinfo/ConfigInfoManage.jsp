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
    <script type="text/javascript" src="/extend/mms-fn.js"></script>
    <script type="text/javascript" src="/configinfo/configInfo.js"></script>
</head>
<script type="text/javascript">
    var configInfo;
    $(function () {
        configInfo = new ConfigInfo('dgConfigInfo', '${url}', 'searchDialog', 'userinfo');
        configInfo.init();
    });
    
    function formatValidFlag(value) {
        return Mms.fn.lookup.formatLookupType(value, configInfo.validFlag);
    }

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
        <div id="toolbarConfigInfo" class="datagrid-toolbar">
            <table>
                <tr>
                    <td><a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="configInfo.insert()">添加</a></td>
                    <td><a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="configInfo.modify()">修改</a></td>
                    <td><a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cut" plain="true" onclick="configInfo.del()">删除</a></td>
                </tr>
            </table>
        </div>
        <table id="dgConfigInfo" class="easyui-datagrid" style="width:100%;height:100%"
               data-options="
                    fit: true,
                    border: false,
                    rownumbers: true,
                    animate: true,
                    collapsible: false,
                    fitColumns: true,
                    autoRowHeight: false,
                    idField :'id',
                    toolbar:'#toolbarConfigInfo',
                    singleSelect: true,
                    checkOnSelect: true,
                    selectOnCheck: false,
                    pagination:true,
                    pageSize:dataOptions.pageSize,
                    pageList:dataOptions.pageList,
                    striped:true">
            <thead>
            <tr>
                <th data-options="field:'id', halign:'center',checkbox:true" width="220">表主键ID</th>
                <th data-options="field:'configCode',align:'center',width:'220'">姓名</th>
                <th data-options="field:'configName',align:'center',width:'220'">密码</th>
                <th data-options="field:'validFlag',align:'center',width:'220',formatter:formatValidFlag">电话</th>
            </tr>
            </thead>
        </table>
    </div>
</div>
</body>
</html>