package controller.kontroler_formi;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.korisnicki_nalozi.DEInstruktor;
import model.korisnicki_nalozi.OGInstruktor;
import model.pracenje_popisa.izvjestaji_o_popisivacu.DnevnaAktivnost;
import model.pracenje_popisa.izvjestaji_o_popisivacu.PopisniKrug;
import model.table_input_models.AktivnostInputModel;
import test.Aplikacija;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;
import java.util.logging.Level;

public class KontrolerFormeZaPregledAktivnostiPopisivaca implements Initializable {

    public TableColumn redniBrojColumn;
    public TableColumn datumColumn;
    public TableColumn brojKrugovaColumn;
    public TableColumn popisniKrugoviColumn;
    public TableView tabela;

    public static DnevnaAktivnost dnevnaAktivnostForShow;
    public static LocalDate datumForShow;

    protected ObservableList<AktivnostInputModel> lista;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        getListaFromServer();
        for(int i=0; i<lista.size(); i++)
        {
            lista.get(i).setId(i+1);
            var wrapper = new Object() {int j;};
            wrapper.j=i;
            lista.get(i).getPopisniKrugovi().setOnAction(e->
            {
                dnevnaAktivnostForShow = lista.get(wrapper.j).getAktivnost();
                datumForShow = dnevnaAktivnostForShow.getDan();
                try
                {
                    Aplikacija.getStage().setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/FormaZaPregledObidjenihPopisnihKrugova.fxml"))));
                }
                catch (IOException ex)
                {
                    Aplikacija.connLogger.getLogger().log(Level.WARNING, "NeuspjeÅ¡no Ä�itanje forme.");
                }
            });
        }
        redniBrojColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        datumColumn.setCellValueFactory(new PropertyValueFactory<>("datum"));
        brojKrugovaColumn.setCellValueFactory(new PropertyValueFactory<>("brojKrugova"));
        popisniKrugoviColumn.setCellValueFactory(new PropertyValueFactory<>("popisniKrugovi"));
        Platform.runLater(() -> tabela.setItems(lista));
    }

    private void getListaFromServer() {
        // TODO: Čekaju se funkcije sa servera
        var aktivnost1 = new DnevnaAktivnost(LocalDate.now(),0,0);
        var popisniKrug = new PopisniKrug("Banja Luka", "LaktaÅ¡i", null);
        var popisniKrug2 = new PopisniKrug("Prijedor", "Omarska", null);
        //aktivnost1.setObidjeniPopisniKrugovi(new HashSet<PopisniKrug>(Arrays.asList(popisniKrug,popisniKrug2)));
        //lista = FXCollections.observableList(Arrays.asList(new AktivnostInputModel(aktivnost1)));
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
}
