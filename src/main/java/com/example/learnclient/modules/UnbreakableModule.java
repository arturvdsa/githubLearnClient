package com.example.learnclient.modules;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;

public class UnbreakableModule extends Module {
    public UnbreakableModule() { super("Unbreakable", Category.PLAYER); }

    @Override public void onTick() {
        onServer(sp -> {
            for (EquipmentSlot slot : EquipmentSlot.values()) {
                ItemStack s = sp.getEquippedStack(slot);
                if (!s.isEmpty() && s.isDamageable()) s.setDamage(0);
            }
        });
    }
}
