package J05_dynamic.J0503_proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * cglib 动态代理
 */
public class EnhanceClassTest {

    static class UserService{

        public void test(){
            System.out.println("-----test-----");
        }

    }


    public static void main(String[] args) {
        UserService userService = new UserService();

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(UserService.class);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.println("enter-----");
                method.invoke(userService,objects);
                methodProxy.invokeSuper(o,objects);
                System.out.println("leave------");
                return null;
            }
        });

        //创建代理类对象，userServiceProxy指向代理类对象UserService$$EnhancerByCGLIB
        UserService userServiceProxy = (UserService) enhancer.create();
        userServiceProxy.test();
    }

}
