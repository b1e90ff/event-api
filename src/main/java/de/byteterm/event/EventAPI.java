package de.byteterm.event;

import de.byteterm.event.listener.Listener;
import de.byteterm.event.listener.annotation.HandleEvent;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.LinkedHashSet;

/**
 * This is the Main from the Event-API. This class is used to handle
 * the {@link Event event} system. For this purpose, this class must be initialized
 * in the desired program, for example in the Main class, in order to
 * always receive the same instance. If you do not do this, the system
 * does not work, because the {@link Listener listeners} are not stored otherwise.
 * Over this class then with the {@link EventAPI#callEvent(Event)} method {@link Event Event} can be triggered.
 * All {@link Listener listeners} are then gone through and the correct traders for the {@link Event event}
 * then executed. However, in order for an {@link Event event} to be traded, {@link Listener listeners} must first
 * be registered with the {@link EventAPI#registerListener(Listener)} method. Multiple events can be handled
 * in one {@link Listener listener}. For this simply a method must be created, which gets the desired
 * {@link Event event} passed. Furthermore, the method must have the annotation {@link HandleEvent @HandleEvent}.
 *
 * @see HandleEvent
 * @see Listener
 * @see Event
 *
 * @since 1.0
 * @author Niklas Tat
 */
public class EventAPI {

    private static final Collection<Listener> listeners;

    static {
        listeners = new LinkedHashSet<>();
    }

    /**
     * With this method the created listeners can be registered
     * in the system so that they can be executed in case of
     * a triggered {@link Event event}. It is important that at least one method
     * in the {@link Listener listener} is passed the desired event and contains
     * the annotation {@link HandleEvent @HandleEvent.}
     *
     * @see EventAPI#callEvent(Event)
     * @see Event
     * @see HandleEvent
     * @see Listener
     *
     * @param listener The {@link Listener} you want to register
     */
    public static void registerListener(Listener listener) {
        if (listeners.contains(listener))
            return;
        listeners.add(listener);
    }

    /**
     * This method is used to disable registered {@link Listener listeners}.
     * For this, simply pass the desired class that implements
     * {@link Listener listeners}. If an {@link Event event} is triggered and the {@link Listener listener} trades
     * this {@link Event event}, the event will no longer be traded by this class.
     *
     * @see EventAPI#callEvent(Event)
     * @see Event
     * @see Listener
     * @see HandleEvent
     *
     * @param listener The {@link Listener} you want to unregister
     */
    public static void unregisterListener(Listener listener) {
        if (!listeners.contains(listener))
            return;
        listeners.remove(listener);
    }

    /**
     * With the help of this method a desired {@link Event event} is triggered.
     * That means that all registered {@link Listener listeners} are gone through,
     * and it is looked which method is responsible there
     * with the {@link HandleEvent @HandleEvent} annotation for the desired event.
     *
     * @see HandleEvent
     * @see Listener
     * @see Event
     * @see EventAPI#registerListener(Listener)
     *
     * @param event The {@link Event} you want to trigger
     */
    public static void callEvent(Event event) {
        for (Listener listener : listeners) {
            Class<? extends Listener> c = listener.getClass();
            final Method[] methods = c.getDeclaredMethods();

            for (Method method : methods) {
                try {
                    HandleEvent handleEvent = method.getAnnotation(HandleEvent.class);
                    if (handleEvent == null) continue;
                    if (method.getParameterTypes().length != 1
                            || !method.getParameterTypes()[0].isAssignableFrom(event.getClass())) continue;
                    method.invoke(listener, event);
                } catch (Exception ex) {
                    System.err.println("Uncaught exception while firing event:");
                    ex.printStackTrace();
                }
            }
        }
    }
}
