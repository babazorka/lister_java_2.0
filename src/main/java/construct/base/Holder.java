package construct.base;

import java.util.ArrayList;
import java.util.List;

public class Holder<T> {
    protected List<T> list = new ArrayList<>();

    public void add(T t){
        list.add(t);
    }

    public List list(){
        return list;
    }

}
