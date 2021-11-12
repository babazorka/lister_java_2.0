package construct.unique;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Set;

public class MultiHash<S, T> {
    private Hashtable<S, ArrayList<T>> mulitMap = new Hashtable<>();

    public void print() {
        for (var key : mulitMap.keySet()) {
            System.out.println(key);
            for (var t : mulitMap.get(key))
                System.out.println(t);
        }
    }

    public void add(S key, T value) throws NotUniqueException {
        if (mulitMap.containsKey(key)) {
            mulitMap.get(key).add(value);
            throw new NotUniqueException();
        }
        mulitMap.put(key, new ArrayList<T>());
        mulitMap.get(key).add(value);
    }

    public Collection<ArrayList<T>> values() {
        return mulitMap.values();
    }

    public Hashtable<S, ArrayList<T>> getMulitMap() {
        return mulitMap;
    }

    public Set<S> keySet() {
        return mulitMap.keySet();
    }

}
