package kontroleri;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import model.PopisnicaZaStanovnika;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KontrolerFormeZaPopisivanjeStanovnika {
    @FXML
    private TextField obrazacTextField;
    @FXML
    private TextField entitetTextField;
    @FXML
    private TextField imeTextField;
    @FXML
    private TextField imeOcaMajkeTextField;
    @FXML
    private TextField prezimeTextField;
    @FXML
    private TextField JMBTextField;
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
    private TextField liceTextField1;
    @FXML
    private TextField liceTextField2;
    @FXML
    private TextField liceTextField3;
    @FXML
    private ComboBox odabirPolaComboBox;
    @FXML
    private RadioButton pitanje2Button1;
    @FXML
    private RadioButton pitanje2Button2;
    @FXML
    private ComboBox razlogOdsustvaPrisustvaComboBox;
    @FXML
    private RadioButton pitanje4Button1;
    @FXML
    private RadioButton pitanje4Button2;
    @FXML
    private TextField pitanje4TextField1;
    @FXML
    private TextField pitanje4TextField2;
    @FXML
    private RadioButton pitanje5Button1;
    @FXML
    private RadioButton pitanje5Button2;
    @FXML
    private TextField pitanje5TextField2;
    @FXML
    private RadioButton pitanje6Button1;
    @FXML
    private RadioButton pitanje6Button2;
    @FXML
    private TextField pitanje6TextField1;
    @FXML
    private TextField pitanje6TextField2;
    @FXML
    private TextField pitanje6TextField3;
    @FXML
    private RadioButton pitanje7Button1;
    @FXML
    private RadioButton pitanje7Button2;
    @FXML
    private RadioButton pitanje8Button1;
    @FXML
    private RadioButton pitanje8Button2;
    @FXML
    private TextField pitanje8TextField1;
    @FXML
    private TextField pitanje8TextField2;
    @FXML
    private TextField pitanje8TextField3;
    @FXML
    private RadioButton pitanje9Button1;
    @FXML
    private RadioButton pitanje9Button2;
    @FXML
    private TextField pitanje9TextField1;
    @FXML
    private TextField pitanje9TextField3;
    @FXML
    private TextField pitanje9TextField2;
    @FXML
    private RadioButton pitanje10Button1;
    @FXML
    private RadioButton pitanje10Button2;
    @FXML
    private RadioButton pitanje11Button1;
    @FXML
    private RadioButton pitanje11Button2;
    @FXML
    private TextField pitanje11TextField1;
    @FXML
    private TextField pitanje11TextField2;
    @FXML
    private TextField pitanje11TextField3;
    @FXML
    private DatePicker pitanje11DatePicker;
    @FXML
    private TextField pitanje12TextField;
    @FXML
    private ComboBox pitanje12ComboBox;
    @FXML
    private TextField pitanje13TextField1;
    @FXML
    private TextField pitanje13TextField2;
    @FXML
    private TextField pitanje13TextField3;
    @FXML
    private RadioButton pitanje14Button1;
    @FXML
    private RadioButton pitanje14Button2;
    @FXML
    private RadioButton pitanje15Button1;
    @FXML
    private RadioButton pitanje15Button2;
    @FXML
    private RadioButton pitanje15Button3;
    @FXML
    private RadioButton pitanje16Button1;
    @FXML
    private TextField pitanje16TextField2;
    @FXML
    private TextField pitanje16TextField1;
    @FXML
    private RadioButton pitanje16Button2;
    @FXML
    private RadioButton pitanje17Button1;
    @FXML
    private RadioButton pitanje17Button2;
    @FXML
    private RadioButton pitanje18Button1;
    @FXML
    private RadioButton pitanje18Button2;
    @FXML
    private RadioButton pitanje19Button1;
    @FXML
    private RadioButton pitanje19Button2;
    @FXML
    private RadioButton pitanje19Button3;
    @FXML
    private RadioButton pitanje19Button4;
    @FXML
    private TextField pitanje19TextField;
    @FXML
    private TextField pitanje23TextField;
    @FXML
    private TextField pitanje25TextField;
    @FXML
    private TextField pitanje26TextField;
    @FXML
    private TextField pitanje27TextField;
    @FXML
    private TextField pitanje29TextField1;
    @FXML
    private TextField pitanje29TextField2;
    @FXML
    private TextField pitanje30TextField;
    @FXML
    private RadioButton pitanje31Button1;
    @FXML
    private RadioButton pitanje31Button2;
    @FXML
    private RadioButton pitanje35Button1;
    @FXML
    private RadioButton pitanje35Button2;
    @FXML
    private RadioButton pitanje37Button1;
    @FXML
    private RadioButton pitanje37Button2;
    @FXML
    private TextField pitanje39TextField;
    @FXML
    private TextField pitanje40TextField1;
    @FXML
    private TextField pitanje40TextField2;
    @FXML
    private RadioButton pitanje41Button1;
    @FXML
    private RadioButton pitanje41Button2;
    @FXML
    private TextField pitanje41TextField1;
    @FXML
    private TextField pitanje41TextField2;
    @FXML
    private TextField pitanje41TextField3;
    @FXML
    private RadioButton pitanje13Button2;
    @FXML
    private RadioButton pitanje13Button3;
    @FXML
    private RadioButton pitanje13Button1;
    @FXML
    private DatePicker pitanje12DatePicker;
    @FXML
    private DatePicker pitanje24DatePicker1;
    @FXML
    private DatePicker pitanje24DatePicker2;
    @FXML
    private DatePicker pitanje24DatePicker3;
    @FXML
    private CheckBox pitanje28CheckBox1;
    @FXML
    private CheckBox pitanje28CheckBox2;
    @FXML
    private CheckBox pitanje28CheckBox3;
    @FXML
    private CheckBox pitanje28CheckBox4;
    @FXML
    private CheckBox pitanje28CheckBox5;
    @FXML
    private CheckBox pitanje42CheckBox1;
    @FXML
    private CheckBox pitanje42CheckBox2;
    @FXML
    private CheckBox pitanje42CheckBox3;
    @FXML
    private CheckBox pitanje42CheckBox4;
    @FXML
    private CheckBox pitanje42CheckBox5;
    @FXML
    private CheckBox pitanje42CheckBox6;
    @FXML
    private CheckBox pitanje42CheckBox7;
    @FXML
    private CheckBox pitanje42CheckBox8;
    @FXML
    private CheckBox pitanje42CheckBox9;
    @FXML
    private CheckBox pitanje42CheckBox10;
    @FXML
    private ComboBox vidStepenComboBox;
    @FXML
    private ComboBox hodStepenComboBox;
    @FXML
    private ComboBox sluhStepenComboBox;
    @FXML
    private ComboBox pamcenjeStepenComboBox;
    @FXML
    private ComboBox odijevanjeStepenComboBox;
    @FXML
    private ComboBox komunikacijaStepenComboBox;
    @FXML
    private ComboBox vidUzrokComboBox;
    @FXML
    private ComboBox sluhUzrokComboBox;
    @FXML
    private ComboBox hodUzrokComboBox;
    @FXML
    private ComboBox pamcenjeUzrokComboBox;
    @FXML
    private ComboBox odijevanjeUzrokComboBox;
    @FXML
    private ComboBox komunikacijaUzrokComboBox;
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
    @FXML
    private ToggleGroup grupa27;
    @FXML
    private ToggleGroup grupa28;
    @FXML
    private ToggleGroup grupa29;
    @FXML
    private ToggleGroup grupa30;
    @FXML
    private ToggleGroup grupa31;
    @FXML
    private ToggleGroup grupa32;
    @FXML
    private ToggleGroup grupa33;
    @FXML
    private ToggleGroup grupa34;
    @FXML
    private ToggleGroup grupa35;
    @FXML
    private ToggleGroup grupa36;
    @FXML
    private ToggleGroup grupa37;
    @FXML
    private ToggleGroup grupa38;
    @FXML
    private ToggleGroup grupa39;
    @FXML
    private ToggleGroup grupa40;

    public KontrolerFormeZaPopisivanjeStanovnika(){
        KontrolerFormeZaRadPopisivaca.popisStanovnikaStage.setOnShowing((event) -> inicijalizujKomponente());
    }

    private void inicijalizujKomponente(){
        List<String> listaPolova = new ArrayList<>();
        listaPolova.add("Ženski");
        listaPolova.add("Muški");
        ObservableList observableList = FXCollections.observableList(listaPolova);
        odabirPolaComboBox.setItems(observableList);
        odabirPolaComboBox.setOnAction((event) -> {
            if((odabirPolaComboBox.getSelectionModel().getSelectedItem()).equals("Muški")) {
                pitanje23TextField.setDisable(true);
                pitanje24DatePicker1.setDisable(true);
                pitanje24DatePicker2.setDisable(true);
                pitanje24DatePicker3.setDisable(true);
            }
            else{
                pitanje23TextField.setDisable(false);
                pitanje24DatePicker1.setDisable(false);
                pitanje24DatePicker2.setDisable(false);
                pitanje24DatePicker3.setDisable(false);
            }
        });

        List<String> listaRazloga = new ArrayList<>();
        listaRazloga.add("1: Rad");
        listaRazloga.add("2: Školovanje");
        listaRazloga.add("3: Porodični razlozi");
        listaRazloga.add("4: Ostali razlozi");
        listaRazloga.add("5: Na radu u inostranstvu kod stranog ili domaćeg poslodavca ili na samostalnom radu");
        listaRazloga.add("6: Upućeno na rad u diplomatsko - konzularna i druga predstavništva, međunarodne organizacije i sl.");
        listaRazloga.add("7: Boravak u inostranstvu kao član domaćinstva lica odsutnog iz razloga 5");
        listaRazloga.add("8: Boravak u inostranstvu kao član domaćinstva lica odsutnog iz razloga 6");
        listaRazloga.add("9: Školovanje u inostranstvu");
        listaRazloga.add("10: Ostali razlozi boravka u inostranstvu");
        ObservableList observableList1 = FXCollections.observableList(listaRazloga);
        razlogOdsustvaPrisustvaComboBox.setItems(observableList1);

        List<String> listaRazlogaDoseljenja = new ArrayList<>();
        listaRazlogaDoseljenja.add("Posao");
        listaRazlogaDoseljenja.add("Školovanje");
        listaRazlogaDoseljenja.add("Porodični razlozi");
        listaRazlogaDoseljenja.add("Prinudni razlozi");
        listaRazlogaDoseljenja.add("Ostali");
        ObservableList observableList2 = FXCollections.observableList(listaRazlogaDoseljenja);
        pitanje12ComboBox.setItems(observableList2);

        grupa1.getToggles().forEach(toggle -> {
            RadioButton button = (RadioButton)toggle;
            button.setOnAction((event) -> {
                if(button.getText().equals("Član domaćinstva koji živi u mjestu popisa godinu " +
                        "dana ili duže ili se doselio sa namjerom da tu živi")){
                    pitanje2Button1.setDisable(false);
                    pitanje2Button2.setDisable(false);
                }
                else {
                    pitanje2Button1.setDisable(true);
                    pitanje2Button2.setDisable(true);
                }
                if(button.getText().equals("Privremeno prisutno u mjestu popisa kraće ili duže od godinu dana " +
                        "zbog rada/školovanja ili boravka")){
                    grupa9.getToggles().forEach(toggle1 -> {
                        Node node = (Node) toggle1 ;
                        node.setDisable(true);
                    });
                    grupa10.getToggles().forEach(toggle1 -> {
                        Node node = (Node) toggle1 ;
                        node.setDisable(true);
                    });
                    pitanje11TextField1.setDisable(true);
                    pitanje11TextField2.setDisable(true);
                    pitanje11TextField3.setDisable(true);
                    pitanje11DatePicker.setDisable(true);
                }
                else{
                    grupa9.getToggles().forEach(toggle1 -> {
                        Node node = (Node) toggle1 ;
                        node.setDisable(false);
                    });
                    grupa10.getToggles().forEach(toggle1 -> {
                        Node node = (Node) toggle1 ;
                        node.setDisable(false);
                    });
                    pitanje11TextField1.setDisable(false);
                    pitanje11TextField2.setDisable(false);
                    pitanje11TextField3.setDisable(false);
                    pitanje11DatePicker.setDisable(false);
                }
            });
        });

        pitanje2Button1.setOnAction((event) -> {
            razlogOdsustvaPrisustvaComboBox.setDisable(true);
            pitanje4Button1.setDisable(true);
            pitanje4Button2.setDisable(true);
            pitanje4TextField1.setDisable(true);
            pitanje4TextField2.setDisable(true);
            pitanje5Button1.setDisable(true);
            pitanje5Button2.setDisable(true);
            pitanje5TextField2.setDisable(true);
            pitanje6Button1.setDisable(true);
            pitanje6Button2.setDisable(true);
            pitanje6TextField1.setDisable(true);
            pitanje6TextField2.setDisable(true);
            pitanje6TextField3.setDisable(true);
            pitanje7Button1.setDisable(true);
            pitanje7Button2.setDisable(true);
        });
        pitanje2Button2.setOnAction((event) -> {
            razlogOdsustvaPrisustvaComboBox.setDisable(false);
            pitanje4Button1.setDisable(false);
            pitanje4Button2.setDisable(false);
            pitanje4TextField1.setDisable(false);
            pitanje4TextField2.setDisable(false);
            pitanje5Button1.setDisable(false);
            pitanje5Button2.setDisable(false);
            pitanje5TextField2.setDisable(false);
            pitanje6Button1.setDisable(false);
            pitanje6Button2.setDisable(false);
            pitanje6TextField1.setDisable(false);
            pitanje6TextField2.setDisable(false);
            pitanje6TextField3.setDisable(false);
            pitanje7Button1.setDisable(false);
            pitanje7Button2.setDisable(false);
        });

        pitanje4Button1.setOnAction((event) -> {
            pitanje4TextField2.clear();
            pitanje4TextField2.setDisable(true);
            pitanje4TextField1.setDisable(false);
            pitanje5Button1.setDisable(true);
            pitanje5Button2.setDisable(true);
            pitanje5TextField2.setDisable(true);
        });
        pitanje4Button2.setOnAction((event) -> {
            pitanje4TextField1.clear();
            pitanje4TextField1.setDisable(true);
            pitanje4TextField2.setDisable(false);
            pitanje5Button1.setDisable(false);
            pitanje5Button2.setDisable(false);
            pitanje5TextField2.setDisable(false);
        });

        pitanje5Button1.setOnAction((event) -> {
            pitanje5TextField2.clear();
            pitanje5TextField2.setDisable(true);
        });
        pitanje5Button2.setOnAction((event) -> pitanje5TextField2.setDisable(false));

        pitanje6Button1.setOnAction((event) -> {
            pitanje6TextField3.clear();
            pitanje6TextField3.setDisable(true);
            pitanje6TextField1.setDisable(false);
            pitanje6TextField2.setDisable(false);
        });
        pitanje6Button2.setOnAction((event) -> {
            pitanje6TextField1.clear();
            pitanje6TextField1.setDisable(true);
            pitanje6TextField2.clear();
            pitanje6TextField2.setDisable(true);
            pitanje6TextField3.setDisable(false);
        });

        pitanje8Button1.setOnAction((event) -> {
            pitanje8TextField3.clear();
            pitanje8TextField3.setDisable(true);
            pitanje8TextField1.setDisable(false);
            pitanje8TextField2.setDisable(false);
        });
        pitanje8Button2.setOnAction((event) -> {
            pitanje8TextField1.clear();
            pitanje8TextField1.setDisable(true);
            pitanje8TextField2.clear();
            pitanje8TextField2.setDisable(true);
            pitanje8TextField3.setDisable(false);
        });

        pitanje9Button1.setOnAction((event) -> {
            pitanje9TextField3.clear();
            pitanje9TextField3.setDisable(true);
            pitanje9TextField1.setDisable(false);
            pitanje9TextField2.setDisable(false);
        });
        pitanje9Button2.setOnAction((event) -> {
            pitanje9TextField1.clear();
            pitanje9TextField1.setDisable(true);
            pitanje9TextField2.clear();
            pitanje9TextField2.setDisable(true);
            pitanje9TextField3.setDisable(false);
        });

        pitanje10Button1.setOnAction((event) -> {
            pitanje11Button1.setDisable(false);
            pitanje11Button2.setDisable(false);
            pitanje11TextField1.setDisable(false);
            pitanje11TextField2.setDisable(false);
            pitanje11TextField3.setDisable(false);
            pitanje11DatePicker.setDisable(false);
        });
        pitanje10Button2.setOnAction((event) -> {
            pitanje11Button1.setDisable(true);
            pitanje11Button2.setDisable(true);
            pitanje11TextField1.setDisable(true);
            pitanje11TextField2.setDisable(true);
            pitanje11TextField3.setDisable(true);
            pitanje11DatePicker.setDisable(true);
        });

        pitanje11Button1.setOnAction((event) -> {
            pitanje11TextField3.clear();
            pitanje11TextField3.setDisable(true);
            pitanje11TextField1.setDisable(false);
            pitanje11TextField2.setDisable(false);
            pitanje11DatePicker.setValue(null);
            pitanje11DatePicker.setDisable(true);
        });
        pitanje11Button2.setOnAction((event) -> {
            pitanje11TextField1.clear();
            pitanje11TextField1.setDisable(true);
            pitanje11TextField2.clear();
            pitanje11TextField2.setDisable(true);
            pitanje11TextField3.setDisable(false);
            pitanje11DatePicker.setDisable(false);
        });

        grupa11.getToggles().forEach(toggle -> {
            RadioButton button = (RadioButton)toggle;
            button.setOnAction((event) -> {
                if(button.getText().equals("Da (država u kojoj je lice živjelo):")){
                    pitanje12TextField.setDisable(false);
                    pitanje12ComboBox.setDisable(false);
                    pitanje12DatePicker.setDisable(false);
                }
                else {
                    pitanje12TextField.clear();
                    pitanje12TextField.setDisable(true);
                    pitanje12ComboBox.setDisable(true);
                    pitanje12DatePicker.setValue(null);
                    pitanje12DatePicker.setDisable(true);
                }
            });
        });

        pitanje13Button1.setOnAction((event) -> {
            pitanje13TextField1.clear();
            pitanje13TextField1.setDisable(true);
            pitanje13TextField2.clear();
            pitanje13TextField2.setDisable(true);
            pitanje13TextField3.clear();
            pitanje13TextField3.setDisable(true);
        });
        pitanje13Button2.setOnAction((event) -> {
            pitanje13TextField1.setDisable(false);
            pitanje13TextField2.setDisable(false);
            pitanje13TextField3.clear();
            pitanje13TextField3.setDisable(true);
        });
        pitanje13Button3.setOnAction((event) -> {
            pitanje13TextField3.setDisable(false);
            pitanje13TextField1.clear();
            pitanje13TextField1.setDisable(true);
            pitanje13TextField2.clear();
            pitanje13TextField2.setDisable(true);
        });

        pitanje14Button1.setOnAction((event) -> {
            pitanje15Button1.setDisable(false);
            pitanje15Button2.setDisable(false);
            pitanje15Button3.setDisable(false);
        });
        pitanje14Button2.setOnAction((event) -> {
            pitanje15Button1.setDisable(true);
            pitanje15Button2.setDisable(true);
            pitanje15Button3.setDisable(true);
        });

        pitanje16Button1.setOnAction((event) -> {
            pitanje16TextField1.setDisable(false);
            pitanje16TextField2.setDisable(false);
            pitanje17Button1.setDisable(false);
            pitanje17Button2.setDisable(false);
            pitanje18Button1.setDisable(false);
            pitanje18Button2.setDisable(false);
        });
        pitanje16Button2.setOnAction((event) -> {
            pitanje16TextField1.clear();
            pitanje16TextField1.setDisable(true);
            pitanje16TextField2.clear();
            pitanje16TextField2.setDisable(true);
            pitanje17Button1.setDisable(true);
            pitanje17Button2.setDisable(true);
            pitanje18Button1.setDisable(true);
            pitanje18Button2.setDisable(true);
        });

        pitanje17Button1.setOnAction((event) -> {
            pitanje18Button1.setDisable(true);
            pitanje18Button2.setDisable(true);
        });
        pitanje17Button2.setOnAction((event) -> {
            pitanje18Button1.setDisable(false);
            pitanje18Button2.setDisable(false);
        });

        pitanje19Button1.setOnAction((event) -> {
            pitanje19TextField.clear();
            pitanje19TextField.setDisable(true);
        });
        pitanje19Button2.setOnAction((event) -> pitanje19TextField.setDisable(false));
        pitanje19Button3.setOnAction((event) -> pitanje19TextField.setDisable(false));
        pitanje19Button4.setOnAction((event) -> {
            pitanje19TextField.clear();
            pitanje19TextField.setDisable(true);
        });

        grupa22.getToggles().forEach(toggle -> {
            RadioButton button = (RadioButton)toggle;
            button.setOnAction((event) -> {
                if(button.getText().equals("Drugo:"))
                    pitanje25TextField.setDisable(false);
                else {
                    pitanje25TextField.clear();
                    pitanje25TextField.setDisable(true);
                }
            });
        });

        grupa23.getToggles().forEach(toggle -> {
            RadioButton button = (RadioButton)toggle;
            button.setOnAction((event) -> {
                if(button.getText().equals("Drugo:"))
                    pitanje26TextField.setDisable(false);
                else {
                    pitanje26TextField.clear();
                    pitanje26TextField.setDisable(true);
                }
            });
        });

        grupa24.getToggles().forEach(toggle -> {
            RadioButton button = (RadioButton)toggle;
            button.setOnAction((event) -> {
                if(button.getText().equals("Drugo:"))
                    pitanje27TextField.setDisable(false);
                else {
                    pitanje27TextField.clear();
                    pitanje27TextField.setDisable(true);
                }
            });
        });

        grupa25.getToggles().forEach(toggle -> {
            RadioButton button = (RadioButton)toggle;
            button.setOnAction((event) -> {
                if(!button.getText().equals("Visoka škola/fakultet/akademija/univerzitet")){
                    if(button.getText().equals("Nepotpuno osnovno obrazovanje (upisati najviši razred koji je lice završilo)")){
                        pitanje29TextField1.setDisable(false);
                        pitanje29TextField2.setDisable(false);
                        pitanje31Button1.setDisable(false);
                        pitanje31Button2.setDisable(false);
                    }
                    else
                    {
                        if(button.getText().equals("Bez ikakvog obrazovanja")){
                            pitanje31Button1.setDisable(false);
                            pitanje31Button2.setDisable(false);
                        }
                        else{
                            pitanje31Button1.setDisable(true);
                            pitanje31Button2.setDisable(true);
                        }
                        pitanje29TextField1.clear();
                        pitanje29TextField1.setDisable(true);
                        pitanje29TextField2.clear();
                        pitanje29TextField2.setDisable(true);
                    }
                    grupa28.getToggles().forEach(toggle1 -> {
                        Node node = (Node) toggle1 ;
                        node.setDisable(true);
                    });
                    pitanje30TextField.setDisable(true);
                }
                else {
                    pitanje29TextField1.clear();
                    pitanje29TextField1.setDisable(true);
                    pitanje29TextField2.clear();
                    pitanje29TextField2.setDisable(true);
                    grupa28.getToggles().forEach(toggle1 -> {
                        Node node = (Node) toggle1 ;
                        node.setDisable(false);
                    });
                    pitanje30TextField.setDisable(false);
                    pitanje31Button1.setDisable(false);
                    pitanje31Button2.setDisable(false);
                }
                if(button.getText().equals("Srednja škola")){
                    grupa26.getToggles().forEach(toggle1 -> {
                        Node node = (Node) toggle1 ;
                        node.setDisable(false);
                    });
                    grupa27.getToggles().forEach(toggle1 -> {
                        Node node = (Node) toggle1 ;
                        node.setDisable(false);
                    });
                }
                else{
                    grupa26.getToggles().forEach(toggle2 -> {
                        Node node = (Node) toggle2 ;
                        node.setDisable(true);
                    });
                    grupa27.getToggles().forEach(toggle2 -> {
                        Node node = (Node) toggle2 ;
                        node.setDisable(true);
                    });
                }
            });
        });

        grupa28.getToggles().forEach(toggle -> {
            RadioButton button = (RadioButton)toggle;
            button.setOnAction((event) -> {
                if(button.getText().equals("Diplomirani (broj godina studija):"))
                    pitanje30TextField.setDisable(false);
                else {
                    pitanje30TextField.clear();
                    pitanje30TextField.setDisable(true);
                }
                pitanje31Button1.setDisable(true);
                pitanje31Button2.setDisable(true);
            });
        });

        grupa31.getToggles().forEach(toggle1 -> {
            RadioButton button = (RadioButton)toggle1;
            button.setOnAction((event) -> {
                grupa32.getToggles().forEach(toggle -> {
                Node node = (Node) toggle ;
                if(!button.getText().equals("Ne"))
                    node.setDisable(true);
                else
                    node.setDisable(false);
                });
                grupa33.getToggles().forEach(toggle -> {
                    Node node = (Node) toggle ;
                    if(!button.getText().equals("Ne"))
                        node.setDisable(true);
                    else
                        node.setDisable(false);
                });
                grupa34.getToggles().forEach(toggle -> {
                    Node node = (Node) toggle ;
                    if(!button.getText().equals("Ne"))
                        node.setDisable(true);
                    else
                        node.setDisable(false);
                });
                grupa35.getToggles().forEach(toggle -> {
                    Node node = (Node) toggle ;
                    if(!button.getText().equals("Ne"))
                        node.setDisable(true);
                    else
                        node.setDisable(false);
                });
            });
        });

        grupa32.getToggles().forEach(toggle1 -> {
            RadioButton button = (RadioButton)toggle1;
            button.setOnAction((event) -> grupa33.getToggles().forEach(toggle -> {
                Node node = (Node) toggle ;
                if(!button.getText().equals("Ne"))
                    node.setDisable(false);
                else
                    node.setDisable(true);
            }));
        });

        pitanje35Button1.setOnAction((event) -> grupa34.getToggles().forEach(toggle -> {
            Node node = (Node) toggle ;
            node.setDisable(true);
        }));
        pitanje35Button2.setOnAction((event) -> grupa34.getToggles().forEach(toggle -> {
            Node node = (Node) toggle ;
            node.setDisable(false);
        }));

        grupa34.getToggles().forEach(toggle -> {
            RadioButton button = (RadioButton)toggle;
            button.setOnAction((event) -> {
                grupa35.getToggles().forEach(toggle1 -> {
                    Node node = (Node) toggle1;
                    node.setDisable(true);
                });
                grupa36.getToggles().forEach(toggle1 -> {
                    Node node = (Node) toggle1;
                    node.setDisable(true);
                });
                pitanje39TextField.setDisable(true);
                pitanje40TextField1.setDisable(true);
                pitanje40TextField2.setDisable(true);
            });
        });

        pitanje37Button1.setOnAction((event) -> {
            grupa36.getToggles().forEach(toggle1 -> {
                Node node = (Node) toggle1;
                node.setDisable(false);
            });
            pitanje39TextField.setDisable(false);
            pitanje40TextField1.setDisable(false);
            pitanje40TextField2.setDisable(false);
        });
        pitanje37Button2.setOnAction((event) -> {
            grupa36.getToggles().forEach(toggle1 -> {
                Node node = (Node) toggle1;
                node.setDisable(true);
            });
            pitanje39TextField.setDisable(true);
            pitanje40TextField1.setDisable(true);
            pitanje40TextField2.setDisable(true);
        });

        pitanje41Button1.setOnAction((event) -> {
            pitanje41TextField3.clear();
            pitanje41TextField3.setDisable(true);
            pitanje41TextField1.setDisable(false);
            pitanje41TextField2.setDisable(false);
        });
        pitanje41Button2.setOnAction((event) -> {
            pitanje41TextField1.clear();
            pitanje41TextField1.setDisable(true);
            pitanje41TextField2.clear();
            pitanje41TextField2.setDisable(true);
            pitanje41TextField3.setDisable(false);
        });

        grupa38.getToggles().forEach(toggle -> {
            RadioButton button = (RadioButton)toggle;
            button.setOnAction((event) -> grupa39.getToggles().forEach(toggle1 -> {
                Node node = (Node) toggle1;
                if(!button.getText().equals("Izdržavano lice"))
                    node.setDisable(true);
                else
                    node.setDisable(false);
            }));
        });

        List<String> stepeniPoteskoce = new ArrayList<>();
        stepeniPoteskoce.add("Nema poteškoća");
        stepeniPoteskoce.add("Ima, manje");
        stepeniPoteskoce.add("Ima, veće");
        stepeniPoteskoce.add("Potpuna nesposobnost");
        ObservableList observableList3 = FXCollections.observableList(stepeniPoteskoce);
        vidStepenComboBox.setItems(observableList3);
        sluhStepenComboBox.setItems(observableList3);
        hodStepenComboBox.setItems(observableList3);
        pamcenjeStepenComboBox.setItems(observableList3);
        odijevanjeStepenComboBox.setItems(observableList3);
        komunikacijaStepenComboBox.setItems(observableList3);

        List<String> uzrociPoteskoce = new ArrayList<>();
        uzrociPoteskoce.add("Urođena");
        uzrociPoteskoce.add("Povreda pri porodu");
        uzrociPoteskoce.add("Bolest");
        uzrociPoteskoce.add("Povreda na radu");
        uzrociPoteskoce.add("Saobraćajna nesreća");
        uzrociPoteskoce.add("Posljedica rata");
        uzrociPoteskoce.add("Ostalo");
        ObservableList observableList4 = FXCollections.observableList(uzrociPoteskoce);
        vidUzrokComboBox.setItems(observableList4);
        sluhUzrokComboBox.setItems(observableList4);
        hodUzrokComboBox.setItems(observableList4);
        pamcenjeUzrokComboBox.setItems(observableList4);
        odijevanjeUzrokComboBox.setItems(observableList4);
        komunikacijaUzrokComboBox.setItems(observableList4);
    }

    @FXML
    public void posaljiPopisnicuButtonAction() {
        setDefaultColors();

        int idObrasca;
        int idEntiteta;
        int idOpstine;
        int idPopisnogKruga;
        int idStana;
        int idDomacinstva;
        int idLica;
        String ime;
        String imeOcaIliMajke;
        String prezime;
        String JMB;
        String pol;
        Map<Integer,List<String>> odgovoriNaPitanja = new HashMap<>();
        for(int i=1; i<=46; ++i)
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
            idLica = Integer.parseInt(liceTextField1.getText() + liceTextField2.getText() + liceTextField3.getText());
            if(idLica < 0 || idLica > 999) {
                liceTextField1.setStyle("-fx-border-color: RED");
                liceTextField2.setStyle("-fx-border-color: RED");
                liceTextField3.setStyle("-fx-border-color: RED");
                prikaziUpozorenje("U svako polje za lice morate unijeti jednocifren broj.");
                return;
            }
        }
        catch(NumberFormatException e){
            liceTextField1.setStyle("-fx-border-color: RED");
            liceTextField2.setStyle("-fx-border-color: RED");
            liceTextField3.setStyle("-fx-border-color: RED");
            prikaziUpozorenje("U polja za lice morate unijeti brojeve.");
            return;
        }

        ime = imeTextField.getText();
        if(ime.isEmpty()) {
            imeTextField.setStyle("-fx-border-color: RED");
            prikaziUpozorenje("Morate unijeti ime lica koje se popisuje.");
            return;
        }

        imeOcaIliMajke = imeOcaMajkeTextField.getText();
        if(imeOcaIliMajke.isEmpty()){
            imeOcaMajkeTextField.setStyle("-fx-border-color: RED");
            prikaziUpozorenje("Morate unijeti ime oca ili majke lica koje se popisuje.");
            return;
        }

        prezime = prezimeTextField.getText();
        if(prezime.isEmpty()){
            prezimeTextField.setStyle("-fx-border-color: RED");
            prikaziUpozorenje("Morate unijeti prezime lica koje se popisuje.");
            return;
        }

        JMB = JMBTextField.getText();
        if(JMB.isEmpty()){
            JMBTextField.setStyle("-fx-border-color: RED");
            prikaziUpozorenje("Morate unijeti JMB lica koje se popisuje.");
            return;
        }

        pol = (String)odabirPolaComboBox.getSelectionModel().getSelectedItem();
        if(pol == null){
            odabirPolaComboBox.setStyle("-fx-border-color: RED");
            prikaziUpozorenje("Morate odabrati pol lica koje se popisuje.");
            return;
        }

        PopisnicaZaStanovnika popisnica = new PopisnicaZaStanovnika(idObrasca, idEntiteta, idOpstine, idPopisnogKruga, idStana,
                idDomacinstva, idLica, ime, imeOcaIliMajke, prezime, JMB, pol);

        RadioButton odgovor1Button = (RadioButton)grupa1.getSelectedToggle();
        if(odgovor1Button == null){
            grupa1.getToggles().forEach(toggle -> {
                RadioButton button = (RadioButton)toggle;
                button.setStyle("-fx-border-color: RED");
            });
            prikaziUpozorenje("Morate odgovoriti na 1. pitanje.");
            return;
        }
        else
            odgovoriNaPitanja.get(1).add(odgovor1Button.getText());

        if(isToggleGroupEnabled(grupa2)){
            RadioButton odgovor2Button = (RadioButton)grupa2.getSelectedToggle();
            if(odgovor2Button == null){
                grupa2.getToggles().forEach(toggle -> {
                    RadioButton button = (RadioButton)toggle;
                    button.setStyle("-fx-border-color: RED");
                });
                prikaziUpozorenje("Morate odgovoriti na 2. pitanje.");
                return;
            }
            else
                odgovoriNaPitanja.get(2).add(odgovor2Button.getText());
        }

        if(!razlogOdsustvaPrisustvaComboBox.isDisabled()){
            String odgovor3 = (String)razlogOdsustvaPrisustvaComboBox.getSelectionModel().getSelectedItem();
            if(odgovor3 != null)
                odgovoriNaPitanja.get(3).add(odgovor3);
            else{
                razlogOdsustvaPrisustvaComboBox.setStyle("-fx-border-color: RED");
                prikaziUpozorenje("Morate odgovoriti na 3. pitanje.");
                return;
            }
        }

        if(isToggleGroupEnabled(grupa3)){
            RadioButton odgovor4Button = (RadioButton)grupa3.getSelectedToggle();
            if(odgovor4Button == null || (pitanje4TextField1.getText().isEmpty() && pitanje4TextField2.getText().isEmpty())){
                grupa3.getToggles().forEach(toggle -> {
                    RadioButton button = (RadioButton)toggle;
                    button.setStyle("-fx-border-color: RED");
                });
                pitanje4TextField1.setStyle("-fx-border-color: RED");
                pitanje4TextField2.setStyle("-fx-border-color: RED");
                prikaziUpozorenje("Morate odgovoriti na 4. pitanje.");
                return;
            }
            else {
                odgovoriNaPitanja.get(4).add(odgovor4Button.getText());
                odgovoriNaPitanja.get(4).add(!pitanje4TextField1.isDisabled() ? pitanje4TextField1.getText()  : pitanje4TextField2.getText());
            }
        }

        if(isToggleGroupEnabled(grupa4)){
            RadioButton odgovor5Button = (RadioButton)grupa4.getSelectedToggle();
            if(odgovor5Button == null || (!pitanje5TextField2.isDisabled() && pitanje5TextField2.getText().isEmpty())){
                grupa4.getToggles().forEach(toggle -> {
                    RadioButton button = (RadioButton)toggle;
                    button.setStyle("-fx-border-color: RED");
                });
                pitanje5TextField2.setStyle("-fx-border-color: RED");
                prikaziUpozorenje("Morate odgovoriti na 5. pitanje.");
                return;
            }
            else {
                odgovoriNaPitanja.get(5).add(odgovor5Button.getText());
                if(!pitanje5TextField2.getText().isEmpty())
                    odgovoriNaPitanja.get(5).add(pitanje5TextField2.getText());
            }
        }

        if(isToggleGroupEnabled(grupa5)){
            RadioButton odgovor6Button = (RadioButton)grupa5.getSelectedToggle();
            if(odgovor6Button == null || (!pitanje6TextField1.isDisabled() && pitanje6TextField1.getText().isEmpty()) ||
                    (!pitanje6TextField2.isDisabled() && pitanje6TextField2.getText().isEmpty()) ||
                    (!pitanje6TextField3.isDisabled() && pitanje6TextField3.getText().isEmpty())){
                grupa5.getToggles().forEach(toggle -> {
                    RadioButton button = (RadioButton)toggle;
                    button.setStyle("-fx-border-color: RED");
                });
                pitanje6TextField1.setStyle("-fx-border-color: RED");
                pitanje6TextField2.setStyle("-fx-border-color: RED");
                pitanje6TextField3.setStyle("-fx-border-color: RED");
                prikaziUpozorenje("Morate odgovoriti na 6. pitanje.");
                return;
            }
            else{
                odgovoriNaPitanja.get(6).add(odgovor6Button.getText());
                if(pitanje6TextField3.isDisabled()){
                    odgovoriNaPitanja.get(6).add(pitanje6TextField1.getText());
                    odgovoriNaPitanja.get(6).add(pitanje6TextField2.getText());
                }
                else
                    odgovoriNaPitanja.get(6).add(pitanje6TextField3.getText());
            }
        }

        if(isToggleGroupEnabled(grupa6)){
            RadioButton odgovor7Button = (RadioButton)grupa6.getSelectedToggle();
            if(odgovor7Button == null){
                grupa6.getToggles().forEach(toggle -> {
                    RadioButton button = (RadioButton)toggle;
                    button.setStyle("-fx-border-color: RED");
                });
                prikaziUpozorenje("Morate odgovoriti na 7. pitanje.");
                return;
            }
            else
                odgovoriNaPitanja.get(7).add(odgovor7Button.getText());
        }

        RadioButton odgovor8Button = (RadioButton)grupa7.getSelectedToggle();
        if(odgovor8Button == null || (!pitanje8TextField1.isDisabled() && pitanje8TextField1.getText().isEmpty()) ||
                (!pitanje8TextField2.isDisabled() && pitanje8TextField2.getText().isEmpty()) ||
                (!pitanje8TextField3.isDisabled() && pitanje8TextField3.getText().isEmpty())){
            grupa7.getToggles().forEach(toggle -> {
                RadioButton button = (RadioButton)toggle;
                button.setStyle("-fx-border-color: RED");
            });
            pitanje8TextField1.setStyle("-fx-border-color: RED");
            pitanje8TextField2.setStyle("-fx-border-color: RED");
            pitanje8TextField3.setStyle("-fx-border-color: RED");
            prikaziUpozorenje("Morate odgovoriti na 8. pitanje.");
            return;
        }
        else{
            odgovoriNaPitanja.get(8).add(odgovor8Button.getText());
            if(pitanje8TextField3.isDisabled()){
                odgovoriNaPitanja.get(8).add(pitanje8TextField1.getText());
                odgovoriNaPitanja.get(8).add(pitanje8TextField2.getText());
            }
            else
                odgovoriNaPitanja.get(8).add(pitanje8TextField3.getText());
        }

        RadioButton odgovor9Button = (RadioButton)grupa8.getSelectedToggle();
        if(odgovor9Button == null || (!pitanje9TextField1.isDisabled() && pitanje9TextField1.getText().isEmpty()) ||
                (!pitanje9TextField2.isDisabled() && pitanje9TextField2.getText().isEmpty()) ||
                (!pitanje9TextField3.isDisabled() && pitanje9TextField3.getText().isEmpty())){
            grupa8.getToggles().forEach(toggle -> {
                RadioButton button = (RadioButton)toggle;
                button.setStyle("-fx-border-color: RED");
            });
            pitanje9TextField1.setStyle("-fx-border-color: RED");
            pitanje9TextField2.setStyle("-fx-border-color: RED");
            pitanje9TextField3.setStyle("-fx-border-color: RED");
            prikaziUpozorenje("Morate odgovoriti na 9. pitanje.");
            return;
        }
        else{
            odgovoriNaPitanja.get(9).add(odgovor9Button.getText());
            if(pitanje9TextField3.isDisabled()){
                odgovoriNaPitanja.get(9).add(pitanje9TextField1.getText());
                odgovoriNaPitanja.get(9).add(pitanje9TextField2.getText());
            }
            else
                odgovoriNaPitanja.get(9).add(pitanje9TextField3.getText());
        }

        if(isToggleGroupEnabled(grupa9)) {
            RadioButton odgovor10Button = (RadioButton) grupa9.getSelectedToggle();
            if (odgovor10Button == null) {
                grupa9.getToggles().forEach(toggle -> {
                    RadioButton button = (RadioButton) toggle;
                    button.setStyle("-fx-border-color: RED");
                });
                prikaziUpozorenje("Morate odgovoriti na 10. pitanje.");
                return;
            } else
                odgovoriNaPitanja.get(10).add(odgovor10Button.getText());
        }

        if(isToggleGroupEnabled(grupa10)){
            RadioButton odgovor11Button = (RadioButton)grupa10.getSelectedToggle();
            if(odgovor11Button == null || (!pitanje11TextField1.isDisabled() && pitanje11TextField1.getText().isEmpty()) ||
                    (!pitanje11TextField2.isDisabled() && pitanje11TextField2.getText().isEmpty()) ||
                    (!pitanje11TextField3.isDisabled() && pitanje11TextField3.getText().isEmpty()) ||
                    pitanje11DatePicker.getValue() == null){
                grupa10.getToggles().forEach(toggle -> {
                    RadioButton button = (RadioButton)toggle;
                    button.setStyle("-fx-border-color: RED");
                });
                pitanje11TextField1.setStyle("-fx-border-color: RED");
                pitanje11TextField2.setStyle("-fx-border-color: RED");
                pitanje11TextField3.setStyle("-fx-border-color: RED");
                pitanje11DatePicker.setStyle("-fx-border-color: RED");
                prikaziUpozorenje("Morate odgovoriti na 11. pitanje.");
                return;
            }
            else{
                odgovoriNaPitanja.get(11).add(odgovor11Button.getText());
                if(pitanje11TextField3.isDisabled()){
                    odgovoriNaPitanja.get(11).add(pitanje11TextField1.getText());
                    odgovoriNaPitanja.get(11).add(pitanje11TextField2.getText());
                }
                else
                    odgovoriNaPitanja.get(11).add(pitanje11TextField3.getText());
                odgovoriNaPitanja.get(11).add(pitanje11DatePicker.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
            }
        }

        RadioButton odgovor12Button = (RadioButton)grupa11.getSelectedToggle();
        if(odgovor12Button == null || (!pitanje12TextField.isDisabled() && pitanje12TextField.getText().isEmpty()) ||
                (!pitanje12ComboBox.isDisabled() && pitanje12ComboBox.getSelectionModel().getSelectedItem() == null) ||
                (!pitanje12DatePicker.isDisabled() && pitanje12DatePicker.getValue() == null)){
            grupa11.getToggles().forEach(toggle -> {
                RadioButton button = (RadioButton)toggle;
                button.setStyle("-fx-border-color: RED");
            });
            pitanje12TextField.setStyle("-fx-border-color: RED");
            pitanje12ComboBox.setStyle("-fx-border-color: RED");
            pitanje12DatePicker.setStyle("-fx-border-color: RED");
            prikaziUpozorenje("Morate odgovoriti na 12. pitanje.");
            return;
        }
        else
        {
            odgovoriNaPitanja.get(12).add(odgovor12Button.getText());
            if(!pitanje12TextField.isDisabled()){
                odgovoriNaPitanja.get(12).add(pitanje12TextField.getText());
                odgovoriNaPitanja.get(12).add((String)pitanje12ComboBox.getSelectionModel().getSelectedItem());
                odgovoriNaPitanja.get(12).add(pitanje12DatePicker.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
            }
        }

        RadioButton odgovor13Button = (RadioButton)grupa12.getSelectedToggle();
        if(odgovor13Button == null || (!pitanje13TextField1.isDisabled() && pitanje13TextField1.getText().isEmpty()) ||
                (!pitanje13TextField2.isDisabled() && pitanje13TextField2.getText().isEmpty()) ||
                (!pitanje13TextField3.isDisabled() && pitanje13TextField3.getText().isEmpty())){
            grupa12.getToggles().forEach(toggle -> {
                RadioButton button = (RadioButton)toggle;
                button.setStyle("-fx-border-color: RED");
            });
            pitanje13TextField1.setStyle("-fx-border-color: RED");
            pitanje13TextField2.setStyle("-fx-border-color: RED");
            pitanje13TextField3.setStyle("-fx-border-color: RED");
            prikaziUpozorenje("Morate odgovoriti na 13. pitanje.");
            return;
        }
        else{
            odgovoriNaPitanja.get(13).add(odgovor13Button.getText());
            if(pitanje13TextField3.isDisabled()){
                odgovoriNaPitanja.get(13).add(pitanje13TextField1.getText());
                odgovoriNaPitanja.get(13).add(pitanje13TextField2.getText());
            }
            else
                odgovoriNaPitanja.get(13).add(pitanje13TextField3.getText());
        }

        RadioButton odgovor14Button = (RadioButton)grupa13.getSelectedToggle();
        if(odgovor14Button == null){
            grupa13.getToggles().forEach(toggle -> {
                RadioButton button = (RadioButton)toggle;
                button.setStyle("-fx-border-color: RED");
            });
            prikaziUpozorenje("Morate odgovoriti na 14. pitanje.");
            return;
        }
        else
            odgovoriNaPitanja.get(14).add(odgovor14Button.getText());

        if(isToggleGroupEnabled(grupa14)){
            RadioButton odgovor15Button = (RadioButton)grupa14.getSelectedToggle();
            if(odgovor15Button == null){
                grupa14.getToggles().forEach(toggle -> {
                    RadioButton button = (RadioButton)toggle;
                    button.setStyle("-fx-border-color: RED");
                });
                prikaziUpozorenje("Morate odgovoriti na 15. pitanje.");
                return;
            }
            else
                odgovoriNaPitanja.get(15).add(odgovor15Button.getText());
        }

        RadioButton odgovor16Button = (RadioButton)grupa15.getSelectedToggle();
        if(odgovor16Button == null || (!pitanje16TextField1.isDisabled() && pitanje16TextField1.getText().isEmpty()) ||
                (!pitanje16TextField2.isDisabled() && pitanje16TextField2.getText().isEmpty())){
            grupa15.getToggles().forEach(toggle -> {
                RadioButton button = (RadioButton)toggle;
                button.setStyle("-fx-border-color: RED");
            });
            pitanje16TextField1.setStyle("-fx-border-color: RED");
            pitanje16TextField2.setStyle("-fx-border-color: RED");
            prikaziUpozorenje("Morate odgovoriti na 16. pitanje.");
            return;
        }
        else{
            odgovoriNaPitanja.get(16).add(odgovor16Button.getText());
            if(!pitanje16TextField1.isDisabled()) {
                odgovoriNaPitanja.get(16).add(pitanje16TextField1.getText());
                odgovoriNaPitanja.get(16).add(pitanje16TextField2.getText());
            }
        }

        if(isToggleGroupEnabled(grupa16)){
            RadioButton odgovor17Button = (RadioButton)grupa16.getSelectedToggle();
            if(odgovor17Button == null){
                grupa16.getToggles().forEach(toggle -> {
                    RadioButton button = (RadioButton)toggle;
                    button.setStyle("-fx-border-color: RED");
                });
                prikaziUpozorenje("Morate odgovoriti na 17. pitanje.");
                return;
            }
            else
                odgovoriNaPitanja.get(17).add(odgovor17Button.getText());
        }

        if(isToggleGroupEnabled(grupa17)){
            RadioButton odgovor18Button = (RadioButton)grupa17.getSelectedToggle();
            if(odgovor18Button == null){
                grupa17.getToggles().forEach(toggle -> {
                    RadioButton button = (RadioButton)toggle;
                    button.setStyle("-fx-border-color: RED");
                });
                prikaziUpozorenje("Morate odgovoriti na 18. pitanje.");
                return;
            }
            else
                odgovoriNaPitanja.get(18).add(odgovor18Button.getText());
        }

        RadioButton odgovor19Button = (RadioButton)grupa18.getSelectedToggle();
        if(odgovor19Button == null || (!pitanje19TextField.isDisabled() && pitanje19TextField.getText().isEmpty())){
            grupa18.getToggles().forEach(toggle -> {
                RadioButton button = (RadioButton)toggle;
                button.setStyle("-fx-border-color: RED");
            });
            pitanje19TextField.setStyle("-fx-border-color: RED");
            prikaziUpozorenje("Morate odgovoriti na 19. pitanje.");
            return;
        }
        else {
            odgovoriNaPitanja.get(19).add(odgovor19Button.getText());
            if(!pitanje19TextField.isDisabled())
                odgovoriNaPitanja.get(19).add(pitanje19TextField.getText());
        }

        RadioButton odgovor20Button = (RadioButton)grupa19.getSelectedToggle();
        if(odgovor20Button == null){
            grupa19.getToggles().forEach(toggle -> {
                RadioButton button = (RadioButton)toggle;
                button.setStyle("-fx-border-color: RED");
            });
            prikaziUpozorenje("Morate odgovoriti na 20. pitanje.");
            return;
        }
        else
            odgovoriNaPitanja.get(20).add(odgovor20Button.getText());

        RadioButton odgovor21Button = (RadioButton)grupa20.getSelectedToggle();
        if(odgovor21Button == null){
            grupa20.getToggles().forEach(toggle -> {
                RadioButton button = (RadioButton)toggle;
                button.setStyle("-fx-border-color: RED");
            });
            prikaziUpozorenje("Morate odgovoriti na 21. pitanje.");
            return;
        }
        else
            odgovoriNaPitanja.get(21).add(odgovor21Button.getText());

        RadioButton odgovor22Button = (RadioButton)grupa21.getSelectedToggle();
        if(odgovor22Button == null){
            grupa21.getToggles().forEach(toggle -> {
                RadioButton button = (RadioButton)toggle;
                button.setStyle("-fx-border-color: RED");
            });
            prikaziUpozorenje("Morate odgovoriti na 22. pitanje.");
            return;
        }
        else
            odgovoriNaPitanja.get(22).add(odgovor22Button.getText());

        if(!pitanje23TextField.isDisabled()){
            if(pitanje23TextField.getText().isEmpty()){
                pitanje23TextField.setStyle("-fx-border-color: RED");
                prikaziUpozorenje("Morate odgovoriti na 23. pitanje.");
                return;
            }
            else{
                try{
                    Integer.parseInt(pitanje23TextField.getText());
                    odgovoriNaPitanja.get(23).add(pitanje23TextField.getText());
                }
                catch(NumberFormatException e){
                    pitanje23TextField.setStyle("-fx-border-color: RED");
                    prikaziUpozorenje("Odgovor na 23. pitanje mora biti broj.");
                    return;
                }
            }
        }

        if(!pitanje24DatePicker1.isDisabled()){
            if(Integer.parseInt(pitanje23TextField.getText()) >= 1 && pitanje24DatePicker1.getValue() == null){
                pitanje24DatePicker1.setStyle("-fx-border-color: RED");
                prikaziUpozorenje("Morate dogovoriti na 24. pitanje.");
                return;
            }
            else if(Integer.parseInt(pitanje23TextField.getText()) >= 1)
                odgovoriNaPitanja.get(24).add(pitanje24DatePicker1.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
            if(Integer.parseInt(pitanje23TextField.getText()) >= 2 && pitanje24DatePicker2.getValue() == null){
                pitanje24DatePicker2.setStyle("-fx-border-color: RED");
                prikaziUpozorenje("Morate dogovoriti na 24. pitanje.");
                return;
            }
            else if(Integer.parseInt(pitanje23TextField.getText()) >= 2)
                odgovoriNaPitanja.get(24).add(pitanje24DatePicker2.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
            if(Integer.parseInt(pitanje23TextField.getText()) >= 3 && pitanje24DatePicker3.getValue() == null){
                pitanje24DatePicker3.setStyle("-fx-border-color: RED");
                prikaziUpozorenje("Morate dogovoriti na 24. pitanje.");
                return;
            }
            else if(Integer.parseInt(pitanje23TextField.getText()) >= 3)
                odgovoriNaPitanja.get(24).add(pitanje24DatePicker3.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        }

        RadioButton odgovor25Button = (RadioButton)grupa22.getSelectedToggle();
        if(odgovor25Button == null || (!pitanje25TextField.isDisabled() && pitanje25TextField.getText().isEmpty())){
            grupa22.getToggles().forEach(toggle -> {
                RadioButton button = (RadioButton)toggle;
                button.setStyle("-fx-border-color: RED");
            });
            pitanje25TextField.setStyle("-fx-border-color: RED");
            prikaziUpozorenje("Morate odgovoriti na 25. pitanje.");
            return;
        }
        else {
            odgovoriNaPitanja.get(25).add(odgovor25Button.getText());
            if(!pitanje25TextField.isDisabled())
                odgovoriNaPitanja.get(25).add(pitanje25TextField.getText());
        }

        RadioButton odgovor26Button = (RadioButton)grupa23.getSelectedToggle();
        if(odgovor26Button == null || (!pitanje26TextField.isDisabled() && pitanje26TextField.getText().isEmpty())){
            grupa23.getToggles().forEach(toggle -> {
                RadioButton button = (RadioButton)toggle;
                button.setStyle("-fx-border-color: RED");
            });
            pitanje26TextField.setStyle("-fx-border-color: RED");
            prikaziUpozorenje("Morate odgovoriti na 26. pitanje.");
            return;
        }
        else {
            odgovoriNaPitanja.get(26).add(odgovor26Button.getText());
            if(!pitanje26TextField.isDisabled())
                odgovoriNaPitanja.get(26).add(pitanje26TextField.getText());
        }

        RadioButton odgovor27Button = (RadioButton)grupa24.getSelectedToggle();
        if(odgovor27Button == null || (!pitanje27TextField.isDisabled() && pitanje27TextField.getText().isEmpty())){
            grupa24.getToggles().forEach(toggle -> {
                RadioButton button = (RadioButton)toggle;
                button.setStyle("-fx-border-color: RED");
            });
            pitanje27TextField.setStyle("-fx-border-color: RED");
            prikaziUpozorenje("Morate odgovoriti na 27. pitanje.");
            return;
        }
        else {
            odgovoriNaPitanja.get(27).add(odgovor27Button.getText());
            if(!pitanje27TextField.isDisabled())
                odgovoriNaPitanja.get(27).add(pitanje27TextField.getText());
        }

        if(pitanje28CheckBox1.isSelected())
            odgovoriNaPitanja.get(28).add(pitanje28CheckBox1.getText());
        if(pitanje28CheckBox2.isSelected())
            odgovoriNaPitanja.get(28).add(pitanje28CheckBox2.getText());
        if(pitanje28CheckBox3.isSelected())
            odgovoriNaPitanja.get(28).add(pitanje28CheckBox3.getText());
        if(pitanje28CheckBox4.isSelected())
            odgovoriNaPitanja.get(28).add(pitanje28CheckBox4.getText());
        if(pitanje28CheckBox5.isSelected())
            odgovoriNaPitanja.get(28).add(pitanje28CheckBox5.getText());
        if(odgovoriNaPitanja.get(28).isEmpty()){
            pitanje28CheckBox1.setStyle("-fx-border-color: RED");
            pitanje28CheckBox2.setStyle("-fx-border-color: RED");
            pitanje28CheckBox3.setStyle("-fx-border-color: RED");
            pitanje28CheckBox4.setStyle("-fx-border-color: RED");
            pitanje28CheckBox5.setStyle("-fx-border-color: RED");
            prikaziUpozorenje("Morate odgovoriti na 28. pitanje.");
            return;
        }

        RadioButton odgovor29Button = (RadioButton)grupa25.getSelectedToggle();
        if(odgovor29Button == null || (!pitanje29TextField1.isDisabled() && pitanje29TextField1.getText().isEmpty()) ||
                (!pitanje29TextField2.isDisabled() && pitanje29TextField2.getText().isEmpty())){
            grupa25.getToggles().forEach(toggle -> {
                RadioButton button = (RadioButton)toggle;
                button.setStyle("-fx-border-color: RED");
            });
            pitanje29TextField1.setStyle("-fx-border-color: RED");
            pitanje29TextField2.setStyle("-fx-border-color: RED");
            prikaziUpozorenje("Morate odgovoriti na 29. pitanje.");
            return;
        }
        else{
            odgovoriNaPitanja.get(29).add(odgovor29Button.getText());
            if(!pitanje29TextField1.isDisabled())
                odgovoriNaPitanja.get(29).add(pitanje29TextField1.getText().isEmpty() ? pitanje29TextField2.getText() : pitanje29TextField1.getText());
            else{
                if(isToggleGroupEnabled(grupa26))
                {
                    RadioButton odgovor29Button1 = (RadioButton)grupa26.getSelectedToggle();
                    RadioButton odgovor29Button2 = (RadioButton)grupa27.getSelectedToggle();
                    if(odgovor29Button1 == null){
                        grupa26.getToggles().forEach(toggle -> {
                            RadioButton button = (RadioButton)toggle;
                            button.setStyle("-fx-border-color: RED");
                        });
                        prikaziUpozorenje("Nepotpun odgovor na 29. pitanje.");
                        return;
                    }
                    else if(odgovor29Button2 == null){
                        grupa27.getToggles().forEach(toggle -> {
                            RadioButton button = (RadioButton)toggle;
                            button.setStyle("-fx-border-color: RED");
                        });
                        prikaziUpozorenje("Nepotpun odgovor na 29. pitanje.");
                        return;
                    }
                    else{
                        odgovoriNaPitanja.get(29).add(odgovor29Button1.getText());
                        odgovoriNaPitanja.get(29).add(odgovor29Button2.getText());
                    }
                }
            }
        }

        if(isToggleGroupEnabled(grupa28)){
            RadioButton odgovor30Button = (RadioButton)grupa28.getSelectedToggle();
            if(odgovor30Button == null || (!pitanje30TextField.isDisabled() && pitanje30TextField.getText().isEmpty())){
                grupa28.getToggles().forEach(toggle -> {
                    RadioButton button = (RadioButton)toggle;
                    button.setStyle("-fx-border-color: RED");
                });
                pitanje30TextField.setStyle("-fx-border-color: RED");
                prikaziUpozorenje("Morate odgovoriti na 30. pitanje.");
                return;
            }
            else {
                odgovoriNaPitanja.get(30).add(odgovor30Button.getText());
                if(!pitanje30TextField.isDisabled())
                    odgovoriNaPitanja.get(30).add(pitanje30TextField.getText());
            }
        }

        if(isToggleGroupEnabled(grupa29)){
            RadioButton odgovor31Button = (RadioButton)grupa29.getSelectedToggle();
            if(odgovor31Button == null){
                grupa29.getToggles().forEach(toggle -> {
                    RadioButton button = (RadioButton)toggle;
                    button.setStyle("-fx-border-color: RED");
                });
                prikaziUpozorenje("Morate odgovoriti na 31. pitanje.");
                return;
            }
            else
                odgovoriNaPitanja.get(31).add(odgovor31Button.getText());
        }

        RadioButton odgovor32Button = (RadioButton)grupa30.getSelectedToggle();
        if(odgovor32Button == null){
            grupa30.getToggles().forEach(toggle -> {
                RadioButton button = (RadioButton)toggle;
                button.setStyle("-fx-border-color: RED");
            });
            prikaziUpozorenje("Morate odgovoriti na 32. pitanje.");
            return;
        }
        else
            odgovoriNaPitanja.get(32).add(odgovor32Button.getText());

        RadioButton odgovor33Button = (RadioButton)grupa31.getSelectedToggle();
        if(odgovor33Button == null){
            grupa31.getToggles().forEach(toggle -> {
                RadioButton button = (RadioButton)toggle;
                button.setStyle("-fx-border-color: RED");
            });
            prikaziUpozorenje("Morate odgovoriti na 33. pitanje.");
            return;
        }
        else
            odgovoriNaPitanja.get(33).add(odgovor33Button.getText());

        if(isToggleGroupEnabled(grupa32)){
            RadioButton odgovor34Button = (RadioButton)grupa32.getSelectedToggle();
            if(odgovor34Button == null){
                grupa32.getToggles().forEach(toggle -> {
                    RadioButton button = (RadioButton)toggle;
                    button.setStyle("-fx-border-color: RED");
                });
                prikaziUpozorenje("Morate odgovoriti na 34. pitanje.");
                return;
            }
            else
                odgovoriNaPitanja.get(34).add(odgovor34Button.getText());
        }

        if(isToggleGroupEnabled(grupa33)){
            RadioButton odgovor35Button = (RadioButton)grupa33.getSelectedToggle();
            if(odgovor35Button == null){
                grupa33.getToggles().forEach(toggle -> {
                    RadioButton button = (RadioButton)toggle;
                    button.setStyle("-fx-border-color: RED");
                });
                prikaziUpozorenje("Morate odgovoriti na 35. pitanje.");
                return;
            }
            else
                odgovoriNaPitanja.get(35).add(odgovor35Button.getText());
        }

        if(isToggleGroupEnabled(grupa34)){
            RadioButton odgovor36Button = (RadioButton)grupa34.getSelectedToggle();
            if(odgovor36Button == null){
                grupa34.getToggles().forEach(toggle -> {
                    RadioButton button = (RadioButton)toggle;
                    button.setStyle("-fx-border-color: RED");
                });
                prikaziUpozorenje("Morate odgovoriti na 36. pitanje.");
                return;
            }
            else
                odgovoriNaPitanja.get(36).add(odgovor36Button.getText());
        }

        if(isToggleGroupEnabled(grupa35)){
            RadioButton odgovor37Button = (RadioButton)grupa35.getSelectedToggle();
            if(odgovor37Button == null){
                grupa35.getToggles().forEach(toggle -> {
                    RadioButton button = (RadioButton)toggle;
                    button.setStyle("-fx-border-color: RED");
                });
                prikaziUpozorenje("Morate odgovoriti na 37. pitanje.");
                return;
            }
            else
                odgovoriNaPitanja.get(37).add(odgovor37Button.getText());
        }

        if(isToggleGroupEnabled(grupa36)){
            RadioButton odgovor38Button = (RadioButton)grupa36.getSelectedToggle();
            if(odgovor38Button == null){
                grupa36.getToggles().forEach(toggle -> {
                    RadioButton button = (RadioButton)toggle;
                    button.setStyle("-fx-border-color: RED");
                });
                prikaziUpozorenje("Morate odgovoriti na 38. pitanje.");
                return;
            }
            else
                odgovoriNaPitanja.get(38).add(odgovor38Button.getText());
        }

        if(!pitanje39TextField.isDisabled()){
            if(!pitanje39TextField.getText().isEmpty())
                odgovoriNaPitanja.get(39).add(pitanje39TextField.getText());
        }

        if(!pitanje40TextField1.isDisabled()){
            if(!pitanje40TextField1.getText().isEmpty()){
                if(pitanje40TextField2.getText().isEmpty()) {
                    pitanje40TextField2.setStyle("-fx-border-color: RED");
                    prikaziUpozorenje("Nepotpun odgovor na 40. pitanje.");
                    return;
                }
                else {
                    odgovoriNaPitanja.get(40).add(pitanje40TextField1.getText());
                    odgovoriNaPitanja.get(40).add(pitanje40TextField2.getText());
                }

            }
            else if(!pitanje40TextField2.getText().isEmpty()){
                pitanje40TextField2.setStyle("-fx-border-color: RED");
                prikaziUpozorenje("Nepotpun odgovor na 40. pitanje.");
                return;
            }
            else if(!pitanje40TextField1.getText().isEmpty() && !pitanje40TextField2.getText().isEmpty()){
                odgovoriNaPitanja.get(40).add(pitanje40TextField1.getText());
                odgovoriNaPitanja.get(40).add(pitanje40TextField2.getText());
            }
        }

        RadioButton odgovor41Button = (RadioButton)grupa37.getSelectedToggle();
        if(odgovor41Button != null && ((!pitanje41TextField1.isDisabled() && pitanje41TextField1.getText().isEmpty()) ||
                (!pitanje41TextField2.isDisabled() && pitanje41TextField2.getText().isEmpty()) ||
                (!pitanje41TextField3.isDisabled() && pitanje41TextField3.getText().isEmpty()))){
            grupa37.getToggles().forEach(toggle -> {
                RadioButton button = (RadioButton)toggle;
                button.setStyle("-fx-border-color: RED");
            });
            pitanje41TextField1.setStyle("-fx-border-color: RED");
            pitanje41TextField2.setStyle("-fx-border-color: RED");
            pitanje41TextField3.setStyle("-fx-border-color: RED");
            prikaziUpozorenje("Nepotpun odgovor na 41. pitanje.");
            return;
        }
        else if(odgovor41Button == null && (!pitanje41TextField1.getText().isEmpty() ||
                !pitanje41TextField2.getText().isEmpty() || !pitanje41TextField3.getText().isEmpty())){
            grupa37.getToggles().forEach(toggle -> {
                RadioButton button = (RadioButton)toggle;
                button.setStyle("-fx-border-color: RED");
            });
            pitanje41TextField1.setStyle("-fx-border-color: RED");
            pitanje41TextField2.setStyle("-fx-border-color: RED");
            pitanje41TextField3.setStyle("-fx-border-color: RED");
            prikaziUpozorenje("Nepotpun odgovor na 41. pitanje.");
            return;
        }
        else if(odgovor41Button != null){
            odgovoriNaPitanja.get(41).add(odgovor41Button.getText());
            if(pitanje41TextField3.isDisabled()){
                odgovoriNaPitanja.get(41).add(pitanje41TextField1.getText());
                odgovoriNaPitanja.get(41).add(pitanje41TextField2.getText());
            }
            else
                odgovoriNaPitanja.get(41).add(pitanje41TextField3.getText());
        }

        if(pitanje42CheckBox1.isSelected())
            odgovoriNaPitanja.get(42).add(pitanje42CheckBox1.getText());
        if(pitanje42CheckBox2.isSelected())
            odgovoriNaPitanja.get(42).add(pitanje42CheckBox2.getText());
        if(pitanje42CheckBox3.isSelected())
            odgovoriNaPitanja.get(42).add(pitanje42CheckBox3.getText());
        if(pitanje42CheckBox4.isSelected())
            odgovoriNaPitanja.get(42).add(pitanje42CheckBox4.getText());
        if(pitanje42CheckBox5.isSelected())
            odgovoriNaPitanja.get(42).add(pitanje42CheckBox5.getText());
        if(pitanje42CheckBox6.isSelected())
            odgovoriNaPitanja.get(42).add(pitanje42CheckBox6.getText());
        if(pitanje42CheckBox7.isSelected())
            odgovoriNaPitanja.get(42).add(pitanje42CheckBox7.getText());
        if(pitanje42CheckBox8.isSelected())
            odgovoriNaPitanja.get(42).add(pitanje42CheckBox8.getText());
        if(pitanje42CheckBox9.isSelected())
            odgovoriNaPitanja.get(42).add(pitanje42CheckBox9.getText());
        if(pitanje42CheckBox10.isSelected())
            odgovoriNaPitanja.get(42).add(pitanje42CheckBox10.getText());
        if(odgovoriNaPitanja.get(42).isEmpty()){
            pitanje42CheckBox1.setStyle("-fx-border-color: RED");
            pitanje42CheckBox2.setStyle("-fx-border-color: RED");
            pitanje42CheckBox3.setStyle("-fx-border-color: RED");
            pitanje42CheckBox4.setStyle("-fx-border-color: RED");
            pitanje42CheckBox5.setStyle("-fx-border-color: RED");
            pitanje42CheckBox6.setStyle("-fx-border-color: RED");
            pitanje42CheckBox7.setStyle("-fx-border-color: RED");
            pitanje42CheckBox8.setStyle("-fx-border-color: RED");
            pitanje42CheckBox9.setStyle("-fx-border-color: RED");
            pitanje42CheckBox10.setStyle("-fx-border-color: RED");
            prikaziUpozorenje("Morate odgovoriti na 42. pitanje.");
            return;
        }

        RadioButton odgovor43Button = (RadioButton)grupa38.getSelectedToggle();
        if(odgovor43Button == null){
            grupa38.getToggles().forEach(toggle -> {
                RadioButton button = (RadioButton)toggle;
                button.setStyle("-fx-border-color: RED");
            });
            prikaziUpozorenje("Morate odgovoriti na 43. pitanje.");
            return;
        }
        else
            odgovoriNaPitanja.get(43).add(odgovor43Button.getText());

        if(isToggleGroupEnabled(grupa39)){
            RadioButton odgovor44Button = (RadioButton)grupa39.getSelectedToggle();
            if(odgovor44Button == null){
                grupa39.getToggles().forEach(toggle -> {
                    RadioButton button = (RadioButton)toggle;
                    button.setStyle("-fx-border-color: RED");
                });
                prikaziUpozorenje("Morate odgovoriti na 44. pitanje.");
                return;
            }
            else
                odgovoriNaPitanja.get(44).add(odgovor44Button.getText());
        }

        if(vidStepenComboBox.getSelectionModel().getSelectedItem() == null || sluhStepenComboBox.getSelectionModel().getSelectedItem() == null || sluhUzrokComboBox.getSelectionModel().getSelectedItem() == null ||
        hodStepenComboBox.getSelectionModel().getSelectedItem() == null || pamcenjeStepenComboBox.getSelectionModel().getSelectedItem() == null || pamcenjeUzrokComboBox.getSelectionModel().getSelectedItem() == null ||
        odijevanjeStepenComboBox.getSelectionModel().getSelectedItem() == null || komunikacijaStepenComboBox.getSelectionModel().getSelectedItem() == null ||
                (vidStepenComboBox.getSelectionModel().getSelectedItem().equals("Nema poteškoća") && vidUzrokComboBox.getSelectionModel().getSelectedItem() == null) ||
                (sluhStepenComboBox.getSelectionModel().getSelectedItem().equals("Nema poteškoća") && sluhUzrokComboBox.getSelectionModel().getSelectedItem() == null) ||
                (hodStepenComboBox.getSelectionModel().getSelectedItem().equals("Nema poteškoća") && hodUzrokComboBox.getSelectionModel().getSelectedItem() == null) ||
                (pamcenjeStepenComboBox.getSelectionModel().getSelectedItem().equals("Nema poteškoća") && pamcenjeUzrokComboBox.getSelectionModel().getSelectedItem() == null) ||
                (odijevanjeStepenComboBox.getSelectionModel().getSelectedItem().equals("Nema poteškoća") && odijevanjeUzrokComboBox.getSelectionModel().getSelectedItem() == null) ||
                (komunikacijaStepenComboBox.getSelectionModel().getSelectedItem().equals("Nema poteškoća") && komunikacijaUzrokComboBox.getSelectionModel().getSelectedItem() == null)){
            vidStepenComboBox.setStyle("-fx-border-color: RED");
            vidUzrokComboBox.setStyle("-fx-border-color: RED");
            sluhStepenComboBox.setStyle("-fx-border-color: RED");
            sluhUzrokComboBox.setStyle("-fx-border-color: RED");
            hodStepenComboBox.setStyle("-fx-border-color: RED");
            hodUzrokComboBox.setStyle("-fx-border-color: RED");
            pamcenjeStepenComboBox.setStyle("-fx-border-color: RED");
            pamcenjeUzrokComboBox.setStyle("-fx-border-color: RED");
            odijevanjeStepenComboBox.setStyle("-fx-border-color: RED");
            odijevanjeUzrokComboBox.setStyle("-fx-border-color: RED");
            komunikacijaStepenComboBox.setStyle("-fx-border-color: RED");
            komunikacijaUzrokComboBox.setStyle("-fx-border-color: RED");
            prikaziUpozorenje("Nepotpun odgovor na 45. pitanje");
            return;
        }
        else{
            odgovoriNaPitanja.get(45).add((String)vidStepenComboBox.getSelectionModel().getSelectedItem());
            odgovoriNaPitanja.get(45).add((String)vidUzrokComboBox.getSelectionModel().getSelectedItem());
            odgovoriNaPitanja.get(45).add((String)sluhStepenComboBox.getSelectionModel().getSelectedItem());
            odgovoriNaPitanja.get(45).add((String)sluhUzrokComboBox.getSelectionModel().getSelectedItem());
            odgovoriNaPitanja.get(45).add((String)hodStepenComboBox.getSelectionModel().getSelectedItem());
            odgovoriNaPitanja.get(45).add((String)hodUzrokComboBox.getSelectionModel().getSelectedItem());
            odgovoriNaPitanja.get(45).add((String)pamcenjeStepenComboBox.getSelectionModel().getSelectedItem());
            odgovoriNaPitanja.get(45).add((String)pamcenjeUzrokComboBox.getSelectionModel().getSelectedItem());
            odgovoriNaPitanja.get(45).add((String)odijevanjeStepenComboBox.getSelectionModel().getSelectedItem());
            odgovoriNaPitanja.get(45).add((String)odijevanjeUzrokComboBox.getSelectionModel().getSelectedItem());
            odgovoriNaPitanja.get(45).add((String)komunikacijaStepenComboBox.getSelectionModel().getSelectedItem());
            odgovoriNaPitanja.get(45).add((String)komunikacijaUzrokComboBox.getSelectionModel().getSelectedItem());
        }

        RadioButton odgovor46Button = (RadioButton)grupa40.getSelectedToggle();
        if(odgovor46Button == null){
            grupa40.getToggles().forEach(toggle -> {
                RadioButton button = (RadioButton)toggle;
                button.setStyle("-fx-border-color: RED");
            });
            prikaziUpozorenje("Morate odgovoriti na 46. pitanje.");
        }
        else
            odgovoriNaPitanja.get(46).add(odgovor46Button.getText());

        popisnica.setOdgovoriNaPitanja(odgovoriNaPitanja);
        //TODO: Poslati popisnicu na glavni server.
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
        liceTextField1.setStyle("-fx-border-color: TRANSPARENT");
        liceTextField2.setStyle("-fx-border-color: TRANSPARENT");
        liceTextField3.setStyle("-fx-border-color: TRANSPARENT");
        imeTextField.setStyle("-fx-border-color: TRANSPARENT");
        imeOcaMajkeTextField.setStyle("-fx-border-color: TRANSPARENT");
        prezimeTextField.setStyle("-fx-border-color: TRANSPARENT");
        JMBTextField.setStyle("-fx-border-color: TRANSPARENT");
        odabirPolaComboBox.setStyle("-fx-border-color: TRANSPARENT");
        grupa1.getToggles().forEach(toggle -> {
            RadioButton button = (RadioButton)toggle;
            button.setStyle("-fx-border-color: TRANSPARENT");
        });
        grupa2.getToggles().forEach(toggle -> {
            RadioButton button = (RadioButton)toggle;
            button.setStyle("-fx-border-color: TRANSPARENT");
        });
        razlogOdsustvaPrisustvaComboBox.setStyle("-fx-border-color: TRANSPARENT");
        grupa3.getToggles().forEach(toggle -> {
            RadioButton button = (RadioButton)toggle;
            button.setStyle("-fx-border-color: TRANSPARENT");
        });
        pitanje4TextField1.setStyle("-fx-border-color: TRANSPARENT");
        pitanje4TextField2.setStyle("-fx-border-color: TRANSPARENT");
        grupa4.getToggles().forEach(toggle -> {
            RadioButton button = (RadioButton)toggle;
            button.setStyle("-fx-border-color: TRANSPARENT");
        });
        pitanje5TextField2.setStyle("-fx-border-color: TRANSPARENT");
        grupa5.getToggles().forEach(toggle -> {
            RadioButton button = (RadioButton)toggle;
            button.setStyle("-fx-border-color: TRANSPARENT");
        });
        pitanje6TextField1.setStyle("-fx-border-color: TRANSPARENT");
        pitanje6TextField2.setStyle("-fx-border-color: TRANSPARENT");
        pitanje6TextField3.setStyle("-fx-border-color: TRANSPARENT");
        grupa6.getToggles().forEach(toggle -> {
            RadioButton button = (RadioButton)toggle;
            button.setStyle("-fx-border-color: TRANSPARENT");
        });
        grupa7.getToggles().forEach(toggle -> {
            RadioButton button = (RadioButton)toggle;
            button.setStyle("-fx-border-color: TRANSPARENT");
        });
        pitanje8TextField1.setStyle("-fx-border-color: TRANSPARENT");
        pitanje8TextField2.setStyle("-fx-border-color: TRANSPARENT");
        pitanje8TextField3.setStyle("-fx-border-color: TRANSPARENT");
        grupa8.getToggles().forEach(toggle -> {
            RadioButton button = (RadioButton)toggle;
            button.setStyle("-fx-border-color: TRANSPARENT");
        });
        pitanje9TextField1.setStyle("-fx-border-color: TRANSPARENT");
        pitanje9TextField2.setStyle("-fx-border-color: TRANSPARENT");
        pitanje9TextField3.setStyle("-fx-border-color: TRANSPARENT");

        grupa9.getToggles().forEach(toggle -> {
            RadioButton button = (RadioButton)toggle;
            button.setStyle("-fx-border-color: TRANSPARENT");
        });
        grupa10.getToggles().forEach(toggle -> {
            RadioButton button = (RadioButton)toggle;
            button.setStyle("-fx-border-color: TRANSPARENT");
        });
        pitanje11TextField1.setStyle("-fx-border-color: TRANSPARENT");
        pitanje11TextField2.setStyle("-fx-border-color: TRANSPARENT");
        pitanje11TextField3.setStyle("-fx-border-color: TRANSPARENT");
        pitanje11DatePicker.setStyle("-fx-border-color: TRANSPARENT");
        grupa11.getToggles().forEach(toggle -> {
            RadioButton button = (RadioButton)toggle;
            button.setStyle("-fx-border-color: TRANSPARENT");
        });
        pitanje12TextField.setStyle("-fx-border-color: TRANSPARENT");
        pitanje12ComboBox.setStyle("-fx-border-color: TRANSPARENT");
        pitanje12DatePicker.setStyle("-fx-border-color: TRANSPARENT");
        grupa12.getToggles().forEach(toggle -> {
            RadioButton button = (RadioButton)toggle;
            button.setStyle("-fx-border-color: TRANSPARENT");
        });
        pitanje13TextField1.setStyle("-fx-border-color: TRANSPARENT");
        pitanje13TextField2.setStyle("-fx-border-color: TRANSPARENT");
        pitanje13TextField3.setStyle("-fx-border-color: TRANSPARENT");
        grupa13.getToggles().forEach(toggle -> {
            RadioButton button = (RadioButton)toggle;
            button.setStyle("-fx-border-color: TRANSPARENT");
        });
        grupa14.getToggles().forEach(toggle -> {
            RadioButton button = (RadioButton)toggle;
            button.setStyle("-fx-border-color: TRANSPARENT");
        });
        grupa15.getToggles().forEach(toggle -> {
            RadioButton button = (RadioButton)toggle;
            button.setStyle("-fx-border-color: TRANSPARENT");
        });
        pitanje16TextField1.setStyle("-fx-border-color: TRANSPARENT");
        pitanje16TextField2.setStyle("-fx-border-color: TRANSPARENT");
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
        pitanje19TextField.setStyle("-fx-border-color: TRANSPARENT");
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
        pitanje23TextField.setStyle("-fx-border-color: TRANSPARENT");
        pitanje23TextField.setStyle("-fx-border-color: TRANSPARENT");
        pitanje24DatePicker1.setStyle("-fx-border-color: TRANSPARENT");
        pitanje24DatePicker2.setStyle("-fx-border-color: TRANSPARENT");
        pitanje24DatePicker3.setStyle("-fx-border-color: TRANSPARENT");
        grupa22.getToggles().forEach(toggle -> {
            RadioButton button = (RadioButton)toggle;
            button.setStyle("-fx-border-color: TRANSPARENT");
        });
        pitanje25TextField.setStyle("-fx-border-color: TRANSPARENT");
        grupa23.getToggles().forEach(toggle -> {
            RadioButton button = (RadioButton)toggle;
            button.setStyle("-fx-border-color: TRANSPARENT");
        });
        pitanje26TextField.setStyle("-fx-border-color: TRANSPARENT");
        grupa24.getToggles().forEach(toggle -> {
            RadioButton button = (RadioButton)toggle;
            button.setStyle("-fx-border-color: TRANSPARENT");
        });
        pitanje27TextField.setStyle("-fx-border-color: TRANSPARENT");
        pitanje28CheckBox1.setStyle("-fx-border-color: TRANSPARENT");
        pitanje28CheckBox2.setStyle("-fx-border-color: TRANSPARENT");
        pitanje28CheckBox3.setStyle("-fx-border-color: TRANSPARENT");
        pitanje28CheckBox4.setStyle("-fx-border-color: TRANSPARENT");
        pitanje28CheckBox5.setStyle("-fx-border-color: TRANSPARENT");
        grupa25.getToggles().forEach(toggle -> {
            RadioButton button = (RadioButton)toggle;
            button.setStyle("-fx-border-color: TRANSPARENT");
        });
        pitanje29TextField1.setStyle("-fx-border-color: TRANSPARENT");
        pitanje29TextField2.setStyle("-fx-border-color: TRANSPARENT");
        grupa26.getToggles().forEach(toggle -> {
            RadioButton button = (RadioButton)toggle;
            button.setStyle("-fx-border-color: TRANSPARENT");
        });
        grupa27.getToggles().forEach(toggle -> {
            RadioButton button = (RadioButton)toggle;
            button.setStyle("-fx-border-color: TRANSPARENT");
        });
        grupa28.getToggles().forEach(toggle -> {
            RadioButton button = (RadioButton)toggle;
            button.setStyle("-fx-border-color: TRANSPARENT");
        });
        pitanje30TextField.setStyle("-fx-border-color: TRANSPARENT");
        grupa29.getToggles().forEach(toggle -> {
            RadioButton button = (RadioButton)toggle;
            button.setStyle("-fx-border-color: TRANSPARENT");
        });
        grupa30.getToggles().forEach(toggle -> {
            RadioButton button = (RadioButton)toggle;
            button.setStyle("-fx-border-color: TRANSPARENT");
        });
        grupa31.getToggles().forEach(toggle -> {
            RadioButton button = (RadioButton)toggle;
            button.setStyle("-fx-border-color: TRANSPARENT");
        });
        grupa32.getToggles().forEach(toggle -> {
            RadioButton button = (RadioButton)toggle;
            button.setStyle("-fx-border-color: TRANSPARENT");
        });
        grupa33.getToggles().forEach(toggle -> {
            RadioButton button = (RadioButton)toggle;
            button.setStyle("-fx-border-color: TRANSPARENT");
        });
        grupa34.getToggles().forEach(toggle -> {
            RadioButton button = (RadioButton)toggle;
            button.setStyle("-fx-border-color: TRANSPARENT");
        });
        grupa35.getToggles().forEach(toggle -> {
            RadioButton button = (RadioButton)toggle;
            button.setStyle("-fx-border-color: TRANSPARENT");
        });
        grupa36.getToggles().forEach(toggle -> {
            RadioButton button = (RadioButton)toggle;
            button.setStyle("-fx-border-color: TRANSPARENT");
        });
        pitanje39TextField.setStyle("-fx-border-color: TRANSPARENT");
        pitanje40TextField1.setStyle("-fx-border-color: TRANSPARENT");
        pitanje40TextField2.setStyle("-fx-border-color: TRANSPARENT");
        grupa37.getToggles().forEach(toggle -> {
            RadioButton button = (RadioButton)toggle;
            button.setStyle("-fx-border-color: TRANSPARENT");
        });
        pitanje41TextField1.setStyle("-fx-border-color: TRANSPARENT");
        pitanje41TextField2.setStyle("-fx-border-color: TRANSPARENT");
        pitanje41TextField3.setStyle("-fx-border-color: TRANSPARENT");
        pitanje42CheckBox1.setStyle("-fx-border-color: TRANSPARENT");
        pitanje42CheckBox2.setStyle("-fx-border-color: TRANSPARENT");
        pitanje42CheckBox3.setStyle("-fx-border-color: TRANSPARENT");
        pitanje42CheckBox4.setStyle("-fx-border-color: TRANSPARENT");
        pitanje42CheckBox5.setStyle("-fx-border-color: TRANSPARENT");
        pitanje42CheckBox6.setStyle("-fx-border-color: TRANSPARENT");
        pitanje42CheckBox7.setStyle("-fx-border-color: TRANSPARENT");
        pitanje42CheckBox8.setStyle("-fx-border-color: TRANSPARENT");
        pitanje42CheckBox9.setStyle("-fx-border-color: TRANSPARENT");
        pitanje42CheckBox10.setStyle("-fx-border-color: TRANSPARENT");
        grupa38.getToggles().forEach(toggle -> {
            RadioButton button = (RadioButton)toggle;
            button.setStyle("-fx-border-color: TRANSPARENT");
        });
        grupa39.getToggles().forEach(toggle -> {
            RadioButton button = (RadioButton)toggle;
            button.setStyle("-fx-border-color: TRANSPARENT");
        });
        vidStepenComboBox.setStyle("-fx-border-color: TRANSPARENT");
        vidUzrokComboBox.setStyle("-fx-border-color: TRANSPARENT");
        sluhStepenComboBox.setStyle("-fx-border-color: TRANSPARENT");
        sluhUzrokComboBox.setStyle("-fx-border-color: TRANSPARENT");
        hodStepenComboBox.setStyle("-fx-border-color: TRANSPARENT");
        hodUzrokComboBox.setStyle("-fx-border-color: TRANSPARENT");
        pamcenjeStepenComboBox.setStyle("-fx-border-color: TRANSPARENT");
        pamcenjeUzrokComboBox.setStyle("-fx-border-color: TRANSPARENT");
        odijevanjeStepenComboBox.setStyle("-fx-border-color: TRANSPARENT");
        odijevanjeUzrokComboBox.setStyle("-fx-border-color: TRANSPARENT");
        komunikacijaStepenComboBox.setStyle("-fx-border-color: TRANSPARENT");
        komunikacijaUzrokComboBox.setStyle("-fx-border-color: TRANSPARENT");
        grupa40.getToggles().forEach(toggle -> {
            RadioButton button = (RadioButton)toggle;
            button.setStyle("-fx-border-color: TRANSPARENT");
        });
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
}
