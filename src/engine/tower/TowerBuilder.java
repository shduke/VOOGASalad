package engine.tower;

import java.util.List;
import java.util.function.BiConsumer;
import engine.TypeBuilder;
import engine.ability.Ability;
import engine.enemy.Enemy;
import engine.weapon.Weapon;


public interface TowerBuilder extends TypeBuilder<Tower, TowerBuilder>, TowerInitializer, BindableTower { //TODO - Add bindable interface

    TowerBuilder buildUpgrades (Integer ... upgrades);
    
    TowerBuilder buildUpgrades (List<Integer> upgrades);

    TowerBuilder buildWeapons (Integer ... weapons);
    
    TowerBuilder buildWeapons (List<Integer> weapons);

    TowerBuilder buildTargets (Integer ... targets);
    
    TowerBuilder buildTargets (List<Integer> targets);

    TowerBuilder buildAbilities (Integer ... targets);
    
    TowerBuilder buildAbilities (List<Integer> targets);

    TowerBuilder buildCost (double cost);

    TowerBuilder buildSellAmount (double sellAmount);

    TowerBuilder buildUnlockLevel (int unlockLevel);

}
