package ua.artcode.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import ua.artcode.db.CodingBatTaskContainer;
import ua.artcode.model.CodingBatTask;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Home on 30.10.2015.
 */
public class TaskGrabber {
    private final String url = "http://codingbat.com/java";
    private CodingBatTaskContainer taskContainer;
    private ArrayList<String> taskLinks;

    public TaskGrabber(CodingBatTaskContainer taskContainer) {
        this.taskContainer = taskContainer;
    }

    private void findLinks() {
        taskLinks = new ArrayList<String>();
        try {
            Document document = Jsoup.connect(url).get();
            Elements links = document.select("a");
            for (Element link : links) {
                if (link.ownText().equals("more")) {
                    String linkOfTaskGroup = "http://codingbat.com" + link.attr("href").toString();
                    initTaskLinks(linkOfTaskGroup);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initTaskLinks(String linkOfTaskGroup) throws IOException {
        Document doc = Jsoup.connect(linkOfTaskGroup).get();
        Elements links = doc.select("a");
        for (Element link : links) {
            if (link.attr("href").toString().contains("prob")) {
                taskLinks.add("http://codingbat.com" + link.attr("href"));
            }
        }
    }

    public void addTasksFromCodingBat() {
        Document doc;
        String title;
        String description = null;
        String examples = null;
        String template;

        findLinks();

        for (int i = 0; i < taskLinks.size(); i++) {
            try {
                doc = Jsoup.connect(taskLinks.get(i)).get();
                String[] absoluteTitle = doc.title().split(" ");
                title = absoluteTitle[3];
                template = doc.body().getElementsByTag("textarea").val().toString();

                Elements tables = doc.body().getElementsByTag("td");
                for (Element infoTable : tables) {
                    if (infoTable.attr("width").toString().equals("700") && infoTable.attr("valign").toString().equals("top")) {
                        String[] absoluteDescription = infoTable.html().split("<br>");
                        description = absoluteDescription[0];

                        String[] taskInfo = infoTable.ownText().split(title + "()");
                        examples = null;
                        for (int j = 1; j < taskInfo.length; j++) {
                            examples = examples + title + taskInfo[j] + "\n";
                        }
//                        System.out.println(title);
//                        System.out.println(description);
//                        System.out.println(examples);
//                        System.out.println(template);
                        taskContainer.addTask(new CodingBatTask(title, description, examples, template));
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
