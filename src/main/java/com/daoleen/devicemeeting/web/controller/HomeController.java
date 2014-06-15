package com.daoleen.devicemeeting.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by alex on 13.6.14.
 */

@Controller
@RequestMapping("/")
public class HomeController {
    private final static Logger logger = LoggerFactory.getLogger(HomeController.class);

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        logger.debug("Format: {}", "myFormat");
        return "home/index";
    }

}
