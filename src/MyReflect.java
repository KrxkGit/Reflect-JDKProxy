import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class MyReflect {
    public static void Reflect() throws ClassNotFoundException, NoSuchMethodException,
            InvocationTargetException, InstantiationException, IllegalAccessException {
        Class cl = Class.forName("MyBean");
        Constructor constructor = cl.getConstructor();
        Object obj = constructor.newInstance();
        if (obj instanceof MyBean) {
            MyBean myBean = (MyBean)obj;
            myBean.setNum(2);
            System.out.println(myBean.getNum());
        }
    }
}

class MyBean {
    private int num;

    public MyBean() {
    }

    void setNum(int num) {
        this.num = num;
    }
    int getNum() {
        return this.num;
    }
}