package com.kiwi.stripes.addon;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;

/**
 * Created by zaza on 2016/3/12.
 */
public class SendSmsUtil {

    private final static String APP_KEY = "23325747";
    private final static String SECRET = "247da014e93780039bd913fc0b9abed9";
    private final static String URL = "http://gw.api.taobao.com/router/rest"; //api请求地址
    private final static String SMS_TYPE = "normal";
    private final static String SMS_FREE_SIGN_NAME_VERIF = "注册验证";
    private final static String SMS_VERIF_TEMPLATE_CODE = "SMS_5615114"; //注册验证template code
    private final static String SMS_PRODUCT = "沪牌不求人";

    public static String sendSmsVerificationCode(String userId, String mobile, String verificationCode) {//userId can be null

        String json = "{\"code\":\"%s\",\"product\":\"%s\"}";
        String smsParamString = String.format(json, verificationCode, SMS_PRODUCT);//SMS content after fill up the template
        TaobaoClient client = new DefaultTaobaoClient(URL, APP_KEY, SECRET);
        AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
        req.setExtend(userId);
        req.setSmsType(SMS_TYPE);
        req.setSmsFreeSignName(SMS_FREE_SIGN_NAME_VERIF);
        req.setSmsParamString(smsParamString);
        req.setRecNum(mobile);
        req.setSmsTemplateCode(SMS_VERIF_TEMPLATE_CODE);
        AlibabaAliqinFcSmsNumSendResponse rsp;
        try {
            rsp = client.execute(req);
            System.out.println(rsp.getBody());
            return rsp.getBody();
        } catch (ApiException e) {
            return "false";
        }
    }
}
