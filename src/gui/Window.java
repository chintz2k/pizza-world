package gui;

import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import gui.elements.BottomPanel;
import gui.elements.TopPanel;
import logic.Game;

/**
 *
 * @author André Heinen
 */
public class Window {

    private VBox root;
    private TopPanel topPanel;
    private VBox content;
    private FlowPane buttons;
    private BottomPanel[] bottomPanels;
    private BottomPanel bottomPanel;

    public Window(Game game) {

        root = new VBox();
        
        topPanel = new TopPanel(game);
        root.getChildren().add(topPanel.getVBox());

        content = new VBox();
        root.getChildren().add(content);

        buttons = new FlowPane();
        root.getChildren().add(buttons);
        
        if (Game.DEBUGGING) {
            bottomPanels = new BottomPanel[Game.NUMBER_OF_PLAYERS];
            for (int i = 0; i < bottomPanels.length; ++i) {
                bottomPanels[i] = new BottomPanel(game, i);
                root.getChildren().add(bottomPanels[i].getVbox());
            }
        } else {
            bottomPanel = new BottomPanel(game);
            root.getChildren().add(bottomPanel.getVbox());
        }

        // DEBUGGING     
        // root.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        // content.setBackground(new Background(new BackgroundFill(Color.PURPLE, CornerRadii.EMPTY, Insets.EMPTY)));
        // buttons.setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));
    }
    
    public VBox getRoot() {
        return root;
    }

    public void update(Region region, Button[] buttons) {
        content.getChildren().clear();
        content.getChildren().add(region);
        this.buttons.getChildren().clear();
        this.buttons.getChildren().addAll(buttons);
        if (buttons.length > 2) {
            for (int i = 0; i < buttons.length; ++i) {
                buttons[i].setPrefSize(GuiConfig.BUTTON_2_WIDTH, GuiConfig.BUTTON_HEIGHT);
            }
            if (Game.DEBUGGING) {
                region.setPrefHeight(GuiConfig.CONTENT_HEIGHT_2_BUTTONROWS_DEBUGGING);
            } else {
                region.setPrefHeight(GuiConfig.CONTENT_HEIGHT_2_BUTTONROWS);
            }
        } else {
            if (buttons.length == 1) {
                buttons[0].setPrefSize(GuiConfig.BUTTON_1_WIDTH, GuiConfig.BUTTON_HEIGHT);
            } else {
                for (int i = 0; i < buttons.length; ++i) {
                    buttons[i].setPrefSize(GuiConfig.BUTTON_2_WIDTH, GuiConfig.BUTTON_HEIGHT);
                }
            }
            if (Game.DEBUGGING) {
                region.setPrefHeight(GuiConfig.CONTENT_HEIGHT_1_BUTTONSROW_DEBUGGING);
            } else {
                region.setPrefHeight(GuiConfig.CONTENT_HEIGHT_1_BUTTONROW);
            }
        }
    }

}
