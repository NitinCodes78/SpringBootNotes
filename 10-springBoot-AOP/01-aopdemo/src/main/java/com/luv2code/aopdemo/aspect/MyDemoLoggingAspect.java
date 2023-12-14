package com.luv2code.aopdemo.aspect;

import com.luv2code.aopdemo.entity.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import java.util.List;
@Aspect
@Component
public class MyDemoLoggingAspect {

    // add a new advice for @AfterReturning on the findAccounts method

    //result is something that will be returned by the method
    @AfterReturning(
            pointcut = "execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))", returning = "result")
    public void afterReturningFindAccountsAdvice(JoinPoint theJoinPoint, List<Account> result){

        String method=theJoinPoint.getSignature().toShortString();
        System.out.println("\n====>> Executing @AfterReturning on method: "+method);

//        System.out.println("\n======>> result is: "+ result);

        //Modifying the Return value according to the problem need

        if(!result.isEmpty()){
            Account tempAccount=result.get(0);
            tempAccount.setName("Dafffy Duckkkk");
        }
        converAccountNamesToUpperCase(result);
    }

    private void converAccountNamesToUpperCase(List<Account> result) {
         for(Account tempAccount:result){
             String upperCaseName=tempAccount.getName().toUpperCase();
             tempAccount.setName(upperCaseName);
         }
    }


    //this is where we add all of our related advices for logging


    //let's start with an @Before advice

    //This is the pointcut expression of the before advice that tells to run at this point i.e. before the execution of addAccount()

    //Any return type with any className, with any method name and whatsoever may be the arguments
    @Pointcut("execution(* com.luv2code.aopdemo.dao.*.*(..))")
    private void forDAOPackage(){}
    //This is a sort of variable name rather than writing this whole query if it's used n number of times

//    @Before("execution(public void updateAccount())")

    //pointcut for getter methods
    @Pointcut("execution(* com.luv2code.aopdemo.dao.*.get*(..))")
    private void getter(){}
    //pointcut for setter methods
    @Pointcut("execution(* com.luv2code.aopdemo.dao.*.set*(..))")
    private void setter(){}
    //pointcut to include package and exclude getter/setter
    @Pointcut("forDAOPackage() && !(getter() || setter())")
    private void forDaoPackageNoGetterSetter(){}

//    @Before("forDaoPackageNoGetterSetter()")
//    public void beforeAddAccountAdvice(){
//         System.out.println("\n======>>>> Executing @Before advice on updateAccount()");
//    }

    @Before("forDaoPackageNoGetterSetter()")
    public void performAnalytics(JoinPoint theJoinPoint){
        System.out.println("\n =====>>> Performing API analytics");

        //JoinPoint has metadata about method call

        //display the method signature

        MethodSignature methodSignature=(MethodSignature) theJoinPoint.getSignature();
        System.out.println("Method: "+methodSignature);

        //display method arguments

        //get args

        Object[] args=theJoinPoint.getArgs();

        //loop thru args

        for(Object tempArg : args){
            System.out.println(tempArg);
        }
    }

}
