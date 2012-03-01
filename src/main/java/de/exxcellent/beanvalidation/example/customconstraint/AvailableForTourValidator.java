package de.exxcellent.beanvalidation.example.customconstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import de.exxcellent.beanvalidation.example.Vehicle;

/**
 * @author Benjamin Schmid <B.Schmid@exxcellent.de>
 */
public class AvailableForTourValidator implements ConstraintValidator<AvailableForTour, Vehicle> {

    @Override
    public void initialize(AvailableForTour constraintAnnotation) {
    }

    @Override
    public boolean isValid(Vehicle value, ConstraintValidatorContext context) {
        return value.getStatus() != Vehicle.Status.BRICKED || value.getNextPlannedTourDate() == null;
    }
}
