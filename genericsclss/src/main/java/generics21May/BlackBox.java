package generics21May;
class Main {
    class IceCream<T> {
        private T flavor;
        public void setFlavor(T flavor) {
            this.flavor = flavor;
        }
        public T getFlavor() {
            return flavor;
        }
    }
    class Freezer {
        public void addIceCreame(IceCream<? extends Number> custom) {
            var mult = custom.flavor.intValue() * 100;
            IO.println("flavor is mult " + mult);
        }
    }
    void main(){
        var freezer = new Freezer();
        var strwIcecream = new IceCream<Integer>();
        strwIcecream.setFlavor(45);
        freezer.addIceCreame(strwIcecream);
    }

}


