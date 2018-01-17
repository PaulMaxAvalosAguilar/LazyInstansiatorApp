package lazyinstanziator;

import com.jfoenix.controls.JFXButton;
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

public class DaoController implements Initializable {

    @FXML
    private TextField field;
    @FXML
    private TextArea area;

    
    @FXML
    private JFXButton copy;
    @FXML
    private JFXButton clear;
    
    @FXML
    private TextField idtype;

    private StringBinding sb;
    private StringBinding minusb;
    private StringProperty minus;
    private StringProperty t;
    private StringProperty id;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        setCSS();

        idtype.setText("");
        field.setText("");
        t = new SimpleStringProperty();
        minus = new SimpleStringProperty();
        t.bind(field.textProperty());
        
        id = new SimpleStringProperty();

        id.bind(idtype.textProperty());
        
        minusb = new StringBinding() {
            {
                super.bind(t);
            }

            @Override
            protected String computeValue() {
                return (!(t.get().trim().isEmpty())) ? t.get().substring(0, 1).toLowerCase() : "";
            }
        };

        minus.bind(minusb);

        sb = new StringBinding() {
            {
                super.bind(t,minus,id);
            }

            @Override
            protected String computeValue() {
                String sc;
                sc = String.format(""
                        + "public class %sDao {\n"
                        + "    private static EntityManagerFactory emf;\n"
                        + "    private static %sJpaController " + minus.get() + "jpac;\n"
                        + "\n"
                        + "    private static %sDao instance;\n"
                        + "\n"
                        + "    private %sDao() {\n"
                        + "        //private constructor singleton\n"
                        + "    }\n"
                        + "\n"
                        + "    public static %sDao getInstance() {\n"
                        + "        emf = EManager.getInstance().getEntityManagerFactory();\n"
                        + "        " + minus.get() + "jpac = new %sJpaController(emf);\n"
                        + "        if (instance == null) {\n"
                        + "            instance = new %sDao();\n"
                        + "        }\n"
                        + "        return instance;\n"
                        + "        //instance initializer\n"
                        + "        //Uses JPAController and local EntityManagerFactory fields\n"
                        + "    }\n"
                        + "\n"
                        + "    public void createRegistro(%s " + minus.get() + ") {\n"
                        + "        " + minus.get() + "jpac.create(" + minus.get() + ");\n"
                        + "    }\n"
                        + "\n"
                        + "    public void updateRegistro(%s " + minus.get() + ") throws Exception {\n"
                        + "        " + minus.get() + "jpac.edit(" + minus.get() + ");\n"
                        + "    }\n"
                        + "\n"
                        + "    public void deleteRegistro("+id.get()+" i) throws NonexistentEntityException {\n"
                        + "        " + minus.get() + "jpac.destroy(i);\n"
                        + "    }\n"
                        + "\n"
                        + "    public List<%s> getAllRegistros() {\n"
                        + "        return " + minus.get() + "jpac.find%sEntities();\n"
                        + "    }\n"
                        + "\n"
                        + "    public %s getRegistro("+id.get()+" i) {\n"
                        + "        return " + minus.get() + "jpac.find%s(i);\n"
                        + "    }\n"
                        + "    \n"
                        + "    public int getNumeroRegistros(){\n"
                        + "        return " + minus.get() + "jpac.get%sCount();\n"
                        + "    }\n"
                        + "\n"
                        + "    public EntityManager getEntityManager(){\n"
                        + "        return "+minus.get()+"jpac.getEntityManager();\n"
                        + "    }\n"
                        + "}"
                        + "", t.get(), t.get(), t.get(), t.get(), t.get(), t.get(), t.get(), t.get(), t.get(), t.get(), t.get(),
                        t.get(), t.get(), t.get());
                return sc;
            }

        };

        area.textProperty().bind(sb);
    }

    private void setCSS() {
        copy.setStyle("-fx-background-color: #4baeaf;");
        clear.setStyle("-fx-background-color: #4baeaf;");
    }

    @FXML
    private void copy(ActionEvent event) {
        final Clipboard clip = Clipboard.getSystemClipboard();
        ClipboardContent c = new ClipboardContent();
        c.putString(area.getText());
        clip.setContent(c);
    }

    @FXML
    private void clear(ActionEvent event) {
        field.setText("");
    }

}
