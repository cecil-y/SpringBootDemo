package spring.springboot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LogController {

    private static Logger logger = LoggerFactory.getLogger(LogController.class);

    @RequestMapping("/logTest.do")
    public String login() {
        logger.info("打印info级别日志");
        logger.error("打印error级别日志");
        logger.debug("打印debug级别日志");
        logger.trace("打印trace级别日志");
        logger.warn("打印warn级别日志");
        return "index";
    }
}

