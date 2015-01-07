package com.cse.warana.service;

/**
 * Created by Sashika
 * on Jan 06 0006, 2015.
 */
public interface CryptoService {

    public String encrypt(String plainText);

    public String decrypt(String cipherText);
}
