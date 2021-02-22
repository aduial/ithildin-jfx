package aduial.ithildin.controller;

import aduial.ithildin.entity.*;
import aduial.ithildin.repository.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import javafx.scene.web.WebView;
import javafx.util.StringConverter;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author Lúthien
 * ᴺS. !mabed-, y]wl2 v. “to ask [a question]” [created by Fiona Jallings, NGNS]
 */
@Component
@FxmlView
public class Mabed{

    private String table = "<table style='margin-top:3px; border: 1px solid black; border-collapse: collapse;'>";
    private String tr    = "<tr style='border: 1px solid black;'>";
    private String th    = "<th style='border: 1px solid black;'>";
    private String th10  = "<th style='border: 1px solid black; width: 10%;'>";
    private String th15  = "<th style='border: 1px solid black; width: 15%;'>";
    private String td    = "<td style='border: 1px solid black; padding: 2 4px;'>";

    @FXML private TextField                        searchTextField;
    @FXML private TableView<SimpLexicon>           matchTable;
    @FXML private TableColumn<SimpLexicon, Long>   idColumn;
    @FXML private TableColumn<SimpLexicon, String> formColumn;
    @FXML private TableColumn<SimpLexicon, String> glossColumn;
    @FXML private TableColumn<SimpLexicon, Long>   entrytypeIdColumn;
    @FXML private ToggleButton                     glossToggleButton;
    @FXML private ComboBox<Language>               languageChooser;
    @FXML private WebView                          primaryWebView;
    @FXML private WebView                          secondaryWebView;
    @FXML private ToggleButton                     glsToggleButton;
    @FXML private ToggleButton                     refToggleButton;
    @FXML private ToggleButton                     drvToggleButton;
    @FXML private ToggleButton                     iflToggleButton;
    @FXML private ToggleButton                     elmToggleButton;
    @FXML private ToggleButton                     cogToggleButton;

    private boolean searchGlosses = false;

    @Autowired
    private LexiconRepo lexiconRepo;
    private Lexicon     selectedLexicon;

    @Autowired
    private RefRepo             refRepo;
    private ObservableList<Ref> refList;

    @Autowired
    private SimpLexiconRepo simpLexiconRepo;

    @Autowired
    private LanguageRepo languageRepo;

    @Autowired
    private SpeechFormRepo speechFormRepo;

    @Autowired
    private EntryNoteRepo entryNoteRepo;

    @Autowired
    private RefGlossRepo refGlossRepo;

    @Autowired
    private RefDerivRepo refDerivRepo;

    @Autowired
    private RefInflectRepo refInflectRepo;

    @Autowired
    private RefElementRepo refElementRepo;

    @Autowired
    private RefCognateRepo refCognateRepo;

    private Entry   parent;
    private WebView wv1;
    private WebView wv2;

    private boolean glsVisible = false;
    private boolean refVisible = false;
    private boolean drvVisible = false;
    private boolean iflVisible = false;
    private boolean elmVisible = false;
    private boolean cogVisible = false;


    public void display(WebView wv1, WebView wv2) {
        this.wv1 = wv1;
        this.wv2 = wv2;
        writeContent();
    }

    //For MultiThreading
    private Executor exec;

    @FXML
    public void initialize() {
        matchTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        populateLanguageChooser(FXCollections.observableArrayList(languageRepo.findLanguagesByIdIsLessThanAndParentIdIsNotNull(1000L)));

        //For multithreading: Create executor that uses daemon threads:
        exec = Executors.newCachedThreadPool((runnable) -> {
            Thread t = new Thread(runnable);
            t.setDaemon(true);
            return t;
        });

        idColumn.setCellValueFactory(new PropertyValueFactory<SimpLexicon,Long>("entryId"));
        formColumn.setCellValueFactory(new PropertyValueFactory<SimpLexicon,String>("form"));
        glossColumn.setCellValueFactory(new PropertyValueFactory<SimpLexicon,String>("gloss"));
        entrytypeIdColumn.setCellValueFactory(new PropertyValueFactory<SimpLexicon,Long>("entryTypeId"));
        idColumn.setVisible(false);
        entrytypeIdColumn.setVisible(false);

        matchTable.setRowFactory((TableView<SimpLexicon> row) -> new TableRow<SimpLexicon>(){
            public void updateItem(SimpLexicon entry, boolean empty) {
                super.updateItem(entry, empty);

                if (entry == null || empty) {
                    setStyle("");
                } else {
                    //Now 'item' has all the info of the Person in this row
                    if (entry.getEntrytypeId() == 1033) {
                        //We apply now the changes in all the cells of the row
                        for (int i = 0; i < getChildren().size(); i++) {
                            ((Labeled) getChildren().get(i)).setTextFill(Color.FORESTGREEN);
                            //              ((Labeled) getChildren().get(i)).setStyle("-fx-background-color: honeydew");
                        }
                    } else if (entry.getEntrytypeId() == 1034) {
                        for (int i = 0; i < getChildren().size(); i++) {
                            ((Labeled) getChildren().get(i)).setTextFill(Color.SLATEBLUE);
                            //              ((Labeled) getChildren().get(i)).setStyle("-fx-background-color: lightcyan");
                        }
                    } else {
                        for (int i = 0; i < getChildren().size(); i++) {
                            ((Labeled) getChildren().get(i)).setTextFill(Color.BLACK);
                            //              ((Labeled) getChildren().get(i)).setStyle("-fx-background-color: white");
                        }
                    }
                }
            }
        });
    }

    public void toggleFormOrGloss(ActionEvent actionEvent) {
        searchGlosses = glossToggleButton.isSelected();
        if (searchGlosses) {
            glossToggleButton.setText("glosses");
        } else {
            glossToggleButton.setText("word forms");
        }
        reallyDoSearchNow();
    }

    public void doSearch(KeyEvent keyEvent) {
        reallyDoSearchNow();
    }

    public void requery(ActionEvent actionEvent) {
        reallyDoSearchNow();
    }

    public void keyReleased(KeyEvent keyEvent) {
        showSelectedLexicon();
    }

    public void rowClicked(MouseEvent mouseEvent) {
        showSelectedLexicon();
    }

    public void scrollFinished(ScrollEvent scrollEvent) {
        showSelectedLexicon();
    }





    public void refToggleButtonChanged(ActionEvent actionEvent) {
        refVisible = refToggleButton.isSelected();
        writeContent();
    }

    public void glsToggleButtonChanged(ActionEvent actionEvent) {
        glsVisible = glsToggleButton.isSelected();
        writeContent();
    }

    public void drvToggleButtonChanged(ActionEvent actionEvent) {
        drvVisible = drvToggleButton.isSelected();
        writeContent();
    }

    public void iflToggleButtonChanged(ActionEvent actionEvent) {
        iflVisible = iflToggleButton.isSelected();
        writeContent();
    }

    public void elmToggleButtonChanged(ActionEvent actionEvent) {
        elmVisible = elmToggleButton.isSelected();
        writeContent();
    }

    public void cogToggleButtonChanged(ActionEvent actionEvent) {
        cogVisible = cogToggleButton.isSelected();
        writeContent();
    }

    private void showSelectedLexicon(){
        SimpLexicon selected = matchTable.getSelectionModel().getSelectedItem();
        selectedLexicon = lexiconRepo.findByEntryId(selected.getEntryId());
        display(primaryWebView, secondaryWebView);
    }

    @FXML
    private void populateMatchTable(ObservableList<SimpLexicon> simpLexiconList) throws ClassNotFoundException {
        //Set items to the userTable
        matchTable.setItems(simpLexiconList);
    }

    private void reallyDoSearchNow() {
        ObservableList<SimpLexicon> simpLexiconList;
        try {
            if (searchGlosses) {

                simpLexiconList = FXCollections.observableArrayList(
                        simpLexiconRepo.findByGlossContainingAndLanguageId(searchTextField.getText(),
                                                                 languageChooser.getValue().getId()));
            } else {
                simpLexiconList = FXCollections.observableArrayList(
                        simpLexiconRepo.findByFormContainingAndLanguageId(searchTextField.getText(),
                                                                languageChooser.getValue().getId()));
            }
            populateMatchTable(simpLexiconList);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
//            throw e;
        }
    }

    @FXML
    private void populateLanguageChooser(ObservableList<Language> languageList) {
        languageChooser.setItems(languageList);
        languageChooser.setConverter(new StringConverter<Language>(){

            @Override
            public String toString(Language lang) {
                return lang.getName();
            }

            @Override
            public Language fromString(String string) {
                return languageChooser.getItems().stream().filter(ap -> ap.getName().equals(string)).findFirst().orElse(
                        null);
            }
        });
        languageChooser.getSelectionModel().select(7);
    }



    private void writeContent() {
        String txt1 = writeHeadLine();
        String txt2 = writeWordNotes();
        refList = FXCollections.observableArrayList(refRepo.findRefsByEntryId(selectedLexicon.getEntryId()));
        if (refVisible) { txt1 += writeRefs(); }
        if (glsVisible) { txt1 += writeGlosses(); }
        if (drvVisible) { txt1 += writeDerivs(); }
        if (iflVisible) { txt1 += writeInflects(); }
        if (elmVisible) { txt1 += writeElements(); }
        if (cogVisible) { txt1 += writeCognates(); }
        wv1.getEngine().loadContent(txt1);
        wv2.getEngine().loadContent(txt2);
    }

    private String writeHeadLine() {
        String txt = "<p> " + spanTag(selectedLexicon.getLangMnemonic() + ". ", 1, 14, 3, 0, "333");
        txt += spanTag(selectedLexicon.getForm() + ", ", 1, 12, 7, 1, "336");
        txt += spanTag(speechFormRepo.findSpeechFormViewByEntryId(selectedLexicon.getEntryId()).getTxt() + ". ", 1, 11, 5, 0, "000");
        txt += spanTag("\"" + selectedLexicon.getGloss() + "\"", 2, 12, 4, 0, "555");
        txt += "</p>";
        return txt;
    }

    private String writeWordNotes() {
        String txt = "";
        for (EntryNoteView env : entryNoteRepo.findByEntryId(selectedLexicon.getEntryId())) {
            txt += optStylePtag(env.getTxt(), 1, 11, 4, 0, "222");
        }
        return txt;
    }

    private String writeRefs() {
        boolean rs      = false;
        String  sources = "";
        for (Ref ref : refList) {
            if (rs) {
                sources += "; ";
            }
            sources += ref.getSource() + " ";
            rs = true;
        }
        if (sources.length() == 0) {
            return "";
        } else {
            return "<p> " + spanTag("References: ", 1, 12, 7, 0, "228") + spanTag(sources, 1, 11, 4, 0, "222") + "</p>";
        }
    }

    private String writeGlosses() {
        boolean rg      = false;
        String  glosses = spanTag("Glosses: ", 1, 12, 7, 0, "228") + "<ul style=\"margin-top:3px;\">";
        for (RefGlossView refGlossView : refGlossRepo.findByEntryId(selectedLexicon.getEntryId())) {
            glosses += "<li " + inline(1, 11, 4, 0, "222") + ">" + refGlossView.getRefgloss();
            rg = true;
        }
        if (rg) {
            return glosses + "</ul>";
        } else {
            return "";
        }
    }

    private String writeDerivs() {
        boolean dvs    = false;
        String  derivs = spanTag("Derivations: ", 1, 12, 7, 0, "228");
        derivs += table + "<tr>";
        derivs += th + spanTag("form ", 2, 11, 4, 2, "228") + "</th>";
        derivs += th15 + spanTag("gloss(es) ", 2, 11, 4, 2, "228") + "</th>";
        derivs += th + spanTag("sourc(es) ", 2, 11, 4, 2, "228") + "</th></tr>";

        for (RefDerivView rdv : refDerivRepo.findByEntryId(selectedLexicon.getEntryId())) {
            derivs += "<tr>";
            derivs += td + spanTag(rdv.getForm(), 2, 12, 4, 1, "2B2") + "</td>" + td + spanTag(rdv.getGlosses(), 12, 11,
                                                                                               4, 0,
                                                                                               "B22") + "</td>" + td + spanTag(
                    rdv.getSources(), 1, 12, 4, 0, "555") + "</td>";
            derivs += "</tr>";
            dvs = true;
        }
        if (dvs) {
            return derivs + "</table></br>";
        } else {
            return "";
        }
    }

    private String writeInflects() {
        boolean ifl      = false;
        String  inflects = spanTag("Inflections: ", 1, 12, 7, 0, "228");
        inflects += table + "<tr>";
        inflects += th + spanTag("form(s) ", 2, 11, 4, 2, "228") + "</th>";
        inflects += th15 + spanTag("speech ", 2, 11, 4, 2, "228") + "</th>";
        inflects += th10 + spanTag("gloss ", 2, 11, 4, 2, "228") + "</th>";
        inflects += th + spanTag("sourc(es) ", 2, 11, 4, 2, "228") + "</th></tr>";

        for (RefInflectView refInflect : refInflectRepo.findByEntryId(selectedLexicon.getEntryId())) {
            String gloss = refInflect.getGloss();
            inflects += "<tr>";
            inflects += td + spanTag(refInflect.getForm(), 2, 12, 4, 1, "2B2") + "</td>" + td + spanTag(
                    refInflect.getGrammar(), 2, 12, 4, 0, "22B") + "</td>" + td + (
                                gloss != null ? spanTag(refInflect.getGloss(), 12, 11, 4, 0,
                                                        "B22") : "") + "</td>" + td + spanTag(refInflect.getSources(),
                                                                                              1, 12, 4, 0,
                                                                                              "555") + "</td>";
            inflects += "</tr>";
            ifl = true;
        }
        if (ifl) {
            return inflects + "</table><br>";
        } else {
            return "";
        }
    }

    private String writeElements() {
        boolean elm      = false;
        String  gloss, sources;
        String  elements = spanTag("Elements: ", 1, 12, 7, 0, "228");
        elements += "<ul style='margin-top:3px;'>";
        for (RefElementView refElement : refElementRepo.findByEntryId(selectedLexicon.getEntryId())) {
            gloss   = null == refElement.getGloss() ? "" : refElement.getGloss();
            sources = null == refElement.getSources() ? "" : refElement.getSources();
            elements += "<li>";
            elements += spanTag(refElement.getLang() + ". ", 2, 11, 4, 0, "222");
            elements += spanTag(refElement.getForm() + " ", 2, 11, 4, 1, "228");
            if (! gloss.equalsIgnoreCase("")) { elements += spanTag(refElement.getGloss() + " ", 2, 11, 4, 0, "222"); }
            if (! sources.equalsIgnoreCase("")) {
                elements += spanTag(refElement.getSources() + " ", 1, 11, 4, 0, "222");
            }
            elements += "</li>";
            elm = true;
        }
        if (elm) {
            return elements + "</ul><br>";
        } else {
            return "";
        }
    }

    private String writeCognates() {
        boolean cog      = false;
        String  gloss, sources;
        String  cognates = spanTag("Cognates: ", 1, 12, 7, 0, "228");
        cognates += "<ul style='margin-top:3px;'>";
        for (RefCognateView refCognate : refCognateRepo.findByEntryId(selectedLexicon.getEntryId())) {
            gloss   = null == refCognate.getGloss() ? "" : refCognate.getGloss();
            sources = null == refCognate.getSources() ? "" : refCognate.getSources();
            cognates += "<li>";
            cognates += spanTag(refCognate.getLang() + ". ", 2, 11, 4, 0, "222");
            cognates += spanTag(refCognate.getForm() + " ", 2, 11, 4, 1, "228");
            if (! gloss.equalsIgnoreCase("")) { cognates += spanTag(refCognate.getGloss() + " ", 2, 11, 4, 0, "222"); }
            if (! sources.equalsIgnoreCase("")) {
                cognates += spanTag(refCognate.getSources() + " ", 1, 11, 4, 0, "222");
            }
            cognates += "</li>";
            cog = true;
        }
        if (cog) {
            return cognates + "</ul><br>";
        } else {
            return "";
        }
    }

    private String optStylePtag(String txt, int fam, int px, int wei, int sty, String col) {
        if (txt.toLowerCase().contains("<p>")) {
            return txt.replaceAll("<p>", "<p " + inline(fam, px, wei, sty, col) + ">");
        } else {
            return wholePtag(txt, fam, px, wei, sty, col);
        }
    }

    private String wholePtag(String txt, int fam, int px, int wei, int sty, String col) {
        return "<p " + inline(fam, px, wei, sty, col) + ">" + txt + "</p>";
    }

    private String spanTag(String txt, int fam, int px, int wei, int sty, String col) {
        return "<span " + inline(fam, px, wei, sty, col) + ">" + txt + "</span>";
    }

    private String startPtag(String txt, int fam, int px, int wei, int sty, String col) {
        return "<p " + inline(fam, px, wei, sty, col) + ">" + txt;
    }



    private String fontFamily(int family) {
        if (family < 1) {
            return "font-family: monaco, monospace;";
        } else if (family == 1) {
            return "font-family: 'Lucida Sans', 'Open Sans', sans-serif;";
        } else {
            return "font-family: Palatino, 'Lucida Bright', serif;";
        }
    }

    private String fontSize(int px) {
        return "font-size: " + px + "px;";
    }

    private String fontWeight(int weight) {
        return "font-weight: " + weight + "00;";
    }

    private String fontColour(String col) {
        return "color: #" + col + ";";
    }

    private String fontStyle(int style) {
        if (style < 1) {
            return "font-style: normal;";
        } else if (style == 1) {
            return "font-style: italic;";
        } else {
            return "font-style: oblique;";
        }
    }

    private String inline(int fam, int px, int wgh, int sty, String col) {
        return "style=\"" + fontFamily(fam) + fontSize(px) + fontWeight(wgh) + fontColour(col) + fontStyle(sty) + "\";";
    }

}
