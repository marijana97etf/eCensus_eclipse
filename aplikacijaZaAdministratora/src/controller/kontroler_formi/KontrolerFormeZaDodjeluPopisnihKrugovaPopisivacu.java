package controller.kontroler_formi;

import eCensus.rest.client.PopisivacCMISKlijent;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import model.korisnicki_nalozi.KorisnikSistema;
import model.korisnicki_nalozi.Popisivac;
import model.pracenje_popisa.izvjestaji_o_popisivacu.PopisniKrug;
import test.Aplikacija;
import util.GradoviCollection;
import util.OpstineCollection;
import util.PromjenaPisma;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.stream.Collectors;

public class KontrolerFormeZaDodjeluPopisnihKrugovaPopisivacu implements Initializable {

    List<Popisivac> lista = new LinkedList<>();

    @FXML
    ChoiceBox<String> PopisivacChoiceBox;
    @FXML
    ChoiceBox<String> GradChoiceBox;
    @FXML
    ChoiceBox<String> OpstinaChoiceBox;
    @FXML
    TextField pathText;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        GradChoiceBox.getItems().addAll(Aplikacija.prevediRecenice(new ArrayList<>(GradoviCollection.getGradovi())));
        OpstinaChoiceBox.getItems().addAll(Aplikacija.prevediRecenice(new ArrayList<>(OpstineCollection.getOpstine())));

        GradChoiceBox.setValue(Aplikacija.prevediRecenicu("Banja Luka"));
        OpstinaChoiceBox.setValue(Aplikacija.prevediRecenicu("Banja Luka"));

        PopisivacCMISKlijent popisivacCMISKlijent = new PopisivacCMISKlijent(KontrolerFormeZaPrijavu.getTrenutniKorisnik());
        Response odgovor  = popisivacCMISKlijent.getListaPopisivaca();

        if(Response.Status.Family.SUCCESSFUL.equals(odgovor.getStatusInfo().getFamily())) {
            lista = odgovor.readEntity(new GenericType<LinkedList<Popisivac>>() {});
        }else {
            Aplikacija.connLogger.logHeaders(Level.SEVERE, odgovor);
        }

        PopisivacChoiceBox.getItems().addAll(lista.stream()
                                                  .map(KorisnikSistema::getKorisnickoIme)
                                                  .collect(Collectors.toList()));
    }

    public void pronadjiPutanju(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(Aplikacija.prevediRecenicu("Pronađi sliku!"));
        File file = fileChooser.showOpenDialog(Aplikacija.getStage());
        Platform.runLater(()-> pathText.setText(file.getPath()));
    }

    public void dodajPopisniKrug(ActionEvent actionEvent) {
        String username = PopisivacChoiceBox.getValue();
        String grad = PromjenaPisma.zamijeniCirilicuLatinicom(GradChoiceBox.getValue());
        //String opstina = PromjenaPisma.zamijeniCirilicuLatinicom(OpstinaChoiceBox.getValue());
        int idOpstine = 0; // Promjeniti
        String putanjaDoSlike = pathText.getText();
        byte[] slikaUBajtovima=null;
        try {
            slikaUBajtovima = Files.readAllBytes(Paths.get(putanjaDoSlike));
        } catch (IOException e) {
            Aplikacija.connLogger.getLogger().log(Level.WARNING, "Nepostojeća slika");
        }
        if(slikaUBajtovima==null)
        {
            return;
        }
        PopisniKrug popisniKrug = new PopisniKrug(idOpstine, grad, null, slikaUBajtovima);

        Popisivac popisivac = lista.stream().filter(e -> e.getKorisnickoIme().equals(username)).findFirst().get();
        popisivac.dodajPopisniKrug(popisniKrug);
    }

    public void povratak(ActionEvent actionEvent) {
        try {
            Aplikacija.getStage().setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/FormaZaRadClanaPKLS.fxml"))));
        } catch (IOException e) {
            Aplikacija.connLogger.getLogger().log(Level.WARNING, "NeuspjeÅ¡no Ä�itanje forme.");
        }
    }
}
