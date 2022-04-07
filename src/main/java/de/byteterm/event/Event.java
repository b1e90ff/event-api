package de.byteterm.event;

import de.byteterm.event.listener.Listener;
import org.jetbrains.annotations.NotNull;

/**
 * This class represents an event. To use it to create an event,
 * you must extend this class to your desired event class and add
 * your desired objects such as PlayerObject. This event can then be processed
 * by the registered listeners. To trigger an event you need to run
 * the {@link EventAPI#callEvent(Event)} method and pass the required objects
 * through the constructor.
 *
 * @see Listener
 * @see EventAPI#callEvent(Event)
 *
 * @since 1.0
 * @author Niklas Tat
 */
public abstract class Event {

    /**
     * This variable stored the event name.
     */
    private String name;

    /**
     * This method is used to get the identifier of
     * an event, which is normally the {@link Class#getSimpleName() classname}.
     *
     * @return The identifier from the event
     */
    @NotNull
    public String getEventName() {
        if (name == null)
            name = getClass().getSimpleName();

        return name;
    }
}
