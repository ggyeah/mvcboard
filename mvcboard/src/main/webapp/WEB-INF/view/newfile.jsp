<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.14/themes/redmond/jquery-ui.css" type="text/css" />
 <!-- jqGrid CSS -->
<link rel="stylesheet" href="//cdn.jsdelivr.net/jqgrid/4.6.0/css/ui.jqgrid.css" type="text/css" />
<!-- The actual JQuery code -->
<script type="text/javascript" src="https://code.jquery.com/jquery-1.10.2.min.js" /></script>
<!-- The JQuery UI code -->
<script type="text/javascript" src="https://code.jquery.com/ui/1.10.3/jquery-ui.min.js" /></script>
<!-- The jqGrid language file code-->
<script type="text/javascript" src="//cdn.jsdelivr.net/jqgrid/4.6.0/i18n/grid.locale-kr.js" /></script>
<!-- The atual jqGrid code -->
<script type="text/javascript" src="//cdn.jsdelivr.net/jqgrid/4.6.0/jquery.jqGrid.src.js" /></script>
<script>
$(document).ready(function() {
	var mydata = [
	   {date:"2007-10-01",name:"test",id:"id",product:"상품1",amount:"10.00",total:"210.00"},
	   {date:"2007-10-02",name:"test2",id:"id2",product:"상품1",amount:"20.00",total:"320.00"},
	   {date:"2007-09-01",name:"test3",id:"id3",product:"상품1",amount:"30.00",total:"430.00"},
	   {date:"2007-10-04",name:"test",id:"id",product:"상품1",amount:"10.00",total:"210.00"},
	   {date:"2007-10-05",name:"test2",id:"id2",product:"상품1",amount:"20.00",total:"320.00"},
	   {date:"2007-09-06",name:"test3",id:"id3",product:"상품2",amount:"30.00",total:"430.00"},
	   {date:"2007-10-04",name:"test",id:"id",product:"상품2",amount:"10.00",total:"210.00"},
	   {date:"2007-10-03",name:"test2",id:"id2",product:"상품2",amount:"20.00",total:"320.00"},
	   {date:"2007-09-01",name:"test3",id:"id3",product:"상품2",amount:"30.00",total:"430.00"},
	   {date:"2007-10-01",name:"test",id:"id",product:"상품2",amount:"10.00",total:"210.00"},
	   {date:"2007-10-02",name:"test2",id:"id2",product:"상품2",amount:"20.00",total:"320.00"},
	   {date:"2007-09-01",name:"test3",id:"id3",product:"상품2",amount:"30.00",total:"430.00"},
	   {date:"2007-10-04",name:"test",id:"id",product:"상품2",amount:"10.00",total:"210.00"},
	   {date:"2007-10-05",name:"test2",id:"id2",product:"상품2",amount:"20.00",total:"320.00"},
	   {date:"2007-09-06",name:"test3",id:"id3",product:"상품2",amount:"30.00",total:"430.00"},
	   {date:"2007-10-04",name:"test",id:"id",product:"상품2",amount:"10.00",total:"210.00"},
	   {date:"2007-10-03",name:"test2",id:"id2",product:"상품2",amount:"20.00",total:"320.00"},
	   {date:"2007-09-01",name:"test3",id:"id3",product:"상품2",amount:"30.00",total:"430.00"},
	  {date:"2007-09-01",name:"test4",id:"id4",product:"상품2",amount:"30.00",total:"430.00"}
	];

	$("#list").jqGrid({
		datatype: "local",
		data: mydata,
		colNames:['날짜', '아이디', '이름','상품','가격','합계'],
		colModel:[
			{name:'date', index:'date', width:90, align: "center"},
			{name:'name', index:'name', width:100 , align: "center" },
			{name:'id', index:'id', width:150, align: "center" ,sortable:false },
			{name:'product', index:'product', width:80, align: "center"},
			{name:'amount', index:'amount', width:80, align: "center"},
			{name:'total', index:'total', width:80, align: "center"}
		],
		autowidth: true,
		rownumbers : true,
		multiselect:true,
		pager:'#pager',
		rowNum: 10,
		rowList: [10, 20, 50],
		sortname: 'id',
		sortorder: 'asc',
		height: 250,
	});

	$(window).on('resize.jqGrid', function() {
		$("#list").jqGrid('setGridWidth', $("#list").parent().parent().parent().parent().parent().width());
	})
	$(".jarviswidget-fullscreen-btn").click(function(){
		setTimeout(function() {
			$("#list").jqGrid('setGridWidth', $("#list").parent().parent().parent().parent().parent().width());
		}, 100);
	});
});
</script>
</head>
<body>
<table id="list"></table>
<div id="pager"></div>
</body>
</html>