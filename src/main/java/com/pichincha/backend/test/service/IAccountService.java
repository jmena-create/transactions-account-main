package com.pichincha.backend.test.service;

import java.util.List;

import com.pichincha.backend.test.dto.AccountDto;
import com.pichincha.backend.test.dto.FavoriteDto;
import com.pichincha.backend.test.dto.NewTransactionDto;
import com.pichincha.backend.test.dto.TransactionDto;

public interface IAccountService {
	
	public AccountDto getAccount(Long id);
	
	public List<TransactionDto> getTransactionsForAccount(Long accountId);
	
	public Long addTransaction(NewTransactionDto transactionDto);
	
	public List<FavoriteDto> showFavorites();
	
}
