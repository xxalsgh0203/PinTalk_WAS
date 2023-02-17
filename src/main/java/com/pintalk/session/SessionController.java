package com.pintalk.session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

@RestController
public class SessionController {

    Logger log = LoggerFactory.getLogger(getClass().getName());

    @GetMapping("/session-info")
    public String sessionInfo(HttpServletRequest request){
        HttpSession session = request.getSession(false);

        if (session == null){
            return "세션이 없습니다.";
        }

        System.out.println("session.getAttributeNames() : " + session.getAttributeNames());

//        session.getAttributeNames().asIterator().forEachRemaining(name -> log.info("session name={}, value={}",name, session.getAttribute(name) ));

        log.info("sessionId={}", session.getId());
        log.info("maxInactiveInterval={}", session.getMaxInactiveInterval());
        log.info("creationTime={}", new Date(session.getCreationTime()));
        log.info("lastAccessTjme={}",new Date(session.getLastAccessedTime()));
        log.info("isNew={}", session.isNew());
        return "세션 출력";
    }
}
