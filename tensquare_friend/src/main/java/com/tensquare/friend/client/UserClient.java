package com.tensquare.friend.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 用户客户端
 */
@Component
@FeignClient("tensquare-user")
public interface UserClient {


    /**
     * 增加粉丝数和关注数
     * @param userid
     * @param friendid
     * @param x
     */
    @RequestMapping(value = "/user/{userid}/{friendid}/{x}",method = RequestMethod.PUT)
    public void updateFanscountAndFollowcount(@PathVariable("userid") String userid, @PathVariable("friendid") String friendid, @PathVariable("x") int x);
}
