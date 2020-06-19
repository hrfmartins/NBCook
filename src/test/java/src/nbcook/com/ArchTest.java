package src.nbcook.com;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {

        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("src.nbcook.com");

        noClasses()
            .that()
                .resideInAnyPackage("src.nbcook.com.service..")
            .or()
                .resideInAnyPackage("src.nbcook.com.repository..")
            .should().dependOnClassesThat()
                .resideInAnyPackage("..src.nbcook.com.web..")
        .because("Services and repositories should not depend on web layer")
        .check(importedClasses);
    }
}
