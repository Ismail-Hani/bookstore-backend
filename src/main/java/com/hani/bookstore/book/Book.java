package com.hani.bookstore.book;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.time.LocalDate;

@Entity
@Table(name = "books")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 300)
    private String title;

    @Column(length = 300)
    private String subtitle;

    @Column(length = 13, unique = true)
    private String isbn13;

    @Column(length = 10)
    private String isbn10;

    @Lob
    private String description;

    @Column(name = "price_cents", nullable = false)
    private Integer priceCents;

    @Column(length = 3, nullable = false)
    private String currency;

    @Column(nullable = false)
    private Integer stock;

    @Column(name = "published_at")
    private LocalDate publishedAt;

    @Column(name = "language_code", length = 8)
    private String languageCode;

    @Column(length = 200)
    private String publisher;

    @Column(name = "page_count")
    private Integer pageCount;

    @Column(nullable = false, length = 32)
    private String format;

    @Lob
    @Column(name = "cover_image_url")
    private String coverImageUrl;

    @Column(nullable = false, length = 32)
    private String status;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @Column(name = "deleted_at")
    private Instant deletedAt;

    @PrePersist
    void onCreate() {
        var now = Instant.now();
        this.createdAt = now;
        this.updatedAt = now;
    }

    @PreUpdate
    void onUpdate() {
        this.updatedAt = Instant.now();
    }

    @Column(nullable = false, length = 200)
    private String author;

}
