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
            buttonOcjeni.setVisible(true);
            Platform.runLater(() -> tabela.setItems(FXCollections.observableList(getListaAktivnostiFromServer(popisivaciChoiceBox.getValue()))));
            // TODO: dodaj trenutnu ocjenu
            Popisivac popisivac = listaPopisivaca.stream().filter(elem-> elem.getKorisnickoIme().equals(popisivaciChoiceBox.getValue())).findFirst().get();
            popisivacStatic = popisivac;
            //trenutnaOcjenaLabel.setText("Trenutna ocjena: "+ ....);
        });

    }

    private List<Popisivac> getListaPopisivacaFromServer() {
        // TODO: Učitati listu popisivača sa servera
        List<Popisivac> listaPopisivaca = Arrays.asList(
                new Popisivac("Marijana", "Zeljkovic", "marijana.zeljkovic", "maki"),
                new Popisivac("Kristijan", "Stepanov", "kristijan.stepanov", "kiki")
        );
        return listaPopisivaca;
    }

    private List<AktivnostInputModel> getListaAktivnostiFromServer(String popisivac) {
        // TODO: Učitati listu aktivnosti za datog popisivača
        var aktivnost1 = new DnevnaAktivnost(LocalDate.now(),0,0);
        var aktivnost2 = new DnevnaAktivnost(LocalDate.now(), 2, 3);
        List<AktivnostInputModel> lista1 = FXCollections.observableList(Collections.singletonList(new AktivnostInputModel(aktivnost1)));
        List<AktivnostInputModel> lista2 = FXCollections.observableList(Collections.singletonList(new AktivnostInputModel(aktivnost2)));
        for(int i=0; i<lista1.size(); i++)
        {
            lista1.get(i).setId(i+1);
        }
        for(int i=0; i<lista2.size(); i++)
        {
            lista2.get(i).setId(i+1);
        }
        if(popisivac.contains("kris"))
            return lista1;
        else
            return lista2;
    }

    public void povratak(ActionEvent actionEvent) {
        String retPath;
        if (KontrolerFormeZaPrijavu.getTrenutniKorisnik() instanceof OGInstruktor) {
            retPath = "/view/FormaZaRadOGInstruktora.fxml";
        } else if (KontrolerFormeZaPrijavu.getTrenutniKorisnik() instanceof DEInstruktor) {
            retPath = "/view/FormaZaRadDEInstruktora.fxml";
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
        initialize(null, null);
    }
}
