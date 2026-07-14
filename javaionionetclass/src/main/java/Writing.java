
void main() {
    var fileName = "fruits.txt";
    try (var fos = new FileOutputStream(fileName)){
        var fruits = "Apples 🍎 \nMangoes 🥭\nBananas 🍌Apples 🍎 \nMangoes 🥭\nBananas 🍌";
        byte[] allFruitsBytes = fruits.getBytes(StandardCharsets.UTF_8);
        fos.write(allFruitsBytes);
        System.out.println("bytes written " + allFruitsBytes.length);
        System.out.println("Done writing ✅");
    } catch (IOException error) {
        System.err.println(
                "failed to write to "
                        + fileName
                        + " file: cause "
                        + error.getLocalizedMessage());
    }
}