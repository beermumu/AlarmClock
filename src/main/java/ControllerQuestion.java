import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerQuestion {

    @FXML
    Label status, question;
    @FXML
    TextField ans;
    @FXML
    Button check;

    Calculate calculate;

    @FXML
    void initialize() {
        calculate = new Calculate();
        question.setText(calculate.getQuestion());
    }

    @FXML
    public void calculate(ActionEvent e) {
        if (Integer.parseInt(ans.getText()) == calculate.getAnswer()) {
            Button click = (Button) e.getSource();

            Stage bg = (Stage) click.getScene().getWindow();

            FXMLLoader p2 = new FXMLLoader(getClass().getResource("firstpage.fxml"));

            try {
                bg.setScene(new Scene((Parent) p2.load(), 500, 300));
                bg.setTitle("Alarm Alarm V2 2018");
                bg.show();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } else {
            status.setText("Wrong Answer");
        }
    }
}
