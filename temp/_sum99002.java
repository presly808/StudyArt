import ua.artcode.utils.dynamic_compile.MethodInvoker;

public class _sum99002 implements MethodInvoker {

    public _sum99002() {
    }

    @Override
    public Object call(Object...args) {
                float arg0 = (float) args[0];
                float arg1 = (float) args[1];
                return sum(arg0,arg1);
    }

public float sum(float a, float b){return a+b;}

}
