var dataOptions = {pageSize: 15, pageList: [10, 15, 30, 50, 100]};

//将form表单元素的值序列化成对象
function serializeObject(form,ignoreBlank){
    var o ={};
    $.each(form.serializeArray(),function(index){
        if(typeof(ignoreBlank) == 'undefined' || (ignoreBlank !=null && ignoreBlank == false)){
            if(o[this['name']]){
                o[this['name']]=o[this['name']]+","+this['value'];
            }else{
                o[this['name']] = this['value'];
            }
        }else{
            if(this['value']!=null && this['value']!=""){
                if(o[this['name']]){
                    o[this['name']]=o[this['name']]+","+this['value'];
                }else{
                    o[this['name']] = this['value'];
                }
            }
        }
    });
    return o;
};

/**
 * easyui的combobox调用的公用方法，自适应宽度
 */
function comboboxOnShowPanel(){
    var panelWidth = $(this).combobox('textbox').parent().outerWidth(true);
    $(this).combobox('panel').panel('resize',{width: panelWidth});
}

/**
 * easyui的combobox调用的公用方法，隐藏Panel
 */
function comboboxHidePanel(){
    $('.easyui-combobox').combobox('hidePanel');
    $('.easyui-combotree').combotree('hidePanel');
}