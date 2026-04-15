package dev.xernas.tass;

import dev.xernas.hydrogen.Application;
import dev.xernas.hydrogen.asset.AssetManager;
import dev.xernas.hydrogen.ecs.Actor;
import dev.xernas.hydrogen.ecs.Scene;
import dev.xernas.hydrogen.ecs.SceneManager;
import dev.xernas.hydrogen.ecs.module.CameraController;
import dev.xernas.hydrogen.ecs.module.LightSource;
import dev.xernas.hydrogen.ecs.module.RenderingModule;
import dev.xernas.hydrogen.rendering.Renderer;
import dev.xernas.hydrogen.rendering.material.ColorMaterial;
import dev.xernas.photon.Library;
import dev.xernas.photon.api.IRenderer;
import dev.xernas.photon.api.Transform;
import dev.xernas.photon.api.framebuffer.IFramebuffer;
import dev.xernas.photon.api.model.IMesh;
import dev.xernas.photon.api.shader.IShader;
import dev.xernas.photon.api.texture.ITexture;
import dev.xernas.photon.api.window.Window;
import dev.xernas.photon.utils.Models;
import org.joml.Vector3f;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Game extends Application {
    @Override
    public Library getLibrary() {
        return Library.OPENGL_4_5;
    }

    @Override
    public String getName() {
        return "Totally Accurate Space Simulator";
    }

    @Override
    public String getVersion() {
        return "1.0.0";
    }

    @Override
    public boolean isDebug() {
        return true;
    }

    @Override
    public Window getWindow() {
        return new Window("TASS", 1280, 720);
    }

    @Override
    public AssetManager getAssetManager() {
        return new AssetManager(this.getClass().getClassLoader(), "shaders");
    }

    @Override
    public void onStartup() {
        Body sun = new Body(3000, 20);

        Scene mainScene = SceneManager.newScene("MainScene")
                .addActor(new Actor("Camera", new Transform.CameraTransform(), new CameraController(10f)))
                .addActor(new Actor("SceneManager", new SceneActor()));
        mainScene.set3D(true);

        List<Body> allBodies = new ArrayList<>();
        allBodies.add(sun);

        mainScene.addActor(new Actor(
                "Sun",
                new RenderingModule("default", Models.createCube(), new ColorMaterial(Color.YELLOW)),
                sun,
                new LightSource(1f))
        );

        for (int i = 0; i < 200; i++) {
            Body planet = new Body(20, 5);
            mainScene.addActor(new Actor(
                    new Transform(new Vector3f((float) (Math.random() * 500 - 500), (float) (Math.random() * 500 - 500), (float) (Math.random() * 500 - 500))),
                    new RenderingModule("lit", Models.createCube(), new ColorMaterial(Color.CYAN)),
                    planet
            ));
            allBodies.add(planet);
        }

        // Gravity Stuff
        PhysicsTask pt = new PhysicsTask(70f, allBodies);
        getTaskManager().newTask(pt);
    }

}
