package kontroleri;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.PopisnicaZaDomacinstvo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KontrolerFormeZaPopisivanjeDomacinstva {
    @FXML
    private TextField obrazacTextField;
    @FXML
    private TextField entitetTextField;
    @FXML
    private TextField opstinaTextField1;
    @FXML
    private TextField opstinaTextField2;
    @FXML
    private TextField opstinaTextField3;
    @FXML
    private TextField opstinaTextField4;
    @FXML
    private TextField opstinaTextField5;
    @FXML
    private TextField popisniKrugTextField1;
    @FXML
    private TextField popisniKrugTextField2;
    @FXML
    private TextField popisniKrugTextField3;
    @FXML
    private TextField stanTextField1;
    @FXML
    private TextField stanTextField2;
    @FXML
    private TextField stanTextField3;
    @FXML
    private TextField domacinstvoTextField1;
    @FXML
    private TextField domacinstvoTextField2;
    @FXML
    private TextField domacinstvoTextField3;
    @FXML
    private TextField zgradaTextField1;
    @FXML
    private TextField zgradaTextField2;
    @FXML
    private TextField zgradaTextField3;
    @FXML
    private TextField ulicaTextField;
    @FXML
    private TextField kucniBrojTextField;
    @FXML
    private TextField dodatakTextField;
    @FXML
    private TextField ulazTextField;
    @FXML
    private TextField brojStanaTextField;
    @FXML
    private TextField idBrojTextField;
    @FXML
    private CheckBox pitanje26CheckBox1;
    @FXML
    private CheckBox pitanje26CheckBox2;
    @FXML
    private CheckBox pitanje26CheckBox3;
    @FXML
    private CheckBox pitanje26CheckBox4;
    @FXML
    private CheckBox pitanje26CheckBox5;
    @FXML
    private CheckBox pitanje26CheckBox6;
    @FXML
    private TextField ukupnoZemljisteDunumTextField;
    @FXML
    private TextField poljoprivrednoZemljisteDunumTextField;
    @FXML
    private TextField ZemljisteSumaDunumTextField;
    @FXML
    private TextField ostaloZemljisteDunumTextField;
    @FXML
    private TextField ostaloZemljisteKvadratniMetarTextField;
    @FXML
    private TextField zemljisteSumaKvadratniMetarTextField;
    @FXML
    private TextField zemljisteSumaDunumTextField;
    @FXML
    private TextField poljoprivrednoZemljisteKvadratniMetarTextField;
    @FXML
    private TextField ukupnoZemljisteKvadratniMetarTextField;
    @FXML
    private TextField brojLicaUStanuTextField;
    @FXML
    private TextField brojClanovaDomacinstvaTextField;
    @FXML
    private TextField brojDomacinstavaUStanuTextField;
    @FXML
    private TextField pitanje4TextField;
    @FXML
    private TextField pitanje6TextField;
    @FXML
    private TextField pitanje7TextField;
    @FXML
    private RadioButton pitanje8Button1;
    @FXML
    private RadioButton pitanje8Button2;
    @FXML
    private TextField pitanje8TextField;
    @FXML
    private TextField pitanje17TextField;
    @FXML
    private RadioButton pitanje29Button1;
    @FXML
    private RadioButton pitanje29Button2;
    @FXML
    private TextField vinogradiDunumTextField;
    @FXML
    private TextField ribnjaciDunumTextField;
    @FXML
    private TextField gljivarniciDunumTextField;
    @FXML
    private TextField vocnaciDunumTextField;
    @FXML
    private TextField oraniceDunumTextField;
    @FXML
    private TextField livadeDunumTextField;
    @FXML
    private TextField rasadniciDunumTextField;
    @FXML
    private TextField ribnjaciMetarTextField;
    @FXML
    private TextField livadeMetarTextField;
    @FXML
    private TextField rasadniciMetarTextField;
    @FXML
    private TextField oraniceMetarTextField;
    @FXML
    private TextField vocnaciMetarTextField;
    @FXML
    private TextField gljivarniciMetarTextField;
    @FXML
    private TextField vinogradiMetarTextField;
    @FXML
    private TextField ovceTextField;
    @FXML
    private TextField kosniceTextField;
    @FXML
    private TextField peradTextField;
    @FXML
    private TextField konjiTextField;
    @FXML
    private TextField kozeTextField;
    @FXML
    private TextField svinjeTextField;
    @FXML
    private TextField kraveTextField;
    @FXML
    private TextField govedaTextField;
    @FXML
    private RadioButton pitanje33Button1;
    @FXML
    private RadioButton pitanje33Button2;
    @FXML
    private TextField pitanje33TextField;
    @FXML
    private ToggleGroup grupa1;
    @FXML
    private ToggleGroup grupa2;
    @FXML
    private ToggleGroup grupa3;
    @FXML
    private ToggleGroup grupa4;
    @FXML
    private ToggleGroup grupa5;
    @FXML
    private ToggleGroup grupa6;
    @FXML
    private ToggleGroup grupa7;
    @FXML
    private ToggleGroup grupa8;
    @FXML
    private ToggleGroup grupa9;
    @FXML
    private ToggleGroup grupa10;
    @FXML
    private ToggleGroup grupa11;
    @FXML
    private ToggleGroup grupa12;
    @FXML
    private ToggleGroup grupa13;
    @FXML
    private ToggleGroup grupa14;
    @FXML
    private ToggleGroup grupa15;
    @FXML
    private ToggleGroup grupa16;
    @FXML
    private ToggleGroup grupa17;
    @FXML
    private ToggleGroup grupa18;
    @FXML
    private ToggleGroup grupa19;
    @FXML
    private ToggleGroup grupa20;
    @FXML
    private ToggleGroup grupa21;
    @FXML
    private ToggleGroup grupa22;
    @FXML
    private ToggleGroup grupa23;
    @FXML
    private ToggleGroup grupa24;
    @FXML
    private ToggleGroup grupa25;
    @FXML
    private ToggleGroup grupa26;

    public KontrolerFormeZaPopisivanjeDomacinstva(){
        KontrolerFormeZaRadPopisivaca.popisDomacinstvaStage.setOnShowing((event) -> inicijalizujKomponente());
    }

    private void inicijalizujKomponente(){
        grupa1.getToggles().forEach(toggle -> {
            RadioButton button = (RadioButton)toggle;
            button.setOnAction((event) -> {
                if("Stan (u zgradi/kući)".equals(button.getText())){
                    grupa2.getToggles().forEach(toggle1 -> {
                        Node node = (Node) toggle1 ;
                        node.setDisable(true);
                    });
                    enableDisableComponentsForQuestion3To24(false);
                }
                else if("Kolektivni (institucionalni) stan".equals(button.getText())){
                    grupa2.getToggles().forEach(toggle1 -> {
                        Node node = (Node) toggle1 ;
                        node.setDisable(false);
                    });
                    enableDisableComponentsForQuestion3To24(false);
                }
                else{
                    grupa2.getToggles().forEach(toggle1 -> {
                        Node node = (Node) toggle1 ;
                        node.setDisable(true);
                    });
                    enableDisableComponentsForQuestion3To24(true);
                }
            });
        });

        grupa2.getToggles().forEach(toggle -> {
            RadioButton button = (RadioButton) toggle;
            button.setOnAction((event) -> enableDisableComponentsForQuestion3To24(true));
        });

        grupa3.getToggles().forEach(toggle -> {
            RadioButton button = (RadioButton) toggle;
            button.setOnAction((event) -> {
                if("Samo za obavljanje djelatnosti".equals(button.getText())){
                    grupa4.getToggles().forEach(toggle1 -> {
                        Node node = (Node) toggle1 ;
                        node.setDisable(false);
                    });
                }
                else{
                    grupa4.getToggles().forEach(toggle1 -> {
                        Node node = (Node) toggle1 ;
                        node.setDisable(true);
                    });
                }
            });
        });

        pitanje8Button1.setOnAction((event) -> pitanje8TextField.setDisable(false));
        pitanje8Button2.setOnAction((event) -> pitanje8TextField.setDisable(true));

        pitanje29Button1.setOnAction((event) -> enableDisableComponentsForQuestion30To33(false));
        pitanje29Button2.setOnAction((event) -> enableDisableComponentsForQuestion30To33(true));

        pitanje33Button1.setOnAction((event) -> pitanje33TextField.setDisable(true));
        pitanje33Button2.setOnAction((event) -> pitanje33TextField.setDisable(false));
    }

    public void posaljiPopisnicuButtonAction() {
        setDefaultColors();

        int idObrasca;
        int idEntiteta;
        int idOpstine;
        int idPopisnogKruga;
        int idStana;
        int idDomacinstva;
        int idZgrade;
        String ulica;
        String kucniBroj;
        String dodatak;
        String ulaz;
        String brojStana;
        String idBroj;
        Map<Integer, List<String>> odgovoriNaPitanja = new HashMap<>();
        for(int i=1; i<=33; ++i)
            odgovoriNaPitanja.put(i,new ArrayList<>());

        try{
            idObrasca = Integer.parseInt(obrazacTextField.getText());
            if(idObrasca < 0 || idObrasca>9) {
                obrazacTextField.setStyle("-fx-border-color: RED");
                prikaziUpozorenje("U polje za obrazac morate unijeti jednocifren broj.");
                return;
            }
        }
        catch(NumberFormatException e){
            obrazacTextField.setStyle("-fx-border-color: RED");
            prikaziUpozorenje("U polje za obrazac morate unijeti broj.");
            return;
        }
        try{
            idEntiteta = Integer.parseInt(entitetTextField.getText());
            if(idEntiteta < 0 || idEntiteta>9) {
                entitetTextField.setStyle("-fx-border-color: RED");
                prikaziUpozorenje("U polje za entitet morate unijeti jednocifren broj.");
                return;
            }
        }
        catch(NumberFormatException e){
            entitetTextField.setStyle("-fx-border-color: RED");
            prikaziUpozorenje("U polje za entitet morate unijeti broj.");
            return;
        }
        try{
            idOpstine = Integer.parseInt(opstinaTextField1.getText() + opstinaTextField2.getText() + opstinaTextField3.getText()
                    + opstinaTextField4.getText() + opstinaTextField5.getText());
            if(idOpstine < 0 || idOpstine > 99999) {
                opstinaTextField1.setStyle("-fx-border-color: RED");
                opstinaTextField2.setStyle("-fx-border-color: RED");
                opstinaTextField3.setStyle("-fx-border-color: RED");
                opstinaTextField4.setStyle("-fx-border-color: RED");
                opstinaTextField5.setStyle("-fx-border-color: RED");
                prikaziUpozorenje("U svako polje za opštinu morate unijeti jednocifren broj.");
                return;
            }
        }
        catch(NumberFormatException e){
            opstinaTextField1.setStyle("-fx-border-color: RED");
            opstinaTextField2.setStyle("-fx-border-color: RED");
            opstinaTextField3.setStyle("-fx-border-color: RED");
            opstinaTextField4.setStyle("-fx-border-color: RED");
            opstinaTextField5.setStyle("-fx-border-color: RED");
            prikaziUpozorenje("U polja za opštinu morate unijeti brojeve.");
            return;
        }
        try{
            idPopisnogKruga = Integer.parseInt(popisniKrugTextField1.getText() + popisniKrugTextField2.getText() +
                    popisniKrugTextField3.getText());
            if(idPopisnogKruga < 0 || idPopisnogKruga > 999) {
                popisniKrugTextField1.setStyle("-fx-border-color: RED");
                popisniKrugTextField2.setStyle("-fx-border-color: RED");
                popisniKrugTextField3.setStyle("-fx-border-color: RED");
                prikaziUpozorenje("U svako polje za popisni krug morate unijeti jednocifren broj.");
                return;
            }
        }
        catch(NumberFormatException e){
            popisniKrugTextField1.setStyle("-fx-border-color: RED");
            popisniKrugTextField2.setStyle("-fx-border-color: RED");
            popisniKrugTextField3.setStyle("-fx-border-color: RED");
            prikaziUpozorenje("U polja za popisni krug morate unijeti brojeve.");
            return;
        }
        try{
            idStana = Integer.parseInt(stanTextField1.getText() + stanTextField2.getText() + stanTextField3.getText());
            if(idStana < 0 || idStana > 999) {
                stanTextField1.setStyle("-fx-border-color: RED");
                stanTextField2.setStyle("-fx-border-color: RED");
                stanTextField3.setStyle("-fx-border-color: RED");
                prikaziUpozorenje("U svako polje za stan morate unijeti jednocifren broj.");
                return;
            }
        }
        catch(NumberFormatException e){
            stanTextField1.setStyle("-fx-border-color: RED");
            stanTextField2.setStyle("-fx-border-color: RED");
            stanTextField3.setStyle("-fx-border-color: RED");
            prikaziUpozorenje("U polja za stan morate unijeti brojeve.");
            return;
        }
        try{
            idDomacinstva = Integer.parseInt(domacinstvoTextField1.getText() + domacinstvoTextField2.getText() +
                    domacinstvoTextField3.getText());
            if(idDomacinstva < 0 || idDomacinstva > 999) {
                domacinstvoTextField1.setStyle("-fx-border-color: RED");
                domacinstvoTextField2.setStyle("-fx-border-color: RED");
                domacinstvoTextField3.setStyle("-fx-border-color: RED");
                prikaziUpozorenje("U svako polje za domaćinstvo morate unijeti jednocifren broj.");
                return;
            }
        }
        catch(NumberFormatException e){
            domacinstvoTextField1.setStyle("-fx-border-color: RED");
            domacinstvoTextField2.setStyle("-fx-border-color: RED");
            domacinstvoTextField3.setStyle("-fx-border-color: RED");
            prikaziUpozorenje("U polja za domaćinstvo morate unijeti brojeve.");
            return;
        }
        try{
            idZgrade = Integer.parseInt(zgradaTextField1.getText() + zgradaTextField2.getText() + zgradaTextField3.getText());
            if(idZgrade < 0 || idZgrade > 999) {
                zgradaTextField1.setStyle("-fx-border-color: RED");
                zgradaTextField2.setStyle("-fx-border-color: RED");
                zgradaTextField3.setStyle("-fx-border-color: RED");
                prikaziUpozorenje("U svako polje za zgradu morate unijeti jednocifren broj.");
                return;
            }
        }
        catch(NumberFormatException e){
            zgradaTextField1.setStyle("-fx-border-color: RED");
            zgradaTextField2.setStyle("-fx-border-color: RED");
            zgradaTextField3.setStyle("-fx-border-color: RED");
            prikaziUpozorenje("U polja za zgradu morate unijeti brojeve.");
            return;
        }

        ulica = ulicaTextField.getText();
        if(ulica.isEmpty()) {
            ulicaTextField.setStyle("-fx-border-color: RED");
            prikaziUpozorenje("Morate unijeti ulicu domaćinstva koje se popisuje.");
            return;
        }

        kucniBroj = kucniBrojTextField.getText();
        if(kucniBroj.isEmpty()){
            kucniBrojTextField.setStyle("-fx-border-color: RED");
            prikaziUpozorenje("Morate unijeti kućni broj domaćinstva koje se popisuje.");
            return;
        }

        dodatak = dodatakTextField.getText();
        if(dodatak.isEmpty()){
            dodatakTextField.setStyle("-fx-border-color: RED");
            prikaziUpozorenje("Morate unijeti dodatak domaćinstva koje se popisuje.");
            return;
        }

        ulaz = ulazTextField.getText();
        if(ulaz.isEmpty()){
            ulazTextField.setStyle("-fx-border-color: RED");
            prikaziUpozorenje("Morate unijeti ulaz domaćinstva koje se popisuje.");
            return;
        }

        brojStana = brojStanaTextField.getText();
        if(brojStana.isEmpty()){
            brojStanaTextField.setStyle("-fx-border-color: RED");
            prikaziUpozorenje("Morate unijeti broj stana domaćinstva koje se popisuje.");
            return;
        }

        idBroj = idBrojTextField.getText();
        if(idBroj.isEmpty()){
            idBrojTextField.setStyle("-fx-border-color: RED");
            prikaziUpozorenje("Morate unijeti ID-broj domaćinstva koje se popisuje.");
            return;
        }

        PopisnicaZaDomacinstvo popisnica = new PopisnicaZaDomacinstvo(idObrasca, idEntiteta, idOpstine, idPopisnogKruga,
                idStana, idDomacinstva, idZgrade, ulica, kucniBroj, dodatak, ulaz, brojStana, idBroj);

        String odgovor1 = getOdgovor(grupa1, 1);
        if (odgovor1.isEmpty())
            return;
        else
            odgovoriNaPitanja.get(1).add(odgovor1);

        if(isToggleGroupEnabled(grupa2)){
            String odgovor2 = getOdgovor(grupa2, 2);
            if (odgovor2.isEmpty())
                return;
            else
                odgovoriNaPitanja.get(2).add(odgovor2);
        }

        if(isToggleGroupEnabled(grupa3)){
            RadioButton odgovor3Button = (RadioButton)grupa3.getSelectedToggle();
            if(odgovor3Button == null){
                grupa3.getToggles().forEach(toggle -> {
                    RadioButton button = (RadioButton)toggle;
                    button.setStyle("-fx-border-color: RED");
                });
                prikaziUpozorenje("Morate odgovoriti na 3. pitanje.");
                return;
            }
            else {
                odgovoriNaPitanja.get(3).add(odgovor3Button.getText());
                if(isToggleGroupEnabled(grupa4)){
                    RadioButton odgovor3Button1 = (RadioButton)grupa4.getSelectedToggle();
                    if(odgovor3Button1 == null){
                        grupa4.getToggles().forEach(toggle -> {
                            RadioButton button = (RadioButton)toggle;
                            button.setStyle("-fx-border-color: RED");
                        });
                        prikaziUpozorenje("Nepotpun odgovor na 3. pitanje.");
                        return;
                    }
                    else
                        odgovoriNaPitanja.get(3).add(odgovor3Button1.getText());
                }
            }
        }

        if(!pitanje4TextField.isDisabled()){
            String odgovor4 = pitanje4TextField.getText();
            if(odgovor4.isEmpty()){
                pitanje4TextField.setStyle("-fx-border-color: RED");
                prikaziUpozorenje("Morate odgovoriti na 4. pitanje.");
                return;
            }
            else
                odgovoriNaPitanja.get(4).add(odgovor4);
        }

        if(isToggleGroupEnabled(grupa5)){
            String odgovor5 = getOdgovor(grupa5, 5);
            if (odgovor5.isEmpty())
                return;
            else
                odgovoriNaPitanja.get(5).add(odgovor5);
        }

        if(!pitanje6TextField.isDisabled()){
            String odgovor6 = pitanje6TextField.getText();
            if(odgovor6.isEmpty()){
                pitanje6TextField.setStyle("-fx-border-color: RED");
                prikaziUpozorenje("Morate odgovoriti na 6. pitanje.");
                return;
            }
            else
                odgovoriNaPitanja.get(6).add(odgovor6);
        }

        if(!pitanje7TextField.isDisabled()){
            String odgovor7 = pitanje7TextField.getText();
            if(odgovor7.isEmpty()){
                pitanje7TextField.setStyle("-fx-border-color: RED");
                prikaziUpozorenje("Morate odgovoriti na 7. pitanje.");
                return;
            }
            else
                odgovoriNaPitanja.get(7).add(odgovor7);
        }

        if(isToggleGroupEnabled(grupa6)){
            RadioButton odgovor8Button = (RadioButton)grupa6.getSelectedToggle();
            if(odgovor8Button == null){
                grupa6.getToggles().forEach(toggle -> {
                    RadioButton button = (RadioButton)toggle;
                    button.setStyle("-fx-border-color: RED");
                });
                prikaziUpozorenje("Morate odgovoriti na 8. pitanje.");
                return;
            }
            else {
                odgovoriNaPitanja.get(8).add(odgovor8Button.getText());
                if(!pitanje8TextField.isDisabled()){
                    String odgovor8Text = pitanje8TextField.getText();
                    if(odgovor8Text.isEmpty()){
                        pitanje8TextField.setStyle("-fx-border-color: RED");
                        prikaziUpozorenje("Nepotpun odgovor na 8. pitanje.");
                        return;
                    }
                    else
                        odgovoriNaPitanja.get(8).add(odgovor8Text);
                }
            }
        }

        if(isToggleGroupEnabled(grupa7)){
            String odgovor9 = getOdgovor(grupa17, 9);
            if (odgovor9.isEmpty())
                return;
            else
                odgovoriNaPitanja.get(9).add(odgovor9);
        }

        if(isToggleGroupEnabled(grupa8)){
            String odgovor10 = getOdgovor(grupa18, 10);
            if (odgovor10.isEmpty())
                return;
            else
                odgovoriNaPitanja.get(10).add(odgovor10);
        }

        if(isToggleGroupEnabled(grupa9)){
            String odgovor11 = getOdgovor(grupa19, 11);
            if (odgovor11.isEmpty())
                return;
            else
                odgovoriNaPitanja.get(11).add(odgovor11);
        }

        if(isToggleGroupEnabled(grupa10)){
            String odgovor12 = getOdgovor(grupa10, 12);
            if (odgovor12.isEmpty())
                return;
            else
                odgovoriNaPitanja.get(12).add(odgovor12);
        }

        if(isToggleGroupEnabled(grupa11)){
            String odgovor13 = getOdgovor(grupa11, 13);
            if (odgovor13.isEmpty())
                return;
            else
                odgovoriNaPitanja.get(13).add(odgovor13);
        }

        if(isToggleGroupEnabled(grupa12)){
            String odgovor14 = getOdgovor(grupa12, 14);
            if (odgovor14.isEmpty())
                return;
            else
                odgovoriNaPitanja.get(14).add(odgovor14);
        }

        if(isToggleGroupEnabled(grupa13)){
            String odgovor15 = getOdgovor(grupa13, 15);
            if (odgovor15.isEmpty())
                return;
            else
                odgovoriNaPitanja.get(15).add(odgovor15);
        }

        if(isToggleGroupEnabled(grupa14)) {
	        String odgovor16 = getOdgovor(grupa14, 16);
	        if(odgovor16.isEmpty())
	            return;
	        else
	            odgovoriNaPitanja.get(16).add(odgovor16);
        }

        if(!pitanje17TextField.isDisabled()){
            String odgovor17 = pitanje17TextField.getText();
            if(odgovor17.isEmpty()){
                return;
            }
            else
                odgovoriNaPitanja.get(17).add(odgovor17);
        }

        if(isToggleGroupEnabled(grupa15)) {
            String odgovor18 = getOdgovor(grupa15, 18);
            if (odgovor18.isEmpty())
                return;
            else
                odgovoriNaPitanja.get(18).add(odgovor18);
        }

        if(isToggleGroupEnabled(grupa16)) {
            String odgovor19 = getOdgovor(grupa16, 19);
            if (odgovor19.isEmpty())
                return;
            else
                odgovoriNaPitanja.get(19).add(odgovor19);
        }

        if(isToggleGroupEnabled(grupa17)) {
            String odgovor20 = getOdgovor(grupa17, 20);
            if (odgovor20.isEmpty())
                return;
            else
                odgovoriNaPitanja.get(20).add(odgovor20);
        }

        if(isToggleGroupEnabled(grupa18)) {
            String odgovor21 = getOdgovor(grupa18, 21);
            if (odgovor21.isEmpty())
                return;
            else
                odgovoriNaPitanja.get(21).add(odgovor21);
        }

        if(isToggleGroupEnabled(grupa19)) {
            String odgovor22 = getOdgovor(grupa19, 22);
            if (odgovor22.isEmpty())
                return;
            else
                odgovoriNaPitanja.get(22).add(odgovor22);
        }

        if(isToggleGroupEnabled(grupa20)) {
            String odgovor23 = getOdgovor(grupa20, 23);
            if (odgovor23.isEmpty())
                return;
            else
                odgovoriNaPitanja.get(23).add(odgovor23);
        }

        String odgovor24 = getOdgovor(grupa21, 24);
        if (odgovor24.isEmpty())
            return;
        else
            odgovoriNaPitanja.get(24).add(odgovor24);

        String odgovor25 = getOdgovor(grupa22, 25);
        if (odgovor25.isEmpty())
            return;
        else
            odgovoriNaPitanja.get(25).add(odgovor25);

        if(pitanje26CheckBox1.isSelected())
            odgovoriNaPitanja.get(26).add(pitanje26CheckBox1.getText());
        if(pitanje26CheckBox2.isSelected())
            odgovoriNaPitanja.get(26).add(pitanje26CheckBox2.getText());
        if(pitanje26CheckBox3.isSelected())
            odgovoriNaPitanja.get(26).add(pitanje26CheckBox3.getText());
        if(pitanje26CheckBox4.isSelected())
            odgovoriNaPitanja.get(26).add(pitanje26CheckBox4.getText());
        if(pitanje26CheckBox5.isSelected())
            odgovoriNaPitanja.get(26).add(pitanje26CheckBox5.getText());
        if(pitanje26CheckBox6.isSelected())
            odgovoriNaPitanja.get(26).add(pitanje26CheckBox6.getText());
        if(odgovoriNaPitanja.get(26).isEmpty()){
            pitanje26CheckBox1.setStyle("-fx-border-color: RED");
            pitanje26CheckBox2.setStyle("-fx-border-color: RED");
            pitanje26CheckBox3.setStyle("-fx-border-color: RED");
            pitanje26CheckBox4.setStyle("-fx-border-color: RED");
            pitanje26CheckBox5.setStyle("-fx-border-color: RED");
            pitanje26CheckBox6.setStyle("-fx-border-color: RED");
            prikaziUpozorenje("Morate odgovoriti na 26. pitanje.");
            return;
        }

        String odgovor27 = getOdgovor(grupa23, 27);
        if (odgovor27.isEmpty())
            return;
        else
            odgovoriNaPitanja.get(27).add(odgovor27);

        String odgovor28UkupnoDunum = getKolicinuPoljoprivrednogDobra(ukupnoZemljisteDunumTextField, 28);
        if(odgovor28UkupnoDunum.isEmpty())
            return;
        else
            odgovoriNaPitanja.get(28).add(odgovor28UkupnoDunum);

        String odgovor28UkupnoMetar = getKolicinuPoljoprivrednogDobra(ukupnoZemljisteKvadratniMetarTextField, 28);
        if(odgovor28UkupnoMetar.isEmpty())
            return;
        else
            odgovoriNaPitanja.get(28).add(odgovor28UkupnoMetar);

        String odgovor28PoljoprivrednoZemljisteDunum = getKolicinuPoljoprivrednogDobra(poljoprivrednoZemljisteDunumTextField, 28);
        if(odgovor28PoljoprivrednoZemljisteDunum.isEmpty())
            return;
        else
            odgovoriNaPitanja.get(28).add(odgovor28PoljoprivrednoZemljisteDunum);

        String odgovor28PoljoprivrednoZemljisteMetar = getKolicinuPoljoprivrednogDobra(poljoprivrednoZemljisteKvadratniMetarTextField, 28);
        if(odgovor28PoljoprivrednoZemljisteMetar.isEmpty())
            return;
        else
            odgovoriNaPitanja.get(28).add(odgovor28PoljoprivrednoZemljisteMetar);

        String odgovor28ZemljisteSumaDunum = getKolicinuPoljoprivrednogDobra(zemljisteSumaDunumTextField, 28);
        if(odgovor28ZemljisteSumaDunum.isEmpty())
            return;
        else
            odgovoriNaPitanja.get(28).add(odgovor28ZemljisteSumaDunum);

        String odgovor28ZemljisteSumaMetar = getKolicinuPoljoprivrednogDobra(zemljisteSumaKvadratniMetarTextField, 28);
        if(odgovor28ZemljisteSumaMetar.isEmpty())
            return;
        else
            odgovoriNaPitanja.get(28).add(odgovor28ZemljisteSumaMetar);

        String odgovor28OstaloZemljisteDunum = getKolicinuPoljoprivrednogDobra(ostaloZemljisteDunumTextField, 28);
        if(odgovor28OstaloZemljisteDunum.isEmpty())
            return;
        else
            odgovoriNaPitanja.get(28).add(odgovor28OstaloZemljisteDunum);


        String odgovor28OstaloZemljisteMetar = getKolicinuPoljoprivrednogDobra(ostaloZemljisteKvadratniMetarTextField, 28);
        if(odgovor28OstaloZemljisteMetar.isEmpty())
            return;
        else
            odgovoriNaPitanja.get(28).add(odgovor28OstaloZemljisteMetar);

        String odgovor29 = getOdgovor(grupa24, 29);
        if (odgovor29.isEmpty())
            return;
        else
            odgovoriNaPitanja.get(29).add(odgovor29);

        if(!oraniceDunumTextField.isDisabled()){
            String odgovor30OraniceDunum = getKolicinuPoljoprivrednogDobra(oraniceDunumTextField, 30);
            if(odgovor30OraniceDunum.isEmpty())
                return;
            else
                odgovoriNaPitanja.get(30).add(odgovor30OraniceDunum);

            String odgovor30OraniceMetar = getKolicinuPoljoprivrednogDobra(oraniceMetarTextField, 30);
            if(odgovor30OraniceMetar.isEmpty())
                return;
            else
                odgovoriNaPitanja.get(30).add(odgovor30OraniceMetar);

            String odgovor30VocnjaciDunum = getKolicinuPoljoprivrednogDobra(vocnaciDunumTextField, 30);
            if(odgovor30VocnjaciDunum.isEmpty())
                return;
            else
                odgovoriNaPitanja.get(30).add(odgovor30VocnjaciDunum);

            String odgovor30VocnjaciMetar = getKolicinuPoljoprivrednogDobra(vocnaciMetarTextField, 30);
            if(odgovor30VocnjaciMetar.isEmpty())
                return;
            else
                odgovoriNaPitanja.get(30).add(odgovor30VocnjaciMetar);

            String odgovor30VinogradiDunum = getKolicinuPoljoprivrednogDobra(vinogradiDunumTextField, 30);
            if(odgovor30VinogradiDunum.isEmpty())
                return;
            else
                odgovoriNaPitanja.get(30).add(odgovor30VinogradiDunum);

            String odgovor30VinogradiMetar = getKolicinuPoljoprivrednogDobra(vinogradiMetarTextField, 30);
            if(odgovor30VinogradiMetar.isEmpty())
                return;
            else
                odgovoriNaPitanja.get(30).add(odgovor30VinogradiMetar);

            String odgovor30RasadniciDunum = getKolicinuPoljoprivrednogDobra(rasadniciDunumTextField, 30);
            if(odgovor30RasadniciDunum.isEmpty())
                return;
            else
                odgovoriNaPitanja.get(30).add(odgovor30RasadniciDunum);

            String odgovor30RasadniciMetar = getKolicinuPoljoprivrednogDobra(rasadniciMetarTextField, 30);
            if(odgovor30RasadniciMetar.isEmpty())
                return;
            else
                odgovoriNaPitanja.get(30).add(odgovor30RasadniciMetar);

            String odgovor30LivadeDunum = getKolicinuPoljoprivrednogDobra(livadeDunumTextField, 30);
            if(odgovor30LivadeDunum.isEmpty())
                return;
            else
                odgovoriNaPitanja.get(30).add(odgovor30LivadeDunum);

            String odgovor30LivadeMetar = getKolicinuPoljoprivrednogDobra(livadeMetarTextField, 30);
            if(odgovor30LivadeMetar.isEmpty())
                return;
            else
                odgovoriNaPitanja.get(30).add(odgovor30LivadeMetar);

            String odgovor30RibnjaciDunum = getKolicinuPoljoprivrednogDobra(ribnjaciDunumTextField, 30);
            if(odgovor30RibnjaciDunum.isEmpty())
                return;
            else
                odgovoriNaPitanja.get(30).add(odgovor30RibnjaciDunum);

            String odgovor30RibnjaciMetar = getKolicinuPoljoprivrednogDobra(ribnjaciMetarTextField, 30);
            if(odgovor30RibnjaciMetar.isEmpty())
                return;
            else
                odgovoriNaPitanja.get(30).add(odgovor30RibnjaciMetar);

            String odgovor30GljivarniciDunum = getKolicinuPoljoprivrednogDobra(gljivarniciDunumTextField, 30);
            if(odgovor30GljivarniciDunum.isEmpty())
                return;
            else
                odgovoriNaPitanja.get(30).add(odgovor30GljivarniciDunum);

            String odgovor30GljivarniciMetar = getKolicinuPoljoprivrednogDobra(gljivarniciMetarTextField, 30);
            if(odgovor30GljivarniciMetar.isEmpty())
                return;
            else
                odgovoriNaPitanja.get(30).add(odgovor30GljivarniciMetar);
        }

        if(!govedaTextField.isDisabled()){
            String odgovor31Goveda = getKolicinuPoljoprivrednogDobra(govedaTextField, 31);
            if(odgovor31Goveda.isEmpty())
                return;
            else
                odgovoriNaPitanja.get(31).add(odgovor31Goveda);

            String odgovor31Krave = getKolicinuPoljoprivrednogDobra(kraveTextField, 31);
            if(odgovor31Krave.isEmpty())
                return;
            else
                odgovoriNaPitanja.get(31).add(odgovor31Krave);

            String odgovor31Svinje = getKolicinuPoljoprivrednogDobra(svinjeTextField, 31);
            if(odgovor31Svinje.isEmpty())
                return;
            else
                odgovoriNaPitanja.get(31).add(odgovor31Svinje);

            String odgovor31Ovce = getKolicinuPoljoprivrednogDobra(ovceTextField, 31);
            if(odgovor31Ovce.isEmpty())
                return;
            else
                odgovoriNaPitanja.get(31).add(odgovor31Ovce);

            String odgovor31Koze = getKolicinuPoljoprivrednogDobra(kozeTextField, 31);
            if(odgovor31Koze.isEmpty())
                return;
            else
                odgovoriNaPitanja.get(31).add(odgovor31Koze);

            String odgovor31Konji = getKolicinuPoljoprivrednogDobra(konjiTextField, 31);
            if(odgovor31Konji.isEmpty())
                return;
            else
                odgovoriNaPitanja.get(31).add(odgovor31Konji);

            String odgovor31Perad = getKolicinuPoljoprivrednogDobra(peradTextField, 31);
            if(odgovor31Perad.isEmpty())
                return;
            else
                odgovoriNaPitanja.get(31).add(odgovor31Perad);

            String odgovor31Kosnice = getKolicinuPoljoprivrednogDobra(kosniceTextField, 31);
            if(odgovor31Kosnice.isEmpty())
                return;
            else
                odgovoriNaPitanja.get(31).add(odgovor31Kosnice);
        }

        if(isToggleGroupEnabled(grupa25)) {
            String odgovor32 = getOdgovor(grupa25, 32);
            if (odgovor32.isEmpty())
                return;
            else
                odgovoriNaPitanja.get(32).add(odgovor32);
        }

        if(isToggleGroupEnabled(grupa26)){
            RadioButton odgovor33Button = (RadioButton)grupa26.getSelectedToggle();
            if(odgovor33Button == null){
                grupa26.getToggles().forEach(toggle -> {
                    RadioButton button = (RadioButton)toggle;
                    button.setStyle("-fx-border-color: RED");
                });
                prikaziUpozorenje("Morate odgovoriti na 33. pitanje.");
                return;
            }
            else {
                odgovoriNaPitanja.get(33).add(odgovor33Button.getText());
                if(!pitanje33TextField.isDisabled()){
                    String odgovor33Text = pitanje33TextField.getText();
                    if(odgovor33Text.isEmpty()){
                        pitanje33TextField.setStyle("-fx-border-color: RED");
                        prikaziUpozorenje("Nepotpun odgovor na 33. pitanje.");
                        return;
                    }
                    else
                        odgovoriNaPitanja.get(33).add(odgovor33Text);
                }
            }
        }

        String brojClanovaDomacinstvaString = brojClanovaDomacinstvaTextField.getText();
        if(brojClanovaDomacinstvaString.isEmpty()){
            brojClanovaDomacinstvaTextField.setStyle("-fx-border-color: RED");
            prikaziUpozorenje("Morate unijeti broj Članova domaćinstva.");
            return;
        }
        else{
            try{
                int brojClanovaDomacinstva = Integer.parseInt(brojClanovaDomacinstvaString);
                popisnica.setBrojClanovaDomacinstva(brojClanovaDomacinstva);
            }
            catch(NumberFormatException e){
                brojClanovaDomacinstvaTextField.setStyle("-fx-border-color: RED");
                prikaziUpozorenje("U polje za broj Članova domaćinstva morate unijeti cijeli broj.");
                return;
            }
        }

        String brojLicaUStanuString = brojLicaUStanuTextField.getText();
        if(brojLicaUStanuString.isEmpty()){
            brojLicaUStanuTextField.setStyle("-fx-border-color: RED");
            prikaziUpozorenje("Morate unijeti broj lica u stanu.");
            return;
        }
        else{
            try{
                int brojLicaUStanu = Integer.parseInt(brojLicaUStanuString);
                popisnica.setBrojLicaUStanu(brojLicaUStanu);
            }
            catch(NumberFormatException e){
                brojLicaUStanuTextField.setStyle("-fx-border-color: RED");
                prikaziUpozorenje("U polje za broj lica u stanu morate unijeti cijeli broj.");
                return;
            }
        }

        String brojDomacinstavaUStanuString = brojDomacinstavaUStanuTextField.getText();
        if(brojDomacinstavaUStanuString.isEmpty()){
            brojDomacinstavaUStanuTextField.setStyle("-fx-border-color: RED");
            prikaziUpozorenje("Morate unijeti broj domaćinstava u stanu.");
            return;
        }
        else{
            try{
                int brojDomacinstavaUStanu = Integer.parseInt(brojDomacinstavaUStanuString);
                popisnica.setBrojLicaUStanu(brojDomacinstavaUStanu);
            }
            catch(NumberFormatException e){
                brojDomacinstavaUStanuTextField.setStyle("-fx-border-color: RED");
                prikaziUpozorenje("U polje za broj domaćinstava u stanu morate unijeti cijeli broj.");
                return;
            }
        }

        //TODO: Spisak lica
        //TODO: Poslati popisnicu za domacinstvo na server
    }

    private String getOdgovor(ToggleGroup grupa, int brojPitanja){
        String answer = "";
        RadioButton odgovorButton = (RadioButton)grupa.getSelectedToggle();
        if(odgovorButton == null){
            grupa.getToggles().forEach(toggle -> {
                RadioButton button = (RadioButton)toggle;
                button.setStyle("-fx-border-color: RED");
            });
            prikaziUpozorenje("Morate odgovoriti na " + brojPitanja + ". pitanje.");
        }
        else
            answer = odgovorButton.getText();
        return answer;
    }

    private String getKolicinuPoljoprivrednogDobra(TextField textField, int brojPitanja){
        String odgovor = textField.getText();
        if(!odgovor.isEmpty()) {
            try {
                Integer.parseInt(odgovor);
            } catch (NumberFormatException e) {
                ostaloZemljisteKvadratniMetarTextField.setStyle("-fx-border-color: RED");
                prikaziUpozorenje("Svi odgovori na " + brojPitanja + ". pitanje moraju biti cijeli brojevi.");
                odgovor = "";
            }
        }
        else{
            ostaloZemljisteKvadratniMetarTextField.setStyle("-fx-border-color: RED");
            prikaziUpozorenje("Nepotpun odgovor na " + brojPitanja + ". pitanje.");
        }
        return odgovor;
    }

    private void setDefaultColors(){
        obrazacTextField.setStyle("-fx-border-color: TRANSPARENT");
        entitetTextField.setStyle("-fx-border-color: TRANSPARENT");
        opstinaTextField1.setStyle("-fx-border-color: TRANSPARENT");
        opstinaTextField2.setStyle("-fx-border-color: TRANSPARENT");
        opstinaTextField3.setStyle("-fx-border-color: TRANSPARENT");
        opstinaTextField4.setStyle("-fx-border-color: TRANSPARENT");
        opstinaTextField5.setStyle("-fx-border-color: TRANSPARENT");
        popisniKrugTextField1.setStyle("-fx-border-color: TRANSPARENT");
        popisniKrugTextField2.setStyle("-fx-border-color: TRANSPARENT");
        popisniKrugTextField3.setStyle("-fx-border-color: TRANSPARENT");
        stanTextField1.setStyle("-fx-border-color: TRANSPARENT");
        stanTextField2.setStyle("-fx-border-color: TRANSPARENT");
        stanTextField3.setStyle("-fx-border-color: TRANSPARENT");
        domacinstvoTextField1.setStyle("-fx-border-color: TRANSPARENT");
        domacinstvoTextField2.setStyle("-fx-border-color: TRANSPARENT");
        domacinstvoTextField3.setStyle("-fx-border-color: TRANSPARENT");
        zgradaTextField1.setStyle("-fx-border-color: TRANSPARENT");
        zgradaTextField2.setStyle("-fx-border-color: TRANSPARENT");
        zgradaTextField3.setStyle("-fx-border-color: TRANSPARENT");
        ulicaTextField.setStyle("-fx-border-color: TRANSPARENT");
        kucniBrojTextField.setStyle("-fx-border-color: TRANSPARENT");
        dodatakTextField.setStyle("-fx-border-color: TRANSPARENT");
        ulazTextField.setStyle("-fx-border-color: TRANSPARENT");
        brojStanaTextField.setStyle("-fx-border-color: TRANSPARENT");
        idBrojTextField.setStyle("-fx-border-color: TRANSPARENT");

        grupa1.getToggles().forEach(toggle -> {
            RadioButton button = (RadioButton)toggle;
            button.setStyle("-fx-border-color: TRANSPARENT");
        });
        grupa2.getToggles().forEach(toggle -> {
            RadioButton button = (RadioButton)toggle;
            button.setStyle("-fx-border-color: TRANSPARENT");
        });
        grupa3.getToggles().forEach(toggle -> {
            RadioButton button = (RadioButton)toggle;
            button.setStyle("-fx-border-color: TRANSPARENT");
        });
        grupa4.getToggles().forEach(toggle -> {
            RadioButton button = (RadioButton)toggle;
            button.setStyle("-fx-border-color: TRANSPARENT");
        });
        pitanje4TextField.setStyle("-fx-border-color: TRANSPARENT");
        grupa5.getToggles().forEach(toggle -> {
            RadioButton button = (RadioButton)toggle;
            button.setStyle("-fx-border-color: TRANSPARENT");
        });
        pitanje6TextField.setStyle("-fx-border-color: TRANSPARENT");
        pitanje7TextField.setStyle("-fx-border-color: TRANSPARENT");
        pitanje8TextField.setStyle("-fx-border-color: TRANSPARENT");
        grupa6.getToggles().forEach(toggle -> {
            RadioButton button = (RadioButton)toggle;
            button.setStyle("-fx-border-color: TRANSPARENT");
        });
        grupa7.getToggles().forEach(toggle -> {
            RadioButton button = (RadioButton)toggle;
            button.setStyle("-fx-border-color: TRANSPARENT");
        });
        grupa8.getToggles().forEach(toggle -> {
            RadioButton button = (RadioButton)toggle;
            button.setStyle("-fx-border-color: TRANSPARENT");
        });
        grupa9.getToggles().forEach(toggle -> {
            RadioButton button = (RadioButton)toggle;
            button.setStyle("-fx-border-color: TRANSPARENT");
        });
        grupa10.getToggles().forEach(toggle -> {
            RadioButton button = (RadioButton)toggle;
            button.setStyle("-fx-border-color: TRANSPARENT");
        });
        grupa11.getToggles().forEach(toggle -> {
            RadioButton button = (RadioButton)toggle;
            button.setStyle("-fx-border-color: TRANSPARENT");
        });
        grupa12.getToggles().forEach(toggle -> {
            RadioButton button = (RadioButton)toggle;
            button.setStyle("-fx-border-color: TRANSPARENT");
        });
        grupa13.getToggles().forEach(toggle -> {
            RadioButton button = (RadioButton)toggle;
            button.setStyle("-fx-border-color: TRANSPARENT");
        });
        grupa14.getToggles().forEach(toggle -> {
            RadioButton button = (RadioButton)toggle;
            button.setStyle("-fx-border-color: TRANSPARENT");
        });
        pitanje17TextField.setStyle("-fx-border-color: TRANSPARENT");
        grupa15.getToggles().forEach(toggle -> {
            RadioButton button = (RadioButton)toggle;
            button.setStyle("-fx-border-color: TRANSPARENT");
        });
        grupa16.getToggles().forEach(toggle -> {
            RadioButton button = (RadioButton)toggle;
            button.setStyle("-fx-border-color: TRANSPARENT");
        });
        grupa17.getToggles().forEach(toggle -> {
            RadioButton button = (RadioButton)toggle;
            button.setStyle("-fx-border-color: TRANSPARENT");
        });
        grupa18.getToggles().forEach(toggle -> {
            RadioButton button = (RadioButton)toggle;
            button.setStyle("-fx-border-color: TRANSPARENT");
        });
        grupa19.getToggles().forEach(toggle -> {
            RadioButton button = (RadioButton)toggle;
            button.setStyle("-fx-border-color: TRANSPARENT");
        });
        grupa20.getToggles().forEach(toggle -> {
            RadioButton button = (RadioButton)toggle;
            button.setStyle("-fx-border-color: TRANSPARENT");
        });
        grupa21.getToggles().forEach(toggle -> {
            RadioButton button = (RadioButton)toggle;
            button.setStyle("-fx-border-color: TRANSPARENT");
        });
        grupa22.getToggles().forEach(toggle -> {
            RadioButton button = (RadioButton)toggle;
            button.setStyle("-fx-border-color: TRANSPARENT");
        });
        pitanje26CheckBox1.setStyle("-fx-border-color: TRANSPARENT");
        pitanje26CheckBox2.setStyle("-fx-border-color: TRANSPARENT");
        pitanje26CheckBox3.setStyle("-fx-border-color: TRANSPARENT");
        pitanje26CheckBox4.setStyle("-fx-border-color: TRANSPARENT");
        pitanje26CheckBox5.setStyle("-fx-border-color: TRANSPARENT");
        pitanje26CheckBox6.setStyle("-fx-border-color: TRANSPARENT");
        grupa23.getToggles().forEach(toggle -> {
            RadioButton button = (RadioButton)toggle;
            button.setStyle("-fx-border-color: TRANSPARENT");
        });
        grupa24.getToggles().forEach(toggle -> {
            RadioButton button = (RadioButton)toggle;
            button.setStyle("-fx-border-color: TRANSPARENT");
        });
        grupa25.getToggles().forEach(toggle -> {
            RadioButton button = (RadioButton)toggle;
            button.setStyle("-fx-border-color: TRANSPARENT");
        });
        grupa26.getToggles().forEach(toggle -> {
            RadioButton button = (RadioButton)toggle;
            button.setStyle("-fx-border-color: TRANSPARENT");
        });
        ukupnoZemljisteDunumTextField.setStyle("-fx-border-color: TRANSPARENT");
        poljoprivrednoZemljisteDunumTextField.setStyle("-fx-border-color: TRANSPARENT");
        ZemljisteSumaDunumTextField.setStyle("-fx-border-color: TRANSPARENT");
        ostaloZemljisteDunumTextField.setStyle("-fx-border-color: TRANSPARENT");
        ostaloZemljisteKvadratniMetarTextField.setStyle("-fx-border-color: TRANSPARENT");
        zemljisteSumaKvadratniMetarTextField.setStyle("-fx-border-color: TRANSPARENT");
        poljoprivrednoZemljisteKvadratniMetarTextField.setStyle("-fx-border-color: TRANSPARENT");
        ukupnoZemljisteKvadratniMetarTextField.setStyle("-fx-border-color: TRANSPARENT");

        vinogradiDunumTextField.setStyle("-fx-border-color: TRANSPARENT");
        ribnjaciDunumTextField.setStyle("-fx-border-color: TRANSPARENT");
        gljivarniciDunumTextField.setStyle("-fx-border-color: TRANSPARENT");
        vocnaciDunumTextField.setStyle("-fx-border-color: TRANSPARENT");
        oraniceDunumTextField.setStyle("-fx-border-color: TRANSPARENT");
        livadeDunumTextField.setStyle("-fx-border-color: TRANSPARENT");
        rasadniciDunumTextField.setStyle("-fx-border-color: TRANSPARENT");
        ribnjaciMetarTextField.setStyle("-fx-border-color: TRANSPARENT");
        livadeMetarTextField.setStyle("-fx-border-color: TRANSPARENT");
        rasadniciMetarTextField.setStyle("-fx-border-color: TRANSPARENT");
        oraniceMetarTextField.setStyle("-fx-border-color: TRANSPARENT");
        vocnaciMetarTextField.setStyle("-fx-border-color: TRANSPARENT");
        gljivarniciMetarTextField.setStyle("-fx-border-color: TRANSPARENT");
        vinogradiMetarTextField.setStyle("-fx-border-color: TRANSPARENT");

        ovceTextField.setStyle("-fx-border-color: TRANSPARENT");
        kosniceTextField.setStyle("-fx-border-color: TRANSPARENT");
        peradTextField.setStyle("-fx-border-color: TRANSPARENT");
        konjiTextField.setStyle("-fx-border-color: TRANSPARENT");
        kozeTextField.setStyle("-fx-border-color: TRANSPARENT");
        svinjeTextField.setStyle("-fx-border-color: TRANSPARENT");
        kraveTextField.setStyle("-fx-border-color: TRANSPARENT");
        govedaTextField.setStyle("-fx-border-color: TRANSPARENT");

        pitanje33TextField.setStyle("-fx-border-color: TRANSPARENT");
    }

    private void enableDisableComponentsForQuestion3To24(boolean b){
        grupa3.getToggles().forEach(toggle1 -> {
            Node node = (Node) toggle1 ;
            node.setDisable(b);
        });
        grupa4.getToggles().forEach(toggle1 -> {
            Node node = (Node) toggle1 ;
            node.setDisable(b);
        });
        grupa5.getToggles().forEach(toggle1 -> {
            Node node = (Node) toggle1 ;
            node.setDisable(b);
        });
        grupa6.getToggles().forEach(toggle1 -> {
            Node node = (Node) toggle1 ;
            node.setDisable(b);
        });
        grupa7.getToggles().forEach(toggle1 -> {
            Node node = (Node) toggle1 ;
            node.setDisable(b);
        });
        grupa8.getToggles().forEach(toggle1 -> {
            Node node = (Node) toggle1 ;
            node.setDisable(b);
        });
        grupa9.getToggles().forEach(toggle1 -> {
            Node node = (Node) toggle1 ;
            node.setDisable(b);
        });
        grupa10.getToggles().forEach(toggle1 -> {
            Node node = (Node) toggle1 ;
            node.setDisable(b);
        });
        grupa11.getToggles().forEach(toggle1 -> {
            Node node = (Node) toggle1 ;
            node.setDisable(b);
        });
        grupa12.getToggles().forEach(toggle1 -> {
            Node node = (Node) toggle1 ;
            node.setDisable(b);
        });
        grupa13.getToggles().forEach(toggle1 -> {
            Node node = (Node) toggle1 ;
            node.setDisable(b);
        });
        grupa14.getToggles().forEach(toggle1 -> {
            Node node = (Node) toggle1 ;
            node.setDisable(b);
        });
        grupa15.getToggles().forEach(toggle1 -> {
            Node node = (Node) toggle1 ;
            node.setDisable(b);
        });
        grupa16.getToggles().forEach(toggle1 -> {
            Node node = (Node) toggle1 ;
            node.setDisable(b);
        });
        grupa17.getToggles().forEach(toggle1 -> {
            Node node = (Node) toggle1 ;
            node.setDisable(b);
        });
        grupa18.getToggles().forEach(toggle1 -> {
            Node node = (Node) toggle1 ;
            node.setDisable(b);
        });
        grupa19.getToggles().forEach(toggle1 -> {
            Node node = (Node) toggle1 ;
            node.setDisable(b);
        });
        grupa20.getToggles().forEach(toggle1 -> {
            Node node = (Node) toggle1 ;
            node.setDisable(b);
        });
        pitanje4TextField.setDisable(b);
        pitanje6TextField.setDisable(b);
        pitanje7TextField.setDisable(b);
        pitanje8TextField.setDisable(b);
        pitanje17TextField.setDisable(b);
    }

    private void enableDisableComponentsForQuestion30To33(boolean b){
        oraniceDunumTextField.setDisable(b);
        oraniceMetarTextField.setDisable(b);
        vocnaciDunumTextField.setDisable(b);
        vocnaciMetarTextField.setDisable(b);
        vinogradiDunumTextField.setDisable(b);
        vinogradiMetarTextField.setDisable(b);
        rasadniciDunumTextField.setDisable(b);
        rasadniciMetarTextField.setDisable(b);
        livadeDunumTextField.setDisable(b);
        livadeMetarTextField.setDisable(b);
        ribnjaciDunumTextField.setDisable(b);
        ribnjaciMetarTextField.setDisable(b);
        gljivarniciDunumTextField.setDisable(b);
        gljivarniciMetarTextField.setDisable(b);

        govedaTextField.setDisable(b);
        kraveTextField.setDisable(b);
        svinjeTextField.setDisable(b);
        ovceTextField.setDisable(b);
        kozeTextField.setDisable(b);
        konjiTextField.setDisable(b);
        peradTextField.setDisable(b);
        kosniceTextField.setDisable(b);

        grupa25.getToggles().forEach(toggle1 -> {
            Node node = (Node) toggle1 ;
            node.setDisable(b);
        });

        grupa26.getToggles().forEach(toggle1 -> {
            Node node = (Node) toggle1 ;
            node.setDisable(b);
        });

        pitanje33TextField.setDisable(b);
    }

    private boolean isToggleGroupEnabled(ToggleGroup grupa){
        ObservableList<Toggle> toggles = grupa.getToggles();
        for(Toggle toggle : toggles){
            if(((RadioButton)toggle).isDisabled())
                return false;
        }
        return true;
    }

    private void prikaziUpozorenje(String poruka){
        Alert userNotSelectedAlert = new Alert(Alert.AlertType.ERROR);
        userNotSelectedAlert.setTitle("Greška");
        userNotSelectedAlert.setHeaderText("Greška!");
        userNotSelectedAlert.setContentText(poruka);
        userNotSelectedAlert.showAndWait();
    }

    @FXML
    public void popuniSpisakLica() {
        try {
            Stage spisakLicaStage = new Stage();
            spisakLicaStage.initModality(Modality.APPLICATION_MODAL);

            Parent root = FXMLLoader.load(getClass().getResource("/forme" + File.separator + "FormaZaPopunjavanjeSpiskaLica.fxml"));
            spisakLicaStage.setScene(new Scene(root,975,280));
            spisakLicaStage.setResizable(false);
            spisakLicaStage.show();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
