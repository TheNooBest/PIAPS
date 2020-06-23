package model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Unit {

	private UnitState state;
	private ImageView image;
	private int hp;
	private int damage;
	private int position;
	private boolean goingRight;

	public Unit(int hp, int damage, int position, boolean goingRight, Image image) {
		this.hp = hp;
		this.damage = damage;
		this.position = position;
		this.goingRight = goingRight;
		this.image = new ImageView(image);
	}

	public void ChangeState(UnitState state) {
		this.state = state;
	}

	public int getHP() {
		return hp;
	}

	public int getDamage() {
		return damage;
	}

	public int getPosition() {
		return position;
	}

	//<editor-fold desc="Action methods">
	public void Do() {
		state.Do();
	}

	public void Move(int direction) {
		position += goingRight ? direction : -direction;
	}

	public void TakeDamage(int damage) {
		// TODO
	}
	//</editor-fold>

}
