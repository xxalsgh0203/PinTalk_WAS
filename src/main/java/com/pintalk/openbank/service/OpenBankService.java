package com.pintalk.openbank.service;

import com.pintalk.openbank.entity.Token;
import com.pintalk.openbank.repository.TokenRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
@PropertySource(value = "classpath:config.properties")
public class OpenBankService {

    @Autowired
    TokenRepository tokenRepository;

    @Value("${OpenBank.ClientId}")
    private String client_id;
    @Value("${OpenBank.ClientSecret}")
    private String client_secret;
    @Value("${OpenBank.TokenScope}")
    private String scope;
    @Value("${OpenBank.GrantType}")
    private String grant_type;
    @Value("${OpenBank.redirectUri}")
    private String redirectUri;
    @Value("${OpenBank.AuthorizeScope}")
    private String authorizeScope;

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

    //사용자인증
    public String authorize() {

        String result ="";

        String requestURL = "https://developers.kftc.or.kr/proxy/oauth/2.0/authorize?";
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
        try {
            URL url = new URL(requestURL);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET"); // http 메서드
            conn.setRequestProperty("Accept", "*/*"); // header Accept 정보

            conn.setDoOutput(true); // 서버로부터 받는 값이 있다면 true

            if(conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                result = conn.getHeaderField("Location");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
