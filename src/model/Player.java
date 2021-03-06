package model;

import controller.ControllerGame;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Player {

	private AGE age;
	private AtomicInteger money;    // AtomicInteger - костыль
	private int hp;
	private int generation;
	private int mineCost;
	private boolean left;
	private int basePosition;
	private List<Unit> units;
	private AbstractUnitFactory factory;

	private ControllerGame game;
	private AnchorPane pane;
	private ImageView image;
	private GameLabel labelMoney;
	private GameLabel labelInfo;

	public Player(Image base, int position, boolean direction, ControllerGame controller) {
		age = AGE.FIRST;
		money = new AtomicInteger(0);
		hp = 10000;
		generation = 1;
		mineCost = 10;
		left = direction;
		basePosition = position;
		units = new ArrayList<>();
		factory = new UnitFactoryFirstAge(position, direction);

		pane = new AnchorPane();
		image = new ImageView(base);
		labelMoney = new GameLabel("0");
		labelInfo = new GameLabel("1 : 10000");
		game = controller;

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
		Iterator itr = units.iterator();
		while (itr.hasNext()) {
			Unit unit = (Unit) itr.next();
			if (unit.getHP() <= 0){
				((AnchorPane)unit.getImageView().getParent()).getChildren().remove(unit.getImageView());
				itr.remove();
			}
			else
				unit.Do(context);
		}
	}

	public Unit getFarUnit() {
		if (units.size() == 0)
			return null;
		return left ?
				units.stream().max((x, y) -> x.getPosition() - y.getPosition()).get() :
				units.stream().min((x, y) -> x.getPosition() - y.getPosition()).get();
	}

	public boolean isPlayerSide() {
		return left;
	}

	public boolean isClose(Unit unit, int distance) {
		return left ?
				unit.getPosition() - basePosition < distance :
				basePosition - unit.getPosition() < distance;
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

	public void UpgradeAge() {
		switch (age) {
			case FIRST:
				if (money.get() < 100)
					return;
				money.set(money.get() - 100);
				hp = 20000;
				labelInfo.setText("2 : 20000");
				factory = new UnitFactorySecondAge(140, true);
				age = AGE.SECOND;
				break;

			case SECOND:
				if (money.get() < 300)
					return;
				money.set(money.get() - 300);
				hp = 40000;
				labelInfo.setText("3 : 40000");
				factory = new UnitFactoryThirdAge(140, true);
				age = AGE.THIRD;
				break;

			case THIRD:
				break;
		}
	}
	//</editor-fold>

	public void TakeDamage(int damage) {
		if (hp <= damage) {
			game.endGame();
		}

		hp -= damage;
		labelInfo.setText(age.getNumber() + " : " + hp);
	}

	public void SetMoney(int money) {
		this.money.set(money);
	}

}
