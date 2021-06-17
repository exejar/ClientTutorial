package me.exejar.clienttutorial.hooks;

import java.lang.reflect.Method;

public class Listener {
    final Object parent;
    final Method method;

    public Listener(final Object parent, final Method method) {
        this.parent = parent;
        if (!method.isAccessible())
            method.setAccessible(true);

        this.method = method;
    }
}
