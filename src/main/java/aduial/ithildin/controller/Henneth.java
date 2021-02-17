package aduial.ithildin.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import net.rgielen.fxweaver.core.FxControllerAndView;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

@Component
@FxmlView
public class Henneth{

    private final FxControllerAndView<Athrabeth, VBox> someDialog;

    @FXML
    public Button openDialogButton;

    public Henneth(FxControllerAndView<Athrabeth, VBox> someDialog) {
        this.someDialog = someDialog;
    }

    @FXML
    public void initialize() {
        openDialogButton.setOnAction(
                actionEvent -> someDialog.getController().show()
        );
    }

}
