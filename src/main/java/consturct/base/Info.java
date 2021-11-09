package consturct.base;

import java.util.Objects;

public class Info {
    protected String naziv;
    protected String napomena;

    public Info(String naziv, String napomena) {
        this.naziv = naziv;
        this.napomena = napomena;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Info info = (Info) o;
        return Objects.equals(naziv, info.naziv) && Objects.equals(napomena, info.napomena);
    }

    @Override
    public String toString() {
        return "Info{" +
                "naziv='" + naziv + '\'' +
                ", napomena='" + napomena + '\'' +
                '}';
    }

    public String getNaziv() {
        return naziv;
    }

    public String getNapomena() {
        return napomena;
    }

    @Override
    public int hashCode() {
        return Objects.hash(naziv, napomena);
    }
}
