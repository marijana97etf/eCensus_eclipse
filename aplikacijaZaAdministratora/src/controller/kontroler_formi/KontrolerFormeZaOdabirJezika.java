package controller.kontroler_formi;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.korisnicki_nalozi.*;
import test.Aplikacija;
import util.PromjenaJezika;
import util.PromjenaPisma;

import javafx.event.ActionEvent;
import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ResourceBundle;
import java.util.logging.Level;

public class KontrolerFormeZaOdabirJezika implements Initializable {
    @FXML
    RadioButton hrvatskiRadio;
    @FXML
    RadioButton bosanskiRadio;
    @FXML
    RadioButton srpskiRadio;

    public void povratak(ActionEvent actionEvent)
    {
        KorisnikSistema korisnikSistema = KontrolerFormeZaPrijavu.getTrenutniKorisnik();
        String path="";
        if(korisnikSistema instanceof AdministratorAgencije)
        {
            path= "/view/FormaZaRadAdministratoraAgencije.fxml";
        }
        else if(korisnikSistema instanceof ClanPKLS)
        {
            path="/view/FormaZaRadClanaPKLS.fxml";
        }
        else if(korisnikSistema instanceof DEInstruktor)
        {
            path="/view/FormaZaRadDEInstruktora.fxml";
        }
        else if(korisnikSistema instanceof OGInstruktor)
        {
            path="/view/FormaZaRadOGInstruktora.fxml";
        }
        try {
            Aplikacija.getStage().setScene(new Scene(FXMLLoader.load(getClass().getResource(path))));
        } catch (IOException ex) {
            Aplikacija.connLogger.getLogger().log(Level.INFO, "Neuspješno učitavanje forme.");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    private void promijeniJezikIPismo(String odabraniJezik) {
        String trenutniJezik = Aplikacija.getProperty();
        if(odabraniJezik != null && !trenutniJezik.equals(odabraniJezik)) {
            if("srpski".equals(trenutniJezik))
                PromjenaPisma.promijeniPismo("latinica");
            prevedi(odabraniJezik);
            if("srpski".equals(odabraniJezik))
                PromjenaPisma.promijeniPismo("cirilica");
            try {
                Aplikacija.setProperty(odabraniJezik);

                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);

                Parent root = FXMLLoader.load(getClass().getResource("/view/FormaZaUspjesnuPromjenuJezikaIPisma.fxml"));
                stage.setScene(new Scene(root));
                stage.setResizable(false);
                stage.showAndWait();

                System.exit(0);
            }
            catch(IOException e) {
                e.printStackTrace();
            }
        }
        else
        {
            Alert poruka = new Alert(Alert.AlertType.ERROR);
            poruka.setContentText(Aplikacija.prevediRecenicu("Morate odabrati jezik i pismo različito od trenutnog"));
            ButtonType tip = poruka.showAndWait().get();
            if(!tip.getText().equals("OK")) return;
        }
    }

    private void prevedi(String odabraniJezik) {
        String formeDirektorijum = "src/view";
        File folder = new File(formeDirektorijum);
        File[] listOfFiles = folder.listFiles();

        for(File file : listOfFiles) {
            try {
                byte[] bytes = new byte[(int)file.length()];
                DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(new FileInputStream(formeDirektorijum + "/" + file.getName())));
                dataInputStream.readFully(bytes);
                dataInputStream.close();
                String original = new String(bytes, StandardCharsets.UTF_8);

                String izmjena = PromjenaJezika.pronadjiIZamijeni(original, odabraniJezik);

                prepisiFajl(file, izmjena);
            }
            catch(IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void prepisiFajl(File file, String sadrzaj) throws IOException {
        BufferedWriter writer =
                new BufferedWriter(new OutputStreamWriter(new FileOutputStream("src/view/" + file.getName()), StandardCharsets.UTF_8));
        writer.write(sadrzaj);
        writer.close();
    }

    public void promjeniJezik(ActionEvent actionEvent) {
        if(hrvatskiRadio.isSelected())
        {
            promijeniJezikIPismo("hrvatski");
        }
        else if(bosanskiRadio.isSelected())
        {
            promijeniJezikIPismo("bosnjacki");
        }
        else if(srpskiRadio.isSelected())
        {
            promijeniJezikIPismo("srpski");
        }
        else
        {
            Alert poruka = new Alert(Alert.AlertType.ERROR);
            poruka.setContentText(Aplikacija.prevediRecenicu("Odaberite jedan od ponuđenih jezika!"));
            ButtonType tip = poruka.showAndWait().get();
            if(!tip.getText().equals("OK")) return;
        }
    }
}
