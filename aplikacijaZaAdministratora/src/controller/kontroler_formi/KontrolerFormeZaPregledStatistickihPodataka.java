package controller.kontroler_formi;

import eCensus.rest.client.AdministratorGlavniServerKlijent;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import model.korisnicki_nalozi.AdministratorAgencije;
import model.korisnicki_nalozi.ClanPKLS;
import model.korisnicki_nalozi.OGInstruktor;
import org.javatuples.Pair;
import test.Aplikacija;
import util.OpstineCollection;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.*;
import java.util.function.Function;
import java.util.logging.Level;

public class KontrolerFormeZaPregledStatistickihPodataka implements Initializable {
    public ChoiceBox<String> ChoiceBox1;
    public ChoiceBox<String> ChoiceBox2;
    public ChoiceBox<String> ChoiceBox3;
    public ChoiceBox<String> ChoiceBox4;
    public ChoiceBox<String> ChoiceBox5;
    public Label label1;
    public Label label2;
    public Label label3;
    public Label label4;
    public Label label5;
    public ChoiceBox<String> statistikaChoiceBox;
    public Button statistikaButton;
    public Rectangle okvir1;
    public Rectangle okvir2;
    public Label rezultatNaslovLabel;
    public Label parametriNaslovLabel;
    public Label rezultatLabel;
    public Label naslov;
    public GridPane grid;

    private final List<String> entiteti = Arrays.asList("Federacija Bosne i Hercegovine", "Republika Srpska", "Distrikt Brčko");
    private final List<String> bracnaStanja = Arrays.asList("Nikad oženjen/udata", "Oženjen/udata", "Razveden/razvedena", "Udovac/udovica");
    private final List<String> polovi = Arrays.asList("Muski", "Zenski");
    private final List<String> nacionalnePripadnosti = Arrays.asList("Bošnjak/Bošnjakinja", "Hrvat/Hrvatica", "Srbin/Srpkinja", "Drugo", "Ne izjasnjava se");
    private final List<String> vjeroispovijesti = Arrays.asList("Islamska", "Katolička", "Pravoslavna", "Agnostik", "Ateist", "Drugo", "Ne izjašnjava se");
    private final List<String> godine = new ArrayList<>();
    private final List<String> opstine = new ArrayList<>(OpstineCollection.getOpstine());
    private final List<String> brojeviZivorodjeneDjece = new ArrayList<>();
    private final List<String> maternjiJezici = Arrays.asList("Bosanski", "Hrvatski", "Srpski", "Drugo");
    private final List<String> pismenosti = Arrays.asList("Pismen", "Polupismen", "Nepismen");
    private final List<String> zavrseneSkole = Arrays.asList("Bez ikakvog obrazovanja","Nepotpuno osnovno obrazovanje",
            "Osnovna škola", "Srednja škola", "Specijalizacija poslije srednje škole", "Viša škola i 1. stepen fakulteta",
            "Visoka škola/fakultet/akademija/univerzitet");
    private final List<String> brojClanova = new ArrayList<>();
    private final List<String> poljoprivredneAktivnosti = Arrays.asList("Da", "Ne");
    private final List<String> prodajaPoljoprivrednihAktivnosti = Arrays.asList("Da", "Ne");
    private final List<String> brojeviStanovaUZgradi = new ArrayList<>();
    private final List<String> brojeviSobaUStanu = new ArrayList<>();

    private final Function<Pair<ChoiceBox,List<String>>, String> f = (Pair<ChoiceBox,List<String>> pair) ->
            ((ChoiceBox)pair.getValue(0)).getValue().equals("Svi") ? "%" :
                    String.valueOf(((List<String>)pair.getValue(1)).indexOf(((ChoiceBox)pair.getValue(0)).getValue()));

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        for(int i=0; i<121; i++) godine.add(String.valueOf(i));
        for(int i=0; i<20; i++) brojeviZivorodjeneDjece.add(String.valueOf(i));
        for(int i=1; i<20; i++) brojClanova.add(String.valueOf(i));
        for(int i=0; i<100; i++) brojeviStanovaUZgradi.add(String.valueOf(i));
        for(int i=1; i<20; i++) brojeviSobaUStanu.add(String.valueOf(i));

        showElements(false);

        GridPane.setMargin(statistikaChoiceBox, new Insets(200,0,0,0));
        GridPane.setMargin(naslov, new Insets(200,0,0,80));

        statistikaChoiceBox.getItems().addAll(FXCollections.observableList(Arrays.asList(
                "Broj stanovnika prema pojedinačnim godinama starosti i polu",
                "Broj stanovnika prema bračnom statusu i polu",
                "Broj ženskog stanovništva prema broju živorođene djece",
                "Broj stanovnika prema nacionalnoj pripadnosti",
                "Broj stanovnik prema vjeroispovjesti",
                "Broj stanovnika prema maternjem jeziku",
                "Broj stanovnika prema pismenosti",
                "Broj stanovnika prema zavrsenoj školi",
                "Broj stanovnika prema kompjuterskoj pismenosti",
                "Broj domaćinstava prema broju članova",
                "Broj domaćinstava prema poljoprivrednoj aktivnosti",
                "Broj zgrada prema broju stanova",
                "Broj stanova prema broju soba",
                "Površina stanova prema broju soba")));
        statistikaChoiceBox.setOnAction(e->
        {
            showElements(true);
            GridPane.setMargin(statistikaChoiceBox, new Insets(0,0,0,0));
            GridPane.setMargin(naslov, new Insets(0,0,0,0));
            int selected = statistikaChoiceBox.getSelectionModel().getSelectedIndex();
            switch (selected)
            {
                case 0:
                {
                    //public int getBrojStanovnikaPremaPojedinacnimGodinamaStarostiIPolu
                    // (String idEntiteta, String idOpstine, String starost, String pol)

                    setujParametre(4,
                            new String[] {"Entitet:", "Opština:", "Starost:", "Pol:"},
                            new List[] { entiteti, opstine, godine, polovi });
                }
                break;
                case 1:
                {
                    // getBrojStanovnikaPremaPremaBracnomStatusuIPolu
                    // (String idEntiteta, String idOpstine, String status, String pol)

                    setujParametre(4,
                            new String[] {"Entitet:", "Opština:", "Bračni status:","Pol:"},
                            new List[] { entiteti, opstine, bracnaStanja , polovi });
                }
                break;
                case 2:
                {
                    //getBrojZenskogStanovnistvaPremaBrojuZivorodjeneDjece
                    // (String idEntiteta,String idOpstine, String brojZivorodjeneDjece)

                    setujParametre(3,
                            new String[] {"Entitet:", "Opština:", "Broj živorođene djece:"},
                            new List[] { entiteti, opstine, brojeviZivorodjeneDjece });
                }
                break;
                case 3:
                {
                    // getBrojStanovnikaPremaNacionalnojPripadnosti
                    // (String idEntiteta, String idOpstine, String nacionalnaPripadnost, String pol)

                    setujParametre(4,
                            new String[] {"Entitet:", "Opština:", "Nacionalna pripadnost:", "Pol:"},
                            new List[] { entiteti, opstine, nacionalnePripadnosti, polovi });
                }
                break;
                case 4:
                {
                    // getBrojStanovnikPremaVjeroispovjesti
                    // (String idEntiteta, String idOpstine, String vjeroispovjest, String pol)

                    setujParametre(4,
                            new String[] {"Entitet:", "Opština:", "Vjeroispovijest:", "Pol:"},
                            new List[] { entiteti, opstine, vjeroispovijesti, polovi });
                }
                break;
                case 5:
                {
                    // getBrojStanovnikaPremaMaternjemJeziku
                    // (String idEntiteta, String idOpstine, String maternjiJezik, String pol)

                    setujParametre(4,
                            new String[] { "Entitet:", "Opština:", "Maternji jezik:", "Pol:"},
                            new List[] { entiteti, opstine, maternjiJezici, polovi });
                }
                break;
                case 6:
                {
                    // getBrojStanovnikaPremaPismenosti
                    // (String idEntiteta, String idOpstine, String pismenost, String pol)

                    setujParametre(4,
                            new String[] { "Entitet:", "Opština:", "Pismenost:", "Pol:"},
                            new List[] { entiteti, opstine, pismenosti, polovi });

                }
                break;
                case 7:
                {
                    // getBrojStanovnikaPremaZavrsenojSkoli
                    // (String idEntiteta, String idOpstine, String zavrsenaSkola, String pol)

                    setujParametre(4,
                            new String[] { "Entitet:", "Opština:", "Završena škola:", "Pol:"},
                            new List[] { entiteti, opstine, zavrseneSkole, polovi });
                }
                break;
                case 8:
                {
                    // getBrojStanovnikaPremaKompjuterskojPismenosti
                    // (String idEntiteta, String idOpstine, String kompjuterskaPismenost, String pol)

                    setujParametre(4,
                            new String[] { "Entitet:", "Opština:", "Kompjuterska pismenost:", "Pol:"},
                            new List[] { entiteti, opstine, pismenosti, polovi });
                }
                break;
                case 9:
                {
                    // getBrojDomacinstavaPremaBrojuClanova
                    // (String idEntiteta, String idOpstine, String brojClanova)

                    setujParametre(3,
                            new String[] { "Entitet:", "Opština:", "Broj clanova:"},
                            new List[] { entiteti, opstine, brojClanova, polovi });
                }
                break;
                case 10:
                {
                    // getBrojDomacinstavaPremaPoljoprivrednojAktivnosti
                    // (String idEntiteta, String idOpstine, String poljoprivreda, String prodaja)

                    setujParametre(4,
                            new String[] { "Entitet:", "Opština:", "Poljoprivredna aktivnost:", "Prodaja:"},
                            new List[] { entiteti, opstine, poljoprivredneAktivnosti, prodajaPoljoprivrednihAktivnosti });
                }
                break;
                case 11:
                {
                    // getBrojZgradaPremaBrojuStanova
                    // (String idEntiteta, String idOpstine, String brojStanova)

                    setujParametre(3,
                            new String[] { "Entitet:", "Opština:", "Broj stanova u zgradi:" },
                            new List[] { entiteti, opstine, brojeviStanovaUZgradi });
                }
                break;
                case 12:
                case 13: {
                    // 12: getBrojStanovaPremaBrojuSoba
                    // 13: getPovrsinaStanovaPremaBrojuSoba
                    // (String idEntiteta, String idOpstine, String brojSoba)

                    setujParametre(3,
                            new String[] { "Entitet:", "Opština:", "Broj soba u stanu:" },
                            new List[] { entiteti, opstine, brojeviSobaUStanu });
                }
                break;
                default:
                    System.out.println("default");
                    break;
            }
        });
    }

    public void setujParametre(int brojParametara, String[] naslovi, List<String>[] liste)
    {
        if(brojParametara>=3 && brojParametara<=5 && naslovi.length != brojParametara && liste.length != brojParametara)
            return;
        label1.setText(naslovi[0]);
        label2.setText(naslovi[1]);
        label3.setText(naslovi[2]);

        ChoiceBox1.getItems().clear();
        ChoiceBox1.getItems().addAll(liste[0]);
        ChoiceBox1.getItems().add("Svi");

        ChoiceBox2.getItems().clear();
        ChoiceBox2.getItems().addAll(liste[1]);
        ChoiceBox2.getItems().add("Svi");

        ChoiceBox3.getItems().clear();
        ChoiceBox3.getItems().addAll(liste[2]);
        ChoiceBox3.getItems().add("Svi");

        ChoiceBox1.setValue("Svi");
        ChoiceBox2.setValue("Svi");
        ChoiceBox3.setValue("Svi");

        if(brojParametara==3)
        {
            ChoiceBox4.setVisible(false);
            label4.setVisible(false);
            ChoiceBox5.setVisible(false);
            label5.setVisible(false);
        }
        else
        {
            label4.setText(naslovi[3]);

            ChoiceBox4.getItems().clear();
            ChoiceBox4.getItems().addAll(liste[3]);
            ChoiceBox4.getItems().add("Svi");

            ChoiceBox4.setValue("Svi");

            if(brojParametara==5)
            {
                label5.setText(naslovi[4]);

                ChoiceBox5.getItems().clear();
                ChoiceBox5.getItems().addAll(liste[4]);
                ChoiceBox5.getItems().add("Svi");

                ChoiceBox4.setValue("Svi");
            }
            else
            {
                ChoiceBox5.setVisible(false);
                label5.setVisible(false);
            }
        }


    }

    public void prikažiStatistiku(ActionEvent actionEvent) {

        int selected = statistikaChoiceBox.getSelectionModel().getSelectedIndex();

        AdministratorGlavniServerKlijent admin= new AdministratorGlavniServerKlijent(KontrolerFormeZaPrijavu.getTrenutniKorisnik());

        int rezultat = 0;

        switch (selected)
        {
            case 0:
            {
//                getBrojStanovnikaPremaPojedinacnimGodinamaStarostiIPolu
//                (String idEntiteta, String idOpstine, String starost, String pol)

                rezultat = admin.getBrojStanovnikaPremaPojedinacnimGodinamaStarostiIPolu(
                        f.apply(new Pair<>(ChoiceBox1, entiteti)),
                        f.apply(new Pair<>(ChoiceBox2, new ArrayList<>(OpstineCollection.getOpstine()))),
                        ChoiceBox3.getValue().replace("Svi", "%"),
                        ChoiceBox4.getValue().replace("Svi", "%"));
            }
            break;
            case 1:
            {
                // getBrojStanovnikaPremaPremaBracnomStatusuIPolu
                // (String idEntiteta, String idOpstine, String status, String pol)

                rezultat = admin.getBrojStanovnikaPremaPremaBracnomStatusuIPolu(
                        f.apply(new Pair<>(ChoiceBox1, entiteti)),
                        f.apply(new Pair<>(ChoiceBox2, new ArrayList<>(OpstineCollection.getOpstine()))),
                        ChoiceBox3.getValue().replace("Svi", "%"),
                        ChoiceBox4.getValue().replace("Svi", "%"));
            }
            break;
            case 2:
            {
                // getBrojZenskogStanovnistvaPremaBrojuZivorodjeneDjece
                // (String idEntiteta,String idOpstine, String brojZivorodjeneDjece)

                rezultat = admin.getBrojZenskogStanovnistvaPremaBrojuZivorodjeneDjece(
                        f.apply(new Pair<>(ChoiceBox1, entiteti)),
                        f.apply(new Pair<>(ChoiceBox2, new ArrayList<>(OpstineCollection.getOpstine()))),
                        ChoiceBox3.getValue().replace("Svi", "%"));

            }
            break;
            case 3:
            {
                // getBrojStanovnikaPremaNacionalnojPripadnosti
                // (String idEntiteta, String idOpstine, String nacionalnaPripadnost, String pol)

                rezultat = admin.getBrojStanovnikaPremaNacionalnojPripadnosti(
                        f.apply(new Pair<>(ChoiceBox1, entiteti)),
                        f.apply(new Pair<>(ChoiceBox2, new ArrayList<>(OpstineCollection.getOpstine()))),
                        ChoiceBox3.getValue().replace("Svi", "%"),
                        ChoiceBox4.getValue().replace("Svi", "%"));
            }
            break;
            case 4:
            {
                // getBrojStanovnikPremaVjeroispovjesti
                // (String idEntiteta, String idOpstine, String vjeroispovjest, String pol)

                rezultat = admin.getBrojStanovnikPremaVjeroispovjesti(
                        f.apply(new Pair<>(ChoiceBox1, entiteti)),
                        f.apply(new Pair<>(ChoiceBox2, new ArrayList<>(OpstineCollection.getOpstine()))),
                        ChoiceBox3.getValue().replace("Svi", "%"),
                        ChoiceBox4.getValue().replace("Svi", "%"));
            }
            break;
            case 5:
            {
                // getBrojStanovnikaPremaMaternjemJeziku
                // (String idEntiteta, String idOpstine, String maternjiJezik, String pol)

                rezultat = admin.getBrojStanovnikaPremaMaternjemJeziku(
                        f.apply(new Pair<>(ChoiceBox1, entiteti)),
                        f.apply(new Pair<>(ChoiceBox2, new ArrayList<>(OpstineCollection.getOpstine()))),
                        ChoiceBox3.getValue().replace("Svi", "%"),
                        ChoiceBox4.getValue().replace("Svi", "%"));
            }
            break;
            case 6:
            {
                // getBrojStanovnikaPremaPismenosti
                // (String idEntiteta, String idOpstine, String pismenost, String pol)

                rezultat = admin.getBrojStanovnikaPremaPismenosti(
                        f.apply(new Pair<>(ChoiceBox1, entiteti)),
                        f.apply(new Pair<>(ChoiceBox2, new ArrayList<>(OpstineCollection.getOpstine()))),
                        ChoiceBox3.getValue().replace("Svi", "%"),
                        ChoiceBox4.getValue().replace("Svi", "%"));
            }
            break;
            case 7:
            {
                // getBrojStanovnikaPremaZavrsenojSkoli
                // (String idEntiteta, String idOpstine, String zavrsenaSkola, String pol)

                rezultat = admin.getBrojStanovnikaPremaZavrsenojSkoli(
                        f.apply(new Pair<>(ChoiceBox1, entiteti)),
                        f.apply(new Pair<>(ChoiceBox2, new ArrayList<>(OpstineCollection.getOpstine()))),
                        ChoiceBox3.getValue().replace("Svi", "%"),
                        ChoiceBox4.getValue().replace("Svi", "%"));
            }
            break;
            case 8:
            {
                // getBrojStanovnikaPremaKompjuterskojPismenosti
                // (String idEntiteta, String idOpstine, String kompjuterskaPismenost, String pol)

                rezultat = admin.getBrojStanovnikaPremaKompjuterskojPismenosti(
                        f.apply(new Pair<>(ChoiceBox1, entiteti)),
                        f.apply(new Pair<>(ChoiceBox2, new ArrayList<>(OpstineCollection.getOpstine()))),
                        ChoiceBox3.getValue().replace("Svi", "%"),
                        ChoiceBox4.getValue().replace("Svi", "%"));
            }
            break;
            case 9:
            {
                // getBrojDomacinstavaPremaBrojuClanova
                // (String idEntiteta, String idOpstine, String brojClanova)

                rezultat = admin.getBrojDomacinstavaPremaBrojuClanova(
                        f.apply(new Pair<>(ChoiceBox1, entiteti)),
                        f.apply(new Pair<>(ChoiceBox2, new ArrayList<>(OpstineCollection.getOpstine()))),
                        ChoiceBox3.getValue().replace("Svi", "%"));
            }
            break;
            case 10:
            {
                // getBrojDomacinstavaPremaPoljoprivrednojAktivnosti
                // (String idEntiteta, String idOpstine, String poljoprivreda, String prodaja)

                rezultat = admin.getBrojDomacinstavaPremaPoljoprivrednojAktivnosti(
                            f.apply(new Pair<>(ChoiceBox1, entiteti)),
                            f.apply(new Pair<>(ChoiceBox2, new ArrayList<>(OpstineCollection.getOpstine()))),
                            ChoiceBox3.getValue().replace("Svi", "%"),
                            ChoiceBox4.getValue().replace("Svi", "%"));
            }
            break;
            case 11:
            {
                // getBrojZgradaPremaBrojuStanova
                // (String idEntiteta, String idOpstine, String brojStanova)

                rezultat = admin.getBrojZgradaPremaBrojuStanova(
                        f.apply(new Pair<>(ChoiceBox1, entiteti)),
                        f.apply(new Pair<>(ChoiceBox2, new ArrayList<>(OpstineCollection.getOpstine()))),
                        ChoiceBox3.getValue().replace("Svi", "%"));

            }
            break;
            case 12:
            {
                // getBrojStanovaPremaBrojuSoba
                // (String idEntiteta, String idOpstine, String brojSoba)

                rezultat = admin.getBrojStanovaPremaBrojuSoba(
                        f.apply(new Pair<>(ChoiceBox1, entiteti)),
                        f.apply(new Pair<>(ChoiceBox2, new ArrayList<>(OpstineCollection.getOpstine()))),
                        ChoiceBox3.getValue().replace("Svi", "%"));
            }
            break;
            case 13:
            {
                // getPovrsinaStanovaPremaBrojuSoba
                // (String idEntiteta, String idOpstine, String brojSoba)

                rezultat = admin.getPovrsinaStanovaPremaBrojuSoba(
                        f.apply(new Pair<>(ChoiceBox1, entiteti)),
                        f.apply(new Pair<>(ChoiceBox2, new ArrayList<>(OpstineCollection.getOpstine()))),
                        ChoiceBox3.getValue().replace("Svi", "%"));
            }
            break;
            default:
            {
                    System.out.println("default");
            }
        }
        rezultatLabel.setText(String.valueOf(rezultat));

    }

    private void showElements(boolean value)
    {
        ChoiceBox1.setVisible(value);
        ChoiceBox2.setVisible(value);
        ChoiceBox3.setVisible(value);
        ChoiceBox4.setVisible(value);
        ChoiceBox5.setVisible(value);
        label1.setVisible(value);
        label2.setVisible(value);
        label3.setVisible(value);
        label4.setVisible(value);
        label5.setVisible(value);
        okvir1.setVisible(value);
        okvir2.setVisible(value);
        rezultatLabel.setVisible(value);
        rezultatNaslovLabel.setVisible(value);
        statistikaButton.setVisible(value);
        parametriNaslovLabel.setVisible(value);
    }
    public void povratak(ActionEvent actionEvent) {

        String retPath;
        if (KontrolerFormeZaPrijavu.getTrenutniKorisnik() instanceof ClanPKLS) {
            retPath = "/view/FormaZaRadClanaPKLS.fxml";
        } else if (KontrolerFormeZaPrijavu.getTrenutniKorisnik() instanceof OGInstruktor) {
            retPath = "/view/FormaZaRadOGInstruktora.fxml";
        } else {
            retPath = "/view/FormaZaRadAdministratoraAgencije.fxml";
        }

        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(retPath));
        } catch (IOException e) {
            Aplikacija.connLogger.getLogger().log(Level.WARNING, "Neuspješno učitavanje forme.");
        }
        Aplikacija.getStage().setScene(new Scene(root));
    }
}
