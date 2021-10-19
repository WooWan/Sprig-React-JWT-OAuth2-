package com.oauth2.OAuth2.controller;

import com.oauth2.OAuth2.domain.User;
import com.oauth2.OAuth2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserRepository userRepository;

    @GetMapping("/api/user")
    public List<User> userList() {
        List<User> userList = userRepository.findAll();
        log.info("users : {}" , userList);
        return userList;
    }
}
