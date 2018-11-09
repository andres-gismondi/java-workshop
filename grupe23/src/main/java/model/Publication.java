package model;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="PUBLICATION")
public class Publication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private int id;
    @ManyToOne
    private User creator;
    @Column(name="TITLE")
    private String title;
    @Column(name="BODY")
    private String body;
    @Column(name="DATE")
    private Timestamp date;
    @Column(name="COMMENTS_ENABLE")
    private Boolean enableComments;
<<<<<<< HEAD
    @ManyToOne
    private Billboard billboard;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "publication",
            orphanRemoval = true
    )

=======
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.ALL}, fetch=FetchType.EAGER, orphanRemoval=true)
>>>>>>> 4df1f815278b389751c097b8baa1ae9d57d3e859
    private List<Commentary> commentaries = new ArrayList<>();


    public Publication() {
    }

    public void addComentary(Commentary commentary){
        this.getCommentaries().add(commentary);
    }
    public void removeComentary(Commentary commentary){
        this.getCommentaries().remove(commentary);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Boolean getEnableComments() {
        return enableComments;
    }

    public void setEnableComments(Boolean enableComments) {
        this.enableComments = enableComments;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }


    public List<Commentary> getCommentaries() {
        return commentaries;
    }

    public void setCommentaries(List<Commentary> commentaries) {
        this.commentaries = commentaries;
    }
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Billboard getBillboard() {
        return billboard;
    }

    public void setBillboard(Billboard billboard) {
        this.billboard = billboard;
    }
}
