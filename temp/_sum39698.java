import ua.artcode.utils.dynamic_compile.MethodInvoker;

public class _sum39698 implements MethodInvoker {

    public _sum39698() {
    }

    @Override
    public Object call(Object...args) {
                byte arg0 = (byte) args[0];
                byte arg1 = (byte) args[1];
                return sum(arg0,arg1);
    }

public byte sum(byte a,byte b){return a-b;}

}
