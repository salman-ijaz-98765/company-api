package com.afroze.projectmanagement.company.api.dto;

import com.afroze.projectmanagement.company.api.ui.model.ProjectSummaryResponseModel;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import java.util.List;

@SuppressFBWarnings({"EI_EXPOSE_REP", "EI_EXPOSE_REP2"})
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

    @SuppressWarnings("unused")
    public List<ProjectSummaryResponseModel> getProjects() {
        return projects;
    }

    public void setProjects(List<ProjectSummaryResponseModel> projects) {
        this.projects = projects;
    }
}
