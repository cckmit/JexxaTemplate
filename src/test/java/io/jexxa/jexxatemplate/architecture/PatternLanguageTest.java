package io.jexxa.jexxatemplate.architecture;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import io.jexxa.addend.applicationcore.Aggregate;
import io.jexxa.addend.applicationcore.ApplicationService;
import io.jexxa.addend.applicationcore.BusinessException;
import io.jexxa.addend.applicationcore.DomainEvent;
import io.jexxa.addend.applicationcore.DomainProcessStep;
import io.jexxa.addend.applicationcore.DomainService;
import io.jexxa.addend.applicationcore.DomainWorkflow;
import io.jexxa.addend.applicationcore.InfrastructureService;
import io.jexxa.addend.applicationcore.Repository;
import io.jexxa.addend.applicationcore.ValueObject;
import io.jexxa.jexxatemplate.JexxaTemplate;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static io.jexxa.jexxatemplate.architecture.PackageName.AGGREGATE;
import static io.jexxa.jexxatemplate.architecture.PackageName.APPLICATIONSERVICE;
import static io.jexxa.jexxatemplate.architecture.PackageName.BUSINESS_EXCEPTION;
import static io.jexxa.jexxatemplate.architecture.PackageName.DOMAIN_EVENT;
import static io.jexxa.jexxatemplate.architecture.PackageName.DOMAIN_PROCESS_SERVICE;
import static io.jexxa.jexxatemplate.architecture.PackageName.DOMAIN_SERVICE;
import static io.jexxa.jexxatemplate.architecture.PackageName.VALUE_OBJECT;

class PatternLanguageTest {

    private static JavaClasses importedClasses;

    @BeforeAll
    static void initBeforeAll()
    {
        importedClasses = new ClassFileImporter()
                .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
                .importPackages(JexxaTemplate.class.getPackage().getName());
    }

    @Test
    void testAnnotationApplicationService()
    {
        // Arrange

        //Act
        var annotationRule = classes().that().resideInAnyPackage(APPLICATIONSERVICE)
                .should().beAnnotatedWith(ApplicationService.class);

        //Assert
        annotationRule.check(importedClasses);
    }

    @Test
    void testAnnotationDomainService() {
        // Arrange

        //Act
        var annotationRule = classes().that().resideInAnyPackage(DOMAIN_SERVICE)
                .should().beAnnotatedWith(Repository.class)
                .orShould().beAnnotatedWith(InfrastructureService.class)
                .orShould().beAnnotatedWith(DomainService.class);

        //Assert
        annotationRule.check(importedClasses);
    }

    @Test
    void testAnnotationDomainProcessService() {
        // Arrange

        //Act
        var annotationRule = classes().that().resideInAnyPackage(DOMAIN_PROCESS_SERVICE)
                .should().beAnnotatedWith(DomainProcessStep.class)
                .orShould().beAnnotatedWith(DomainWorkflow.class)
                .allowEmptyShould(true);

        //Assert
        annotationRule.check(importedClasses);
    }

    @Test
    void testAnnotationDomainEvent() {
        // Arrange

        //Act
        var annotationRule = classes().that().resideInAnyPackage(DOMAIN_EVENT)
                .should().beAnnotatedWith(DomainEvent.class)
                .allowEmptyShould(true);

        //Assert
        annotationRule.check(importedClasses);
    }

    @Test
    void testAnnotationValueObject() {
        // Arrange

        //Act
        var annotationRule = classes().that().resideInAnyPackage(VALUE_OBJECT)
                .should().beAnnotatedWith(ValueObject.class)
                .allowEmptyShould(true);

        //Assert
        annotationRule.check(importedClasses);
    }

    @Test
    void testAnnotationBusinessException() {
        // Arrange

        //Act
        var annotationRule = classes().that().resideInAnyPackage(BUSINESS_EXCEPTION)
                .should().beAnnotatedWith(BusinessException.class)
                .allowEmptyShould(true);

        //Assert
        annotationRule.check(importedClasses);
    }

    @Test
    void testAnnotationAggregate() {
        // Arrange

        //Act
        var annotationRule = classes().that().resideInAnyPackage(AGGREGATE)
                .should().beAnnotatedWith(Aggregate.class)
                .allowEmptyShould(true);

        //Assert
        annotationRule.check(importedClasses);
    }

}
