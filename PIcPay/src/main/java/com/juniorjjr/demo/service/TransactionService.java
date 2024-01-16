package com.juniorjjr.demo.service;

import com.juniorjjr.demo.domain.dto.TransactionDTO;
import com.juniorjjr.demo.domain.transaction.Transaction;
import com.juniorjjr.demo.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@Service
public class TransactionService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private TransactionRepository repository;

    @Autowired
    private NotficationService notficationService;

    @Autowired
    private UserService userService;


    public Transaction  createTransacion(TransactionDTO transactionDTO) throws Exception {
        var payer = this.userService.findByid(transactionDTO.payerId());
        var payee  = this.userService.findByid(transactionDTO.PayeeId());

        this.userService.validate(payer, transactionDTO.amount());

        boolean isAuthorize = authorizedTransaction();
        if (!isAuthorize){
            throw new Exception("Transação não autorizada ");
        }

        Transaction newTransaction = new Transaction();
        newTransaction.setAmount(transactionDTO.amount());
        newTransaction.setPayer(payer);
        newTransaction.setPayee(payee);
        newTransaction.setTransactionTime(LocalDateTime.now());

        payer.setBalance(payer.getBalance().subtract(transactionDTO.amount()));
        payee.setBalance(payee.getBalance().add(transactionDTO.amount()));

        this.repository.save(newTransaction);
        this.userService.saveUser(payee);
        this.userService.saveUser(payer);

        notficationService.SendNotification(payer, "transação realizada com sucesso ");
        notficationService.SendNotification(payee, "transação recebida com sucesso");

        return newTransaction;
    }
    public boolean authorizedTransaction(){
        var response = restTemplate.getForEntity("https://run.mocky.io/v3/5794d450-d2e2-4412-8131-73d0293ac1cc", Map.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            String message = (String) response.getBody().get("message");
            return "Autorizado".equalsIgnoreCase(message);
        }else {
            return false;
        }
    }


}
