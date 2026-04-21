package dev.xernas.tass.physics;

import dev.xernas.hydrogen.task.Task;
import org.joml.Vector3f;

import java.util.List;

public class GravityTask extends Task {

    private final List<Body> bodies;
    private final float gravityConstant;

    private final Vector3f dir = new Vector3f();
    private final Vector3f normalized = new Vector3f();
    private final Vector3f forceVector = new Vector3f();

    public GravityTask(float gravityConstant, List<Body> bodies) {
        this.bodies = bodies;
        this.gravityConstant = gravityConstant;
    }


    public void computeGravitation(Body first, Body other) {
        dir.set(new Vector3f(other.getPosition()).sub(first.getPosition()));
        float length = dir.length();
        dir.normalize(normalized);

        // Newton Universal Gravity
        float softeningFactor = (first.getRadius() + other.getRadius()) / 2f;
        float force = ((first.getMass() * other.getMass()) / (length * length + softeningFactor * softeningFactor)) * gravityConstant;

        // acceleration = force/mass
        normalized.mul(force / first.getMass(), forceVector); // Put the force in the forceVector (combine direction and force)
        first.accelerate(forceVector);
        normalized.mul(force / first.getMass(), forceVector); // Put the force in the forceVector (combine direction and force)
        first.accelerate(new Vector3f(normalized).mul(force / first.getMass()));
        other.accelerate(new Vector3f(normalized).mul(-force / other.getMass()));
    }

    @Override
    public float getTimer() {
        return 1/500f;
    }

    @Override
    public void update(float dt) {
        for (int i = 0; i < bodies.size(); i++) {
            for (int j = i + 1; j < bodies.size(); j++) {
                Body first = bodies.get(i);
                Body other = bodies.get(j);
                computeGravitation(first, other);
            }
        }
    }
}
