package writer;

public class SpecificWrite {
    static public final String NO_TAPE = "";
    static public final String NUMBEROF = "numberOf";
    static public final String DESCRIPTION_1 = "Leva cifra je ukupna duzina secenja puta cena secenja.";
    static public final String SUM = "SUM";
    static public final Character SEPARATOR = ';';

    static public String floatToString(float value) {
//        return String.valueOf(value).replace('.', ',');
        return String.format("%.2f",value);
    }

    public static void main(String[] args) {
        float koka = 1.f;
        System.out.println(floatToString(koka));
        }
}
