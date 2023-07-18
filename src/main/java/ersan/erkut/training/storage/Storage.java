package ersan.erkut.training.storage;

import java.io.*;
import java.util.*;

public interface Storage<T, K>
{
    void write(T type) throws IOException;

    Optional<T> read(K key) throws IOException;
}
