function Userinfo(datagrid,url,searchD,form){
    if(!datagrid || typeof(datagrid)!=='string'&&datagrid.trim()!==''){
        throw new Error("datagrid不能为空！");
    }
    var _selectId='';
    var	_url=url;
    this.getUrl = function(){
        return _url;
    }
    this._datagridId="#"+datagrid;
    this._doc = document;
    this._formId="#"+form;
    this._searchDiaId ="#"+searchD;
};
//初始化操作
Userinfo.prototype.init=function(){
    var _self = this;
    this.searchDialog =$(this._searchDiaId).css('display','block').dialog({
        title:'查询'
    });
    this.searchDialog.dialog('close',true);
    this._datagrid=$(this._datagridId).datagrid({
        url:this.getUrl()+"getUserinfoByPage"
    });
};
Userinfo.prototype.insert=function(invoiceFlag){
    this.nData = new CommonDialog("insert","790","500",this.getUrl()+'Add/null',"添加",false,true,false);
    this.nData.show();
};
//修改页面
Userinfo.prototype.modify=function(){
    var rows = this._datagrid.datagrid('getChecked');
    if(rows.length !== 1){
        alert("请选择一条数据编辑！");
        return false;
    }
    this.nData = new CommonDialog("edit","790","500",this.getUrl()+'Edit/'+rows[0].userId,"修改",false,true,false);
    this.nData.show();
};
//保存功能
Userinfo.prototype.save=function(form,id){
    var _self = this;
    $.ajax({
        url:_self.getUrl()+"save",
        data : {data :JSON.stringify(form)},
        type : 'post',
        async : false,
        dataType : 'json',
        success : function(r){
            if (r.flag == "success"){
                _self.reLoad();
                _self._datagrid.datagrid('uncheckAll').datagrid('unselectAll').datagrid('clearSelections');
                $(id).dialog('close');
                $.messager.show({
                    title : '提示',
                    msg : '保存成功！'
                });
            }else{
                $.messager.show({
                    title : '提示',
                    msg : r.error
                });
            }
        }
    });
};
//删除
Userinfo.prototype.del=function(){
    var rows = this._datagrid.datagrid('getChecked');
    var _self = this;
    var ids = [];
    var l =rows.length;
    if(l > 0){
        $.messager.confirm('请确认','您确定要删除当前所选的数据？',function(b){
            if(b){
                for(;l--;){
                    ids.push(rows[l].userId);
                }
                $.ajax({
                    url:_self.getUrl()+'delete',
                    data:JSON.stringify(ids),
                    contentType : 'application/json',
                    type : 'post',
                    dataType : 'json',
                    success : function(r){
                        if (r.flag == "success") {
                            _self.reLoad();
                            _self._datagrid.datagrid('uncheckAll').datagrid('unselectAll').datagrid('clearSelections');
                            $.messager.show({
                                title : '提示',
                                msg : '删除成功！'
                            });
                        }else{
                            $.messager.show({
                                title : '提示',
                                msg : r.error
                            });
                        }
                    }
                });
            }
        });
    }else{
        $.messager.alert('提示','请选择要删除的记录！','warning');
    }
};
//关闭对话框
Userinfo.prototype.closeDialog=function(id){
    $(id).dialog('close');
};
//重载数据
Userinfo.prototype.reLoad=function(){
    this._datagrid.datagrid('load',{});
};
//添加新用户
function newUsers(){
    //添加用户对话框
    $('#adddg').dialog('open').dialog('setTitle','添加用户');
    //数据清空
    $('#fam').form('clear');

}

//用户信息修改
function editUsers(){
    //选中某一行
    var row = $('#datagrid').datagrid('getSelected');
    if (row){
        $('#modifydg').dialog('open').dialog('setTitle','修改信息');
        //显示未修改前的用户信息
        $('#fim').form('load',row);
    }
}


//信息保存按钮事件
function saveUsers(){
    var row = $('#datagrid').datagrid('getSelected');
    var add;
    if(row==null){
        add="/user/update?id=0";
    } else{
        add="/user/update?id="+row.userId;
    }

    $('#fim').form('submit',{
        url: add,
        onSubmit: function(){
            return $(this).form('validate');
        },
        success: function(result){
            var result = eval('('+result+')');
            if (result.success){

                $('#modifydg').dialog('close');		// close the dialog
                $('#datagrid').datagrid('reload');	// reload the user data
                $.messager.show({
                    title: 'Success',
                    msg: '保存成功'
                });
            } else {
                $.messager.show({
                    title: 'Error',
                    msg: result.msg
                });
            }
        }
    });
}

//用户信息添加按钮事件
function addUsers(){
    var add="/user/save_users";
    $('#fam').form('submit',{
        url: add,
        onSubmit: function(){
            return $(this).form('validate');
        },
        success: function(result){
            var result = eval('('+result+')');
            if (result.success){

                $('#adddg').dialog('close');		// close the dialog
                $('#datagrid').datagrid('reload');	// reload the user data
                $.messager.show({
                    title: 'Success',
                    msg: '添加成功'
                });
            } else {
                $.messager.show({
                    title: 'Error',
                    msg: result.msg
                });
            }
        }
    });
}


//用户删除按钮事件
function removeUsers(){
    var row = $('#datagrid').datagrid('getSelected');

    if (row){
        $.messager.confirm('Confirm','确定要删除该用户?',function(r){
            if (r){

                $.post('/user/remove_users',{id:row.userId},function(result){
                    if (result.success){

                        $('#datagrid').datagrid('reload');	// reload the user data
                        $.messager.show({
                            title: 'Success',
                            msg: '删除成功'
                        });
                    } else {
                        $.messager.show({	// show error message
                            title: 'Error',
                            msg: result.msg
                        });
                    }
                },'json');
            }
        });
    }
}