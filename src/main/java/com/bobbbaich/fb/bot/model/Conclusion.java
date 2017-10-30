package com.bobbbaich.fb.bot.model;

import javax.persistence.*;

@Entity
@Table(name = "conclusions")
public class Conclusion {
    private Long id;
    private Fact fact;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @OneToOne(mappedBy = "conclusion", fetch = FetchType.LAZY)
    public Fact getFact() {
        return fact;
    }

    public void setFact(Fact fact) {
        this.fact = fact;
    }
}
