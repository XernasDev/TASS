package dev.xernas.tass.physics;

import dev.xernas.hydrogen.task.Task;

import java.util.ArrayList;
import java.util.List;

public class PhysicsTask extends Task {

    private final List<Body> bodies;

    public PhysicsTask(List<Body> bodies) {
        this.bodies = bodies;
    }

    @Override
    public float getTimer() {
        return 1/500f;
    }

    @Override
    public void update(float dt) {
        for (Body body : new ArrayList<>(bodies)) body.updatePosition(dt);
    }

}

