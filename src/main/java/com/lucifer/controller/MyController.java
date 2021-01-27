package com.lucifer.controller;

import com.lucifer.model.User;
import com.lucifer.util.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class MyController {

    @Operation(summary = "首页",
            description = "跳转到 /doc")
    @GetMapping(value = "/")
    public void index(HttpServletResponse response) throws IOException {
        response.sendRedirect("/docs");
    }

    @GetMapping(value = "/docs")
    public void docs(HttpServletResponse response) throws IOException {
        response.sendRedirect("/docs/index.html");
    }

    @Operation(summary = "heart beat",
            description = "--")
    @GetMapping(value = "/heartbeat")
    public Map heartBeat(){
        Map map = new HashMap();
        map.put("version","x.y.z");
        map.put("releasedAt","2020-01-01T10:12:12.123Z");
        return map;
    }
    @Operation(summary = "创建用户",
            description = "描述的文字",
            responses = {
                    @ApiResponse(description = "返回插入的用户",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = User.class))),
                    @ApiResponse(responseCode = "400", description = "返回400时候错误的原因")}
            )
    @PostMapping(value = "/user")
    public User create(@RequestBody User user){
        String id = UUID.randomUUID().toString();
        user.setId(id);
        //
        //insert user to db
        return  user;

    }

    @Operation(summary = "用户登录",
            description = "我这里就简写了，正确的逻辑是要到redis 活数据库里比较密码，然后生成token,把token 和 userId 对应的关系 插入到redis",
            responses = {
                    @ApiResponse(description = "返回token",
                            content = @Content(mediaType = "application/json"
                                    )),
                    @ApiResponse(responseCode = "400", description = "返回400时候错误的原因")}
    )
    @PostMapping(value = "/user/login")
    public Map login(@RequestBody User user){
        Map resultMap = new HashMap();
        if (StringUtils.isEmpty(user.getEmail())) {
            return resultMap;
        }
        // let any password is able to login
        if (StringUtils.isEmpty(user.getPassword())) {
            return resultMap;
        }
        User dbUser = null;
        {
            // get user from redis or db
            //dbUser = userService.getUser(email);

            // compare password
        }
        String token = RandomStringUtils.random(32,"abcdefghijklmnopqrstuvwxyz1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        // set token and userid to redis key value
        // restTemplate.opsForKey.set("userservice:user.login.token:"+token,dbUser.getId());
        resultMap.put("token",token);
        return  resultMap;

    }

    @Operation(summary = "获取用户信息",
            description = "我这里就简写了，正确的逻辑是要到redis 先取到 token对应的 userId 然后 再根据userId 取出User 返回" ,
            responses = {
                    @ApiResponse(description = "返回用户信息",
                            content = @Content(mediaType = "application/json"
                            )),
                    @ApiResponse(responseCode = "400", description = "返回400时候错误的原因")}
    )
    @GetMapping(value = "/user")
    public User get(@RequestHeader String token){
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        /**
         * 我这里就简写了，正确的逻辑是要到redis 先取到 token对应的 userId 然后 再根据userId 取出User 返回
         */
        String id = null;
        User user = new User();
        user.setId("abcdefg");
        user.setEmail("liufeng45gh@163.com");
        user.setFirst("fengxuan");
        user.setLast("liu");
        user.setPassword(null);

        //id = restTemplate.opsForKey.get("userservice:user.login.token:"+token);
        //
        //user =  userService.getUserById(id );

        return  user;

    }

    @Operation(summary = "用户登出",
            description = "我这里就简写了，正确的逻辑是要到redis 删除 token" ,
            responses = {
                    @ApiResponse(description = "返回成功",
                            content = @Content(mediaType = "application/json"
                            )),
                    @ApiResponse(responseCode = "200", description = "success"),
                    @ApiResponse(responseCode = "400", description = "返回400时候错误的原因")}
    )
    @PostMapping(value = "/user/logout")
    public Result logout(@RequestHeader String token){

        // remove token form redis
        // restTemplate.opsForKey.delete("userservice:user.login.token:"+token);

        return  Result.ok();

    }
}
