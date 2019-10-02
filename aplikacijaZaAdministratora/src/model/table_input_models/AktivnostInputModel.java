package model.table_input_models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;
import model.pracenje_popisa.izvjestaji_o_popisivacu.DnevnaAktivnost;
import test.Aplikacija;

public class AktivnostInputModel {
    protected Integer id;
    protected SimpleStringProperty datum;
    protected SimpleIntegerProperty brojPopisanihStanovnika;
    protected SimpleIntegerProperty brojPopisanihDomacinstava;

    public AktivnostInputModel(DnevnaAktivnost aktivnost) {
        id=1;
        datum = new SimpleStringProperty(aktivnost.getDan().toString());
        brojPopisanihStanovnika = new SimpleIntegerProperty(aktivnost.getBrojPopisanihStanovnika());
        brojPopisanihDomacinstava = new SimpleIntegerProperty(aktivnost.getBrojPopisanihDomacinstava());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDatum() {
        return datum.get();
    }

    public SimpleStringProperty datumProperty() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum.set(datum);
    }

    public int getBrojPopisanihStanovnika() {
        return brojPopisanihStanovnika.get();
    }

    public SimpleIntegerProperty brojPopisanihStanovnikaProperty() {
        return brojPopisanihStanovnika;
    }

    public void setBrojPopisanihStanovnika(int brojPopisanihStanovnika) {
        this.brojPopisanihStanovnika.set(brojPopisanihStanovnika);
    }

    public int getBrojPopisanihDomacinstava() {
        return brojPopisanihDomacinstava.get();
    }

    public SimpleIntegerProperty brojPopisanihDomacinstavaProperty() {
        return brojPopisanihDomacinstava;
    }

    public void setBrojPopisanihDomacinstava(int brojPopisanihDomacinstava) {
        this.brojPopisanihDomacinstava.set(brojPopisanihDomacinstava);
    }
}
