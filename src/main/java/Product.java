import java.time.Instant;

public class Product {
    private Integer id;
    private String name;
    private Instant creationDatetime;
    private Category category;

    public Product(Integer id, String name, Instant creationDatetime, Category category) {
        this.id = id;
        this.name = name;
        this.creationDatetime = creationDatetime;
        this.category = category;
    }

    public Product(Integer id, String name, Instant creationDatetime) {
        this.id = id;
        this.name = name;
        this.creationDatetime = creationDatetime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Instant getCreationDatetime() {
        return creationDatetime;
    }

    public void setCreationDatetime(Instant creationDatetime) {
        this.creationDatetime = creationDatetime;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", name='" + name + '\'' +
                ", creationDatetime=" + creationDatetime +
                ", category=" + category + "\n";
    }
}
