package com.dima;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogService {
    private Logger logger;

    @Autowired
    public void setLogger() {
        this.logger = LoggerFactory.getLogger(LogService.class);
    }

    public void log(String s) {
        logger.info(s);
    }

    public void log(String s, Object o) {
        logger.info(s, o);
    }
}
