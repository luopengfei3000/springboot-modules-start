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
</head>
<script type="text/javascript">
	$.extend($.fn.validatebox.defaults.rules, {
		maxLength: {
			validator: function(value, param){
				return param[0] >= value.length;

			},
			message: '请输入最多 {0} 字符.'
		},
		single: {
			validator: function(value){
				var flag=false;
				$.ajax({
					url:parent.configInfo.getUrl()+"single",
					data : {'value':value},
					type : 'post',
					async: false,
					dataType : 'json',
					success : function(r){
						if (r.count == 0){
							flag=true;
						}
					}
				});
				return flag;
			},
			message: '配置编码重复！'
		}
	});

	function closeForm(){
		parent.configInfo.closeDialog("#insert");
	}
	function saveForm(){
		if ($('#form').form('validate') == false) {
			return;
		}
		//$('#saveButton').linkbutton('disable').unbind("click");
		parent.configInfo.save(serializeObject($('#form')),"#insert");
	}
</script>
<body class="easyui-layout" fit="true">
	<div data-options="region:'center',split:true,border:false">
		<form id='form'>
			<input type="hidden" name="id" />
			<table class="form_commonTable">
				<tr>
					<th width="10%"><span style="color: red;">*</span> 配置编码:</th>
					<td width="40%">
						<input title="配置编码" class="easyui-validatebox"data-options="required:true,validType:['maxLength[128]','single']" style="width: 180px;" type="text" name="configCode" id="configCode" />
					</td>
					<th width="10%"><span style="color: red;">*</span> 配置名称:</th>
					<td width="40%">
						<input title="配置名称" class="easyui-validatebox" data-options="required:true,validType:'maxLength[128]'" style="width: 180px;" type="text" name="configName" id="configName" />
					</td>
				</tr>
				<tr>
					<th width="10%"><span style="color: red;">*</span> 是否有效 </th>
					<td width="40%">
						<mms:mmslookup name="validFlag" lookupCode="VALID_FLAG" isNull="true"
									   dataOptions="width:180,editable:false,panelHeight:'auto'">
						</mms:mmslookup>
					</td>
				</tr>
				<tr>
					<th width="10%">备注:</th>
					<td colspan="3" width="90%">
						 <textarea class="textareabox" data-options="validType:'maxLength[2048]'" style="box-sizing: border-box; height: 100px !important;" rows="7" id="note" name="note"></textarea>
					</td>
				</tr>
			</table>
		</form>
		<div id="searchBtns" class="datagrid-toolbar foot-formopera" style="margin-top: 30px">
            <table class="tableForm" border="0" cellspacing="1"  width='100%'>
                <tr>
                    <td align="center">
                        <a title="保存" id="saveButton" class="easyui-linkbutton primary-btn" onclick="saveForm();" href="javascript:void(0);">保存</a> <a title="返回" id="returnButton" class="easyui-linkbutton " onclick="closeForm();" href="javascript:void(0);">返回</a>  
                    </td>
                </tr>
            </table>
        </div>
	</div>
</body>
</html>