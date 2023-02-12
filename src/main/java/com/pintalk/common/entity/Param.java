package com.pintalk.common.entity;

import com.pintalk.account.entity.AccountHist;
import com.pintalk.user.entity.UserMember;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class Param extends BaseEntity {

    public Param getParamObject(Param param){
        return param;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/login")
    public Param getLoginParam(Param param){

        param.setId(param.getId());
        param.setPassword(param.getPassword());
        return param;
    }

    @RequestMapping(method = RequestMethod.GET, path = {"/userMemberList","/userMemberListForm"})
    public Param getUserMemberParam(Param param){

        param.setGender(param.getGender());
        param.setName(param.getName());
        param.setYear(param.getYear());
        param.setMonth(param.getMonth());
        param.setDay(param.getDay());
        param.setPhone1(param.getPhone1());
        param.setPhone2(param.getPhone2());
        param.setPhone3(param.getPhone3());
        param.setAddress(param.getAddress());
        param.setEmail(param.getEmail());
        param.setSignDateStart(param.getSignDateStart());
        param.setSignDateEnd(param.getSignDateEnd());
        param.setModifyDateStart(param.getModifyDateStart());
        param.setModifyDateEnd(param.getModifyDateEnd());
        param.setSaveStatus(param.getSaveStatus());
        param.setAdmin_yn(param.getAdmin_yn());
        param.setResCnt(param.getResCnt());

        return param;
    }

//    @RequestMapping(method = RequestMethod.GET, path = {"/accountList","/accountListForm"})
//    public Param getAccountParam(Param param){
//
//        param.setUserMember(param.getUserMember());
//        param.setFinTechUseNum(param.getFinTechUseNum());
//        param.setAccountAlias(param.getAccountAlias());
//        param.setBankCodeStd(param.getBankCodeStd());
//        param.setBankCodeSub(param.getBankCodeSub());
//        param.setBankName(param.getBankName());
//        param.setSavingsBankName(param.getSavingsBankName());
//        param.setHolderName(param.getHolderName());
//        param.setHolderType(param.getHolderType());
//        param.setAccountType(param.getAccountType());
//        param.setAccountNo(param.getAccountNo());
//        param.setInquiryAgreeYn(param.getInquiryAgreeYn());
//        param.setInquiryAgreeDt(param.getInquiryAgreeDt());
//        param.setTransferAgree_yn(param.getTransferAgree_yn());
//        param.setTransferAgreeDt(param.getTransferAgreeDt());
//        param.setState(param.getState());
//        param.setCreateDt(param.getCreateDt());
//        param.setModifyDt(param.getModifyDt());
//        param.setAccountHists(param.getAccountHists());
//
//        return param;
//    }


    private Integer no;             //유저 고유 번호

    private String id;              //유저 아이디
    private String password;        //유저 패스워드
    private String name;            //유저 이름
    private String ssn;             //유저 주민번호
    private String ssn1;            //유저 주민번호(앞자리)
    private String ssn2;            //유저 주민번호(뒷자리)

    private String year;            //유저 생년
    private String month;           //유저 월
    private String day;             //유저 일

    private String phone1;          //유저 번호 앞자리
    private String phone2;          //유저 번호 중간자리
    private String phone3;          //유저 번호 뒷자리
    private String email;           //유저 이메일
    private String gender;          //유저 성별
    private String address;         //유저 통합 주소
    private String address1;        //유저 사는곳
    private String address2;        //유저 상세주소
    private String jobKey;          //유저 직업코드
    private String status;          //유저 상태
    private String saveStatus;      //유저 활성화

    private String signDate;        // 가입날짜
    private String modifyDate;      // 정보수정 날짜

    private String signDateStart;   // 가입일자 시작
    private String signDateEnd;     // 가입일자 끝
    private String modifyDateStart; // 수정일자 시작
    private String modifyDateEnd;   // 수정일자 끝
    
    private String admin_yn;        // 관리자 여부
    private String resCnt;          // 사용자 등록계좌 개수
    
    private LocalDateTime reg_Date;
    private LocalDateTime update_Date;





    private UserMember userMember;
    private String finTechUseNum;
    private String accountAlias;
    private String bankCodeStd;
    private String bankCodeSub;
    private String bankName;
    private String savingsBankName;
    private String holderName;
    private String holderType;
    private String accountType;
    private String accountNo;
    private String inquiryAgreeYn;
    private String inquiryAgreeDt;
    private String transferAgree_yn;
    private String transferAgreeDt;
    private String state;
    private String createDt;
    private String modifyDt;
//    private List<AccountHist> accountHists = new ArrayList();
}
