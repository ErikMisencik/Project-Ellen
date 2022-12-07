package sk.tuke.kpi.oop.game.items;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.ActorContainer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Backpack implements ActorContainer<Collectible> {

    private final int capacity;
    private final String name;
    private List<Collectible> inBackpack = new ArrayList<>();
    private int polk;


    public Backpack(String name, int capacity){
        this.name = name;
        this.capacity = capacity;
    }

    @Override
    public @NotNull List<Collectible> getContent() {
        return List.copyOf(inBackpack);
    }

    @Override
    public int getCapacity() {
        return capacity;
    }

    @Override
    public int getSize() {
        return inBackpack.size();
    }

    @Override
    public @NotNull String getName() {
        return name;
    }

    @Override
    public void add(@NotNull Collectible actor) {
        if(inBackpack.size() < getCapacity()){
            inBackpack.add(actor);
            polk = polk +1;
        }
        else{
            throw new IllegalStateException(getName()+" is full ");
        }
    }

    @Override
    public void remove(@NotNull Collectible actor) {
        if (inBackpack !=null) {
            polk = polk +1;
            inBackpack.remove(actor);
        }
    }

    @Nullable
    @Override
    public Collectible peek() {
        if (getSize()>0) {
            polk = polk +1;
            return inBackpack.get(getSize()-1);
        }
        else {
            return null;
        }
    }

    @Override
    public void shift() {
        Collections.rotate(inBackpack, 1);
    }

    @NotNull
    @Override
    public Iterator<Collectible> iterator() {
        return inBackpack.iterator();
    }
}
