package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.GameButton;

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

	private final static int MENU_BUTTONS_OFFSET_X = 20;
	private final static int MENU_BUTTONS_OFFSET_Y = 20;

	private List<GameButton> menuButtons = new ArrayList<>();

	public ControllerMenu() {
		mainPane = new AnchorPane();
		mainScene = new Scene(mainPane, WIDTH, HEIGHT);
		mainStage = new Stage();
		mainStage.setScene(mainScene);

		createBackground();
		createLogo();
		createButtons();
	}

	public Stage GetMainStage() {
		return mainStage;
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

		// TODO
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
