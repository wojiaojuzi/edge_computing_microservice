package com.convoy.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

//@FeignClient(name="admin-service",url="localhost:2999/loger/log")
@FeignClient(name="admin-service")
public interface AdminFeign{

    @RequestMapping(value="/admin")
    String getUserIdFromToken(@RequestParam("token")String token);
}
