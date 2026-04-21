package dev.xernas.tass.gameplay.bodies;

import dev.xernas.hydrogen.ecs.Actor;
import dev.xernas.hydrogen.ecs.Scene;
import dev.xernas.hydrogen.ecs.module.RenderingModule;
import dev.xernas.hydrogen.rendering.material.ColorMaterial;
import dev.xernas.photon.api.Transform;
import dev.xernas.photon.utils.Models;
import dev.xernas.tass.physics.Body;
import org.joml.Vector3f;

import java.awt.*;

public class AstralBody extends Actor {

    private final Body body;

    public AstralBody(BodyType type, float mass, Vector3f defaultPosition, Vector3f defaultVelocity) {
        super(new Transform(defaultPosition));

        body = new Body(mass, mass / type.getMassToRadiusRatio());
        RenderingModule renderingModule = new RenderingModule("default", Models.createCube(), new ColorMaterial(type.getMainColor()));

        body.accelerate(defaultVelocity.mul(100f));

        newModules(body, renderingModule);
    }

    public Body getBody() {
        return body;
    }

}
