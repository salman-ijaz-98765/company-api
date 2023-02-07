package com.afroze.projectmanagement.company.api.data;

import com.afroze.projectmanagement.company.api.ui.model.HttpResponseModel;
import com.afroze.projectmanagement.company.api.ui.model.ProjectSummaryResponseModel;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

import java.util.List;

@FeignClient(name="pm-project-ws")
public interface ProjectServiceClient {
    @GetMapping("/company/{id}/projects")
    @Retry(name="pm-project-ws")
    @CircuitBreaker(name="pm-project-ws", fallbackMethod = "getProjectsFallback")
    HttpResponseModel<List<ProjectSummaryResponseModel>> getProjectsByCompanyId(@PathVariable long id);

    @DeleteMapping("/company/{id}/projects")
    @Retry(name="pm-project-ws")
    @CircuitBreaker(name="pm-project-ws", fallbackMethod = "deleteProjectsByCompanyIdFallback")
    void deleteProjectsByCompanyId(@PathVariable long id);
}

