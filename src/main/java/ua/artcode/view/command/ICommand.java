package ua.artcode.view.command;

/**
 * Created by Razer on 27.12.15.
 */
public interface ICommand {

    boolean execute(String param);

    String getName();

}
