void main() {
    try (var writer = new FileWriter("fruits.txt"))  {
        var fruits = "Apples 🍎 \nMangoes 🥭\nBananas 🍌Apples 🍎 \nMangoes 🥭\nBananas 🍌";
        int c = 0;
        var strb = new StringBuilder();
        while (c < 10) {
            var uuid = UUID.randomUUID().toString();
            strb.append(System.lineSeparator()); // \n
            strb.append(uuid);
            c += 1;
        }
        writer.append(fruits);
        writer.append(strb.toString());
    } catch (IOException error) {
        System.err.println(error);
    }
}