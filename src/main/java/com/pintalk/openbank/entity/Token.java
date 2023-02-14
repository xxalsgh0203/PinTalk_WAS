package com.pintalk.openbank.entity;

import com.pintalk.common.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Token extends BaseEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(columnDefinition = "int not null comment '토큰_고유번호'")
    private int no;

    @Column(columnDefinition = "varchar(255) not null comment '오픈뱅킹에서 발행된 Access Token'")
    private String access_token;
    @Column(columnDefinition = "varchar(10) not null comment '이용기관코드'")
    private String client_use_code;
    @Column(columnDefinition = "varchar(255) not null comment 'Access Token 권한 범위'")
    private String scope;
    @Column(columnDefinition = "varchar(255) not null comment 'Access Token 유형'")
    private String token_type;
    @Column(columnDefinition = "varchar(255) not null comment 'Access Token 만료 기간'")
    private String expires_in;
}
