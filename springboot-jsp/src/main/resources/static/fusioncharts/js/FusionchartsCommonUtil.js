//图形通用属性定义JS
/*
 * 
 animation 是否显示加载图表时的动画
 palette 内置的图表样式,共5个
 paletteColors 自定义图表元素颜色(为多个,如过过少会重复;不设置：默认颜色随机生成)
 showAboutMenuItem 右键是否显示"关于FusionCharts"
 aboutMenuItemLabel 右键关于自定义文字
 aboutMenuItemLink 右键关于自定义链接(FusionCharts链接格式)
 showZeroPies 是否显示0值的饼
 showPercentValues labels上是否显示百分数
 showPercentInToolTip tip上是否显示百分数
 showLabels 是否显示Label
 manageLabelOverflow 当Label溢出时进行自动管理
 useEllipsesWhenOverflow 当Label溢出时候使用...
 showValues 是否显示值
 labelSepChar label上的分隔符
 defaultAnimation 是否关闭默认动画一开始自定义动画
 clickURL 整个图表的点击的url


 标题
 caption 主标题
 subCaption 副标题
 captionAlignment : 设置标题的水平对齐方式 范围：“左”，“中”，“右”]
captionOnTop : 标题是否应位于数据绘图区域之上
captionFontSize : Number [+]
subCaptionFontSize : Number [+]
captionFont : String [+]
subCaptionFont : String [+]
captionFontColor : Color [+]
subCaptionFontColor : Color [+]
captionFontBold : Boolean [+]
subCaptionFontBold : Boolean [+]


 图表的装饰
 showBorder 显示边框
 borderColor 边框颜色
 borderThickness 边框粗细
 borderAlpha 边框透明度
 bgColor 背景颜色
 bgAlpha 背景透明度
 bgRatio 背景比例
 bgAngle 背景角度
 bgSWF 背景flash地址可以是图片地址
 bgSWFAlpha 背景flash的透明度
 showVLineLabelBorder
 logoURL log地址
 logoPosition log位置
 logoAlpha log透明度
 logoScale log比例
 logoLink log链接


 元素的装饰
 showPlotBorder 每一片的边框
 plotBorderColor 每一片的边框颜色
 plotBorderThickness 每一片的边框粗细
 plotBorderAlpha 每一片的边框透明度
 plotFillAlpha 每一片的边框填充透明度
 use3DLighting 3d光效果


 饼/圈专有属性
 slicingDistance 当点击图表的时候这一片饼离开中心点的距离
 pieRadius 饼的外半径
 startingAngle 起始角度
 enableRotation 开启旋转
 pieInnerFaceAlpha 图表里面的透明度
 pieOuterFaceAlpha 图表外面的透明度
 pieYScale 饼立起来的角度,角度越大看到的面积越大.
 pieSliceDepth 图表的厚度
 标明线 & Labels (label和图表元素之间的线)
 enableSmartLabels 是否开启标明显
 skipOverlapLabels 跳过重复标签
 isSmartLineSlanted 标明线方式(倾斜或直)
 smartLineColor 标明线颜色
 smartLineThickness 标明线粗细
 smartLineAlpha 标明线透明度
 labelDistance
 smartLabelClearance 标明线长度


 数字
 formatNumber 逗号来分隔数字(千位，百万位),默认为1(True)；若取0,则不加分隔符
 formatNumberScale 是否格式化数字,默认为1(True),自动的给你的数字加上K（千）或M（百万）；若取0,则不加K或M
 defaultNumberScale 默认数量级
 numberScaleUnit
 numberScaleValue
 numberPrefix 数字前缀
 numberSuffix 数字后缀
 decimalSeparator 小数分隔符
 thousandSeparator 千位分割符
 inDecimalSeparator 在十进位分割符
 inThousandSeparator 在千位分割符
 decimals 小数
 forceDecimals 是否用0填充以至满足要保留的小数位


 字体
 baseFont 基本字体
 baseFontSize 基本字号
 baseFontColor 基本字颜色
 Tool-tip
 showToolTip 是否显示提示
 toolTipBgColor 提示背景色
 toolTipBorderColor 提示边框色
 toolTipSepChar 提示分隔符
 showToolTipShadow 是否显示提示Tool-tip阴影


 边距
 captionPadding 标题内边距
 chartLeftMargin 图表左外边距
 chartRightMargin 图表右外边距
 chartTopMargin 图表上外边距
 chartBottomMargin 图表下外边距
 set元素
 borderColor 边框颜色
 isSliced 被切开
 label [label]
 value 值
 color 颜色
 link 链接(FusionCharts链接格式)
 toolText 自定义提示


 图例(3.2版本以上)
 showLegend 是否显示图例
 legendPosition 图例位置
 legendCaption 图例说明
 legendIconScale 图例图标0-5(图例图标大小)
 legendBgColor 图例背景色
 legendBgAlpha 图例透明度
 legendBorderColor 图例边框颜色
 legendBorderThickness 图例边框粗细
 legendBorderAlpha 图例边框透明度
 legendShadow 图例阴影
 legendAllowDrag 是否允许拖动图例
 legendScrollBgColor 图例滚动条背景色
 legendScrollBarColor 图例滚动条颜色
 legendScrollBtnColor 图例滚动条按钮颜
 reverseLegend 反转图例
 interactiveLegend 图例交互(是否可点击)
 legendNumColumns 设置图例的列数(如果设置不当会自动设置,如果legendPosition设置为右面该属性自动设置为1)
 minimiseWrappingInLegend
 /*
 */
function FusionchartsCommonUtil() {
}

FusionchartsCommonUtil.prototype.getChartAttribute = function() {
	var chartAttribute = {
		PYAxisName : "",
		SYAxisName : "",
		baseFontColor : "#ffffff",
		baseFontSize : "14",
		bgAlpha : "0",
		bgSWFAlpha : "0",
		bgcolor : "#000000",
		canvasBgAlpha : "0",
		canvasBgColor : "#ffffff",
		canvasBorderColor : "#000000",
		valueFontColor:"#ffffff",
		//palettecolors : "#E64571, #88D786",
		caption : "",
		captionOnTop :"1",
		captionFont : "微软雅黑",
		captionFontBold :"0",
		captionFontColor : "#000",
		captionFontSize : "18",
		captionPadding : "8",
		chartBottomMargin : "10",
		chartTopMargin : "10",
		chartleftMargin : "10",
		chartrightMargin : "10",
		divLineDecimalPrecision : "1",
		divLineThickness : "1",
		formatNumberScale : "0",
		labelDisplay : "WRAP",
		legendBgAlpha : "0",
		legendBorderAlpha : "0",
		legendBorderColor : "#000000",
		legendItemFont : "黑体",
		legendItemFontColor : "#fff",
		legendItemFontSize : "14",
		legendShadow : "0",
		limitsDecimalPrecision : "1",
		numberPrefix : "",
		numberSuffix : "",
		placeValuesInside : "0",
		plotfillratio : "100",
		showAlternateHGridColor : "0",
		showAlternateVGridColor : "0",
		showBorder : "0",
		showCanvasBorder : "0",
		showPlotBorder : "0",
		showShadow : "0",
		slicingDistance : "10",
		subCaption : "",
		toolTipBgColor : "#fff",
		toolTipBorderColor : "#000",
		toolTipColor : "#000",
		use3DLighting : "0"
	}
	return chartAttribute;
}

FusionchartsCommonUtil.prototype.getDoughnut2dAttribute = function() {
	var doughnut2dAttribute = {
		baseFontColor : "#00a0e9",
		baseFontSize : "14",
		bgAlpha : "0",
		bgColor : "#000000",
		canvasBgAlpha : "0",
		captionFontSize : ",14",
		centerLabel : "$label: $value",
		centerLabelColor : "#FFFFFF",
		centerLabelFontSize : "14",
		valueFontColor:"#fff",
		caption : "",
		captionOnTop :"1",
		captionFont : "微软雅黑",
		captionFontBold :"0",
		captionFontColor : "#fbb812",
		captionFontSize : "14",
		captionPadding : "8",
		chartBottomMargin : "10",
		chartTopMargin : "10",
		chartleftMargin : "10",
		chartrightMargin : "10",
		decimals : "0",
		enableSmartLabels : "0",
		formatNumberScale : "0",
		labelDisplay : "NONE",
		legendBgAlpha:"0",
		legendBorderAlpha : "0",
		legendShadow : "0",
		outCnvBaseFontColor : "ffffff",
		showBorder : "0",
		showLabels : "0",
		showLegend : "0",
		showPercentValues : "0",
		showPlotBorder : "0",
		showShadow : "0",
		showTooltip : "1",
		showValues : "0",
		slicingDistance : "0",
		startingAngle : "0",
		subcaptionFontBold : "0",
		subcaptionFontSize : "14",
		toolTipBgColor : "#000000",
		toolTipBorderColor : "#000000",
		toolTipColor : "#ffffff",
		use3DLighting : "0"
	}
	return doughnut2dAttribute;
}
/**
 * 多维柱形图通用方法 适用于数据类型为二维数据
 * 数据list，分类Obj（横坐标包含ID,CODE,NAME）,系列（及每个分类下的子分类包含id，code,name）,值得字段
 */
/*
 * var categoryColObj = { "id" : "", "code" : "Mds_Item_Code", "name" :
 * "Mds_Item_name" }; var seriesColObj = { "id" : "", "code" : "Type_Code",
 * "name" : "Type_name", "color" : "color" };
 * 
 */
FusionchartsCommonUtil.prototype.getStackedcolumnInfo = function(chartList, categoryColObj, seriesColObj, displayValueCol, chartLinkName, paramObj) {

	var categoryIdCol = categoryColObj.id;

	var categoryCodeCol = categoryColObj.code;

	if (typeof (categoryCodeCol) != "undefined" && categoryCodeCol != "") {
		categoryCodeCol = categoryCodeCol.toLocaleUpperCase();
	}
	var categoryNameCol = categoryColObj.name;
	if (typeof (categoryNameCol) != "undefined" && categoryNameCol != "") {
		categoryNameCol = categoryNameCol.toLocaleUpperCase();
	}

	var seriesIdCol = seriesColObj.id;
	if (typeof (seriesIdCol) != "undefined" && seriesIdCol != "") {
		seriesIdCol = seriesIdCol.toLocaleUpperCase();
	}
	var seriesCodeCol = seriesColObj.code;
	if (typeof (seriesCodeCol) != "undefined" && seriesCodeCol != "") {
		seriesCodeCol = seriesCodeCol.toLocaleUpperCase();
	}
	var seriesNameCol = seriesColObj.name;
	if (typeof (seriesNameCol) != "undefined" && seriesNameCol != "") {
		seriesNameCol = seriesNameCol.toLocaleUpperCase();
	}

	var seriesNameColorCol = seriesColObj.color;
	if (typeof (seriesNameColorCol) != "undefined" && seriesNameColorCol != "") {
		seriesNameColorCol = seriesNameColorCol.toLocaleUpperCase();
	}

	var seriesNameRenderasCol = seriesColObj.renderas;
	if (typeof (seriesNameRenderasCol) != "undefined" && seriesNameRenderasCol != "") {
		seriesNameRenderasCol = seriesNameRenderasCol.toLocaleUpperCase();
	}
	displayValueCol = displayValueCol.toLocaleUpperCase();

	var categoryArray = new Array();
	var xAxisNamesArray = new Array();
	var seriesNameArray = new Array(new Array(), new Array(), new Array());
	var totalQty = 0;
	for ( var i = 0; i < chartList.length; i++) {
		var categoryColObj = new Object();
		// 组装categorie
		var category = chartList[i][categoryNameCol];
		if (typeof (category) != "undefined" && category != "") {
			if ($.inArray(category + "", xAxisNamesArray) < 0) {
				xAxisNamesArray.push(category + "");
				categoryColObj.label = category + "";
				categoryArray.push(categoryColObj);
			}
		}
		// 组装seriesname
		var seriesName = chartList[i][seriesNameCol];
		var seriesColor = chartList[i][seriesNameColorCol];
		var renderas = chartList[i][seriesNameRenderasCol];
		if (typeof (seriesName) != "undefined" && seriesName != "") {
			if ($.inArray(seriesName + "", seriesNameArray[0]) < 0) {
				seriesNameArray[0].push(seriesName + "");
				seriesNameArray[1].push(seriesColor);
				seriesNameArray[2].push(renderas);
			}
		}
		// 总数
		totalQty += chartList[i][displayValueCol];
	}
	// 组装categories
	var categorysArray = new Array();
	var categorysObj = new Object();
	categorysObj.category = categoryArray;
	categorysArray.push(categorysObj);

	// 组装dataset

	var datasetArray = new Array();

	for ( var i = 0; i < seriesNameArray[0].length; i++) {

		var datasetObj = new Object();
		var seriesName = seriesNameArray[0][i];
		datasetObj.seriesName = seriesName;
		var color = seriesNameArray[1][i];
		if (color != "") {
			datasetObj.color = color;
		}
		var renderas = seriesNameArray[2][i];
		if (renderas != "") {
			datasetObj.renderas = renderas;
		}

		var dataArray = new Array();
		var flag = false;
		for ( var m = 0; m < xAxisNamesArray.length; m++) {
			var value = "0";
			var showValue = "0";
			var dataObj = new Object();
			for ( var k = 0; k < chartList.length; k++) {
				var categoryId = chartList[k][categoryIdCol];
				var categoryCode = chartList[k][categoryCodeCol];
				var categoryName = chartList[k][categoryNameCol];
				var seriesId = chartList[k][seriesIdCol];
				var seriesCode = chartList[k][seriesCodeCol];
				var seriesName = chartList[k][seriesNameCol];
				var returncategoryObj = {
					"id" : categoryId,
					"code" : categoryCode,
					"name" : categoryName
				};
				var returnseriesObj = {
					"id" : seriesId,
					"code" : seriesCode,
					"name" : seriesName
				};
				if (categoryName == xAxisNamesArray[m]) {
					if (seriesNameArray[0][i] == seriesName) {
						value = chartList[k][displayValueCol];
						// showValue = "1";
						if (typeof (chartLinkName) != "undefined" && chartLinkName != "") {
							dataObj.link = "javascript:" + chartLinkName + "('" + JSON.stringify(returncategoryObj) + "','" + JSON.stringify(returnseriesObj) + "','" + JSON.stringify(paramObj) + "')";
						}
					}

				}
			}
			dataObj.value = value;
			dataObj.showValue = showValue;
			dataObj.alpha = "100";
			dataArray.push(dataObj);
		}
		datasetObj.data = dataArray;
		datasetArray.push(datasetObj);

	}
	var returnObj = new Object();
	returnObj.categorysArray = categorysArray;
	returnObj.datasetArray = datasetArray;
	returnObj.totalQty = totalQty;
	return returnObj;

}
/**
 * 多维柱形图通用方法 ，适用于数据类型为多列值
 * 数据list，分类Obj（横坐标包含ID,CODE,NAME）,系列（及每个分类下的子分类包含id，code,name，显示值得字段）
 * 
 * 
 */
/*
 * var seriesNameArray = [ { "id" : "", "code" : "1", "name" : "总数", "color" :
 * "EA2000", "chartLinkName" : "link", "displayValueCol" : "Display_Value1" }, {
 * "id" : "", "code" : "2", "name" : "交付", "color" : "62ACF9", "chartLinkName" :
 * "link", "displayValueCol" : "DISPLAY_VALUE2" }, { "id" : "", "code" : "3",
 * "name" : "配套", "color" : "000000", "chartLinkName" : "link",
 * "displayValueCol" : "DISPLAY_VALUE3" } ] var categoryColObj = { "id" : "",
 * "code" : "Mds_Item_Code", "name" : "Mds_Item_name" };
 */

FusionchartsCommonUtil.prototype.getStackedcolumnInfoByCol = function(chartList, categoryColObj, seriesNameArray, paramObj) {
	var categoryIdCol = categoryColObj.id;

	var categoryCodeCol = categoryColObj.code;

	if (typeof (categoryCodeCol) != "undefined" && categoryCodeCol != "") {
		categoryCodeCol = categoryCodeCol.toLocaleUpperCase();
	}
	var categoryNameCol = categoryColObj.name;
	if (typeof (categoryNameCol) != "undefined" && categoryNameCol != "") {
		categoryNameCol = categoryNameCol.toLocaleUpperCase();
	}
	var categoryArray = new Array();
	var xAxisNamesArray = new Array();
	for ( var i = 0; i < chartList.length; i++) {
		var categoryColObj = new Object();
		// 组装categorie
		var category = chartList[i][categoryNameCol];
		if ($.inArray(category, xAxisNamesArray) < 0) {
			xAxisNamesArray.push(category + "");
			categoryColObj.label = category + "";
			categoryArray.push(categoryColObj);
		}
	}
	// 组装categories
	var categorysArray = new Array();
	var categorysObj = new Object();
	categorysObj.category = categoryArray;
	categorysArray.push(categorysObj);

	// 组装dataset

	var datasetArray = new Array();
	for ( var i = 0; i < seriesNameArray.length; i++) {

		var datasetObj = new Object();
		var seriesName = seriesNameArray[i].name;
		datasetObj.seriesName = seriesName;
		var color = seriesNameArray[i].color;
		if (color != "") {
			datasetObj.color = color;
		}
		var dataArray = new Array();
		var chartLinkName = seriesNameArray[i].chartLinkName;
		var displayValueCol = seriesNameArray[i].displayValueCol;
		displayValueCol = displayValueCol.toLocaleUpperCase();

		var returnseriesObj = {
			"id" : seriesNameArray[i].id,
			"code" : seriesNameArray[i].code,
			"name" : seriesNameArray[i].name
		};

		for ( var k = 0; k < chartList.length; k++) {
			var dataObj = new Object();
			var value = "0";
			value = chartList[k][displayValueCol];
			dataObj.value = value;

			var categoryId = chartList[k][categoryIdCol];
			var categoryCode = chartList[k][categoryCodeCol];
			var categoryName = chartList[k][categoryNameCol];

			var returncategoryObj = {
				"id" : categoryId,
				"code" : categoryCode,
				"name" : categoryName
			};
			if (typeof (chartLinkName) != "undefined" && chartLinkName != "") {
				dataObj.link = "javascript:" + chartLinkName + "('" + JSON.stringify(returncategoryObj) + "','" + JSON.stringify(returnseriesObj) + "','" + JSON.stringify(paramObj) + "')";
			}
			dataArray.push(dataObj);

		}

		datasetObj.data = dataArray;
		datasetArray.push(datasetObj);

	}
	var returnObj = new Object();
	returnObj.categorysArray = categorysArray;
	returnObj.datasetArray = datasetArray;
	return returnObj;
}
