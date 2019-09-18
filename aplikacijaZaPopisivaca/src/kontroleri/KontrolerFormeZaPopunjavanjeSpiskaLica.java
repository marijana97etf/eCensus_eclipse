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
import model.ClanDomacinstva;

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
    private ComboBox<String> polozajComboBox;
    
    private int row = 3;
    
    public KontrolerFormeZaPopunjavanjeSpiskaLica() {		
		KontrolerFormeZaPopisivanjeDomacinstva.spisakLicaStage.setOnShowing((event) -> init());
	}
    
    private void init() {
    	inicijalizujOdnosComboBox(odnosComboBox);
    	inicijalizujPolozajComboBox(polozajComboBox);
    	
    	poljaZaPopunjavanje = new HashMap<Integer, List<Node>>();
		List<Node> list = new ArrayList<>();
		list.add(imeTextField);
		list.add(prezimeTextField);
		list.add(JMBGTextField);
		list.add(odnosComboBox);
		list.add(redniBrojPorodiceTextField);
		list.add(polozajComboBox);
		poljaZaPopunjavanje.put(1, list);
    }
    
    private void inicijalizujOdnosComboBox(ComboBox comboBox) {
    	List<String> odnosi = new ArrayList<>();
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
        ObservableList observableList = FXCollections.observableList(odnosi);
        odnosComboBox.setItems(observableList);
    }
    
    private void inicijalizujPolozajComboBox(ComboBox comboBox) {
    	List<String> polozaji = new ArrayList<>();
        polozaji.add("Supruga/suprug");
        polozaji.add("Partnerka/partner u neformalnoj zajednici");
        polozaji.add("Zajedničko dijete");
        polozaji.add("Sama majka sa djetetom/djecom");
        polozaji.add("Sam otac sa djetetom/djecom");
        polozaji.add("Dijete koje živi samo sa jednim roditeljem");
        polozaji.add("Dijete samo supruge ili partnerke");
        polozaji.add("Dijete samo supruga ili partnera");
        polozaji.add("Ne pripada nijednoj od porodica");
        ObservableList observableList = FXCollections.observableList(polozaji);
        polozajComboBox.setItems(observableList);
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
        odnosPremaNosiocuDomacinstvaComboBox.setPromptText("Odaberi tip odnosa...");
        TextField redniBrojPorodiceTextField = new TextField();
        ComboBox<String> polozajUPorodiciComboBox = new ComboBox<>();
        polozajUPorodiciComboBox.setMinWidth(200);
        polozajUPorodiciComboBox.setPrefWidth(200);
        polozajUPorodiciComboBox.setMaxWidth(200);
        polozajUPorodiciComboBox.setPromptText("Odaberi položaj...");

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
    			prikaziUpozorenje("Nepotpun unos.");
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
    }
    
    private void setDefaultColors() {
    	Set<Entry<Integer, List<Node>>> poljaZaPopunjavanjeEntries = poljaZaPopunjavanje.entrySet();
    	for(Entry<Integer, List<Node>> entry : poljaZaPopunjavanjeEntries) {
    		List<Node> polja = entry.getValue();
    		int brojPraznihPolja = 0;
    		for(Node n : polja)
    			n.setStyle("-fx-border-color: TRANSPARENT");
    	}
    }
    
    private void prikaziUpozorenje(String poruka){
        Alert userNotSelectedAlert = new Alert(Alert.AlertType.ERROR);
        userNotSelectedAlert.setTitle("Greška");
        userNotSelectedAlert.setHeaderText("Greška!");
        userNotSelectedAlert.setContentText(poruka);
        userNotSelectedAlert.showAndWait();
    }
}
