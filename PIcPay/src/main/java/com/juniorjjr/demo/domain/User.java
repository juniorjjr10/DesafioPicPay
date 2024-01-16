package com.juniorjjr.demo.domain;

import com.juniorjjr.demo.domain.dto.UserDTO;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity(name = "users")
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(unique = true)
    private String document;
    @Column(unique = true)
    private String email;
    private String password;
    private UserType userType;
    private BigDecimal balance;

    public User(UserDTO user) {
        this.name = user.name();
        this.document = user.document();
        this.email = user.email();
        this.password = user.password();
        this.userType = user.userType();
        this.balance = user.balance();

    }
}
