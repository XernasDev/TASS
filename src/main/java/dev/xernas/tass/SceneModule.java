package dev.xernas.tass;

import dev.xernas.hydrogen.ecs.Actor;
import dev.xernas.hydrogen.ecs.Scene;
import dev.xernas.hydrogen.ecs.SceneManager;
import dev.xernas.hydrogen.ecs.module.Module;
import dev.xernas.hydrogen.ecs.module.RenderingModule;
import dev.xernas.hydrogen.rendering.Renderer;
import dev.xernas.photon.api.window.Window;
import dev.xernas.photon.api.window.input.Input;
import dev.xernas.photon.api.window.input.Key;
import dev.xernas.tass.physics.Body;

import java.util.List;

public class SceneModule extends Module {

    private Window window;

    private final List<Body> bodies;

    public SceneModule(List<Body> bodies) {
        this.bodies = bodies;
    }

    @Override
    public void onStart(Actor actor, Window window, Renderer renderer) {
        this.window = window;
    }

    @Override
    public void onUpdate() {
        window.setTitle(window.getDefaultTitle() + " - FPS: " + Game.getFPS() + " - Number Of Bodies : " + bodies.size());
    }

    @Override
    public void onInput(Input input) {
        if (input.hasPressed(Key.KEY_ENTER)) {
            System.out.println("Pressed enter");
            Scene scene = SceneManager.getCurrentScene();
            Actor selectedActor = scene.getActorsWithModule(RenderingModule.class).get(
                            (int) (Math.random() * (scene.getActorsWithModule(RenderingModule.class).size()))
                    );
            System.out.println("Selected Actor : " + selectedActor.getName());

            scene.getCameraActor().getTransform().setPosition(selectedActor.getTransform().getPosition());
        }

    }
}
