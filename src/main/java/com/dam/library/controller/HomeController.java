package com.dam.library.controller;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

@Controller
@RequestMapping("/home")
public class HomeController {


    private static final Logger logger = Logger.getLogger(HomeController.class);

    @RequestMapping("/index")
    public String home(HttpServletResponse response){
        logger.trace("this is trace message");
        logger.debug("this is debug message");
        logger.info("this is info message");
        logger.warn("this is warn message");
        logger.error("this is error message");
        logger.fatal("this is fatal message");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "Home Page");
        Writer writer = null;
        try {
            writer = response.getWriter();
            writer.write(jsonObject.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
