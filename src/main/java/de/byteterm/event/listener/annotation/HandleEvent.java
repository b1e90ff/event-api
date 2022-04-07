package de.byteterm.event.listener.annotation;

import de.byteterm.event.Event;
import de.byteterm.event.EventAPI;
import de.byteterm.event.listener.Listener;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation is written over methods
 * that 1. are in a class that has implemented
 * {@link Listener listener} and 2. the method is passed an event
 * and should act an {@link Event event}. Without this annotation over a method a
 * registered {@link Listener listener} can't trade an {@link Event event}
 *
 * @see EventAPI#callEvent(Event)
 * @see Listener
 * @see Event
 *
 * @since 1.0
 * @author Niklas Tat
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface HandleEvent {
}
