package com.pintalk.openbank;

import com.pintalk.openbank.entity.Token;
import com.pintalk.openbank.repository.TokenRepository;
import com.pintalk.openbank.service.OpenBankService;
import com.pintalk.user.entity.QUserMember;
import com.pintalk.user.entity.UserMember;
import com.querydsl.jpa.impl.JPAQuery;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Component
@TestPropertySource("classpath:config.properties")
@Transactional
public class ApiTests {

    @Autowired
    EntityManager entityManager;


    @Autowired
    OpenBankService openBankService;

    @Autowired
    TokenRepository tokenRepository;

    @Value("${OpenBank.ClientId}")
    private String client_id;
    @Value("${OpenBank.ClientSecret}")
    private String client_secret;
    @Value("${OpenBank.TokenScope}")
    private String tokenScope;
    @Value("${OpenBank.GrantType}")
    private String grant_type;
    @Value("${OpenBank.redirectUri}")
    private String redirectUri;
    @Value("${OpenBank.AuthorizeScope}")
    private String authorizeScope;

    @Test
    //오픈뱅킹 토큰발급
    public void Token() {

        String  requestURL = "https://developers.kftc.or.kr/proxy/oauth/2.0/token?client_id=";
                requestURL += client_id + "&client_secret=";
                requestURL += client_secret + "&scope=";
                requestURL += tokenScope + "&grant_type=";
                requestURL += grant_type;
        try {
            URL url = new URL(requestURL);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST"); // http 메서드
            conn.setRequestProperty("Accept", "application/json"); // header Accept 정보
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded"); // header Content-Type 정보

            conn.setDoOutput(true); // 서버로부터 받는 값이 있다면 true

            // 서버로부터 데이터 읽어오기
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = null;

            if(conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                while ((line = br.readLine()) != null) { // 읽을 수 있을 때 까지 반복
                    sb.append(line);
                }
                br.close();
            }

            JSONObject obj = new JSONObject(sb.toString()); // json으로 변경 (역직렬화)

            System.out.println("obj : " + obj);

            Token token = new Token();

            token.setAccess_token((String) obj.get("access_token"));
            token.setClient_use_code((String) obj.get("client_use_code"));
            token.setScope((String) obj.get("scope"));
            token.setToken_type((String) obj.get("token_type"));
            token.setExpires_in((Integer) obj.get("expires_in"));

            tokenRepository.save(token);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void accountRegister(){







        String  requestURL = "https://testapi.openbanking.or.kr/v2.0/token?";
        requestURL += client_id + "client_secret=";
        requestURL += client_secret + "scope=";
        requestURL += tokenScope + "grant_type=";
        requestURL += grant_type;

    }

    @Test
    public void jpaQuerydsl(){

        List<Token> found = entityManager.createQuery("select p from Token p").getResultList();
        System.out.println("found : " + found);

    }

    @Test
    public void queryDsl_findPostsByMyCriteria_Three(){
        JPAQuery<UserMember> query = new JPAQuery<>(entityManager);
        QUserMember qUserMember = new QUserMember("u");

        List<UserMember> userMembers = query.from(qUserMember)
                .where(qUserMember.name.contains("새"))
                .orderBy(qUserMember.no.desc())
                .fetch();

        assertThat(userMembers).hasSize(3);

        System.out.println("userMembers : " + userMembers);

    }
    @Test
    public void token2(){
        JPAQuery<Token> query = new JPAQuery<>(entityManager);
        QUserMember qUserMember = new QUserMember("u");

        List<Token> userMembers = query.from(qUserMember)
                .where(qUserMember.name.contains("새"))
                .orderBy(qUserMember.no.desc())
                .fetch();

        assertThat(userMembers).hasSize(3);

        System.out.println("userMembers : " + userMembers);

    }
    @Test
    public void token33(){

        Token tokens = new Token();
        System.out.println("ssss : " + tokens);

    }

    @Test
    public void register(){
        System.out.println(tokenRepository.getMaxAccessToken());
    }



    @Test
    @SuppressWarnings("unchecked")
    //오픈뱅킹 토큰발급
    public void authorize() throws IOException {

        String  requestURL = "https://developers.kftc.or.kr/proxy/oauth/2.0/authorize?";
        requestURL += "response_type=code";
        requestURL += "&client_id="+client_id;
        requestURL += "&redirect_uri="+redirectUri;
        requestURL += "&scope="+authorizeScope;
        requestURL += "&client_info=test";
        requestURL += "&state=b80BLsfigm9OokPTjy03elbJqRHOfGSY";
        requestURL += "&auth_type=0";
        requestURL += "&cellphone_cert_yn=Y";
        requestURL += "&authorized_cert_yn=Y";
        requestURL += "&account_hold_auth_yn=N";
        requestURL += "&register_info=A";
        URL url = new URL(requestURL);

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        try {
            conn.setRequestMethod("GET"); // http 메서드
            conn.setRequestProperty("Accept", "*/*"); // header Accept 정보

            conn.setDoOutput(true); // 서버로부터 받는 값이 있다면 true
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void serviceTest() throws IOException {

        System.out.println("sss : " + openBankService.authorize());
    }

}
