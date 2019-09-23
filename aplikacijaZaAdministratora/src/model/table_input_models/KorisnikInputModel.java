package model.table_input_models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import model.korisnicki_nalozi.KorisnikSistema;
import model.korisnicki_nalozi.Popisivac;

public class KorisnikInputModel {
    protected Integer id;
    protected SimpleStringProperty prezime;
    protected SimpleStringProperty ime;
    protected SimpleStringProperty korisnickoIme;
    protected SimpleObjectProperty<Button>[] Buttoni;
    protected KorisnikSistema korisnikSistema;

    @SuppressWarnings("unchecked")
	public KorisnikInputModel(KorisnikSistema korisnikSistema)
    {
        id = 0;
        prezime = new SimpleStringProperty(korisnikSistema.getPrezime());
        ime = new SimpleStringProperty(korisnikSistema.getIme());
        korisnickoIme = new SimpleStringProperty(korisnikSistema.getKorisnickoIme());
        if(korisnikSistema instanceof Popisivac)
        {
        	var pregledajAktivnosti = new SimpleObjectProperty<>(new Button("Pregled aktivnosti"));
        	Buttoni = new SimpleObjectProperty[] { pregledajAktivnosti };
        }
        else 
        {
        	var brisanjeButton = new SimpleObjectProperty<>(new Button("Obriši"));
        	var izmjenaButton = new SimpleObjectProperty<>(new Button("Izmjeni"));
        	Buttoni = new SimpleObjectProperty[] { izmjenaButton, brisanjeButton };
        }
        this.korisnikSistema = korisnikSistema;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPrezime() {
        return prezime.get();
    }

    public SimpleStringProperty prezimeProperty() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime.set(prezime);
    }

    public String getIme() {
        return ime.get();
    }

    public SimpleStringProperty imeProperty() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime.set(ime);
    }

    public String getKorisnickoIme() {
        return korisnickoIme.get();
    }

    public SimpleStringProperty korisnickoImeProperty() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme.set(korisnickoIme);
    }

    public Button[] getButtons() {
        List<Button> list = new ArrayList<Button>();
        for(SimpleObjectProperty<Button> sop : Buttoni)
        {
        	list.add(sop.get());
        }
        return list.toArray(new Button[list.size()]);
    }


    public SimpleObjectProperty<HBox> ButtonsProperty() {
        HBox hBox = new HBox(getButtons());
        hBox.setAlignment(Pos.CENTER);
        return new SimpleObjectProperty<>(hBox);
    }

    public KorisnikSistema getKorisnikSistema() {
        return korisnikSistema;
    }

    public void setKorisnikSistema(KorisnikSistema korisnikSistema) {
        this.korisnikSistema = korisnikSistema;
    }

    public void updateKorisnikSistema()
    {
        korisnikSistema.setIme(ime.get());
        korisnikSistema.setPrezime(prezime.get());
        korisnikSistema.setKorisnickoIme(korisnickoIme.get());
    }
}
