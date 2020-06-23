package model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Unit {

	private UnitState state;
	private ImageView image;
	private int hp;
	private int damage;
	private int speed;
	private int position;
	private boolean goingRight;

	public Unit(int hp, int damage, int speed, int position, boolean goingRight, Image image) {
		this.hp = hp;
		this.damage = damage;
		this.speed = speed;
		this.position = position;
		this.goingRight = goingRight;
		this.image = new ImageView(image);
		this.state = new UnitStateMoving(this);

		this.image.setLayoutX(position);
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

	public int getSpeed() {
		return speed;
	}

	public int getPosition() {
		return position;
	}

	public ImageView getImageView() {
		return image;
	}

	//<editor-fold desc="Action methods">
	public void Do(Context context) {
		state.Do(context);
	}

	public void Move(int direction) {
		position += goingRight ? direction : -direction;
		image.setLayoutX(position);
	}

	public void TakeDamage(int damage) {
		// TODO
	}
	//</editor-fold>

}
