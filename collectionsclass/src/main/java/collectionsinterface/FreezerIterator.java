package collectionsinterface;
import java.util.Iterator;
public class FreezerIterator<R extends Flavor> implements Iterator<R> {
    private final R[] elements;
    private int index = 0;
    public FreezerIterator(R[] values) {
        this.elements = values;
    }
    @Override
    public boolean hasNext() {
        //  index 5 // 5 elements
        if (index >= elements.length) {
            return false;
        } else {
            return true;
        }
    }
    @Override
    public R next() {
        R element = elements[index];
        index += 1;
        return element;
    }
}
