import ua.artcode.utils.dynamic_compile.MethodInvoker;

public class _concat44380 implements MethodInvoker {

    public _concat44380() {
    }

    @Override
    public Object call(Object...args) {
                String arg0 = (String) args[0];
                int arg1 = (int) args[1];
                return concat(arg0,arg1);
    }

public String concat(String word, int b){return word+b;}

}
