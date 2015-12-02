package ua.artcode.model.codingbat;

import org.apache.commons.lang.StringUtils;

import java.io.Serializable;
import java.util.Arrays;


public class CodingBatTask implements Serializable, Comparable<CodingBatTask> {

    private String id;
    private String codingBatId;
    private String groupName;
    private String title;
    private String description;
    private String examples;
    private String template;

    // parsed template
    private MethodSignature methodSignature;

    private TaskTestDataContainer taskTestDataContainer;

    public CodingBatTask() {
    }

    public CodingBatTask(String codingBatId, String title, String description,
                         String examples, String template, String groupName) {
        this.codingBatId = codingBatId;
        this.title = title;
        this.description = description;
        this.examples = examples;
        this.template = template;
        this.groupName = groupName;
        initMethodSignature();
    }

    public CodingBatTask(String id, String codingBatId, String title,
                         String description, String examples, String template, String groupName) {
        this.id = id;
        this.codingBatId = codingBatId;
        this.title = title;
        this.description = description;
        this.examples = examples;
        this.template = template;
        this.groupName = groupName;

        initMethodSignature();

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCodingBatId() {
        return codingBatId;
    }

    public void setCodingBatId(String codingBatId) {
        this.codingBatId = codingBatId;
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

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public MethodSignature getMethodSignature() {
        return methodSignature;
    }

    public void setMethodSignature(MethodSignature methodSignature) {
        this.methodSignature = methodSignature;
    }

    public TaskTestDataContainer getTaskTestDataContainer() {
        return taskTestDataContainer;
    }

    public void setTaskTestDataContainer(TaskTestDataContainer taskTestDataContainer) {
        this.taskTestDataContainer = taskTestDataContainer;
    }

    // generate alt+ins
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CodingBatTask{");
        sb.append("id='").append(id).append('\'');
        sb.append(", codingBatId='").append(codingBatId).append('\'');
        sb.append(", groupName='").append(groupName).append('\'');
        sb.append(", title='").append(title).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", examples='").append(examples).append('\'');
        sb.append(", template='").append(template).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CodingBatTask that = (CodingBatTask) o;

        return !(id != null ? !id.equals(that.id) : that.id != null);

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public int compareTo(CodingBatTask o) {
        return this.id.compareTo(o.id);
    }

    // TODO extract to separate class and write test
    private void initMethodSignature() {
        methodSignature = new MethodSignature();
        methodSignature.setReturnType(StringUtils.substringBetween(template, " ", " "));
        String inParams = StringUtils.substringBetween(template, "(", ")");
        if (!inParams.equals("")) {
            for (String param : inParams.split(",")) {
                String[] typeName = param.trim().split(" ");
                methodSignature.addInArg(typeName[0], typeName[1]);
            }
        }

    }

}
