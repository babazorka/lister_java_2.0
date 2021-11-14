package writer;

public enum OutHeader {
    ;
    static private final String TIMES = "*";
    static public final String PRICE = "price";
    static public final String PERIMETER = "perimeter";
    static public final String SURFACE = "surface";
    static public final String VOLUME = "volume";
    static public final String LenX = "lenX";
    static public final String LenY = "lenY";
    static public final String LenZ = "lenZ";
    static public final String NOTE = "note";
    static public final String NAME = "name";
    static public final String QUANTITY = "quantity";
    static public final String TEXTURE = "texture";
    static public final String KT = "kt";
    static public final String TAPE = "tape";
    static public final String PRICE_QUANTITY = PRICE + TIMES + QUANTITY;
    static public final String PRICE_SURFACE = PRICE + TIMES + SURFACE;
    static public final String PRICE_PERIMETER = PRICE + TIMES + PERIMETER;


}
