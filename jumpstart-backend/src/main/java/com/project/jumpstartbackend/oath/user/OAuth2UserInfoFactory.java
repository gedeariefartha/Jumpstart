package com.project.jumpstartbackend.oath.user;

import java.util.Map;

import com.project.jumpstartbackend.entity.AuthProvider;
import com.project.jumpstartbackend.execption.OAuth2AuthenticationProcessingException;


public class OAuth2UserInfoFactory {

    public static OAuth2UserInfo getOAuth2UserInfo(String registrationId, Map<String, Object> attributes) {
        if(registrationId.equalsIgnoreCase(AuthProvider.facebook.toString())) {
            return new FacebookOAuth2UserInfo(attributes);
        } else {
            throw new OAuth2AuthenticationProcessingException("Sorry! Login with " + registrationId + " is not supported yet.");
        }
    }
}
