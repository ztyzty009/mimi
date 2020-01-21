package cn.zty.demo.provider;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class QcloudProvider {
   @Value("${qcloud.qfiel.pubilc-key}")
    private String pubilckey;
   @Value("${qcloud.qfiel.private-key}")
    private String privatekey;

}
