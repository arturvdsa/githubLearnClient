package com.example.learnclient.modules;

import net.minecraft.entity.attribute.EntityAttributes;

public class ReachModule extends Module {
    private final Setting entity = number("Entity", 6.0, 3.0, 20.0, 0.5);
    private final Setting block = number("Block", 8.0, 4.5, 30.0, 0.5);

    public ReachModule() { super("Reach", Category.COMBAT); }

    private void apply(double e, double b) {
        setAttr(mc.player, EntityAttributes.ENTITY_INTERACTION_RANGE, e);
        setAttr(mc.player, EntityAttributes.BLOCK_INTERACTION_RANGE, b);
        onServer(sp -> {
            setAttr(sp, EntityAttributes.ENTITY_INTERACTION_RANGE, e);
            setAttr(sp, EntityAttributes.BLOCK_INTERACTION_RANGE, b);
        });
    }
    @Override protected void onEnable() { apply(entity.get(), block.get()); }
    @Override protected void onDisable() { apply(3.0, 4.5); }
    @Override protected void onSettingChanged(Setting s) { if (isEnabled()) apply(entity.get(), block.get()); }
}
