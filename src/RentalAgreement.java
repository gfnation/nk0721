import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;

public class RentalAgreement {
    private final Checkout checkout;
    private final String toolCode;
    private final String toolType;
    private final String toolBrand;
    private final int rentalDays;
    private final LocalDate checkoutDate;
    private final LocalDate dueDate;
    private final double dailyRentalCharge;
    private final int chargeDays;
    private final double preDiscountCharge;
    private final int discountPercent;
    private final double discountAmount;
    private final double finalCharge;
    private String agreement;

    public RentalAgreement(Checkout checkout) {
        this.checkout = checkout;

        AvailableTools availableTools = checkout.getAvailableTools();
        ToolTypes toolTypes = availableTools.getToolTypes();

        toolCode = availableTools.name();
        toolType = toolTypes.getToolType();
        toolBrand = availableTools.getBrandName();
        rentalDays = checkout.getNumOfRentalDays();
        checkoutDate = checkout.getCheckOutDate();
        dueDate = calculateDueDate();
        dailyRentalCharge = toolTypes.getDailyCharge();
        chargeDays = calculateChargeDays();
        preDiscountCharge = dailyRentalCharge * chargeDays;
        discountPercent = checkout.getDiscountPercentage();
        // Round here to avoid rounding errors later
        discountAmount = Double.parseDouble(String.format("%.2f", (discountPercent / 100.0) * preDiscountCharge));
        finalCharge = preDiscountCharge - discountAmount;
        setAgreement();
    }

    private void setAgreement() {
        StringBuilder agreement = new StringBuilder();
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MM/dd/yy");

        agreement.append(String.format("Tool code: %s\n", toolCode));
        agreement.append(String.format("Tool type: %s\n", toolType));
        agreement.append(String.format("Tool brand: %s\n", toolBrand));
        agreement.append(String.format("Rental Days: %s\n", rentalDays));
        agreement.append(String.format("Check out date: %s\n", dateFormat.format(checkoutDate)));
        agreement.append(String.format("Due date: %s\n", dateFormat.format(dueDate)));
        agreement.append(String.format("Daily rental charge: $%.2f\n", dailyRentalCharge));
        agreement.append(String.format("Charge days: %d\n", chargeDays));
        agreement.append(String.format("Pre-discount charge: $%.2f\n", preDiscountCharge));
        agreement.append(String.format("Discount percent: %d%%\n", discountPercent));
        agreement.append(String.format("Discount amount: $%.2f\n", discountAmount));
        agreement.append(String.format("Final charge: $%.2f\n", finalCharge));

        this.agreement = agreement.toString();
    }

    public void printAgreement() {
        System.out.println(agreement);
    }

    private LocalDate calculateDueDate() {
        int numOfRentalDays = checkout.getNumOfRentalDays();
        LocalDate checkoutDate = checkout.getCheckOutDate();

        return checkoutDate.plusDays(numOfRentalDays);
    }

    private int calculateChargeDays() {
        LocalDate startDate = checkout.getCheckOutDate();
        LocalDate endDate = calculateDueDate();

        ToolTypes toolTypes = checkout.getAvailableTools().getToolTypes();
        boolean isWeekdayCharge = toolTypes.isWeekdayCharge();
        boolean isWeekendCharge = toolTypes.isWeekendCharge();
        boolean isHolidayCharge = toolTypes.isHolidayCharge();

        int chargeDays = 0;

        while (startDate.isBefore(endDate)) {
            startDate = startDate.plusDays(1);
            int dayOfWeek = startDate.getDayOfWeek().getValue();
            if ((isWeekdayCharge && isWeekday(dayOfWeek)) ||
                    (isWeekendCharge && isWeekend(dayOfWeek))) {

                if (isHolidayCharge && isObservedHoliday(startDate)) {
                    chargeDays++;
                } else if (!isObservedHoliday(startDate)) {
                    chargeDays++;
                }
            } else if (!isWeekdayCharge && isHolidayCharge && isObservedHoliday(startDate)) {
                chargeDays++;
            }
        }
        return chargeDays;
    }

    private boolean isWeekday(int dayOfWeek) {
        return dayOfWeek >= DayOfWeek.MONDAY.getValue()
                && dayOfWeek <= DayOfWeek.FRIDAY.getValue();
    }

    private boolean isWeekend(int dayOfWeek) {
        return dayOfWeek == DayOfWeek.SATURDAY.getValue()
                || dayOfWeek == DayOfWeek.SUNDAY.getValue();
    }

    private boolean isObservedHoliday(LocalDate date) {
        LocalDate laborDay = LocalDate.of(date.getYear(), Month.SEPTEMBER, 1);
        while (laborDay.getDayOfWeek() != DayOfWeek.MONDAY) {
            laborDay = laborDay.plusDays(1);
        }

        LocalDate independenceDay = LocalDate.of(date.getYear(), Month.JULY, 4);
        if (independenceDay.getDayOfWeek() == DayOfWeek.SATURDAY) {
            independenceDay = independenceDay.minusDays(1);
        } else if (independenceDay.getDayOfWeek() == DayOfWeek.SUNDAY) {
            independenceDay = independenceDay.plusDays(1);
        }

        return (date.equals(laborDay) || date.equals(independenceDay));
    }

    // Generated Getters //

    public Checkout getCheckout() {
        return checkout;
    }

    public String getToolCode() {
        return toolCode;
    }

    public String getToolType() {
        return toolType;
    }

    public String getToolBrand() {
        return toolBrand;
    }

    public int getRentalDays() {
        return rentalDays;
    }

    public LocalDate getCheckoutDate() {
        return checkoutDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public double getDailyRentalCharge() {
        return dailyRentalCharge;
    }

    public int getChargeDays() {
        return chargeDays;
    }

    public double getPreDiscountCharge() {
        return preDiscountCharge;
    }

    public double getFinalCharge() {
        return finalCharge;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }
}
