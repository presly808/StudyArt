import ua.artcode.utils.dynamic_compile.MethodInvoker;

public class _sum51984 implements MethodInvoker {

    public _sum51984() {
    }

    @Override
    public Object call(Object...args) {
                Integer arg0 = (Integer) args[0];
                Integer arg1 = (Integer) args[1];
                return sum(arg0,arg1);
    }

public int sum(int a, int b){return a+b;}

}
