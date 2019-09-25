package model.korisnicki_nalozi;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import java.util.Base64;


public class KorisnikSistema implements Serializable {
	
	protected long id;
	protected String ime;
	protected String prezime;
	protected String korisnickoIme;
	protected String lozinkaHash;

	protected String trustStore, keyStore;
	protected String trustLozinka, keyLozinka;

	// to be changed
	{
		trustLozinka = keyLozinka = "sigurnost";
	}

	public KorisnikSistema() {
		
	}
	
	public KorisnikSistema(String ime, String prezime, String korisnickoIme, String lozinkaHash) {
		this.ime = ime;
		this.prezime = prezime;
		this.korisnickoIme = korisnickoIme;
		this.lozinkaHash = lozinkaHash;
	}

	public KorisnikSistema(long id, String ime, String prezime, String korisnickoIme, String lozinkaHash,
			String trustStore, String trustLozinka, String keyStore, String keyLozinka) {
		this(ime, prezime, korisnickoIme, lozinkaHash);
		this.id = id;
		this.trustStore = trustStore;
		this.keyStore = keyStore;
		this.trustLozinka = trustLozinka;
		this.keyLozinka = keyLozinka;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((ime == null) ? 0 : ime.hashCode());
		result = prime * result + ((korisnickoIme == null) ? 0 : korisnickoIme.hashCode());
		result = prime * result + ((lozinkaHash == null) ? 0 : lozinkaHash.hashCode());
		result = prime * result + ((prezime == null) ? 0 : prezime.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof KorisnikSistema))
			return false;
		KorisnikSistema other = (KorisnikSistema) obj;
		if (ime == null) {
			if (other.ime != null)
				return false;
		} else if (!ime.equals(other.ime))
			return false;
		if (korisnickoIme == null) {
			if (other.korisnickoIme != null)
				return false;
		} else if (!korisnickoIme.equals(other.korisnickoIme))
			return false;
		if (lozinkaHash == null) {
			if (other.lozinkaHash != null)
				return false;
		} else if (!lozinkaHash.equals(other.lozinkaHash))
			return false;
		if (prezime == null) {
			if (other.prezime != null)
				return false;
		} else if (!prezime.equals(other.prezime))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "KorisnikSistema [id=" + id + ", ime=" + ime + ", prezime=" + prezime
				+ ", korisnickoIme=" + korisnickoIme + ", lozinkaHash=" + lozinkaHash + "]";
	}

	public static String napraviHesLozinke(String lozinka) {
		try {
			return new String(Base64.getEncoder().encode(MessageDigest.getInstance("MD5").digest(lozinka.getBytes(StandardCharsets.UTF_8))));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		} 
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getKorisnickoIme() {
		return korisnickoIme;
	}

	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}

	public String getLozinkaHash() {
		return lozinkaHash;
	}

	public void setLozinkaHash(String lozinkaHash) {
		this.lozinkaHash = lozinkaHash;
	}

	public void setTrustLozinka(String trustLozinka) {
		this.trustLozinka = trustLozinka;
	}

	public void setKeyLozinka(String keyLozinka) {
		this.keyLozinka = keyLozinka;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTrustStore() {
		return trustStore;
	}

	public void setTrustStore(String trustStore) {
		this.trustStore = trustStore;
	}

	public String getKeyStore() {
		return keyStore;
	}

	public void setKeyStore(String keyStore) {
		this.keyStore = keyStore;
	}

	public String getTrustLozinka() {
		return trustLozinka;
	}

	public String getKeyLozinka() {
		return keyLozinka;
	}
}