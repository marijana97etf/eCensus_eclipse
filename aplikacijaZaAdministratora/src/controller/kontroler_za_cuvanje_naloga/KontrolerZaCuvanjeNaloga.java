package controller.kontroler_za_cuvanje_naloga;


import controller.kontroler_za_cuvanje_naloga.CuvanjeNaloga;
import model.korisnicki_nalozi.SkladisteNaloga;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class KontrolerZaCuvanjeNaloga
{
    private CuvanjeNaloga cuvanjeNaloga;

    private SkladisteNaloga skladisteNaloga;

    public CuvanjeNaloga getCuvanjeNaloga() {
        return cuvanjeNaloga;
    }

    public void setCuvanjeNaloga(CuvanjeNaloga cuvanjeNaloga) {
        this.cuvanjeNaloga = cuvanjeNaloga;
    }

    public SkladisteNaloga getSkladisteNaloga() {
        return skladisteNaloga;
    }

    public void setSkladisteNaloga(SkladisteNaloga skladisteNaloga) {
        this.skladisteNaloga = skladisteNaloga;
    }

    public KontrolerZaCuvanjeNaloga(CuvanjeNaloga cuvanjeNaloga)
    {
        this.cuvanjeNaloga = cuvanjeNaloga;
    }
    public boolean sacuvajNaloge(SkladisteNaloga skladisteNaloga) {
        try {
            return cuvanjeNaloga.sacuvajNaloge(skladisteNaloga);
        } catch (Exception e) {
            return false;
        }
    }
    public boolean ucitajNaloge() {
        SkladisteNaloga tmp = null;
        try {
            tmp = cuvanjeNaloga.ucitajSacuvaneNaloge();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        if(tmp != null) {
            skladisteNaloga = tmp;
            return true;
        }
        skladisteNaloga = new SkladisteNaloga();
        return false;
    }
}