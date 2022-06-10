package com.frizzycode.whitelist.security;

import com.frizzycode.whitelist.exceptions.InvalidIpException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@Slf4j
public class CustomIpAuthenticationProvider implements AuthenticationProvider {

    protected static final Set<String> whitelist = new HashSet<>();

    public CustomIpAuthenticationProvider(){
        whitelist.add("127.0.0.1");
        whitelist.add("41.58.247.236");
        whitelist.add("192.168.8.121");
    }

    public List<String> getIpList(){
        return whitelist.stream().toList();
    }

    public void addToWhitelist(String ipAddress){
        whitelist.add(ipAddress.trim().replace(" ", ""));
    }

    public void removeFromWhitelist(String ipAddress){
        whitelist.remove(ipAddress.trim().replace(" ", ""));
    }

    public boolean existsInWhitelist(String ipAddress){
        return whitelist.contains(ipAddress.trim().replace(" ", ""));
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        WebAuthenticationDetails details = (WebAuthenticationDetails) authentication.getDetails();
        String userIp = details.getRemoteAddress();
        if (!existsInWhitelist(userIp))
            throw new InvalidIpException("Invalid IP Address");
        return new  UsernamePasswordAuthenticationToken(authentication.getPrincipal(), authentication.getCredentials(), authentication.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
