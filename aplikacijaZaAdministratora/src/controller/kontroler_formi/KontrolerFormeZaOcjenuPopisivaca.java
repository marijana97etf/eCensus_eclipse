package controller.kontroler_formi;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import model.korisnicki_nalozi.Popisivac;
import test.Aplikacija;

import java.net.URL;
import java.util.ResourceBundle;

import eCensus.rest.client.OGInstruktorCMISKLijent;
import eCensus.rest.client.PopisivacCMISKlijent;

public class KontrolerFormeZaOcjenuPopisivaca implements Initializable {
    @FXML
    protected ChoiceBox<String> ocjena;
    @FXML
    protected Label username;

    public void ocijeni(ActionEvent actionEvent)
    {
        Popisivac popisivac = KontrolerFormeZaPregledAktivnostiPopisivaca.popisivacStatic;
        int ocjenaNum = ocjena.getItems().indexOf(ocjena.getValue()) + 1;
        
        OGInstruktorCMISKLijent ogInstruktorCMISKlijent = new OGInstruktorCMISKLijent(KontrolerFormeZaPrijavu.getTrenutniKorisnik());
        ogInstruktorCMISKlijent.azurirajOcjenuPopisivaca((int) KontrolerFormeZaPrijavu.getTrenutniKorisnik().getId(), (int) popisivac.getId(), ocjenaNum);
        
        Alert poruka = new Alert(Alert.AlertType.INFORMATION);
        poruka.setContentText(Aplikacija.prevediRecenicu("Uspješno ste ocjenili popisivača."));
        ButtonType tip = poruka.showAndWait().get();
        KontrolerFormeZaPregledAktivnostiPopisivaca.stageStatic.close();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    	Popisivac popisivac = KontrolerFormeZaPregledAktivnostiPopisivaca.popisivacStatic;
    	username.setText(popisivac.getKorisnickoIme());
        ocjena.getItems().clear();
        ocjena.getItems().addAll(
                Aplikacija.prevediRecenicu("Nedovoljan"),
                Aplikacija.prevediRecenicu("Dovoljan"),
                Aplikacija.prevediRecenicu("Dobar"),
                Aplikacija.prevediRecenicu("Vrlo dobar"),
                Aplikacija.prevediRecenicu("Odličan")
        );
        ocjena.setValue(Aplikacija.prevediRecenicu("Dobar"));
    }
}
