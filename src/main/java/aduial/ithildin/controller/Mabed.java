package aduial.ithildin.controller;

import aduial.ithildin.entity.Language;
import aduial.ithildin.entity.Lexicon;
import aduial.ithildin.entity.SimpLexicon;
import aduial.ithildin.repository.LanguageRepo;
import aduial.ithildin.repository.LexiconRepo;
import aduial.ithildin.repository.SimplexiconRepo;
import aduial.ithildin.view.LexiconPage;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.util.StringConverter;
import net.rgielen.fxweaver.core.FxControllerAndView;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author Lúthien
 * S. dangweth n. “answer, (lit.) *back-report” -> results pane
 */
@Component
@FxmlView
public class Mabed{

    private final FxControllerAndView<Athrabeth, VBox> athrabeth;

    @FXML private TextField                        searchTextField;
    @FXML private TableView<SimpLexicon>           matchTable;
    @FXML private TableColumn<SimpLexicon, Number> idColumn;
    @FXML private TableColumn<SimpLexicon, String> formColumn;
    @FXML private TableColumn<SimpLexicon, String> glossColumn;
    @FXML private TableColumn<SimpLexicon, Number> entrytypeIdColumn;
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

    @Autowired
    private SimplexiconRepo simplexiconRepo;

    @Autowired
    private LexiconRepo lexiconRepo;

    @Autowired
    private LanguageRepo languageRepo;

    private boolean searchGlosses = false;
    private LexiconPage currentLexiconPage;

    //For MultiThreading
    private Executor exec;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public Mabed(FxControllerAndView<Athrabeth, VBox> athrabeth) {
        this.athrabeth = athrabeth;
    }

    @FXML
    public void initialize() {
        matchTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        populateLanguageChooser(languageRepo.findLanguagesByIdGreaterThanAndParentIdIsNotNull(1000L));

        //For multithreading: Create executor that uses daemon threads:
        exec = Executors.newCachedThreadPool((runnable) -> {
            Thread t = new Thread(runnable);
            t.setDaemon(true);
            return t;
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
        showSelected();
    }

    public void rowClicked(MouseEvent mouseEvent) {
        showSelected();
    }

    public void scrollFinished(ScrollEvent scrollEvent) {
        showSelected();
    }

    public void refToggleButtonChanged(ActionEvent actionEvent) {
        currentLexiconPage.setRefVisible(refToggleButton.isSelected());
    }

    public void glsToggleButtonChanged(ActionEvent actionEvent) {
        currentLexiconPage.setGlsVisible(glsToggleButton.isSelected());
    }

    public void drvToggleButtonChanged(ActionEvent actionEvent) {
        currentLexiconPage.setDrvVisible(drvToggleButton.isSelected());
    }

    public void iflToggleButtonChanged(ActionEvent actionEvent) {
        currentLexiconPage.setIflVisible(iflToggleButton.isSelected());
    }

    public void elmToggleButtonChanged(ActionEvent actionEvent) {
        currentLexiconPage.setElmVisible(elmToggleButton.isSelected());
    }

    public void cogToggleButtonChanged(ActionEvent actionEvent) {
        currentLexiconPage.setCogVisible(cogToggleButton.isSelected());
    }

    private void showSelected(){
        SimpLexicon selected = matchTable.getSelectionModel().getSelectedItem();
        //    System.out.println("Thou clicketh: " + selected.getId());
        Lexicon selectedLexicon = lexiconRepo.findFirstByEntryId(selected.getEntryId());
        currentLexiconPage = new LexiconPage(selectedLexicon, refToggleButton.isSelected(),
                                             glsToggleButton.isSelected(), drvToggleButton.isSelected(),
                                             iflToggleButton.isSelected(), elmToggleButton.isSelected(),
                                             cogToggleButton.isSelected());
        currentLexiconPage.display(primaryWebView, secondaryWebView);
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
                simpLexiconList = simplexiconRepo.findByGlossAndLanguageId(searchTextField.getText(),
                                                                           languageChooser.getValue().getId());
            } else {
                simpLexiconList = simplexiconRepo.findByGlossAndLanguageId(searchTextField.getText(),
                                                                           languageChooser.getValue().getId());
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

}
