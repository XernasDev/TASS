package dev.xernas.tass.gameplay;

import dev.xernas.hydrogen.ecs.Actor;
import dev.xernas.hydrogen.ecs.SceneManager;
import dev.xernas.hydrogen.ecs.module.Module;
import dev.xernas.hydrogen.rendering.Renderer;
import dev.xernas.photon.api.window.Window;
import dev.xernas.photon.api.window.input.Input;
import dev.xernas.photon.api.window.input.Key;
import dev.xernas.tass.Game;
import dev.xernas.tass.gameplay.bodies.AstralBody;
import dev.xernas.tass.gameplay.bodies.BodyType;
import dev.xernas.tass.physics.Body;
import org.joml.Vector3f;

import java.util.List;

public class BodyInstantiator extends Module {

    private final List<Body> bodies;

    public BodyInstantiator(List<Body> bodies) {
        this.bodies = bodies;
    }

    @Override
    public void onStart(Actor actor, Window window, Renderer renderer) {

    }

    @Override
    public void onInput(Input input) {
        if (input.hasPressed(Key.MOUSE_LEFT)) {
            AstralBody body = new AstralBody(BodyType.STAR, 100f, new Vector3f(10, 10, 10), new Vector3f(10f, 20f, 10f));
            SceneManager.getCurrentScene().instantiate(body);
            bodies.add(body.getBody());
        }
    }
}
