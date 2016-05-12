// Class xử lý việc bảo mật
// Bao gồm những chức năng chính sau:
// Mã hóa đối xứng, bất đối xứng, hàm băm, chữ ký điện tử

// 2 thư viện security cho java
import java.security.*;
import java.util.*;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;

import java.io.UnsupportedEncodingException;
import java.lang.String;

public class MyCrypto 
{
	private String privateKey;
	private String publicKey;
	private String symmetricKey;

	public String digestMessage(String mess, String mode)
	{
		if (mess.length() == 0 || mode.length() == 0)
		{
			System.out.println("Empty input strings!");
			return "";
		}
		byte [] plaintext = mess.getBytes();
		String result = "";
		
		try 
		{
			MessageDigest messDigest = MessageDigest.getInstance(mode);
			
			// print out the provider used
		    System.out.println( "\nKey provided by\n" + messDigest.getProvider().getInfo() );
			
		   //digest message and return
		    messDigest.update(plaintext);
		    result = new String (messDigest.digest());
		} 
		catch (NoSuchAlgorithmException e) 
		{
			System.out.println("Only supported: MD2, MD5, SHA-1, SHA-256, SHA-383, SHA-512");
			e.printStackTrace();
		}
		System.out.println("Message digest: " + result);
		return result;
	}
	
	
	// Tao symmetric key dung de ma hoa van ban
	public String createSymmetricKey(int keySize, String mode)
	{
		String encodedKey = null;
		if(keySize <= 0)
		{
			System.out.println("Invalid key size, key size must be postive!");
			return encodedKey;
		}
		
		if(keySize < 128)
		{
			System.out.println("Warning: Key size is less than 128 bits!");	
		}
		
		try 
		{
			// Khoi tao key!
			KeyGenerator keyGen = KeyGenerator.getInstance(mode);
			keyGen.init(keySize);
			Key key = keyGen.generateKey();
			System.out.println("Algorithm: " + mode);
			System.out.println("Key generated: " + key.toString());
			System.out.println("Key Generation Completed!");
			
			//Encode lai thanh string de export ra
			byte[] keyBytes = key.getEncoded();
			encodedKey = new String(Base64.getEncoder().encode(keyBytes));
			System.out.println("Your secret key: " + encodedKey);
			return encodedKey;
		} 
		catch (NoSuchAlgorithmException e) 
		{
			System.out.println("Only supported: DES, TripleDES, AES, RC2, RC4, RC5, Blowfish, PBE.");
			e.printStackTrace();
		}
		return encodedKey;
	}
	
	public SecretKeySpec importSymmetricKey(String secretKey, String mode)
	{
		SecretKeySpec keySpec = null;
		if(secretKey.length() <= 0 || mode.length() <= 0)
		{
			System.out.println("Invalid input, input size must larger than 0!");
			return keySpec;
		}
			
		byte[] keyBytes;
		try 
		{
			keyBytes = Base64.getDecoder().decode(secretKey.getBytes("UTF-8"));
			keySpec = new SecretKeySpec(keyBytes,mode);
			System.out.println("Key get: " + keySpec.toString());
			System.out.println("Key algorithm: " + keySpec.getAlgorithm());
		} 
		catch (UnsupportedEncodingException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return keySpec;
	}
	
	public String encryptMessage(Key key, String message)
	{
		try 
		{
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			System.out.println("Key provider:\n" + cipher.getProvider().getInfo());
			
			System.out.println("Message is being encrypted...");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] plainText = message.getBytes("UTF-8");
			byte[] cipherText = cipher.doFinal(plainText);
			String encryptMess = new String (cipherText,"UTF-8");
			System.out.println("Message is encrypted!");
			System.out.println("Ciphertext: " + encryptMess);
			return encryptMess;
		}
		catch (NoSuchAlgorithmException | BadPaddingException | InvalidKeyException | NoSuchPaddingException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (UnsupportedEncodingException | IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace(); 
		}
		return null; 
	}
	
	public static String keyToString(Key key)
	{
		byte[] keyBytes = key.getEncoded();
		return new String(Base64.getEncoder().encode(keyBytes));
	}
	
	public static Key stringToKey(String string)
	{
		Key key = null;
		byte[] keyBytes;
		try 
		{
			keyBytes = Base64.getDecoder().decode(string.getBytes("UTF-8"));
			SecretKeySpec keySpec = new SecretKeySpec(keyBytes,"AES");
			key = keySpec;
		} catch (UnsupportedEncodingException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return key;
	}
	
	public void decryptMessage(Key key, String message)
	{
		
	}
	
	public String encryptAsymmetricKey(String plainText, String mode)
	{

		return "";
	}
	
	public String decryptAsymmetricKey(String cipherText)
	{
		String plainText;
		return "";
	}
	
	
	
	public String getPrivateKey() {
		return privateKey;
	}
	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}
	public String getPublicKey() {
		return publicKey;
	}
	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}
	public String getSymmetricKey() {
		return symmetricKey;
	}
	public void setSymmetricKey(String symmetricKey) {
		this.symmetricKey = symmetricKey;
	}

	
}



