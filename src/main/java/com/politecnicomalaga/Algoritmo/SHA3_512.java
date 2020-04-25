package com.politecnicomalaga.Algoritmo;

import com.politecnicomalaga.DataTransfer.DataTransfer;
import org.apache.commons.codec.binary.Hex;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class SHA3_512 implements Algoritmo{
    private MessageDigest messageDigest;
    private String salt;

    public SHA3_512() throws NoSuchAlgorithmException {
        messageDigest = MessageDigest.getInstance("SHA3-512");
    }

    @Override
    public String getPasswordHash(String password) throws NoSuchAlgorithmException {
        return generateSaltedPassHash(password,generateSalt());
    }

    public String generateSaltedPassHash(String password, String salt) throws NoSuchAlgorithmException {
        if (messageDigest == null)
            throw new NoSuchAlgorithmException("Algorithm not exists");
        messageDigest.update(salt.getBytes());
        byte[] bytes = messageDigest.digest(password.getBytes());
        return new String(Hex.encodeHex(bytes));
    }

    @Override
    public String getSalt() {
        return salt;
    }

    @Override
    public boolean checkPassword(DataTransfer datos) throws NoSuchAlgorithmException {
        String password = (String) datos.get("password");
        String passwordHash = (String) datos.get("passwordHash");
        String salt = (String) datos.get("salt");
        String newPassword = generateSaltedPassHash(password,salt);
        return newPassword.equals(passwordHash);
    }

    public String generateSalt() {
        SecureRandom secureRandom;
        try {
            secureRandom = SecureRandom.getInstance("SHA1PRNG");
            byte[] salt = new byte[16];
            secureRandom.nextBytes(salt);
            setSalt(Hex.encodeHexString(salt));
            return Hex.encodeHexString(salt);
        } catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        return null;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

}
