package kontroleri;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import main.Main;
import model.ClanDomacinstva;
import util.PrikazObavjestenja;

public class KontrolerFormeZaPopunjavanjeSpiskaLica {
	private Map<Integer, List<Node>> poljaZaPopunjavanje;
		
    @FXML
    private GridPane gridPane;
    @FXML 
    private TextField imeTextField;
    @FXML
    private TextField prezimeTextField;
    @FXML
    private TextField JMBGTextField;
    @FXML
    private ComboBox<String> odnosComboBox;
    @FXML
    private TextField redniBrojPorodiceTextField;
    @FXML
    private ComboBox<String> pComboBox;
    
    private int row = 3;
    
    public KontrolerFormeZaPopunjavanjeSpiskaLica() {		
		KontrolerFormeZaPopisivanjeDomacinstva.spisakLicaStage.setOnShowing((event) -> init());
	}
    
    private void init() {
    	inicijalizujOdnosComboBox(odnosComboBox);
    	inicijalizujPolozajComboBox(pComboBox);
    	
    	poljaZaPopunjavanje = new HashMap<Integer, List<Node>>();
		List<Node> list = new ArrayList<>();
		list.add(imeTextField);
		list.add(prezimeTextField);
		list.add(JMBGTextField);
		list.add(odnosComboBox);
		list.add(redniBrojPorodiceTextField);
		list.add(pComboBox);
		poljaZaPopunjavanje.put(1, list);
    }
    
    private void inicijalizujOdnosComboBox(ComboBox comboBox) {
    	List<String> odnosi = new ArrayList<>();
    	if(!"српски".equals(Main.trenutniJezik)) {
	        odnosi.add("Nosilac domaćinstva");
	        odnosi.add("Supruga/suprug");
	        odnosi.add("Partnerka/partner u neformalnoj zajednici");
	        odnosi.add("Kćerka/sin/usvojeno dijete/pastorka/pastorak");
	        odnosi.add("Majka/otac");
	        odnosi.add("Svekrva/svekar/tašta/tast");
	        odnosi.add("Unuka/unuk");
	        odnosi.add("Snaha/zet");
	        odnosi.add("Baka/deda");
	        odnosi.add("Sestra/brat");
	        odnosi.add("Ostala rodbina");
	        odnosi.add("Nije u srodstvu");
	        odnosi.add("Nije član domaćinstva (privremeno prisutno lice)");
    	}
    	else {
    		odnosi.add("Носилац домаћинства");
	        odnosi.add("Супруга/супруг");
	        odnosi.add("Партнерка/партнер у неформалној заједници");
	        odnosi.add("Кћерка/син/усвојено дијете/пасторка/пасторак");
	        odnosi.add("Мајка/отац");
	        odnosi.add("Свекрва/свекар/ташта/таст");
	        odnosi.add("Унука/унук");
	        odnosi.add("Снаха/зет");
	        odnosi.add("Бака/деда");
	        odnosi.add("Сестра/брат");
	        odnosi.add("Остала родбина");
	        odnosi.add("Није у сродству");
	        odnosi.add("Није члан домаћинства (привремено присутно лице)");
    	}
        ObservableList observableList = FXCollections.observableList(odnosi);
        odnosComboBox.setItems(observableList);
    }
    
    private void inicijalizujPolozajComboBox(ComboBox comboBox) {
    	List<String> polozaji = new ArrayList<>();
	    	if(!"српски".equals(Main.trenutniJezik)) {
	        polozaji.add("Supruga/suprug");
	        polozaji.add("Partnerka/partner u neformalnoj zajednici");
	        polozaji.add("Zajedničko dijete");
	        polozaji.add("Sama majka sa djetetom/djecom");
	        polozaji.add("Sam otac sa djetetom/djecom");
	        polozaji.add("Dijete koje živi samo sa jednim roditeljem");
	        polozaji.add("Dijete samo supruge ili partnerke");
	        polozaji.add("Dijete samo supruga ili partnera");
	        polozaji.add("Ne pripada nijednoj od porodica");
	    	}
	    	else {
	    		polozaji.add("Супруга/супруг");
		        polozaji.add("Партнерка/партнер у неформалној заједници");
		        polozaji.add("Заједничко дијете");
		        polozaji.add("Сама мајка са дјететом/дјецом");
		        polozaji.add("Сам отац са дјететом/дјецом");
		        polozaji.add("Дијете које живи само са једним родитељем");
		        polozaji.add("Дијете само супруге или партнерке");
		        polozaji.add("Дијете само супруга или партнера");
		        polozaji.add("Не припада ни једној од породица");
	    	}
        ObservableList observableList = FXCollections.observableList(polozaji);
        pComboBox.setItems(observableList);
    }

    @FXML
    private void dodajVrstu() {
        TextField imeTextField = new TextField();
        TextField prezimeTextField = new TextField();
        TextField JMBGTextField = new TextField();
        ComboBox<String> odnosPremaNosiocuDomacinstvaComboBox = new ComboBox<>();
        odnosPremaNosiocuDomacinstvaComboBox.setMinWidth(200);
        odnosPremaNosiocuDomacinstvaComboBox.setPrefWidth(200);
        odnosPremaNosiocuDomacinstvaComboBox.setMaxWidth(200);
        if(!"српски".equals(Main.trenutniJezik))
        	odnosPremaNosiocuDomacinstvaComboBox.setPromptText("Odaberi tip odnosa...");
        else
        	odnosPremaNosiocuDomacinstvaComboBox.setPromptText("Одабери тип односа...");
        TextField redniBrojPorodiceTextField = new TextField();
        ComboBox<String> polozajUPorodiciComboBox = new ComboBox<>();
        polozajUPorodiciComboBox.setMinWidth(200);
        polozajUPorodiciComboBox.setPrefWidth(200);
        polozajUPorodiciComboBox.setMaxWidth(200);
        if(!"српски".equals(Main.trenutniJezik))
        	polozajUPorodiciComboBox.setPromptText("Odaberi položaj...");
        else
        	polozajUPorodiciComboBox.setPromptText("Одабери положај...");

        RowConstraints rc = new RowConstraints();
        rc.setMinHeight(30);
        rc.setMaxHeight(30);
        rc.setPrefHeight(30);
        gridPane.getRowConstraints().add(rc);

        gridPane.add(imeTextField,0,row);
        gridPane.add(prezimeTextField, 1, row);
        gridPane.add(JMBGTextField, 2, row);
        gridPane.add(odnosPremaNosiocuDomacinstvaComboBox, 3, row);
        gridPane.add(redniBrojPorodiceTextField, 4, row);
        gridPane.add(polozajUPorodiciComboBox, 5, row);
        
        List<Node> list = new ArrayList<>();
        list.add(imeTextField);
        list.add(prezimeTextField);
        list.add(JMBGTextField);
        list.add(odnosPremaNosiocuDomacinstvaComboBox);
        list.add(redniBrojPorodiceTextField);
        list.add(polozajUPorodiciComboBox);
        
        poljaZaPopunjavanje.put(row-1, list);

        ++row;
    }
    
    @FXML
    private void sacuvajSpisakLica() {
    	setDefaultColors();
    	
    	Set<Entry<Integer, List<Node>>> poljaZaPopunjavanjeEntries = poljaZaPopunjavanje.entrySet();
    	for(Entry<Integer, List<Node>> entry : poljaZaPopunjavanjeEntries) {
    		List<Node> polja = entry.getValue();
    		int brojPraznihPolja = 0;
    		for(Node n : polja) {
    			if(n instanceof TextField) {
    				TextField field = (TextField)n;
    				if(field.getText().isBlank())
    					brojPraznihPolja++;
    			}
    			else {
    				ComboBox comboBox = (ComboBox)n;
    				if(comboBox.getSelectionModel().getSelectedItem() == null)
    					brojPraznihPolja++;
    			}
    		}
    		if(brojPraznihPolja > 0 && brojPraznihPolja < 6){
    			for(Node n : polja) {
    				if(n instanceof TextField) {
        				TextField field = (TextField)n;
        				if(field.getText().isBlank())
        					n.setStyle("-fx-border-color: RED");
        			}
        			else {
        				ComboBox comboBox = (ComboBox)n;
        				if(comboBox.getSelectionModel().getSelectedItem() == null)
        					n.setStyle("-fx-border-color: RED");
        			}
    			}
    			if(!"српски".equals(Main.trenutniJezik))
    				PrikazObavjestenja.prikaziUpozorenje("Nepotpun unos.");
    			else
    				PrikazObavjestenja.prikaziUpozorenje("Непотпун унос.");
    			return;
    		}
    	}
    	
    	List<ClanDomacinstva> spisakLica = new ArrayList<>();
    	
    	for(Entry<Integer, List<Node>> entry : poljaZaPopunjavanjeEntries) {
    		List<Node> polja = entry.getValue();
    		String ime = ((TextField)polja.get(0)).getText();
    		String prezime = ((TextField)polja.get(1)).getText();
    		String JMBG = ((TextField)polja.get(2)).getText();
    		String odnosPremaNosiocuDomacinstva = (String)((ComboBox)polja.get(3)).getSelectionModel().getSelectedItem();
    		int redniBrojPorodice = Integer.parseInt(((TextField)polja.get(4)).getText());
    		String polozajClanaUPorodici = (String)((ComboBox)polja.get(5)).getSelectionModel().getSelectedItem();
    		
    		ClanDomacinstva clanDomacinstva = new ClanDomacinstva(ime, prezime, JMBG, odnosPremaNosiocuDomacinstva,
    				redniBrojPorodice, polozajClanaUPorodici);
    		
    		spisakLica.add(clanDomacinstva);
    	}
    	
    	KontrolerFormeZaPopisivanjeDomacinstva.spisakLica = spisakLica;
    	
    	KontrolerFormeZaPopisivanjeDomacinstva.spisakLicaStage.close();
    }
    
    private void setDefaultColors() {
    	Set<Entry<Integer, List<Node>>> poljaZaPopunjavanjeEntries = poljaZaPopunjavanje.entrySet();
    	for(Entry<Integer, List<Node>> entry : poljaZaPopunjavanjeEntries) {
    		List<Node> polja = entry.getValue();
    		for(Node n : polja)
    			n.setStyle("-fx-border-color: TRANSPARENT");
    	}
    }
}
