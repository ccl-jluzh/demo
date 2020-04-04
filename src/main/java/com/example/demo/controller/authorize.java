package com.example.demo.controller;

import com.example.demo.dto.AccessTokenDto;
import com.example.demo.dto.githubUser;
import com.example.demo.provider.githubprovider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class authorize {

    @Autowired
    private githubprovider githubprovider;

    @Value("${github.client.id}")
    private String clientId;

    @Value("${github.client.secret}")
    private String clientSecret;

    @Value("%{github.redirect.url}")
    private String redirectUrl;

    @GetMapping("callback")
    public String callback(@RequestParam(name = "code") String code,
                            @RequestParam(name = "state")String state){
        AccessTokenDto accessTokenDto = new AccessTokenDto();
        accessTokenDto.setCode(code);
        accessTokenDto.setState(state);
        accessTokenDto.setRedirect_uri("redirectUrlg");
        accessTokenDto.setClient_id("clientId");
        accessTokenDto.setClient_secret("clientSecret");
        String accessToken = githubprovider.getAccessToken(accessTokenDto);
        githubUser user=githubprovider.getUser(accessToken);
        System.out.println(user.getName());
        return "index";
            }

}
