package com.sincos.app;

//import com.sincos.app.settings.SettingsHolder;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/*import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;*/

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxmls/main.fxml"));
        fxmlLoader.setController(new Controller(primaryStage));
        Parent root = fxmlLoader.load();
        primaryStage.setTitle("SINCOS MIP-API");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        //primaryStage.getIcons().add(new javafx.scene.image.Image("/images/mrmate_32.png"));

        //Platform.setImplicitExit(false);



        /*Parent root = FXMLLoader.load(getClass().getResource("/main.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));*/
        primaryStage.show();


    }


    public static void main(String[] args) {
        launch(args);
    }
}
