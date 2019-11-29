package com.xeuj.rough;

import com.sun.javafx.binding.StringFormatter;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParseFile {
    public static void main(String[] args) {
        try {
            List<String> list = new ArrayList();
            List<String> htmlList = new ArrayList();
            String htmlOption = "<option value=\"%s\">%s</option>";
            StringBuilder stringBuilder = new StringBuilder();
            BufferedReader reader = new BufferedReader(new FileReader("/Users/ranjanchoudhury/old_bak/Documents/subCats.txt"));
            String line;
            while ((line=reader.readLine())!= null) {
                list.add(line.split("\t")[1]);
            }
            System.out.println(list);
            list.forEach(element -> {
                element = element.replaceAll(" ","     ");
                element = element.replaceAll(" ","_");
                stringBuilder.append(StringFormatter.format(htmlOption, element, element).getValue()).append("\n");
            });
            System.out.println(stringBuilder);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
