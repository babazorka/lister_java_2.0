package writer;

public class SpecificWrite {
    static public final String NO_TAPE = "";
    static public final String NUMBEROF = "numberOf";
    static public final String DESCRIPTION_1 = "Leva cifra je ukupna duzina secenja puta cena secenja.";
    static public final String SUM = "SUM";

    static public String floatToString(float value) {
        return String.valueOf(value).replace('.', ',');
    }

    public static void main(String[] args) {
        float koka = 1.f;
        String string = String.valueOf(koka);
        System.out.println(string);
        System.out.println(floatToString(koka));
    }
}
