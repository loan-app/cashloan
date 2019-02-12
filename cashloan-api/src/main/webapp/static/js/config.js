var u = navigator.userAgent;
window.browser = {};
window.browser.iPhone = u.indexOf('iPhone') > -1;
window.browser.android = u.indexOf('Android') > -1 || u.indexOf('Linux') > -1;//android or uc
window.browser.ipad = u.indexOf('iPad') > -1;
window.browser.isclient = u.indexOf('lyWb') > -1;
window.browser.ios = u.match(/Mac OS/); //ios
window.browser.width = window.innerWidth;
window.browser.height = window.innerHeight;
window.browser.wx = u.match(/MicroMessenger/);
//getQueryString('source_tag') && window.localStorage.setItem("source_tag",getQueryString('source_tag'));
//window.source_tag = localStorage.source_tag ? localStorage.source_tag : 'wap';

function downLoad() {
    if (window.browser.iPhone || window.browser.ipad || window.browser.ios) {
        return iosDownload();
    } else {
        return androidDownload();
    }
}

function iosDownload() {
    //return "https://fir.im/zkdm";
    return "https://3gle.cn/b/lbqh";
}
function androidDownload() {
    //return "https://fir.im/zkdx";
    return "https://3gle.cn/b/lbqh";
}

//邀请页面App下载的地址
function getInvite_a(){
    return downLoad();
    //return "http://www.pgyer.com//app//qrcodeHistory//c5163bba43277a844f499515fa3fb83da934fb50cc645029ef5321255ec83ad9";
}
//邀请页面头部图片的地址
function getInvite_img(){
    return "/static/images/invite.png";
}
//首页头部图片的地址
function getIndex_img1(){
    return "/static/images/probg.png";
}
//首页什么是现金贷图片的地址
function getIndex_img2(){
    return "/static/images/txtTop.png";
}
//首页现金贷优势图片的地址
function getIndex_img3(){
    return "/static/images/txtBtm.png";
}
//帮助中心客服电话
function getTelephone(){
    return '18688017544。';
}
//支付宝账号
function getAirpay(){
    return '***@******.com';
}
//银行卡账号
function getBank(){
    return '*****';
}
//对公银行卡
function getBank2(){
    return '中国银行6216 *********';
}