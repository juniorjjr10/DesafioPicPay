package com.juniorjjr.demo.repository;

import com.juniorjjr.demo.domain.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {
}
