package ua.artcode.utils.codingbat;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ua.artcode.model.codingbat.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;


public class CodingBatTaskGrabber {

    private static final Logger LOG = Logger.getLogger(CodingBatTaskGrabber.class);

    //@Value("${codingbat.url}")
    public  String codingBatUrl = "http://codingbat.com";

    private List<String> taskLinksContainer;

    public CodingBatTaskGrabber() {

    }

    private void findGroupLinks() {
        LOG.trace("find group links");
        taskLinksContainer = new ArrayList<>();
        try {
            Document doc = Jsoup.connect(codingBatUrl + "/java").get();
            // added one unnecessary element with empty link. Can't fix this
            Elements groupLinks = doc.getElementsMatchingOwnText("more");
            for (Element link : groupLinks) {
                // verify not empty links
                if (!link.attr("href").equals("")) {
                    // create actual link of task group
                    String linkOfTaskGroup = codingBatUrl + link.attr("href");
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
            taskLinksContainer.add(codingBatUrl + link.attr("href"));
        }
    }

    private String getCodingBatId(String taskLink) {
        String[] separatedLink = taskLink.split("/");
        return separatedLink[4];
    }

    private String[] getFullTitle(Document doc) {
        // This method give array of strings:
        // 0 - name of site
        // 1 - program language
        // 2 - name of task group
        // 3 - name of task
        return doc.title().split(" ");
    }

    private String getTitle(Document doc) {
        return getFullTitle(doc)[3];
    }

    private String getGroupName(Document doc) {
        return getFullTitle(doc)[2];
    }

    private String getTemplate(Document doc) {
        return doc.body().getElementsByTag("textarea").val();
    }

    private String getDescription(Element infoTable) {
        String[] fullDescription = infoTable.html().split("<br>");
        return fullDescription[0];
    }

    private String getExamples(Element infoTable, String title) {
        String examples = "";
        String[] taskInfo = infoTable.ownText().split(title);

        for (int j = 1; j < taskInfo.length; j++) {
            if (j == taskInfo.length - 1) {
                examples = examples + title + taskInfo[j] + "\n";
            } else {
                examples = examples + title + taskInfo[j];
            }
        }
        return examples;
    }

    public Collection<Task> getTasksFromCodingBat() {
        LOG.trace("Start loading tasks from coding bat");

        Collection<Task> taskCollection = new LinkedList<>();

        findGroupLinks();

        for (String taskLink : taskLinksContainer) {

            try {

                Document doc = Jsoup.connect(taskLink).get();

                String codingBatId = getCodingBatId(taskLink);
                String groupName = getGroupName(doc);
                String title = getTitle(doc);

                String description;
                String examples;
                String template = getTemplate(doc);

                // get table with needed information(descriptions, examples)
                Elements tables = doc.body().getElementsByAttributeValue("width", "700");

                for (Element infoTable : tables) {

                    description = getDescription(infoTable);
                    examples = getExamples(infoTable, title);

                    Task codingBatTask = new Task(title, description, examples, template, groupName);

                    codingBatTask.setMethodSignature(CodingBatTaskUtils.getMethodSignature(codingBatTask.getTemplate()));
                    CodingBatTaskUtils.initTaskTestDataContainer(codingBatTask,codingBatId);

                    taskCollection.add(codingBatTask);
                }
            } catch (IOException e) {
                LOG.error(e);
            }

        }
        return taskCollection;
    }
}
