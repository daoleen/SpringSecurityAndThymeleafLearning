package com.daoleen.devicemeeting.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 14.6.14.
 */

@Controller
@RequestMapping("/account")
public class AccountController {
    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

    @RequestMapping(method = RequestMethod.GET, value = "/login")
    public String login(Model model, @RequestParam(value = "fail", required = false, defaultValue = "false") boolean fail) {
        logger.debug("The fail value is: {}", fail);
        model.addAttribute("fail", fail);
        return "account/login";
    }

    private void someUsefulSecurityApiMethods() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.getAuthentication().isAuthenticated();
        securityContext.getAuthentication().getAuthorities();
        securityContext.getAuthentication().getPrincipal();
        securityContext.getAuthentication().getName();

        // creating and login new user
        List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("ROLE_USER", "ROLE_ADMIN"); // user roles
        UserDetails userDetails = new User("username", "password", authorities);
        List<UserDetails> listUsersDetails = new ArrayList<UserDetails>();
        listUsersDetails.add(userDetails);
        UserDetailsManager userDetailsManager = new InMemoryUserDetailsManager(listUsersDetails);
        //userDetailsManager = new JdbcUserDetailsManager();    // for JDBC
        userDetailsManager.createUser(userDetails);

        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(),
                userDetails.getAuthorities());
        securityContext.setAuthentication(authentication);

        // and so on ...
    }
}
