package org.example.todoapispringapplication;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeMonitorAspect {

    @Around("@annotation(TimeMonitor)")
    public void logTime(ProceedingJoinPoint joinPoint)
    {
        long startTime = System.currentTimeMillis();

        //excute the join point
        try {
            //excute the join point
            joinPoint.proceed();
        }
        catch(Throwable e)
        {
            System.out.println("something went wrong "+e.getMessage());

        }
        finally {
            long end=System.currentTimeMillis();
            long diff=end-startTime;
            System.out.println("Total time of execution of the method is: "+diff+"ms");
        }


    }
}
