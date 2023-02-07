package com.afroze.projectmanagement.company.api.data;

import com.afroze.projectmanagement.company.api.ui.model.HttpResponseModel;
import com.afroze.projectmanagement.company.api.ui.model.ProjectSummaryResponseModel;

import java.util.ArrayList;
import java.util.List;

public class ProjectServiceClientFallback implements ProjectServiceClient {
    @Override
    public HttpResponseModel<List<ProjectSummaryResponseModel>> getProjectsByCompanyId(long id) {
        return HttpResponseModel.success(new ArrayList<>());
    }

    @Override
    public void deleteProjectsByCompanyId(long companyId) {
    }
}
