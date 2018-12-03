<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
 <%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/";
	String invitationCode = request.getParameter("invitationCode");
	String channelCode = request.getParameter("channelCode");
	String inviteUserId = request.getParameter("userId");
%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=0">
  <title>首页</title>
  <style>
    html{
      font-family: "微软雅黑";
    }
    .clearfix:after{
      content: ".";
      display: block;
      height: 0;
      clear: both;
      visibility: hidden;
    }
    html,.probody,.proView{
      width: 100%;
      height: 100%;
      margin: 0px;
      padding: 0px;
    }
    .proTop{
      position: relative;
    }
    .proTop .probg{
      width: 100%;
      height: auto;
      display: block;
    }
    .nobtnLeft{
      position: absolute;
      bottom: 8%;
      left: 11%;
      width: 16.4%;
      height: auto;
    }
    .nobtnLeft img{
      width: 100%;
      height: auto;
      display: block;
    }
    .nobtnRight{
      position: absolute;
      bottom: 8%;
      right: 11%;
      width: 16.4%;
      height: auto;
    }
    .nobtnRight img{
      width: 100%;
      height: auto;
      display: block;
    }
    .noBtn{
      position: absolute;
      bottom: 7%;
      left: 50%;
      width: 16.4%;
      margin-left: -8.2%;
      height: auto;
    }
    .noBtn img{
      width: 100%;
      height: auto;
      display: block;
    }
    .proBtm{
      width: 100%;
      background: #fdfae2;
      padding-top: 1.5em;
    }
    .txtTop{
      position: relative;
      border: 1px solid #ea316a;
      padding: 2em 1em 1.5em;
      background: #fff;
      border-radius: 5px;
      margin: 0 6%;
    }
    .txtTop img{
      width: 50%;
      height: auto;
      display: block;
      position: absolute;
      left: 50%;
      top: -1.2em;
      margin-left: -25%;
    }
    .txtTop p{
      margin: 0;
      padding: 0;
      color: #333;
      line-height: 20px;
      font-size: 14px;
    }
    .txtBtm{
      position: relative;
      border: 1px solid #ea316a;
      padding: 4em 1em 0;
      background: #fff;
      border-radius: 5px;
      margin: 1em 6%;
    }
    .txtBtm .txtBtmImg{
      width: 50%;
      height: auto;
      display: block;
      position: absolute;
      left: 50%;
      top: 1em;
      margin-left: -25%;
    }
    .txtBtm ul{
      margin: 0;
      padding: 0;
      list-style: none;
    }
    .txtBtm ul li{
      border-bottom: 1px dashed #eee;
      padding: 0.2em 0;
    }
    .txtBtm ul li:last-child{
      border-bottom: 0 none;
    }
    .txtBtm ul li img{
      width: 12%;
      height: auto;
      display: block;
      margin: 0.5em 0.5em 0.5em 1em;
      float: left;
    }
    .txtBtm ul li .txtBtmTxt{
      float: left;
      width: 75%;
      padding-left: 0.4em;
      padding-top: 0.3em;
    }
    .txtBtm ul li .txtBtmTxt h3{
      margin: 0;
      padding: 0;
      color: #eb0b50;
      font-weight: 500;
      font-size: 14px;
    }
    .txtBtm ul li .txtBtmTxt p{
      margin: 0;
      padding: 0;
      color: #333;
      font-size: 12px;
    }
    .proView .regBtn-a{
      width: 100%;
      text-align: center;
      background: #eb0b50;
      display: block;
      height: 48px;
      line-height: 48px;
      color: #fff;
      font-size: 16px;
      text-decoration: initial;
    }
    .condition{
      background: url(../static/images/bgImg.png) no-repeat;
      width:100%;
      height:100%;
      background-size:100%100%;
      overflow:hidden;
    }
    .condition .conditionBox{
      background: url(../static/images/application.png) no-repeat;
      width:74%;
      height:48%;
      background-size:100%100%;
      margin:0 auto;
      padding:3% 8%;
      position:fixed;
      bottom:0;
      left:5%
    }
    .conditionBox  .appleBox{
      height:55%;
    }
    .conditionBox p{
      margin:8px 0;
      color:#fff;
    }
    .conditionBox h2{
      margin:10px 0;
      color:#fff;
      font-weight: normal;
      font-size: 18px;
    }
    .conditionBox .textRight{
      float:right
    }
    .regBtn{
      width:34%;
      height:24%;
      color:#fff;
      text-decoration:none;
      display:block;
      background: url(../static/images/btnBg.png) no-repeat;
      background-size:100%100%;
      margin:10% auto;
      text-align: center;
      font-size:24px;
      font-weight: bold;
      padding-top:6%;
    }
  </style>
</head>
<body class="probody">
  <div class="condition">
      <div></div>
      <div class="conditionBox">
          <div class="appleBox">
            <p class="clearfix">
              <span>额度范围</span>
              <span class="textRight">期长</span>
            </p>
            <p>
              <span>1000-5000</span>
              <span class="textRight">一周</span>
            </p>
            <h2>申请条件</h2>
            <p>1.  18周岁-60周岁</p>
            <p>2.  手机固定使用>9个月</p>
          </div>
          <a class="regBtn" href="http://www.unidata.xin/v2/#/apply/login?id=10007068">注册<br/>领钱</a>
      </div>
  </div>
  <script src="/static/js/jquery.min.js"></script>
  <script type="text/javascript" src="/static/js/config.js" ></script>
  <script>
    $('.probg').attr('src',getIndex_img1()); //头部图片
    $('#txtTopImg').attr('src',getIndex_img2()); //第一个横幅
    $('.txtBtmImg').attr('src',getIndex_img3()); //第二个横幅
  </script>
</body>
</html>