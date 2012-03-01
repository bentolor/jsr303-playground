package de.exxcellent.beanvalidation.example;

import java.util.Date;
import javax.persistence.Id;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import de.exxcellent.beanvalidation.example.customconstraint.AvailableForTour;
import de.exxcellent.beanvalidation.example.groups.CheckOnUpdate;

/**
 * @author Benjamin Schmid <B.Schmid@exxcellent.de>
 */
@AvailableForTour
public class Vehicle {

    @Id
    @NotNull(groups = CheckOnUpdate.class)
    private Long id;

    @NotNull
    @Size(min = 5, max = 15)
    @Pattern.List(@Pattern(regexp = "[A-Z]{1,2} [A-Z]{1,2}-\\d+"))
    private String licencePlate;

    @NotNull
    private Status status;

    @Future
    private Date nextPlannedTourDate;

    public Vehicle() {
        this.status = Status.NEW;
    }

    public Vehicle(String licencePlate) {
        this.status = Status.NEW;
        this.licencePlate = licencePlate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getNextPlannedTourDate() {
        return nextPlannedTourDate;
    }

    public void setNextPlannedTourDate(Date nextPlannedTourDate) {
        this.nextPlannedTourDate = nextPlannedTourDate;
    }

    public enum Status {
        NEW,
        USED,
        BRICKED
    }
}
