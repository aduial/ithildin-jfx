package aduial.ithildin;

import javafx.application.Application;
import aduial.ithildin.application.IthildinJfxApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 * @author (original) Rene Gielen
 */
@SpringBootApplication
public class IthildinBootApplication{

    public static void main(String[] args) {
        Application.launch(IthildinJfxApplication.class, args);
    }

}
