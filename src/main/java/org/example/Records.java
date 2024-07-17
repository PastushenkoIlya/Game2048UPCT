package org.example;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import javax.swing.*;
import java.awt.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Records extends JDialog {
    private List<Record> records;
    private JTextArea textArea;

    public Records(Frame parent) {
        super(parent, "Records", true);
        records = new ArrayList<>();
        textArea = new JTextArea(10, 30);
        textArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);

        setSize(400, 300);
        setLocationRelativeTo(parent);

        loadRecords();
        updateTextArea();
    }

    public void addRecord(String nombre, long puntos) {
        records.add(new Record(nombre, puntos));
        Collections.sort(records, Comparator.comparingLong(Record::getPuntos).reversed());

        if (records.size() > 10) {
            records = records.subList(0, 10);
        }

        updateTextArea();
        saveRecords();
    }

    private void updateTextArea() {
        StringBuilder sb = new StringBuilder();
        for (Record record : records) {
            sb.append(record).append("\n");
        }
        textArea.setText(sb.toString());
    }

    private void loadRecords() {

        try (FileReader reader = new FileReader("src/main/resources/records.json")) {
            JSONTokener tokener = new JSONTokener(reader);
            JSONObject jsonObject = new JSONObject(tokener);
            JSONArray items = jsonObject.getJSONArray("items");

            for (int i = 0; i < items.length(); i++) {
                JSONObject item = items.getJSONObject(i);
                String nombre = item.getString("nombre");
                long puntos = item.getLong("puntos");
                records.add(new Record(nombre, puntos));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveRecords() {
        JSONArray items = new JSONArray();
        for (Record record : records) {
            JSONObject item = new JSONObject();
            item.put("nombre", record.getNombre());
            item.put("puntos", record.getPuntos());
            items.put(item);
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("items", items);

        try (FileWriter writer = new FileWriter("src/main/resources/records.json")) {
            writer.write(jsonObject.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
