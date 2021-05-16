package com.abc.service;

import org.springframework.stereotype.Service;

@Service
public class CryptoServiceImpl implements CryptoService {

	@Override
	public String encrypt(String password) {
		// Write your logic to encrypt password
		return password;
	}

	@Override
	public String decrypt(String decrypt) {
		// Write your logic to decrypt password
		return decrypt;
	}

}
