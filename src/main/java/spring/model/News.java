package spring.model;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "news")
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "text")
    private String text;

    @Column(name = "header_text")
    private String headerText;

    @Column(name = "likes")
    private Long like;

    @Column(name = "img_name")
    private String imgName;

    @Column(name = "img_path")
    private String imgPath;

    public News(String text, Long like, String imgPath, String imgName) {
        this.text = text;
        this.like = like;
        this.imgPath = imgPath;
        this.imgName = imgName;
    }

    public News(String text, Long like, String imgPath, String imgName, List<Categories> categories) {
        this.text = text;
        this.like = like;
        this.imgPath = imgPath;
        this.imgName = imgName;
        this.categories = categories;
    }

    public News(String text, Long like, String imgName) {
        this.text = text;
        this.like = like;
        this.imgName = imgName;
    }

    public News(String text, String headerText, Long like, String imgName, String imgPath) {
        this.text = text;
        this.headerText = headerText;
        this.like = like;
        this.imgName = imgName;
        this.imgPath = imgPath;
    }

    @ManyToMany(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinTable(name = "news_categories",
            joinColumns = @JoinColumn(name = "news_id"),
            inverseJoinColumns = @JoinColumn(name = "categories_id"))
    private List<Categories> categories;

    public News() {

    }

    public List<Categories> getCategories() {
        return categories;
    }

    public void setCategories(List<Categories> categories) {
        this.categories = categories;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public String getHeaderText() {
        return headerText;
    }

    public void setHeaderText(String headerText) {
        this.headerText = headerText;
    }
    public String getText() {
        return text;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }
    public void setText(String text) {
        this.text = text;
    }

    public Long getLike() {
        return like;
    }

    public void setLike(Long like) {
        this.like = like;
    }
}
