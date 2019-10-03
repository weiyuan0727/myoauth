package com.example.oauthserver.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * Created by weiyuan on 2019/7/20/020.
 */
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    ConsumerTokenServices consumerTokenServices;
    Logger logger = LoggerFactory.getLogger(UserController.class);

    @RequestMapping(value = "/current", method = RequestMethod.GET)
    public Principal getUser(Principal principal) {
        logger.info(">>>>>>>>>>>>>>>>>>>>>>>>");
        logger.info(principal.toString());
        logger.info(">>>>>>>>>>>>>>>>>>>>>>>>");
        return principal;
    }

    @RequestMapping("/logout")
    public String ch(@RequestParam("access_token") String access_token,@RequestParam("user_name") String user_name) {
        String msg = "";
        boolean cancleBoolean = consumerTokenServices.revokeToken(access_token);
        if (cancleBoolean) {
            msg = "退出成功";

        } else {
            msg = "退出失败";
        }
        return msg;
    }

}
