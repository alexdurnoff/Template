package ru.durnov.view.unitData;

import ru.durnov.view.RowsNumbers;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringRow implements RowsNumbers {
    private final String text;
    private final static Pattern numberPattern = Pattern.compile("[0-9]{1,3}");
    private final static Pattern diapasonPattern = Pattern.compile("([0-9]{1,3})-([0-9]{1,3})");

    public StringRow(String text){
        this.text = text;
    }


    @Override
    public List<Integer> listNumbers() {
        List<Integer> list = new ArrayList<>();
        String[] strings = this.text.split(",");
        for (String string : strings) {
            String trimString = string.trim();
            Matcher numberMatcher = numberPattern.matcher(trimString);
            if (numberMatcher.matches()){
                try {
                    list.add(Integer.parseInt(trimString));
                } catch (NumberFormatException ignored) {
                }
            } else {
                Matcher diapasonMatcher = diapasonPattern.matcher(trimString);
                int start = 0;
                int end = 0;
                try {
                    if (diapasonMatcher.find()) {
                        start = Integer.parseInt(diapasonMatcher.group(1));
                        end = Integer.parseInt(diapasonMatcher.group(2));
                    }
                } catch (NumberFormatException e) {
                    continue;
                }
                if (start != 0 && end != 0){
                    for (int i = start; i <=end; i++){
                        list.add(i);
                    }
                }
            }
        }
        return list;
    }
}
