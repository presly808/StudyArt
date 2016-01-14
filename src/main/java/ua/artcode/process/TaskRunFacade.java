package ua.artcode.process;

import ua.artcode.model.codingbat.CodingBatTask;
import ua.artcode.model.codingbat.TaskTestResult;
import ua.artcode.model.codingbat.TestArg;
import ua.artcode.preprocess.TemplateProcessor;
import ua.artcode.utils.codingbat.DataUnmarshaller;
import ua.artcode.utils.dynamic_compile.BaseClassLoader;
import ua.artcode.utils.dynamic_compile.DynamicCompiler;
import ua.artcode.utils.dynamic_compile.MethodInvoker;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class TaskRunFacade {

    private String templatePath;
    private File srcRoot;
    private DataUnmarshaller dateConverter;
    private DynamicCompiler dynamicCompiler;
    private TemplateProcessor templateProcessor;
    private String message;

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


        if (!srcRoot.exists()) {
            srcRoot.mkdir();
        }

    }

    //TODO check file
    public void runTask(CodingBatTask task, String method) {
        TaskTestResult taskTestResult;
        //Make method from template
        //TODO refactor this section
        //String templatePath = AppPrope
        String className = generateMagicTempClassName(task);
        String generatedSrcFile = srcRoot.getPath() + "/" + className + ".java";

        String methodName = task.getTitle();

        //TODO refactor getting argsForTemplate
        List argsForTemplate = task.getTaskTestDataContainer().getTaskTestDataList().get(0).getInData();
        List<TestArg> kostylList = new ArrayList<>();
        for (int i = 0; i < argsForTemplate.size(); i++) {
            kostylList.add(new TestArg(i, task.getMethodSignature().getInArgList().get(i).getType(), argsForTemplate.get(i)));
        }
        templateProcessor.process(templatePath, generatedSrcFile, className, methodName, kostylList, method);

        message = dynamicCompiler.compile(generatedSrcFile);
        if (message == null) {
            Class cl = BaseClassLoader.uriLoadClass(srcRoot, className);
            //Convert types, which retrieved fromDB as String
            dateConverter.convert(task);
            try {
                MethodInvoker action = (MethodInvoker) cl.newInstance();
                taskTestResult = TestRunner.run(action, task.getTaskTestDataContainer());
                taskTestResult.setCodingBatId(task.getCodingBatId());
                taskTestResult.setUserCode(method);
                if (message != null) {
                    taskTestResult.setStatus(message);
                }
                System.out.println(taskTestResult.toString());
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        } else {
            taskTestResult = new TaskTestResult();
            taskTestResult.setStatus(message);
            System.out.println(taskTestResult.getStatus().toString());
        }

    }

    private String generateMagicTempClassName(CodingBatTask task) {
        return "_" + task.getTitle() + String.valueOf(System.currentTimeMillis()).substring(8);
    }

    public TemplateProcessor getTemplateProcessor() {
        return templateProcessor;
    }

    public void setTemplateProcessor(TemplateProcessor templateProcessor) {
        this.templateProcessor = templateProcessor;
    }

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
