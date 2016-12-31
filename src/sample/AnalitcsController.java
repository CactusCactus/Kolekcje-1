package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by Comarch on 2016-12-31.
 */
public class AnalitcsController implements Initializable{
    @FXML
    private ListView<String> listView;
    private ObservableList<String> items;

    public AnalitcsController(ArrayList<String> list) {
        items = FXCollections.observableArrayList (list);
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listView.setItems(items);
    }

}
