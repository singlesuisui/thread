package com.sofia.semaphore;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 仓库类Storage实现缓冲区 
 *
 * Email:530025983@qq.com 
 *
 * @author MONKEY.D.MENG 2011-03-15 
 *
 */
public class Storage {
    // 仓库最大存储量  
    private final int MAX_SIZE = 200;

    // 仓库存储的载体  
    private LinkedList<Object> list = new LinkedList<Object>();

    // 生产num个产品  
    public void produce(int num,String name)
    {
        // 同步代码段  
        synchronized (list)
        {
            // 如果仓库剩余容量不足  
            while (list.size() + num > MAX_SIZE)
            {
                System.out.println("【要生产的产品数量】:" + num + "    【库存量】:"
                        + list.size() + "    暂时不能执行生产任务!");
                try
                {
                    // 由于条件不满足，生产阻塞  
                    list.wait();
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }

            // 生产条件满足情况下，生产num个产品  
            for (int i = 1; i <= num; ++i)
            {
                list.add(new Object());
            }

            System.out.println(name+"【已经生产产品数】:" + num + "    【现仓储量为】:" + list.size());

            list.notifyAll();
        }
    }

    // 消费num个产品  
    public void consume(int num,String name)
    {
        // 同步代码段  
        synchronized (list)
        {
            // 如果仓库存储量不足  
            while (list.size() < num)
            {
                System.out.println("【要消费的产品数量】:" + num + "    【库存量】:"
                        + list.size() + "    暂时不能执行消费任务!");
                try
                {
                    // 由于条件不满足，消费阻塞  
                    list.wait();
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }

            // 消费条件满足情况下，消费num个产品  
            for (int i = 1; i <= num; ++i)
            {
                list.remove();
            }

            System.out.println(name+"【已经消费产品数】:" + num + "    【现仓储量为】:" + list.size());

            list.notifyAll();
        }
    }

    // get/set方法  
    public LinkedList<Object> getList()
    {
        return list;
    }


    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(20);
        // 仓库对象
        Storage storage = new Storage();
        List<Runnable> runnable = new ArrayList<Runnable>();

        // 生产者对象
        Producer p1 = new Producer(storage,"生产者1");
        Producer p2 = new Producer(storage,"生产者2");
        Producer p3 = new Producer(storage,"生产者3");
        Producer p4 = new Producer(storage,"生产者4");
        Producer p5 = new Producer(storage,"生产者5");
        Producer p6 = new Producer(storage,"生产者6");
        Producer p7 = new Producer(storage,"生产者7");
        // 消费者对象"生产者1"
        Consumer c1 = new Consumer(storage,"消费者1");
        Consumer c2 = new Consumer(storage,"消费者2");
        Consumer c3 = new Consumer(storage,"消费者3");

        // 设置生产者产品生产数量
        p1.setNum(10);
        p2.setNum(10);
        p3.setNum(10);
        p4.setNum(10);
        p5.setNum(10);
        p6.setNum(10);
        p7.setNum(10);

        // 设置消费者产品消费数量
        c1.setNum(50);
        c2.setNum(20);
        c3.setNum(30);

        runnable.add(c1);
//        runnable.add(c2);
//        runnable.add(c3);
//        runnable.add(p1);
//        runnable.add(p2);
//        runnable.add(p3);
//        runnable.add(p4);
//        runnable.add(p5);
        runnable.add(p6);
        runnable.add(p7);


        for(Runnable object:runnable){
            service.execute(object);
        }
    }


}