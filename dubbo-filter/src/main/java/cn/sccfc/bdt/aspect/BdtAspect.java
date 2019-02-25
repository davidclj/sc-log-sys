package cn.sccfc.bdt.aspect;

import cn.sccfc.bdt.AnnotationResolver;
import cn.sccfc.bdt.annotation.BdtAnnotation;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
@Aspect
public class BdtAspect {

    private static final Logger logger = LoggerFactory.getLogger(BdtAspect.class);


    @Pointcut(value = "@annotation(cn.sccfc.bdt.annotation.BdtAnnotation)")
    public void pointCut(){}

    @Before(value = "pointCut() && @annotation(bdtAnnotation)")
    public void before(JoinPoint joinPoint,BdtAnnotation bdtAnnotation) {

        Object param1 = AnnotationResolver.newInstance().resolver(joinPoint, bdtAnnotation.param1());
        logger.debug("parsm1={},parsm2={},parsm3={},parsm4={}", param1, bdtAnnotation.param2(), bdtAnnotation.param3(), bdtAnnotation.param4());

    }

    @AfterReturning(value = "pointCut() && @annotation(bdtAnnotation)",returning = "result")
    public void after(BdtAnnotation bdtAnnotation,Object result) {
        //do something...
        logger.info("the result of this method is {}" , result );
    }

//    @Around("@annotation(cn.sccfc.bdt.annotation.BdtAnnotation)")
//    public Object around(JoinPoint joinPoint) {
//
//        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
//        Method method = methodSignature.getMethod();
//        BdtAnnotation bdtAnnotation = method.getAnnotation(BdtAnnotation.class);
//
//        Object param1 = AnnotationResolver.newInstance().resolver(joinPoint, bdtAnnotation.param1());
//        Object param2 = AnnotationResolver.newInstance().resolver(joinPoint, bdtAnnotation.param2());
//        Object param3 = AnnotationResolver.newInstance().resolver(joinPoint, bdtAnnotation.param3());
//        Object param4 = AnnotationResolver.newInstance().resolver(joinPoint, bdtAnnotation.param4());
//
//        logger.debug("parsm1={},parsm2={},parsm3={},parsm4={}", param1, param2, param3, param4);
//
//        return null;
//
//    }
}
