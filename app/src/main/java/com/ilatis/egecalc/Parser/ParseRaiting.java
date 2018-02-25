package com.ilatis.egecalc.Parser;

import com.ilatis.egecalc.Parser.HelperClasses.ClassRaiting;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by thymomenosgata on 26.02.18.
 */

public class ParseRaiting {
    private static final String URL = "http://egecalc.ru/rating/";
    public static ArrayList<ClassRaiting> Parser() throws IOException {
        Document doc = Jsoup.connect(URL).get();
        ArrayList<ClassRaiting> rait = new ArrayList<>();
        for(int j = 0; j<2; j++) {
            Elements el = doc.getElementsByTag("tbody").get(j)
                    .getElementsByTag("a");

            Elements el2 = doc.getElementsByTag("tbody").get(j)
                    .getElementsByTag("tr");

            Elements el3 = doc.getElementsByTag("tbody").get(j)
                    .getElementsByTag("tr");
            int i = 0;
            for (Element e : el2) {
                rait.add(new ClassRaiting(el.get(i).text(),
                        el2.get(i).getElementsByTag("td").get(1).text(),
                        el3.get(i).getElementsByTag("td").get(2).text()));
                i++;
            }
        }
        return rait;
    }

}
