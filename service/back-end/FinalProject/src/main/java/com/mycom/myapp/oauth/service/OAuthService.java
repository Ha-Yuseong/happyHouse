package com.mycom.myapp.oauth.service;

import java.util.HashMap;

public interface OAuthService {

    HashMap<String, String> isRegisteredUser(String email);

    HashMap<String, String> getKakaoUserInfo(String accessToken);
}