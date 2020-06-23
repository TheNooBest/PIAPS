package model;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Player {

	private int age;
	private AtomicInteger money;
	private int hp;
	private List<Unit> units;
	private AbstractUnitFactory factory;

	private AnchorPane pane;
	private ImageView image;
	private GameLabel labelMoney;
	private GameLabel labelInfo;

	public Player(Image base) {
		age = 1;
		money = new AtomicInteger(0);
		hp = 100;
		units = new ArrayList<>();
		factory = new UnitFactoryFirstAge(140, true);

		pane = new AnchorPane();
		image = new ImageView(base);
		labelMoney = new GameLabel("0");
		labelInfo = new GameLabel("1 : 100");

		// Таймер, чтобы монетки капали кажду секунду
		Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), ev -> {
			money.incrementAndGet();
			labelMoney.setText(String.valueOf(money));
		}));
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();

		pane.getChildren().add(image);
		pane.getChildren().add(labelInfo);

		double labelScaleFactor = 140.0 / 380.0;
		labelInfo.setLayoutX((140 - 380) / 2.0);
		labelInfo.setLayoutY(-labelScaleFactor * 49 * 1.5);
		labelInfo.setScaleX(labelScaleFactor);
		labelInfo.setScaleY(labelScaleFactor);
	}

	public AnchorPane getPane() {
		return pane;
	}

	public GameLabel getMoneyLabel() {
		return labelMoney;
	}

	public void IterateUnits() {
		for (Unit unit : units) {
			unit.Do();
		}
	}

	// Бул возвращаю по приколу, мб пригодится
	public boolean BuyUnitSword() {
		Unit unit = factory.createUnitSword(money);
		if (unit == null)
			return false;

		units.add(unit);
		return true;
	}

	public boolean BuyUnitArrow() {
		Unit unit = factory.createUnitArrow(money);
		if (unit == null)
			return false;

		units.add(unit);
		return true;
	}

	public boolean BuyUnitPig() {
		Unit unit = factory.createUnitPig(money);
		if (unit == null)
			return false;

		units.add(unit);
		return true;
	}

}
