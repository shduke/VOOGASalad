package engine.level;

import engine.AbstractTypeManager;
import engine.enemy.EnemyManager;
import engine.path.PathManager;
import engine.settings.GameModeManager;


/**
 *
 * Created by ezra on 11/17/16.
 */
public class LevelTypeManager extends AbstractTypeManager<Level> implements LevelManager {
	//TODO path references
	
    @Override
    public void visitRemoveEntry (EnemyManager manager, Integer index) {
        applyToAllEntities(a -> a.removeEnemyReferences(index));

    }

	@Override
	public void visitRemoveEntry(PathManager manager, Integer index) {
		removePathReferences(index);
	}

	@Override
	public void visitAddPath(GameModeManager manager, Integer pathID) {
		applyToAllEntities(a -> a.addPath(pathID));
	}

	@Override
	public void visitRemovePath(GameModeManager manager, Integer pathID) {
		removePathReferences(pathID);	
	}
	
	private void removePathReferences(int pathID) {
        applyToAllEntities(a -> a.removePath(pathID));
	}

}
