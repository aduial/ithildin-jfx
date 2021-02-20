package aduial.ithildin.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import net.rgielen.fxweaver.core.FxControllerAndView;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

@Component
@FxmlView
public class Henneth{

    private final FxControllerAndView<Athrabeth, VBox> mabed;

    @FXML
    public Button openDialogButton;

    public Henneth(FxControllerAndView<Athrabeth, VBox> mabed) {
        this.mabed = mabed;
    }

    @FXML
    public void initialize() {
//        openDialogButton.setOnAction(
//                actionEvent -> mabed.getController().show() );
    }

    public void handleSearchAction(ActionEvent actionEvent) {
    }

    public void handleEditAction(ActionEvent actionEvent) {
    }
}
