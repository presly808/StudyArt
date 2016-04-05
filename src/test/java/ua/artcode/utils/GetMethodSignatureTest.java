package ua.artcode.utils;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import ua.artcode.model.taskComponent.MethodSignature;
import ua.artcode.utils.codingbat.CodingBatTaskUtils;


/**
 * Created by Maxim on 08.12.2015.
 */
public class GetMethodSignatureTest {

    //private static CodingBatTaskUtils taskUtils;

    @BeforeClass
    public static void initCodingBatTaskUtils() {
       // taskUtils = new CodingBatTaskUtils();
    }

    @Test
    public void testReturnType() {
        MethodSignature methodSignature = CodingBatTaskUtils.getMethodSignature(
                "public boolean groupSum5(int start, int[] nums, int target) {\n" +
                "  \n" +
                "}");
        Assert.assertEquals("boolean", methodSignature.getReturnType());
    }

    @Test
    public void testReturnTypeWithoutPublic() {
        MethodSignature methodSignature = CodingBatTaskUtils.getMethodSignature(
                "boolean groupSum5(int start, int[] nums, int target) {\n" +
                        "  \n" +
                        "}");
        Assert.assertEquals("boolean", methodSignature.getReturnType());
    }

    @Test
    public void testNumInArgs() {
        MethodSignature methodSignature = CodingBatTaskUtils.getMethodSignature(
                "public boolean groupSum5(int start, int[] nums, int target) {\n" +
                        "  \n" +
                        "}");
        Assert.assertEquals(3, methodSignature.getInArgList().size());
    }

    @Test
    public void testNumInArgsWithoutSpaces() {
        MethodSignature methodSignature = CodingBatTaskUtils.getMethodSignature(
                "public boolean groupSum5(int start,int[] nums,int target) {\n" +
                        "  \n" +
                        "}");
        Assert.assertEquals(3, methodSignature.getInArgList().size());
    }

    @Test
    public void testArgsName() {
        MethodSignature methodSignature = CodingBatTaskUtils.getMethodSignature(
                "public boolean method(int start, int[] nums, String str) {\n" +
                        "  \n" +
                        "}");
        Assert.assertEquals("start", methodSignature.getInArgList().get(0).getName());
        Assert.assertEquals("nums", methodSignature.getInArgList().get(1).getName());
        Assert.assertEquals("str", methodSignature.getInArgList().get(2).getName());

    }

    @Test
    public void testArgsType() {
        MethodSignature methodSignature = CodingBatTaskUtils.getMethodSignature(
                "public boolean method(int start, int[] nums, String str) {\n" +
                        "  \n" +
                        "}");
        Assert.assertEquals("int", methodSignature.getInArgList().get(0).getType());
        Assert.assertEquals("int[]", methodSignature.getInArgList().get(1).getType());
        Assert.assertEquals("String", methodSignature.getInArgList().get(2).getType());

    }
}
