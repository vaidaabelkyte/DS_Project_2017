package ie.gmit.sw.entity;

import java.io.Serializable;

public class Entity implements Serializable {

    private static int id;

    private String world;

    public Entity() {
        id++;
    }

    public Entity(String world) {
        id++;
        this.world = world;
    }

    public String getWorld() {
        return world;
    }

    public void setWorld(String world) {
        this.world = world;
    }

    public int getId() {
        return id;
    }

}
