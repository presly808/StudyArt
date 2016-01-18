package ua.artcode.preprocess;

/**
 * Created by Razer on 28.12.15.
 */

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import ua.artcode.model.codingbat.TestArg;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;


//TODO velocity
public class TemplateProcessor {

    private VelocityEngine velocityEngine;

    //TODO process(CodinngBatTask task)
    public void process(String templatePath, String srcFileDest, String className, String methodName, List<TestArg> args, String allMethod) {

        //velocityEngine.init();
        //TODO inti velocity via spring
        // инициализация Velocity
        VelocityContext vc = new VelocityContext(); // создание контекста Velocity

        vc.put("methodName", methodName);
        vc.put("allMethod", allMethod);
        vc.put("className", className);
        vc.put("argsList", args);
        vc.put("lastArgNum", args.size() - 1); // use it variable for set comma iteration

        Template template = velocityEngine.getTemplate(templatePath, "utf-8"); // download template from file

        //TODO hold template in app for optimization
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(srcFileDest)))) {
            template.merge(vc, bw); // метод merge() принимает набор данных в виде объекта "vc" и объект потока "bw"
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public VelocityEngine getVelocityEngine() {
        return velocityEngine;
    }

    public void setVelocityEngine(VelocityEngine velocityEngine) {
        this.velocityEngine = velocityEngine;
    }
}