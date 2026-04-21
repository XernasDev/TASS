package dev.xernas.tass.gameplay.bodies;

import java.awt.*;

public enum BodyType {

    ASTEROID(0.5f, Color.DARK_GRAY),
    MOON(1f, Color.GRAY),
    DWARF_PLANET(1.5f, Color.GREEN), // PLACEHOLDER FOR BROWN
    PLANET(2f, Color.BLUE),
    STAR(20f, Color.YELLOW),
    GIANT_STAR(15f, Color.RED),
    NEUTRON_STAR(100f, Color.CYAN),
    BLACK_HOLE(2000f, Color.BLACK);

    private final float massToRadiusRatio; // X times mass for 1x radius
    private final Color mainColor;

    BodyType(float massToRadiusRatio, Color mainColor) {
        this.massToRadiusRatio = massToRadiusRatio;
        this.mainColor = mainColor;
    }

    public float getMassToRadiusRatio() {
        return massToRadiusRatio;
    }

    public Color getMainColor() {
        return mainColor;
    }
}
