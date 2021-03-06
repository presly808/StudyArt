package ua.artcode.model.common;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.*;
import ua.artcode.model.taskComponent.MethodSignature;
import ua.artcode.model.taskComponent.TaskTestDataContainer;
import ua.artcode.validation.Description;
import ua.artcode.validation.Template;
import ua.artcode.validation.Title;


@Entity
public class Task implements Comparable<Task> {

    @Id
    private ObjectId id;

    @Title
    private String groupName;

    @Title
    @Indexed(unique = true)
    private String title;

    @Description
    private String description;

    private String examples;

    @Template
    private String template;

    @Reference(lazy = true)
    private User owner;

    // for user statistics
    @Transient
    private boolean performed;

    private String solution;

    private MethodSignature methodSignature;

    private TaskTestDataContainer taskTestDataContainer = new TaskTestDataContainer();

//    public Task() {
//    }

    public Task() {
        id = new ObjectId();
    }

    public Task(String title, String description,
                String examples, String template, String groupName) {
        this.title = title;
        this.description = description;
        this.examples = examples;
        this.template = template;
        this.groupName = groupName;
    }

    public Task(String title, String description,
                String examples, String template, String groupName, String solution) {
        this.title = title;
        this.description = description;
        this.examples = examples;
        this.template = template;
        this.groupName = groupName;
        this.solution = solution;
    }


    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
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

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public boolean isPerformed() {
        return performed;
    }

    public void setPerformed(boolean performed) {
        this.performed = performed;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Task{");
        sb.append("id='").append(id).append('\'');
        sb.append(", groupName='").append(groupName).append('\'');
        sb.append(", title='").append(title).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", examples='").append(examples).append('\'');
        sb.append(", template='").append(template).append('\'');
        sb.append(", method signature='").append(methodSignature).append('\'');
        sb.append(", task test data container='").append(taskTestDataContainer).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task that = (Task) o;

        return !(id != null ? !id.equals(that.id) : that.id != null);
    }


    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public int compareTo(Task o) {
        return this.id.compareTo(o.id);
    }

}
