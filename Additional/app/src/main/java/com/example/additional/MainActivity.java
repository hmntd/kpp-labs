package com.example.additional;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.additional.generators.DataGenerator;
import com.example.additional.models.Customer;
import com.example.additional.models.Event;
import com.example.additional.models.Ticket;
import com.example.additional.services.BookingService;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private BookingService bookingService;
    private TextView tvConsoleLog;
    private EditText etSearchEvent;
    private Customer testCustomer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bookingService = new BookingService();
        DataGenerator.seedData(bookingService);

        testCustomer = new Customer(777, "Dev", "dev@dev.com", "+3800000000");

        tvConsoleLog = findViewById(R.id.tvConsoleLog);
        etSearchEvent = findViewById(R.id.etSearchEvent);
        Button btnSearchTickets = findViewById(R.id.btnSearchTickets);
        Button btnUpcomingEvents = findViewById(R.id.btnUpcomingEvents);
        Button btnBuyTestTicket = findViewById(R.id.btnBuyTestTicket);
        Button btnMyTickets = findViewById(R.id.btnMyTickets);
        Button btnClearLogs = findViewById(R.id.btnClearLogs);

        btnSearchTickets.setOnClickListener(v -> {
            String query = etSearchEvent.getText().toString();
            List<Ticket> freeTickets = bookingService.findFreeTickets(query);

            StringBuilder sb = new StringBuilder("Результати пошуку (" + query + "):\n");
            if (freeTickets.isEmpty()) {
                sb.append("Квитків не знайдено.");
            } else {
                for (Ticket t : freeTickets) {
                    sb.append("Квиток №").append(t.number).append(" | Ціна: $").append(t.cost).append("\n");
                }
            }
            log(sb.toString());
        });

        btnUpcomingEvents.setOnClickListener(v -> {
            List<Event> upcoming = bookingService.findUpcomingEvents();
            StringBuilder sb = new StringBuilder("Майбутні події:\n");
            for (Event e : upcoming) {
                sb.append("- ").append(e.name).append(" (").append(e.eventDate.toString()).append(")\n");
            }
            log(sb.toString());
        });

        btnBuyTestTicket.setOnClickListener(v -> {
            try {
                List<Event> events = bookingService.findUpcomingEvents();
                if (events.isEmpty()) throw new Exception("Немає івентів");

                List<Ticket> free = bookingService.findFreeTickets(events.get(0).name);
                if (free.isEmpty()) throw new Exception("Немає вільних квитків");

                Ticket toBuy = free.get(0);
                bookingService.buyTicket(testCustomer, toBuy);

                log("Успішно куплено!\nКвиток №" + toBuy.number + " тепер належить " + testCustomer.name);
            } catch (Exception e) {
                log("Помилка купівлі: " + e.getMessage());
            }
        });

        btnMyTickets.setOnClickListener(v -> {
            showMyTicketsDialog();
        });

        btnClearLogs.setOnClickListener(v -> {
            tvConsoleLog.setText("Логи очищені.\n");
        });
    }

    private void showMyTicketsDialog() {
        StringBuilder sb = new StringBuilder();
        List<Ticket> tickets = testCustomer.tickets;

        if (tickets.isEmpty()) {
            sb.append("Ви не маєте білетів");
        } else {
            for (Ticket t : tickets) {
                sb.append("Білет №").append(t.number)
                        .append(" | Подія: ").append(t.eventName)
                        .append(" | Ціна: $").append(t.cost).append("\n");
            }
        }

        new android.app.AlertDialog.Builder(this)
                .setTitle("Ваші придбані білети")
                .setMessage(sb.toString())
                .setPositiveButton("Закрити", (dialog, which) -> dialog.dismiss())
                .show();
    }

    private void log(String message) {
        String time = new java.text.SimpleDateFormat("HH:mm:ss", java.util.Locale.getDefault()).format(new java.util.Date());

        tvConsoleLog.append("[" + time + "] " + message + "\n-----------------\n");
    }
}