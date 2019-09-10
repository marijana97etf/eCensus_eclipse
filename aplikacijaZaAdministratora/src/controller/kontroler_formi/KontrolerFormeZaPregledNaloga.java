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
import model.korisnicki_nalozi.ClanPKLS;
import model.korisnicki_nalozi.DEInstruktor;
import model.korisnicki_nalozi.KorisnikSistema;
import model.korisnicki_nalozi.OGInstruktor;
import model.table_input_models.*;
import test.Pokreni_GUI_Aplikaciju;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public abstract class KontrolerFormeZaPregledNaloga implements Initializable {

    protected static KorisnikInputModel accountForEdit;


    public static KorisnikInputModel getAccountForEdit() {
        return accountForEdit;
    }

    public static void setAccountForEdit(ClanPKLSInputModel accountForEdit) {
        accountForEdit = accountForEdit;
    }

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
        Parent root = FXMLLoader.load(getClass().getResource("/view/FormaZaRadAdministratora.fxml"));
        Pokreni_GUI_Aplikaciju.getStage().setScene(new Scene(root));
    }

    @FXML
    private Label labelaZaIme;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        initializeList();

        KorisnikSistema korisnikSistema = KontrolerFormeZaPrijavu.getCurrentAccount();
        var wrapper = new Object()
        {
            String sadrzajLabele;
            String prezimeIIme = korisnikSistema.getPrezime() + " " + korisnikSistema.getIme();
        };
        Platform.runLater(()->
        {
            wrapper.sadrzajLabele=labelaZaIme.getText();
            labelaZaIme.setText(wrapper.sadrzajLabele + wrapper.prezimeIIme);
        });

        redniBrojColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        prezimeColumn.setCellValueFactory(new PropertyValueFactory<>("prezime"));
        imeColumn.setCellValueFactory(new PropertyValueFactory<>("ime"));
        korisnickoImeColumn.setCellValueFactory(new PropertyValueFactory<>("korisnickoIme"));
        opcijeColumn.setCellValueFactory(new PropertyValueFactory<>("obaButtona"));
        Platform.runLater(()->tabela.setItems(lista));

        popraviIdove(lista);
        tabela.getItems().addAll(lista);

        for(int i=0; i<tabela.getItems().size(); i++)
        {
            var item = tabela.getItems().get(i);
            item.getBrisanjeButton().setOnAction(e->
            {
                tabela.getItems().removeAll(item);
                Pokreni_GUI_Aplikaciju.getKontrolerZaCuvanjeNaloga().getSkladisteNaloga().remove(item.getKorisnikSistema());
                popraviIdove(tabela.getItems());
                Platform.runLater(()-> tabela.refresh());
            });

            item.getIzmjenaButton().setOnAction(e->
            {
                String path, quest="Da li želite da izmjenite nalog ";
                if(item.getKorisnikSistema() instanceof DEInstruktor)
                {
                    path="/view/FormaZaIzmjenuNalogaDEInstruktora.fxml";
                    quest+="državnog/entitetskog instruktora?";
                }
                else if(item.getKorisnikSistema() instanceof ClanPKLS)
                {
                    path="/view/FormaZaIzmjenuNalogaClanaPKLS.fxml";
                    quest+="administratora PKLS-a?";
                }
                else if(item.getKorisnikSistema() instanceof OGInstruktor)
                {
                    path="/view/FormaZaIzmjenuNalogaOGInstruktora.fxml";
                    quest+="opštinkog/gradskog instruktora?";
                }
                else
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Nalog nije u redu!");
                    ButtonType buttonType = alert.showAndWait().get();
                    return;
                }
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText(quest);
                ButtonType buttonType = alert.showAndWait().get();
                if(!buttonType.getText().equals("OK")) return;
                accountForEdit=item;
                try {
                    Pokreni_GUI_Aplikaciju.getStage().setScene(new Scene(FXMLLoader.load(getClass().getResource(path))));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                Platform.runLater(()-> tabela.refresh());
            });
        }
    }

    public static void popraviIdove(ObservableList<KorisnikInputModel> lista)
    {
        var counterWrapper = new Object() { int idCounter = 1; };
        lista.forEach(e -> e.setId(counterWrapper.idCounter++));
    }

    public abstract void initializeList();
}
