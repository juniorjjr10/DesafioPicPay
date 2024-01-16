package com.juniorjjr.demo.domain.dto;

import java.math.BigDecimal;

public record TransactionDTO(BigDecimal amount,Long payerId, Long PayeeId) {
}
