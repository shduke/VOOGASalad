package authoring.editorview.tower.subviews.editorfields;

import java.util.ResourceBundle;
import authoring.editorview.ImageBank;
import authoring.editorview.tower.ITowerSetView;
import authoring.editorview.tower.TowerAuthoringViewDelegate;
import authoring.utilityfactories.ButtonFactory;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;


/**
 * 
 * @author Kayla Schulz
 * @author andrewbihl
 *
 */
public class TowerUpgradeBank extends ImageBank implements ITowerSetView {

    protected final int DEFAULT_BANK_HEIGHT = 80;

    private TowerAuthoringViewDelegate delegate;
    private ResourceBundle labelsResource;
    private Button addTowerUpgrade;

    public TowerUpgradeBank (ResourceBundle labelsResource) {
        super();
        Button createTowerButton =
                ButtonFactory.makeButton("New",
                                         e -> {
                                             delegate.onUserPressedCreateTowerUpgrade();
                                         });
        this.listView.setOrientation(Orientation.HORIZONTAL);
        this.listView.setMaxHeight(DEFAULT_BANK_HEIGHT);
        this.labelsResource = labelsResource;
        this.items.add(createTowerButton);
        this.CONTENT_OFFSET = 1;
    }

    @Override
    public void setDelegate (TowerAuthoringViewDelegate delegate) {
        this.delegate = delegate;
    }

    @Override
    public Node getInstanceAsNode () {
        return this.listView;
    }

    private void dummyMethod () {
        System.out.println("Help");
    }

    @Override
    protected void userSelectedRow (int index) {
        this.delegate.onUserSelectedTower(this.itemIDs.get(index));
    }
}
