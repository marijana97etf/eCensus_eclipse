package model.table_input_models;

import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Button;
import model.korisnicki_nalozi.OGInstruktor;

public class OGInstruktorInputModel extends KorisnikInputModel {

    public OGInstruktorInputModel(OGInstruktor korisnikSistema)
    {
        super(korisnikSistema);
        var brisanjeButton = new SimpleObjectProperty<>(new Button("Obriši"));
        var izmjenaButton = new SimpleObjectProperty<>(new Button("Izmjeni"));
        Buttoni = new SimpleObjectProperty[] { izmjenaButton, brisanjeButton };
    }
}
