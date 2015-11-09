package ua.artcode.utils;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import ua.artcode.model.CodingBatTask;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

// TODO need refactor, decompose big methods
public class CodingBatTaskGrabber {

    private static final Logger LOG = Logger.getLogger(CodingBatTaskGrabber.class);

    public static final String CODINGBAT_BASE_URL = "http://codingbat.com";
    private List<String> taskLinks;

    public CodingBatTaskGrabber() {

    }

    private void findGroupLinks() {
        LOG.trace("find group links");
        taskLinks = new ArrayList<>();
        try {
            Document document = Jsoup.connect(CODINGBAT_BASE_URL + "/java").get();
            Elements links = document.select("a");
            for (Element link : links) {
                if (link.ownText().equals("more")) {
                    String linkOfTaskGroup = CODINGBAT_BASE_URL + link.attr("href");
                    initTaskLinks(linkOfTaskGroup);
                }
            }
        } catch (IOException e) {
            LOG.error(e);
        }
    }

    private void initTaskLinks(String linkOfTaskGroup) throws IOException {
        Document doc = Jsoup.connect(linkOfTaskGroup).get();
        Elements links = doc.select("a");
        for (Element link : links) {
            if (link.attr("href").contains("prob")) {
                taskLinks.add(CODINGBAT_BASE_URL + link.attr("href"));
            }
        }
    }

    public Collection<CodingBatTask> getTasksFromCodingBat() {
        LOG.trace("Start loading tasks from coding bat");

        Collection<CodingBatTask> taskCollection = new LinkedList<>();

        findGroupLinks();

        for (String taskLink : taskLinks) {

            try {

                Document doc;
                String title;
                String description;
                String examples;
                String template;

                doc = Jsoup.connect(taskLink).get();

                title = doc.title().split(" ")[3];

                template = doc.body().getElementsByTag("textarea").val();

                Elements tables = doc.body().getElementsByTag("td");

                for (Element infoTable : tables) {
                    //
                    if (infoTable.attr("width").equals("700") && infoTable.attr("valign").equals("top")) {
                        String[] absoluteDescription = infoTable.html().split("<br>");
                        description = absoluteDescription[0];

                        String[] taskInfo = infoTable.ownText().split(title + "()");
                        examples = null;

                        for (int j = 1; j < taskInfo.length; j++) {
                            examples = examples + title + taskInfo[j] + "\n";
                        }

                        taskCollection.add(new CodingBatTask(title, description, examples, template));
                    }
                }
            } catch (IOException e) {
                LOG.error(e);
            }

        }
        return taskCollection;
    }
}
