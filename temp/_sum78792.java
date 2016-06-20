import ua.artcode.utils.dynamic_compile.MethodInvoker;

public class _sum78792 implements MethodInvoker {

    public _sum78792() {
    }

    @Override
    public Object call(Object...args) {
                int arg0 = (int) args[0];
                int arg1 = (int) args[1];
                return sum(arg0,arg1);
    }

public int sum(int a, int b){return a+b;}

}
