package utils;

import javafx.scene.control.*;
import javafx.stage.Stage;
import src.model.Appointment;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Helper methods
 */
public class Helper {

    /**
     * Closes the current stage
     * @param button
     */
    public static void closeWindow(Button button) {
        Stage stage = (Stage) button.getScene().getWindow();
        stage.close();
    }


    /**
     * Formats a cell within a tableview to display only a time when in a DateTime format
     * @param col
     */
    public static void cellFormatterToTime(TableColumn<Appointment, LocalDateTime> col) {
        DateTimeFormatter myTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        col.setCellFactory(column -> {
            return new TableCell<Appointment, LocalDateTime>() {
                @Override
                protected void updateItem(LocalDateTime item, boolean empty) {
                    super.updateItem(item, empty);

                    if (item == null || empty) {
                        setText(null);
                        setStyle("");
                    } else {
                        // Format date.
                        setText(myTimeFormatter.format(item));
                    }
                }
            };
        });
    }

    /**
     * Formats a cell within a tableview to display only a date when in a DateTime format
     * @param col
     */
    public static void cellFormatterToDate(TableColumn<Appointment, LocalDateTime> col) {
        DateTimeFormatter myDateFormatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");

        col.setCellFactory(column -> {
            return new TableCell<Appointment, LocalDateTime>() {
                @Override
                protected void updateItem(LocalDateTime item, boolean empty) {
                    super.updateItem(item, empty);

                    if (item == null || empty) {
                        setText(null);
                        setStyle("");
                    } else {
                        // Format date.
                        setText(myDateFormatter.format(item));
                    }
                }
            };
        });
    }

    /**
     * Disables past dates and all weekends from a specified DatePicker.
     * @param datePicker
     */
    public static void formatCalendar(DatePicker datePicker) {

        datePicker.setDayCellFactory(picker -> new DateCell() {
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate today = LocalDate.now();

                DayOfWeek saturday = DayOfWeek.SATURDAY;
                DayOfWeek sunday = DayOfWeek.SUNDAY;

                setDisable(empty || date.compareTo(today) < 0 || date.getDayOfWeek() == saturday || date.getDayOfWeek() == sunday);
            }
        });

    }

}
