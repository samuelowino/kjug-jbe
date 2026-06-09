void main() {
    // Core Operations on ArrayList
    // set | add
    List<String> colors = new ArrayList<>();
    colors.add(0,"black");
    colors.add("blue"); // 0
    colors.add("yellow");
    colors.add("orange");
    colors.add("purple");

    colors.set(0,"maroon"); // replacement operation
    colors.set(2,"no color");
    colors.set(3,null);
    colors.set(4,null);

    colors.add(2,"blue");
    colors.add(3,"green");
    colors.add(4,"pink");


    IO.println("all colors " + colors);
}