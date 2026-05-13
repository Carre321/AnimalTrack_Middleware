package com.tonin.animaltrack.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.mindrot.jbcrypt.BCrypt;

import com.tonin.animaltrack.service.EncryptionService;

public class EncryptionServiceBCryptImpl implements EncryptionService {

	private static Logger logger = LogManager.getLogger(EncryptionServiceBCryptImpl.class.getName());

    public EncryptionServiceBCryptImpl() {
    }

    @Override
    public String encrypt(String data) {
        return BCrypt.hashpw(data, BCrypt.gensalt());
    }

    @Override
    public boolean checkEncryption(String clearData, String encryptedData) {
        if (clearData == null || encryptedData == null) {
            return false;
        }
        try {
            return BCrypt.checkpw(clearData, encryptedData);
        } catch (IllegalArgumentException e) {
            logger.warn("No se pudo comprobar la contrasena cifrada.", e);
            return false;
        }
    }
}
