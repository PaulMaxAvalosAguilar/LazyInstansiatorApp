package lazyinstanziator;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class EhashController implements Initializable {

    @FXML
    private TextField getText;
    @FXML
    private TextField classText;
    @FXML
    private TextArea equalsArea;
    @FXML
    private TextArea hashArea;
    private Button doneb;

    private StringBinding equalsBinding;
    private StringBinding hashBinding;
    private IntegerProperty count = new SimpleIntegerProperty();

    String classpart = "";
    String firstpart = "";
    String secondpart = "";
    String all = "";

    String start = "";
    String body = "";
    String end = "";
    String allhash = "";

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        classText.setText("");
        getText.setText("");

        count.set(0);

        setEqualsBinding();
        setHashtext();

    }

    private void setEqualsBinding() {

        equalsBinding = new StringBinding() {

            {
                super.bind(classText.textProperty(), count);
            }

            @Override
            protected String computeValue() {

                if (count.get() == 0) {
                    classpart = "@Override\n"
                            + "    public boolean equals(Object obj) {\n"
                            + "        if (obj == null || getClass() != obj.getClass()) {\n"
                            + "            return false;\n"
                            + "        }\n"
                            + "        final " + classText.getText() + " other = (" + classText.getText() + ") obj;\n";

                    secondpart = "\n}";
                    all = classpart + secondpart;

                } else if (count.get() == 1) {

                    classpart = "@Override\n"
                            + "    public boolean equals(Object obj) {\n"
                            + "        if (obj == null || getClass() != obj.getClass()) {\n"
                            + "            return false;\n"
                            + "        }\n"
                            + "        final " + classText.getText() + " other = (" + classText.getText() + ") obj;\n";
                    firstpart = "        return Objects.equals(this.get" + getText.getText() + "(), other.get" + getText.getText() + "())";
                    secondpart = ";\n}";
                    all = classpart + firstpart + secondpart;

                } else if (count.get() > 1) {
                    classpart = "@Override\n"
                            + "    public boolean equals(Object obj) {\n"
                            + "        if (obj == null || getClass() != obj.getClass()) {\n"
                            + "            return false;\n"
                            + "        }\n"
                            + "        final " + classText.getText() + " other = (" + classText.getText() + ") obj;\n";

                    firstpart += "\n\t\t&& Objects.equals(this.get" + getText.getText() + "(), other.get" + getText.getText() + "())";
                    secondpart = ";\n}";
                    all = classpart + firstpart + secondpart;

                }

                return all;

            }

        };

        equalsArea.textProperty().bind(equalsBinding);

    }

    private void setHashtext() {
        start = "@Override\n"
                + "    public int hashCode() {\n"
                + "        int hash = 3;";
        end = "\n    \treturn hash;\n"
                + "    }";

        String comma = ";";
        if (count.get() == 0) {
            allhash = start + end;
            hashArea.setText(allhash);
        } else if (count.get() == 1) {
            body = "\n\thash = 97 * hash + Objects.hashCode(this.get" + getText.getText() + "())";
            allhash = start + body + comma + end;

            hashArea.setText(allhash);
        } else if (count.get() > 1) {
            body += "\n        + Objects.hashCode(this.get" + getText.getText() + "())";
            allhash = start + body + comma + end;
            hashArea.setText(allhash);
        }
    }

    @FXML
    private void addGet(ActionEvent event) {
        int i = count.get();
        i++;
        count.set(i);
        setHashtext();
    }

    @FXML
    private void reset(ActionEvent event) {
        int i = count.get();
        i = 0;
        count.set(i);
        setHashtext();
    }

}


