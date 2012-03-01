package de.exxcellent.beanvalidation.example.customconstraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * @author Benjamin Schmid <B.Schmid@exxcellent.de>
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AvailableForTourValidator.class)
public @interface AvailableForTour {

    String message() default "Ein verschrottetes Fahrzeug darf nicht verplant sein.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
