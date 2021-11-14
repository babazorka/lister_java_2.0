package writer;

public class SpecificWrite {
    static public final String NO_TAPE = "";
    static public final String NUMBEROF = "numberOf";
    static public final String DESCRIPTION_1 = "Leva cifra je ukupna duzina secenja puta cena secenja.";
    static public final String SUM = "SUM";
    static public final Character SEPARATOR_COMMA = ',';
    static public final Character SEPARATOR_SEMICOLON = ';';
    static private Character SEPARATOR = SEPARATOR_SEMICOLON;

    static public void SET_SEPARATOR(Character separator) {
        SEPARATOR = separator;
    }

    static public Character SEPARATOR() {
        return SEPARATOR;
    }

    static public String floatToString(float value) {
        if (SEPARATOR == ';')
            return String.format("%.2f", value);
        return String.format("%.2f", value).replace(',', '.');
    }

    static public Character FREE_CHARACTER() {
        if (SEPARATOR == SEPARATOR_SEMICOLON)
            return SEPARATOR_COMMA;
        return SEPARATOR_SEMICOLON;
    }

    public static void main(String[] args) {
        float koka = 1455.41654645f;
        System.out.println(floatToString(koka));
    }
}
