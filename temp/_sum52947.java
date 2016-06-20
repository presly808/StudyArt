import ua.artcode.utils.dynamic_compile.MethodInvoker;

public class _sum52947 implements MethodInvoker {

    public _sum52947() {
    }

    @Override
    public Object call(Object...args) {
                int arg0 = (int) args[0];
                int arg1 = (int) args[1];
                return array(arg0,arg1);
    }

public int[] array(int a, int b){return  new int[]{a,b};}

}
