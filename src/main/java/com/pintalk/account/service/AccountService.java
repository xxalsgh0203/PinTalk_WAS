package com.pintalk.account.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.function.Util;
import com.pintalk.account.component.AccountSpecification;
import com.pintalk.account.entity.Account;
import com.pintalk.account.repository.AccountRepository;
import com.pintalk.common.entity.Param;
import com.pintalk.user.entity.UserMember;
import com.pintalk.user.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;


@Service
@EnableJpaAuditing
public class AccountService {

    @Autowired
    public AccountRepository repository;
    @Autowired
    public UserRepository userRepository;

    Util util = new Util();

    Logger log = LoggerFactory.getLogger(getClass().getName());

    /**
     * 계좌 전체 리스트
     *
     * @param pageable
     * @return
     */
    public Page<Account> accountList(Pageable pageable) {
        log.debug("==================AccountService.accountList.START==================");
        Page<Account> result = repository.findAll(pageable);
        log.debug("==================AccountService.accountList.END==================");
        return result;
    }


    /**
     * 계좌 단일 리스트
     *
     * @param param
     * @param pageable
     * @return
     * @throws ParseException
     */
    public Page<Account> accountListSearch(Param param, Pageable pageable) throws ParseException {
        log.debug("==================AccountService.accountListSearch.START==================");
        HashMap paramMap = util.convertObjectToMap(param);
        HashMap searchMap = new HashMap();

        //HashMap null 값 제거
        searchMap = util.mapKeyValueOupPut(paramMap);

        Map<String, Object> searchKeys = new HashMap<>();

        // Parameter 순차적으로 세팅
        for (Object key : searchMap.keySet()) {
            String value = String.valueOf(searchMap.get(key));
            if (value != null && !value.isEmpty() && !"null".equals(value)) {
                searchKeys.put((String) key, searchMap.get(key));
            }
        }

        AccountSpecification accountSpecification = new AccountSpecification();

        Page<Account> result = null;
        //결과값
        result = repository.findAll(accountSpecification.searchWith(searchKeys), pageable);
        log.debug("==================AccountService.accountListSearch.END==================");
        return result;
    }

    /**
     * 계좌상세 페이지
     *
     * @param no
     * @return
     */
    public Account accountDetail(int no) {
        log.debug("==================AccountService.accountDetail.START==================");
        Account result = repository.findById(no).get();
        log.debug("최종 결과값 : " + result);
        log.debug("==================AccountService.accountDetail.END==================");
        return result;
    }

//    /**
//     * 계좌 신규 등록
//     *
//     * @param
//     * @return
//     */
//    public boolean accountInsert(HashMap hashMap) throws ParseException {
//        log.debug("==================AccountService.accountInsert.START==================");
//
//        System.out.println("param : " + hashMap);
//
//        String time = util.toNowFormat("yyyyMMddHHmmss");
//
//        hashMap.put("createDt",time);
//        hashMap.put("modifyDt",time);
//
//        UserMember userMember = new UserMember();
//        for(Object key : hashMap.keySet()){
//            System.out.println(key);
//            if(key.equals("user_member_no")) {
//                userMember.setNo((Integer) hashMap.get("user_member_no"));
//                hashMap.remove("user_member_no");
//            }
//        }
//
//        System.out.println("hashMaphashMaphashMap : " + hashMap);
////        System.out.println("hashMap.get(\"user_member_no\") : " + hashMap.get("user_member_no"));
//        ObjectMapper objectMapper = new ObjectMapper();
//        Account account = objectMapper.convertValue(hashMap, Account.class);
//
////        Optional<UserMember> userMember = userRepository.findById((Integer) hashMap.get("user_member_no"));
////        account.setUserMember(userMember);
//
////        System.out.println("userMember : " + userMember);
//        System.out.println("account : " + account.toString());
//
//        try {
//            repository.save(account);
//        } catch (Exception e) {
//            return false;
//        }
//        log.debug("==================AccountService.accountInsert.END==================");
//        return true;
//    }

//    /**
//     * 계좌상세 페이지 수정 처리
//     *
//     * @param param
//     * @return
//     */
//    public boolean accountDetailModify(Param param) {
//        log.debug("==================AccountService.accountDetailModify.START==================");
//
//        int no = param.getNo();
//
//        Map<String, String> paramMap = util.convertObjectToMap(param);
//        HashMap resultMap = new HashMap();
//        Account result = new Account();
//
//        Optional<Account> oAccount = repository.findById(no);
//        if (oAccount.isPresent()) {
//            Account gaccount = oAccount.get();
//            if (gaccount.getAccountNo() != null) {
//                for (Map.Entry key : paramMap.entrySet()) {
//                    resultMap.put(key.getKey(), key.getValue());
//                }
//
//                result = Account.builder()
//                        .accountNo(gaccount.getAccountNo())
//                        .build();
//            }
//
//        }
//        log.debug("최종 결과값 : " + result);
//        try {
//            repository.save(result);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//
//        log.debug("==================AccountService.accountDetailModify.END==================");
//        return true;
//    }
//


}
