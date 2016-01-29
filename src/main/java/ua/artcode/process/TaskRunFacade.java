package ua.artcode.process;

import org.apache.log4j.Logger;
import ua.artcode.model.codingbat.CodingBatTask;
import ua.artcode.model.codingbat.TaskTestResult;
import ua.artcode.model.codingbat.TestArg;
import ua.artcode.preprocess.TemplateProcessor;
import ua.artcode.utils.codingbat.CodingBatTaskUtils;
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

    private static final Logger LOG = Logger.getLogger(TaskRunFacade.class);

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

    public TaskTestResult runTask(CodingBatTask task, String userCode) {

        TaskTestResult taskTestResult = new TaskTestResult();

        String className = generateMagicTempClassName(task);
        String generatedSrcFile = srcRoot.getPath() + "/" + className + ".java";

        String methodName = CodingBatTaskUtils.getMethodName(task.getTemplate());

        List<TestArg> adapterList = prepareData(task);

        templateProcessor.process(templatePath, generatedSrcFile, className, methodName, adapterList, userCode);

        message = dynamicCompiler.compile(generatedSrcFile);
        if (message == null) {
            Class cl = BaseClassLoader.uriLoadClass(srcRoot, className);
            //Convert types, which retrieved fromDB as String
            dateConverter.convert(task);
            try {
                MethodInvoker action = (MethodInvoker) cl.newInstance();
                taskTestResult = TestRunner.run(action, task.getTaskTestDataContainer());
                taskTestResult.setCodingBatId(task.getCodingBatId());
                taskTestResult.setUserCode(userCode);
                taskTestResult.setStatus(CodingBatTaskUtils.statusGenerator(taskTestResult.getResults()));
            } catch (InstantiationException | IllegalAccessException e) {
                LOG.error(e.getMessage());
            }
        } else {
            taskTestResult.setStatus(message);
        }
        return taskTestResult;
    }

    private List prepareData(CodingBatTask task) {
        List argsForTemplate = task.getTaskTestDataContainer().getTaskTestDataList().get(0).getInData();
        List<TestArg> adapterList = new ArrayList<>();
        for (int i = 0; i < argsForTemplate.size(); i++) {
            adapterList.add(new TestArg(i, task.getMethodSignature().getInArgList().get(i).getType(), argsForTemplate.get(i)));
        }
        return adapterList;
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
