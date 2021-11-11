package construct.base;

import java.util.Objects;

public class Info {
    protected String name;
    protected String note;

    public Info(String naziv, String napomena) {
        this.name = naziv;
        this.note = napomena;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Info info = (Info) o;
        return Objects.equals(name, info.name) && Objects.equals(note, info.note);
    }

    @Override
    public String toString() {
        return "Info{" +
                "name='" + name + '\'' +
                ", note='" + note + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public String getNote() {
        return note;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, note);
    }
}
