package thread_creation;

public class MethodReferenceApproach {

    public void m1(){
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName()+" Executing "+i);
        }
    }

    public static void main(String[] args) {

        Runnable r=new MethodReferenceApproach()::m1;

        Thread t=new Thread(r,"first thread");
        t.start();

        new Thread(new MethodReferenceApproach()::m1,"second thread").start();
    }
}
