package com.tonin.animaltrack.service;

public interface EncryptionService {

    String encrypt(String data);

    boolean checkEncryption(String clearData, String encryptedData);
}
