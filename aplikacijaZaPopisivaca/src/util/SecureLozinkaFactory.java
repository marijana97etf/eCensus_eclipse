package util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class SecureLozinkaFactory {
	
	protected String algoritam =  "AES/ECB/PKCS5Padding";
	protected String algoritamZaGenerisanjeKljuca = "PBKDF2WithHmacSHA256";
	protected char[] kljucAlgoritma = "%*a1b2ffA8@".toCharArray();
	protected int duzinaKljuca = 256;
	protected byte[] salt;
	protected String charset = "UTF-8";

	public SecureLozinkaFactory() throws NoSuchAlgorithmException {
		 salt = new byte[8];
		 salt[0]=(byte)110;
		 salt[1]=(byte)22;
		 salt[2]=(byte)192;
		 salt[3]=(byte)4;
		 salt[4]=(byte)1;
		 salt[5]=(byte)87;
		 salt[6]=(byte)167;
		 salt[7]=(byte)221;
	}

	public static void main(String[] args) {
		if(args.length!=2) {
			System.out.println("Moraju se unijeti tacno dva argumenta u formatu :" + System.lineSeparator() +
					"java <ime-klase> <putanja-do-kriptovane-lozinke> <lozinka> ");
			return;
		}
		String putanjaDoKriptovaneLozinke = args[0],lozinka = args[1];
		try {
			SecureLozinkaFactory factory = new SecureLozinkaFactory();
			factory.enktriptujLozinku(putanjaDoKriptovaneLozinke, lozinka);
			System.out.println("Uspjesna enkkripcija.");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void enktriptujLozinku(String putanja,String lozinka) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, IOException, IllegalBlockSizeException, BadPaddingException {
		SecretKeyFactory factory = SecretKeyFactory.getInstance(algoritamZaGenerisanjeKljuca);
		KeySpec spec = new PBEKeySpec(kljucAlgoritma,salt,65536, duzinaKljuca);
		SecretKey tmp = factory.generateSecret(spec);
		SecretKey secret = new SecretKeySpec(tmp.getEncoded(), "AES");
		
		Cipher cipher = Cipher.getInstance(algoritam);
		cipher.init(Cipher.ENCRYPT_MODE, secret);
		byte[] rezultat = cipher.doFinal(lozinka.getBytes(charset));
		try (PrintWriter pw = new PrintWriter(new FileWriter(putanja))){
			byte[] enkodovanRezultat = Base64.getEncoder().encode(rezultat);
			pw.println(new String(enkodovanRezultat,charset));
		}
		
	}
	
	public String dekriptujLozinku(String putanja) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, FileNotFoundException, IOException, IllegalBlockSizeException, BadPaddingException {
		String lozinka = null;
		SecretKeyFactory factory = SecretKeyFactory.getInstance(algoritamZaGenerisanjeKljuca);
		KeySpec spec = new PBEKeySpec(kljucAlgoritma,salt,65536, duzinaKljuca);
		SecretKey tmp = factory.generateSecret(spec);
		SecretKey secret = new SecretKeySpec(tmp.getEncoded(), "AES");
		
		Cipher cipher = Cipher.getInstance(algoritam);
		cipher.init(Cipher.DECRYPT_MODE, secret);
		try(BufferedReader br = new BufferedReader(new FileReader(putanja))){
			byte[] rezultat = br.readLine().getBytes(charset);
			rezultat = Base64.getDecoder().decode(rezultat);
			lozinka = new String(cipher.doFinal(rezultat),charset);
		}
		
		return lozinka;
	}


}
