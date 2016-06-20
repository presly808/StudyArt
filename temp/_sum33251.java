import ua.artcode.utils.dynamic_compile.MethodInvoker;

public class _sum33251 implements MethodInvoker {

    public _sum33251() {
    }

    @Override
    public Object call(Object...args) {
                Long arg0 = (Long) args[0];
                Long arg1 = (Long) args[1];
                return sum(arg0,arg1);
    }

public long sum(long a, long b){return a+b;}

}
