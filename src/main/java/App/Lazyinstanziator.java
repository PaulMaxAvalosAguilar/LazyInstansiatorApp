
package App;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class Lazyinstanziator extends Application {

    public static Stage st;

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = (Parent) FXMLLoader.load(getClass().getResource("/FXML/main.fxml"));
        Scene scene = new Scene(root);
        st = stage;
        stage.setScene(scene);
        stage.getIcons().add(new Image("/Images/desktop-2-xxl.png"));
        stage.setTitle("JavaFXLazyInstanziator");
        stage.setResizable(false);
        stage.show();
    }
    
    public static void main(String[] args) {
        Lazyinstanziator.launch((String[]) args);
    }

}
