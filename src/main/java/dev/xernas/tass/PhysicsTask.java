package dev.xernas.tass;

import dev.xernas.hydrogen.task.Task;
import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PhysicsTask extends Task {

    private final List<Body> bodies = new ArrayList<>();
    private final float gravityConstant;

    public PhysicsTask(float gravityConstant, List<Body> bodies) {
        this.bodies.addAll(bodies);
        this.gravityConstant = gravityConstant;
    }

    @Override
    public float getTimer() {
        return 1/500f;
    }

    @Override
    public void update(float dt) {
        for (Body body : bodies) {
            for (Body other : bodies) {
                if (body.equals(other)) continue;
                computeGravitation(body, other);
            }
        }
        for (Body body : bodies) body.updatePosition(dt);
    }

    public void computeGravitation(Body first, Body other) {
        Vector3f dir = new Vector3f(other.getPosition()).sub(first.getPosition());
        float length = dir.length();
        Vector3f normalized = dir.normalize();

        // Newton Universal Gravity
        float force = (float) ((first.getMass() * other.getMass()) / (Math.pow(length, 2) + (first.getRadius() + other.getRadius()))) * gravityConstant;

        // acceleration = force/mass
        first.accelerate(normalized.mul(force / first.getMass()));
    }

    public List<Body> getBodies() {
        return bodies;
    }
}

