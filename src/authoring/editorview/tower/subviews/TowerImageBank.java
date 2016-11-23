package authoring.editorview.tower.subviews;

import java.io.File;
import java.io.IOException;
import authoring.editorview.PhotoFileChooser;
import authoring.editorview.tower.TowerEditorViewDelegate;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;


public class TowerImageBank extends PhotoFileChooser {

    private TowerEditorViewDelegate delegate;
    private ScrollPane towerBank;
    private VBox vbox;
    private File chosenFile;

    public TowerImageBank () {
        towerBank = new ScrollPane();
    }

    public void setDelegate (TowerEditorViewDelegate delegate) {
        this.delegate = delegate;
    }
    
    public Node getInstanceAsNode () {
        return towerBank;
    }

    @Override
    public void openFileChooser (FileChooser chooseFile) throws IOException {
        // TODO Auto-generated method stub
        
    }

}
