package engine.weapon;

import java.util.List;
import authoring.editorview.weapon.IWeaponEditorView;
import engine.AbstractTypeManagerController;
import engine.ManagerMediator;


public class WeaponTypeManagerController extends
        AbstractTypeManagerController<WeaponManager, WeaponBuilder, Weapon, IWeaponEditorView>
        implements WeaponManagerController {

    public WeaponTypeManagerController (ManagerMediator managerMediator) {
        super(new WeaponTypeManager(), new WeaponTypeBuilder(), managerMediator);
    }

    @Override
    protected WeaponBuilder constructTypeProperties (IWeaponEditorView weaponUpdater,
                                                     WeaponBuilder typeBuilder) {
        return typeBuilder
                .addEffectListener( (oldValue, newValue) -> weaponUpdater
                        .updateCollisionEffectDisplay(newValue))
                .addFireRateListener( (oldValue, newValue) -> weaponUpdater
                        .updateFireRateDisplay(newValue))
                .addRangeListener( (oldValue, newValue) -> weaponUpdater
                        .updateRangeDisplay(newValue))
                .addSpeedListener( (oldValue, newValue) -> weaponUpdater
                        .updateSpeedDisplay(newValue))
                .addTargetsListener( (oldValue, newValue) -> weaponUpdater
                        .updateTargetEnemies(newValue))
                .addTrajectoryListener( (oldValue, newValue) -> weaponUpdater
                        .updateWeaponTrajectory(newValue));
    }

    @Override
    public void setWeaponRange (int weaponID, double weaponRange) {
        getTypeManager().getEntity(weaponID).setRange(weaponRange);
    }

    @Override
    public void setWeaponFireRate (int weaponID, double weaponFireRate) {
        getTypeManager().getEntity(weaponID).setFireRate(weaponFireRate);
    }

    @Override
    public void setWeaponSpeed (int weaponID, double weaponSpeed) {
        getTypeManager().getEntity(weaponID).setSpeed(weaponSpeed);
    }

    @Override
    public void setWeaponCollisionEffect (int weaponID, String weaponCollisionEffect) {
        getTypeManager().getEntity(weaponID).setEffect(weaponCollisionEffect);
    }

    @Override
    public void setWeaponTrajectory (int weaponID, String weaponTrajectory) {
        getTypeManager().getEntity(weaponID).setTrajectory(weaponTrajectory);
    }

    @Override
    public void setNewWeaponTargetEnemy (int weaponID, int enemyID) {
        getTypeManager().getEntity(weaponID).addTarget(enemyID);
    }

    @Override
    public void removeWeaponTargetEnemy (int weaponID, int enemyID) {
        getTypeManager().getEntity(weaponID).removeTarget(enemyID);
    }

    @Override
    public double getWeaponRange (int weaponID) {
        return getTypeManager().getEntity(weaponID).getRange();
    }

    @Override
    public double getWeaponFireRate (int weaponID) {
        return getTypeManager().getEntity(weaponID).getFireRate();
    }

    @Override
    public double getWeaponSpeed (int weaponID) {
        return getTypeManager().getEntity(weaponID).getSpeed();
    }

    @Override
    public String getWeaponCollisionEffect (int weaponID) {
        return getTypeManager().getEntity(weaponID).getEffect();
    }

    @Override
    public String getWeaponTrajectory (int weaponID) {
        return getTypeManager().getEntity(weaponID).getTrajectory();
    }

    @Override
    public List<Integer> getTargetEnemies (int weaponID) {
        return getTypeManager().getEntity(weaponID).getTargets();
    }

}