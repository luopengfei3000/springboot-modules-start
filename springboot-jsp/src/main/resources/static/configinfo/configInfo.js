function ConfigInfo(datagrid,url,searchD,form){
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
ConfigInfo.prototype.init=function(){
    var _self = this;
    this.searchDialog =$(this._searchDiaId).css('display','block').dialog({
        title:'查询'
    });
    this.searchDialog.dialog('close',true);
    this._datagrid=$(this._datagridId).datagrid({
        url:this.getUrl()+"getConfigInfoByPage"
    });
};

ConfigInfo.prototype.insert=function(invoiceFlag){
    this.nData = new CommonDialog("insert","790","500",this.getUrl()+'Add/null',"添加",false,true,false);
    this.nData.show();
};
//修改页面
ConfigInfo.prototype.modify=function(){
    var rows = this._datagrid.datagrid('getChecked');
    if(rows.length !== 1){
        alert("请选择一条数据编辑！");
        return false;
    }
    this.nData = new CommonDialog("edit","790","500",this.getUrl()+'Edit/'+rows[0].id,"修改",false,true,false);
    this.nData.show();
};
//保存功能
ConfigInfo.prototype.save=function(form,id){
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
ConfigInfo.prototype.del=function(){
    var rows = this._datagrid.datagrid('getChecked');
    var _self = this;
    var ids = [];
    var l =rows.length;
    if(l > 0){
        $.messager.confirm('请确认','您确定要删除当前所选的数据？',function(b){
            if(b){
                for(;l--;){
                    ids.push(rows[l].id);
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
ConfigInfo.prototype.closeDialog=function(id){
    $(id).dialog('close');
};
//重载数据
ConfigInfo.prototype.reLoad=function(){
    this._datagrid.datagrid('load',{});
};
ConfigInfo.prototype.validFlag = [];
Mms.fn.lookup.getLookupType('VALID_FLAG', function (r) {
    r && ( ConfigInfo.prototype.validFlag = r);
});