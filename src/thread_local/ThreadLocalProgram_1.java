package concepts.threads.thread_local;

/**
 * Created by ritmukherjee on 9/20/2016.
 */
public class ThreadLocalProgram_1 {
    public static void main(String[] args) {

        /*Instance creation of ThreadLocal variable*/
        ThreadLocal tl1=new ThreadLocal();

        /*before setting any value to Threadlocal variable, it always null and getMethod() return null*/
        System.out.println("value of thread Local tl1 when initial value not override   :"+tl1.get());

        /*we can set a value to Thread local variable by set(Object newValue) method*/
        tl1.set("Ritam");
        System.out.println("After setting value at tl1  :" + tl1.get());

        /*We can also remove the value from ThreadLocal variable by using remove method*/
        tl1.remove();

        /*After removing ThreadLocal variable also initialValue() method internally call
                which always return null*/
        System.out.println("After remove value from tl1 :" + tl1.get());
        System.out.println("------------------------------------");

       /* Over ride of initial value of ThreadLocal variable*/
        ThreadLocal tl2=new ThreadLocal(){
            @Override
            protected Object initialValue() {
                return "abc";
            }
        };

      /*  After overiding Thread local variable not holding null value its holding abc which we have overridden.*/
        System.out.println("value of thread Local tl2 when initial value is overridden   :"+tl2.get());

        /*we can set a value to Thread local variable by set(Object newValue) method which also overridden*/
        tl2.set("Ritam");
        System.out.println("After setting value at tl2  :" +tl2.get());
        tl2.remove();

        /*After remove if we want to acess ThreadLocal variable it calls default initialValue() method
                so now it will not return abc not null*/
        System.out.println("After remove value from tl1 :" + tl2.get());
        System.out.println("------------------------------------");
    }
}
