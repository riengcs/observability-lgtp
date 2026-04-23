package com.example.dogservice.security;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties("application.security")
public record SecurityProperties(List<UserProperties> users) {

}
