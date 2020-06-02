/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.fafic.util;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.StringDecoder;
import org.apache.commons.codec.binary.Hex;


/**
 *
 * @author Luciano
 */
public class PasswordGeneration {
    
    
    private static SecretKeySpec secretKeySepc;
    
    private static Cipher cipher;
    
    private static byte[] getKey(){
       try {
            
           String keySecret = "l(*F@f1k&#";
           MessageDigest md = MessageDigest.getInstance("MD5");
           return md.digest(keySecret.getBytes());
           
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(PasswordGeneration.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
        
    }
    
    public static String encryptPassword(String senha){
        String encriptedSenha = null;
        try {
            byte[] cifraKey = getKey();
            secretKeySepc = new SecretKeySpec(cifraKey, "AES");
            cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySepc);
            byte[] resultado = cipher.doFinal(senha.getBytes("UTF-8"));

            encriptedSenha = Hex.encodeHexString(resultado);

        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException | UnsupportedEncodingException ex) {
            Logger.getLogger(PasswordGeneration.class.getName()).log(Level.SEVERE, null, ex);
        }
       
         return encriptedSenha;

    }
    
    public static byte[] decryptPassword(String senha){
        byte [] senhaPlana = null;
        try {
            byte[] keyPass = getKey();
            byte[] bufferSenha = Hex.decodeHex(senha);
            secretKeySepc = new SecretKeySpec(keyPass, "AES");
            cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySepc);
            senhaPlana = cipher.doFinal(bufferSenha);
        } catch (DecoderException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException ex) {
            Logger.getLogger(PasswordGeneration.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return senhaPlana;
    }
    
    public static boolean checkPassword(String senhaPlana, String senhaEncriptada){
        return encryptPassword(senhaPlana).equals(senhaEncriptada);
    }
    
}
