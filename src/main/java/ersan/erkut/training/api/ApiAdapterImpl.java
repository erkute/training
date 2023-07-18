package ersan.erkut.training.api;

import com.fasterxml.jackson.core.type.*;
import com.fasterxml.jackson.databind.*;
import ersan.erkut.training.model.*;
import org.springframework.stereotype.*;

import java.net.*;
import java.net.http.*;
import java.util.*;

@Component
public class ApiAdapterImpl implements ApiAdapter
{
    private static final String ENDPOINT = "https://jsonplaceholder.typicode.com/posts";

    private final ObjectMapper objectMapper;

    public ApiAdapterImpl()
    {
        this.objectMapper = new ObjectMapper();
    }

    public List<Post> getPosts()
    {
        try
        {
            HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(ENDPOINT))
                .GET()
                .build();

            HttpResponse<String> response = HttpClient.newBuilder()
                .build()
                .send(request, HttpResponse.BodyHandlers.ofString());

            return objectMapper.readValue(response.body(), new TypeReference<>()
            {
            });
        }
        catch (Exception e)
        {
            throw new RuntimeException("failed to get posts", e);
        }
    }
}
