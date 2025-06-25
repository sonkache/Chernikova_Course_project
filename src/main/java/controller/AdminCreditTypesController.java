package controller;

import database.Database;
import model.CreditType;
import model.CreditTypes;
import view.AdminCreditTypesView;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.sql.*;
public class AdminCreditTypesController {
    private final AdminCreditTypesView view;
    private final CreditTypes model;
    public AdminCreditTypesController(AdminCreditTypesView view, CreditTypes model) {
        this.view = view;
        this.model = model;
        view.getAddButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                onAdd();
            }
        });
        update();
        model.addListener(new Runnable() {
            @Override
            public void run() {
                update();
            }
        });

    }
    private void update() {
        view.getTypes().setItems(FXCollections.observableArrayList(model.getAll()));
    }
    private void onAdd() {
        String name = view.getName().getText().trim();
        String cond = view.getConditions().getText().trim();
        String percText = view.getPercent().getText().trim();
        String periodText = view.getPeriod().getText().trim();
        if (name.isEmpty() || percText.isEmpty() || periodText.isEmpty()) {
            return;
        }

        try {
            double percent = Double.parseDouble(percText);
            int period = Integer.parseInt(periodText);
            try (Connection conn = Database.getConnection()) {
                String sql = "INSERT INTO credit_types (name, conditions, percent, return_period) "
                        + "VALUES (?, ?, ?, ?)";
                PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, name);
                ps.setString(2, cond);
                ps.setDouble(3, percent);
                ps.setInt(4, period);
                ps.executeUpdate();
                ResultSet keys = ps.getGeneratedKeys();
                keys.next();
                int newId = keys.getInt(1);
                CreditType ct = new CreditType(newId, name, cond, percent, period);
                model.add(ct);
                view.getName().clear();
                view.getConditions().clear();
                view.getPercent().clear();
                view.getPeriod().clear();
            }
        } catch (NumberFormatException | SQLException ex) {
            ;
        }
    }
}
