package kontroleri;

import java.io.File;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.ws.rs.ProcessingException;

import eCensus.rest.client.PopisivacCMISKlijent;
import eCensus.rest.client.PopisivacGlavniServerKlijent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.ElementTabeleDomacinstvo;
import model.ElementTabeleStanovnistvo;
import model.Popisnica;
import model.PopisnicaZaDomacinstvo;
import model.PopisnicaZaStanovnika;
import model.pracenje_popisa.izvjestaji_o_popisivacu.DnevnaAktivnost;
import model.pracenje_popisa.izvjestaji_o_popisivacu.Kontrolnik;
import util.PrikazObavjestenja;
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
			PopisivacGlavniServerKlijent glavniServer = new PopisivacGlavniServerKlijent(KontrolerFormeZaPrijavu.korisnik);
	        PopisivacCMISKlijent cmisServer = new PopisivacCMISKlijent(KontrolerFormeZaPrijavu.korisnik);
			
			List<PopisnicaZaDomacinstvo> popisniceDomacinstvo = SerijalizacijaPopisnica.deserijalizujPopisniceZaDomacinstvo();
			
			for(ListIterator<PopisnicaZaDomacinstvo> iterator = popisniceDomacinstvo.listIterator(); iterator.hasNext();) {
				PopisnicaZaDomacinstvo popisnica = iterator.next();
				glavniServer.obradiPopisniceZaDomacinstva(popisnica);
				
				Kontrolnik kontrolnik = new Kontrolnik(popisnica.getIdPopisnogKruga(), popisnica.getIdOpstine(), 1, 1, popisnica.getBrojClanovaDomacinstva());
			    cmisServer.azurirajKontrolnik(kontrolnik);
			    
			    PopisivacCMISKlijent cmis = new PopisivacCMISKlijent(KontrolerFormeZaPrijavu.korisnik);
	      		cmis.azurirajAktivostPopisivaca((int)KontrolerFormeZaPrijavu.korisnik.getId(), new DnevnaAktivnost(LocalDate.now(), 0, 1));
			    
				iterator.remove();
			}
			
			SerijalizacijaPopisnica.serijalizujPopisniceZaDomacinstvo(popisniceDomacinstvo);
			
			List<PopisnicaZaStanovnika> popisniceStanovnistvo = SerijalizacijaPopisnica.deserijalizujPopisniceZaStanovnika();
     	          
			for(ListIterator<PopisnicaZaStanovnika> iterator = popisniceStanovnistvo.listIterator();
						iterator.hasNext();) {
				PopisnicaZaStanovnika popisnica = iterator.next();
				glavniServer.obradiPopisniceZaStanovnike(popisnica);
				
				PopisivacCMISKlijent cmis = new PopisivacCMISKlijent(KontrolerFormeZaPrijavu.korisnik);
	      		cmis.azurirajAktivostPopisivaca((int)KontrolerFormeZaPrijavu.korisnik.getId(), new DnevnaAktivnost(LocalDate.now(), 1, 0));
			    
				iterator.remove();
			}

			SerijalizacijaPopisnica.serijalizujPopisniceZaStanovnika(popisniceStanovnistvo);
			
			PrikazObavjestenja.prikaziInfo("Popisnice su poslate.");
			
			Stage stage = (Stage) tabelaDomacinstvo.getScene().getWindow();
		    stage.close();
		}
		catch(ProcessingException e) {
			PrikazObavjestenja.prikaziUpozorenje("Nema pristupa internetu. Pokusajte poslati popisnice kasnije.");
		}
	}
}
