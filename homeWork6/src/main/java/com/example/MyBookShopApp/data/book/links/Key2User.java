package com.example.MyBookShopApp.data.book.links;
import lombok.Data;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class Key2User implements Serializable {
    private static final long serialVersionUID = 2651842678772425822L;
    @Column(name = "book_id")
    private int bookId;

    @Column(name = "user_id")
    private int userId;
}
