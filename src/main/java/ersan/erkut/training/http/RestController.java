package ersan.erkut.training.http;

import ersan.erkut.training.*;
import ersan.erkut.training.model.*;
import lombok.*;
import org.apache.catalina.connector.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@org.springframework.web.bind.annotation.RestController
@RequiredArgsConstructor
public class RestController
{
    private final PostService postService;

    @GetMapping("/posts/{id}")
    ResponseEntity<Post> getPost(@PathVariable int id) {
        Optional<Post> postMaybe = postService.getPost(id);
        return postMaybe.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/posts")
    ResponseEntity<Response> addPost(@RequestBody Post post) {
        postService.savePost(post);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/posts/random")
    ResponseEntity<List<Post>> getRandomPosts() {
        List<Post> posts = postService.getRandomPosts();
        return ResponseEntity.ok(posts);
    }
}
