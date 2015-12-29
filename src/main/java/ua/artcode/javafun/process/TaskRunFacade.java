package ua.artcode.javafun.process;

import ua.artcode.exception.CompilationException;
import ua.artcode.javafun.dynamic_compile.BaseClassLoader;
import ua.artcode.javafun.dynamic_compile.DynamicCompiler;
import ua.artcode.javafun.dynamic_compile.MethodInvoker;
import ua.artcode.javafun.model.TestArg;
import ua.artcode.javafun.model.common.Task;
import ua.artcode.javafun.preprocess.DataUnmarshaller;
import ua.artcode.javafun.preprocess.TemplateProcessor;

import java.io.File;
import java.util.List;

//TODO make high level of abstraction (create interface) will be spring component
public class TaskRunFacade {

    private String templatePath = "/src/main/resources/general_template.vm";
    private File srcRoot = new File("temp/");
    private DataUnmarshaller dateConverter = new DataUnmarshaller();
    private DynamicCompiler dynamicCompiler = new DynamicCompiler();
    private TemplateProcessor templateProcessor = new TemplateProcessor();

    public TaskRunFacade() {
        // init temp folder for task sources
    }

    public TaskRunFacade(String templatePath, File srcRoot, DataUnmarshaller dateConverter,
                         DynamicCompiler dynamicCompiler, TemplateProcessor templateProcessor) {

        this.templatePath = templatePath;
        this.srcRoot = srcRoot;
        this.dateConverter = dateConverter;
        this.dynamicCompiler = dynamicCompiler;
        this.templateProcessor = templateProcessor;


        if(!srcRoot.exists()){
            srcRoot.mkdir();
        }

    }

    public void runTask(Task task, String method) throws CompilationException {
        //Make method from template

        //TODO refactor this section

        String className = generateMagicTempClassName(task);
        String generatedSrcFile = srcRoot.getPath()+ "/" + className + ".java";


        String methodName = task.getTaskMethod().getMethodName();

        //TODO refactor getting argsForTemplate
        List<TestArg> argsForTemplate = task.getTestCase().getDataPointList().get(0).getIn();
        templateProcessor.process(templatePath, generatedSrcFile, className, methodName, argsForTemplate, method);

        dynamicCompiler.compile(generatedSrcFile);

        Class cl = BaseClassLoader.uriLoadClass(srcRoot, className);

        //Convert types, which retrieved fromDB as String
        dateConverter.convert(task.getTestCase());

        try {
            MethodInvoker action = (MethodInvoker) cl.newInstance();
            TestRunner.run(action, task.getTestCase());
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    private String generateMagicTempClassName(Task task) {
        return "_" + task.getTaskMethod().getMethodName() + String.valueOf(System.currentTimeMillis()).substring(8);
    }

    public TemplateProcessor getTemplateProcessor() {
        return templateProcessor;
    }

    public void setTemplateProcessor(TemplateProcessor templateProcessor) {this.templateProcessor = templateProcessor;}

    public File getSrcRoot() {
        return srcRoot;
    }

    public void setSrcRoot(File srcRoot) {
        this.srcRoot = srcRoot;
    }

    public DataUnmarshaller getDateConverter() {
        return dateConverter;
    }

    public void setDateConverter(DataUnmarshaller dateConverter) {
        this.dateConverter = dateConverter;
    }

    public DynamicCompiler getDynamicCompiler() {
        return dynamicCompiler;
    }

    public void setDynamicCompiler(DynamicCompiler dynamicCompiler) {
        this.dynamicCompiler = dynamicCompiler;
    }


}
