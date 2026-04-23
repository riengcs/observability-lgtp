package com.example.dogservice.security;

import java.util.List;

public record UserProperties(String name, String password, List<String> roles) {

}
