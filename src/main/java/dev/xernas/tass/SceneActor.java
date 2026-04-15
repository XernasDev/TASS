package dev.xernas.tass;

import dev.xernas.hydrogen.ecs.Actor;
import dev.xernas.hydrogen.ecs.Scene;
import dev.xernas.hydrogen.ecs.SceneManager;
import dev.xernas.hydrogen.ecs.module.Module;
import dev.xernas.hydrogen.ecs.module.RenderingModule;
import dev.xernas.photon.api.window.Window;
import dev.xernas.photon.api.window.input.Input;
import dev.xernas.photon.api.window.input.Key;

public class SceneActor extends Module {

    private Window window;

    @Override
    public void onStart(Actor actor, Window window) {
        this.window = window;
    }

    @Override
    public void onUpdate() {
        window.setTitle(window.getDefaultTitle() + " - FPS: " + Game.getFPS());
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
