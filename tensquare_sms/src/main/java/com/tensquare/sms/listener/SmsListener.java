package com.tensquare.sms.listener;

import com.aliyuncs.exceptions.ClientException;
import com.tensquare.sms.utils.SmsUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

@Component
@RabbitListener(queues = "sms")
@Slf4j
public class SmsListener {

    @Resource
    private SmsUtil smsUtil;

    @RabbitHandler
    public void executeSms(Map<String, String> map) {
        String mobile = map.get("mobile");
        String checkCode = map.get("checkcode");
        log.info("手机号： {}" + mobile);
        log.info("验证码： {}" + checkCode);
        try {
            smsUtil.sendSms(mobile, "SMS_171117464", "十次方", "{\"checkcode\": \"" + checkCode + "\"}");
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }
}
