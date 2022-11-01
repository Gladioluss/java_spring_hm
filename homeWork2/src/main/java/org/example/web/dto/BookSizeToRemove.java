package org.example.web.dto;

import javax.validation.constraints.NotBlank;

public class BookSizeToRemove {
    @NotBlank
    private String size;

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
