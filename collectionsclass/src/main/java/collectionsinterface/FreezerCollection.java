package collectionsinterface;
import java.util.Collection;
import java.util.Iterator;
public class FreezerCollection implements Collection<Object> {
    private final Flavor[] flavors;
    public FreezerCollection(Flavor[] flavors) {
        this.flavors = flavors;
    }
    @Override
    public int size() {
        return flavors.length;
    }
    @Override
    public boolean isEmpty() {
        return flavors.length < 1;
    }
    @Override
    public boolean contains(Object element) {
        if (!(element instanceof Flavor flavor)) {
            System.err.println("cast failed");
           return false;
        }
        var isContained = false;
        for (Flavor candidate : flavors) { // iteration
            if (candidate.name().contentEquals(flavor.name())) {
                isContained = true;
                break;
            }
        }
        return isContained;
    }
    @Override
    public Iterator<Object> iterator() {
        throw new UnsupportedOperationException();
    }
    @Override
    public Object[] toArray() {
        return flavors;
    }
    @Override
    public boolean add(Object o) {
        throw new UnsupportedOperationException();
    }
    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }
    @Override
    public boolean addAll(Collection c) {
        throw new UnsupportedOperationException();
    }
    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }
    @Override
    public boolean retainAll(Collection otherFlavors) {
        throw new UnsupportedOperationException();
    }
    @Override
    public boolean removeAll(Collection otherFlavors) {
        throw new UnsupportedOperationException();
    }
    @Override
    public boolean containsAll(Collection otherFlavors) {
        throw new UnsupportedOperationException();
    }
    @Override
    public Object[] toArray(Object[] otherFlavors) {
        throw new UnsupportedOperationException();
    }
}
