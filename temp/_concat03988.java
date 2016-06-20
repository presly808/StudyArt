import ua.artcode.utils.dynamic_compile.MethodInvoker;

public class _concat03988 implements MethodInvoker {

    public _concat03988() {
    }

    @Override
    public Object call(Object...args) {
                char arg0 = (char) args[0];
                char arg1 = (char) args[1];
                return concat(arg0,arg1);
    }

public String concat(char word, char b){return word+b;}

}
