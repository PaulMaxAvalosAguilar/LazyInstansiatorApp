package lazyinstanziator;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

public class JPAControllerController implements Initializable {

    @FXML
    private TextField textfield;

    @FXML
    private JFXButton copy;

    @FXML
    private JFXButton clear;

    @FXML
    private JFXTextArea area;

    @FXML
    private TextField idtype;

    @FXML
    private TextField idname;

    @FXML
    private JFXButton manager;

    @FXML
    private Button inoe;

    @FXML
    private Button neee;

    @FXML
    private Button peee;

    private StringProperty t = new SimpleStringProperty();
    private StringBinding sb;

    private StringBinding getterB;
    private StringProperty getter = new SimpleStringProperty();

    private StringProperty type = new SimpleStringProperty();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        setCSS();
        setTooltips();

        idname.textProperty().set("");
        getterB = new StringBinding() {
            {
                super.bind(idname.textProperty());
            }

            @Override
            protected String computeValue() {
                String getter;
                getter = !(idname.textProperty().get().isEmpty()) ? idname.textProperty().get().substring(0, 1).toUpperCase()
                        + (idname.textProperty().get().substring(1)) : "";
                return getter;
            }
        };

        getter.bind(getterB);

        idtype.setText("");
        type.bind(idtype.textProperty());

        textfield.setText("");
        t.bind(textfield.textProperty());

        sb = new StringBinding() {
            {
                super.bind(t, getter, type);
            }

            @Override
            protected String computeValue() {
                String sc;
                sc = ""
                        + "import java.io.Serializable;\n"
                        + "import javax.persistence.EntityManager;\n"
                        + "import javax.persistence.EntityManagerFactory;\n"
                        + "import javax.persistence.EntityNotFoundException;\n"
                        + "import javax.persistence.Query;\n"
                        + "import javax.persistence.criteria.CriteriaQuery;\n"
                        + "import javax.persistence.criteria.Root;"
                        + "\n"
                        + "\n"
                        + "public class " + t.get() + "JpaController implements Serializable {\n"
                        + "\n"
                        + "    public " + t.get() + "JpaController(EntityManagerFactory emf) {\n"
                        + "        this.emf = emf;\n"
                        + "    }\n"
                        + "    private EntityManagerFactory emf = null;\n"
                        + "\n"
                        + "    public EntityManager getEntityManager() {\n"
                        + "        return emf.createEntityManager();\n"
                        + "    }\n"
                        + "\n"
                        + "    public void create(" + t.get() + " " + t.get().toLowerCase() + ") {\n"
                        + "        EntityManager em = null;\n"
                        + "        try {\n"
                        + "            em = getEntityManager();\n"
                        + "            em.getTransaction().begin();\n"
                        + "            em.persist(" + t.get().toLowerCase() + ");\n"
                        + "            em.getTransaction().commit();\n"
                        + "        } finally {\n"
                        + "            if (em != null) {\n"
                        + "                em.close();\n"
                        + "            }\n"
                        + "        }\n"
                        + "    }\n"
                        + "\n"
                        + "    public void edit(" + t.get() + " " + t.get().toLowerCase() + ") throws NonexistentEntityException, Exception {\n"
                        + "        EntityManager em = null;\n"
                        + "        try {\n"
                        + "            em = getEntityManager();\n"
                        + "            em.getTransaction().begin();\n"
                        + "            " + t.get().toLowerCase() + " = em.merge(" + t.get().toLowerCase() + ");\n"
                        + "            em.getTransaction().commit();\n"
                        + "        } catch (Exception ex) {\n"
                        + "            String msg = ex.getLocalizedMessage();\n"
                        + "            if (msg == null || msg.length() == 0) {\n"
                        + "                " + type.get() + " id = " + t.get().toLowerCase() + ".get" + getter.get() + "();\n"
                        + "                if (find" + t.get() + "(id) == null) {\n"
                        + "                    throw new NonexistentEntityException(\"The " + t.get().toLowerCase() + " with id \" + id + \" no longer exists.\");\n"
                        + "                }\n"
                        + "            }\n"
                        + "            throw ex;\n"
                        + "        } finally {\n"
                        + "            if (em != null) {\n"
                        + "                em.close();\n"
                        + "            }\n"
                        + "        }\n"
                        + "    }\n"
                        + "\n"
                        + "    public void destroy(" + type.get() + " id) throws NonexistentEntityException {\n"
                        + "        EntityManager em = null;\n"
                        + "        try {\n"
                        + "            em = getEntityManager();\n"
                        + "            em.getTransaction().begin();\n"
                        + "            " + t.get() + " " + t.get().toLowerCase() + " ;\n"
                        + "            try {\n"
                        + "                " + t.get().toLowerCase() + " = em.getReference(" + t.get() + ".class, id);\n"
                        + "                " + t.get().toLowerCase() + ".get" + getter.get() + "();\n"
                        + "            } catch (EntityNotFoundException enfe) {\n"
                        + "                throw new NonexistentEntityException(\"The " + t.get().toLowerCase() + " with id \" + id + \" no longer exists.\", enfe);\n"
                        + "            }\n"
                        + "            em.remove(" + t.get().toLowerCase() + ");\n"
                        + "            em.getTransaction().commit();\n"
                        + "        } finally {\n"
                        + "            if (em != null) {\n"
                        + "                em.close();\n"
                        + "            }\n"
                        + "        }\n"
                        + "    }\n"
                        + "\n"
                        + "    public List<" + t.get() + "> find" + t.get() + "Entities() {\n"
                        + "        return find" + t.get() + "Entities(true, -1, -1);\n"
                        + "    }\n"
                        + "\n"
                        + "    public List<" + t.get() + "> find" + t.get() + "Entities(int maxResults, int firstResult) {\n"
                        + "        return find" + t.get() + "Entities(false, maxResults, firstResult);\n"
                        + "    }\n"
                        + "\n"
                        + "    private List<" + t.get() + "> find" + t.get() + "Entities(boolean all, int maxResults, int firstResult) {\n"
                        + "        EntityManager em = getEntityManager();\n"
                        + "        try {\n"
                        + "            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();\n"
                        + "            cq.select(cq.from(" + t.get() + ".class));\n"
                        + "            Query q = em.createQuery(cq);\n"
                        + "            if (!all) {\n"
                        + "                q.setMaxResults(maxResults);\n"
                        + "                q.setFirstResult(firstResult);\n"
                        + "            }\n"
                        + "            return q.getResultList();\n"
                        + "        } finally {\n"
                        + "            em.close();\n"
                        + "        }\n"
                        + "    }\n"
                        + "\n"
                        + "    public " + t.get() + " find" + t.get() + "(" + type.get() + " id) {\n"
                        + "        EntityManager em = getEntityManager();\n"
                        + "        try {\n"
                        + "            return em.find(" + t.get() + ".class, id);\n"
                        + "        } finally {\n"
                        + "            em.close();\n"
                        + "        }\n"
                        + "    }\n"
                        + "\n"
                        + "    public int get" + t.get() + "Count() {\n"
                        + "        EntityManager em = getEntityManager();\n"
                        + "        try {\n"
                        + "            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();\n"
                        + "            Root<" + t.get() + "> rt = cq.from(" + t.get() + ".class);\n"
                        + "            cq.select(em.getCriteriaBuilder().count(rt));\n"
                        + "            Query q = em.createQuery(cq);\n"
                        + "            return ((Long) q.getSingleResult()).intValue();\n"
                        + "        } finally {\n"
                        + "            em.close();\n"
                        + "        }\n"
                        + "    }\n"
                        + "    \n"
                        + "}";
                return sc;
            }

        };

        area.textProperty().bind(sb);
    }

    private void setCSS() {
        copy.setStyle("-fx-background-color: #ff66ff;");
        clear.setStyle("-fx-background-color: #ff66ff;");
        manager.setStyle("-fx-background-color: #ff66ff;");
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
        textfield.setText("");
    }

    @FXML
    private void emanagercopy(ActionEvent event) {
        final Clipboard clip = Clipboard.getSystemClipboard();
        ClipboardContent c = new ClipboardContent();
        c.putString(getManagerText());
        clip.setContent(c);
    }

    private String getManagerText() {
        String emanager;
        emanager = ""
                + "import javax.persistence.EntityManagerFactory;\n"
                + "import javax.persistence.Persistence;\n"
                + "\n"
                + "\n"
                + "public class EManager {\n"
                + "    private static EntityManagerFactory em = Persistence.createEntityManagerFactory(\"aplicación\");\n"
                + "    private static EManager instance;\n"
                + "    \n"
                + "    \n"
                + "    private EManager(){\n"
                + "        //Singleton class\n"
                + "    }\n"
                + "\n"
                + "    \n"
                + "    \n"
                + "    public static EManager getInstance(){\n"
                + "        return (instance == null)? instance = new EManager(): instance;\n"
                + "        //Instance initializer\n"
                + "    }\n"
                + "    \n"
                + "    public EntityManagerFactory getEntityManagerFactory(){\n"
                + "        //EntityManagerFactory accesor\n"
                + "        return em;\n"
                + "    }\n"
                + "\n"
                + "    \n"
                + "    public void destroyManagerFactory() {\n"
                + "    em.close();\n"
                + "    }\n"
                + "}";
        return emanager;
    }

    @FXML
    private void exception1(ActionEvent event) {
        final Clipboard clip = Clipboard.getSystemClipboard();
        ClipboardContent c = new ClipboardContent();
        c.putString(getEx1());
        clip.setContent(c);

    }

    @FXML
    private void exception2(ActionEvent event) {
        final Clipboard clip = Clipboard.getSystemClipboard();
        ClipboardContent c = new ClipboardContent();
        c.putString(getEx2());
        clip.setContent(c);
    }

    @FXML
    private void exception3(ActionEvent event) {
        final Clipboard clip = Clipboard.getSystemClipboard();
        ClipboardContent c = new ClipboardContent();
        c.putString(getEx3());
        clip.setContent(c);
    }

    private String getEx1() {
        String exception;
        exception = ""
                + "import java.util.ArrayList;\n"
                + "import java.util.List;\n"
                + "\n"
                + "public class IllegalOrphanException extends Exception {\n"
                + "    private List<String> messages;\n"
                + "    public IllegalOrphanException(List<String> messages) {\n"
                + "        super((messages != null && messages.size() > 0 ? messages.get(0) : null));\n"
                + "        if (messages == null) {\n"
                + "            this.messages = new ArrayList<String>();\n"
                + "        }\n"
                + "        else {\n"
                + "            this.messages = messages;\n"
                + "        }\n"
                + "    }\n"
                + "    public List<String> getMessages() {\n"
                + "        return messages;\n"
                + "    }\n"
                + "}";

        return exception;
    }

    private String getEx2() {
        String exception;
        exception = ""
                + "public class NonexistentEntityException extends Exception {\n"
                + "    public NonexistentEntityException(String message, Throwable cause) {\n"
                + "        super(message, cause);\n"
                + "    }\n"
                + "    public NonexistentEntityException(String message) {\n"
                + "        super(message);\n"
                + "    }\n"
                + "}";

        return exception;
    }

    private String getEx3() {
        String exception;
        exception = ""
                + "public class PreexistingEntityException extends Exception {\n"
                + "    public PreexistingEntityException(String message, Throwable cause) {\n"
                + "        super(message, cause);\n"
                + "    }\n"
                + "    public PreexistingEntityException(String message) {\n"
                + "        super(message);\n"
                + "    }\n"
                + "}\n"
                + "";

        return exception;
    }

    private void setTooltips() {
        ex1();
        ex2();
        ex3();
    }

    private void ex1() {
        final Tooltip t = new Tooltip();
        t.setText("IllegalOrphanException");
        inoe.setTooltip(t);
    }

    private void ex2() {
        final Tooltip t = new Tooltip();
        t.setText("NonexistentEntityException");
        neee.setTooltip(t);
    }

    private void ex3() {
       final Tooltip t = new Tooltip();
       t.setText("PreexistingEntityException");
       peee.setTooltip(t);
    }
}
