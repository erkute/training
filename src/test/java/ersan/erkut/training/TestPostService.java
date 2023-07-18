package ersan.erkut.training;

import ersan.erkut.training.api.*;
import ersan.erkut.training.model.*;
import ersan.erkut.training.storage.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.mockito.*;
import org.mockito.junit.jupiter.*;

import java.io.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TestPostService
{
    @Mock
    private ApiAdapter apiAdapter;

    @Mock
    private Storage<Post, Integer> storage;

    private PostService postService;

    @BeforeEach
    public void setup()
    {
        postService = new PostService(apiAdapter, storage);
    }

    @Test
    void testWritePost() throws IOException
    {
        Post post = new Post("userid", 1, "title", "body");

        // method under test
        postService.savePost(post);

        verify(storage).write(post);
        verify(apiAdapter, never()).getPosts();
    }

    @Test
    void testWritePostWithException() throws IOException
    {
        doThrow(IOException.class).when(storage).write(any());
        Post post = new Post("userid", 1, "title", "body");

        // method under test
        Exception exception = assertThrows(RuntimeException.class, () -> postService.savePost(post));

        assertTrue(exception.getMessage().contains("failed to save post"));
        assertTrue(exception.getCause() instanceof IOException);
        verify(storage).write(post);
        verify(apiAdapter, never()).getPosts();
    }

    @Test
    void testGetPost() throws IOException
    {
        Post post = new Post("userid", 1, "title", "body");
        when(storage.read(1)).thenReturn(Optional.of(post));
        when(storage.read(2)).thenReturn(Optional.empty());

        // method under test
        assertEquals(Optional.of(post), postService.getPost(1));
        assertEquals(Optional.empty(), postService.getPost(2));

        verify(apiAdapter, never()).getPosts();
    }

    @Test
    void testGetPostWithException() throws IOException
    {
        when(storage.read(1)).thenThrow(IOException.class);

        // method under test
        Exception exception = assertThrows(RuntimeException.class, () -> postService.getPost(1));

        assertTrue(exception.getMessage().contains("failed to read post with id: 1"));
        assertTrue(exception.getCause() instanceof IOException);
        verify(apiAdapter, never()).getPosts();
    }
}
