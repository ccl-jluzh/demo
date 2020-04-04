package com.example.demo.controller;

import com.example.demo.dto.AccessTokenDto;
import com.example.demo.dto.githubUser;
import com.example.demo.provider.githubprovider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class authorize {

    @Autowired
    private githubprovider githubprovider;
    @GetMapping("callback")
    public String callback(@RequestParam(name = "code") String code,
                            @RequestParam(name = "state")String state){
        AccessTokenDto accessTokenDto = new AccessTokenDto();
        accessTokenDto.setCode(code);
        accessTokenDto.setState(state);
        accessTokenDto.setRedirect_uri("http://localhost:8080/callback");
        accessTokenDto.setClient_id("9c73e142d5c20d816085");
        accessTokenDto.setClient_secret("c4168382467f4bd9ca3699979f58b3d092531081");
        String accessToken = githubprovider.getAccessToken(accessTokenDto);
        githubUser user=githubprovider.getUser(accessToken);
        System.out.println(user.getName());
        return "index";
            }

}
