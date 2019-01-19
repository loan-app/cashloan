<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
 <%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/";
	String invitationCode= request.getParameter("invitationCode");
	String channelCode= request.getParameter("channelCode");
	String inviteUserId= request.getParameter("userId");
	String loanSource= request.getParameter("loanSource");
%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <title>轻松借款</title>
    <meta name="keywords" content="贷款,小额借钱,借贷,贷款app,急用钱,短期快速放贷,极速借款借钱,小额贷款">
    <meta name="description" content="专注于为个人提供正规小额贷款、无抵押贷款、个人贷款、闪电借钱等服务">
    <script src="/static/js/flexable.js"></script>
    <meta name="format-detection" content="telephone=no" />
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="#7CD88E">
    <link rel="stylesheet" href="/static/css/style.css"/>
</head>
<style>
    body{background: #ca0a07;}
</style>
<body>
    <img src="" style="position:absolute;opacity: 0;left:-10000px;z-index: -1000;">

    <div class="signup seven">                          
        <img class="bar" alt="" src="/static/images/invite.png"/>          
        <div class="content">
            <form action="/api/user/wxRegister.htm">
                <div class='centerDiv'><input name="phone" type="tel" value="" maxlength="11" placeholder="请输入手机号"/><span class='clear'  >x</span></div>
                <div class='centerDiv'><input name="password" type="password" value="" maxlength="16" placeholder="设置登录密码"/><span class='clear' >x</span></div>
                <%if(channelCode != null&&!channelCode.equals("")&&!channelCode.equals("null")){ %>
                <div class='centerDiv'><input id='channelCode' name="channelCode" type="hidden" value="<%=channelCode%>"/></div>
                <%} %>
                <%if(invitationCode != null&&!invitationCode.equals("")&&!invitationCode.equals("null")){ %>
                <div class='centerDiv'><input id='invitation' name="invitationCode" type="text" value="<%=invitationCode%>" disabled="true" placeholder="推荐人"/></div>
                <%} %>
                 <%if(channelCode != null&&!channelCode.equals("")&&!channelCode.equals("null")){ %>
                <div class='centerDiv'><input id='channelCode' name="channelCode" type="hidden" value="<%=channelCode%>" /></div>
                <%} %>
                <p class="picVerify clearfix">
                    <input type="text" id="code" maxlength="4" name="code" placeholder="请输入图片验证码" />  
                    <img id="imgObj" alt="验证码"  src="/api/h5/imgCode/generate.htm" onclick="changeImg()"/> 
                </p>
                <p class="special clearfix">
                    <input name="vcode" type="text" value="" placeholder="请输入验证码" maxlength="4"/>
                    <button id="btn">获取验证码</button>
                </p>
                <a href="javascript:;" id="btn-reg" class="reg-btn">立即申请</a>
                <p class="clearfix other">
                    <input id="checkbox" name="yes" type="checkbox" value=""/>
                    <label for="checkbox" onclick="click_a();">同意<a href="protocol_register.jsp">《使用协议》</a>
                          <i src="/static/images/yes.png" id="click_a"></i>
                    </label> 
                     <%--<a href="#">APP下载</a>                              --%>
                </p>
            </form>
        </div>
    </div>

    <div class="popup tips" style="display:none">
        <div class="overlay"></div>
        <div class="dialog" style="height: 3.3rem;">
        <span class="close"></span>
        <h2 id="confirm" style="height: 1.8rem; line-height: 0.9rem; margin-top: 0.4rem">...</h2>
        <p>
            <a href="javascript:;" class="yes">确定</a>
        </p>
      </div>
    </div>

    <div class="popup pop" style="display:none">
        <div class="overlay"></div>
        <div class="dialog">
            <span class="close"></span>
            <h2>...</h2>
            <p>
                <a href="/user/getAppUrl">立即下载APP，一键提现</a>
            </p>
        </div>
    </div>
</body>
</html>
 
<script src="/static/js/jquery.min.js"></script>
<script type="text/javascript" src="/static/js/placeholders.js" ></script> 
<script type="text/javascript" src="/static/js/signup.js" ></script>
<script type="text/javascript" src="/static/js/jquery.md5.js" ></script>
<script type="text/javascript" src="/static/js/config.js" ></script>
<script>
    //头部信息
    var _hmt = _hmt || [];
    (function() {
      var hm = document.createElement("script");
      hm.src = "/static/js/hm.js";
      var s = document.getElementsByTagName("script")[0]; 
      s.parentNode.insertBefore(hm, s);
    })();

    kdlcJsApiShareBack(); 
    function kdlcJsApiShareBack(){
        if (typeof(kdlcJsApi) != 'undefined') {
            kdlcJsApi.pageAddShare('{"isShare":1,"shareBtnTitle":"\u6309\u94ae\u6587\u6848","shareTitle":"\u5206\u4eabtitle","sharePageTitle":"\u5206\u4eab\u6709\u5956\u63cf\u8ff0","shareContent":"\u5206\u4eab\u63cf\u8ff0","shareUrl":"http:\/\/www.koudailc.com","shareImg":"http:\/\/res.koudailc.com\/article\/20160506\/3572c6e05464b6.png","sharePlatform":["wx","wechatf","qq","qqzone","sina","sms"],"shareSuccessAlert":"\u5206\u4eab\u6210\u529f\u5f39\u6846\u6587\u6848","shareIsUp":1,"shareUpId":11,"shareUpType":1,"shareUpUrl":"http:\/\/www.koudailc.com"}');
        };
        return 'kdlc_share_back';
    }

    //接口定义
    //var codeurl = '/invite/getRegCode';
    //var signup ='/invite/getRegister';
    //var reg ='/user/addRegister';
    //var register ='/appSystem/getBorrowByApp';

    //新加
    var codeurl = '/api/user/sendSms.htm';//获取验证码
    var signup = '/api/user/validateSmsCode.htm';//判断验证码手否正确
    var checkurl = '/api/user/h5SendSms.htm';
    //app注册接口
    //var reg ='/api/user/register.htm';
    //微信渠道注册接口
    var reg ='/api/user/wxRegister.htm';
    
    var invitationCode='<%=invitationCode%>';
    var inviteUserId='<%=inviteUserId%>';
    var channelCode='<%=channelCode%>';

    //协议选中切换
    var i = 0;
    function click_a(){
        if(i%2==0){
            $('#click_a').css('display', 'none');
            if(typeof bgColor !== 'undefined'){
               $('#click_a').css('background-color',bgColor);
            }
        }else{
            var src = $('#click_a').attr('src');
            $('#click_a').css('display', 'inline');
            $('#click_a').css('background','url('+ src + ') 0 0 no-repeat').css('background-size','0.3733333333rem 0.3733333333rem');
        }
        i++;
    } 
 // 刷新图片  
    function changeImg() {  
        var imgSrc = $("#imgObj");  
        var times = (new Date()).getTime(); 
        imgSrc.attr("src", '/api/h5/imgCode/generate.htm?timestamp='+times);  
    }
    //头部图片
    $('img').eq(0).attr('src',getInvite_img());
    //app下载地址
    $('.other>a').attr('href',getInvite_a());
    var input = $('.centerDiv input');
    for(var i = 0 ; i < input.length; i++){
        input.eq(i).blur(function(){
            if($(this).val() != '' && $(this).val() != undefined && $(this).val() != null){
                $(this).siblings('.clear').css({'display':'inline-block'});
            }else{
                $(this).siblings('.clear').css({'display':'none'});
            }
        })
    }
    var clear = $('.clear');
    for(var i = 0 ; i < clear.length; i++){
        clear.eq(i).click(function(){
            $(this).css({'display':'none'});
            $(this).siblings('input').val('');
        })
    }
</script>