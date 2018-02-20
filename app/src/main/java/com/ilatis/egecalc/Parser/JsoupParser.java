package com.ilatis.egecalc.Parser;

import com.ilatis.egecalc.Parser.HelperClasses.ClassForUniversities;
import com.ilatis.egecalc.Parser.HelperClasses.Discipline;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by thymomenosgata on 18.02.18.
 */

public class JsoupParser {
    static final String URL = "http://postyplenie.ru/calculator.php?Vuz=all&obzestvoznanie=100" +
            "&russkiy=100&informatika=100&biologiya=100&geografiya=100&ximiya=100&fizika=100" +
            "&literatura=100&history=100&matematika=100&lang=100&Submit.x=0&Submit.y=0";
    public static ArrayList<ClassForUniversities> Parse() throws IOException {
        Document doc = Jsoup.connect(URL).get();
        Elements strU = doc.getElementsByClass("s");

        ArrayList<ClassForUniversities> univers = new ArrayList<ClassForUniversities>();
        int i = 0;
        for(Element el : strU){
            Elements strS = doc.getElementsByClass("s").get(i)
                    .getElementsByTag("td").get(2).getAllElements();
            Elements strD = doc.getElementsByClass("s").get(i)
                    .getElementsByClass("small_text gray_text");
            Elements strC = doc.getElementsByClass("s").get(i)
                    .getElementsByTag("td").get(4).getAllElements();
            Elements strY = doc.getElementsByClass("s").get(i)
                    .getElementsByClass("blue_text").get(0).getAllElements();

            String ss = geter(strS.text(), strD.text());
            int prB = Integer.parseInt(geter(strC.text(), " "));
            int prY = Integer.parseInt(geter(strY.text(), " "));
            Discipline disc = new Discipline();
            disc = getDisc(strD.text());

            univers.add(
                    new ClassForUniversities(el.getElementsByClass("light_gray_blue").text(),
                            doc.getElementsByClass("s").get(i)
                                    .getElementsByClass("light_gray_back gray_border_right").text(),
                            ss,
                            disc,
                            prB,
                            prY
                    ));
            i++;
        }
        return univers;
    }

    public static Discipline getDisc(String input) {

        Discipline disc = new Discipline();
        Pattern pattern = Pattern.compile("[ ,.!?]");
        String[] str = pattern.split(input);
        for (String s : str) {
            if (s.equals("обществознание")) {
                disc.setObsh(true);
            } else if (s.equals("русский")) {
                disc.setRus(true);
            } else if (s.equals("информатика и ИКТ")) {
                disc.setIkt(true);
            } else if (s.equals("биология")) {
                disc.setBio(true);
            } else if (s.equals("химия")) {
                disc.setHim(true);
            } else if (s.equals("физика")) {
                disc.setFiz(true);
            } else if (s.equals("литература")) {
                disc.setLit(true);
            } else if (s.equals("география")) {
                disc.setGeo(true);
            } else if (s.equals("история")) {
                disc.setIst(true);
            } else if (s.equals("математика")) {
                disc.setMat(true);
            } else if (s.equals("иностранный язык")) {
                disc.setInyz(true);
            }
        }
        return disc;
    }

    public static String geter(String input, String inputCut){
        Pattern pattern = Pattern.compile(inputCut);
        Matcher matcher = pattern.matcher(input);
        String prB = matcher.replaceAll("");
        return prB;
    }
}
