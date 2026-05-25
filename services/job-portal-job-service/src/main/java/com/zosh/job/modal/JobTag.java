package com.zosh.job.modal;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "job_tags")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobTag {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(unique = true)
    private String slug;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime cratedAt;
}
