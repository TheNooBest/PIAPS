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

	private AGE age;
	private AtomicInteger money;    // AtomicInteger - костыль
	private int hp;
	private int generation;
	private int mineCost;
	private List<Unit> units;
	private AbstractUnitFactory factory;

	private AnchorPane pane;
	private ImageView image;
	private GameLabel labelMoney;
	private GameLabel labelInfo;

	public Player(Image base) {
		age = AGE.FIRST;
		money = new AtomicInteger(0);
		hp = 100;
		generation = 1;
		mineCost = 10;
		units = new ArrayList<>();
		factory = new UnitFactoryFirstAge(140, true);

		pane = new AnchorPane();
		image = new ImageView(base);
		labelMoney = new GameLabel("0");
		labelInfo = new GameLabel("1 : 100");

		// Таймер, чтобы монетки капали кажду секунду
		Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), ev -> {
			money.set(money.get() + generation);
			labelMoney.setText(String.valueOf(money));
		}));
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();

		pane.getChildren().add(image);
	}

	public AnchorPane getPane() {
		return pane;
	}

	public GameLabel getInfoLabel() {
		return labelInfo;
	}

	public GameLabel getMoneyLabel() {
		return labelMoney;
	}

	public void IterateUnits(Context context) {
		for (Unit unit : units) {
			unit.Do(context);
		}
	}

	//<editor-fold desc="Buy">
	// Возвращаю картинку, которую добавлю на карту
	public ImageView BuyUnitSword() {
		Unit unit = factory.createUnitSword(money);
		if (unit == null)
			return null;

		units.add(unit);
		return unit.getImageView();
	}

	public ImageView BuyUnitArrow() {
		Unit unit = factory.createUnitArrow(money);
		if (unit == null)
			return null;

		units.add(unit);
		return unit.getImageView();
	}

	public ImageView BuyUnitPig() {
		Unit unit = factory.createUnitPig(money);
		if (unit == null)
			return null;

		units.add(unit);
		return unit.getImageView();
	}
	//</editor-fold>

	//<editor-fold desc="Upgrade">
	public void UpgradeMine() {
		if (money.get() < mineCost)
			return;

		money.set(money.get() - mineCost);
		mineCost += mineCost;
		generation++;
	}

	// TODO Надо как-то вывести на кнопки стоимость юнитов (Надо ли? :^) )
	public void UpgradeAge() {
		switch (age) {
			case FIRST:
				if (money.get() < 100)
					return;
				money.set(money.get() - 100);
				hp = 140;
				labelInfo.setText("2 : 140");
				factory = new UnitFactorySecondAge(140, true);
				break;

			case SECOND:
				if (money.get() < 300)
					return;
				money.set(money.get() - 300);
				hp = 200;
				labelInfo.setText("3 : 200");
				factory = new UnitFactoryThirdAge(140, true);
				break;

			case THIRD:
				break;
		}
	}
	//</editor-fold>

	public void TakeDamage(int damage) {
		if (hp <= damage) {
			// TODO
		}

		hp -= damage;
		labelInfo.setText(age.getNumber() + " : " + hp);
	}

}
