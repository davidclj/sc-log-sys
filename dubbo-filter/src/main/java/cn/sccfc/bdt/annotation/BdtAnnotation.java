package cn.sccfc.bdt.annotation;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface BdtAnnotation {
    String param1() default "";
    String param2() default "" ;
    String param3() default "" ;
    String param4() default "" ;
}