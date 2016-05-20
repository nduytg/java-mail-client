// Class xử lý việc bảo mật
// Bao gồm những chức năng chính sau:
// Mã hóa đối xứng, bất đối xứng, hàm băm, chữ ký điện tử

// 2 thư viện security cho java
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import javax.crypto.spec.IvParameterSpec;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.String;

public class MyCrypto 
{
	private String privateKey;
	private String publicKey;
	private String symmetricKey;
	//Init vector luu cung, khoi can truyen qua lai
	private String symmectricInitVector = "HelloDuyHelloDuy";
	private String rsaInitVector = "HelloDuyHelloDuy";

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
	
	// Algorithm: AES
	// Mode: CBC
	// Tham so: Key, IV, Messsge
	public static String symEncryptMessage(String key, String initVector, String message)
	{
		try 
		{
			IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
			// Hash thanh 32 bytes - 256 bits key
			MessageDigest sha = MessageDigest.getInstance("SHA-256");
			byte[] keyBytes = sha.digest(key.getBytes());
			
			//SecretKeySpec keySpec = new SecretKeySpec(key.getBytes("UTF-8"),"AES");
			SecretKeySpec keySpec = new SecretKeySpec(keyBytes,"AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			cipher.init(Cipher.ENCRYPT_MODE, keySpec, iv);
			
			byte[] cipherBytes = cipher.doFinal(message.getBytes());
			String cipherText = new String(Base64.getEncoder().encode(cipherBytes));
			System.out.println("Encrypted Message: " + cipherText);
			
			return cipherText;	
		}
		catch (NoSuchAlgorithmException | BadPaddingException | InvalidKeyException | InvalidAlgorithmParameterException
				| NoSuchPaddingException | UnsupportedEncodingException | IllegalBlockSizeException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		System.out.println("Encrypt failed!!");
		return null; 
	}
	
	public static String symDecryptMessage(String key, String initVector, String cipherText)
	{
		try 
		{
			IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
			
			// Hash thanh 32 bytes - 256 bits key
			MessageDigest sha = MessageDigest.getInstance("SHA-256");
			byte[] keyBytes = sha.digest(key.getBytes());
			
			//SecretKeySpec keySpec = new SecretKeySpec(key.getBytes("UTF-8"),"AES");
			SecretKeySpec keySpec = new SecretKeySpec(keyBytes,"AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			cipher.init(Cipher.DECRYPT_MODE, keySpec, iv);
			
			byte[] plainBytes = cipher.doFinal(Base64.getDecoder().decode(cipherText));
			String plainText = new String(plainBytes);
			System.out.println("Decrypted Message: " + plainText);
			
			return plainText;
		}
		catch (NoSuchAlgorithmException | BadPaddingException | InvalidKeyException | NoSuchPaddingException |
				UnsupportedEncodingException | IllegalBlockSizeException | InvalidAlgorithmParameterException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		System.out.println("Decrypt failed!!");
		return null;
	}
	
	public static String encrypRSAMessage(PublicKey key, String initVector, String message)
	{
		try 
		{
			IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));

			Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			
			byte[] cipherBytes = cipher.doFinal(message.getBytes());
			String cipherText = new String(Base64.getEncoder().encode(cipherBytes));
			System.out.println("Encrypted Message: " + cipherText);
			
			return cipherText;	
		} 
		catch (UnsupportedEncodingException | NoSuchPaddingException | NoSuchAlgorithmException | 
		InvalidKeyException | IllegalBlockSizeException | BadPaddingException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		System.out.println("RSA Encryption failed!");
		return null;
	}
	
	public static String decryptRSAMessage(PrivateKey key, String initVector, String cipherText)
	{
		try 
		{
			IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
			
			
			//SecretKeySpec keySpec = new SecretKeySpec(key.getBytes("UTF-8"),"AES");
			Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1PADDING");
			cipher.init(Cipher.DECRYPT_MODE, key);
			
			byte[] plainBytes = cipher.doFinal(Base64.getDecoder().decode(cipherText));
			String plainText = new String(plainBytes);
			System.out.println("Decrypted Message: " + plainText);
			
			return plainText;
		}
		catch (NoSuchAlgorithmException | BadPaddingException | InvalidKeyException | NoSuchPaddingException |
				UnsupportedEncodingException | IllegalBlockSizeException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
		System.out.println("RSA Encryption failed!");
		return null;
	}
	
	// Generate 2048-bit RSA Key Pair
	public static KeyPair createRSAKeyPair()
	{	
		try 
		{
			System.out.println("Generating RSA Key pair....");
			KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
			//  2048 bits
			keyGen.initialize(2048);
			KeyPair keyPair = keyGen.generateKeyPair();
			System.out.println("RSA key pair has been completed! ^^");
			System.out.println("Private Key: " + keyToString(keyPair.getPrivate()));
			System.out.println("Public Key: " + keyToString(keyPair.getPublic()));
			return keyPair;
		} 
		catch (NoSuchAlgorithmException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Failed in generating RSA Key Pair...!");
		return null;
	}
	
	// public static void exportRSAPublic
	// ghi xuong file Pem
	public static void exportRSAKeyPair(KeyPair keypair, String filename)
	{
		try
		{
			File keyPairFile = new File(filename);
			
			// File nay chua ca private key va public key
			if (keyPairFile.getParentFile() != null) 
			{
			keyPairFile.getParentFile().mkdirs();
			}
			keyPairFile.createNewFile();

			String temp;
			//ObjectOutputStream outputStream;
			//outputStream = new ObjectOutputStream(new FileOutputStream(keyPairFile));
			//outputStream.flush();
			PrintWriter writer = new PrintWriter(keyPairFile);
			
			temp = "-----BEGIN RSA PUBLIC KEY-----\n" + keyToString(keypair.getPublic()) +
	          "\n-----END RSA PUBLIC KEY-----\n";
			writer.println(temp);
			
			temp = "-----BEGIN RSA PRIVATE KEY-----\n" + keyToString(keypair.getPrivate()) + 
					"\n-----END RSA PRIVATE KEY-----\n";
			writer.println(temp);
			
			writer.close();
			
		}
		catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	 
	 
	public static KeyPair importRSAKeyPair(String filename)
	{
		 try 
		 {
			File file = new File(filename);
			FileReader reader = new FileReader(file);
			BufferedReader bufReader = new BufferedReader(reader);
			bufReader.readLine();
			
			String strKey = bufReader.readLine();
			PublicKey pubKey = stringToPubKey(strKey);
			
			bufReader.readLine();
			bufReader.readLine();
			bufReader.readLine();
			
			strKey = bufReader.readLine();
			PrivateKey pvtKey = stringToPrivateKey(strKey);
			KeyPair keyPair = new KeyPair(pubKey,pvtKey);
			bufReader.close();
			return keyPair;
		} 
		 catch (IOException e) 
		 {
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
	
	public static Key stringToKey(String string, String mode)
	{
		Key key = null;
		byte[] keyBytes;
		try 
		{
			keyBytes = Base64.getDecoder().decode(string.getBytes("UTF-8"));
			SecretKeySpec keySpec = new SecretKeySpec(keyBytes,mode);
			key = keySpec;
		} catch (UnsupportedEncodingException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return key;
	}
	
	public static PublicKey stringToPubKey(String strKey)
	{
		byte[] publicBytes = Base64.getDecoder().decode(strKey);
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicBytes);
		KeyFactory keyFactory;
		
		try {
			keyFactory = KeyFactory.getInstance("RSA");
			PublicKey pubKey = keyFactory.generatePublic(keySpec);
			return pubKey;
		} 
		catch (NoSuchAlgorithmException | InvalidKeySpecException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static PrivateKey stringToPrivateKey(String strKey)
	{
		byte[] publicBytes = Base64.getDecoder().decode(strKey);
		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(publicBytes);
		KeyFactory keyFactory;
		
		try {
			keyFactory = KeyFactory.getInstance("RSA");
			PrivateKey prvKey = keyFactory.generatePrivate(keySpec);
			return prvKey;
		} 
		catch (NoSuchAlgorithmException | InvalidKeySpecException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
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



