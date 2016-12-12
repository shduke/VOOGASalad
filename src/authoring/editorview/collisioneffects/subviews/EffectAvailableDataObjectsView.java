package authoring.editorview.collisioneffects.subviews;

import authoring.editorview.collisioneffects.EffectAuthoringViewDelegate;
import authoring.editorview.collisioneffects.EffectSetView;
import javafx.scene.Node;
import javafx.scene.control.ListView;


public class EffectAvailableDataObjectsView implements EffectSetView {

    private EffectAuthoringViewDelegate delegate;
    private ListView<Node> listView;

    public EffectAvailableDataObjectsView () {
        listView = new ListView<Node>();
    }

    @Override
    public Node getInstanceAsNode () {
        return listView;
    }

    @Override
    public void setDelegate (EffectAuthoringViewDelegate delegate) {
        this.delegate = delegate;
    }

}
