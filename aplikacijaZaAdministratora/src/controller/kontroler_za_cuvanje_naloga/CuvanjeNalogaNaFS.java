package controller.kontroler_za_cuvanje_naloga;

import controller.kontroler_za_cuvanje_naloga.CuvanjeNaloga;
import javafx.util.Pair;
import model.korisnicki_nalozi.SkladisteNaloga;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import java.io.*;
import java.security.*;

import static javax.crypto.Cipher.*;

public class CuvanjeNalogaNaFS implements CuvanjeNaloga {

    private static SecretKey key;
    private static byte[] iv;

    @Override
    public boolean sacuvajNaloge(SkladisteNaloga skladisteNaloga) {

        try
        {
            try
            {
                readKey();
            }
            catch (Exception e)
            {
                KeyGenerator generator = KeyGenerator.getInstance("AES");
                SecretKey secretKey = generator.generateKey();
                key = secretKey;

                // generate IV
                SecureRandom random = new SecureRandom();
                iv = new byte[16];
                random.nextBytes(iv);

                saveKey();
            }

            // create cipher
            Cipher cipher = getInstance(key.getAlgorithm() + "/CBC/PKCS5Padding");
            cipher.init(ENCRYPT_MODE, key , new IvParameterSpec(iv));

            // create sealed object
            SealedObject sealedEm1 = new SealedObject(skladisteNaloga, cipher);

            // Create stream
            FileOutputStream fos = new FileOutputStream("src/files/accounts.aes");
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            CipherOutputStream cos = new CipherOutputStream(bos, cipher);
            ObjectOutputStream oos = new ObjectOutputStream(cos);
            oos.writeObject(sealedEm1);
            oos.close();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public SkladisteNaloga ucitajSacuvaneNaloge() {

        try
        {
            readKey();

            Cipher cipher = getInstance(key.getAlgorithm() + "/CBC/PKCS5Padding");

            // turn the mode of cipher to decryption
            cipher.init( Cipher.DECRYPT_MODE, key, new IvParameterSpec( iv ) ); // reuse the key and iv generated before

            // create stream
            CipherInputStream cipherInputStream = new CipherInputStream( new BufferedInputStream( new FileInputStream( "src/files/accounts.aes" ) ), cipher );
            ObjectInputStream inputStream = new ObjectInputStream( cipherInputStream );
            SealedObject sealedObject = (SealedObject) inputStream.readObject();
            SkladisteNaloga sn = (SkladisteNaloga) sealedObject.getObject(cipher);
            if(sn==null)
                return null;
            return sn;
        }
        catch (Exception e)
        {
            return null;
        }
    }


    private static void saveKey() throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/files/key.dat"));
        Pair<SecretKey, byte[]>  pair = new Pair<>(key, iv);
        oos.writeObject(pair);
        oos.flush();
        oos.close();
    }

    private static void readKey() throws IOException, ClassNotFoundException {
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/files/key.dat")))
        {
            var pair = (Pair<SecretKey, byte[]>) ois.readObject();
            key = pair.getKey();
            iv = pair.getValue();
        }
    }
}
