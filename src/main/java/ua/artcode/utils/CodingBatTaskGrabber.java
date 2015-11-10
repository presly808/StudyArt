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
            Elements links = document.select("a");// get all links from the document
            for (Element link : links) {
                if (link.ownText().equals("more")) {// find links of task group
                    String linkOfTaskGroup = CODINGBAT_BASE_URL + link.attr("href");
                    initTaskLinks(linkOfTaskGroup);// init links in the group
                }
            }
        } catch (IOException e) {
            LOG.error(e);
        }
    }

    private void initTaskLinks(String linkOfTaskGroup) throws IOException {
        Document doc = Jsoup.connect(linkOfTaskGroup).get();
        Elements links = doc.select("a");// get all links from the document
        for (Element link : links) {
            if (link.attr("href").contains("prob")) {// find links of task
                taskLinks.add(CODINGBAT_BASE_URL + link.attr("href"));
            }
        }
    }

    private String getTitle(Document doc) {
        String[] fullTitle = doc.title().split(" ");
        return fullTitle[3];
    }

    private String getGroupName(Document doc) {
        String[] fullTitle = doc.title().split(" ");
        return fullTitle[2];
    }

    private String getTemplate(Document doc) {
        String template = doc.body().getElementsByTag("textarea").val();
        return template;
    }

    private String getDescription(Element infoTable) {
        String[] fullDescription = infoTable.html().split("<br>");
        return fullDescription[0];
    }

    private String getExamples(Element infoTable, String title) {
        String examples = null;
        String[] taskInfo = infoTable.ownText().split(title + "()");

        for (int j = 1; j < taskInfo.length; j++) {
            if (j == taskInfo.length - 1) {
                examples = examples + title + taskInfo[j];
            }
            else {
                examples = examples + title + taskInfo[j] + "\n";
            }
        }
        return examples;
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
                String groupName;

                doc = Jsoup.connect(taskLink).get();

                title = getTitle(doc);
                groupName = getGroupName(doc);
                template = getTemplate(doc);

                Elements tables = doc.body().getElementsByTag("td");
                //get all tables in the document
                for (Element infoTable : tables) {
                    // find the table with the right information
                    if (infoTable.attr("width").equals("700") && infoTable.attr("valign").equals("top")) {

                        description = getDescription(infoTable);
                        examples = getExamples(infoTable, title);

                        taskCollection.add(new CodingBatTask(title, description, examples, template, groupName));
                    }
                }
            } catch (IOException e) {
                LOG.error(e);
            }

        }
        return taskCollection;
    }
}
