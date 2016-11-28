package authoring.editorview.enemy;

import java.io.IOException;

/**
 * 
 * @author Diane Hadley
 *
 */


public class EnemyEditorViewFactory {

    public static IEnemyEditorView build (int width, int height) throws IOException {
        return new EnemyEditorView();
    }

}
