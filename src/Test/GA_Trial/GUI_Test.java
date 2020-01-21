package GA_Trial;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.junit.Before;
import org.junit.Test;
import org.loadui.testfx.GuiTest;
import static org.mockito.Mockito.verify;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import java.io.IOException;
import static org.mockito.Mockito.times;
import static org.loadui.testfx.Assertions.*;

public class GUI_Test extends GuiTest {


    @Override
    protected Parent getRootNode() {
        Parent parent = null;


        try {
            parent = FXMLLoader.load(getClass().getResource("FXML-View.fxml"));
            return parent;
        } catch (IOException e) {
            e.printStackTrace();
        }

return parent;
    }


    @Test
    public void shouldShowRightText(){
        Label reiseziel = find("#reiseziel");
        assertTrue(reiseziel.getText().equals("Nach"));
    }

    @Test
    public void shouldClickOnButton(){
        Button showAll = find("#showAll");
        click(showAll);
    }
}
