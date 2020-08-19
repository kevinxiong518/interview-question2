package com.backbase.assignment.util;

import com.backbase.assignment.exception.InvalidNumbersException;
import com.backbase.assignment.model.NumberList;
import lombok.var;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NumbersUtil {

    public static final Logger LOG = LoggerFactory.getLogger(NumbersUtil.class);

    public static NumberList convertToList(String numbers) {
        final List<Integer> numList = new ArrayList<>();
        NumberList numberList = new NumberList(numList, Strings.EMPTY);
        if (numbers == null || numbers.trim().isEmpty())
            return numberList;

        String[] numStrings = numbers.split(",");
        StringBuilder sb = new StringBuilder();
        for (String numString : numStrings) {
            try {
                numList.add(Integer.parseInt(numString.trim()));
                sb.append(numString.trim());
                sb.append(',');
            } catch (NumberFormatException e) {
                LOG.error(e.getMessage(), e);
                throw new InvalidNumbersException("Invalid integer value " + numString.trim() + " found in numbers parameter.");
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        numberList.setStringValue(sb.toString());
        return numberList;
    }

    public static boolean isPermutation(List<Integer> l1, List<Integer> l2) {
        if (l1 == null || l2 == null) return false;
        if (l1.size() != l2.size()) return false;

        Map<Integer, Integer> counts = new HashMap<>();
        for (int i = 0; i < l1.size(); i++) {
            counts.put(l1.get(i), counts.getOrDefault(l1.get(i), 0) + 1);
            counts.put(l2.get(i), counts.getOrDefault(l2.get(i), 0) - 1);
        }

        for (var entry : counts.entrySet())
            if (entry.getValue() != 0) return false;

        return true;
    }

}
