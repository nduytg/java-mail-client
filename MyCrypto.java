// Class xử lý việc bảo mật
// Bao gồm những chức năng chính sau:
// Mã hóa đối xứng, bất đối xứng, hàm băm, chữ ký điện tử

// 2 thư viện security cho java
import java.security.*;
import java.util.Base64;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.Cipher;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
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
	// Cai nay dung de tao random session key 
	// cho mode Ket hop ma hoa doi xung va bat doi xung
//	public String createSymmetricKey(int keySize, String mode)
//	{
//		String encodedKey = null;
//		if(keySize <= 0)
//		{
//			System.out.println("Invalid key size, key size must be postive!");
//			return encodedKey;
//		}
//		
//		if(keySize < 128)
//		{
//			System.out.println("Warning: Key size is less than 128 bits!");	
//		}
//		
//		try 
//		{
//			// Khoi tao key!
//			KeyGenerator keyGen = KeyGenerator.getInstance(mode);
//			keyGen.init(keySize);
//			Key key = keyGen.generateKey();
//			System.out.println("Algorithm: " + mode);
//			System.out.println("Key generated: " + key.toString());
//			System.out.println("Key Generation Completed!");
//			
//			//Encode lai thanh string de export ra
//			//byte[] keyBytes = key.getEncoded();
//			//encodedKey = new String(Base64.getEncoder().encode(keyBytes));
//			encodedKey = keyToString(key);
//			System.out.println("Your secret key: " + encodedKey);
//			return encodedKey;
//		} 
//		catch (NoSuchAlgorithmException e) 
//		{
//			System.out.println("Only supported: DES, TripleDES, AES, RC2, RC4, RC5, Blowfish, PBE.");
//			e.printStackTrace();
//		}
//		return encodedKey;
//	}
	
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
	
	
	public static String AsymEncryptMessage(String key, String initVector, String message)
	{
		return "";
	}
	
	public static String AsymDecryptMessage(String key, String initVector, String cipherText)
	{
		return "";
	}
	
	
	// Generate 2048-bit RSA Key Pair
	public static KeyPair createRSAKeyPair()
	{	
		try 
		{
			System.out.println("Generating RSA Key pair....");
			KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
			keyGen.initialize(2048);
			KeyPair keyPair = keyGen.generateKeyPair();
			System.out.println("RSA key pair has been completed! ^^");
			System.out.println("Private Key: " + keyToString(keyPair.getPrivate()));
			System.out.println("Public Key: " + keyPair.getPublic());
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
	
	 public static void exportRSAtoXML(KeyPair keypair, String filename)
	 {
		
		try {
			ObjectOutputStream outputStream;
			outputStream = new ObjectOutputStream(new FileOutputStream(filename));
			outputStream.writeObject(keypair);
			outputStream.close();
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
	
	public void importRSAKeyPair()
	{
		
	}
	
	public void importRSAPrivate()
	{
		
	}
	
	public void importRSAPublic()
	{
		
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



