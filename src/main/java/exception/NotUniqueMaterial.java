package exception;

import imlemented.Material;

public class NotUniqueMaterial extends Throwable {
    protected Material material;

    public NotUniqueMaterial(Material material) {
        this.material = material;
    }

    public Material getMaterial() {
        return material;
    }
}
