package com.ggg.cmdb.base.model.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("api/v1")
@Slf4j
public class CmdbModelApi {

     @GetMapping("/test")
     public Object test(@RequestParam String cmModelId ) {
         log.info("test:{}",cmModelId);
        return new Date();
    }
}
