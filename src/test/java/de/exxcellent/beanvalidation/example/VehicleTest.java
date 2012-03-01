package de.exxcellent.beanvalidation.example;

import java.util.Date;
import javax.validation.Validation;
import javax.validation.Validator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import de.exxcellent.beanvalidation.example.groups.CheckOnUpdate;

/**
 * @author Benjamin Schmid <B.Schmid@exxcellent.de>
 */
public class VehicleTest {

    private Validator validator;

    @Before
    public void setUp() throws Exception {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    public void testLicencePlate() {
        Vehicle vehicle = new Vehicle("UL AB-123");
        Assert.assertEquals(0, validator.validate(vehicle).size());

        vehicle.setLicencePlate("ul AB-123");
        Assert.assertEquals(1, validator.validate(vehicle).size());
    }


    @Test
    public void testFuture() {
        Vehicle vehicle = new Vehicle("UL AB-123");
        vehicle.setNextPlannedTourDate(new Date(System.currentTimeMillis() - 0xffff));

        Assert.assertEquals(1, validator.validate(vehicle).size());
    }

    @Test
    public void testBricked() {
        Vehicle vehicle = new Vehicle("UL AB-123");
        vehicle.setNextPlannedTourDate(new Date(System.currentTimeMillis() + 0xffff));

        // No issue with that
        vehicle.setStatus(Vehicle.Status.USED);
        Assert.assertEquals(0, validator.validate(vehicle).size());

        vehicle.setStatus(Vehicle.Status.BRICKED);
        Assert.assertEquals(1, validator.validate(vehicle).size());
    }

    @Test
    public void testGroups() {
        Vehicle vehicle = new Vehicle("UL AB-123");
        Assert.assertEquals(0, validator.validate(vehicle).size());

        Assert.assertEquals(1, validator.validate(vehicle, CheckOnUpdate.class).size());

    }

}
