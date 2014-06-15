package com.daoleen.devicemeeting.web.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Created by alex on 15.6.14.
 */

/**
 * Здесь можно вручную определить способ доступа к информации пользователя
 * Например, вместо хранения и доступа In-Memory-Users или JdbcUserService
 * можно использовать свой сервис для доступа к информации о пользователе,
 * такой как Spring Data / JPA
 * Подробнее - стр. 58
 */
public class MyUserDetailsService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Здесь просто получается из репозитория UserDetails
        // т.е. получается данные юзера (login, password, is_enabled ....)
        // и наделяется authorities

        // если юзер не найден, нужно выбросить ексепшн
        if(false) {
            throw new UsernameNotFoundException("msg");
        }


        // если здесь вернуть объект MyUserDetails (хотя без разницы), то в разметке можно будет получать
        // больше параметров. Например:
        // <span sec:authentication property="principal.MYLASTNAME">...</span>
        // или в коде так:
        // SecurityContext context = SecurityContextHolder.getContext();
        // Authentication authentication = context.getAuthentication();
        // MyUserDetails user = (MyUserDetails) authentication.getPrincipal();
        // String lastName = user.getMYLASTNAME();

        // это все работает, т.к. теперь спринг будет работать через этот провайдер
        return new MyUserDetails();
    }
}
