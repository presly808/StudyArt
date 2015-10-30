package ua.artcode.model;

import java.io.Serializable;

public class CodingBatTask implements Serializable, Comparable<CodingBatTask> {

    private String id;
    private String title;
    private String description;
    private String examples;
    private String template;

    public CodingBatTask() {
    }

    public CodingBatTask(String title, String description,
                         String examples, String template) {
        this.title = title;
        this.description = description;
        this.examples = examples;
        this.template = template;
    }

    public CodingBatTask(String id, String title,
                         String description, String examples, String template) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.examples = examples;
        this.template = template;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExamples() {
        return examples;
    }

    public void setExamples(String examples) {
        this.examples = examples;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    // generate alt+ins
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CodingBatTask{");
        sb.append("id='").append(id).append('\'');
        sb.append(", title='").append(title).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", examples='").append(examples).append('\'');
        sb.append(", template='").append(template).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public int compareTo(CodingBatTask o) {
        return this.id.compareTo(o.id);
    }

}
