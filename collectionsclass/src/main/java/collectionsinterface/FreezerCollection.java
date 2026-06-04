package collectionsinterface;
import java.util.Collection;
import java.util.Iterator;
public class FreezerCollection<T extends Flavor> implements Collection<T> {
    private final T[] elements; // fixed size
    public FreezerCollection(T[] elements) {
        this.elements = elements;
    }
    @Override
    public int size() {
        return elements.length;
    }
    @Override
    public boolean isEmpty() {
        return elements.length < 1;
    }
    @Override
    public boolean contains(Object element) {
        if (!(element instanceof Flavor flavor)) {
            System.err.println("cast failed");
           return false;
        }
        var isContained = false;
        for (T candidate : elements) { // iteration
            if (candidate.getName().contentEquals(flavor.getName())) {
                isContained = true;
                break;
            }
        }
        return isContained;
    }
    @Override
    public Iterator<T> iterator() {
        return new FreezerIterator<T>(elements);
    }
    @Override
    public T[] toArray() {
        return elements;
    }
    @Override
    public boolean add(T o) {
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
    public <T1> T1[] toArray(T1[] a) {
        throw new UnsupportedOperationException();
    }
}
