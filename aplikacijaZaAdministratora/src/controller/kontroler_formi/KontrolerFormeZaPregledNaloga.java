package controller.kontroler_formi;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.korisnicki_nalozi.*;
import model.table_input_models.*;
import test.Aplikacija;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;

import javax.ws.rs.core.Response;

import eCensus.rest.client.ClanPKLSCMISKlijent;

public abstract class KontrolerFormeZaPregledNaloga implements Initializable {

    protected static KorisnikInputModel accountForEdit;


    public static KorisnikInputModel getAccountForEdit() {
        return accountForEdit;
    }

    public static void setAccountForEdit(KorisnikInputModel accountForEdit2) {
        accountForEdit = accountForEdit2;
    }

    public static TableView<KorisnikInputModel> staticTabela;

    @FXML
    protected TableView<KorisnikInputModel> tabela;

    @FXML
    protected TableColumn<Object, Object> redniBrojColumn;

    @FXML
    protected TableColumn<Object, Object> prezimeColumn;

    @FXML
    protected TableColumn<Object, Object> imeColumn;

    @FXML
    protected TableColumn<Object, Object> korisnickoImeColumn;

    @FXML
    protected TableColumn<Object, Object> opcijeColumn;

    protected ObservableList<KorisnikInputModel> lista;

    public void back(ActionEvent actionEvent) throws IOException {
        String retPath;
        if (KontrolerFormeZaPrijavu.getTrenutniKorisnik() instanceof ClanPKLS) {
            retPath = "/view/FormaZaRadClanaPKLS.fxml";
        } else if (KontrolerFormeZaPrijavu.getTrenutniKorisnik() instanceof OGInstruktor) {
            retPath = "/view/FormaZaRadOGInstruktora.fxml";
        } else {
            retPath = "/view/FormaZaRadAdministratoraAgencije.fxml";
        }
        Parent root = FXMLLoader.load(getClass().getResource(retPath));
        Aplikacija.getStage().setScene(new Scene(root));
    }

    @FXML
    private Label labelaZaIme;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        if (!initializeList())
            return;

        Platform.runLater(() -> tabela.refresh());

        staticTabela = tabela;

        KorisnikSistema korisnikSistema = KontrolerFormeZaPrijavu.getTrenutniKorisnik();
        var wrapper = new Object() {
            String sadrzajLabele;
            String prezimeIIme = korisnikSistema.getPrezime() + " " + korisnikSistema.getIme();
        };
        Platform.runLater(() ->
        {
            wrapper.sadrzajLabele = labelaZaIme.getText();
            labelaZaIme.setText(wrapper.sadrzajLabele + wrapper.prezimeIIme);
        });

        redniBrojColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        prezimeColumn.setCellValueFactory(new PropertyValueFactory<>("prezime"));
        imeColumn.setCellValueFactory(new PropertyValueFactory<>("ime"));
        korisnickoImeColumn.setCellValueFactory(new PropertyValueFactory<>("korisnickoIme"));
        opcijeColumn.setCellValueFactory(new PropertyValueFactory<>("Buttons"));
        Platform.runLater(() -> tabela.setItems(lista));

        System.out.print(lista);
        popraviIdove(lista);
        tabela.getItems().addAll(lista);

        if (tabela.getItems().get(0).getButtons().length == 2) {
            for (int i = 0; i < tabela.getItems().size(); i++) {
                var item = tabela.getItems().get(i);
                item.getButtons()[1].setOnAction(e ->
                {
                    tabela.getItems().removeAll(item);

                    ClanPKLSCMISKlijent clanPKLSCMISKlijent = new ClanPKLSCMISKlijent(KontrolerFormeZaPrijavu.getTrenutniKorisnik());
                    Response odgovor = clanPKLSCMISKlijent.obrisiKorisnika(item.getKorisnikSistema());
                    if (Response.Status.Family.SUCCESSFUL.equals(odgovor.getStatusInfo().getFamily())) {
                        Aplikacija.connLogger.getLogger().log(Level.INFO, "Uspješno brisanje.");
                    } else {

                        Aplikacija.connLogger.logHeaders(Level.SEVERE, odgovor);
                    }

                    popraviIdove(tabela.getItems());
                    Platform.runLater(() -> tabela.refresh());
                });

                item.getButtons()[0].setOnAction(e ->
                {
                    String path, quest = "Da li želite da izmjenite nalog ";
                    if (item.getKorisnikSistema() instanceof DEInstruktor) {
                        path = "/view/FormaZaIzmjenuNalogaDEInstruktora.fxml";
                        quest += "državnog/entitetskog instruktora?";
                    } else if (item.getKorisnikSistema() instanceof ClanPKLS) {
                        path = "/view/FormaZaIzmjenuNalogaClanaPKLS.fxml";
                        quest += "administratora PKLS-a?";
                    } else if (item.getKorisnikSistema() instanceof OGInstruktor) {
                        path = "/view/FormaZaIzmjenuNalogaOGInstruktora.fxml";
                        quest += "opštinkog/gradskog instruktora?";
                    } else if (item.getKorisnikSistema() instanceof Popisivac) {
                        path = "/view/FormaZaIzmjenuNalogaPopisivaca.fxml";
                        quest += "popisivača?";
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Nalog nije u redu!");
                        ButtonType buttonType = alert.showAndWait().get();
                        return;
                    }
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setContentText(quest);
                    ButtonType buttonType = alert.showAndWait().get();
                    if (!buttonType.getText().equals("OK")) return;
                    accountForEdit = item;
                    try {
                        Aplikacija.getStage().setScene(new Scene(FXMLLoader.load(getClass().getResource(path))));
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                });
            }
        }
        else if (tabela.getItems().get(0).getButtons().length == 1)
        {
            for (int i = 0; i < tabela.getItems().size(); i++) {
                var item = tabela.getItems().get(i);
                item.getButtons()[0].setOnAction(e ->
                {
                    String path;
                    item.getButtons()[0].setText("Aktivnost");
                    path = "/view/FormaZaPregledAktivnostiPopisivaca.fxml";

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("Da li želite da pogledate aktivnosti popisivača?");
                    ButtonType buttonType = alert.showAndWait().get();
                    if (!buttonType.getText().equals("OK")) return;
                    accountForEdit = item;
                    try {
                        Aplikacija.getStage().setScene(new Scene(FXMLLoader.load(getClass().getResource(path))));
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                });
            }
        }
    }

        public static void popraviIdove (ObservableList < KorisnikInputModel > lista)
        {
            var counterWrapper = new Object() {
                int idCounter = 1;
            };
            lista.forEach(e -> e.setId(counterWrapper.idCounter++));
        }

        public abstract boolean initializeList ();

        public static TableView<KorisnikInputModel> getTabela () {
            return staticTabela;
        }

        public static void setTabela (TableView < KorisnikInputModel > tabela) {
            KontrolerFormeZaPregledNaloga.staticTabela = tabela;
        }
    }
