package com.example.bookStorApi.domain;

import com.example.bookStorApi.constants.Category;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity(name = "book")

public class Book {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private long id;
    private String title;
    private String author;
    private int totalCount;
    private int price;
    private int sold;
    private Category category;
    private String gptRecommend;
    private String description;

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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Category getCategory() {
        return category;
    }
    public void setCategory(Category category){
        this.category = category;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGptRecommend() {
        return gptRecommend;
    }

    public void setGptRecommend(String gptRecommend) {
        this.gptRecommend = gptRecommend;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id == book.id && totalCount == book.totalCount &&
                price == book.price && sold == book.sold &&
                Objects.equals(title, book.title) && Objects.equals(author, book.author) &&
                category == book.category && Objects.equals(gptRecommend, book.gptRecommend) &&
                Objects.equals(description, book.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, author, totalCount, price, sold, category, gptRecommend, description);
    }
}
