package com.pichincha.backend.test.service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.pichincha.backend.test.dto.AccountDto;
import com.pichincha.backend.test.dto.FavoriteDto;
import com.pichincha.backend.test.dto.NewTransactionDto;
import com.pichincha.backend.test.dto.TransactionDto;
import com.pichincha.backend.test.model.Transaction;
import com.pichincha.backend.test.repository.AccountRepository;
import com.pichincha.backend.test.repository.TransactionRepository;

@Service
public class AccountService implements IAccountService {
	
	private static final Logger logger = LoggerFactory.getLogger(AccountService.class);
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private TransactionRepository transactionRepository;

	public AccountDto getAccount(Long id) {
		return accountRepository.findById(id)
			.map(account -> new AccountDto(account.getNumber(), account.getType(), account.getCreationDate()))
			.orElse(null);
	}
	
	
	/**
	 * Returns a list of all transactions for a account with passed id.
	 *
	 * @param accountId id of the account
	 * @return list of transactions sorted by creation date descending - most recent first
	 */
	@Override
	@Transactional(readOnly = true)
	public List<TransactionDto> getTransactionsForAccount(Long accountId) {
		List<Transaction> lstTransactions = transactionRepository.findAllByIdAccountOrderByCreationDateDesc(accountId);
		List<TransactionDto> lstTransactionsFinal = null;
		
		if(lstTransactions != null) {
			lstTransactionsFinal = lstTransactions.stream().map(t -> new TransactionDto(t.getId(), t.getComment(), t.getType(), t.getCreationDate())).collect(Collectors.toList());
		}
		return lstTransactionsFinal;
	}

	/**
	 * Creates a new transaction
	 *
	 * @param newTransactionDto data of new transaction
	 * @return id of the created transaction
	 * @throws IllegalArgumentException if there is no account for passed newTransactionDto.accountId
	 */
	public Long addTransaction(NewTransactionDto transactionDto) {
		Transaction transactionNew = null;
		LocalDateTime dateTime = LocalDateTime.now();
		try {
			if(transactionDto == null || transactionDto.getAccountId() == null) {
				throw new IllegalArgumentException("account id is required..");
			}
			transactionNew = new Transaction();
			transactionNew.setComment(transactionDto.getComment());
			transactionNew.setCreationDate(dateTime);
			transactionNew.setIdAccount(transactionDto.getAccountId());
			transactionNew.setType(transactionDto.getType());
			transactionRepository.save(transactionNew);
		} catch (IllegalArgumentException e) {
			logger.error("Error.: Internal server. {}", e.getMessage());
		}	
		return transactionNew.getId();
	}


	@Override
	public List<FavoriteDto> showFavorites() {
		RestTemplate restTemplate = new RestTemplate();
		String url = "https://test-accounts.free.beeceptor.com/";
		ResponseEntity<String> resp = restTemplate.postForEntity(url, restTemplate, String.class);
		logger.info("Respuesta ", resp);
		return null;
	}
	
	
	

}
