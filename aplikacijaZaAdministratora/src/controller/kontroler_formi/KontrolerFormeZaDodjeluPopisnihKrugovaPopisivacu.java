package controller.kontroler_formi;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.korisnicki_nalozi.KorisnikSistema;
import model.korisnicki_nalozi.Popisivac;
import model.pracenje_popisa.izvjestaji_o_popisivacu.PopisniKrug;
import test.Aplikacija;
import util.GradoviCollection;
import util.OpstineCollection;
import util.PromjenaPisma;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.stream.Collectors;

public class KontrolerFormeZaDodjeluPopisnihKrugovaPopisivacu implements Initializable {

    public static int index = 0;
    public static List<PopisniKrug> popisniKrugovi;

    public Button dodajUliceButton;
    @FXML
    ChoiceBox<String> GradChoiceBox;
    @FXML
    ChoiceBox<String> OpstinaChoiceBox;
    @FXML
    ImageView slikaPopisnogKruga;
    @FXML
    ChoiceBox<String> popisivaciChoiceBox;

    List<Popisivac> popisivaci;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        popisniKrugovi = KontrolerFormeZaDodavanjePopisnihKrugova.listaKrugova;
        slikaPopisnogKruga.setImage(new Image(new ByteArrayInputStream(popisniKrugovi.get(index).getSlikaBytes())));

        GradChoiceBox.getItems().addAll(Aplikacija.prevediRecenice(new ArrayList<>(GradoviCollection.getGradovi())));
        OpstinaChoiceBox.getItems().addAll(Aplikacija.prevediRecenice(new ArrayList<>(OpstineCollection.getOpstine())));

        GradChoiceBox.setValue(Aplikacija.prevediRecenicu("Banja Luka"));
        OpstinaChoiceBox.setValue(Aplikacija.prevediRecenicu("Banja Luka"));

        popisniKrugovi = ucitajListuPopisnihKrugovaSaServera();
        popisivaci = ucitajListuPopisivacaSaServera();

        popisivaciChoiceBox.getItems().addAll(popisivaci.stream()
                                                  .map(KorisnikSistema::getKorisnickoIme)
                                                  .collect(Collectors.toList()));
    }

    private List<Popisivac> ucitajListuPopisivacaSaServera() {
        //        PopisivacCMISKlijent popisivacCMISKlijent = new PopisivacCMISKlijent(KontrolerFormeZaPrijavu.getTrenutniKorisnik());
//        Response odgovor  = popisivacCMISKlijent.getListaPopisivaca();
//
//        if(Response.Status.Family.SUCCESSFUL.equals(odgovor.getStatusInfo().getFamily())) {
//            lista = odgovor.readEntity(new GenericType<LinkedList<Popisivac>>() {});
//        }else {
//            Aplikacija.connLogger.logHeaders(Level.SEVERE, odgovor);
//        }
        List<Popisivac> listaPopisivaca = Arrays.asList(
                new Popisivac("Marijana", "Zeljkovic", "marijana.zeljkovic", "maki"),
                new Popisivac("Kristijan", "Stepanov", "kristijan.stepanov", "kiki")
        );
        return listaPopisivaca;
    }

    private List<PopisniKrug> ucitajListuPopisnihKrugovaSaServera() {
        List<PopisniKrug> listaPopisnihKrugova = KontrolerFormeZaDodavanjePopisnihKrugova.listaKrugova;
        // TODO: Učitati sa servera listu popisnih krugova
        return listaPopisnihKrugova;
    }

    public void dodjeliPopisniKrug(ActionEvent actionEvent) {
        // TODO: Pošalji na server popisni krug.
        Popisivac popisivac = popisivaci.stream().filter(e->
                e.getKorisnickoIme().equals(PromjenaPisma.zamijeniCirilicuLatinicom(popisivaciChoiceBox.getValue()))).findFirst().get();
        PopisniKrug popisniKrug = popisniKrugovi.get(index);
    }

    public void povratak(ActionEvent actionEvent) {
        try {
            Aplikacija.getStage().setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/FormaZaRadClanaPKLS.fxml"))));
        } catch (IOException e) {
            Aplikacija.connLogger.getLogger().log(Level.WARNING, "NeuspjeÅ¡no Ä�itanje forme.");
        }
    }

    public void prethodniPopisniKrug(MouseEvent mouseEvent) {
            slikaPopisnogKruga.setImage(new Image(new ByteArrayInputStream(popisniKrugovi.get(index=((popisniKrugovi.size()+index-1)%popisniKrugovi.size())).getSlikaBytes())));
    }

    public void sljedeciPopisniKrug(MouseEvent mouseEvent) {
        slikaPopisnogKruga.setImage(new Image(new ByteArrayInputStream(popisniKrugovi.get(index=((index+1)%popisniKrugovi.size())).getSlikaBytes())));
    }

    public void uvecajSliku(MouseEvent mouseEvent) {
        if(mouseEvent.getClickCount()==2)
        {
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);

            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/view/FormaZaPrikazSlike.fxml"));
            } catch (IOException e) {
                Aplikacija.connLogger.getLogger().log(Level.WARNING, "Neuspješno čitanje forme.");
                return;
            }
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.showAndWait();
        }
    }
}
