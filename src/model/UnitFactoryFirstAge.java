package model;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.concurrent.atomic.AtomicInteger;

public class UnitFactoryFirstAge implements AbstractUnitFactory {

	private Unit unit;
	private int defaultPosition;
	private boolean goingRight;

	public UnitFactoryFirstAge(int defaultPosition, boolean goingRight) {
		this.defaultPosition = defaultPosition;
		this.goingRight = goingRight;
	}

	public Unit createUnitSword(AtomicInteger money) {
		int cost = 10;
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
		return new Unit(10, 5, 1, defaultPosition, goingRight, image);
	}

	public Unit createUnitArrow(AtomicInteger money) {
		int cost = 12;
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
		return new Unit(5, 7, 1, defaultPosition, goingRight, image);
	}

	public Unit createUnitPig(AtomicInteger money) {
		int cost = 8;
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
		return new Unit(7, 4, 2, defaultPosition, goingRight, image);
	}
}
