package com.example.learnclient.modules;

/** Одноразовая кнопка: выполняет действие и сама выключается. */
public class ActionModule extends Module {
    private final Runnable action;

    public ActionModule(String name, Category category, Runnable action) {
        super(name, category);
        this.action = action;
    }

    @Override protected void onEnable() {
        try { action.run(); } finally { setEnabled(false); }
    }
}
