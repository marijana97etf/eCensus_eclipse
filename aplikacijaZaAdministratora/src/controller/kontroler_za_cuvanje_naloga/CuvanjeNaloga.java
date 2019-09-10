package controller.kontroler_za_cuvanje_naloga;

import model.korisnicki_nalozi.SkladisteNaloga;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public interface CuvanjeNaloga {
    boolean sacuvajNaloge(SkladisteNaloga skladisteNaloga) throws NoSuchAlgorithmException, IOException, NoSuchPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException, InvalidKeyException;
    SkladisteNaloga ucitajSacuvaneNaloge() throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, IOException, ClassNotFoundException, BadPaddingException, IllegalBlockSizeException;
}
