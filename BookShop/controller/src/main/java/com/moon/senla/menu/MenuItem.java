package com.moon.senla.menu;

import com.moon.senla.action.IAction;

public class MenuItem {

    private final String title;
    private final IAction action;
    private final Menu nextMenu;

    public MenuItem(String title, IAction action, Menu nextMenu) {
        this.title = title;
        this.action = action;
        this.nextMenu = nextMenu;
    }

    public void doAction() {
        action.execute();
    }

    public Menu getNextMenu() {
        return nextMenu;
    }

    @Override
    public String toString() {
        return "\n" + title;
    }
}
