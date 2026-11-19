package com.example.learnclient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.lwjgl.glfw.GLFW;

import com.example.learnclient.modules.*;
import com.example.learnclient.modules.Module;
import com.example.learnclient.modules.Module.Category;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.util.math.Vec3d;

public class ModuleManager {
    private final List<Module> modules = new ArrayList<>();

    private void add(Module m) { modules.add(m); }

    public void init() {
        // ---- Combat
        add(new KillAuraModule());
        add(new MultiAuraModule());
        add(new ReachModule());
        add(new StrengthModule());

        // ---- Movement
        add(new SprintModule());
        add(new FlyModule());
        add(new SpeedModule());
        add(new BunnyHopModule());
        add(new StepModule());
        add(new HighJumpModule());
        add(new NoFallModule());
        add(new MoonGravityModule());
        add(new SpiderModule());
        add(new WaterWalkModule());
        add(new EffectModule("Slow Fall", Category.MOVEMENT, StatusEffects.SLOW_FALLING, 0));
        add(new EffectModule("Swim Fast", Category.MOVEMENT, StatusEffects.DOLPHINS_GRACE, 0));
        add(new ActionModule("Dash", Category.MOVEMENT, () -> {
            var p = MinecraftClient.getInstance().player;
            if (p != null) p.setVelocity(p.getRotationVec(1.0f).multiply(3.0));
        }));
        add(new ActionModule("Launch", Category.MOVEMENT, () -> {
            var p = MinecraftClient.getInstance().player;
            if (p != null) p.setVelocity(new Vec3d(0, 3.0, 0));
        }));

        // ---- Render
        add(new FullbrightModule());
        add(new EspModule());
        add(new XRayModule());

        // ---- Player
        add(new AutoRespawnModule());
        add(new GodModeModule());
        add(new CreativeModule());
        add(new NoClipModule());
        add(new FastBreakModule());
        add(new UnbreakableModule());
        add(new MegaHealthModule());
        add(new GiantModule());
        add(new InvisibleModule());
        add(new EffectModule("Regen", Category.PLAYER, StatusEffects.REGENERATION, 4));
        add(new EffectModule("Tank", Category.PLAYER, StatusEffects.RESISTANCE, 4));
        add(new EffectModule("No Fire", Category.PLAYER, StatusEffects.FIRE_RESISTANCE, 0));
        add(new EffectModule("Aqua Lung", Category.PLAYER, StatusEffects.WATER_BREATHING, 0));
        add(new EffectModule("No Hunger", Category.PLAYER, StatusEffects.SATURATION, 0));
        add(new EffectModule("Lucky", Category.PLAYER, StatusEffects.LUCK, 9));

        // ---- Misc
        add(new DeathCoordsModule());
        add(new FreeLootModule());
        add(new GiveXpModule());
        add(new OreFinderModule());
        add(new BlinkTpModule());
        add(new GodGearModule());
        add(new BodyguardsModule());
        add(new NukeMobsModule());
        add(new AlwaysDayModule());
        add(new CommandModule("Always Night", Category.MISC, new String[0], new String[0],
                new String[] { "time set night" }));
        add(new CommandModule("Peaceful", Category.MISC, new String[] { "difficulty peaceful" },
                new String[] { "difficulty normal" }, new String[0]));
        add(new CommandModule("Keep Inventory", Category.MISC, new String[] { "gamerule keepInventory true" },
                new String[] { "gamerule keepInventory false" }, new String[0]));
        add(new ActionModule("Starter Kit", Category.MISC, () -> {
            String n = Module.playerName();
            for (String s : new String[] { "golden_carrot 64", "torch 64", "oak_planks 64", "cobblestone 64",
                    "ender_pearl 16", "water_bucket 1", "lava_bucket 1", "tnt 32", "bed 1" })
                Module.runCommand("give " + n + " " + s);
        }));
        // ---- новые функции v1.7
        add(new EffectModule("Jump Boost", Category.MOVEMENT, StatusEffects.JUMP_BOOST, 3));
        add(new EffectModule("Speed Boost", Category.MOVEMENT, StatusEffects.SPEED, 2));
        add(new ActionModule("Heal All", Category.PLAYER, () -> Module.runOnServer(sp -> {
            sp.setHealth(sp.getMaxHealth());
            sp.getHungerManager().setFoodLevel(20);
            sp.getHungerManager().setSaturationLevel(20f);
        })));
        add(new ActionModule("Clear Inventory", Category.MISC, () -> Module.runCommand("clear " + Module.playerName())));
        add(new ActionModule("Set Day", Category.MISC, () -> Module.runCommand("time set day")));
        add(new ActionModule("Clear Weather", Category.MISC, () -> Module.runCommand("weather clear")));
    }

    public List<Module> getModules() { return modules; }

    public List<Module> getModules(Category c) {
        List<Module> out = new ArrayList<>();
        for (Module m : modules) if (m.getCategory() == c) out.add(m);
        return out;
    }

    /** Найти модуль по имени: без регистра и без пробелов ("godmode", "esp", "fullbright"). */
    public Module find(String query) {
        String q = norm(query);
        if (q.isEmpty()) return null;
        for (Module m : modules) if (norm(m.getName()).equals(q)) return m;
        for (Module m : modules) if (norm(m.getName()).startsWith(q)) return m;
        return null;
    }

    private static String norm(String s) { return s.toLowerCase().replaceAll("[\\s_\\-]", ""); }

    private final Map<Integer, Boolean> down = new HashMap<>();

    /** Опрос биндов. allowToggle = false, пока открыт экран (чат, GUI). */
    public void pollBinds(long window, boolean allowToggle) {
        Map<Integer, Boolean> cur = new HashMap<>();
        for (Module m : modules) {
            int k = m.getBind();
            if (k >= 0) cur.computeIfAbsent(k, kk -> GLFW.glfwGetKey(window, kk) == GLFW.GLFW_PRESS);
        }
        if (allowToggle) {
            for (Module m : modules) {
                int k = m.getBind();
                if (k >= 0 && cur.get(k) && !down.getOrDefault(k, false)) m.toggle();
            }
        }
        down.clear();
        down.putAll(cur);
    }

    public void tick() {
        for (Module m : modules) if (m.isEnabled()) m.onTick();
    }
}
