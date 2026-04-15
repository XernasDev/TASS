package dev.xernas.tass;

import dev.xernas.hydrogen.ecs.Actor;
import dev.xernas.hydrogen.ecs.module.Module;
import dev.xernas.photon.api.Transform;
import dev.xernas.photon.api.window.Window;
import org.joml.Vector3f;

public class Body extends Module {

    private Transform transform;

    private Vector3f position = new Vector3f();
    private Vector3f oldPosition = new Vector3f();
    private final Vector3f acceleration = new Vector3f();
    private final float mass;
    private final float radius;

    public Body(float mass, float radius) {
        this.mass = mass;
        this.radius = radius;
    }

    @Override
    public void onStart(Actor actor, Window window) {
        this.transform = actor.getTransform();
        transform.scale(radius);
        this.position = new Vector3f(transform.getPosition());
        this.oldPosition = new Vector3f(transform.getPosition());
    }

    @Override
    public void onUpdate() {
        transform.setPosition(position);
    }

    public Vector3f getPosition() {
        return position;
    }

    // Verlet
    public void updatePosition(float dt) {
        Vector3f velocity = new Vector3f(position).sub(oldPosition);

        oldPosition.set(position);

        position.add(velocity.add(new Vector3f(acceleration).mul(dt * dt)));

        acceleration.zero();
    }

    public float getMass() {
        return mass;
    }

    public float getRadius() {
        return radius;
    }

    public void accelerate(Vector3f acceleration) {
        this.acceleration.add(acceleration);
    }
}
