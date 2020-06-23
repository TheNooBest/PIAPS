package model;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GameLabel extends Label {

	private final String FONT_PATH = "src/model/resources/kenvector_future.ttf";
	private final String BACKGROUND_IMAGE = "src/model/resources/red_label.png";

	public GameLabel(String text) {
		setPrefWidth(380);
		setPrefHeight(49);
		setText(text);
		setAlignment(Pos.CENTER);
		setWrapText(true);
		setLabelFont();

		BackgroundImage backgroundImage = null;
		try {
			backgroundImage = new BackgroundImage(new Image(new FileInputStream(BACKGROUND_IMAGE), 380, 49, false, true),
					BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return;
		}
		setBackground(new Background(backgroundImage));
	}

	private void setLabelFont() {
		try {
			setFont(Font.loadFont(new FileInputStream(FONT_PATH), 23));
		} catch (FileNotFoundException e) {
			setFont(Font.font("Verdana", 23));
		}
	}

}
