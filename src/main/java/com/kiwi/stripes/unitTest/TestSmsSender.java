package com.kiwi.stripes.unitTest;

import com.kiwi.stripes.addon.SendSmsUtil;
import com.taobao.api.ApiException;

public class TestSmsSender {
    private static String appkey = "23325747";
    private static String secret = "247da014e93780039bd913fc0b9abed9";
    private static String url = "http://gw.api.taobao.com/router/rest";//api请求地址

    public static void main(String[] args) throws ApiException {

        SendSmsUtil.sendSmsVerificationCode(null,"13636315983","555555");


//        String json="{\"code\":\"888888\",\"product\":\"abc\"}";
//        TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
//        AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
//        req.setExtend("123456");
//        req.setSmsType("normal");
//        req.setSmsFreeSignName("注册验证");
//        req.setSmsParamString(json);
//        req.setRecNum("13636315983");
//        req.setSmsTemplateCode("SMS_5615114");
//        AlibabaAliqinFcSmsNumSendResponse rsp = client.execute(req);
//        System.out.println(rsp.getBody());
//        try {
//            rsp = client.execute(req);
//            System.out.println(rsp.getBody());
//        } catch (Exception e) {
//            // TODO: handle exception
//        }
    }
}