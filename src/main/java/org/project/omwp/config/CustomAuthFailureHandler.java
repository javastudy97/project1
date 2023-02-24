package org.project.omwp.config;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

@Component
public class CustomAuthFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {

        String errorMsg="";

        if(exception instanceof BadCredentialsException) {
            errorMsg = "아이디 또는 비밀번호가 맞지 않습니다.";
        }else if(exception instanceof UsernameNotFoundException){
            errorMsg = "계정이 존재하지 않습니다.";
        }


        errorMsg = URLEncoder.encode(errorMsg, "UTF-8");
        setDefaultFailureUrl("/member/login?error=true&exception=" + errorMsg);
        super.onAuthenticationFailure(request, response, exception);
    }
}
