package com.estebancano.webapp.biblioteca;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.estebancano.webapp.biblioteca.system.Main;

import javafx.application.Application;

@SpringBootApplication
public class Biblioteca2020374Application {

	public static void main(String[] args) {
		//levanta JavaFX
		Application.launch(Main.class, args);
		//Levanta SpringBoot
		SpringApplication.run(Biblioteca2020374Application.class, args);
	}

}
