package com.placement.GithubProject.entity;

import com.placement.GithubProject.entity.MyGit;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RepoOwner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String avatar_url;
    private String html_url;
    private String type;
    private String site_admin;

    @OneToMany(mappedBy = "owner",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Set<MyGit> repos;

}
