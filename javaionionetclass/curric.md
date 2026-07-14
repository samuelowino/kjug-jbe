# Java I/O & Networking - 3-Level Exhaustive API Reference

---

## Level Structure

| Level | Focus            | Package                          | Model                        |
|-------|------------------|----------------------------------|------------------------------|
| **1** | Blocking I/O     | `java.io`                        | Thread-blocking streams      |
| **2** | Non-Blocking I/O | `java.nio`                       | Channels, Buffers, Selectors |
| **3** | Networking       | `java.net` + `java.nio.channels` | Sockets, Protocols, Async    |

---

# LEVEL 1: BLOCKING I/O (java.io)
**Characteristics:** Thread blocks until I/O completes | Simple but not scalable | 130+ classes/interfaces

---

## 1.1 Base Abstract Classes

| API            | Purpose                                                                            |
|----------------|------------------------------------------------------------------------------------|
| `InputStream`  | Base for all byte input (extends `Closeable`, `AutoCloseable`)                     |
| `OutputStream` | Base for all byte output (extends `Closeable`, `AutoCloseable`)                    |
| `Reader`       | Base for all character input (extends `Readable`, `Closeable`)                     |
| `Writer`       | Base for all character output (extends `Closeable`, `Appendable`, `AutoCloseable`) |

---

## 1.2 File Streams (Byte & Character)

**Byte Streams:**

| API                | Purpose                                  |
|--------------------|------------------------------------------|
| `FileInputStream`  | Reads bytes from file                    |
| `FileOutputStream` | Writes bytes to file                     |
| `FileReader`       | Reads chars from file (default encoding) |
| `FileWriter`       | Writes chars to file (default encoding)  |

---

## 1.3 Memory Streams

**Byte:**

| API                       | Purpose                              |
|---------------------------|--------------------------------------|
| `ByteArrayInputStream`    | Reads from byte[] in memory          |
| `ByteArrayOutputStream`   | Writes to byte[] in memory           |
| `StringBufferInputStream` | Reads from StringBuffer (deprecated) |

**Character:**

| API               | Purpose                     |
|-------------------|-----------------------------|
| `CharArrayReader` | Reads from char[] in memory |
| `CharArrayWriter` | Writes to char[] in memory  |
| `StringReader`    | Reads from String           |
| `StringWriter`    | Writes to StringBuffer      |

---

## 1.4 Buffered Streams (Performance)

| API                    | Purpose                                  |
|------------------------|------------------------------------------|
| `BufferedInputStream`  | Buffered byte input (default 8192 bytes) |
| `BufferedOutputStream` | Buffered byte output                     |
| `BufferedReader`       | Buffered char input + `readLine()`       |
| `BufferedWriter`       | Buffered char output + `newLine()`       |

---

## 1.5 Filter Streams (Decorators)

**Byte:**

| API                     | Purpose                         | 
|-------------------------|---------------------------------|
| `FilterInputStream`     | Base for byte input decorators  |
| `FilterOutputStream`    | Base for byte output decorators |
| `PushbackInputStream`   | Unread bytes (lookahead)        |
| `LineNumberInputStream` | Line tracking (deprecated)      |
| `SequenceInputStream`   | Concatenate multiple streams    |

**Character:**

| API                | Purpose                                  |
|--------------------|------------------------------------------|
| `FilterReader`     | Base for char input decorators           |
| `FilterWriter`     | Base for char output decorators          |
| `PushbackReader`   | Unread chars (lookahead)                 |
| `LineNumberReader` | Line tracking (BufferedReader extension) |

---

## 1.6 Data & Object Streams

**Data Streams (Primitives):**

| API                | Purpose                                          |
|--------------------|--------------------------------------------------|
| `DataInput`        | Interface for primitive input                    |
| `DataOutput`       | Interface for primitive output                   |
| `DataInputStream`  | Read primitives (`readInt()`, `readDouble()`)    |
| `DataOutputStream` | Write primitives (`writeInt()`, `writeDouble()`) |

**Object Streams (Serialization):**

| API                  | Purpose                                   |
|----------------------|-------------------------------------------|
| `ObjectInput`        | Interface for object input                |
| `ObjectOutput`       | Interface for object output               |
| `ObjectInputStream`  | Deserialize Java objects                  |
| `ObjectOutputStream` | Serialize Java objects                    |
| `ObjectStreamClass`  | Serialization descriptor                  |
| `ObjectStreamField`  | Field descriptor                          |
| `Serializable`       | Marker interface for serializable classes |
| `Externalizable`     | Custom serialization (full control)       |

---

## 1.7 Pipe Streams (Thread Communication)

| API                 | Purpose                |
|---------------------|------------------------|
| `PipedInputStream`  | Read from pipe (bytes) |
| `PipedOutputStream` | Write to pipe (bytes)  |
| `PipedReader`       | Read from pipe (chars) |
| `PipedWriter`       | Write to pipe (chars)  |

---

## 1.8 Console & System I/O

| API          | Purpose                                  |
|--------------|------------------------------------------|
| `System.in`  | Standard input (`InputStream`)           |
| `System.out` | Standard output (`PrintStream`)          |
| `System.err` | Standard error (`PrintStream`)           |
| `Console`    | Secure console I/O with password masking |

---

## 1.9 Formatted I/O

| API               | Purpose                                    |
|-------------------|--------------------------------------------|
| `PrintStream`     | Formatted byte output (`printf`, `format`) |
| `PrintWriter`     | Formatted char output (`printf`, `format`) |
| `Formatter`       | Formatting engine (`%d`, `%s`, `%f`)       |
| `Scanner`         | Tokenize + parse structured text           |
| `StreamTokenizer` | Tokenize character streams (legacy)        |
| `StringTokenizer` | Simple tokenizer (legacy)                  |

---

## 1.10 Random Access & Specialized

| API                | Purpose                                           |
|--------------------|---------------------------------------------------|
| `RandomAccessFile` | Seekable file I/O (`seek()`, `read()`, `write()`) |
| `File`             | Legacy file metadata (use `Path` in NIO)          |
| `FileDescriptor`   | OS file descriptor handle                         |
| `FileFilter`       | Filter files by attributes                        |
| `FilenameFilter`   | Filter files by name                              |

---

## 1.11 Compression (Blocking)

| API                    | Purpose                        |
|------------------------|--------------------------------|
| `GZIPInputStream`      | Decompress GZIP data           |
| `GZIPOutputStream`     | Compress GZIP data             |
| `ZipInputStream`       | Read ZIP archives              |
| `ZipOutputStream`      | Write ZIP archives             |
| `ZipEntry`             | ZIP entry metadata             |
| `ZipFile`              | Random access ZIP              |
| `JarInputStream`       | Read JAR archives              |
| `JarOutputStream`      | Write JAR archives             |
| `JarEntry`             | JAR entry metadata             |
| `DeflaterInputStream`  | Compress (Deflate)             |
| `InflaterInputStream`  | Decompress (Inflate)           |
| `DeflaterOutputStream` | Compress to stream             |
| `InflaterOutputStream` | Decompress from stream         |
| `CheckedInputStream`   | Compute checksum while reading |
| `CheckedOutputStream`  | Compute checksum while writing |

---

## 1.12 Character Encoding

| API                  | Purpose                             |
|----------------------|-------------------------------------|
| `InputStreamReader`  | Bridge: bytes → chars               |
| `OutputStreamWriter` | Bridge: chars → bytes               |
| `Charset`            | Character set abstraction           |
| `StandardCharsets`   | UTF-8, UTF-16, ISO-8859-1 constants |
| `CharsetDecoder`     | Decodes bytes to chars              |
| `CharsetEncoder`     | Encodes chars to bytes              |

---

## 1.13 Exceptions (Blocking I/O)

| Exception                      | Purpose                          |
|--------------------------------|----------------------------------|
| `IOException`                  | Base for I/O exceptions          |
| `FileNotFoundException`        | File doesn't exist               |
| `EOFException`                 | End of file reached unexpectedly |
| `StreamCorruptedException`     | Stream corrupted                 |
| `InvalidClassException`        | Serialization mismatch           |
| `NotSerializableException`     | Object not serializable          |
| `UTFDataFormatException`       | Malformed UTF-8 data             |
| `UnsupportedEncodingException` | Charset not supported            |
| `CharConversionException`      | Character conversion error       |

---

## 1.14 Utility Methods

| Method             | Purpose                                |
|--------------------|----------------------------------------|
| `close()`          | Close resource (in Try-With-Resources) |
| `flush()`          | Force write buffered data              |
| `available()`      | Number of bytes available              |
| `skip(n)`          | Skip n bytes/chars                     |
| `mark()`/`reset()` | Mark and reset position                |
| `markSupported()`  | Check mark/reset support               |

---

# LEVEL 2: NON-BLOCKING I/O (java.nio)
**Characteristics:** Non-blocking | Channels & Buffers | Selectors for multiplexing | 70+ classes/interfaces

---

## 2.1 Base Components

**Buffer System:**

| API                | Type           | Purpose                    |
|--------------------|----------------|----------------------------|
| `Buffer`           | Abstract Class | Base for all buffers       |
| `ByteBuffer`       | Class          | Byte buffer (heap/direct)  |
| `CharBuffer`       | Class          | Character buffer           |
| `DoubleBuffer`     | Class          | Double buffer              |
| `FloatBuffer`      | Class          | Float buffer               |
| `IntBuffer`        | Class          | Integer buffer             |
| `LongBuffer`       | Class          | Long buffer                |
| `ShortBuffer`      | Class          | Short buffer               |
| `MappedByteBuffer` | Class          | Memory-mapped file buffer  |
| `ByteOrder`        | Enum           | BIG_ENDIAN / LITTLE_ENDIAN |

**Buffer Operations:**

| Method                     | Purpose                                     |
|----------------------------|---------------------------------------------|
| `allocate(capacity)`       | Create new buffer                           |
| `allocateDirect(capacity)` | Direct OS memory (faster I/O)               |
| `wrap(array)`              | Create buffer from existing array           |
| `put()`                    | Write to buffer                             |
| `get()`                    | Read from buffer                            |
| `flip()`                   | Ready for read (limit=position, position=0) |
| `rewind()`                 | Reset position to 0                         |
| `compact()`                | Shift unread data to beginning              |
| `clear()`                  | Reset for writing                           |
| `remaining()`              | Bytes left to read                          |
| `hasRemaining()`           | Any bytes left?                             |
| `slice()`                  | Create view buffer                          |
| `duplicate()`              | Share buffer content                        |

---

## 2.2 Channels

**Base Channel Interfaces:**

| API                     | Purpose                     |
|-------------------------|-----------------------------|
| `Channel`               | Base interface              |
| `Closeable`             | Close resource              |
| `InterruptibleChannel`  | Channel can be interrupted  |
| `ReadableByteChannel`   | Read bytes                  |
| `WritableByteChannel`   | Write bytes                 |
| `ByteChannel`           | Read + Write                |
| `ScatteringByteChannel` | Read into multiple buffers  |
| `GatheringByteChannel`  | Write from multiple buffers |

**File Channels:**

| API                   | Purpose                        |
|-----------------------|--------------------------------|
| `FileChannel`         | Channel for file I/O           |
| `FileLock`            | File lock abstraction          |
| `SeekableByteChannel` | Channel with position          |
| `FileChannel.MapMode` | READ_ONLY, READ_WRITE, PRIVATE |

**Network Channels:**

| API                   | Purpose                                 |
|-----------------------|-----------------------------------------|
| `SocketChannel`       | Non-blocking TCP socket                 |
| `ServerSocketChannel` | Non-blocking TCP server                 |
| `DatagramChannel`     | Non-blocking UDP socket                 |
| `SelectableChannel`   | Channel can be registered with Selector |

**Pipe Channels:**
| API | Purpose |
|-----|---------|
| `Pipe` | Factory for pipe channels |
| `Pipe.SourceChannel` | Pipe input |
| `Pipe.SinkChannel` | Pipe output |

---

## 2.3 Selectors (Multiplexing)

| API                           | Purpose                                                  |
|-------------------------------|----------------------------------------------------------|
| `Selector`                    | Multiplex multiple channels                              |
| `SelectionKey`                | Registration token                                       |
| `SelectableChannel`           | Channel that supports Selector                           |
| `SelectorProvider`            | Factory for Selector                                     |
| `SelectionKey` - Interest Ops | OP_READ, OP_WRITE, OP_CONNECT, OP_ACCEPT                 |
| `SelectableChannel` methods   | `register(Selector, int)`                                |
| `Selector` methods            | `select()`, `selectNow()`, `selectedKeys()`              |
| `SelectionKey` methods        | `channel()`, `selector()`, `interestOps()`, `readyOps()` |

---

## 2.4 File Operations (NIO.2)

**Path & Filesystem:**

| API               | Purpose                   |
|-------------------|---------------------------|
| `Path`            | File path abstraction     |
| `Paths`           | Factory for Path          |
| `FileSystem`      | Filesystem abstraction    |
| `FileSystems`     | Factory for FileSystem    |
| `FileStore`       | Storage device info       |
| `WatchService`    | Directory monitoring      |
| `WatchKey`        | Watch registration        |
| `WatchEvent`      | File change event         |
| `DirectoryStream` | Iterate directory entries |

**File Attributes:**

| API                            | Purpose                |
|--------------------------------|------------------------|
| `BasicFileAttributes`          | Basic metadata         |
| `DosFileAttributes`            | DOS/Windows attributes |
| `PosixFileAttributes`          | POSIX attributes       |
| `AclFileAttributeView`         | ACL permissions        |
| `FileOwnerAttributeView`       | File owner             |
| `PosixFileAttributeView`       | POSIX permissions      |
| `UserDefinedFileAttributeView` | Custom metadata        |
| `FileAttribute`                | Generic attribute      |
| `FileTime`                     | Nanosecond timestamp   |
| `PosixFilePermissions`         | Unix permission utils  |
| `AclEntry`                     | ACL entry              |
| `UserPrincipal`                | User abstraction       |
| `GroupPrincipal`               | Group abstraction      |

**Watch Events:**

| Event                                  | Purpose           |
|----------------------------------------|-------------------|
| `StandardWatchEventKinds.ENTRY_CREATE` | File/dir created  |
| `StandardWatchEventKinds.ENTRY_DELETE` | File/dir deleted  |
| `StandardWatchEventKinds.ENTRY_MODIFY` | File/dir modified |
| `StandardWatchEventKinds.OVERFLOW`     | Events lost       |

---

## 2.5 Asynchronous Channels (NIO.2)

| API                               | Purpose                    |
|-----------------------------------|----------------------------|
| `AsynchronousFileChannel`         | Async file I/O             |
| `AsynchronousSocketChannel`       | Async TCP client           |
| `AsynchronousServerSocketChannel` | Async TCP server           |
| `AsynchronousDatagramChannel`     | Async UDP                  |
| `CompletionHandler<V,A>`          | Callback interface         |
| `Future`                          | Async result (with Future) |
| `PendingFuture`                   | Pending async operation    |

**CompletionHandler Methods:**

| Method                          | Purpose          |
|---------------------------------|------------------|
| `completed(result, attachment)` | Success callback |
| `failed(exception, attachment)` | Error callback   |

---

## 2.6 Locking & Access

| API                             | Purpose            |
|---------------------------------|--------------------|
| `FileLock`                      | File lock          |
| `FileLockInterruptionException` | Lock interrupted   |
| `FileNotFoundException`         | File not found     |
| `AccessDeniedException`         | Permission denied  |
| `NoSuchFileException`           | File doesn't exist |

---

# LEVEL 3: NETWORKING (java.net + java.nio.channels)
**Characteristics:** Network communication | TCP, UDP, HTTP | 90+ classes/interfaces

---

## 3.1 TCP Sockets (Blocking)

**Client:**

| API                 | Purpose                          |
|---------------------|----------------------------------|
| `Socket`            | TCP client socket                |
| `SocketImpl`        | Platform-specific implementation |
| `SocketFactory`     | Create Sockets with policy       |
| `SocketAddress`     | Socket address abstraction       |
| `InetSocketAddress` | IP + port                        |

**Server:**

| API                   | Purpose              |
|-----------------------|----------------------|
| `ServerSocket`        | TCP server listener  |
| `ServerSocketFactory` | ServerSocket factory |

**Options:**

| API                     | Purpose                         |
|-------------------------|---------------------------------|
| `StandardSocketOptions` | TCP_NODELAY, SO_KEEPALIVE, etc. |
| `SocketOption`          | Generic socket option           |

---

## 3.2 UDP Sockets

| API                  | Purpose                     |
|----------------------|-----------------------------|
| `DatagramSocket`     | UDP socket                  |
| `DatagramPacket`     | UDP packet (data + address) |
| `MulticastSocket`    | UDP multicast               |
| `DatagramSocketImpl` | Platform implementation     |

---

## 3.3 Addresses & Interfaces

**Addresses:**

| API                 | Purpose                      |
|---------------------|------------------------------|
| `InetAddress`       | IP address (v4/v6)           |
| `Inet4Address`      | IPv4 address                 |
| `Inet6Address`      | IPv6 address                 |
| `InetSocketAddress` | Address + port               |
| `NetworkInterface`  | NIC (Network Interface Card) |
| `InterfaceAddress`  | NIC address info             |
| `SocketAddress`     | Abstract socket address      |

---

## 3.4 Non-Blocking Network (NIO Channels)

**TCP Channels:**

| API                            | Purpose                          |
|--------------------------------|----------------------------------|
| `SocketChannel`                | Non-blocking TCP (with Selector) |
| `ServerSocketChannel`          | Non-blocking TCP server          |
| `SocketChannel`                | Blocking or non-blocking mode    |
| `ServerSocketChannel.bind()`   | Bind to port                     |
| `ServerSocketChannel.accept()` | Accept connection (non-blocking) |

**UDP Channel:**

| API               | Purpose          |
|-------------------|------------------|
| `DatagramChannel` | Non-blocking UDP |

**Async Network:**

| API                               | Purpose          |
|-----------------------------------|------------------|
| `AsynchronousSocketChannel`       | Async TCP client |
| `AsynchronousServerSocketChannel` | Async TCP server |
| `AsynchronousDatagramChannel`     | Async UDP        |

---

## 3.5 HTTP & URL Connections

**URLs:**

| API                     | Purpose                     |
|-------------------------|-----------------------------|
| `URL`                   | Uniform Resource Locator    |
| `URI`                   | Uniform Resource Identifier |
| `URLEncoder`            | Encode URL parameters       |
| `URLDecoder`            | Decode URL parameters       |
| `MalformedURLException` | Invalid URL                 |

**Connections:**

| API                  | Purpose                    |
|----------------------|----------------------------|
| `URLConnection`      | Generic URL connection     |
| `HttpURLConnection`  | HTTP/HTTPS connection      |
| `JarURLConnection`   | JAR file access            |
| `HttpsURLConnection` | HTTPS (SSL/TLS)            |
| `CacheRequest`       | Cache request abstraction  |
| `CacheResponse`      | Cache response abstraction |
| `ResponseCache`      | Caching policy             |

**HTTP Modern (Java 11+):**

| API               | Purpose                  |
|-------------------|--------------------------|
| `HttpClient`      | HTTP/2 client (Java 11+) |
| `HttpRequest`     | HTTP request builder     |
| `HttpResponse`    | HTTP response            |
| `HttpHeaders`     | HTTP headers             |
| `HttpBodyHandler` | Body processing          |

---

## 3.6 Proxies & Authentication

**Proxy:**

| API                             | Purpose                     |
|---------------------------------|-----------------------------|
| `Proxy`                         | Proxy server config         |
| `ProxySelector`                 | Proxy selection policy      |
| `Proxy.Type`                    | HTTP, SOCKS, DIRECT         |
| `ProxySelector` system property | `java.net.useSystemProxies` |

**Authentication:**

| API                                             | Purpose                  |
|-------------------------------------------------|--------------------------|
| `Authenticator`                                 | Authentication callback  |
| `PasswordAuthentication`                        | Credentials              |
| `Authenticator.setDefault()`                    | Set global authenticator |
| `Authenticator.requestPasswordAuthentication()` | Request credentials      |

**Cookies:**

| API             | Purpose              |
|-----------------|----------------------|
| `HttpCookie`    | HTTP cookie          |
| `CookieHandler` | Cookie handler       |
| `CookieManager` | Cookie store manager |
| `CookieStore`   | Cookie storage       |

---

## 3.7 SSL/TLS

| API                      | Purpose              |
|--------------------------|----------------------|
| `SSLSocket`              | SSL/TLS socket       |
| `SSLServerSocket`        | SSL/TLS server       |
| `SSLSocketFactory`       | SSL socket factory   |
| `SSLServerSocketFactory` | SSL server factory   |
| `SSLContext`             | SSL configuration    |
| `TrustManager`           | Certificate trust    |
| `KeyManager`             | Certificate identity |
| `HostnameVerifier`       | Hostname validation  |

---

## 3.8 Network Exceptions

| Exception                  | Purpose              |
|----------------------------|----------------------|
| `SocketException`          | Socket error         |
| `ConnectException`         | Connection refused   |
| `BindException`            | Port in use          |
| `NoRouteToHostException`   | No route             |
| `PortUnreachableException` | UDP port unreachable |
| `ProtocolException`        | Protocol error       |
| `SocketTimeoutException`   | Timeout              |
| `UnknownHostException`     | Host not found       |
| `UnknownServiceException`  | Service unknown      |
| `MalformedURLException`    | Invalid URL          |

---

## 3.9 URI/URL Comparison

| API | Purpose |
|-----|---------|
| `URI` | RFC 2396 URI (more strict) |
| `URL` | URI + ability to open connection |
| `URI` methods | `normalize()`, `resolve()`, `relativize()` |
| `URL` methods | `openConnection()`, `openStream()` |

---

## 3.10 HTTP Headers & Status Codes

**HTTP Headers:**
| API | Purpose |
|-----|---------|
| `HttpURLConnection` | `setRequestProperty()`, `getHeaderField()` |
| `URLConnection` | `addRequestProperty()`, `getHeaderFields()` |

**HTTP Methods:**
| Method | Purpose |
|--------|---------|
| `GET` | Read resource |
| `POST` | Create resource |
| `PUT` | Update resource |
| `DELETE` | Delete resource |
| `HEAD` | Headers only |
| `OPTIONS` | Supported methods |
| `PATCH` | Partial update |

**Status Codes:**
| Code | Meaning |
|------|---------|
| 200 | OK |
| 201 | Created |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 500 | Internal Server Error |

---

# 🗺️ API Quick Reference Map

| Level           | Blocking I/O (java.io)                                            | Non-Blocking (java.nio)                           | Networking (java.net)                |
|-----------------|-------------------------------------------------------------------|---------------------------------------------------|--------------------------------------|
| **Base**        | `InputStream`, `OutputStream`, `Reader`, `Writer`                 | `Buffer`, `ByteBuffer`, `Channel`                 | `Socket`, `ServerSocket`, `URL`      |
| **Files**       | `FileInputStream`, `FileOutputStream`, `FileReader`, `FileWriter` | `FileChannel`, `AsynchronousFileChannel`, `Files` | `URLConnection`, `HttpURLConnection` |
| **Memory**      | `ByteArrayInputStream`, `CharArrayReader`, `StringReader`         | `ByteBuffer`, `CharBuffer`                        | `InetAddress`                        |
| **Buffering**   | `BufferedInputStream`, `BufferedReader`                           | `ByteBuffer` direct allocation                    | `Socket.setBufferSize()`             |
| **Performance** | `Buffered` variants                                               | `MappedByteBuffer`, Direct Buffers                | `Selector` (NIO), connection pooling |
| **Threading**   | Blocking (thread per I/O)                                         | Non-blocking (Selector, async)                    | Blocking (thread per socket)         |
| **Scalability** | ❌ Limited (threads)                                               | ✅ Excellent (Selector)                            | ⚠️ Thread pool needed                |
| **Data Types**  | Primitives, Objects, Text                                         | Bytes only (buffers)                              | Bytes (streams)                      |
| **Protocols**   | File, Pipe                                                        | File, Pipe, Socket                                | TCP, UDP, HTTP, SSL                  |
| **Special**     | Serialization, GZIP, ZIP                                          | Memory-mapped, Locks, Watch                       | Authenticator, Proxy, Cookies        |
| **Modern**      | `Files` (NIO.2), `HttpClient`                                     | All NIO.2 APIs                                    | `HttpClient` (Java 11+)              |

---

# 📝 Integration Examples

| Scenario          | Blocking I/O                          | Non-Blocking                  | Networking                 |
|-------------------|---------------------------------------|-------------------------------|----------------------------|
| **Read file**     | `FileReader` + `BufferedReader`       | `Files.readString()`          | `URL.openStream()`         |
| **Write file**    | `FileWriter` + `BufferedWriter`       | `Files.writeString()`         | `HttpURLConnection` output |
| **Binary file**   | `FileInputStream` + `DataInputStream` | `FileChannel` + `ByteBuffer`  | `Socket.getOutputStream()` |
| **Large file**    | `BufferedInputStream`                 | `MappedByteBuffer`            | N/A                        |
| **Client-Server** | `Socket` + Thread                     | `SocketChannel` + `Selector`  | `ServerSocket` + Thread    |
| **HTTP Client**   | `HttpURLConnection` (blocking)        | `HttpClient` (async)          | `HttpClient` (Java 11+)    |
| **UDP**           | `DatagramSocket`                      | `DatagramChannel`             | `DatagramSocket`           |
| **Chat server**   | Thread per client (poor)              | Selector + Channel (scalable) | Both                       |

---

## 📊 API Count Summary

| Level                   | Count         | Key Packages                                                                |
|-------------------------|---------------|-----------------------------------------------------------------------------|
| **1: Blocking I/O**     | ~130          | `java.io`, `java.util.zip`, `java.security`, `javax.crypto`                 |
| **2: Non-Blocking I/O** | ~80           | `java.nio`, `java.nio.channels`, `java.nio.file`, `java.nio.file.attribute` |
| **3: Networking**       | ~90           | `java.net`, `java.nio.channels`, `java.net.http`                            |
| **Total**               | **~300 APIs** |                                                                             |


---