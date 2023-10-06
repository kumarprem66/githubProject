package com.placement.GithubProject.service;

import com.placement.GithubProject.entity.RepoOwner;
import com.placement.GithubProject.exception.OwnerNotFoundException;
import com.placement.GithubProject.repository.RepoOwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RepoOwnerImplementation implements RepoOwnerOps{

    @Autowired
    private RepoOwnerRepository ownerRepository;

    @Override
    public RepoOwner getRepoOwner(Long id) {

        Optional<RepoOwner> owner = ownerRepository.findById(id);
        if (owner.isPresent()) {

            return owner.get();
        }
        throw new OwnerNotFoundException("Owner not found with the given id "+id);

    }

    @Override
    public RepoOwner saveRepoOwner(RepoOwner owner) {

       return ownerRepository.save(owner);
    }


}
