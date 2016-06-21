import ua.artcode.utils.dynamic_compile.MethodInvoker;

public class _concat25064 implements MethodInvoker {

    public _concat25064() {
    }

    @Override
    public Object call(Object...args) {
                char arg0 = (char) args[0];
                char arg1 = (char) args[1];
                return concat(arg0,arg1);
    }

public String concat(char word, char b){return word+b;}

}
