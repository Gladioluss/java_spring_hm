package com.example.MyBookShopApp.data;

import javax.persistence.*;

@Entity
@Table(name = "black_list_tokens")
public class BlackListTokenEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "black_list_token")
    private String blackToken;

    public String getBlackToken() {
        return blackToken;
    }

    public void setBlackToken(String blackToken) {
        this.blackToken = blackToken;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
