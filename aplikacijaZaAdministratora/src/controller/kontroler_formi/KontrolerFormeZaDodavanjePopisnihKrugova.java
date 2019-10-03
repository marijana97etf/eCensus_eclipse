package controller.kontroler_formi;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.korisnicki_nalozi.KorisnikSistema;
import model.korisnicki_nalozi.Popisivac;
import model.pracenje_popisa.izvjestaji_o_popisivacu.PopisniKrug;
import test.Aplikacija;
import util.GradoviCollection;
import util.OpstineCollection;
import util.PromjenaPisma;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.logging.Level;
import java.util.stream.Collectors;

import eCensus.rest.client.ClanPKLSCMISKlijent;

public class KontrolerFormeZaDodavanjePopisnihKrugova implements Initializable {

    //public static List<PopisniKrug> listaKrugova = new ArrayList<>();
    public static Stage newStage;
    public Button dodajUliceButton;

    List<Popisivac> lista = new LinkedList<>();

    @FXML
    ChoiceBox<String> GradChoiceBox;
    @FXML
    ChoiceBox<String> OpstinaChoiceBox;
    @FXML
    TextField pathText;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        GradChoiceBox.getItems().addAll(Aplikacija.prevediRecenice(new ArrayList<>(GradoviCollection.getGradovi())));
        OpstinaChoiceBox.getItems().addAll(Aplikacija.prevediRecenice(new ArrayList<>(OpstineCollection.getListaOpstina())));

        GradChoiceBox.setValue(Aplikacija.prevediRecenicu("Banja Luka"));
        OpstinaChoiceBox.setValue(Aplikacija.prevediRecenicu("Banja Luka"));

        dodajUliceButton.setOnAction(e->
        {
            Stage stage = new Stage();
            newStage = stage;
            stage.initModality(Modality.APPLICATION_MODAL);

            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/view/FormaZaUcitavanjeUlica.fxml"));
            } catch (IOException ex) {
                Aplikacija.connLogger.getLogger().log(Level.WARNING, "Neuspješno čitanje forme.");
                return;
            }
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.showAndWait();
        });
    }

    public void pronadjiPutanju(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(Aplikacija.prevediRecenicu("Pronađi sliku!"));
        File file = fileChooser.showOpenDialog(Aplikacija.getStage());
        Platform.runLater(()->
        {
            if(file!=null) pathText.setText(file.getPath());
        });
    }

    public void dodajPopisniKrug(ActionEvent actionEvent) {
        String grad = PromjenaPisma.zamijeniCirilicuLatinicom(GradChoiceBox.getValue());
        String opstina = PromjenaPisma.zamijeniCirilicuLatinicom(OpstinaChoiceBox.getValue());
        int idOpstine = Integer.parseInt(OpstineCollection.getOpstine().get(opstina));
        String putanjaDoSlike = pathText.getText();
        byte[] slikaUBajtovima=null;
        try {
            slikaUBajtovima = Files.readAllBytes(Paths.get(putanjaDoSlike));
        } catch (IOException e) {
            Aplikacija.connLogger.getLogger().log(Level.WARNING, "Nepostojeća slika");
        }
        List<String> listaUlica = KontrolerFormeZaUcitavanjeUlica.listaUlica;
        if(slikaUBajtovima==null)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            var uspjesnaPrijavaAdminAgencije=Aplikacija.prevediRecenicu("Niste učitali sliku!");
            alert.setContentText(uspjesnaPrijavaAdminAgencije);
            ButtonType buttonType = alert.showAndWait().get();
            return;
        }
        if(listaUlica==null || listaUlica.size()==0)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            var uspjesnaPrijavaAdminAgencije=Aplikacija.prevediRecenicu("Niste učitali ulice!");
            alert.setContentText(uspjesnaPrijavaAdminAgencije);
            alert.showAndWait();
            return;
        }
        PopisniKrug popisniKrug = new PopisniKrug(idOpstine, grad, listaUlica, slikaUBajtovima);

        ClanPKLSCMISKlijent clanPKLSCMISKlijent = new ClanPKLSCMISKlijent(KontrolerFormeZaPrijavu.getTrenutniKorisnik());
        List<PopisniKrug> listaPopisnihKrugova = Arrays.asList(clanPKLSCMISKlijent.getListaPopisnihKrugova(grad, idOpstine).readEntity(PopisniKrug[].class));
        
        if(listaPopisnihKrugova.contains(popisniKrug)) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            var uspjesnaPrijavaAdminAgencije=Aplikacija.prevediRecenicu("Već postoji ovakav popisni krug!");
            alert.setContentText(uspjesnaPrijavaAdminAgencije);
            alert.showAndWait();
            return;
        }
        
        clanPKLSCMISKlijent.dodajPopisniKrug(popisniKrug);

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        var uspjesnaPrijavaAdminAgencije=Aplikacija.prevediRecenicu("Uspješno ste se dodali popisni krug!");
        alert.setContentText(uspjesnaPrijavaAdminAgencije);
        alert.showAndWait();

    }

    public void povratak(ActionEvent actionEvent) {
        try {
            Aplikacija.getStage().setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/FormaZaRadClanaPKLS.fxml"))));
        } catch (IOException e) {
            Aplikacija.connLogger.getLogger().log(Level.WARNING, "Neuspješno čitanje forme.");
        }
    }
    public void skoci(ActionEvent actionEvent) {
        try {
            Aplikacija.getStage().setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/FormaZaDodjeluPopisnihKrugovaPopisivacu.fxml"))));
        } catch (IOException e) {
            Aplikacija.connLogger.getLogger().log(Level.WARNING, "Neuspješno čitanje forme.");
        }
    }
}
