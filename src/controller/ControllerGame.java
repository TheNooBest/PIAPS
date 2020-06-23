package controller;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Context;
import model.Player;
import model.UISubScene;
import model.Unit;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Random;

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
	private Random randomGenerator;

	public ControllerGame() {
		gamePane = new AnchorPane();
		gameScene = new Scene(gamePane, WIDTH, HEIGHT);
		gameStage = new Stage();
		gameStage.setScene(gameScene);
		randomGenerator = new Random();

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

	public void endGame() {
		gameStage.close();
		gameTimer.stop();
		menuStage.show();
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
		Unit playerUnit = player.getFarUnit();
		Unit AIUnit = AI.getFarUnit();

		Context playerContext = new Context(AIUnit, AIUnit == null ? 100000 : AIUnit.getPosition(), AI);
		Context aiContext = new Context(playerUnit, playerUnit == null ? 100000 : playerUnit.getPosition(), player);

		AIMove(randomGenerator.nextInt(10000));
		player.IterateUnits(playerContext);
		AI.IterateUnits(aiContext);
	}

	private void AIMove(int a) {
		if (a < 9970) {
			return;
		}
		if (a < 9980) {
			AI.SetMoney(1000);
			ImageView image = AI.BuyUnitPig();
			image.setLayoutY(800 - 216 - 32);
			gamePane.getChildren().add(image);
			return;
		}
		if (a < 9990) {
			AI.SetMoney(1000);
			ImageView image = AI.BuyUnitSword();
			image.setLayoutY(800 - 216 - 32);
			gamePane.getChildren().add(image);
			return;
		}
		if (a < 10000) {
			AI.SetMoney(1000);
			ImageView image = AI.BuyUnitArrow();
			image.setLayoutY(800 - 216 - 32);
			gamePane.getChildren().add(image);
			return;
		}
		AI.SetMoney(1000);
		AI.UpgradeAge();
	}

	private void createPlayerBase() {
		Image base = null;
		try {
			base = new Image(new FileInputStream("src/model/resources/base.png"), 140, 140, false, true);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		player = new Player(base, 140, true, this);
		gamePane.getChildren().add(player.getPane());
		player.getPane().setLayoutY(HEIGHT - 216 - 140);

		ui.addElement(player.getInfoLabel(), 600, 60);
		ui.addElement(player.getMoneyLabel(), 600, 120);

		ui.connectPlayerToButtons(player, gamePane);
	}

	private void createEnemyBase() {
		Image base = null;
		try {
			base = new Image(new FileInputStream("src/model/resources/base2.png"), 140, 140, false, true);
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		AI = new Player(base, WIDTH - 140 - 32, false, this);
		gamePane.getChildren().add(AI.getPane());
		AI.getPane().setLayoutX(WIDTH - 140);
		AI.getPane().setLayoutY(HEIGHT - 216 - 140);
	}

	private void createUI() {
		ui = new UISubScene();
		ui.setLayoutY(HEIGHT - 216);
		gamePane.getChildren().add(ui);
	}

}
