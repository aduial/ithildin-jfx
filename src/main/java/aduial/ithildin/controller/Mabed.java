package aduial.ithildin.controller;

import aduial.ithildin.entity.Language;
import aduial.ithildin.entity.Lexicon;
import aduial.ithildin.entity.SimpLexicon;
import aduial.ithildin.repository.LanguageRepo;
import aduial.ithildin.repository.LexiconRepo;
import aduial.ithildin.repository.SimpLexiconRepo;
import aduial.ithildin.view.LexiconPage;
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

    @Autowired
    private SimpLexiconRepo simpLexiconRepo;

    @Autowired
    private LexiconRepo lexiconRepo;

    @Autowired
    private LanguageRepo languageRepo;

    private boolean searchGlosses = false;
    private LexiconPage currentLexiconPage;

    //For MultiThreading
    private Executor exec;

    @FXML
    public void initialize() {
        matchTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        populateLanguageChooser(FXCollections.observableArrayList(languageRepo.findLanguagesByIdGreaterThanAndParentIdIsNotNull(1000L)));

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

                simpLexiconList = FXCollections.observableArrayList(
                        simpLexiconRepo.findByGlossAndLanguageId(searchTextField.getText(),
                                                                 languageChooser.getValue().getId()));
            } else {
                simpLexiconList = FXCollections.observableArrayList(
                        simpLexiconRepo.findByFormAndLanguageId(searchTextField.getText(),
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

}
