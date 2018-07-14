package com.lrm.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/log")
public class LogApi {

    private final Logger logger = LoggerFactory.getLogger(LogApi.class);


    @GetMapping("/testlog")
    public String log(){
        logger.trace("log -- trace");
        logger.debug("log -- debug");
        logger.info("log -- info");
        logger.warn("log -- warn");
        logger.error("log -- error");
        return "testlog";

    }

}
