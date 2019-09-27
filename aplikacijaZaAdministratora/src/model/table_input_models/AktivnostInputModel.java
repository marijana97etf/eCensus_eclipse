package model.table_input_models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;
import model.pracenje_popisa.izvjestaji_o_popisivacu.DnevnaAktivnost;

public class AktivnostInputModel {
    protected Integer id;
    protected SimpleStringProperty datum;
    protected SimpleObjectProperty<Button> popisniKrugovi;
    protected SimpleIntegerProperty broj;
    protected DnevnaAktivnost aktivnost;


    public AktivnostInputModel(DnevnaAktivnost aktivnost) {
        id=1;
        datum = new SimpleStringProperty(aktivnost.getDan().toString());
        Button b = new Button();
        b.setText("Pregledaj popisne krugove");
        popisniKrugovi = new SimpleObjectProperty<>(b);
        this.aktivnost = aktivnost;
    }

    public DnevnaAktivnost getAktivnost() {
        return aktivnost;
    }

    public void setAktivnost(DnevnaAktivnost aktivnost) {
        this.aktivnost = aktivnost;
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

    public SimpleIntegerProperty brojKrugovaProperty() {
        /*if(aktivnost.getObidjeniPopisniKrugovi()!=null)
            return  new SimpleIntegerProperty(aktivnost.getObidjeniPopisniKrugovi().size());
        else
            return new SimpleIntegerProperty(0);*/
    	return new SimpleIntegerProperty(0);
    }

    public void setDatum(String datum) {
        this.datum.set(datum);
    }

    public Button getPopisniKrugovi() {
        return popisniKrugovi.get();
    }

    public SimpleObjectProperty<Button> popisniKrugoviProperty() {
        return popisniKrugovi;
    }

    public void setObidjeniPopisniKrugovi(Button obidjeniPopisniKrugovi) {
        this.popisniKrugovi.set(obidjeniPopisniKrugovi);
    }

    public int getBroj() {
        return broj.get();
    }

    public SimpleIntegerProperty brojProperty() {
        return broj;
    }

    public void setBroj(int broj) {
        this.broj.set(broj);
    }
}
