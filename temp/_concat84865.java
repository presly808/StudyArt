import ua.artcode.utils.dynamic_compile.MethodInvoker;

public class _concat84865 implements MethodInvoker {

    public _concat84865() {
    }

    @Override
    public Object call(Object...args) {
                String arg0 = (String) args[0];
                Integer arg1 = (Integer) args[1];
                return concat(arg0,arg1);
    }

public String concat(String word, int b){return word+b;}

}
