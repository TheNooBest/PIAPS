package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.AboutSubScene;
import model.GameButton;
import model.InfoLabel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class ControllerMenu {

	private final static int WIDTH = 1000;
	private final static int HEIGHT = 800;
	private AnchorPane mainPane;
	private Stage mainStage;
	private Scene mainScene;

	private AboutSubScene aboutSubScene;
	private AboutSubScene sceneToHude;

	private final static int MENU_BUTTONS_OFFSET_X = 70;
	private final static int MENU_BUTTONS_OFFSET_Y = 280;

	private List<GameButton> menuButtons = new ArrayList<>();

	public ControllerMenu() {
		mainPane = new AnchorPane();
		mainScene = new Scene(mainPane, WIDTH, HEIGHT);
		mainStage = new Stage();
		mainStage.setScene(mainScene);

		createBackground();
		createLogo();
		createButtons();
		createAboutSubScene();
	}

	public Stage GetMainStage() {
		return mainStage;
	}
	private void showSubscene(AboutSubScene aboutSubscene){
		if(sceneToHude != null){
			sceneToHude.moveSubScence();
		}
		aboutSubscene.moveSubScence();
		sceneToHude = aboutSubscene;
	}
	private void createAboutSubScene() {
		aboutSubScene = new AboutSubScene();
		mainPane.getChildren().add(aboutSubScene);

		InfoLabel aboutLabel = new InfoLabel("ИГРА РАЗРАБОТАНА СТУДЕНТАМИ НИУ МИЭТ ИНСТИТУТА СПИНТЕХ ГРУППЫ ПИН-32: " +
				"\nДМИТРИЙ МЕЩАНКИН, \nДМИТРИЙ СУХОНОСИК, \nАРТЁМ СОРОКИН " +
				"\nСПРАЙТЫ ВЗЯТЫ С САЙТА: https://kenney.nl/");
		aboutLabel.setLayoutX(30);
		aboutLabel.setLayoutY(3);

		aboutSubScene.getPane().getChildren().add(aboutLabel);
	}

	//<editor-fold desc="Menu buttons">
	private void createButtons() {
		createStartButton();
		createAboutButton();
		createExitButton();
	}

	private void addMenuButton(GameButton button) {
		button.setLayoutX(MENU_BUTTONS_OFFSET_X);
		button.setLayoutY(MENU_BUTTONS_OFFSET_Y + menuButtons.size() * 70);
		menuButtons.add(button);
		mainPane.getChildren().add(button);
	}

	private void createStartButton() {
		GameButton button = new GameButton("Start");
		addMenuButton(button);

		button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				ControllerGame game = new ControllerGame();
				game.createNewGame(mainStage);
			}
		});
	}

	private void createAboutButton() {
		GameButton button = new GameButton("About");
		addMenuButton(button);

		button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				showSubscene(aboutSubScene);
			}
		});
	}

	private void createExitButton() {
		GameButton button = new GameButton("Exit");
		addMenuButton(button);

		button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				mainStage.close();
			}
		});
	}
	//</editor-fold>

	private void createBackground() {
		// Хренова Джава. Нормальный конструктор с URL не работает
		Image bgPattern = null;
		try {
			bgPattern = new Image(new FileInputStream("src/controller/resources/bg_wood.png"), 256, 256, false, true);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		BackgroundImage bg = new BackgroundImage(bgPattern, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
		mainPane.setBackground(new Background(bg));
	}

	private void createLogo() {
		ImageView logo = null;
		try {
			logo = new ImageView(new Image(new FileInputStream("src/controller/resources/logo.png")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return;
		}
		logo.setLayoutX(500);
		logo.setLayoutY(20);
		mainPane.getChildren().add(logo);
	}

}
