package ersan.erkut.training;

import ersan.erkut.training.api.*;
import ersan.erkut.training.model.*;
import ersan.erkut.training.storage.*;
import lombok.*;
import lombok.extern.slf4j.*;
import org.springframework.stereotype.*;

import java.io.*;
import java.util.*;

@Service

@Slf4j
@RequiredArgsConstructor
public class PostService
{
    private final ApiAdapter apiAdapter;

    private final Storage<Post, Integer> storage;

    public void savePost(Post post)
    {
        try
        {
            storage.write(post);
        }
        catch (IOException e)
        {
            throw new RuntimeException("failed to save post", e);
        }
    }

    public Optional<Post> getPost(int id) {
        try
        {
            return storage.read(id);
        }
        catch (IOException e)
        {
            throw new RuntimeException("failed to read post with id: " + id, e);
        }
    }

    public List<Post> getRandomPosts()
    {
        return apiAdapter.getPosts();
    }
}
