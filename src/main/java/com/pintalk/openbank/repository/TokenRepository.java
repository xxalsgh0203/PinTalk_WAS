package com.pintalk.openbank.repository;

import com.pintalk.openbank.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<Token, Integer> {


    @Query("select a.access_token from Token a where a.no = (select max(u.no) from Token u)")
    String getMaxAccessToken();
}