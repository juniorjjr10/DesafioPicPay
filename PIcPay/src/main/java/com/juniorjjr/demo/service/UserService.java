package com.juniorjjr.demo.service;

import com.juniorjjr.demo.domain.User;
import com.juniorjjr.demo.domain.UserType;
import com.juniorjjr.demo.domain.dto.UserDTO;
import com.juniorjjr.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserService {



    @Autowired
    private UserRepository repository;
    public void saveUser(User user) {
        this.repository.save(user);
    }
    public User createUser(UserDTO userDTO){
        User newUser = new User(userDTO);
        this.saveUser(newUser);
        return newUser;



    }



    public List<User> getAllUsers(){
        return this.repository.findAll();

    }
    public User findByid(Long id) throws Exception {
        return repository.findById(id).orElseThrow(()-> new Exception("Usuario Não encontrado"));

    }

    public Boolean validate(User payer, BigDecimal amount) throws Exception {
        if (payer.getUserType()== UserType.MERCHANT){
            throw new Exception("um usuario logista não pode fazer transaçoões");
        }
        if (payer.getBalance().compareTo(amount)< 0){
            throw new Exception("saldo insuficiente");
        }
        return true;


    }
}
