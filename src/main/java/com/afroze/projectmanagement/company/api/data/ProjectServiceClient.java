package com.afroze.projectmanagement.company.api.data;

import com.afroze.projectmanagement.company.api.ui.model.HttpResponseModel;
import com.afroze.projectmanagement.company.api.ui.model.ProjectSummaryResponseModel;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
@FeignClient(name="pm-project-ws")
public interface ProjectServiceClient {
    @GetMapping("/company/{id}/projects")
    @Retry(name="pm-project-ws")
    @CircuitBreaker(name="pm-project-ws", fallbackMethod = "getProjectsByCompanyIdFallback")
    HttpResponseModel<List<ProjectSummaryResponseModel>> getProjectsByCompanyId(@PathVariable long id);

    @DeleteMapping("/company/{id}/projects")
    @Retry(name="pm-project-ws")
    @CircuitBreaker(name="pm-project-ws", fallbackMethod = "deleteProjectsByCompanyIdFallback")
    void deleteProjectsByCompanyId(@PathVariable long id);

    default HttpResponseModel<List<ProjectSummaryResponseModel>> getProjectsByCompanyIdFallback(
            @PathVariable long id,
            Exception e) {
        return HttpResponseModel.success(new ArrayList<>());
    }

    default void deleteProjectsByCompanyIdFallback(@PathVariable long id, Exception e) {
        // returns void because obviously
    }
}

