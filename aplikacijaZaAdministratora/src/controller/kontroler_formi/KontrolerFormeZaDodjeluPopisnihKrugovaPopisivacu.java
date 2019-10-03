package controller.kontroler_formi;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.stream.Collectors;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import eCensus.rest.client.ClanPKLSCMISKlijent;
import eCensus.rest.client.PopisivacCMISKlijent;

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

        GradChoiceBox.getItems().addAll(Aplikacija.prevediRecenice(new ArrayList<>(GradoviCollection.getGradovi())));
        OpstinaChoiceBox.getItems().addAll(Aplikacija.prevediRecenice(new ArrayList<>(OpstineCollection.getListaOpstina())));

        GradChoiceBox.setValue(Aplikacija.prevediRecenicu("Banja Luka"));
        OpstinaChoiceBox.setValue(Aplikacija.prevediRecenicu("Banja Luka"));

        var wrapper = new Object() {
        	String grad = GradChoiceBox.getValue();
        	int idOpstine = Integer.parseInt(OpstineCollection.getOpstine().get(OpstinaChoiceBox.getValue()));
        };
        
        popisniKrugovi = ucitajListuPopisnihKrugovaSaServera(wrapper.grad, wrapper.idOpstine);
        popisivaci = ucitajListuPopisivacaSaServera();

        popisivaciChoiceBox.getItems().addAll(popisivaci.stream()
                                                  .map(KorisnikSistema::getKorisnickoIme)
                                                  .collect(Collectors.toList()));
        
        GradChoiceBox.setOnAction(e -> {
        	wrapper.grad = GradChoiceBox.getValue();
        	popisniKrugovi = ucitajListuPopisnihKrugovaSaServera(wrapper.grad, wrapper.idOpstine);
        	if(popisniKrugovi.size()!=0) {
        		slikaPopisnogKruga.setVisible(true);
             	slikaPopisnogKruga.setImage(new Image(new ByteArrayInputStream(popisniKrugovi.get(index).getSlikaBytes())));
        	}
             	else
        		slikaPopisnogKruga.setVisible(false);
        });
        
        OpstinaChoiceBox.setOnAction(e -> {
        	wrapper.idOpstine = Integer.parseInt(OpstineCollection.getOpstine().get(OpstinaChoiceBox.getValue()));
        	popisniKrugovi = ucitajListuPopisnihKrugovaSaServera(wrapper.grad, wrapper.idOpstine);
        	if(popisniKrugovi.size()!=0) {
        		slikaPopisnogKruga.setVisible(true);
             	slikaPopisnogKruga.setImage(new Image(new ByteArrayInputStream(popisniKrugovi.get(index).getSlikaBytes())));
        	} else
        		slikaPopisnogKruga.setVisible(false);
        });
        if(popisniKrugovi.size()!=0)
        	slikaPopisnogKruga.setImage(new Image(new ByteArrayInputStream(popisniKrugovi.get(index).getSlikaBytes())));
    }

    private List<Popisivac> ucitajListuPopisivacaSaServera() {
        PopisivacCMISKlijent popisivacCMISKlijent = new PopisivacCMISKlijent(KontrolerFormeZaPrijavu.getTrenutniKorisnik());
        Response odgovor  = popisivacCMISKlijent.getListaPopisivaca();
        List<Popisivac> popisivaci = null;
        if(Response.Status.Family.SUCCESSFUL.equals(odgovor.getStatusInfo().getFamily())) {
        	popisivaci = odgovor.readEntity(new GenericType<LinkedList<Popisivac>>() {});
        	return popisivaci;
        } else {
            Aplikacija.connLogger.logHeaders(Level.SEVERE, odgovor);
        }
        return new ArrayList<>();
    }

    private List<PopisniKrug> ucitajListuPopisnihKrugovaSaServera(String grad, int idOpstine) {
    	ClanPKLSCMISKlijent clanPKLSCMISKlijent = new ClanPKLSCMISKlijent(KontrolerFormeZaPrijavu.getTrenutniKorisnik());
        return Arrays.asList(clanPKLSCMISKlijent.getListaPopisnihKrugova(grad, idOpstine).readEntity(PopisniKrug[].class));
    }

    public void dodjeliPopisniKrug(ActionEvent actionEvent) {
        Popisivac popisivac = popisivaci.stream().filter(e->
                e.getKorisnickoIme().equals(PromjenaPisma.zamijeniCirilicuLatinicom(popisivaciChoiceBox.getValue()))).findFirst().get();
        PopisniKrug popisniKrug = popisniKrugovi.get(index);
        
        PopisivacCMISKlijent popisivacCMISKlijent = new PopisivacCMISKlijent(KontrolerFormeZaPrijavu.getTrenutniKorisnik());
        List<PopisniKrug> listaPopisnihKrugova = Arrays.asList(popisivacCMISKlijent.getListaPopisnihKrugova((int) popisivac.getId()).readEntity(PopisniKrug[].class));
        
        if(listaPopisnihKrugova.contains(popisniKrug)) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            var uspjesnaPrijavaAdminAgencije=Aplikacija.prevediRecenicu("Već postoji ovakav popisni krug!");
            alert.setContentText(uspjesnaPrijavaAdminAgencije);
            alert.showAndWait();
            return;
        }
        
        popisivacCMISKlijent.dodajPopisneKrugovePopisivacu((int) popisivac.getId(), popisniKrug.getId(), popisniKrug.getIdOpstine());
        
        Alert poruka = new Alert(Alert.AlertType.INFORMATION);
        poruka.setContentText(Aplikacija.prevediRecenicu("Uspješno ste dodjelili popisni krug."));
        var tip = poruka.showAndWait();
    }

    public void povratak(ActionEvent actionEvent) {
        try {
            Aplikacija.getStage().setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/FormaZaRadClanaPKLS.fxml"))));
        } catch (IOException e) {
            Aplikacija.connLogger.getLogger().log(Level.WARNING, "NeuspjeÅ¡no Ä�itanje forme.");
        }
    }

    public void prethodniPopisniKrug(MouseEvent mouseEvent) {
        if(popisniKrugovi.size() != 0) {
        	slikaPopisnogKruga.setImage(new Image(new ByteArrayInputStream(popisniKrugovi.get(index=((popisniKrugovi.size()+index-1)%popisniKrugovi.size())).getSlikaBytes())));
        }
    }

    public void sljedeciPopisniKrug(MouseEvent mouseEvent) {
    	if(popisniKrugovi.size() != 0) {
    		slikaPopisnogKruga.setImage(new Image(new ByteArrayInputStream(popisniKrugovi.get(index=((index+1)%popisniKrugovi.size())).getSlikaBytes())));
    	}
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
