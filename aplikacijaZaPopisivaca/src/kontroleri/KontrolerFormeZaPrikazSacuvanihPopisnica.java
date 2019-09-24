package kontroleri;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import eCensus.rest.client.PopisivacGlavniServerKlijent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import main.Main;
import model.ElementTabeleDomacinstvo;
import model.ElementTabeleStanovnistvo;
import model.Popisnica;
import model.PopisnicaZaDomacinstvo;
import model.PopisnicaZaStanovnika;
import util.PrikazObavjestenja;
import util.PromjenaPisma;
import util.SerijalizacijaPopisnica;

public class KontrolerFormeZaPrikazSacuvanihPopisnica {
    private static final String CONFIG_FILE_PATH = "resources" + File.separator + "config.properties";

	List<PopisnicaZaDomacinstvo> popisniceDomacinstvo;
	List<PopisnicaZaStanovnika> popisniceStanovnistvo;
	
	@FXML
	private TableView<ElementTabeleDomacinstvo> tabelaDomacinstvo;
	@FXML
	private TableView<ElementTabeleStanovnistvo> tabelaStanovnistvo;
	@FXML
	private TableColumn<ElementTabeleStanovnistvo, ElementTabeleStanovnistvo> obrazacKolonaS;
	@FXML
	private TableColumn<ElementTabeleStanovnistvo, ElementTabeleStanovnistvo> entitetKolonaS;
	@FXML
	private TableColumn<ElementTabeleStanovnistvo, ElementTabeleStanovnistvo> opstinaKolonaS;
	@FXML
	private TableColumn<ElementTabeleStanovnistvo, ElementTabeleStanovnistvo> popisniKrugKolonaS;
	@FXML
	private TableColumn<ElementTabeleStanovnistvo, ElementTabeleStanovnistvo> stanKolonaS;
	@FXML
	private TableColumn<ElementTabeleStanovnistvo, ElementTabeleStanovnistvo> domacinstvoKolonaS;
	@FXML
	private TableColumn<ElementTabeleStanovnistvo, ElementTabeleStanovnistvo> liceKolonaS;
	@FXML
	private TableColumn<ElementTabeleDomacinstvo, ElementTabeleDomacinstvo> obrazacKolonaD;
	@FXML
	private TableColumn<ElementTabeleDomacinstvo, ElementTabeleDomacinstvo> entitetKolonaD;
	@FXML
	private TableColumn<ElementTabeleDomacinstvo, ElementTabeleDomacinstvo> opstinaKolonaD;
	@FXML
	private TableColumn<ElementTabeleDomacinstvo, ElementTabeleDomacinstvo> popisniKrugKolonaD;
	@FXML
	private TableColumn<ElementTabeleDomacinstvo, ElementTabeleDomacinstvo> stanKolonaD;
	@FXML
	private TableColumn<ElementTabeleDomacinstvo, ElementTabeleDomacinstvo> domacinstvoKolonaD;
	@FXML
	private TableColumn<ElementTabeleDomacinstvo, ElementTabeleDomacinstvo> zgradaKolonaD;

	public KontrolerFormeZaPrikazSacuvanihPopisnica() {
		KontrolerFormeZaRadPopisivaca.pregledSacuvanihPopisnicaStage.setOnShowing((event) -> inicijalizujTabele());
	}
	
	private void inicijalizujTabele() {
		obrazacKolonaS.setCellValueFactory(new PropertyValueFactory<>("obrazac"));
		entitetKolonaS.setCellValueFactory(new PropertyValueFactory<>("entitet"));
		opstinaKolonaS.setCellValueFactory(new PropertyValueFactory<>("opstina"));
		popisniKrugKolonaS.setCellValueFactory(new PropertyValueFactory<>("popisniKrug"));
		stanKolonaS.setCellValueFactory(new PropertyValueFactory<>("stan"));
		domacinstvoKolonaS.setCellValueFactory(new PropertyValueFactory<>("domacinstvo"));
		liceKolonaS.setCellValueFactory(new PropertyValueFactory<>("lice"));
		
		obrazacKolonaD.setCellValueFactory(new PropertyValueFactory<>("obrazac"));
		entitetKolonaD.setCellValueFactory(new PropertyValueFactory<>("entitet"));
		opstinaKolonaD.setCellValueFactory(new PropertyValueFactory<>("opstina"));
		popisniKrugKolonaD.setCellValueFactory(new PropertyValueFactory<>("popisniKrug"));
		stanKolonaD.setCellValueFactory(new PropertyValueFactory<>("stan"));
		domacinstvoKolonaD.setCellValueFactory(new PropertyValueFactory<>("domacinstvo"));
		zgradaKolonaD.setCellValueFactory(new PropertyValueFactory<>("zgrada"));
		
		List<PopisnicaZaDomacinstvo> popisniceDomacinstvo = SerijalizacijaPopisnica.deserijalizujPopisniceZaDomacinstvo();
		for(PopisnicaZaDomacinstvo p : popisniceDomacinstvo) {
			ElementTabeleDomacinstvo e = new ElementTabeleDomacinstvo(p.getIdObrasca(), p.getIdEntiteta(),
					p.getIdOpstine(), p.getIdPopisnogKruga(), p.getIdZgrade(), p.getIdStana(), p.getIdDomacinstva());
			tabelaDomacinstvo.getItems().add(e);
		}
		
		List<PopisnicaZaStanovnika> popisniceStanovnistvo = SerijalizacijaPopisnica.deserijalizujPopisniceZaStanovnika();
		for(PopisnicaZaStanovnika p : popisniceStanovnistvo) {
			ElementTabeleStanovnistvo e = new ElementTabeleStanovnistvo(p.getIdObrasca(), p.getIdEntiteta(),
					p.getIdOpstine(), p.getIdPopisnogKruga(), p.getIdLica(), p.getIdStana(), p.getIdDomacinstva());
			tabelaStanovnistvo.getItems().add(e);
		}
	}
	
	@FXML
	private void posaljiPopisnice() {
		try {
			Properties properties = new Properties();
	    	properties.load(new FileInputStream(new File(CONFIG_FILE_PATH)));
	    	String keystore = properties.getProperty("DEFAULT_KEYSTORE");
	    	String keystoreLozinka = properties.getProperty("DEFAULT_KEYSTORE_PASSWORD");
	    	String truststore = properties.getProperty("DEFAULT_TRUSTSTORE");
	    	String truststoreLozinka = properties.getProperty("DEFAULT_TRUSTSTORE_PASSWORD");
	    	
			PopisivacGlavniServerKlijent glavniServer = new PopisivacGlavniServerKlijent(keystore, keystoreLozinka, truststore, truststoreLozinka, 
	        		KontrolerFormeZaPrijavu.korisnik.getKorisnickoIme(), KontrolerFormeZaPrijavu.korisnik.getLozinkaHash());
	        
			List<PopisnicaZaDomacinstvo> popisniceDomacinstvo = SerijalizacijaPopisnica.deserijalizujPopisniceZaDomacinstvo();
			//poslati popisnice
			
			List<PopisnicaZaStanovnika> popisniceStanovnistvo = SerijalizacijaPopisnica.deserijalizujPopisniceZaStanovnika();
			//poslati popisnice
			
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
}
