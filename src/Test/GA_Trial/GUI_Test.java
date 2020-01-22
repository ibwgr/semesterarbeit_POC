package GA_Trial;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import org.junit.Test;
import org.loadui.testfx.GuiTest;
import static org.junit.Assert.*;


import java.io.IOException;


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
    public void shouldShowRightErfassenText(){
        Label erfassen = find("#erfassung");
        assertTrue(erfassen.getText().equals("Erfassen"));
    }

    @Test
    public void shouldShowRightStandortText(){
        Label standort = find("#standOrt");
        assertTrue(standort.getText().equals("Von"));
    }

    @Test
    public void shouldShowRightReisezielText(){
        Label reiseziel = find("#reiseziel");
        assertTrue(reiseziel.getText().equals("Nach"));
    }

    @Test
    public void shouldShowRightReisedatumlText(){
        Label reisedatum = find("#reisedatum");
        assertTrue(reisedatum.getText().equals("Datum"));
    }

    @Test
    public void shouldShowRightKostenText(){
        Label kosten = find("#kosten");
        assertTrue(kosten.getText().equals("Kosten"));
    }

    @Test
    public void shouldShowRightChurText(){
        Label chur = find("#chur");
        assertTrue(chur.getText().equals("Chur"));
    }

    @Test
    public void shouldShowRightAuswertungText(){
        Label auswertung = find("#auswertung");
        assertTrue(auswertung.getText().equals("Auswertung"));
    }

    @Test
    public void shouldShowRightRelationText(){
        Label relation = find("#gaRel");
        assertTrue(relation.getText().equals("Preisverhältnis zu GA"));
    }

    @Test
    public void shouldShowRightPreisText(){
        Label preissum = find("#preisSum");
        assertTrue(preissum.getText().equals("Total Reisekosten"));
    }


    @Test
    public void shouldClickComboBoxDestination(){
        ComboBox destination = find("#comboBoxZiel");
        click(destination);
        assertTrue(destination.isShowing());
    }

    @Test
    public void shouldClickComboBoxMonth(){
        ComboBox month = find("#comboBoxMonat");
        click(month);
        assertTrue(month.isShowing());
    }


    @Test
    public void shouldShowMonthJanuary() {
        ComboBox month = find("#comboBoxMonat");
        click(month);
        click("Januar");
        assertTrue(month.getSelectionModel().getSelectedItem().equals("Januar"));
    }


    @Test
    public void shouldShowRightTextEnterReiseButton(){
        Button enterReise= find("#enterReise");
        assertTrue(enterReise.getText().equals("Erfasse Reise"));
    }

    @Test
    public void shouldClickDeleteTripButton(){
        Button deleteTrip = find("#deleteTrip");
        click(deleteTrip);
        assertTrue(deleteTrip.isFocused());
    }

    @Test
    public void shouldShowRightTextDeleteReiseButton(){
        Button deleteReise= find("#deleteTrip");
        assertTrue(deleteReise.getText().equals("Reise löschen"));
    }

    @Test
    public void shouldClickOnShowAllButton(){
        Button showAll = find("#showAll");
        click(showAll);
        assertTrue(showAll.isFocused());
    }

    @Test
    public void shouldShowRightTextShowAllButton(){
        Button showAll= find("#showAll");
        assertTrue(showAll.getText().equals("Zeige alle Reisen"));
    }

    @Test
    public void shouldClickDatePicker(){
        DatePicker datePicker = find("#datePicker");
        click(datePicker);
        assertTrue(datePicker.isFocused());
    }

    @Test
    public void shouldClickEnterReiseButton(){
        Button enterReise = find("#enterReise");
        click(enterReise);
        assertTrue(enterReise.isFocused());
    }

    @Test
    public void e2eProbing(){
        ComboBox reiseZiel = find("#comboBoxZiel");
        click(reiseZiel);
        click("Anderer Zielort");
        sleep(1000);
        TextField other = find("#other");
        other.setText("Test123");
        sleep(1000);
        TextField price = find("#price");
        price.setText("999");
        sleep(1000);
        Button erfassen = find("#enterReise");
        click(erfassen);
        sleep(1000);
        click("999.0");
        sleep(1000);
        click("#deleteTrip");
    }
}
