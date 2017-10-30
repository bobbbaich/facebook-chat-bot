package com.bobbbaich.fb.bot.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "facts")
public class Fact {
    private Long id;
    private String text;
    private User requestBy;
    private Set<Opinion> opinions;
    private Conclusion conclusion;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="request_by_fk")
    public User getRequestBy() {
        return requestBy;
    }

    public void setRequestBy(User requestBy) {
        this.requestBy = requestBy;
    }

    @OneToMany(mappedBy="relatedFact", fetch = FetchType.LAZY)
    public Set<Opinion> getOpinions() {
        return opinions;
    }

    public void setOpinions(Set<Opinion> opinions) {
        this.opinions = opinions;
    }

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "conclusion")
    public Conclusion getConclusion() {
        return conclusion;
    }

    public void setConclusion(Conclusion conclusion) {
        this.conclusion = conclusion;
    }
}
