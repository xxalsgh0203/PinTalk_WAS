package com.pintalk.openbank.service;

import com.pintalk.openbank.entity.Token;
import com.pintalk.openbank.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class OpenBankService {

    @Autowired
    TokenRepository tokenRepository;

    @Value("${OpenBank-Client-Id}")
    private String client_id;

    @Value("${OpenBank-Client-Secret}")
    private String client_secret;
    @Value("${OpenBank-Scope}")
    private String scope;
    @Value("${OpenBank-Grant-Type}")
    private String grant_type;

    //오픈뱅킹 토큰발급
    public void Token() {

        String  requestURL = "https://developers.kftc.or.kr/proxy/oauth/2.0/token?client_id=";
        requestURL += client_id + "&client_secret=";
        requestURL += client_secret + "&scope=";
        requestURL += scope + "&grant_type=";
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


}
