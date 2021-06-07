package com.example.jpa.service;

public interface CryptoService {

    String encrypt(String password);

    String decrypt(String password);
}
