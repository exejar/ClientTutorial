package me.exejar.clienttutorial.hooks;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

public class EventBus {
    private Map<Class<?>, List<Listener>> listenerMap;

    public EventBus() { this.listenerMap = new HashMap<>(); }

    public void subscribe(Object object) {
        for (Method method : object.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(Subscribe.class)) {
                if (method.getParameterCount() == 1) {
                    List<Listener> list = this.listenerMap.computeIfAbsent(method.getParameterTypes()[0], obj -> new CopyOnWriteArrayList<>());
                    list.add(new Listener(object, method));
                }
            }
        }
    }

    public void unsubscribe(Object object) {
        for (List<Listener> list : this.listenerMap.values()) {
            list.removeIf(obj -> obj.parent == object);
        }
    }

    public Object callEvent(Object object) {
        List<Listener> list = this.listenerMap.get(object.getClass());
        if (list != null) {
            for (Listener listener : list) {
                Object parent = listener.parent;
                Method method = listener.method;
                try {
                    method.invoke(parent, object);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }

        return object;
    }

}
