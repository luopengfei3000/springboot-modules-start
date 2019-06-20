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
    <link rel="stylesheet" type="text/css" href="/fusioncharts/css/style.css">
    <script type="text/javascript" src="/easyui/jquery.min.js"></script>
    <script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/easyui/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="/common/CommonDialog.js"></script>
    <script type="text/javascript" src="/extend/easyUiExtend.js"></script>
    <script type="text/javascript" src="/extend/Tools.js"></script>
    <script type="text/javascript" src="/extend/mms-fn.js"></script>
    <script type="text/javascript" src="/configinfo/configInfo.js"></script>
    <script type="text/javascript" src="/fusioncharts/js/FusionchartsCommonUtil.js"></script>
    <script type="text/javascript" src="/fusioncharts/fusioncharts-xt/js/fusioncharts.js"></script>

</head>
<script type="text/javascript">
    var url;
    $(function () {
        url = "${url}";
        loadChartData('chartContainer');
    });

    //饼图初始化
    function loadChartData(chartId) {
        var _self = this;
        $.ajax({
            url : url + "getConfigInfoData4Chart",
            data : {},
            type : 'post',
            dataType : 'json',
            success : function(r) {
                if (r.flag == "success") {
                    var dataArray = [];
                    var charList = r.dataList;
                    for ( var i = 0; i < charList.length; i++) {
                        var dataObj = {};
                        var label =charList[i].CONFIG_NAME;
                        dataObj.label = label + "";
                        dataObj.value = charList[i].NUM;
                        dataObj.link = "JavaScript:onWasteCodeClick("+"'"+charList[i].CONFIG_NAME+"'"+");";
                        dataArray.push(dataObj);
                    }
                    var commonChart = new FusionchartsCommonUtil();
                    var chartAttribute = commonChart.getChartAttribute();
                    chartAttribute.toolTipColor="000";
                    chartAttribute.toolTipBgColor="fff";
                    chartAttribute.toolTipBorderColor="fff";
                    chartAttribute.baseFontSize="12";
                    chartAttribute.showLegend="1";
                    chartAttribute.showValues="1";
                    chartAttribute.caption="通用代码统计";
                    chartAttribute.captionFontsize="16";
                    chartAttribute.captionFontColor="fff";
                    chartAttribute.legendItemFontColor="FFF";
                    chartAttribute.baseFontColor="FFF";
                    chartAttribute.legendIconScale="1";
                    chartAttribute.legendPosition="right";
                    var fusioncharts = new FusionCharts({
                        //Pie2D 圆形   Column2D 柱状性
                        type : 'Column2D',//更换图标的类型Column2D,Pie2D
                        renderAt : chartId,//图标位置id
                        dataFormat : 'json',
                        width : '100%',
                        height : '100%',
                        dataSource : {
                            "chart" : chartAttribute,
                            "data" : dataArray
                        }
                    })
                    fusioncharts.setTransparent();
                    fusioncharts.configure("ChartNoDataText", "未查询到相关数据");
                    fusioncharts.configure("PBarLoadingText", "正在加载数据,请稍后");
                    fusioncharts.render();
                } else {
                    $.messager.alert('提示', r.error, 'warning');
                }
            }
        });
    }
    //点击饼图加载数据
    function onWasteCodeClick(code){
        alert();
        //比如图下是列表，可用来查询列表数据
    }
</script>
<body class="easyui-layout" fit="true">
<div data-options="region:'center'" style="background:#ffffff;height:0px;padding:0px;overflow:hidden;">
    <div class="chartBox100">
        <div id="chartContainer" style="height: 100%; width: 98%"></div>
    </div>
</div>
</body>
</html>