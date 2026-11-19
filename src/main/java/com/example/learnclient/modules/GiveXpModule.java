package com.example.learnclient.modules;

public class GiveXpModule extends Module {
    private final Setting levels = number("Levels", 30, 5, 1000, 5);

    public GiveXpModule() { super("Give XP", Category.MISC); }

    @Override protected void onEnable() {
        int n = levels.getInt();
        onServer(sp -> sp.addExperienceLevels(n));
        setEnabled(false);
    }
}
