package com.pichincha.backend.test.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.pichincha.backend.test.dto.AccountDto;
import com.pichincha.backend.test.dto.FavoriteDto;
import com.pichincha.backend.test.dto.NewTransactionDto;
import com.pichincha.backend.test.dto.TransactionDto;
import com.pichincha.backend.test.service.IAccountService;

@Controller
@RestController
@RequestMapping("/accounts")
public class AccountController {
	
	private static final Logger logger = LoggerFactory.getLogger(AccountController.class);
	
	@Autowired
	private IAccountService accountService;

	@GetMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public AccountDto getAccount(@PathVariable Long id) {
		logger.info("Inicia getAccount");
		return accountService.getAccount(id);
	}
	
	@PostMapping(value = "/{id}/transactions")
	public ResponseEntity<Long> addTransaction(@PathVariable Long id, @RequestBody NewTransactionDto transactionDto) {
		Long idTransaction = null;
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		logger.info("Inicia addTransaction");
		try {
			transactionDto.setAccountId(id);
			idTransaction = accountService.addTransaction(transactionDto);
		} catch (IllegalArgumentException e) {
			logger.error("Error.: Internal Server {}", e);
			return new ResponseEntity<Long>(null, headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Long>(idTransaction, headers, HttpStatus.CREATED);
	}
	
	@PostMapping(value = "/showFavorite")
	public ResponseEntity<Boolean> showTransaction() {
		
		List<FavoriteDto> list =  accountService.showFavorites();
		
		return null;
	}
	
	@GetMapping(value = "/{id}/transactions")
	@ResponseStatus(HttpStatus.OK)
	public List<TransactionDto> getTransactionsForAccount(@PathVariable Long id) {
		logger.info("Inicia getTransactionsForAccount");
		return accountService.getTransactionsForAccount(id);
	}

}
