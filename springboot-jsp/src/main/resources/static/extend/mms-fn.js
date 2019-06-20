var Mms = Mms ||{};
Mms.fn =Mms.fn||{};
Mms.fn.lookup =Mms.fn.lookup||{};
Mms.fn.lookup =((function(a){
	var _$=a;
	return {
		/**
		 * 获得系统可用的通用代码
		 * @param type 通用代码code
		 * @param func 回调函数
                 * @param async 默认异步请求true，如果需要发送同步请求，将此选项设置为false
		 */
		getLookupType:function(type,func,async){
                        var _async = true;
                        if(async !== undefined){
                            _async = async;
                        }
			_$.ajax({
				url: '/configLInfo/operation/getConfigLInfoByConfigCode/' + type,
				type :'post',
				cache :false,
				dataType :'json',
                async: _async,
				success : func
			})
		},
		/**
		 * /**格式化显示通用代码
		 */
		formatLookupType:function(value,array){
			if(!(value||value===0)) return '';
			var l=array.length;
			//start   ws
			var show_value="";
			var value_array=[];
			if(value.length>1){
				value_array = value.split(",");
				for(var i=0;i<l;i++){
					for(var j=0;j<value_array.length;j++){
						if(array[i].detailValue == value_array[j] && array[i].detailValue!=""){
							show_value=show_value+array[i].detailName+",";
						}
					}
			   }
				
			    return show_value.substring(0, show_value.length-1);
			  //end  ws
			}else{
				for(;l--;){
					if(array[l].detailValue == value){
						return array[l].detailName;
					}
				}
			}
		}
	};
})($));
