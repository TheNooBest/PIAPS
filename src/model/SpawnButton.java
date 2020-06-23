package model;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class SpawnButton extends Button {

	private final String FONT_PATH = "src/model/resources/kenvector_future.ttf";
	private final String BUTTON_STYLE = "-fx-background-color: transparent; " +
			"-fx-background-size: 98px 98px; " +
			"-fx-background-repeat: no-repeat; " +
			"-fx-background-image: url('/model/resources/btn1.png'); " +
			"-fx-content-display: top; ";
	private final String BUTTON_STYLE_PRESSED = "-fx-background-color: transparent; " +
			"-fx-background-size: 98px 90px; " +
			"-fx-background-repeat: no-repeat; " +
			"-fx-background-image: url('/model/resources/btn2.png'); " +
			"-fx-content-display: top; ";

	private ImageView unitImage;

	public SpawnButton(String text, Image unitImage) {
		setText(text);
		setButtonFont();
		setPrefWidth(98);
		setPrefHeight(98);
		setStyle(BUTTON_STYLE);
		initButtonListeners();

		this.unitImage = new ImageView(unitImage);
		setGraphic(this.unitImage);
	}

	private void setButtonFont() {
		try {
			setFont(Font.loadFont(new FileInputStream(FONT_PATH), 10));
		} catch (FileNotFoundException e) {
			setFont(Font.font("Verdana", 10));
		}
	}

	private void setButtonReleasedStyle() {
		setStyle(BUTTON_STYLE);
		setPrefHeight(98);
		setLayoutY(getLayoutY() - 8);
	}

	private void setButtonPressedStyle() {
		setStyle(BUTTON_STYLE_PRESSED);
		setPrefHeight(90);
		setLayoutY(getLayoutY() + 8);
	}

	private void initButtonListeners() {
		setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				setEffect(new DropShadow());
			}
		});
		setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				setEffect(null);
			}
		});
		setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (event.getButton().equals(MouseButton.PRIMARY))
					setButtonPressedStyle();
			}
		});
		setOnMouseReleased(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (event.getButton().equals(MouseButton.PRIMARY))
					setButtonReleasedStyle();
			}
		});
	}

}
