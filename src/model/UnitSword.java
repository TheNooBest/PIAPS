package model;

import javafx.scene.image.Image;

public class UnitSword extends Unit {

	public UnitSword(int hp, int damage, int position, boolean goingRight, Image image) {
		super(hp, damage, 1, position, goingRight, image);
	}

}
