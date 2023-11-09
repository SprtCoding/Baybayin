package com.sprtcoding.baybayin.Translators;

import java.util.HashMap;
import java.util.Map;

public class TagalogTranslator {
    private static final Map<String, String> tagalogToBaybayinMapping = new HashMap<>();

    static {
        // Mappings for single letters
        tagalogToBaybayinMapping.put("a", "ᜀ");
        tagalogToBaybayinMapping.put("b", "ᜊ᜔");
        tagalogToBaybayinMapping.put("c", "ᜃ᜔");
        tagalogToBaybayinMapping.put("d", "ᜇ᜔");
        tagalogToBaybayinMapping.put("e", "ᜁ");
        tagalogToBaybayinMapping.put("f", "F᜔");
        tagalogToBaybayinMapping.put("g", "ᜄ᜔");
        tagalogToBaybayinMapping.put("h", "ᜑ᜔");
        tagalogToBaybayinMapping.put("i", "ᜁ");
        tagalogToBaybayinMapping.put("j", "J᜔");
        tagalogToBaybayinMapping.put("k", "ᜃ᜔");
        tagalogToBaybayinMapping.put("l", "ᜎ᜔");
        tagalogToBaybayinMapping.put("m", "ᜋ᜔");
        tagalogToBaybayinMapping.put("n", "ᜈ᜔");
        tagalogToBaybayinMapping.put("o", "ᜂ");
        tagalogToBaybayinMapping.put("p", "ᜉ᜔");
        tagalogToBaybayinMapping.put("q", "Q᜔");
        tagalogToBaybayinMapping.put("r", "ᜇ᜔");
        tagalogToBaybayinMapping.put("s", "ᜐ᜔");
        tagalogToBaybayinMapping.put("t", "ᜆ᜔");
        tagalogToBaybayinMapping.put("u", "ᜂ");
        tagalogToBaybayinMapping.put("w", "ᜏ᜔");
        tagalogToBaybayinMapping.put("x", "X᜔");
        tagalogToBaybayinMapping.put("y", "ᜌ᜔");
        tagalogToBaybayinMapping.put("z", "Z᜔");

        // Complete word mappings
        tagalogToBaybayinMapping.put("ka", "ᜃ");
        tagalogToBaybayinMapping.put("ke", "ᜃᜒ");
        tagalogToBaybayinMapping.put("ki", "ᜃᜒ");
        tagalogToBaybayinMapping.put("ku", "ᜃᜓ");
        tagalogToBaybayinMapping.put("ko", "ᜃᜓ");

        tagalogToBaybayinMapping.put("sa", "ᜐ");
        tagalogToBaybayinMapping.put("se", "ᜐᜒ");
        tagalogToBaybayinMapping.put("si", "ᜐᜒ");
        tagalogToBaybayinMapping.put("su", "ᜐᜓ");
        tagalogToBaybayinMapping.put("so", "ᜐᜓ");

        tagalogToBaybayinMapping.put("da", "ᜇ");
        tagalogToBaybayinMapping.put("de", "ᜇᜒ");
        tagalogToBaybayinMapping.put("di", "ᜇᜒ");
        tagalogToBaybayinMapping.put("do", "ᜇᜓ");
        tagalogToBaybayinMapping.put("du", "ᜇᜓ");


        tagalogToBaybayinMapping.put("ga", "ᜄ");
        tagalogToBaybayinMapping.put("ge", "ᜄᜒ");
        tagalogToBaybayinMapping.put("gi", "ᜄᜒ");
        tagalogToBaybayinMapping.put("go", "ᜄᜓ");
        tagalogToBaybayinMapping.put("gu", "ᜄᜓ");

        tagalogToBaybayinMapping.put("ha", "ᜑ");
        tagalogToBaybayinMapping.put("he", "ᜑᜒ");
        tagalogToBaybayinMapping.put("hi", "ᜑᜒ");
        tagalogToBaybayinMapping.put("ho", "ᜑᜓ");
        tagalogToBaybayinMapping.put("hu", "ᜑᜓ");

        tagalogToBaybayinMapping.put("la", "ᜎ");
        tagalogToBaybayinMapping.put("le", "ᜎᜒ");
        tagalogToBaybayinMapping.put("li", "ᜎᜒ");
        tagalogToBaybayinMapping.put("lo", "ᜎᜓ");
        tagalogToBaybayinMapping.put("lu", "ᜎᜓ");

        tagalogToBaybayinMapping.put("ma", "ᜋ");
        tagalogToBaybayinMapping.put("me", "ᜋᜒ");
        tagalogToBaybayinMapping.put("mi", "ᜋᜒ");
        tagalogToBaybayinMapping.put("mo", "ᜋᜓ");
        tagalogToBaybayinMapping.put("mu", "ᜋᜓ");

        tagalogToBaybayinMapping.put("na", "ᜈ");
        tagalogToBaybayinMapping.put("ne", "ᜈᜒ");
        tagalogToBaybayinMapping.put("ni", "ᜈᜒ");
        tagalogToBaybayinMapping.put("no", "ᜈᜓ");
        tagalogToBaybayinMapping.put("nu", "ᜈᜓ");

        tagalogToBaybayinMapping.put("nga", "ᜅ");
        tagalogToBaybayinMapping.put("nge", "ᜅᜒ");
        tagalogToBaybayinMapping.put("ngi", "ᜅᜒ");
        tagalogToBaybayinMapping.put("ngo", "ᜅᜓ");
        tagalogToBaybayinMapping.put("ngu", "ᜅᜓ");

        tagalogToBaybayinMapping.put("pa", "ᜉ");
        tagalogToBaybayinMapping.put("pe", "ᜉᜒ");
        tagalogToBaybayinMapping.put("pi", "ᜉᜒ");
        tagalogToBaybayinMapping.put("po", "ᜉᜓ");
        tagalogToBaybayinMapping.put("pu", "ᜉᜓ");

        tagalogToBaybayinMapping.put("ra", "ᜇ");
        tagalogToBaybayinMapping.put("re", "ᜍᜒ");
        tagalogToBaybayinMapping.put("ri", "ᜍᜒ");
        tagalogToBaybayinMapping.put("ro", "ᜍᜓ");
        tagalogToBaybayinMapping.put("ru", "ᜍᜓ");

        tagalogToBaybayinMapping.put("ta", "ᜆ");
        tagalogToBaybayinMapping.put("te", "ᜆᜒᜒ");
        tagalogToBaybayinMapping.put("ti", "ᜆᜒ");
        tagalogToBaybayinMapping.put("to", "ᜆᜓ");
        tagalogToBaybayinMapping.put("tu", "ᜆᜓ");

        tagalogToBaybayinMapping.put("wa", "ᜏ");
        tagalogToBaybayinMapping.put("we", "ᜏᜒ");
        tagalogToBaybayinMapping.put("wi", "ᜏᜒ");
        tagalogToBaybayinMapping.put("wo", "ᜏᜓ");
        tagalogToBaybayinMapping.put("wu", "ᜏᜓ");

        tagalogToBaybayinMapping.put("ya", "ᜌ");
        tagalogToBaybayinMapping.put("ye", "ᜌᜒ");
        tagalogToBaybayinMapping.put("yi", "ᜌᜒ");
        tagalogToBaybayinMapping.put("yo", "ᜌᜓ");
        tagalogToBaybayinMapping.put("yu", "ᜌᜓ");

        tagalogToBaybayinMapping.put("ba", "ᜊ");
        tagalogToBaybayinMapping.put("bi", "ᜊᜒ");
        tagalogToBaybayinMapping.put("be", "ᜊᜒ");
        tagalogToBaybayinMapping.put("bu", "ᜊᜓᜒ");
        tagalogToBaybayinMapping.put("bo", "ᜊᜓᜒ");
    }

    public static String translate(String input) {
        if (input == null) {
            return "Invalid input";
        }

        StringBuilder result = new StringBuilder();

        int i = 0;
        while (i < input.length()) {
            int j = i + 2;
            int k = i + 3;
            if (j <= input.length()) {
                String word = input.substring(i, j).toLowerCase();
                String baybayin = tagalogToBaybayinMapping.get(word);
                if (baybayin != null) {
                    result.append(baybayin);
                    i = j;
                    continue;
                }
            } else if(k <= input.length()) {
                String word = input.substring(i, k).toLowerCase();
                String baybayin = tagalogToBaybayinMapping.get(word);
                if (baybayin != null) {
                    result.append(baybayin);
                    i = k;
                    continue;
                }
            }

            String letter = input.substring(i, i + 1).toLowerCase();
            String baybayin = tagalogToBaybayinMapping.get(letter);
            if (baybayin != null) {
                result.append(baybayin);
            } else {
                result.append(letter);
            }

            i++;
        }

        return result.toString();
    }
}
