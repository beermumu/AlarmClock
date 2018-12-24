import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ControllerFirstpage {
    Alarm alarm = new Alarm();

    ObservableList<Integer> hourList = FXCollections.observableArrayList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23);
    ObservableList<Integer> minuteList = FXCollections.observableArrayList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30
            , 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59);
    ObservableList<Integer> secondList = FXCollections.observableArrayList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30
            , 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59);
    @FXML
    TableView<String> timeTable;
    @FXML
    Label status, curTime, alarmTime, statusData, question;
    @FXML
    TextField ans;
    @FXML
    Button add, check;
    @FXML
    ComboBox hh, mm, ss;

    Stage stage;

    @FXML
    void initialize() {
        hh.setItems(hourList);
        hh.setValue(0);
        mm.setItems(minuteList);
        mm.setValue(0);
        ss.setItems(secondList);
        ss.setValue(0);
        initClock();
    }

    @FXML
    public void addTime(ActionEvent e) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Alarm Time");
        alert.setHeaderText("Alarm Time Check");
        alert.setContentText("Are you ok with this alarm time?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            alarm.setTime(Integer.parseInt(hh.getSelectionModel().getSelectedItem().toString()), Integer.parseInt(mm.getSelectionModel().getSelectedItem().toString()), Integer.parseInt(ss.getSelectionModel().getSelectedItem().toString()));
            hh.setDisable(true);
            mm.setDisable(true);
            ss.setDisable(true);
            add.setDisable(true);
            alarmTime.setText("Alarm Time --> " + hh.getSelectionModel().getSelectedItem().toString() + ":" + mm.getSelectionModel().getSelectedItem().toString() + ":" + ss.getSelectionModel().getSelectedItem().toString());
            statusData.setText("Alarm was Set");
        }
        Button button = (Button) e.getSource();
        stage = (Stage) button.getScene().getWindow();
    }

    private void initClock() {
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            curTime.setText(LocalDateTime.now().format(formatter));
        }), new KeyFrame(Duration.seconds(1)));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    NowTime nowTime = new NowTime();
                    if (alarm.getHour() == nowTime.getHh() && alarm.getMinute() == nowTime.getMm() && alarm.getSecond() == nowTime.getSs()) {
                        Platform.runLater(() -> {
                            try {
                                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("questionpage.fxml"));
                                stage.setScene(new Scene(fxmlLoader.load()));
                                stage.setTitle("Answer If You Can");
                                stage.show();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });
                    }
                }

            }
        });
        t.start();
    }

    @FXML
    public void handleCloseButtonAction(ActionEvent event) {
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }
}
