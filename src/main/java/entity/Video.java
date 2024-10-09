package entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "Videos")
@NamedQuery(name="Video.findAll", query="SELECT v FROM Video v")
public class Video implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "VideoId")
    private int videoId;

    @Column(name = "Active")
    private int active;

    @Column(name = "Description", columnDefinition = "nvarchar(1000) null")
    private String description;

    @Column(name = "Poster", columnDefinition = "nvarchar(500) null")
    private String poster;

    @NotEmpty(message = "Không đuược bỏ trống")
    @Column(name = "Title", columnDefinition = "nvarchar(255) null")
    private String title;

    @Column(name = "Views")
    private int views;

    //bi-directional many-to-one association to Category
    @ManyToOne
    @JoinColumn(name="CategoryId")

    private Category category;

    @Override
    public String toString() {
        return "Video{" +
                "videoId='" + videoId + '\'' +
                ", active=" + active +
                ", description='" + description + '\'' +
                ", poster='" + poster + '\'' +
                ", title='" + title + '\'' +
                ", views=" + views +
                ", category=" + category +
                '}';
    }
}
