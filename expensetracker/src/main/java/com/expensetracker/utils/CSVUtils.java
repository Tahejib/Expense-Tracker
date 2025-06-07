package com.expensetracker.utils;

import com.expensetracker.model.Expense;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

public class CSVUtils {

    public static void writeCsv(Writer writer, List<Expense> expenses) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(writer);

        // Writing CSV Header
        bufferedWriter.write("ID,Title,Amount,Date,Category\n");

        // Writing Expense Data
        for (Expense expense : expenses) {
            bufferedWriter.write(expense.getId() + "," + expense.getTitle() + "," + expense.getAmount() + "," + expense.getDate() + "," + expense.getCategory() + "\n");
        }

        bufferedWriter.flush();
    }
}
