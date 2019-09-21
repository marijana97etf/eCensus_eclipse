package kontroleri;

import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.ElementTabeleDomacinstvo;
import model.ElementTabeleStanovnistvo;
import model.Popisnica;
import model.PopisnicaZaDomacinstvo;
import model.PopisnicaZaStanovnika;
import util.SerijalizacijaPopisnica;

public class KontrolerFormeZaPrikazSacuvanihPopisnica {
	
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
		
		List<Popisnica> popisniceDomacinstvo = SerijalizacijaPopisnica.deserijalizujPopisnice("domacinstvo");
		for(Popisnica p : popisniceDomacinstvo) {
			PopisnicaZaDomacinstvo pd = (PopisnicaZaDomacinstvo)p;
			ElementTabeleDomacinstvo e = new ElementTabeleDomacinstvo(pd.getIdObrasca(), pd.getIdEntiteta(),
					pd.getIdOpstine(), pd.getIdPopisnogKruga(), pd.getIdZgrade(), pd.getIdStana(), pd.getIdDomacinstva());
			tabelaDomacinstvo.getItems().add(e);
		}
		
		List<Popisnica> popisniceStanovnistvo = SerijalizacijaPopisnica.deserijalizujPopisnice("stanovnistvo");
		for(Popisnica p : popisniceStanovnistvo) {
			PopisnicaZaStanovnika ps = (PopisnicaZaStanovnika)p;
			ElementTabeleStanovnistvo e = new ElementTabeleStanovnistvo(ps.getIdObrasca(), ps.getIdEntiteta(),
					ps.getIdOpstine(), ps.getIdPopisnogKruga(), ps.getIdLica(), ps.getIdStana(), ps.getIdDomacinstva());
			tabelaStanovnistvo.getItems().add(e);
		}
	}
	
	@FXML
	private void posaljiPopisnicu() {
		//TODO:Poslati popisnice na glavni server
	}
	
}
