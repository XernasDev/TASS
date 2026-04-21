package dev.xernas.tass;

import dev.xernas.hydrogen.Application;
import dev.xernas.hydrogen.asset.AssetManager;
import dev.xernas.hydrogen.ecs.Actor;
import dev.xernas.hydrogen.ecs.Scene;
import dev.xernas.hydrogen.ecs.SceneManager;
import dev.xernas.hydrogen.ecs.module.CameraController;
import dev.xernas.hydrogen.ecs.module.LightSource;
import dev.xernas.hydrogen.ecs.module.RenderingModule;
import dev.xernas.hydrogen.rendering.material.ColorMaterial;
import dev.xernas.photon.Library;
import dev.xernas.photon.api.Transform;
import dev.xernas.photon.api.window.Window;
import dev.xernas.photon.utils.Models;
import dev.xernas.tass.gameplay.BodyInstantiator;
import dev.xernas.tass.gameplay.bodies.AstralBody;
import dev.xernas.tass.gameplay.bodies.BodyType;
import dev.xernas.tass.physics.Body;
import dev.xernas.tass.physics.GravityTask;
import dev.xernas.tass.physics.PhysicsTask;
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
        return new AssetManager(this.getClass().getClassLoader(), "shaders", "textures");
    }

    @Override
    public void onStartup() {
        Scene mainScene = SceneManager.newScene("MainScene");
        mainScene.set3D(true);

        List<Body> allBodies = new ArrayList<>();

        AstralBody blackHole = new AstralBody(BodyType.BLACK_HOLE, 10000f, new Vector3f(), new Vector3f());
        allBodies.add(blackHole.getBody());

        mainScene.newActor(blackHole);

        mainScene.newActor(new Actor("Camera", new Transform.CameraTransform(), new CameraController(1f)))
                .newActor(new Actor("SceneManager", new SceneModule(allBodies), new BodyInstantiator(allBodies)));



        // Gravity Stuff
        PhysicsTask pt = new PhysicsTask(allBodies);
        GravityTask gravityTask = new GravityTask(0.5f, allBodies);

        getTaskManager().newTask(pt);
        getTaskManager().newTask(gravityTask);
    }

}
