package com.pintalk.account.controller;

import com.pintalk.account.entity.Account;
import com.pintalk.account.service.AccountService;
import com.pintalk.common.Service.ComCodeService;
import com.pintalk.common.entity.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
public class AccountController {

    private Logger log = LoggerFactory.getLogger(getClass().getName());

    @Autowired
    AccountService accountService;

    @Autowired
    ComCodeService comCodeService;

    /**
     * 계좌 리스트화면 (조회)
     * @param pageable
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, path = {"/accountList","/accountListForm"})
    public List getAccountList(
            @PageableDefault(page = 0, size = 10, sort = "no", direction = Sort.Direction.DESC) Pageable pageable
            ,Param param
    ) throws ParseException {
        log.debug("==================AccountController.getAccountList.START==================");

        List result_li = new ArrayList();
        HashMap result_hs = new HashMap();


        Page<Account> list = null;
        if(param.getParamObject(param) == null){
            list = accountService.accountList(pageable);
        } else {
            list = accountService.accountListSearch(param, pageable);
        }
        log.debug("검색 리스트 : " + list);
        log.debug("=======================================");

        int currPage = list.getPageable().getPageNumber();
        int totalPage = list.getTotalPages();
        int startPage = (int) Math.floor(currPage / 10) * 10;
        int endPage = Math.min(totalPage, startPage + 10);

        result_hs.put("currPage", currPage);
        result_hs.put("startPage", startPage);
        result_hs.put("endPage", endPage);
        result_hs.put("totalPage", totalPage);
        result_li.add(list.getContent());
        result_li.add(result_hs);

        log.debug("최종 결과 리턴 값 : " + result_li);
        log.debug("==================AccountController.getAccountList.END==================");
        return result_li;
    }

    /**
     * 계좌상세 페이지
     * @param no
     * @return
     */
    @GetMapping("/accountDetail/{no}")
    public List accountDetail(@PathVariable int no) {
        log.debug("==================AccountController.accountDetail.START==================");

        List<Account> result_li = new ArrayList();
        result_li.add(accountService.accountDetail(no));

        log.debug("============================");
        log.debug("최종 결과값 : " +result_li);
        log.debug("==================AccountController.accountDetail.END==================");
        return result_li;
    }

    /**
     * 계좌 신규 처리
     * @param hashMap
     * @throws ParseException
     */
    @RequestMapping(value = "/accountInsert", method = RequestMethod.POST)
    public boolean getAccountInsert(@RequestBody HashMap hashMap) throws ParseException {
        log.debug("==================AccountController.getAccountInsert.START==================");
        accountService.accountInsert(hashMap);
        log.debug("==================AccountController.getAccountInsert.END==================");
        return true;
    }

//    /**
//     * 계좌상세 페이지 수정 처리
//     * @param param
//     * @return
//     */
//    @RequestMapping(value = "/accountDetailModify", method = RequestMethod.POST)
//    public boolean accountDetailModify(@RequestBody Param param) {
//        log.debug("==================AccountController.accountDetailModify.START==================");
//        boolean result = accountService.accountDetailModify(param);
//        log.debug("==================AccountController.accountDetailModify.END==================");
//        return result;
//    }
//    /**
//     * 계좌 신규 화면
//     * @throws ParseException
//     */
//    @RequestMapping(value = "/accountInsertView", method = RequestMethod.GET)
//    public List getAccountInsertView() {
//        log.debug("==================AccountController.getAccountInsertView.START==================");
//        List result_li = new ArrayList();
//
//        log.debug("==================AccountController.getAccountInsertView.END==================");
//        return result_li;
//    }
}
