package com.example.bookStorApi.dto;

import com.example.bookStorApi.constants.Category;

import java.util.Objects;

public class BookDTO {
    private long id;
    private String title;
    private String author;
    private int price;
    private int totalCount;
    private int sold;
    private Category category;
    private String gptRecommend;
    private String description;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getSold() {
        return sold;
    }

    public void setSold(int sold) {
        this.sold = sold;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getGptRecommend() {
        return gptRecommend;
    }

    public void setGptRecommend(String gptRecommend) {
        this.gptRecommend = gptRecommend;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        BookDTO bookDTO = (BookDTO) o;
        return id == bookDTO.id && price == bookDTO.price && totalCount == bookDTO.totalCount && sold == bookDTO.sold && Objects.equals(title, bookDTO.title) && Objects.equals(author, bookDTO.author) && category == bookDTO.category && Objects.equals(gptRecommend, bookDTO.gptRecommend) && Objects.equals(description, bookDTO.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, author, price, totalCount, sold, category, gptRecommend, description);
    }

    public boolean objectIsEmpty(){
        return (this.id == 0 && this.title == null && this.author == null);
    }
}
