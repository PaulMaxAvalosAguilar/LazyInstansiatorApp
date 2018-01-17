
package lazyinstanziator;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;


public class mainFXController implements Initializable {

    @FXML
    private AnchorPane pojoanc;
    @FXML
    private AnchorPane daoanc;
    @FXML
    private AnchorPane JPAC;
    @FXML
    private AnchorPane ehash;
   
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            AnchorPane pn = FXMLLoader.load(getClass().getResource("/FXML/builder.fxml"));
            pojoanc.getChildren().setAll(pn);
        } catch (IOException ex) {
            Logger.getLogger(mainFXController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            AnchorPane pn = FXMLLoader.load(getClass().getResource("/FXML/Dao.fxml"));
            daoanc.getChildren().setAll(pn);
        } catch (IOException ex) {
            Logger.getLogger(mainFXController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            AnchorPane pn = FXMLLoader.load(getClass().getResource("/FXML/JPAController.fxml"));
            JPAC.getChildren().setAll(pn);
        } catch (IOException ex) {
            Logger.getLogger(mainFXController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            AnchorPane pn = FXMLLoader.load(getClass().getResource("/FXML/Ehash.fxml"));
            ehash.getChildren().setAll(pn);
        } catch (IOException ex) {
            Logger.getLogger(mainFXController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }       
}
