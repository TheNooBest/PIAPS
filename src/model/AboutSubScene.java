package model;

import javafx.animation.TranslateTransition;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.util.Duration;

public class AboutSubScene extends SubScene {
    private final static String FONT_PATH = "/model/resources/kenvector_future.ttf";
    private final static String BACKGROUND_IMAGE = "/model/resources/red_panel.png";

    private boolean isHidden;

    public AboutSubScene() {
        super(new AnchorPane(), 600, 400);
        prefWidth(600);
        prefHeight(400);

        BackgroundImage image = new BackgroundImage(new Image(BACKGROUND_IMAGE,600,400,false,true), BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,null);

        isHidden = true;

        AnchorPane root = (AnchorPane) this.getRoot();

        root.setBackground(new Background(image));

        setLayoutX(1024);
        setLayoutY(280);
    }
    public void moveSubScence(){
        TranslateTransition translateTransition = new TranslateTransition();
        translateTransition.setDuration(Duration.seconds(0.3));
        translateTransition.setNode(this);

        if(isHidden) {
            translateTransition.setToX(-676);
            isHidden = false;
        }
        else {
            translateTransition.setToX(0);
            isHidden = true;
        }

        translateTransition.play();
    }
    public AnchorPane getPane(){
        return (AnchorPane) this.getRoot();
    }
}
