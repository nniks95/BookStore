package com.nikola.spring.entities;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "books")
public class BookEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, length = 40)
    private String ISBN;
    @Column(length = 60, nullable = false, unique = true)
    private String title;
    @ManyToOne
    @JoinColumn(nullable = false, name = "author_id")
    private AuthorEntity author;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "genre_id")
    private GenreEntity genre;
    @Column(nullable = false, length = 40)
    private String language;
    @ManyToOne
    @JoinColumn(nullable = false, name = "publisher_id")
    private PublisherEntity publisher;
    private Byte available;
    @Column(nullable = false)
    private Double price;
    @OneToMany(fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "book")
    private List<ReviewEntity> reviews;
    @Column(nullable = false)
    private Integer numberOfPages;
    @ManyToMany
    @JoinTable(name = "book_award", joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "award_id"))
    private List<AwardEntity> awards;
    @ManyToMany
    @JoinTable(name = "book_wishlist", joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "wishlist_id"))
    private List<WishlistEntity> wishlists;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public AuthorEntity getAuthor() {
        return author;
    }

    public void setAuthor(AuthorEntity author) {
        this.author = author;
    }

    public GenreEntity getGenre() {
        return genre;
    }

    public void setGenre(GenreEntity genre) {
        this.genre = genre;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public PublisherEntity getPublisher() {
        return publisher;
    }

    public void setPublisher(PublisherEntity publisher) {
        this.publisher = publisher;
    }

    public Byte getAvailable() {
        return available;
    }

    public void setAvailable(Byte available) {
        this.available = available;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<ReviewEntity> getReviews() {
        return reviews;
    }

    public void setReviews(List<ReviewEntity> reviews) {
        this.reviews = reviews;
    }

    public Integer getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(Integer numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public List<AwardEntity> getAwards() {
        return awards;
    }

    public void setAwards(List<AwardEntity> awards) {
        this.awards = awards;
    }

    public List<WishlistEntity> getWishlists() {
        return wishlists;
    }

    public void setWishlists(List<WishlistEntity> wishlists) {
        this.wishlists = wishlists;
    }

    @Override
    public String toString() {
        return "BookEntity{" +
                "id=" + id +
                ", ISBN='" + ISBN + '\'' +
                ", title='" + title + '\'' +
                ", author=" + author +
                ", genre=" + genre +
                ", language='" + language + '\'' +
                ", publisher=" + publisher +
                ", available=" + available +
                ", price=" + price +
                ", numberOfPages=" + numberOfPages +
                '}';
    }
}
