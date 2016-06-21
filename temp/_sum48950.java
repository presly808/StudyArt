import ua.artcode.utils.dynamic_compile.MethodInvoker;

public class _sum48950 implements MethodInvoker {

    public _sum48950() {
    }

    @Override
    public Object call(Object...args) {
                int arg0 = (int) args[0];
                int arg1 = (int) args[1];
                return array(arg0,arg1);
    }

public int[] array(int a, int b){return  new int[]{a,b};}

}
