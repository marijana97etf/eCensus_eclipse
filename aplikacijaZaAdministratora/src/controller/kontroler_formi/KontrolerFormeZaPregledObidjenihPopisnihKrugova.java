package controller.kontroler_formi;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.pracenje_popisa.izvjestaji_o_popisivacu.PopisniKrug;
import model.table_input_models.AktivnostInputModel;
import model.table_input_models.PopisniKrugInputModel;
import test.Aplikacija;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.logging.Level;
import java.util.stream.Collectors;

public class KontrolerFormeZaPregledObidjenihPopisnihKrugova implements Initializable
{
    public TableView tabela;
    public TableColumn redniBrojColumn;
    public TableColumn gradColumn;
    public TableColumn opstinaColumn;
    public Label datum;

    protected ObservableList<PopisniKrugInputModel> lista;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        redniBrojColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        gradColumn.setCellValueFactory(new PropertyValueFactory<>("grad"));
        opstinaColumn.setCellValueFactory(new PropertyValueFactory<>("opstina"));
        List<PopisniKrugInputModel> list = new ArrayList<>();
        Set<PopisniKrug> popisniKrugovi = KontrolerFormeZaPregledAktivnostiPopisivaca.dnevnaAktivnostForShow.getObidjeniPopisniKrugovi();
        PopisniKrug[] nizPopisnihKrugova = popisniKrugovi.toArray(new PopisniKrug[popisniKrugovi.size()]);
        for (int i=0; i<popisniKrugovi.size(); i++) {
            PopisniKrugInputModel popisniKrugInputModel = new PopisniKrugInputModel(nizPopisnihKrugova[i]);
            popisniKrugInputModel.setId(i+1);
            list.add(popisniKrugInputModel);
        }
        lista = FXCollections.observableList(list);
        tabela.getItems().addAll(lista);
        String date = datum.getText();
        datum.setText(date + KontrolerFormeZaPregledAktivnostiPopisivaca.datumForShow);
    }

    public void povratak(ActionEvent actionEvent) {
        try
        {
            Aplikacija.getStage().setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/FormaZaPregledAktivnostiPopisivaca.fxml"))));
        }
        catch (IOException ex)
        {
            Aplikacija.connLogger.getLogger().log(Level.WARNING, "Neuspješno čitanje forme.");
        }
    }
}
