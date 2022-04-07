package de.byteterm.event.listener;

import de.byteterm.event.Event;
import de.byteterm.event.EventAPI;
import de.byteterm.event.listener.annotation.HandleEvent;

/**
 * To be able to react to a triggered event,
 * this class must be implemented at your desired 
 * class. In this class you can then create 
 * one or more methods that pass the desired event. So 
 * that the method can be executed properly, the method 
 * must be marked with the annotation {@linkplain HandleEvent @HandleEvent}. Last but not least,
 * this class is registered via the method 
 * {@link EventAPI#registerListener(Listener)}. Without this the listener cannot
 * be called. A listener can also be unregistered again by executing 
 * {@link EventAPI#unregisterListener(Listener)}.
 * If in a {@link Listener listener} class several methods have the annotation and
 * act different or same {@link Event events}, this class must be registered only once
 *
 * @see EventAPI#callEvent(Event)
 * @see Event
 * @see HandleEvent
 * @see EventAPI#unregisterListener(Listener)
 * @see EventAPI#registerListener(Listener)
 *
 * @author Niklas Tat
 * @since 1.0
 */
public interface Listener {
}
