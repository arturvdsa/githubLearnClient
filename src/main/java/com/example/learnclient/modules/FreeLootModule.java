package com.example.learnclient.modules;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class FreeLootModule extends Module {
    public FreeLootModule() { super("Free Loot", Category.MISC); }

    @Override protected void onEnable() {
        onServer(sp -> {
            sp.giveItemStack(new ItemStack(Items.NETHERITE_INGOT, 64));
            sp.giveItemStack(new ItemStack(Items.DIAMOND, 64));
            sp.giveItemStack(new ItemStack(Items.ENCHANTED_GOLDEN_APPLE, 16));
            sp.giveItemStack(new ItemStack(Items.TOTEM_OF_UNDYING, 1));
        });
        setEnabled(false); // одноразовая кнопка
    }
}
