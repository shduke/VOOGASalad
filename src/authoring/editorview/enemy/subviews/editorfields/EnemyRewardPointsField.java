package authoring.editorview.enemy.subviews.editorfields;

import java.util.ResourceBundle;
import authoring.editorview.TextFieldView;
import authoring.editorview.enemy.EnemyEditorViewDelegate;
import authoring.editorview.enemy.IEnemySetView;
import authoring.utilityfactories.BoxFactory;
import authoring.utilityfactories.TextFieldFactory;
import javafx.scene.Node;
import javafx.scene.control.TextField;


public class EnemyRewardPointsField extends TextFieldView implements IEnemySetView {

    private EnemyEditorViewDelegate delegate;
    private TextField enemyRewardPointsField;

    public EnemyRewardPointsField (ResourceBundle labelsResource) {
        super(labelsResource);
    }

    @Override
    public void setDelegate (EnemyEditorViewDelegate delegate) {
        this.delegate = delegate;
    }

    @Override
    public Node getInstanceAsNode () {
        return hbox;
    }

    @Override
    public void updateField (String newData) {
        enemyRewardPointsField.setText(newData);
    }

    @Override
    protected void makeTextField (ResourceBundle labelsResource) {
        enemyRewardPointsField =
                TextFieldFactory.makeTextField(labelsResource.getString("EnterInt"),
                                               e -> delegate
                                                       .onUserEnteredEnemyPoints(enemyRewardPointsField
                                                               .getText()));
        hbox = BoxFactory.createHBoxWithLabelandNode(labelsResource.getString("RewardPoints"),
                                                     enemyRewardPointsField);
    }

}
