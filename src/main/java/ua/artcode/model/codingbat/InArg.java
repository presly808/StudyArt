package ua.artcode.model.codingbat;

/**
 * Created by Razer on 13.12.15.
 */
public class InArg {

    private String type;
    private String name;

    public InArg(String type, String name) {
        this.type = type;
        this.name = name;
    }

    public InArg() {
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("InArg{");
        sb.append("type='").append(type).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

