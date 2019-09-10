package controller.kontroler_formi;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import model.korisnicki_nalozi.DEInstruktor;
import model.korisnicki_nalozi.KorisnikSistema;
import model.table_input_models.DEInstruktorInputModel;
import model.table_input_models.KorisnikInputModel;
import test.Pokreni_GUI_Aplikaciju;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class KontrolerFormeZaPregledDEInstruktora extends KontrolerFormeZaPregledNaloga {


    @FXML
    Label labelaZaIme;


    @Override
    public void initializeList() {
        lista = FXCollections.observableArrayList(Pokreni_GUI_Aplikaciju
                .getKontrolerZaCuvanjeNaloga()
                .getSkladisteNaloga()
                .stream()
                .filter(e -> e instanceof DEInstruktor)
                .map(KorisnikInputModel::new)
                .collect(Collectors.toList()));
    }
}
