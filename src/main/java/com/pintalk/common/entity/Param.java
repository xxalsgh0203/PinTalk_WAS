package com.pintalk.common.entity;

import com.pintalk.user.entity.UserMember;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString(exclude = "param")
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

    @RequestMapping(method = RequestMethod.GET, path = {"/accountList","/accountListForm"})
    public Param getAccountParam(Param param){

        param.setUserMember(param.getUserMember());
        param.setFinTechUseNum(param.getFinTechUseNum());
        param.setAccountAlias(param.getAccountAlias());
        param.setBankCodeStd(param.getBankCodeStd());
        param.setBankCodeSub(param.getBankCodeSub());
        param.setBankName(param.getBankName());
        param.setSavingsBankName(param.getSavingsBankName());
        param.setHolderName(param.getHolderName());
        param.setHolderType(param.getHolderType());
        param.setAccountType(param.getAccountType());
        param.setAccountNo(param.getAccountNo());
        param.setInquiryAgreeYn(param.getInquiryAgreeYn());
        param.setInquiryAgreeDt(param.getInquiryAgreeDt());
        param.setTransferAgree_yn(param.getTransferAgree_yn());
        param.setTransferAgreeDt(param.getTransferAgreeDt());
        param.setState(param.getState());
        param.setCreateDt(param.getCreateDt());
        param.setModifyDt(param.getModifyDt());
//        param.setAccountHists(param.getAccountHists());

        return param;
    }


    private Integer no;             //고유 번호

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




    private UserMember userMember;          //유저 고유번호
    private String finTechUseNum;           //핀테크이용번호
    private String accountAlias;            //계좌별명
    private String bankCodeStd;             //출금(개설)기관.대표코드
    private String bankCodeSub;             //출금(개설)기관.점별코드
    private String bankName;                //출금(개설)기관명
    private String savingsBankName;         //개별저축은행명
    private String holderName;              //계좌예금주명
    private String accountBalance;          //계좌잔액
    private String holderType;              //계좌구분(P:개인)
    private String accountType;             //계좌종류(‘1’:수시입출금, ‘2’:예적금, ‘6’:수익증권, ‘T’:종합계좌)
    private String accountNo;               //계좌번호
    private String inquiryAgreeYn;          //조회서비스 동의여부
    private String inquiryAgreeDt;          //조회서비스 동의일시
    private String transferAgree_yn;        //출금서비스 동의여부
    private String transferAgreeDt;         //출금서비스 동의일시
    private String state;                   //계좌상태(‘01’:사용, ‘09’:해지)
    private String createDt;                //계좌개설 일시
    private String modifyDt;                //계좌수정 일시
//    private List<AccountHist> accountHists = new ArrayList();
}
