package lazyinstanziator;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

public class builderFXCOntroller
        implements Initializable {

    @FXML
    private TextField accesiblerole;
    @FXML
    private TextField javafxproperty;
    @FXML
    private TextField shadowtype;
    @FXML
    private TextField name;
    @FXML
    private TextArea area;
    private StringBinding sb;
    private StringBinding may;
    private StringProperty ar = new SimpleStringProperty();
    private StringProperty jfxp = new SimpleStringProperty();
    private StringProperty st = new SimpleStringProperty();
    private StringProperty n = new SimpleStringProperty();

    private StringProperty nom = new SimpleStringProperty();

    public void initialize(URL url, ResourceBundle rb) {
        accesiblerole.textProperty().set("");
        javafxproperty.textProperty().set("");
        shadowtype.textProperty().set("");
        name.textProperty().set("");

        ar.bind(accesiblerole.textProperty());
        jfxp.bind(javafxproperty.textProperty());
        st.bind(shadowtype.textProperty());
        n.bind(name.textProperty());

        may = new StringBinding() {
            {
                super.bind(name.textProperty());
            }

            protected String computeValue() {
                String nameup = "";
                nameup = !(name.textProperty().get().isEmpty()) ? (name.textProperty().get()).substring(0, 1).toUpperCase()
                        + (name.textProperty().get()).substring(1) : "";
                return nameup;
            }
        };

        nom.bind(may);

        sb = new StringBinding() {
            {
                super.bind(ar, jfxp, st, n);
            }

            protected String computeValue() {
                return ar.get() + " " + jfxp.get() + " " + n.get() + ";\n"
                        + ar.get() + " " + st.get() + " _" + n.get() + ";\n\npublic "
                        //getters
                        + st.get() + " get" + nom.get() + "(){\n\treturn("
                        + n.get() + "==null)? _" + n.get() + ": " + n.get() + ".get();\n}"
                        //setters
                        + "\n\npublic void set" + nom.get() + "(" + st.get() + " " + n.get() + "){"
                        + "\nif(this." + n.get() + "==null){"
                        + "\n\t_" + n.get() + "=" + n.get() + ";"
                        + "\n\t}else{"
                        + "\n\tthis." + n.get() + ".set(" + n.get() + ");"
                        + "\n\t}"
                        + "\n}"
                        + "\n"
                        //PRoperty Accesors
                        + "\npublic " + jfxp.get() + " " + n.get() + "Property(){"
                        + "\n\treturn(" + n.get() + "==null)? " + n.get() + "= new Simple" + jfxp.get() + "(this,\""+n.get()+"\",_" + n.get() + "):" + n.get() + ";"
                        + "\n}";
            }
        };

        area.textProperty().bind(sb);
    }

    @FXML
    private void clear(ActionEvent event) {
        accesiblerole.textProperty().set("");
        javafxproperty.textProperty().set("");
        shadowtype.textProperty().set("");
        name.textProperty().set("");
    }

    @FXML
    private void copy(ActionEvent event) {
        final Clipboard clipboar = Clipboard.getSystemClipboard();
        final ClipboardContent content = new ClipboardContent();
        content.putString(area.getText());
        clipboar.setContent(content);

    }
}
