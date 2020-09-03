package com.xlh.study.networkcheck.aspectj;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.xlh.study.networkcheck.annotation.NoNetworkShow;
import com.xlh.study.networkcheck.utils.NetworkUtils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.lang.reflect.Method;

/**
 * @author: Watler Xu
 * time:2020/9/3
 * description: 以注解为切点，调用网络工具类判断有无网络，做对应逻辑处理
 * version:0.0.1
 */
@Aspect
public class NetworkCheckAspect {

    /**
     * 进行一个切点
     * 注解NetworkCheck的包名路径，此处建议Copy Reference
     */
    @Pointcut("execution(@com.xlh.study.networkcheck.annotation.NetworkCheck * *(..))")
    public void pointActionMethod() {

    }

    /**
     * 进行切面的处理
     * 填写切点的方法名，注意带上()
     *
     * @param proceedingJoinPoint
     * @throws Throwable
     */
    @Around("pointActionMethod()")
    public void aProceedingJoinPoint(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        Context context = null;

        // getThis() 当前切点方法所在的类
        final Object thisObject = proceedingJoinPoint.getThis();

        // 拿到上下文
        context = getContext(thisObject);

        if (context == null) {
            throw new IllegalAccessException("context == null");
        }

        if (NetworkUtils.isNetworkAvaiable(context)) {
            // 网络正常，使NetworkCheck注解修饰的方法正常执行
            proceedingJoinPoint.proceed();
        } else {
            // 网络不正常，进行各种处理，以下多种处理方式，可任选其一

            // 方式一：普通toast
            Toast.makeText(context, "没有网络", Toast.LENGTH_LONG).show();

            // 方式二：显示NoNetworkShow注解中的处理方式，使用反射找到注解
            /*Class<?> thisObjectClass = thisObject.getClass();
            Method[] declaredMethods = thisObjectClass.getDeclaredMethods();
            for (Method method : declaredMethods) {
                method.setAccessible(true);// 让虚拟机跳跃不要检测
                boolean annotationPresent = method.isAnnotationPresent(NoNetworkShow.class);

                if (annotationPresent) {
                    method.invoke(thisObject);
                }
            }*/

            // 方式三：跳转到网络设置
//            NetworkUtils.goNetSettings(context);
        }
    }

    /**
     * 通过对象获取上下文
     *
     * @param object
     * @return
     */
    private Context getContext(Object object) {
        if (object instanceof Activity) {
            return (Activity) object;
        } else if (object instanceof Fragment) {
            Fragment fragment = (Fragment) object;
            return fragment.getActivity();
        } else if (object instanceof View) {
            View view = (View) object;
            return view.getContext();
        }
        return null;
    }

}
