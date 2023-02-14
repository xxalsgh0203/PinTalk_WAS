package com.pintalk.account.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.pintalk.common.entity.BaseEntity;
import com.pintalk.user.entity.UserMember;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "account")
@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class Account extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "ACCOUNT_GENERATOR")
    @Column(columnDefinition = "int not null comment '계좌_SEQ'")
    private Integer no;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_member_no")
    @JsonBackReference
    private UserMember userMember;


    @Column(columnDefinition = "varchar(24) not null comment '핀테크이용번호'")
    private String finTechUseNum;

    @Column(columnDefinition = "varchar(50) null comment '계좌별명'")
    private String accountAlias;

    @Column(columnDefinition = "varchar(3) not null comment '출금(개설)기관.대표코드'")
    private String bankCodeStd;

    @Column(columnDefinition = "varchar(7) null comment '출금(개설)기관.점별코드'")
    private String bankCodeSub;

    @Column(columnDefinition = "varchar(20) null comment '출금(개설)기관명'")
    private String bankName;

    @Column(columnDefinition = "varchar(20) comment '개별저축은행명'")
    private String savingsBankName;

    @Column(columnDefinition = "varchar(20) null comment '계좌예금주명'")
    private String holderName;

    @Column(columnDefinition = "int null comment '계좌잔액'")
    private String accountBalance;

    @Column(columnDefinition = "varchar(1) not null comment '계좌구분(P:개인)'")
    private String holderType;

    @Column(columnDefinition = "varchar(1) not null comment '계좌종류(‘1’:수시입출금, ‘2’:예적금, ‘6’:수익증권, ‘T’:종합계좌)'")
    private String accountType;

    @Column(columnDefinition = "varchar(20) not null comment '계좌번호'")
    private String accountNo;

    @Column(columnDefinition = "varchar(1) null comment '조회서비스 동의여부'")
    private String inquiryAgreeYn;

    @Column(columnDefinition = "varchar(14) null comment '조회서비스 동의일시'")
    private String inquiryAgreeDt;

    @Column(columnDefinition = "varchar(1) null comment '출금서비스 동의여부'")
    private String transferAgree_yn;

    @Column(columnDefinition = "varchar(14) null comment '출금서비스 동의일시'")
    private String transferAgreeDt;

    @Column(columnDefinition = "varchar(2) not null comment '계좌상태(‘01’:사용, ‘09’:해지)'")
    private String state;


    @Column(columnDefinition = "varchar(14) not null comment '계좌개설 일시'")
    private String createDt;
    @Column(columnDefinition = "varchar(14) not null comment '계좌수정 일시'")
    private String modifyDt;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<AccountHist> accountHists = new ArrayList<>();



}
