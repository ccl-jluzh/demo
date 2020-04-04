package com.example.demo.provider;


import com.alibaba.fastjson.JSON;
import com.example.demo.dto.AccessTokenDto;
import com.example.demo.dto.githubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class githubprovider {
   public  String getAccessToken(AccessTokenDto AccessTokenDto){
        MediaType mediaType= MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();


       RequestBody body = RequestBody. create(mediaType, JSON.toJSONString(AccessTokenDto));
       Request request = new Request.Builder()
                   .url("https://github.com/login/oauth/access_token")
                   .post(body)
                   .build();
           try (Response response = client.newCall(request).execute()) {
               String string=response.body().string();
               String token = string.split("&")[0].split("=")[1];
               System.out.println(token);
               return token;
           } catch (IOException e) {
               e.printStackTrace();
           }
       return null;
   }
        public githubUser getUser(String accessToken) {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("https://api.github.com/user?access_token=" + accessToken)
                    .build();
            try {
                Response response = client.newCall(request).execute();
                String string = response.body().string();
                githubUser githubUser = JSON.parseObject(string, githubUser.class);
                return githubUser;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return  null;
        }
}

