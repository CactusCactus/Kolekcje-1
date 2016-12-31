package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Controller {
    ArrayList<String> list;
    ArrayList<Integer> countList;
    ArrayList<String> letterList;
    @FXML private Label fileNameLabel;
    @FXML private Button analiticsButton;
    @FXML private void handleOpenAction() {
        Stage stage = (Stage) fileNameLabel.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Wybierz plik tekstowy");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("TXT", "*.txt"));
        File file = fileChooser.showOpenDialog(stage);
        if(file != null) {
            handleFile(file);
            fileNameLabel.setText("Wybrany plik: " + file.getName());
            analiticsButton.setDisable(false);
        }

    }
    @FXML private void handleAnalyzeAction() {
        analizeFile();
        letterList = new ArrayList<>();
        char letter = 'a';
        for(Integer count: countList) {
            letterList.add(letter + ": " + count);
            letter ++;
        }

        openNewWindow();


    }
    private void openNewWindow(){
        AnalitcsController analitcsController = new AnalitcsController(letterList);
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("analytics.fxml"));
            fxmlLoader.setController(analitcsController);
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Analiza");
            Scene scene = new Scene(root, 300, 275);
            stage.setScene(scene);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void analizeFile(){
        if(list != null && list.size() > 0) {
            countList = new ArrayList<>();
            for (String word : list) {
                for (int i = 'a'; i <= 'z'; i++) {
                    int counter = 0;
                    for (int j = 0; j < word.length(); j++) {
                        if (word.charAt(j) == (char) i) {
                            counter++;
                        }
                    }
                    if(countList.size() > i - 97) {
                        countList.set(i - 97, countList.get(i - 97) + counter);

                    } else {
                        countList.add(counter);

                    }
                }
            }
        }
    }
    public void handleFile(File file) {
        Scanner s = null;
        try {
            s = new Scanner(file);
            list = new ArrayList<>();
            while (s.hasNext()){
                list.add(s.next().toLowerCase());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (s != null) {
                s.close();
            }
        }

    }

}
