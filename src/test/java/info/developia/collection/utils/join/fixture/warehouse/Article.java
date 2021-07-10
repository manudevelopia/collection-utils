package info.developia.collection.utils.join.fixture.warehouse;

public class Article {
    private long id;
    private String name;

    public Article(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
