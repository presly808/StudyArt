package ua.artcode.view.command;

/**
 * Created by Razer on 27.12.15.
 */
public class Help implements ICommand {

    private String name = "help";

    @Override
    public boolean execute(String param) {
        return false;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Help{");
        sb.append("name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
