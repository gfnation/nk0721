import Exceptions.InvalidPercentageException;
import Exceptions.RentalDayCountException;

import java.time.LocalDate;

public class Checkout {
    private AvailableTools availableTools;
    private int numOfRentalDays;
    private LocalDate checkOutDate;
    private int discountPercentage;

    public AvailableTools getAvailableTools() {
        return availableTools;
    }

    public void setAvailableTools(AvailableTools availableTools) {
        this.availableTools = availableTools;
    }

    public int getNumOfRentalDays() {
        return numOfRentalDays;
    }

    public void setNumOfRentalDays(int numOfRentalDays) throws RentalDayCountException {
        if (numOfRentalDays < 1) {
            throw new RentalDayCountException("Rental day count is not 1 or greater.");
        }
        this.numOfRentalDays = numOfRentalDays;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public int getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(int discountPercentage) throws InvalidPercentageException {
        if (discountPercentage < 0 || discountPercentage > 100) {
            throw new InvalidPercentageException("The discount percentage must be a whole number between 1-100.");
        }
        this.discountPercentage = discountPercentage;
    }
}
