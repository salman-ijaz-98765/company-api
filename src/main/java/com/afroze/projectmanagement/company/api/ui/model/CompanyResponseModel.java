package com.afroze.projectmanagement.company.api.ui.model;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import java.util.List;

@SuppressFBWarnings({"EI_EXPOSE_REP", "EI_EXPOSE_REP2"})
public class CompanyResponseModel {
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

    @SuppressWarnings("unused")
    public String getTags() {
        return tags;
    }

    @SuppressWarnings("unused")
    public void setTags(String tags) {
        this.tags = tags;
    }

    @SuppressWarnings("unused")
    public List<ProjectSummaryResponseModel> getProjects() {
        return projects;
    }

    @SuppressWarnings("unused")
    public void setProjects(List<ProjectSummaryResponseModel> projects) {
        this.projects = projects;
    }
}
