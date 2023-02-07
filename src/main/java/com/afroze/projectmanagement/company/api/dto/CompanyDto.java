package com.afroze.projectmanagement.company.api.dto;

import com.afroze.projectmanagement.company.api.ui.model.ProjectSummaryResponseModel;

import java.util.List;

public class CompanyDto {
    private Long id;

    private String name;

    private String tags;

    private List<ProjectSummaryResponseModel> projects;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public List<ProjectSummaryResponseModel> getProjects() {
        return projects;
    }

    public void setProjects(List<ProjectSummaryResponseModel> projects) {
        this.projects = projects;
    }
}
