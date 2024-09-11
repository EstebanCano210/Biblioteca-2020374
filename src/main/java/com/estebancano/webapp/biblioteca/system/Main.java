package com.estebancano.webapp.biblioteca.system;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import com.estebancano.webapp.biblioteca.Biblioteca2020374Application;
import com.estebancano.webapp.biblioteca.controller.FXController.IndexController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application{
    private ConfigurableApplicationContext aplicationContext;
    private Stage stage;
    private Scene scene;

    @Override
    public void init(){
        this.aplicationContext = new SpringApplicationBuilder(Biblioteca2020374Application.class).run();
    }

    //se ejecuta al iniciar la aplicacion de JavaFX
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.stage = primaryStage;
        stage.setTitle("Biblioteca");
        indexView();
        //carga la escena principal
        stage.show();
    }

    //Metodo Cambio de Escena
    public Initializable cambioEscena(String fxmlName, int width, int height) throws IOException{
        Initializable initializable = null;
        FXMLLoader loader = new FXMLLoader();

        loader.setControllerFactory(aplicationContext::getBean);
        InputStream archivo = Main.class.getResourceAsStream("/templates/" + fxmlName);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(Main.class.getResource("/templates/" + fxmlName));

        scene = new Scene((AnchorPane)loader.load(archivo), width, height);
        stage.setScene(scene);
        stage.sizeToScene();
        initializable = (Initializable)loader.getController();

        return initializable;
    }

    public void indexView(){
        try {
            IndexController indexView = (IndexController)cambioEscena("index.fxml", 550, 400);
            indexView.setStage(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
