package ersan.erkut.training.storage;

import ersan.erkut.training.model.*;
import lombok.*;
import org.springframework.stereotype.*;

import java.util.*;

@Component
@RequiredArgsConstructor
public class Cache implements Storage<Post, Integer>
{
    private final Map<Integer, Post> cacheMap = new HashMap<>();

    @Override
    public void write(Post post)
    {
        cacheMap.put(post.id(), post);
    }

    @Override
    public Optional<Post> read(Integer key)
    {
        return Optional.of(cacheMap.get(key));
    }
}
