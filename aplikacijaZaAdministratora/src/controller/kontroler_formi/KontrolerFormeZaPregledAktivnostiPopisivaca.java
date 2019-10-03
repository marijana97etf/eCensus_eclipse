package controller.kontroler_formi;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.korisnicki_nalozi.ClanPKLS;
import model.korisnicki_nalozi.DEInstruktor;
import model.korisnicki_nalozi.KorisnikSistema;
import model.korisnicki_nalozi.OGInstruktor;
import model.korisnicki_nalozi.Popisivac;
import model.pracenje_popisa.izvjestaji_o_popisivacu.DnevnaAktivnost;
import model.table_input_models.AktivnostInputModel;
import test.Aplikacija;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;
import java.util.logging.Level;
import java.util.stream.Collectors;

import eCensus.rest.client.PopisivacCMISKlijent;

public class KontrolerFormeZaPregledAktivnostiPopisivaca implements Initializable {

    public TableView<AktivnostInputModel> tabela;
    public TableColumn<?,?> redniBrojColumn;
    public TableColumn<?,?> datumColumn;
    public TableColumn<?,?> brojPopisanihStanovnika;
    public TableColumn<?,?> brojPopisanihDomacinstava;
    public ChoiceBox<String> popisivaciChoiceBox;
    public Label trenutnaOcjenaLabel;
    public Button buttonOcjeni;

    public List<Popisivac> listaPopisivaca;
    public static Popisivac popisivacStatic;
    public static Stage stageStatic;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listaPopisivaca = getListaPopisivacaFromServer();
        Platform.runLater(() -> popisivaciChoiceBox.setItems(FXCollections.observableList(listaPopisivaca.stream().map(KorisnikSistema::getKorisnickoIme).collect(Collectors.toList()))));
        tabela.setDisable(true);
        buttonOcjeni.setVisible(false);
        redniBrojColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        datumColumn.setCellValueFactory(new PropertyValueFactory<>("datum"));
        brojPopisanihStanovnika.setCellValueFactory(new PropertyValueFactory<>("brojPopisanihStanovnika"));
        brojPopisanihDomacinstava.setCellValueFactory(new PropertyValueFactory<>("brojPopisanihDomacinstava"));
        popisivaciChoiceBox.setOnAction(e->
        {
            tabela.setDisable(false);
            if(KontrolerFormeZaPrijavu.getTrenutniKorisnik() instanceof OGInstruktor)
            	buttonOcjeni.setVisible(true);
            Platform.runLater(() -> tabela.setItems(FXCollections.observableList(getListaAktivnostiFromServer(popisivaciChoiceBox.getValue()))));
            
            Popisivac popisivac = listaPopisivaca.stream().filter(elem-> elem.getKorisnickoIme().equals(popisivaciChoiceBox.getValue())).findFirst().get();
            popisivacStatic = popisivac;
            PopisivacCMISKlijent popisivacCMISKlijent = new PopisivacCMISKlijent(KontrolerFormeZaPrijavu.getTrenutniKorisnik());
            int ocjena = popisivacCMISKlijent.getOcjena((int) popisivacStatic.getId()).readEntity(Integer.class);
            trenutnaOcjenaLabel.setText("Trenutna ocjena: " + ocjena);
        });

    }

    private List<Popisivac> getListaPopisivacaFromServer() {
        PopisivacCMISKlijent popisivacCMISKlijent = new PopisivacCMISKlijent(KontrolerFormeZaPrijavu.getTrenutniKorisnik());
        List<Popisivac> listaPopisivaca = Arrays.asList(popisivacCMISKlijent.getListaPopisivaca().readEntity(Popisivac[].class));
        return listaPopisivaca;
    }

    private List<AktivnostInputModel> getListaAktivnostiFromServer(String popisivac) {
    	
    	PopisivacCMISKlijent popisivacCMISKlijent = new PopisivacCMISKlijent(KontrolerFormeZaPrijavu.getTrenutniKorisnik());
    	
        int idPopisivaca = (int) listaPopisivaca.stream().filter(e -> e.getKorisnickoIme().equals(popisivac)).findFirst().get().getId();
        
        List<DnevnaAktivnost> listaAktivnosti = Arrays.asList(popisivacCMISKlijent.getListaAktivnostiPopisivaca(idPopisivaca).readEntity(DnevnaAktivnost[].class));

        return listaAktivnosti.stream().map(AktivnostInputModel::new).collect(Collectors.toList());
    }

    public void povratak(ActionEvent actionEvent) {
        String retPath;
        if (KontrolerFormeZaPrijavu.getTrenutniKorisnik() instanceof OGInstruktor) {
            retPath = "/view/FormaZaRadOGInstruktora.fxml";
        } else if (KontrolerFormeZaPrijavu.getTrenutniKorisnik() instanceof DEInstruktor) {
            retPath = "/view/FormaZaRadDEInstruktora.fxml";
        } else if (KontrolerFormeZaPrijavu.getTrenutniKorisnik() instanceof ClanPKLS){
            retPath = "/view/FormaZaRadClanaPKLS.fxml";
        } else {
            retPath = "/view/FormaZaRadAdministratoraAgencije.fxml";
        }
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(retPath));
        } catch (IOException e) {
            Aplikacija.connLogger.getLogger().log(Level.WARNING, "NeuspjeÅ¡no Ä�itanje forme.");
        }
        Aplikacija.getStage().setScene(new Scene(root));
    }


    public void ocijeniPopisivaca(ActionEvent actionEvent)
    {
        Stage stage = new Stage();
        stageStatic = stage;
        stage.initModality(Modality.APPLICATION_MODAL);

        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/view/FormaZaOcjenuPopisivaca.fxml"));
        } catch (IOException e) {
            Aplikacija.connLogger.getLogger().log(Level.WARNING, "Neuspješno čitanje forme.");
            return;
        }
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.showAndWait();

        PopisivacCMISKlijent popisivacCMISKlijent = new PopisivacCMISKlijent(KontrolerFormeZaPrijavu.getTrenutniKorisnik());
        int ocjena = popisivacCMISKlijent.getOcjena((int) popisivacStatic.getId()).readEntity(Integer.class);
        trenutnaOcjenaLabel.setText("Trenutna ocjena: " + ocjena);
        
    }
}
