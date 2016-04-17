package ua.artcode.utils.codingbat;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ua.artcode.model.common.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;


public class CodingBatTaskGrabber {

    private static final Logger LOG = Logger.getLogger(CodingBatTaskGrabber.class);

    public  String codingBatUrl = "http://codingbat.com";

    private List<String> taskLinksContainer;

    public CodingBatTaskGrabber() {

    }

    private void findGroupLinks() {
        LOG.debug("Find links of group tasks.");
        taskLinksContainer = new ArrayList<>();
        try {
            Document doc = Jsoup.connect(codingBatUrl + "/java").get();


            Elements groupLinks = new Elements();
            Elements groupClass = doc.getElementsByClass("summ");
            for(Element e : groupClass) {
                Elements group = e.getElementsByAttribute("href");
                groupLinks.addAll(group);
            }

            for (Element link : groupLinks) {

                // verify not empty links
                if (!link.attr("href").equals("")) {
                    // create actual link of task group
                    String linkOfTaskGroup = codingBatUrl + link.attr("href");
                    LOG.info("Task's groups: " + linkOfTaskGroup);
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
        String title = getFullTitle(doc)[3];
        LOG.debug("Title: " + title);
        return title;
    }

    private String getGroupName(Document doc) {
        String group = getFullTitle(doc)[2];
        LOG.debug("Group: " + group);
        return group;
    }

    private String getDescription(Document doc) {
        String description = doc.getElementsByClass("minh").text();
        LOG.debug("Description: " + description);
        return description;
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
        LOG.debug("Examples: " + examples);
        return examples;
    }

    private String getTemplate(Document doc) {
        String template = doc.getElementById("ace_div").text();
        LOG.debug("Template: " + template);
        return template;
    }

    public Collection<Task> getTasksFromCodingBat() {
        LOG.info("Start loading tasks from codingbat.com");

        Collection<Task> taskCollection = new LinkedList<>();

        findGroupLinks();

        LOG.info("Task links found: " + taskLinksContainer.size());

        for (String taskLink : taskLinksContainer) {

            try {

                Document doc = Jsoup.connect(taskLink).get();

                String codingBatId = getCodingBatId(taskLink);
                String groupName = getGroupName(doc);
                String title = getTitle(doc);

                String description = getDescription(doc);
                String examples;
                String template = getTemplate(doc);

                // get table with needed information(descriptions, examples)
                Elements tables = doc.body().getElementsByAttributeValue("width", "700");

                for (Element infoTable : tables) {
                    //description = getDescription(infoTable);
                    examples = getExamples(infoTable, title);

                    Task codingBatTask = new Task(title, description, examples, template, groupName);

                    codingBatTask.setMethodSignature(CodingBatTaskUtils.getMethodSignature(codingBatTask.getTemplate()));
                    CodingBatTaskUtils.initTaskTestDataContainer(codingBatTask,codingBatId);

                    taskCollection.add(codingBatTask);
                    LOG.info("Grab task: " + codingBatTask.getTitle());
                }
            } catch (IOException e) {
                LOG.error(e);
            }

        }
        LOG.info("Finish loading tasks from codingbat.com");
        return taskCollection;
    }
}
