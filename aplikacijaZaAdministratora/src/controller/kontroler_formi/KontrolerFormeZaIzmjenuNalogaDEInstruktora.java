package controller.kontroler_formi;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import model.korisnicki_nalozi.DEInstruktor;
import model.table_input_models.KorisnikInputModel;
import test.Pokreni_GUI_Aplikaciju;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class KontrolerFormeZaIzmjenuNalogaDEInstruktora implements Initializable {

    @FXML
    TextField imeIzmjena;

    @FXML
    TextField prezimeIzmjena;

    @FXML
    TextField usernameIzmjena;

    @FXML
    ChoiceBox choiceBox;

    public void izmjeni(ActionEvent actionEvent) {

        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText("Da li Å¾elite da saÄ�uvate izmjene naloga drÅ¾avnog/entitetskog instruktora?");
        ButtonType buttonType = alert.showAndWait().get();
        if(!buttonType.getText().equals("OK")) return;
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/view/FormaZaPregledNalogaDEInstruktora.fxml"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Pokreni_GUI_Aplikaciju.getStage().setScene(new Scene(root));
        var account = KontrolerFormeZaPregledClanovaPKLS.getAccountForEdit();
        account.setPrezime(prezimeIzmjena.getText());
        account.setIme(imeIzmjena.getText());
        account.setKorisnickoIme(usernameIzmjena.getText());
        ((DEInstruktor)account.getKorisnikSistema()).setEntitet(DEInstruktor.stringToEntitet((String) choiceBox.getValue()));
        account.updateKorisnikSistema();
    }

    public void back(ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText("Da li Å¾elite da napustite izmjenu naloga drÅ¾avnog/entitetskog instruktora?");
        ButtonType buttonType = alert.showAndWait().get();
        if(!buttonType.getText().equals("OK")) return;
        Pokreni_GUI_Aplikaciju.getStage().setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/FormaZaPregledNalogaDEInstruktora.fxml"))));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        KorisnikInputModel account = KontrolerFormeZaPregledClanovaPKLS.getAccountForEdit();
        imeIzmjena.setText(account.getIme());
        prezimeIzmjena.setText(account.getPrezime());
        usernameIzmjena.setText(account.getKorisnickoIme());
        choiceBox.getItems().addAll("Bosna i Hercegovina", "Federacija Bosne i Hercegovine", "Republika Srpska");
        choiceBox.setValue(((DEInstruktor)account.getKorisnikSistema()).entitetToString());
    }
}
