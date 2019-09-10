package controller.kontroler_formi;

import javafx.collections.FXCollections;
import model.korisnicki_nalozi.DEInstruktor;
import model.table_input_models.KorisnikInputModel;
import test.Pokreni_GUI_Aplikaciju;

import java.util.stream.Collectors;

public class KontrolerFormeZaPregledOGInstruktora extends KontrolerFormeZaPregledNaloga {
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
