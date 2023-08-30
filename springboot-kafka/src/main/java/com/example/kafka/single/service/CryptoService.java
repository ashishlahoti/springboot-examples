package com.example.kafka.single.service;

public interface CryptoService {

	public String encrypt(String password);
	
	public String decrypt(String password);
}
