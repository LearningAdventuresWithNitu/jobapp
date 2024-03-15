package com.learningadventures.jobapp.job.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.learningadventures.jobapp.job.Job;
import com.learningadventures.jobapp.job.JobRepository;
import com.learningadventures.jobapp.job.JobService;

@Service
public class JobServiceImpl implements JobService {
    private JobRepository jobRepository;
    
    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    private Long nextId = 1L;

    @Override
    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    @Override
    public void createJob(Job job) {
        job.setId(nextId++);
        jobRepository.save(job);
    }

    @Override
    public Job getJobById(Long id) {
        return jobRepository.findById(id).orElse(null);
    }

    @Override
    public boolean deleteJobById(Long id) {
        try {
            jobRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updateJob(Long id, Job updatedJob) {
        Optional<Job> jobOptional = jobRepository.findById(id);

        if(jobOptional.isPresent()) {
            Job job = jobOptional.get();
            job.setTitle(updatedJob.getTitle());
            job.setDescription(updatedJob.getDescription());
            job.setMinSalary(updatedJob.getMinSalary());
            job.setMaxSalary(updatedJob.getMaxSalary());
            job.setLocation(updatedJob.getLocation());
    
            jobRepository.save(job);
            return true;
        }
        return false;
    }

}