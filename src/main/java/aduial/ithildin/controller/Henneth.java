package aduial.ithildin.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import net.rgielen.fxweaver.core.FxControllerAndView;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

/**
 * @author Lúthien
 * S. henneth n. “window”
 */
@Component
@FxmlView
public class Henneth{

    @FXML private BorderPane rootLayout;
    @FXML private StackPane  stackPane;

    @FXML private MenuItem                             searchMenuItem;
    @FXML private MenuItem                             editMenuItem;

    private final FxWeaver fxWeaver;
    private final FxControllerAndView<Mabed, VBox> mabed;

    public Henneth(FxWeaver fxWeaver, FxControllerAndView<Mabed, VBox> mabed) {
        this.fxWeaver = fxWeaver;
        this.mabed    = mabed;
    }

    @FXML
    public void initialize() {
        stackPane.getChildren().add(mabed.getView().get());
    }

    public void handleSearchAction(ActionEvent actionEvent) {
    }

    public void handleEditAction(ActionEvent actionEvent) {
    }
}
