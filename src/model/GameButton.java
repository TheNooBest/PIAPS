package model;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GameButton extends Button {

	private final String FONT_PATH = "src/model/resources/kenvector_future.ttf";
	private final String BUTTON_STYLE = "-fx-background-color: transparent; -fx-background-image: url('/model/resources/red_button.png'); ";
	private final String BUTTON_STYLE_PRESSED = "-fx-background-color: transparent; -fx-background-image: url('/model/resources/red_button_pressed.png'); ";

	public GameButton(String text) {
		setText(text);
		setButtonFont();
		setPrefWidth(190);
		setPrefHeight(49);
		setStyle(BUTTON_STYLE);
		initButtonListeners();
	}

	private void setButtonFont() {
		try {
			setFont(Font.loadFont(new FileInputStream(FONT_PATH), 23));
		} catch (FileNotFoundException e) {
			setFont(Font.font("Verdana", 23));
		}
	}

	private void setButtonReleasedStyle() {
		setStyle(BUTTON_STYLE);
		setPrefHeight(49);
		setLayoutY(getLayoutY() - 4);
	}

	private void setButtonPressedStyle() {
		setStyle(BUTTON_STYLE_PRESSED);
		setPrefHeight(45);
		setLayoutY(getLayoutY() + 4);
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
