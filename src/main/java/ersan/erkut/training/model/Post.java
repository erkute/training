package ersan.erkut.training.model;

public record Post (String userId, int id, String title, String body) {
    @Override
    public String toString()
    {
        return "Post{" +
            "userId='" + userId + '\'' +
            ", id=" + id +
            ", title='" + title + '\'' +
            ", body='" + body + '\'' +
            '}';
    }
}
