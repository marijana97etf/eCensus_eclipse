package controller.kontroler_formi;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Properties;
import java.util.ResourceBundle;
import org.javatuples.Pair;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import jdk.jshell.spi.ExecutionControl;
import model.korisnicki_nalozi.AdministratorAgencije;
import model.korisnicki_nalozi.ClanPKLS;
import model.korisnicki_nalozi.DEInstruktor;
import model.korisnicki_nalozi.KorisnikSistema;
import model.korisnicki_nalozi.OGInstruktor;
import model.pracenje_popisa.JEZIK;
import model.pracenje_popisa.PISMO;
import test.Aplikacija;
import util.PromjenaJezika;
import util.PromjenaPisma;

public class KontrolerFormeZaPromjenuJezika implements Initializable {

	
	private JEZIK trenutniJezik;
	private PISMO trenutnoPismo;
	
	{
		var property = getProperty();
		trenutniJezik = property.getValue0();
		trenutnoPismo = property.getValue1();
	}
	
    @FXML
    Label label;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        KorisnikSistema korisnikSistema = KontrolerFormeZaPrijavu.getTrenutniKorisnik();
        String newLine = System.lineSeparator();
        if(korisnikSistema instanceof AdministratorAgencije)
        {
            label.setText("Administrator agencije :" + newLine + korisnikSistema.getPrezime() + " " + korisnikSistema.getIme());
        }
        else if(korisnikSistema instanceof ClanPKLS)
        {
            label.setText("Član PKLS :" + newLine + korisnikSistema.getPrezime() + " " + korisnikSistema.getIme());
        }
        else if(korisnikSistema instanceof DEInstruktor)
        {
            label.setText("Državni/entitetski instruktor :" + newLine + korisnikSistema.getPrezime() + " " + korisnikSistema.getIme());
        }
        else if(korisnikSistema instanceof OGInstruktor) {
            label.setText("Opštinski/gradski instruktor :" + newLine + korisnikSistema.getPrezime() + " " + korisnikSistema.getIme());
        }
        else
        {
            Alert alert2 = new Alert(Alert.AlertType.ERROR);
            alert2.setContentText("Nalog nije ok!");
            ButtonType buttonType2 = alert2.showAndWait().get();
        }
        
    }

    @FXML
    RadioButton srpski;

    @FXML
    RadioButton bosanski;

    @FXML
    RadioButton hrvatski;


    public void promjeniJezik(ActionEvent actionEvent) throws IOException, ExecutionControl.NotImplementedException {
        KorisnikSistema korisnikSistema = KontrolerFormeZaPrijavu.getTrenutniKorisnik();
        if(srpski.isSelected())
        {
            korisnikSistema.setJezik(JEZIK.SRPSKI);
            korisnikSistema.setPismo(PISMO.CIRILICA);
        }
        else if(bosanski.isSelected())
        {
        	korisnikSistema.setJezik(JEZIK.BOSANSKI);
        	korisnikSistema.setPismo(PISMO.LATINICA);
        }
        else if(hrvatski.isSelected())
        {
        	korisnikSistema.setJezik(JEZIK.HRVATSKI);
        	korisnikSistema.setPismo(PISMO.LATINICA);
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Odaberite jezik.");
            ButtonType buttonType = alert.showAndWait().get();
            if(!buttonType.getText().equals("OK")) 
            {
            	return;
            }
        }
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Da li želite da promjenite jezik?");
        ButtonType buttonType = alert.showAndWait().get();
        if(!buttonType.getText().equals("OK")) 
        {
        	return;
        }
        promijeniJezikIPismo(korisnikSistema.getJezik(), korisnikSistema.getPismo());
        back(actionEvent);
    }
	
	private void promijeniJezikIPismo(JEZIK odabraniJezik, PISMO odabranoPismo) {
		
		boolean uslov = odabraniJezik != null && 
				(JEZIK.SRPSKI.equals(odabraniJezik)&&
				 JEZIK.SRPSKI.equals(trenutniJezik)&&
				 !trenutnoPismo.equals(odabranoPismo))||
					!trenutniJezik.equals(odabraniJezik);
		if(uslov) 
		{
			switch(odabranoPismo)
			{
				case LATINICA:
					PromjenaPisma.promijeniPismo(PISMO.LATINICA);
					break;
				case CIRILICA:
					PromjenaPisma.promijeniPismo(PISMO.CIRILICA);
						break;
			}
			prevedi(odabraniJezik, odabranoPismo);
			if(JEZIK.SRPSKI.equals(odabraniJezik))
				PromjenaPisma.promijeniPismo(PISMO.CIRILICA);
			try {
				setProperty(odabraniJezik, odabranoPismo);
				
				// ALERT! 
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
	            System.exit(0);
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		}
		else
			prikaziUpozorenje("Morate odabrati jezik i pismo različito od trenutnog");
	}
	
	private void prevedi(JEZIK odabraniJezik, PISMO odabranoPismo) {
		String formeDirektorijum = "src" + File.separator + "view";
		File folder = new File(formeDirektorijum);
		File[] listOfFiles = folder.listFiles();
		
		for(File file : listOfFiles) {
			try {
				byte[] bytes = new byte[(int)file.length()];
				DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(new FileInputStream(formeDirektorijum + File.separator + file.getName())));
				dataInputStream.readFully(bytes);           
				dataInputStream.close();      
				String original = new String(bytes, StandardCharsets.UTF_8);
										
				String izmjena = PromjenaJezika.pronadjiIZamijeni(original, odabraniJezik, odabranoPismo);
						
				prepisiFajl(file, izmjena);	            
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private Pair<JEZIK, PISMO> getProperty() {
		String jezikIPismo = "";
		try {
			Properties properties = new Properties();
			FileInputStream in = new FileInputStream(new File("." + File.separator + "resources" + File.separator + "config.properties"));
			properties.load(in);
			jezikIPismo = properties.getProperty("TRENUTNI_JEZIK_I_PISMO");
			String jezik = jezikIPismo.split("-")[0];
			String pismo = jezikIPismo.split("-")[1];
			
			if(jezik.equals(JEZIK.BOSANSKI.toString()))
			{
				trenutniJezik=JEZIK.BOSANSKI;
			}
			else if(jezik.equals(JEZIK.HRVATSKI.toString()))
			{
				trenutniJezik=JEZIK.HRVATSKI;
			}
			else
			{
				trenutniJezik=JEZIK.SRPSKI;
			}

			if(pismo.equals(PISMO.CIRILICA.toString()))
				trenutnoPismo = PISMO.CIRILICA;
			else 
				trenutnoPismo = PISMO.LATINICA;
			
			in.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return new Pair<>(trenutniJezik, trenutnoPismo);
	}
	
	private void setProperty(JEZIK jezik, PISMO pismo) throws IOException{
		String jezikIPismo = jezik.toString()+"-"+pismo.toString();
		
		Properties properties = new Properties();
		FileInputStream in = new FileInputStream(new File("." + File.separator + "resources" + File.separator + "config.properties"));
		properties.load(in);
		in.close();
		
		FileOutputStream out = new FileOutputStream("." + File.separator + "resources" + File.separator + "config.properties");
		properties.setProperty("TRENUTNI_JEZIK_I_PISMO", jezikIPismo);
		properties.store(out, null);
		out.close();
	}
	
	private void prepisiFajl(File file, String sadrzaj) throws IOException {
		BufferedWriter writer =
	             new BufferedWriter(new OutputStreamWriter(new FileOutputStream("src" + File.separator + "view" + File.separator + file.getName()), StandardCharsets.UTF_8));
		writer.write(sadrzaj);
	    writer.close();
	}
	
	private void prikaziUpozorenje(String poruka){
    	String greska = "Greška";
    	if(PISMO.CIRILICA.equals(trenutnoPismo)) {
    		poruka = PromjenaPisma.zamijeniLatinicuCiricom(poruka);
    		greska = PromjenaPisma.zamijeniLatinicuCiricom(poruka);
    	}
    	
        Alert userNotSelectedAlert = new Alert(Alert.AlertType.ERROR);
	    userNotSelectedAlert.setTitle(greska);
	    userNotSelectedAlert.setHeaderText(greska + "!");
        userNotSelectedAlert.setContentText(poruka);
        userNotSelectedAlert.showAndWait();
    }
	
    public void back(ActionEvent actionEvent) throws IOException, ExecutionControl.NotImplementedException {
        KorisnikSistema korisnikSistema = KontrolerFormeZaPrijavu.getTrenutniKorisnik();
        if(korisnikSistema instanceof AdministratorAgencije)
        {
            Aplikacija.getStage().setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/FormaZaRadAdministratora.fxml"))));
        }
        else if(korisnikSistema instanceof ClanPKLS)
        {
            Aplikacija.getStage().setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/FormaZaRadClanaPKLS.fxml"))));
        }
        else if(korisnikSistema instanceof DEInstruktor) 
        {
        	Aplikacija.getStage().setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/FormaZaRadDEInstruktora.fxml"))));
        }
        else if(korisnikSistema instanceof OGInstruktor) 
        {
        	Aplikacija.getStage().setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/FormaZaRadOGInstruktora.fxml"))));
        }
        else
        {
            Alert alert2 = new Alert(Alert.AlertType.ERROR);
            alert2.setContentText("Nalog nije ok!");
            ButtonType buttonType2 = alert2.showAndWait().get();
        }
    }
}
