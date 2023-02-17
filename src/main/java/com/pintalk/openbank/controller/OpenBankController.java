package com.pintalk.openbank.controller;

import com.pintalk.openbank.service.OpenBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OpenBankController {

    @Autowired
    OpenBankService openBankService;

    @RequestMapping(path = "/openBank/authorize")
    public String authorize() {
        return openBankService.authorize();
    }

}
