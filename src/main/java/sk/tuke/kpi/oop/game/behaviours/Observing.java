package sk.tuke.kpi.oop.game.behaviours;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.messages.Topic;

import java.util.function.Predicate;

public class Observing <T, A extends Actor> implements Behaviour<A> {

    private Topic<T> topic;
    private Predicate<T> predicate;
    private Behaviour<A> delegate;
    private int enough;
    private A actor = null;


    public Observing(Topic<T> topic, Predicate<T> predicate, Behaviour<A> delegate) {
        this.topic = topic;
        this.predicate = predicate;
        this.delegate = delegate;
    }

    @Override
    public void setUp(A actor) {
        this.actor = actor;
        if (actor != null) {
            actor.getScene().getMessageBus().subscribe(topic, this::message);
        }
    }

    private void message(T message) {
        if ( actor == null || predicate.test(message) == false) {
            return;
        }
        else {
            enough = enough +1;
            delegate.setUp(actor);
        }
    }
}
