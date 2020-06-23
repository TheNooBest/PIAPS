package model;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.concurrent.atomic.AtomicInteger;

public class UnitFactorySecondAge implements AbstractUnitFactory {

	private Unit unit;
	private int defaultPosition;
	private boolean goingRight;

	public UnitFactorySecondAge(int defaultPosition, boolean goingRight) {
		this.defaultPosition = defaultPosition;
		this.goingRight = goingRight;
	}

	public Unit createUnitSword(AtomicInteger money) {
		int cost = 15;
		if (money.get() < cost)
			return null;

		Image image = null;
		try {
			image = new Image(new FileInputStream("src/model/resources/sword.png"), 32, 32, false, true);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		money.set(money.get() - cost);
		return new UnitSword(15, 7, defaultPosition, goingRight, image);
	}

	public Unit createUnitArrow(AtomicInteger money) {
		int cost = 18;
		if (money.get() < cost)
			return null;

		Image image = null;
		try {
			image = new Image(new FileInputStream("src/model/resources/arrow.png"), 32, 32, false, true);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		money.set(money.get() - cost);
		return new UnitArcher(8, 11, defaultPosition, goingRight, image);
	}

	public Unit createUnitPig(AtomicInteger money) {
		int cost = 10;
		if (money.get() < cost)
			return null;

		Image image = null;
		try {
			image = new Image(new FileInputStream("src/model/resources/pig.png"), 32, 32, false, true);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		money.set(money.get() - cost);
		return new UnitPig(10, 6, defaultPosition, goingRight, image);
	}
}
