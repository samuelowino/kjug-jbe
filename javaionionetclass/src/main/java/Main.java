void main() {
    // Java 7 : try-with-resources
    // close -> autocloseable
    // IOException
    //create the input stream
    var fileName = "important.txt";
    try (var fis = new FileInputStream(fileName)){
        // reading of the file
        // returns int: byte of data : -1 : EOF
        int nextBytesOrEOF;
        while ((nextBytesOrEOF = fis.read()) != -1) {
            var line = (char) nextBytesOrEOF;
            System.out.print(line);
        }
        //automagically -> Autocloseable -> try-with-resources
    } catch (IOException error) {
        IO.println("error! something went wrong: cause "
                + error.getLocalizedMessage());
    }
}