package com.afroze.projectmanagement.company.api.architecture;

import org.junit.jupiter.api.Test;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class ArchitectureTest {
    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
                .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
                .importPackages("com.afroze.projectmanagement.company.api");

        noClasses()
                .that()
                .resideInAnyPackage("com.afroze.projectmanagement.company.api.service..").or()
                .resideInAnyPackage("com.afroze.projectmanagement.company.api.repository..").or()
                .resideInAnyPackage("com.afroze.projectmanagement.company.api.dto..").or()
                .resideInAnyPackage("com.afroze.projectmanagement.company.api.exception..").or()
                .resideInAnyPackage("com.afroze.projectmanagement.company.api.data..")
                .should()
                .dependOnClassesThat()
                .resideInAnyPackage("..com.afroze.projectmanagement.company.api.ui.controller..")
                .because("Services and repositories should not depend on web layer")
                .check(importedClasses);
    }
}
