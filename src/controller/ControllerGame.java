package controller;

import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Player;
import model.UISubScene;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ControllerGame {

	private final static int WIDTH = 1000;
	private final static int HEIGHT = 800;
	private AnchorPane gamePane;
	private Stage gameStage;
	private Scene gameScene;

	private Stage menuStage;

	private UISubScene ui;

	private Player player;
	private Player AI;

	public ControllerGame() {
		gamePane = new AnchorPane();
		gameScene = new Scene(gamePane, WIDTH, HEIGHT);
		gameStage = new Stage();
		gameStage.setScene(gameScene);

		createUI();
		createPlayerBase();
		createEnemyBase();
	}

	public void createNewGame(Stage menuStage) {
		this.menuStage = menuStage;
		this.menuStage.hide();
		gameStage.show();
	}

	private void createPlayerBase() {
		Image base = null;
		try {
			base = new Image(new FileInputStream("src/model/resources/base.png"), 140, 140, false, true);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		player = new Player(base);
		gamePane.getChildren().add(player.getPane());
		player.getPane().setLayoutY(HEIGHT - 216 - 140);

		ui.addElement(player.getMoneyLabel(), 600, 100);

		ui.connectPlayerToButtons(player);
	}

	private void createEnemyBase() {

	}

	private void createUI() {
		ui = new UISubScene();
		ui.setLayoutY(HEIGHT - 216);
//		ui.connectPlayerToButtons(player);
		gamePane.getChildren().add(ui);
	}

}
