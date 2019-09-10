package model.table_input_models;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import model.korisnicki_nalozi.KorisnikSistema;

public class KorisnikInputModel {
    protected Integer id;
    protected SimpleStringProperty prezime;
    protected SimpleStringProperty ime;
    protected SimpleStringProperty korisnickoIme;
    protected SimpleObjectProperty<Button> izmjenaButton;
    protected SimpleObjectProperty<Button> brisanjeButton;
    protected KorisnikSistema korisnikSistema;

    public KorisnikInputModel(KorisnikSistema korisnikSistema)
    {
        id = 0;
        prezime = new SimpleStringProperty(korisnikSistema.getPrezime());
        ime = new SimpleStringProperty(korisnikSistema.getIme());
        korisnickoIme = new SimpleStringProperty(korisnikSistema.getKorisnickoIme());
        izmjenaButton = new SimpleObjectProperty<>(new Button("Izmjeni"));
        brisanjeButton = new SimpleObjectProperty<>(new Button("Obriši"));
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

    public Button getIzmjenaButton() {
        return izmjenaButton.get();
    }

    public SimpleObjectProperty<Button> izmjenaButtonProperty() {
        return izmjenaButton;
    }

    public void setIzmjenaButton(Button izmjenaButton) {
        this.izmjenaButton.set(izmjenaButton);
    }

    public Button getBrisanjeButton() {
        return brisanjeButton.get();
    }

    public SimpleObjectProperty<Button> brisanjeButtonProperty() {
        return brisanjeButton;
    }

    public void setBrisanjeButton(Button brisanjeButton) {
        this.brisanjeButton.set(brisanjeButton);
    }

    public SimpleObjectProperty<HBox> obaButtonaProperty() {
        HBox hBox = new HBox(izmjenaButton.get(), brisanjeButton.get());
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
