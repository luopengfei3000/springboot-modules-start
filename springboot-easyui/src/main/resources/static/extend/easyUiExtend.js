/**
 * 扩展easyUI的datagrid行编辑器
 */
function extendDatagridEditor() {
	$.extend($.fn.datagrid.defaults.editors, {
		/**
		 * 通用选择编辑器，输入框+选择图标 options.handInput：是否可以手工输入，默认false
		 * options.clickFn：点击事件的方法，必填 输入参数：rowIndex 当前编辑的行索引，必填
		 */
		commonselector : {
			init : function(container, options) {
				var ipt = '<input class="datagrid-editable-input validatebox-text selector-div-input" type="text" style="width:100%;height: 16px;" disabled="disabled">';
				if (options && options.handInput) {
					ipt = '<input class="datagrid-editable-input validatebox-text selector-div-input" type="text" style="width:100%;height: 16px;">';
				}
				var input = $('<div class="selector-div selector-div-bg">' + ipt + '<span class="input-right-icon"></span>' + '</div>').appendTo(container);
				if (options && options.clickFn) {
					$(input).find("input").data("validatebox", {
						options : options
					});
					var rowIndex = $(container).parents("tr.datagrid-row").attr("datagrid-row-index");
					$(container).find(".input-right-icon").on("click", function() {
						options.clickFn.call(this, rowIndex);
					});
				}
				return input;
			},
			getValue : function(target) {
				return $(target).find("input").val();
			},
			setValue : function(target, value) {
				$(target).find("input").val(value);
			},
			resize : function(target, width) {
				$(target).width(width - 25);
			}
		},
		textarea : {
			init : function(container, options) {
				var input = $('<textarea class="datagrid-editable-input" rows=' + options.rows + '></textarea>').validatebox(options).appendTo(container);
				return input;
			},
			getValue : function(target) {
				return $(target).val();
			},
			setValue : function(target, value) {
				$(target).val(value);
			},
			resize : function(target, width) {
				var input = $(target);
				if ($.boxModel == true) {
					input.width(width - (input.outerWidth() - input.width()));
				} else {
					input.width(width);
				}
			}
		},
		my97 : {

			init : function(container, options) {
				var ipt = '<input class="datagrid-editable-input Wdate" type="text" style="width:100%;height: 16px;"  onclick="WdatePicker({dateFmt:\'yyyy-MM-dd HH:mm:ss\',readOnly:true});"  />';
				if (options && options.dateFmt) {
					ipt = '<input class="datagrid-editable-input Wdate" type="text" style="width:100%;height: 16px;"  onclick="WdatePicker({dateFmt:\'' + options.dateFmt + '\',readOnly:true});"  />';
				}
				var input = $(ipt).appendTo(container);
				return input;
			},
			getValue : function(target) {
				return $(target).val();
			},
			setValue : function(target, value) {
				$(target).val(value);
			},
			resize : function(target, width) {
				var input = $(target);
				if ($.boxModel == true) {
					input.width(width - (input.outerWidth() - input.width()));
				} else {
					input.width(width);
				}
			}
		}
	});
}

/**
 * input标签中在 data-options添加属性 validType: md input添加样式 easyui-datebox
 */
function extendDatebox() {
	$.extend($.fn.validatebox.defaults.rules, {
		md : {
			validator : function(value, param) {
				// var re =/^(\d{4})-(\d{2})-(\d{2})$/;
				// 判断日期格式符合YYYY-MM-DD标准 re.test(value)
				return isDate(value);
			},
			message : '日期格式应为： 2015-02-03 或 2015/02/03'
		}
	});

	// 扩展datebox可编辑,反定位日期控件值
	$('.easyui-datebox').datebox({
		editable : true,
		parser : function(s) {
			if (isDate(s)) {
				var d = toDate(s);
				return new Date(d);
			} else {
				return new Date();
			}
		}
	});
}

// 日期格式验证
// 是否为空
function isNullOrEmpty(str) {
	return str === undefined || str === null || str === "";
}
// 短字符串日期
function isShortDate(str) {
	str = isNullOrEmpty(str) ? "" : String(str);
	var r = str.replace(/(^\s*)|(\s*$)/g, "").match(/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/);
	if (r == null) {
		return false;
	}
	var d = new Date(r[1], r[3] - 1, r[4]);
	return (d.getFullYear() == r[1] && (d.getMonth() + 1) == r[3] && d.getDate() == r[4]);
}
// 长字符串日期
function isLongDate(str) {
	str = isNullOrEmpty(str) ? "" : String(str);
	var r = str.replace(/(^\s*)|(\s*$)/g, "").match(/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2}) (\d{1,2}):(\d{1,2}):(\d{1,2})$/);
	if (r == null) {
		return false;
	}
	var d = new Date(r[1], r[3] - 1, r[4], r[5], r[6], r[7]);
	return (d.getFullYear() == r[1] && (d.getMonth() + 1) == r[3] && d.getDate() == r[4] && d.getHours() == r[5] && d.getMinutes() == r[6] && d.getSeconds() == r[7]);
}
// 判断是否为日期格式
function isDate(str) {
	return isLongDate(str) || isShortDate(str);
}
// 字符串转日期格式
function toDate(str) {
	str = isNullOrEmpty(str) ? "" : String(str);
	try {
		return new Date(str.replace(/-/g, "\/"));
	} catch (e) {
		return null;
	}
}

// 自动调整百分比页面布局大小，在南布局south和中布局center上加height="xx%"（两个百分比相加应等于百分之百）；在布局上加width="xx%"
function resizeLayout() {
	$(".layout-panel").each(function() {
		var layoutWidth = $(this).children(".layout-body").attr("width");
		var layoutHeight = $(this).children(".layout-body").attr("height");
		var layoutObj = $(this).parent(".easyui-layout");
		if (!layoutWidth && !layoutHeight) {
			return;
		}
		var parentWidth = $(layoutObj).width();
		var parentHeight = $(layoutObj).height();
		if (parentWidth && layoutWidth) {
			layoutWidth = layoutWidth.replace(/%/, "");
			if ($(this).hasClass("layout-panel-east")) {
				$(layoutObj).layout("panel", "east").panel("resize", {
					width : parentWidth * parseInt(layoutWidth) / 100
				});
			}
			if ($(this).hasClass("layout-panel-west")) {
				$(layoutObj).layout("panel", "west").panel("resize", {
					width : parentWidth * parseInt(layoutWidth) / 100
				});
			}
			if ($(this).hasClass("layout-panel-center")) {
				$(layoutObj).layout("panel", "center").panel("resize", {
					width : parentWidth * parseInt(layoutWidth) / 100
				});
			}
		}
		if (parentHeight && layoutHeight) {
			layoutHeight = layoutHeight.replace(/%/, "");
			var thisLayoutNorthHeight = $(layoutObj).layout("panel", "north").parent(".layout-panel-north").height();

			if ($(this).hasClass("layout-panel-center")) {
				$(layoutObj).layout("panel", "center").panel("resize", {
					height : (parentHeight - thisLayoutNorthHeight) * parseInt(layoutHeight) / 100
				});
			}

			if ($(this).hasClass("layout-panel-south")) {
				$(layoutObj).layout("panel", "south").panel("resize", {
					height : (parentHeight - thisLayoutNorthHeight) * parseInt(layoutHeight) / 100
				});
			}

			if ($(this).hasClass("layout-panel-south")) {
				// 如果有north、center和south三个区域，并且center和north区域都有高度限制，则给north加上监听
				var setSouthHeight = $(layoutObj).layout("panel", "south").attr("height");
				var setCenterHeight = $(layoutObj).layout("panel", "center").attr("height");
				if ($(layoutObj).layout("panel", "north") && $(layoutObj).layout("panel", "center") && $(layoutObj).layout("panel", "south") && setCenterHeight && setSouthHeight) {

					setCenterHeight = setCenterHeight.replace(/%/, "");
					setSouthHeight = setSouthHeight.replace(/%/, "");

					$(layoutObj).layout("panel", "north").panel({
						onCollapse : function() {
							var layoutObj = $(this).parent(".layout-panel-north").parent(".easyui-layout");
							var thisLayoutSouthHeight = $(layoutObj).layout("panel", "south").height();
							var thisLayoutCenterHeight = $(layoutObj).layout("panel", "center").height();
							var hc = (thisLayoutCenterHeight + thisLayoutSouthHeight) * parseInt(setCenterHeight) / 100;
							$(layoutObj).layout("panel", "center").panel("resize", {
								height : (thisLayoutCenterHeight + thisLayoutSouthHeight) * parseInt(setCenterHeight) / 100
							});
							var hs = (thisLayoutCenterHeight + thisLayoutSouthHeight) * parseInt(setSouthHeight) / 100
							$(layoutObj).layout("panel", "south").panel("resize", {
								height : hs,
								top : document.body.clientHeight - hs
							});
						},
						onResize : function(width, height) {
							var hs = (parentHeight - height) * parseInt(layoutHeight) / 100;
							$(layoutObj).layout("panel", "south").panel("resize", {
								height : hs,
								top : document.body.clientHeight - hs
							});
						}
					});
				}
			}

		}
		$(layoutObj).layout("resize");
	});
}

// 设置全局AJAX默认参数
function ajaxSetting() {
	$.ajaxSetup({
		cache : false, // ajax不缓存
		headers : { // 默认添加请求头
			"Source" : document.URL
		}
	});
}

// datagrid单元格鼠标悬停提示
function cellTip(datagridId) {
	if (!datagridId) {
		return;
	}
	if (datagridId.indexOf("#") === -1) {
		datagridId = "#" + datagridId;
	}
	$(datagridId).datagrid('doCellTip', {
		onlyShowInterrupt : false,
		position : 'bottom',
		tipStyler : {
			'backgroundColor' : '#FFFFFF',
			boxShadow : '1px 1px 3px #292929',
			'max-width' : '400px'
		}
	});
	datagridColTitle();
};
/*
 * datagrid列标题提示 用法如：<span title="title">列名</span>
 */ 

function datagridColTitle() {
	$(".datagrid-header-row").find("div").each(function() {
		var title = $(this).children("span:first-child").children("span:first-child").attr('title');
		if (title==null||title==""){
			title=$(this).children("span:first-child").text();
		}
		$(this).attr("title", title);
	});
}
/**
 * validatebox验证扩展
 */
$.extend($.fn.validatebox.defaults.rules, {
	/**
	 * 数字输入验证扩展，分别验证整数小数输入及输入超出长度限制禁止输入 调用：validType：numLengthInputValid[整数,小数,
	 * 是否大于零] 例如：validType：numLengthInputValid[5,2,0] 是否大于零(0或1)
	 */
	numLengthInputValid : {
		validator : function(value, param) {
			var objNumPrecisionValid = $.fn.validatebox.defaults.rules.numLengthInputValid;
			var objCtrl = $(this);

			var decimalReg = eval('/^[\\d]{0,}[.]{1}[\\d]{0,}$/');// 小数正则表达式
			var validNumReg = eval('/^[0]{2}/');// 整数有效数字正则表达式
			var intMReg = eval('/^0$|^[1-9][\\d]{0,' + (param[0] - 1) + '}$/');// 整数位数正则表达式
			// 输入小数校验
			if (decimalReg.test(value)) {
				if (param[1] == 0) {
					if(!param[2]){
						objNumPrecisionValid.message = '请输入0或者{0}位正整数';
					}else{
						objNumPrecisionValid.message = '请输入{0}位正整数';
					}
					return false;
				}

				var arrValue = (value + "").split('.');

				if (validNumReg.test(arrValue[0])) {
					objNumPrecisionValid.message = '输入有效数字';
					return false;
				}

				if (!intMReg.test(arrValue[0])) {
					objNumPrecisionValid.message = '请输入{0}位整数部分';
					return false;
				}

				var decNReg = eval('/^[\\d]{0,' + (param[1] - 1) + '}[\\d]$/');// 小数位数正则表达式
				if (!decNReg.test(arrValue[1])) {
					objNumPrecisionValid.message = '请输入{1}位小数';
					return false;
				}
			} else {
				if (validNumReg.test(value)) {
					objNumPrecisionValid.message = '输入有效数字';
					return false;
				}

//				if(!intMReg.test(value) || param[2] && value <= 0){
//					objNumPrecisionValid.message = '请输入{0}位正整数';
//    				return false;
//    			}
				
				if(!intMReg.test(value)){
					if(!param[2]){
						objNumPrecisionValid.message = '请输入0或者{0}位正整数';
					}else{
						objNumPrecisionValid.message = '请输入{0}位正整数';
					}
    				return false;
    			}
				if(param[2] && value <= 0){
					objNumPrecisionValid.message = '输入必须大于0';
    				return false;
				}
			}
			return true;
		},
		message : ''
	},
	maxLength : {
		validator : function(a, b) {
			return a.length <= b[0]
		},
		message : "请输入不超过 {0} 字符."
	},
	minLength : {
		validator : function(a, b) {
			return a.length >= b[0]
		},
		message : "请至少输入 {0} 字符."
	}
});

/**
 * 【是否大于零】位1而且【需要验证的值】大于0，或满足正整数[m,n]位数，返回true，否则返回false
 * numLengthInputValid(需要验证的值,需要验证列名,整数最大位数,小数最大位数,是否大于零(0或1))
 */
function numLengthInputValid(value, tipStr, m, n, isGreaterZero) {

	var decimalReg = eval('/^[\\d]{0,}[.]{1}[\\d]{0,}$/');// 小数正则表达式
	var validNumReg = eval('/^[0]{2}/');// 有效数字正则表达式
	var intMReg = eval('/^0$|^[1-9][\\d]{0,' + (m - 1) + '}$/');// 整数位数正则表达式

	if (decimalReg.test(value) && n > 0) {
		// 输入小数校验
		if (n == 0) {
			$.messager.alert('提示', '【' + tipStr + '】必须输入{0}位正整数！', 'warning');
			return false;
		}

		var arrValue = (value + "").split('.');

		if (validNumReg.test(arrValue[0])) {
			$.messager.alert('提示', '【' + tipStr + '】必须输入有效数字！', 'warning');
			return false;
		}

		if (!intMReg.test(arrValue[0])) {
			$.messager.alert('提示', '【' + tipStr + '】最大输入' + m + '位整数部分！', 'warning');
			return false;
		}

		var decNReg = eval('/^[\\d]{0,' + (n - 1) + '}[\\d]$/');// 小数位数正则表达式
		if (!decNReg.test(arrValue[1])) {
			$.messager.alert('提示', '【' + tipStr + '】最大输入' + n + '位小数！', 'warning');
			return false;
		}
	} else {
		if (validNumReg.test(value)) {
			$.messager.alert('提示', '【' + tipStr + '】必须输入有效数字！', 'warning');
			return false;
		}

		if (!intMReg.test(value)) {
			$.messager.alert('提示', '【' + tipStr + '】最大输入' + m + '位正整数！', 'warning');
			return false;
		}

		if (isGreaterZero && value <= 0) {
			$.messager.alert('提示', '【' + tipStr + '】必须大于0！', 'warning');
			return false;
		}
	}
	return true;
};

/**
* EasyUI DataGrid根据字段动态合并单元格
* 参数 tableID 要合并table的id
* 参数 colList 要合并的列,用逗号分隔(例如："mds_item_code,mds_item_name");
*/
function mergeCellsByField(datagridId, colList) {
    var ColArray = colList.split(",");

    if (!datagridId) {
		return;
	}
    var tTable = $(datagridId);
	if (datagridId.indexOf("#") === -1) {
		tTable =$("#" + datagridId);
	}
    var TableRowCnts = tTable.datagrid("getRows").length;
    var tmpA;
    var tmpB;
    var PerTxt = "";
    var CurTxt = "";
    var alertStr = "";
    for (j = ColArray.length - 1; j >= 0; j--) {
        PerTxt = "";
        tmpA = 1;
        tmpB = 0;

        for (i = 0; i <= TableRowCnts; i++) {
            if (i == TableRowCnts) {
                CurTxt = "";
            }
            else {
                CurTxt = tTable.datagrid("getRows")[i][ColArray[j]];
            }
            if (PerTxt == CurTxt) {
                tmpA += 1;
            }
            else {
                tmpB += tmpA;
                
                tTable.datagrid("mergeCells", {
                    index: i - tmpA,
                    field: ColArray[j],　　//合并字段
                    rowspan: tmpA,
                    colspan: null
                });
               
                tmpA = 1;
            }
            PerTxt = CurTxt;
        }
    }
}
