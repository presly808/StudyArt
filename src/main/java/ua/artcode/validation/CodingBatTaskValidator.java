package ua.artcode.validation;

import ua.artcode.exception.AppValidationException;
import ua.artcode.model.codingbat.CodingBatTask;

import java.util.regex.Pattern;

/**
 * Created by Maxim on 18.12.2015.
 */
public class CodingBatTaskValidator implements Validator<CodingBatTask> {

    private static final String CODING_BAT_ID_PATTERN = "p\\d+";
    private static final String GROUP_NAME_PATTERN = "[\\w._-]{3,20}";
    private static final String TITLE_PATTERN = "[\\w._-]{3,20}";
    private static final String DESCRIPTION_PATTERN = ".{10,200}";
    private static final String EXAMPLES_PATTERN = ".{10,200}";
    private static final String TEMPLATE_PATTERN = "(public\\s+|private\\s+|protected\\s+)?(static\\s+)?" +
            ".+\\s+[\\w\\$]+\\s*\\(.*\\)\\s*\\{.*\\}";
    private static final String RETURN_TYPE_PATTERN = "void|char(\\[.*\\])?|String(\\[.*\\])?|" +
            "byte(\\[.*\\])?|short(\\[.*\\])?|int(\\[.*\\])?|long(\\[.*\\])?|" +
            "float(\\[.*\\])?|double(\\[.*\\])?|boolean(\\[.*\\])?|" +
            "List(\\<.*\\>)|ArrayList(\\<.*\\>)|LinkedList(\\<.*\\>)|" +
            "Set(\\<.*\\>)|TreeSet(\\<.*\\>)|HashSet(\\<.*\\>)";



    private Pattern codingBatIdPattern = Pattern.compile(CODING_BAT_ID_PATTERN);
    private Pattern groupNamePattern = Pattern.compile(GROUP_NAME_PATTERN);
    private Pattern titlePattern = Pattern.compile(TITLE_PATTERN);
    private Pattern descriptionPattern = Pattern.compile(DESCRIPTION_PATTERN);
    private Pattern examplesPattern = Pattern.compile(EXAMPLES_PATTERN);
    private Pattern templatePattern = Pattern.compile(TEMPLATE_PATTERN);
    private Pattern returnTypePattern = Pattern.compile(RETURN_TYPE_PATTERN);



    private boolean isValidateCodingBatId(String codingBatId) {
        return codingBatIdPattern.matcher(codingBatId).matches();
    }
    private boolean isValidateGroupName(String groupName) {
        return groupNamePattern.matcher(groupName).matches();
    }
    private boolean isValidateTitle(String title) {
        return titlePattern.matcher(title).matches();
    }
    private boolean isValidateDescription(String description){
        return descriptionPattern.matcher(description).matches();
    }
    private boolean isValidateExamples(String examples) {
        return examplesPattern.matcher(examples).matches();
    }
    private boolean isValidTemplate(String template) {
        return templatePattern.matcher(template).matches();
    }
    private boolean isValidateReturnType(String returnType) {
        return returnTypePattern.matcher(returnType).matches();
    }



    @Override
    public boolean validate(CodingBatTask entity) throws AppValidationException {
        AppValidationException exceptionMessageContainer = new AppValidationException();
        if (!isValidateCodingBatId(entity.getCodingBatId())) {
            exceptionMessageContainer.addMessage(String.format("codingBatId %s is invalid, recommendation %s",
                    entity.getCodingBatId(), "codingBatId must start with the letter \"p\" and contains digits\n" +
                            "length at least 2 characters and maximum of 10"));
        }
        if (!isValidateGroupName(entity.getGroupName())) {
            exceptionMessageContainer.addMessage(String.format("groupName %s is invalid, recommendation %s",
                    entity.getGroupName(), "can contains letters from a-zA-z\n" +
                            "can contains digits from 0-9\n" +
                            "can contains special symbols \"_ . -\"\n" +
                            "length at least 3 characters and maximum of 20"));
        }
        if (!isValidateTitle(entity.getTitle())) {
            exceptionMessageContainer.addMessage(String.format("title %s is invalid, recommendation %s",
                    entity.getTitle(), "can contains letters from a-zA-z\n" +
                            "can contains digits from 0-9\n" +
                            "can contains special symbols \"_ . -\"\n" +
                            "length at least 3 characters and maximum of 20"));
        }
        if (!isValidateDescription(entity.getDescription())) {
            exceptionMessageContainer.addMessage(String.format("description %s is invalid, recommendation %s",
                    entity.getDescription(), "can contains any character\n" +
                            "length at least 10 characters and maximum of 200"));
        }
        if (!isValidateExamples(entity.getExamples())) {
            exceptionMessageContainer.addMessage(String.format("examples %s is invalid, recommendation %s",
                    entity.getExamples(), "can contains any character\n" +
                            "length at least 10 characters and maximum of 200"));
        }
        if (!isValidTemplate(entity.getTemplate().trim())) {
            exceptionMessageContainer.addMessage(String.format("%s template is invalid, recommendation %s",
                    entity.getTemplate(), "example of valid template\n " +
                            "public void method(argType1 argName1, argType1 argName1){}"));
        }
        if (!isValidateReturnType(entity.getMethodSignature().getReturnType())) {
            exceptionMessageContainer.addMessage(String.format("returnType %s is invalid, recommendation %s",
                    entity.getMethodSignature().getReturnType(),"can be all data types in java and arrays\n" +
            "and some java collections"));
        }
        if (entity.getTaskTestDataContainer().getTaskTestDataList().size() < 3) {
            exceptionMessageContainer.addMessage("Not enough taskTestData. Min taskTestData = 3");
        }
        if(!exceptionMessageContainer.getExceptionMessageList().isEmpty()){
            throw exceptionMessageContainer;
        }
        return true;
    }

}
