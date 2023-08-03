import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

interface MyServices {
    void doThing();
}

class MyTools implements MyServices {
    @Override
    public void doThing() {
        System.out.println(this.getClass().getName() + " DoThing");
    }
}
class MyProxyHandler implements InvocationHandler {
    private Object target;
    public Object bind(Object target) {
        this.target = target;
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(),
                this);
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("执行前");
        Object result = method.invoke(this.target, args);
        System.out.println("执行后");
        return result;
    }
}

public class MyProxy {
    public static void Test() {
        MyServices myServices = new MyTools(); // 创建实现类
        MyServices proxy = (MyServices) new MyProxyHandler().bind(myServices); // 创建代理类对象
        myServices.doThing();
        proxy.doThing();
    }
}