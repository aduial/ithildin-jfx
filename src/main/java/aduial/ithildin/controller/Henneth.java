package aduial.ithildin.controller;

import aduial.ithildin.entity.Language;
import aduial.ithildin.entity.Lexicon;
import aduial.ithildin.entity.Simplexicon;
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

@Component
@FxmlView
public class Henneth{

    private final FxControllerAndView<Athrabeth, VBox> athrabeth;

    @FXML public  Button                           athrabethButton;

    @FXML private TextField                        searchTextField;
    @FXML private TableView<Simplexicon>           matchTable;
    @FXML private TableColumn<Simplexicon, Number> idColumn;
    @FXML private TableColumn<Simplexicon, String> formColumn;
    @FXML private TableColumn<Simplexicon, String> glossColumn;
    @FXML private TableColumn<Simplexicon, Number> entrytypeIdColumn;
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

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public Henneth(FxControllerAndView<Athrabeth, VBox> athrabeth) {
        this.athrabeth = athrabeth;
    }

    @FXML
    public void initialize() {
        populateLanguageChooser(languageRepo.findLanguagesByIdGreaterThanAndParentIdIsNotNull(1000L));
        athrabethButton.setOnAction(
                actionEvent -> athrabeth.getController().show()
                                   );
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
    }

    public void rowClicked(MouseEvent mouseEvent) {
        Simplexicon selected = matchTable.getSelectionModel().getSelectedItem();
        //    System.out.println("Thou hath clicketh: " + selected.getId());
        Lexicon selectedLexicon = lexiconRepo.findOne(selected.getId());

        currentLexiconPage = new LexiconPage(selectedLexicon, refToggleButton.isSelected(),
                                             glsToggleButton.isSelected(), drvToggleButton.isSelected(),
                                             iflToggleButton.isSelected(), elmToggleButton.isSelected(),
                                             cogToggleButton.isSelected());
        currentLexiconPage.display(primaryWebView, secondaryWebView);
    }

    public void scrollFinished(ScrollEvent scrollEvent) {
    }

    public void refToggleButtonChanged(ActionEvent actionEvent) {
    }

    public void glsToggleButtonChanged(ActionEvent actionEvent) {
    }

    public void drvToggleButtonChanged(ActionEvent actionEvent) {
    }

    public void iflToggleButtonChanged(ActionEvent actionEvent) {
    }

    public void elmToggleButtonChanged(ActionEvent actionEvent) {
    }

    public void cogToggleButtonChanged(ActionEvent actionEvent) {
    }


    @FXML
    private void populateMatchTable(ObservableList<Simplexicon> simplexiconList) throws ClassNotFoundException {
        //Set items to the userTable
        matchTable.setItems(simplexiconList);
    }

    private void reallyDoSearchNow() {
        ObservableList<Simplexicon> simplexiconList;
        try {
            if (searchGlosses) {
                simplexiconList = simplexiconRepo.findByGlossAndLanguageId(searchTextField.getText(),
                                                               languageChooser.getValue().getId());
            } else {
                simplexiconList = simplexiconRepo.findByGlossAndLanguageId(searchTextField.getText(),
                                                              languageChooser.getValue().getId());
            }
            populateMatchTable(simplexiconList);
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
