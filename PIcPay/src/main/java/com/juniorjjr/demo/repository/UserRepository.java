package com.juniorjjr.demo.repository;

import com.juniorjjr.demo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
