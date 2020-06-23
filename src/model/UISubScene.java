package model;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class UISubScene extends SubScene {

	private final static String FONT_PATH = "src/model/resources/kenvector_future.ttf";
	private final static String BG_IMAGE_PATH = "src/controller/resources/grass.png";

	private final static int WIDTH = 1000;
	private final static int HEIGHT = 216;

	private AnchorPane pane;

	private SpawnButton spawnSwordButton;
	private SpawnButton spawnArrowButton;
	private SpawnButton spawnPigButton;

	private GameButton upgradeMineButton;
	private GameButton upgradeAgeButton;

	public UISubScene() {
		super(new AnchorPane(), WIDTH, HEIGHT);
		prefWidth(WIDTH);
		prefHeight(HEIGHT);

		BackgroundImage bg = null;
		try {
			bg = new BackgroundImage(new Image(new FileInputStream(BG_IMAGE_PATH), 132, 216, false, true),
					BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		pane = (AnchorPane) this.getRoot();
		pane.setBackground(new Background(bg));
		addSpawnButtons();
		addUpgradeButtons();
	}

	public void addElement(Node node, int x, int y) {
		node.setLayoutX(x);
		node.setLayoutY(y);
		pane.getChildren().add(node);
	}

	private void addSpawnButtons() {
		Image imageSword = null;
		Image imageArrow = null;
		Image imagePig = null;

		//<editor-fold desc="try-catch">
		try {
			imageSword = new Image(new FileInputStream("src/model/resources/sword.png"), 32, 32, false, true);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			imageArrow = new Image(new FileInputStream("src/model/resources/arrow.png"), 32, 32, false, true);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			imagePig = new Image(new FileInputStream("src/model/resources/pig.png"), 32, 32, false, true);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		//</editor-fold>

		spawnSwordButton = new SpawnButton("Swordsman", imageSword);
		spawnArrowButton = new SpawnButton("Archer", imageArrow);
		spawnPigButton = new SpawnButton("Pig", imagePig);

		pane.getChildren().add(spawnSwordButton);
		pane.getChildren().add(spawnArrowButton);
		pane.getChildren().add(spawnPigButton);

		spawnSwordButton.setLayoutX(20);
		spawnSwordButton.setLayoutY(70);
		spawnArrowButton.setLayoutX(140);
		spawnArrowButton.setLayoutY(70);
		spawnPigButton.setLayoutX(260);
		spawnPigButton.setLayoutY(70);
	}

	private void addUpgradeButtons() {
		upgradeAgeButton = new GameButton("Up Age");
		upgradeMineButton = new GameButton("Up Mine");

		upgradeAgeButton.setWrapText(true);
		upgradeMineButton.setWrapText(true);

		pane.getChildren().add(upgradeAgeButton);
		pane.getChildren().add(upgradeMineButton);

		upgradeAgeButton.setLayoutX(380);
		upgradeAgeButton.setLayoutY(60);
		upgradeMineButton.setLayoutX(380);
		upgradeMineButton.setLayoutY(120);
	}

	public void connectPlayerToButtons(Player player) {
		spawnSwordButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				player.BuyUnitSword();
			}
		});
		spawnArrowButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				player.BuyUnitArrow();
			}
		});
		spawnPigButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				player.BuyUnitPig();
			}
		});
		upgradeAgeButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				player.UpgradeAge();
			}
		});
		upgradeMineButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				player.UpgradeMine();
			}
		});
	}

}
