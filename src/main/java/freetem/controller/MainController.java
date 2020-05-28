package freetem.controller;

import freetem.data.Temtem;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Daylon
 * created 2/10/2020
 */

@Controller
public class MainController {

    @RequestMapping("/")
    public String index()
    {
        return "index.html";
    }

    @RequestMapping("prices")
    public String prices(Model model)
    {
        List<Temtem> tems = new ArrayList<>();
        List<String> urls = new ArrayList<>();
        try {
            Document doc = Jsoup.connect("https://temtem.gamepedia.com/Temtem_Species").userAgent("mozilla/17.0").get();
            Element table = doc.select("table").get(1);
            Elements rows = table.select("tr");

            for (int i = 1; i < rows.size(); i++) {
                Element row = rows.get(i);
                Elements cols = row.select("td");
                if (i > 1)
                urls.add("https://temtem.gamepedia.com/" + cols.get(1).text());
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        for (String url : urls)
        {
            Temtem next = new Temtem();
            try {
                Document doc = Jsoup.connect(url).userAgent("mozilla/17.0").get();
                Element table = doc.select("table").get(0);
                Elements rows = table.select("tr");
                next.setName(url.replace("https://temtem.gamepedia.com/", ""));
                for (Element row : rows)
                {
                    if (row.text().contains("Catch Rate"))
                    {
                        next.setCatchRate(Integer.parseInt(row.text().split("Catch Rate ")[1]));
                    }
                    if (row.text().contains("No."))
                    {
                        int id = Integer.parseInt(row.text().split("No. ")[1].replaceAll("⮜", "").replaceAll("⮞", ""));
                        next.setId(id);
                    }
                }
                if (next.getCatchRate() > 0)
                {
                    double a = 1.0 / next.getCatchRate();
                    double d = Math.ceil(a * 300);
                    next.setStartingPrice((int)(20 + d));
                }
                tems.add(next);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        model.addAttribute("tems", tems);
        return "prices.html";
    }

    @RequestMapping("calculator")
    public String calculator()
    {
        return "calculator.html";
    }

    @RequestMapping("goals")
    public String goals()
    {
        return "goals.html";
    }

}
