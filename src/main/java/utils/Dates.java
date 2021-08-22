package utils;

import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.util.Callback;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

/**
 *
 * @author Nensi
 */
public class Dates {

	
    private Dates() {
    }

   
    public static Timestamp toTimestamp(LocalDate data) {
        return Timestamp.valueOf(data.atStartOfDay());
    }

    public static Timestamp toTimestamp(LocalDateTime data) {
        return Timestamp.valueOf(data);
    }

    
    public static Timestamp toTimestamp(String data) {
        return Timestamp.valueOf(LocalDateTime.parse(data, formatter("yyyy-MM-dd HH:mm.ss")));
    }

    public static Timestamp toTimestamp(String data, String modelo) {
        return Timestamp.valueOf(LocalDateTime.parse(data, formatter(modelo)));
    }

    public static LocalDateTime toDateTime(Timestamp time) {
        return time.toLocalDateTime();
    }

    public static LocalDate toDate(Timestamp time) {
        return time.toLocalDateTime().toLocalDate();
    }

    
    public static LocalDate toDate(String data) {
        return LocalDate.parse(data, formatter("yyyy-MM-dd"));
    }

    
    public static LocalDate toDate(String data, String modelo) {
        return LocalDate.parse(data, formatter(modelo));
    }

    
    public static String toString(Timestamp data) {
        return data == null ? "" : data.toLocalDateTime().format(formatter("dd/MM/yyyy"));
    }

    
    public static String toString(Timestamp data, String modelo) {
        return data == null ? "" : data.toLocalDateTime().format(formatter(modelo));
    }

    public static String toString(LocalDate data) {
        return data == null ? "" : data.format(formatter("dd/MM/yyyy"));
    }

    public static String toString(LocalDate data, String modelo) {
        return data == null ? "" : data.format(formatter(modelo));
    }

    
    public static String toString(LocalDateTime data, String modelo) {
        return data == null ? "" : data.format(formatter(modelo));
    }

    
    public static String mes(String data) {
        return Month.of(Integer.parseInt(data)).getDisplayName(TextStyle.SHORT, new Locale("pt")).toUpperCase();
    }

    public static Timestamp atual() {
        return toTimestamp(LocalDate.now());
    }

   
    private static DateTimeFormatter formatter(String modelo) {
        return DateTimeFormatter.ofPattern(modelo);
    }

    public static void blockDataAnterior(LocalDate data, DatePicker calendarario) {

        final Callback<DatePicker, DateCell> dayCellFactory = new Callback<DatePicker, DateCell>() {
            @Override
            public DateCell call(final DatePicker datePicker) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {

                        super.updateItem(item, empty);

                        if (item.isBefore(data.plusDays(1))) {
                            setDisable(true);
                            setStyle("-fx-background-color: #ffc8c3;");
                        }
                    }
                };
            }
        };

        calendarario.setDayCellFactory(dayCellFactory);
        calendarario.setValue(data.plusDays(1));
    }
}
