import ua.artcode.utils.dynamic_compile.MethodInvoker;

public class _sum02996 implements MethodInvoker {

    public _sum02996() {
    }

    @Override
    public Object call(Object...args) {
                int arg0 = (int) args[0];
                int arg1 = (int) args[1];
                return sum(arg0,arg1);
    }

public String sum(int a, int b){return a-b;}

}
