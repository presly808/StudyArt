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
    private List<String> taskLinksContainer;

    public CodingBatTaskGrabber() {

    }

    private void findGroupLinks() {
        LOG.trace("find group links");
        taskLinksContainer = new ArrayList<>();
        try {
            Document doc = Jsoup.connect(CODINGBAT_BASE_URL + "/java").get();
            // added one unnecessary element with empty link. Can't fix this
            Elements groupLinks = doc.getElementsMatchingOwnText("more");
            for (Element link : groupLinks) {
                // verify not empty links
                if (!link.attr("href").equals("")) {
                    // create actual link of task group
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
        // get elements with links of task
        Elements taskLinks = doc.getElementsByAttributeValueContaining("href", "prob");
        for (Element link : taskLinks) {
            // create and add actual task link
                taskLinksContainer.add(CODINGBAT_BASE_URL + link.attr("href"));
        }
    }

    private String[] getFullTitle(Document doc) {
        // This method give array of strings:
        // 0 - name of site
        // 1 - program language
        // 2 - name of task group
        // 3 - name of task
        String[] fullTitle = doc.title().split(" ");
        return fullTitle;
    }

    private String getTitle(Document doc) {
        String title = getFullTitle(doc)[3];
        return title;
    }

    private String getGroupName(Document doc) {
        String groupName = getFullTitle(doc)[2];
        return groupName;
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

        for (String taskLink : taskLinksContainer) {

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
                // get table with needed information(descriptions, examples)
                Elements tables = doc.body().getElementsByAttributeValue("width", "700");

                for (Element infoTable : tables) {

                        description = getDescription(infoTable);
                        examples = getExamples(infoTable, title);

                        taskCollection.add(new CodingBatTask(title, description, examples, template, groupName));
                }
            } catch (IOException e) {
                LOG.error(e);
            }

        }
        return taskCollection;
    }
}
