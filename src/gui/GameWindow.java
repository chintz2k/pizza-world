package gui;

import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import gui.elements.BottomPanel;
import gui.elements.TopPanel;
import logic.Game;

public class GameWindow {

    TopPanel tp;
    Text top;

    BottomPanel bp;
    Text bot;

    Group mid;
    MainWindow mw;
    Parent mwGroup;

    VBox container;

    Group root;

    
    public GameWindow(double height, Game game) {

        tp = new TopPanel(game);
        top = tp.getText();

        bp = new BottomPanel(game);
        bot = bp.getText();

        mid = new Group();
        mw = new MainWindow(this/* TODO das >this< ist wahrscheinlich das problem */, game);
        mwGroup = mw.getRoot();
        mid.getChildren().add(mwGroup);

        container = new VBox();
        container.getChildren().addAll(top, mid, bot);

        root = new Group();
        root.getChildren().add(container);

    }
    
    public Parent getRoot() {
        return root;
    }

    public void change(Group root) {
        mid.getChildren().clear();
        mid.getChildren().add(root);
    }

    public double getWidth() {
        return root.getScene().getWidth();
    }

}
