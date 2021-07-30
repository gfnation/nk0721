import Exceptions.InvalidPercentageException;
import Exceptions.RentalDayCountException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class RentalAgreementTest {

    Checkout checkout;
    RentalAgreement rentalAgreement;

    @Before
    public void setUp() throws RentalDayCountException, InvalidPercentageException {
        checkout = new Checkout();
    }

    @After
    public void cleanUp() {
        checkout = null;
        rentalAgreement = null;
    }

    @Test(expected = InvalidPercentageException.class)
    public void test1() throws RentalDayCountException, InvalidPercentageException {
        checkout.setAvailableTools(AvailableTools.LADW);
        checkout.setCheckOutDate(LocalDate.of(2015, Month.SEPTEMBER, 3));
        checkout.setNumOfRentalDays(5);
        checkout.setDiscountPercentage(101);
        rentalAgreement = new RentalAgreement(checkout);
        rentalAgreement.printAgreement();
    }

    @Test
    public void test2() throws RentalDayCountException, InvalidPercentageException {
        checkout.setAvailableTools(AvailableTools.LADW);
        checkout.setCheckOutDate(LocalDate.of(2020, Month.JULY, 2));
        checkout.setNumOfRentalDays(3);
        checkout.setDiscountPercentage(10);
        rentalAgreement = new RentalAgreement(checkout);

        rentalAgreement.printAgreement();

        // Due Date
        LocalDate expectedDueDate = LocalDate.of(2020, Month.JULY, 5);
        assertEquals(rentalAgreement.getDueDate(), expectedDueDate);

        // Charge Days
        int expectedNumOfChargeDays = 2;
        assertEquals(rentalAgreement.getChargeDays(), expectedNumOfChargeDays);

        // Pre-discount charge
        String expectedPreDiscountCharge = "3.98";
        assertEquals(String.format("%.2f", rentalAgreement.getPreDiscountCharge()), expectedPreDiscountCharge);

        // Discount amount
        String expectedDiscountAmount = "0.40";
        assertEquals(String.format("%.2f", rentalAgreement.getDiscountAmount()), expectedDiscountAmount);

        // Final Charge
        String expectedFinalCharge = "3.58";
        assertEquals(String.format("%.2f", rentalAgreement.getFinalCharge()), expectedFinalCharge);
    }

    @Test
    public void test3() throws RentalDayCountException, InvalidPercentageException {
        checkout.setAvailableTools(AvailableTools.CHNS);
        checkout.setCheckOutDate(LocalDate.of(2015, Month.JULY, 2));
        checkout.setNumOfRentalDays(5);
        checkout.setDiscountPercentage(25);

        rentalAgreement = new RentalAgreement(checkout);
        rentalAgreement.printAgreement();

        // Due Date
        LocalDate expectedDueDate = LocalDate.of(2015, Month.JULY, 7);
        assertEquals(rentalAgreement.getDueDate(), expectedDueDate);

        // Charge Days
        int expectedNumOfChargeDays = 3;
        assertEquals(rentalAgreement.getChargeDays(), expectedNumOfChargeDays);

        // Pre-discount charge
        String expectedPreDiscountCharge = "4.47";
        assertEquals(String.format("%.2f", rentalAgreement.getPreDiscountCharge()), expectedPreDiscountCharge);

        // Discount amount
        String expectedDiscountAmount = "1.12";
        assertEquals(String.format("%.2f", rentalAgreement.getDiscountAmount()), expectedDiscountAmount);

        // Final Charge
        String expectedFinalCharge = "3.35";
        assertEquals(String.format("%.2f", rentalAgreement.getFinalCharge()), expectedFinalCharge);

    }

    @Test
    public void test4() throws RentalDayCountException, InvalidPercentageException {
        checkout.setAvailableTools(AvailableTools.JAKD);
        checkout.setCheckOutDate(LocalDate.of(2015, Month.SEPTEMBER, 3));
        checkout.setNumOfRentalDays(6);
        checkout.setDiscountPercentage(0);
        rentalAgreement = new RentalAgreement(checkout);
        rentalAgreement.printAgreement();

        // Due Date
        LocalDate expectedDueDate = LocalDate.of(2015, Month.SEPTEMBER, 9);
        assertEquals(rentalAgreement.getDueDate(), expectedDueDate);

        // Charge Days
        int expectedNumOfChargeDays = 3;
        assertEquals(rentalAgreement.getChargeDays(), expectedNumOfChargeDays);

        // Pre-discount charge
        String expectedPreDiscountCharge = "8.97";
        assertEquals(String.format("%.2f", rentalAgreement.getPreDiscountCharge()), expectedPreDiscountCharge);

        // Discount amount
        String expectedDiscountAmount = "0.00";
        assertEquals(String.format("%.2f", rentalAgreement.getDiscountAmount()), expectedDiscountAmount);

        // Final Charge
        String expectedFinalCharge = "8.97";
        assertEquals(String.format("%.2f", rentalAgreement.getFinalCharge()), expectedFinalCharge);
    }

    @Test
    public void test5() throws RentalDayCountException, InvalidPercentageException {
        checkout.setAvailableTools(AvailableTools.JAKR);
        checkout.setCheckOutDate(LocalDate.of(2015, Month.JULY, 2));
        checkout.setNumOfRentalDays(9);
        checkout.setDiscountPercentage(0);

        rentalAgreement = new RentalAgreement(checkout);
        rentalAgreement.printAgreement();

        // Due Date
        LocalDate expectedDueDate = LocalDate.of(2015, Month.JULY, 11);
        assertEquals(rentalAgreement.getDueDate(), expectedDueDate);

        // Charge Days
        int expectedNumOfChargeDays = 5;
        assertEquals(rentalAgreement.getChargeDays(), expectedNumOfChargeDays);

        // Pre-discount charge
        String expectedPreDiscountCharge = "14.95";
        assertEquals(String.format("%.2f", rentalAgreement.getPreDiscountCharge()), expectedPreDiscountCharge);

        // Discount amount
        String expectedDiscountAmount = "0.00";
        assertEquals(String.format("%.2f", rentalAgreement.getDiscountAmount()), expectedDiscountAmount);

        // Final Charge
        String expectedFinalCharge = "14.95";
        assertEquals(String.format("%.2f", rentalAgreement.getFinalCharge()), expectedFinalCharge);
    }

    @Test
    public void test6() throws InvalidPercentageException, RentalDayCountException {
        checkout.setAvailableTools(AvailableTools.JAKR);
        checkout.setCheckOutDate(LocalDate.of(2020, Month.JULY, 2));
        checkout.setNumOfRentalDays(4);
        checkout.setDiscountPercentage(50);

        rentalAgreement = new RentalAgreement(checkout);
        rentalAgreement.printAgreement();

        // Due Date
        LocalDate expectedDueDate = LocalDate.of(2020, Month.JULY, 6);
        assertEquals(rentalAgreement.getDueDate(), expectedDueDate);

        // Charge Days
        int expectedNumOfChargeDays = 1;
        assertEquals(rentalAgreement.getChargeDays(), expectedNumOfChargeDays);

        // Pre-discount charge
        String expectedPreDiscountCharge = "2.99";
        assertEquals(String.format("%.2f", rentalAgreement.getPreDiscountCharge()), expectedPreDiscountCharge);

        // Discount amount
        String expectedDiscountAmount = "1.50";
        assertEquals(String.format("%.2f", rentalAgreement.getDiscountAmount()), expectedDiscountAmount);

        // Final Charge
        String expectedFinalCharge = "1.49";
        assertEquals(String.format("%.2f", rentalAgreement.getFinalCharge()), expectedFinalCharge);
    }
}
