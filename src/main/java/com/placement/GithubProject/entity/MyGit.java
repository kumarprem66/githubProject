package com.placement.GithubProject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name="git_data",uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
public class MyGit {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String html_url;
    private String description;
    private String created_at;
    private int open_issues;
    private int watchers;

    @ManyToOne
    @JoinColumn(name = "ownerId")
    private RepoOwner owner;

}
