package ru.durnov.view;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class NodeWithLabelVBox extends VBox {
    private final Label label;

    public NodeWithLabelVBox(Node node, String text){
        this.label = new Label(text);
        this.getChildren().addAll(label, node);
        this.setSpacing(3);
    }

    public NodeWithLabelVBox(Label label, Node node) {
        this.label = label;
        this.getChildren().addAll(label, node);
        this.setSpacing(3);
    }
}
