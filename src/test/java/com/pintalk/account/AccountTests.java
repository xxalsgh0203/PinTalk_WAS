package com.pintalk.account;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.function.Util;
import com.pintalk.account.entity.Account;
import com.pintalk.user.entity.UserMember;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootTest
public class AccountTests {

    @Test
    public void accountInsert(){
        HashMap hashMap = new HashMap();

        hashMap.put("finTechUseNum","3278-12-589031");
        hashMap.put("accountAlias","동그라미");
        hashMap.put("bankCodeStd","002");
        hashMap.put("bankCodeSub","1000000");
        hashMap.put("bankName","국민은행");
        hashMap.put("savingsBankName","국민은행저축");
        hashMap.put("holderName","동그라미");
        hashMap.put("accountBalance","400000");
        hashMap.put("holderType","P");
        hashMap.put("accountType","1");
        hashMap.put("accountNo","327812589031");
        hashMap.put("inquiryAgreeYn","Y");
        hashMap.put("inquiryAgreeDt","20230112110105");
        hashMap.put("transferAgree_yn","Y");
        hashMap.put("transferAgreeDt","20230112110105");
        hashMap.put("state","01");

        hashMap.put("createDt","");
        hashMap.put("modifyDt","");

        Stream<Map.Entry<String,Object>> entryStream = hashMap.keySet().stream();

        System.out.println("entryStream : " + entryStream.collect(Collectors.toList()));

        ObjectMapper objectMapper = new ObjectMapper();
        Account account = objectMapper.convertValue(hashMap, Account.class);

        UserMember userMember = new UserMember();
        userMember.setNo(20);

        account.setUserMember(userMember);

    }

    @Test
    public void testTime(){
        SimpleDateFormat format1 = new SimpleDateFormat ( "yyyyMMddHHmmss");
        Date time = new Date();
        String time1 = format1.format(time);
        Util util = new Util();

        System.out.println(util.toNowFormat("yyyyMMddHHmmss"));
//        System.out.println("time1 : " + time1);
    }



//    @Test
//    public void accountListInsert(){
//        HashMap hashMap = new HashMap();
//
//        hashMap.put("finTechUseNum","3278-12-589031");
//        hashMap.put("accountAlias","동그라미");
//        hashMap.put("bankCodeStd","002");
//        hashMap.put("bankCodeSub","1000000");
//        hashMap.put("bankName","국민은행");
//        hashMap.put("savingsBankName","국민은행저축");
//        hashMap.put("holderName","동그라미");
//        hashMap.put("accountBalance","400000");
//        hashMap.put("holderType","P");
//        hashMap.put("accountType","1");
//        hashMap.put("accountNo","327812589031");
//        hashMap.put("inquiryAgreeYn","Y");
//        hashMap.put("inquiryAgreeDt","20230112110105");
//        hashMap.put("transferAgree_yn","Y");
//        hashMap.put("transferAgreeDt","20230112110105");
//        hashMap.put("state","01");
//
//        hashMap.put("createDt","");
//        hashMap.put("modifyDt","");
//
//        Optional<String> optional = (Optional<String>) hashMap.entrySet().stream().collect(Collectors.toList());
//
//        System.out.println("entryStream : " + optional);
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        Account account = objectMapper.convertValue(hashMap, Account.class);
//
//        UserMember userMember = new UserMember();
//        userMember.setNo(20);
//
//        account.setUserMember(userMember);
//
//    }
}
