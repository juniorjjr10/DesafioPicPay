package com.juniorjjr.demo.domain.dto;

import com.juniorjjr.demo.domain.UserType;

import java.math.BigDecimal;

public record UserDTO(String name, String document, String email, String password, UserType userType, BigDecimal balance) {
}
