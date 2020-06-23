package controller;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Context;
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
	private AnimationTimer gameTimer;

	public ControllerGame() {
		gamePane = new AnchorPane();
		gameScene = new Scene(gamePane, WIDTH, HEIGHT);
		gameStage = new Stage();
		gameStage.setScene(gameScene);

		createUI();
		createPlayerBase();
		createEnemyBase();
		createGameLoop();
	}

	public void createNewGame(Stage menuStage) {
		this.menuStage = menuStage;
		this.menuStage.hide();
		gameStage.show();
	}

	private void createGameLoop() {
		gameTimer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				gameIteration();
			}
		};
		gameTimer.start();
	}

	private void gameIteration() {
		Context context = new Context(500);
		player.IterateUnits(context);
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

		ui.addElement(player.getInfoLabel(), 600, 60);
		ui.addElement(player.getMoneyLabel(), 600, 120);

		ui.connectPlayerToButtons(player, gamePane);
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
